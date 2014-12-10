package bo;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import constant.ErrorConstant;


import dao.LoginDao;

import util.PropertyUtil;
import vo.LoginVo;

import exception.BusinessException;
import exception.DatabaseException;

public class LoginBo {
	public static final Logger LOG = Logger.getLogger("LoginBO");
	public ArrayList <String> errorList = new ArrayList<String>();
	public LoginVo validate(LoginVo lvo) throws BusinessException, DatabaseException, IOException
	{
		LOG.info(" LOginBo Invoked" );	
			boolean flag=true;

//checking whether customerid is null
		if(lvo.getCus_id().isEmpty()||lvo.getCus_id()==null||lvo.getCus_id().equals(""))
		{  LOG.info(" validating customerId" );
			errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.LOGIN_ERROR1,"resource/ErrorCode"));
			flag=false;
		}
		else
		{
			errorList.add(" ");
			
		}
		                                                                                    //checking whether password is empty
		if(lvo.getCus_pass().isEmpty()||lvo.getCus_pass()==null||lvo.getCus_pass().equals(""))
		{
			LOG.info(" validating password" );
			errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.LOGIN_ERROR2,"resource/ErrorCode"));
			flag=false;
		}
		else
		{
			errorList.add(" ");
			
		}
		if(flag==true)
		{                                                                                 //transfer to dao
			LoginDao ldao=new LoginDao();
			LOG.info("calling  dao" );
			ldao.varify(lvo);
			
			return lvo;		
		}
		else
			
		{   LOG.info("in businessException" );
			BusinessException exp =new BusinessException();                                  //throwing business exception
			exp.setError1(errorList);
			throw exp;
		}
		
	}
}
