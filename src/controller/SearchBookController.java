package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import exception.BusinessException;
import exception.DatabaseException;

import vo.AddBookVO;
import vo.CartDataVO;
import vo.SearchBookVO;

import bo.AddBookBO;
import bo.SearchBookBO;

public class SearchBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger= Logger.getLogger("SearchController");
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    
		if(request.getSession(false)==null){
			System.out.println("Inside search book controller session");
			response.sendRedirect("/BookShopping/jsp/Login.jsp");
		} else {
		/********** Logged in successfully; populate search criteria from database ***************/
		if (getServletContext().getAttribute("userlogin")!=null || request.getParameter("userlogin")!=null) {
		     System.out.println("Userlogin and search");
			getServletContext().removeAttribute("userlogin");
			getServletContext().removeAttribute("searchResult");
			request.getSession(false).removeAttribute("result");
			ArrayList list = new ArrayList();
			SearchBookBO bo = new SearchBookBO();
			RequestDispatcher dispatch = request
			.getRequestDispatcher("/jsp/SearchingAddingBooks.jsp");
			try {
			list = bo.searchBookCriteria();
			request.getSession(false).setAttribute("searchCriteria", list);
			//request.setAttribute("searchCriteria", "header");
			
			logger.info("Search Criteria from database is fetched");
	
	         response.sendRedirect("/BookShopping/jsp/SearchingAddingBooks.jsp");
			
			} catch(DatabaseException de) {
				DatabaseException db = new DatabaseException();
				request.setAttribute("DbError", de.getErrorMessage());
				dispatch.forward(request, response);
			}
		}

		/******* Search Button is clicked; Fetches matching data from database ************/
		else if (request.getParameter("search") != null) {
			System.out.println("Inside search button in controller");
		    request.getSession(false).removeAttribute("result");
			ArrayList list;
			SearchBookVO vo = new SearchBookVO();
			vo.setCategory(request.getParameter("category"));
			if (!request.getParameter("price").equals("")) {
				vo.setPrice(Float.parseFloat(request.getParameter("price")));
			}
			vo.setLanguage(request.getParameter("language"));
			vo.setBinding(request.getParameter("binding"));
			vo.setDeliveryTime(request.getParameter("deliverytime"));
			SearchBookBO bo = new SearchBookBO();
			RequestDispatcher dispatch = request
			.getRequestDispatcher("/jsp/SearchingAddingBooks.jsp");
			try {
			list = bo.setQuery(vo);
			getServletContext().setAttribute("searchResult", list);
			//request.setAttribute("searchResult", "SearchResult");
			logger.info("search result is returned");
			dispatch.forward(request,response);
			//dispatch.forward(request, response);
			} catch(DatabaseException de) {
				DatabaseException d = new DatabaseException();
				request.setAttribute("DbError", d.getErrorMessage());
				dispatch.forward(request, response);
			}
			
			
			
		}

		/*********** AddCart button is clicked; Saves data into Cart_details *********/
		else if (request.getParameter("addcart") != null) {
			ArrayList<CartDataVO> list = new ArrayList<CartDataVO>();
			request.getSession(false).removeAttribute("result");
			String items[] = request.getParameterValues("items");

			/******* If items in not null means atleast one book is selected ***********/
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					CartDataVO vo = new CartDataVO();
					vo.setBookId(Integer.parseInt(items[i]));
					try {
						vo.setQuantity(Integer.parseInt(request
								.getParameter(items[i])));
					} catch (NumberFormatException e) {
						vo.setTextQuantity(request.getParameter(items[i]));
						vo.setQuantity(-999);
					}
					list.add(vo);
				}

				HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) getServletContext()
						.getAttribute("availability");
				AddBookBO bo = new AddBookBO();
				
				try {
					ArrayList<CartDataVO> result = bo.validateBookData(list, map);
				
				/********** If result is contains false means quantity is not invalid ********************/
				if (result.contains(false)) {

					RequestDispatcher dispatch = request
					.getRequestDispatcher("/jsp/SearchingAddingSuccess.jsp");
					 try {
					ArrayList result1 = bo
							.addBook(list, map, (Integer) request.getSession(false).getAttribute("cartid"));
					ArrayList<AddBookVO> cartData = (ArrayList<AddBookVO>) result1.get(0);
					ArrayList<CartDataVO> successData = (ArrayList<CartDataVO>)result1.get(1);
					getServletContext().setAttribute("searchResult", cartData);
					request.getSession(false).setAttribute("addsuccess", successData);
					logger.info("Books added to cart");
					response.sendRedirect("/BookShopping/jsp/SearchingAddingSuccess.jsp");

					 } catch(DatabaseException de) {
					     DatabaseException d = new DatabaseException();
					     request.setAttribute("DbError", d.getErrorMessage());
						 dispatch.forward(request, response);

					 }
					
					
				} else {
					result.remove(true);
					//request.getSession(false).setAttribute("searchCriteria", "header");
					//request.getSession(false).setAttribute("searchResult", "SearchResult");
					request.getSession(false).setAttribute("result", result);
					request.getSession(false).setAttribute("Invalid Entry", "Invalid");
					BusinessException b = new BusinessException();
					throw b;
					
				}
				
                } catch(BusinessException be) {
					response.sendRedirect("/BookShopping/jsp/SearchingAddingBooks.jsp");
					}


			}

			/********** None book is selected **********/
			else {
				//request.getSession(false).setAttribute("searchCriteria", "header");
				//request.getSession(false).setAttribute("searchResult", "SearchResult");
				request.getSession(false).setAttribute("NoSelection",
						"Please select at least one book");
				logger.info("No book is selected");
				//RequestDispatcher dispatch = request
				//		.getRequestDispatcher("/jsp/SearchingAddingBooks.jsp");
				//dispatch.forward(request, response);
				response.sendRedirect("/BookShopping/jsp/SearchingAddingBooks.jsp");
			}
		}
	}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
