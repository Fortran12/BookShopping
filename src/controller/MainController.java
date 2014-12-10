package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger= Logger.getLogger("MainController");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("userlogin")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("LoginController");
			System.out.println("Inside login main controller");
			dispatch.forward(request, response);
		}
		else if(request.getParameter("fetchsearch")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchBookController");
			dispatch.forward(request, response);
		}
		/****** Search Button is clicked; Transfer to SearchController ***************/
		else if(request.getParameter("search")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchBookController");
			dispatch.forward(request, response);
		
		/******** AddCart Button is clicked; Transfer to SearchController  **********/
		} else if(request.getParameter("addcart")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchBookController");
			dispatch.forward(request, response);
		}
		else if(request.getParameter("payment")!=null) {
			RequestDispatcher dispatch = request.getRequestDispatcher("OrderPaymentController");
			dispatch.forward(request, response);
		}
		logger.info(" Inside MainControllerClass ");
	}

}
