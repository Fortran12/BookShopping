package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import vo.LoginVo;
import bo.LoginBo;
import exception.BusinessException;
import exception.DatabaseException;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("LoginController");
	private static final long serialVersionUID = 1L;
    
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		if(request.getSession(false).getAttribute("Userid")==null && request.getParameter("userlogin")==null){
		
				response.sendRedirect("/BookShopping/jsp/Login.jsp");
			
		} else {
		LOG.info("Inside method-doPost in LoginController");
        String cus_id=request.getParameter("customerid");
        String cus_pass=request.getParameter("customerpassword");
        LoginVo lvo=new LoginVo();                                                     //creating object for LoginVo
        lvo.setCus_pass(cus_pass);                                                      //setting value to loginVo object
        lvo.setCus_id(cus_id);
        LoginBo lbo=new LoginBo();
        try {
     	 LoginVo lvo1=new LoginVo();                                                      //creating object to get values from dao
			lvo1=lbo.validate(lvo);                                                       //calling login Bo
			HttpSession session = request.getSession();                                   //creating session
			session.setAttribute("Userid",lvo1.getCus_id());
			session.setAttribute("cartid",lvo1.getCart_id());
			getServletContext().setAttribute("userlogin", "UserLogin");
			response.sendRedirect("SearchBookController");
		} catch (BusinessException e) {
			request.setAttribute("errors",e.getError1());                                  //getting errors
            request.setAttribute("error", e.getErrormsg());
           	RequestDispatcher dis=request.getRequestDispatcher("/jsp/Login.jsp");
			try {
				dis.forward(request, response);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		} catch (DatabaseException e) {                                                    //database exception
			 request.setAttribute("Messege", e.getMessage());
			RequestDispatcher dis=request.getRequestDispatcher("/jsp/Login.jsp");
			try {
				dis.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			request.setAttribute("Messege", e.getMessage());
			RequestDispatcher dis=request.getRequestDispatcher("/jsp/Login.jsp");
			try {
				dis.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	}
	}


