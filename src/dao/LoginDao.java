package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.QueryConstant;

import util.DbConfigurationUtil;
import util.PropertyUtil;
import vo.LoginVo;

import exception.BusinessException;
import exception.DatabaseException;

public class LoginDao {
	public static final Logger LOG = Logger.getLogger("LoginBO");
	public ArrayList <String> errorList = new ArrayList<String>();
    public LoginVo varify(LoginVo lvo) throws BusinessException, DatabaseException, IOException
{
    	
Connection conn=null;
 PreparedStatement ps =null;
ResultSet rs=null;
String pass=lvo.getCus_pass();
String cusid=lvo.getCus_id();
int id = 0;
try {
	   id = Integer.parseInt(lvo.getCus_id());
	} catch(NumberFormatException e) {
		LOG.info("invalid password or customerid" );
		BusinessException exp =new BusinessException();                                        //if invalid throws business exception
		exp.setErrormsg(PropertyUtil.getErrorMessage(ErrorConstant.LOGIN_ERROR3,"/resource/ErrorCode"));
		throw exp;
	}

try {
	 conn = DbConfigurationUtil.getConnection();                                              //establishing connection
	 LOG.info("database connection established" );
	ps=conn.prepareStatement(QueryConstant.USER_EXISTS);                                       //validating id and password
    ps.setInt(1,id);
	ps.setString(2,pass);
	
	rs=ps.executeQuery();
	
	if(rs.next())
	{ 
		
	 lvo.setCart_id(rs.getInt("cart_id"));                                                    //if valid customer getting customer id
	
	}
	else
	{
		 LOG.info("invalid password or customerid" );
		BusinessException exp =new BusinessException();                                        //if invalid throws business exception
		exp.setErrormsg(PropertyUtil.getErrorMessage(ErrorConstant.LOGIN_ERROR3,"/resource/ErrorCode"));
		throw exp;
	}
} catch (ClassNotFoundException e) {
	
	e.printStackTrace();
}
		catch (SQLException e) {
			DatabaseException dEx = new DatabaseException(e.getMessage());
			throw dEx;

		}

 
return lvo;

}
}
