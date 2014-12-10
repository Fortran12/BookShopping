package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.SuccessConstant;
import exception.BusinessException;
import util.PropertyUtil;
import vo.QuantityErrorVO;
import vo.successVO;
import vo.viewVO;
import bo.viewBO;

/**
 * Servlet implementation class viewController
 */
public class viewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger("ViewContoller");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession(false).getAttribute("Userid") == null) {
			response.sendRedirect("/BookShopping/jsp/Login.jsp");
		} else {
			// TODO Auto-generated method stub
			// if(request.get)
			boolean flag = true;

			String b_id = (String) request.getParameter("book_id");
			// System.out.println(b_id);

			BusinessException be = new BusinessException();
			ArrayList<String> err = new ArrayList<String>();
			RequestDispatcher rd = request
					.getRequestDispatcher("/BookShopping/jsp/EditRemove.jsp");
			RequestDispatcher rd4 = request
					.getRequestDispatcher("/jsp/EditRemove.jsp");
			RequestDispatcher rd1 = request
					.getRequestDispatcher("/jsp/Update.jsp");
			RequestDispatcher rd3 = request
					.getRequestDispatcher("/BookShopping/jsp/success.jsp");
			ServletConfig conf = getServletConfig();
			ServletContext context = conf.getServletContext();
			viewBO bo = new viewBO();
			ArrayList<viewVO> q;
			viewVO v = new viewVO();
			successVO svo = new successVO();
			LOG.info("ViewContoller Class : DoGet Method");

			/* Control to Controller from jsp */
			if (request.getParameter("update") == null) {

				// System.out.println("inside");
				request.getSession(false).removeAttribute("errors");
				viewBO b = new viewBO();
				LOG.info("ViewContoller Class : DoGet Method. To retrieve book details");
				ArrayList<viewVO> l = b.viewBook((Integer) request.getSession(
						false).getAttribute("cartid"));
				/* Saving vo items from dao_to_bo in in l */
				request.getSession(false).setAttribute("view", l);
				/* setting vo in a context */
				context.setAttribute("viewL", l);
				request.getRequestDispatcher("/jsp/EditRemove.jsp").forward(request, response);
				//response.sendRedirect("/BookShopping/jsp/EditRemove.jsp");

			}

			/* from homepage to controller */
			else if (request.getParameter("update") != null) {
				/* retrieves those books that are checked */
				LOG.info("ViewContoller Class : DoGet Method. Toupdate those books that are checked");
				QuantityErrorVO errvo = new QuantityErrorVO();
				Enumeration<String> enu = request.getParameterNames();
				String[] books = new String[100];
				String[] quantity = new String[books.length];
				int i = 0;
				while (enu.hasMoreElements()) {
					String check = enu.nextElement();
					String qty;
					if (!check.equals("update")) {
						books[i] = check;// bookid
						quantity[i] = request.getParameter(books[i]);

						i++;
					}
				}

				System.out.println("lenght " + books.length);
				for(int j=0;j<i;j++) {
					System.out.println(books[j]+ "  " + quantity[j]);
				}
				
				try {
				viewBO BO = new viewBO();
				ArrayList<successVO> s1 = BO.validate((ArrayList<viewVO>)context.getAttribute("viewL"),books, quantity,i,(Integer)request.getSession(false).getAttribute("cartid"));
				request.getSession(false).setAttribute("success",s1);
				response.sendRedirect("/BookShopping/jsp/success.jsp");
				}
				catch(BusinessException e)
				{
					request.getSession(false).setAttribute("errors",PropertyUtil.getErrorMessage(
							ErrorConstant.INVALID_INPUT2,
							"/resource/ErrorCode"));
					response.sendRedirect("/BookShopping/jsp/EditRemove.jsp");
				}
		
				//ArrayList<successVO> success_list = new ArrayList<successVO>();
				//successVO s2 = new successVO();
				
				
				

			}/*
			 * catch (BusinessException b) { request.setAttribute("errors",
			 * b.errorList);
			 * System.out.println("inside bexception size :"+b.errorList
			 * .size());
			 * //System.out.println("inside business exception:"+b.errorList
			 * .get(1)); //System.out.println(b.getErrorList());
			 * LOG.error("ViewContoller Class : DoGet Method. Business Exception"
			 * +b.errorList); //viewBO b1 = new viewBO(); //ArrayList<viewVO> l
			 * =
			 * b1.viewBook((Integer)request.getSession(false).getAttribute("cartid"
			 * )); // Saving vo items from dao_to_bo in in l q =
			 * (ArrayList<viewVO>) context.getAttribute("viewL");
			 * request.setAttribute("view", q); rd4.forward(request, response);
			 * return ; }
			 */

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}