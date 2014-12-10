package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import exception.ApplicationException;
import exception.BusinessException;
import exception.DatabaseException;

import bo.RegisterBo;

import util.PropertyUtil;
import vo.RegisterVo;

public class RegisterController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("RegisterController");
	private static final long serialVersionUID = 1L;
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		doPost(request,response);	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		LOG.info("Inside method-doPost in RegisterController");
		String cus_name=request.getParameter("name");
		String cus_pass=request.getParameter("userpassword");
		String cus_dob=request.getParameter("dob");
		String cus_addr=request.getParameter("addr");
		String cus_cont=request.getParameter("phno");
		String cus_email=request.getParameter("mail");
		RegisterVo rvo=new RegisterVo();                                              //creating object for RegisterVo
		rvo.setCus_addr(cus_addr);                                                   //setting values to vo object
		rvo.setCus_cont(cus_cont);
		rvo.setCus_dob(cus_dob);
		rvo.setCus_email(cus_email);
		rvo.setCus_name(cus_name);
		rvo.setCus_pass(cus_pass);
		
		RegisterBo rbo=new RegisterBo();
		try {
			RegisterVo rvo1=new RegisterVo();
			LOG.info("calling RegisterBo");
			rvo1=rbo.validateCustomer(rvo);                                      //calling RegisterBo
			int cust_id=rvo1.getCus_id();
			int cart_id=rvo1.getCard_id();
		//	request.setAttribute("id", cust_id);
			//request.setAttribute("caid", cart_id);
			getServletContext().setAttribute("id", cust_id);
			getServletContext().setAttribute("caid", cart_id);
			response.sendRedirect("/BookShopping/jsp/SuccessRegister.jsp");
		}
		catch (BusinessException e)
		{LOG.info("inside BusinessException ");
			request.setAttribute("errors",e.getError1());  
			request.setAttribute("error",e.getErrormsg());
			request.setAttribute("valid", rvo);
			RequestDispatcher dis=request.getRequestDispatcher("/jsp/Register.jsp");
			try {
				dis.forward(request, response);
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		 catch (DatabaseException e)           //database exception
		 {
			request.setAttribute("Messege", e.getMessage());
			RequestDispatcher dis=request.getRequestDispatcher("/jsp/Register.jsp");
			try {
				dis.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (ApplicationException e)                                   //application exception
		{
			String message=PropertyUtil.getErrorMessage(ErrorConstant.FATAL_ERROR,"/resource/ErrorCode");
			request.setAttribute("error",message);
			RequestDispatcher disp= request.getRequestDispatcher("Jsp/Register.jsp"); 
			try {
				disp.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (IOException e)
		{
			String message=PropertyUtil.getErrorMessage(ErrorConstant.FATAL_ERROR,"/resource/ErrorCode");
			request.setAttribute("error",message);
			RequestDispatcher disp= request.getRequestDispatcher("Jsp/Register.jsp"); 
			try {
				disp.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}
	}

