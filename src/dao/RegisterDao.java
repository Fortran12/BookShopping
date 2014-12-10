package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.QueryConstant;

import exception.ApplicationException;
import exception.BusinessException;
import exception.DatabaseException;



import util.DbConfigurationUtil;
import util.PropertyUtil;
import vo.RegisterVo;

public class RegisterDao {
	public static final Logger LOG = Logger.getLogger("RegisterBO");
	Connection conn=null;
	PreparedStatement ps =null;
	ResultSet rs=null;
	public ArrayList <String> errorList = new ArrayList<String>();
	public RegisterVo addCustomer(RegisterVo rvo) throws BusinessException, DatabaseException,ApplicationException, IOException {
		
		String cus_name=rvo.getCus_name();
		String cus_pass=rvo.getCus_pass();
		String cus_addr=rvo.getCus_addr();
		String cus_cont=rvo.getCus_cont();
		String cus_email=rvo.getCus_email();
		String cus_dob=rvo.getCus_dob();
	    
		try {
			 conn = DbConfigurationUtil.getConnection();                                  //establishing a condition
 
			 LOG.info(" Connection established" );	
			ps=conn.prepareStatement(QueryConstant.VALIDATE_REG);
			ps.setString(1,cus_email);
			rs=ps.executeQuery();
			
			if(rs.next())
			{ 
				LOG.info(" register already" );	
				BusinessException exp =new BusinessException();
				exp.setErrormsg(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR14,"/resource/ErrorCode"));
				throw exp;
			}
			else
			{
			ps=conn.prepareStatement(QueryConstant.INSERT_USER);
			ps.setString(1,cus_name);
			ps.setString(2,cus_pass);
			ps.setString(3,cus_dob);
			ps.setString(4,cus_addr);
			ps.setString(5,cus_cont);
			ps.setString(6,cus_email);
			int n=ps.executeUpdate();
			
			if(n==1)
			{  LOG.info("insection into database" );
			  ps=conn.prepareStatement(QueryConstant.VALIDATE_REG);              //checking whether the user register already
			   ps.setString(1,cus_email);
			   rs=ps.executeQuery();
			if(rs.next())
			{
				rvo.setCard_id(rs.getInt("cart_id"));                               //getting cartid from database
				rvo.setCus_id(rs.getInt("cust_id"));                               //getting customer id   from database
			}
			}
			}
		} catch (ClassNotFoundException e) {                                        //Application exception 
			ApplicationException appEx = new ApplicationException(
					e.getMessage());
			throw appEx;
		} catch (SQLException e) {                                                   //database exception
			DatabaseException dEx = new DatabaseException(e.getMessage());
			throw dEx;

		}
		
		return rvo;
	}
}
