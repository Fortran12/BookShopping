package exception;

import java.util.ArrayList;

import vo.CartDataVO;

public class BusinessException extends Exception {
   ArrayList<CartDataVO> vo;
	public void setError(ArrayList<CartDataVO> vo) {
		this.vo = vo;
	}
	public ArrayList<CartDataVO> getError() {
		return vo;
	}
	
	ArrayList<String> error;
	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	String errormsg;

	public ArrayList<String> getError1() {
		return error;
	}

	public void setError1(ArrayList<String> error) {
		this.error = error;
	}
	
	public ArrayList<String> error4;

	public ArrayList<String> getError4() {
		return error4;
	}

	public void setError4(ArrayList<String> error4) {
		this.error4 = error4;
	}
	
	public ArrayList<String> errorList=new ArrayList<String>();

	public ArrayList<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<String> errorList) {
		this.errorList = errorList;
	}

}
