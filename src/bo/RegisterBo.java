package bo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import dao.RegisterDao;
import util.PropertyUtil;
import vo.RegisterVo;
import dao.RegisterDao;
import exception.ApplicationException;
import exception.BusinessException;
import exception.DatabaseException;
import vo.RegisterVo;

public class RegisterBo {
	public static final Logger LOG = Logger.getLogger("RegisterBO");
	 public ArrayList <String> errorList = new ArrayList<String>();
  public RegisterVo validateCustomer(RegisterVo rvo) throws BusinessException, DatabaseException, IOException, ApplicationException
  {
		  boolean flag=true;
		  int l=rvo.getCus_pass().length();
		  //validating whether name is empty
		  if(rvo.getCus_name().isEmpty()||rvo.getCus_name()==null||rvo.getCus_name().equals("") )
			{
			  	LOG.info(" customer name empty");
			  	rvo.setCus_name(" ");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR1,"resource/ErrorCode"));
				flag=false;
			}
		  //checking whether name contains only alphabets and space
		else if(!rvo.getCus_name().matches("[a-z A-Z]{1,}"))
			{
			LOG.info("customer name format wrong  " );
			rvo.setCus_name(" ");
			errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR2,"resource/ErrorCode"));
				flag=false;
			}
		else
		{
			errorList.add(" ");
			
			
		}
		  
		  
		  
		  
		  
		  //checking whether  password is empty
		  String passformat="[a-z A-Z 0-9]{1,}";
		  if(rvo.getCus_pass().isEmpty()||rvo.getCus_pass()==null||rvo.getCus_pass().equals("") )
			{
			   rvo.setCus_pass(" ");
			   LOG.info(" password is empty " );
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR3,"resource/ErrorCode"));
				flag=false;
			} 
		  else if(!rvo.getCus_pass().matches(passformat))                        //checking whether password in alphanumeric format
		  {
			    LOG.info(" password not in given format");
			   rvo.setCus_pass(" ");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR4,"resource/ErrorCode"));
				flag=false;
		  }
		 else if(l<6 || l>10)                                                         //checking length of password
		  {
			 LOG.info(" password length not matches");
			 rvo.setCus_pass(" ");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR5,"resource/ErrorCode"));
				flag=false;
		  }
		  else
			{
				errorList.add(" ");
				
			}
		  
		  
		  
		  
		  
		  
		  int flag1=0;
		 
		  if(rvo.getCus_dob().isEmpty()||rvo.getCus_dob()==null||rvo.getCus_dob().equals(""))             //checking whether date is empty
			{
			  LOG.info("dob empty");
			   rvo.setCus_dob(" ");
			     flag1=1;
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR6,"resource/ErrorCode"));
				flag=false;
			}
		  else if(flag1==0)
			  {
			 
			  DateFormat format = new SimpleDateFormat("yyyy-MMM-dd");                           //validating date in given formate
		       try {
			         Date da = format.parse(rvo.getCus_dob());
			         Date current = new Date();
						if(da.after(current))				                           // comparing date with current date.
						{ LOG.info("dob greater than current date");
							rvo.setCus_dob(" ");
					   		errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR7,"resource/ErrorCode"));
							flag=false;
						}
						else
						{

							  errorList.add(" "); 
							 	
						}
						
		            } 
		       catch (ParseException e) {
		    	   rvo.setCus_dob(" ");                                                         //if date not in given format
		    	   LOG.info("dob format wrong");
		    	   errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR8,"resource/ErrorCode"));
					flag=false;
		            	
		          }
			  } 
		 
			  

		  
		  
		  
		  
		  
		  if(rvo.getCus_addr().isEmpty()||rvo.getCus_addr()==null||rvo.getCus_addr().equals("") )              //checking whether address is empty
			{LOG.info("address empty");
			   rvo.setCus_addr(" ");
			 	errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR9,"resource/ErrorCode"));
				flag=false;
			}else
			{
				errorList.add(" ");
				
				
			}
		  
		  
		  if(rvo.getCus_cont().isEmpty()||rvo.getCus_cont()==null||rvo.getCus_cont().equals("") )         //checking for empty condition
			{
			   rvo.setCus_cont(" ");
			   LOG.info("contactno empty");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR10,"resource/ErrorCode"));
				flag=false;
			}
		  else if(!rvo.getCus_cont().matches("[0-9]{10}"))                                      //checking for format of contact number
		  {  LOG.info("contactno invalid");
			  rvo.setCus_cont(" ");
			 
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR11,"resource/ErrorCode"));
				flag=false;
		  }
		  else
			{
				errorList.add(" ");
			
				
			}
		  
		  
		  
		  
		 String pattern = "[A-Za-z0-9_]+[@][A-Za-z0-9]+[.][a-z]+";
		  if(rvo.getCus_email().isEmpty()||rvo.getCus_email()==null||rvo.getCus_email().equals("") )                  //checking for empty condition
			{
			   rvo.setCus_email(" ");
			   LOG.info("email empty");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR12,"resource/ErrorCode"));
				flag=false;
			}
		  else if(!rvo.getCus_email().matches(pattern))                                                             //checking for email pattern
		  {
			  LOG.info("email invalid");
			  rvo.setCus_email(" ");
				errorList.add(PropertyUtil.getErrorMessage(ErrorConstant.REGISTER_ERROR13,"resource/ErrorCode"));
				flag=false;
		  }
		  else
			{
				errorList.add(" ");
				
				
			}
		  if(flag==false)
			{
			  System.out.println("Invalid values");
			  LOG.info(" in business exception");                                         //throwing business exception
				BusinessException exp =new BusinessException();
				
				exp.setError1(errorList);
				throw exp;
		}
		  
		  else
			{
			  LOG.info(" control to dao");
			  RegisterDao rdao=new RegisterDao();                                              //transfer to dao
				rdao.addCustomer(rvo);
				return rvo; 
				
			
			}
			
		  
		
	  }
  }

