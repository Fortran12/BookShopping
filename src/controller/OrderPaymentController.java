package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import exception.BusinessException;
import exception.DatabaseException;

import bo.OrderPaymentBO;
import vo.OrderDetailsVO;
import vo.PaymentVO;

public class OrderPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger
			.getLogger("OrderPaymentController");

	public OrderPaymentController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		OrderPaymentBO paybo = new OrderPaymentBO();
		HttpSession session = request.getSession(false);
		List<PaymentVO> bookList;

		LOG.info("Inside method-doPost in OrderPayment Controller class");

		// Retrieving Book Details added to cart from database and forwarding to
		// the Payment Screen.
		if( request.getSession(false)==null) {
		response.sendRedirect("/BookShopping/jsp/Login.jsp");
		} else {
		if ( request.getParameter("payment") != null) {
			System.out.println("WE are in first payment");
			try {
				bookList = paybo.getBDetails((Integer)request.getSession(false).getAttribute("cartid")); // Book Details retrieved from
												// database.
				//request.setAttribute("bookList", bookList);
				session.removeAttribute("derror");
				session.removeAttribute("elist");
				session.removeAttribute("error");
				request.setAttribute("bookList", bookList);
				 // Book
																		// Details
																		// forwarded
																		// to
																		// Payment
																		// Screen.
				LOG.info("Exit-method doPost for receiving Book Details in OrderPaymentController");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/OrderPayment.jsp");
				rd.forward(request, response);
			
			}

			catch (DatabaseException de) {
				LOG.error("OrderPaymentController Class DatabaseException "
						+ de.getErrors4());
				session.setAttribute("derror", de.getErrors4());
				session.getAttribute("bookList");
				response.sendRedirect("/BookShopping/jsp/OrderPayment.jsp"); // Database
																		// Exception
																		// forwarded
																		// to
																		// Payment
			
			}

		}

		// Forwarding values to database after payment is done.
		else if (request.getParameter("makepayment") != null) {
			String accno = request.getParameter("accno");
			String bname = request.getParameter("bname");
			String ifsc = request.getParameter("bcode");
			String sum = request.getParameter("amt");
			float total = Float.parseFloat(sum);
			System.out.println("WE are in second payment");

			PaymentVO payto = new PaymentVO(); // Payment Details set to
												// PaymentTO
			payto.setAccno(accno);
			payto.setBname(bname);
			payto.setIfsc(ifsc);
			payto.setTotal(total);
			session.setAttribute("elist", payto);

			OrderDetailsVO oto;
			try {
				oto = paybo.insertODetails(payto, (Integer)request.getSession(false).getAttribute("cartid"), Integer.parseInt((String)request.getSession(false).getAttribute("Userid"))); // Order Details retrieved
													// from database.
				session.setAttribute("orderDetails", oto);
				getServletContext().setAttribute("orderDetails", oto);
				//session.getAttribute("bookList");
				LOG.info("Exit-method doPost for receiving Order Details in OrderPaymentController");
				response.sendRedirect("/BookShopping/jsp/OrderPaymentSuccess.jsp"); // Order
																				// Details
																				// forwarded
																				// to
																				// Payment
																				// Success
																				// Screen.
			
				//rd1.forward(request, response);
				
			} catch (BusinessException be) {
				LOG.error("OrderPaymentController Class BussinessException "
						+ be.getError4()); // Business Exception forwarded to
											// Payment Screen.
				session.setAttribute("error", be.getError4());
				//session.getAttribute("bookList");
				//session.getAttribute("elist");
				response.sendRedirect("/BookShopping/jsp/OrderPayment.jsp");
				
			} catch (DatabaseException de) {
				LOG.error("OrderPaymentController Class DatabaseException "
						+ de.getErrors4()); // Database Exception forwarded to
											// Payment Screen.
				session.setAttribute("derror", de.getErrors4());
						session.getAttribute("bookList");
				response.sendRedirect("/BookShopping/jsp/OrderPayment.jsp");
			}
		}

	}
	}
}
