package bo;

import interfaces.IOrderPaymentInterface;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.SuccessConstant;

import vo.OrderDetailsVO;
import vo.PaymentVO;
import util.PropertyUtil;
import dao.OrderPaymentDAO;
import exception.BusinessException;
import exception.DatabaseException;


public class OrderPaymentBO 
{
	private final static Logger LOG = Logger.getLogger("OrderPaymentBO");
	IOrderPaymentInterface ipay=(IOrderPaymentInterface) new OrderPaymentDAO(); 
	
	/*
	 * Function to carry out retrieving book details from database.
	 * It will call the DAO function and retrieve the details.
	 * It will return a List of PaymentTO type.
	 */
	
	public List<PaymentVO> getBDetails(int cartId) throws DatabaseException 
	{
		List<PaymentVO> bookList=new ArrayList<PaymentVO>();
		bookList=ipay.getBookDetails(cartId);
		LOG.info("Method  to get Book Details inside OrderPaymentBO invoked " + bookList);	
		return bookList;
	}
	
	
	/*
	 * Function to validate account number. 
	 * It will take an input string and check whether it contains only digits or not.
	 * It will return a boolean value.
	 */
	
	public boolean validateAccno(String accno)
	{
		LOG.info("Method to Validate Account Number inside OrderPaymentBO Invoked " + accno);	
		String regex="[0-9]{10,}";
		if(accno.matches(regex)){
			return true;
		}
		else{
		return false;
		}
	}
	
	/*
	 * Function to validate bank name. 
	 * It will take an input string and check whether it contains only alphabets or not.
	 * It will return a boolean value.
	 */
	
	public boolean validateBankname(String bankname)
	{
		LOG.info("Method to Validate Bank Name inside OrderPaymentBO Invoked " + bankname);	
		String regex="[a-zA-Z]+";
		if(bankname.matches(regex)){
			return true;
		}
		else{
		return false;
		}
	}
	
	/*
	 * Function to validate account number. 
	 * It will take an input string and check whether it is in the IFSC Code Format(XXXXYYYYYYY. where, X=alphabet and Y=digit).
	 * It will return a boolean value.
	 */
	
	public boolean validateIFSC(String ifsc)
	{
		LOG.info("Method to Validate IFSC Code inside OrderPaymentBO Invoked " + ifsc);	
		String regex="[A-Z a-z]{4}[0-9]{7}";
		if(ifsc.matches(regex)){
			return true;
		}
		else{
		return false;
		}
	}
	
	
	/*
	 * Function for inserting order details into the database.
	 * It will check if all the business rules are implemented or not.
	 * If all the rules are implemented then it will call the DAO function.
	 * It will return a OrderDetailsTO object.
	 */
	
	public OrderDetailsVO insertODetails(PaymentVO payto, int cartid, int cust_id) throws BusinessException, DatabaseException 
	{
		ArrayList<String> error=new ArrayList<String>();
		boolean flag=true;
		
		//Account Number Validation
		if("".equals(payto.getAccno()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.ACCNO_INVALID_ERROR1, "resource/ErrorCode"));
		}
		else
		if(!validateAccno(payto.getAccno()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.ACCNO_INVALID_ERROR2, "resource/ErrorCode"));
		}
		else
		{
			error.add("");
		}
		
		//Bank Name Validation
		if("".equals(payto.getBname()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.BANK_NAME_ERROR1, "resource/ErrorCode"));
		}
		else
		if(!validateBankname(payto.getBname()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.BANK_NAME_ERROR2, "resource/ErrorCode"));
		}
		else
		{
			error.add("");
		}
		
		//IFSC Code Validation
		if("".equals(payto.getIfsc()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.BANK_IFSC_ERROR1, "resource/ErrorCode"));
		}
		else
		if(!validateIFSC(payto.getIfsc()))
		{
			flag=false;
			error.add(PropertyUtil.getErrorMessage(ErrorConstant.BANK_IFSC_ERROR2, "resource/ErrorCode"));
		}
		else
		{
			error.add("");
		}

		if(flag == false)		// Business Exception thrown if any business rules are not satisfied.
		{
			BusinessException exp =new BusinessException();
			exp.setError4(error);
			LOG.error("OrderPaymentBO Class BussinessException "+error);
			throw exp;
		}
		
		else
		{
			error.add("");
			flag=true;
			OrderDetailsVO odto=new OrderDetailsVO();
			LOG.error("OrderPaymentBO Validation Success "+flag);
			odto=ipay.insertOrderDetails(payto, cartid,cust_id);		// OrderPaymentDAO function call on successful validation.
			LOG.info("OrderPaymentBO Class Insert Details Success "+odto);
			return odto;
		}
	}
}
