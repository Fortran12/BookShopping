package exception;

import java.util.ArrayList;

public class ApplicationException extends Exception {

	public ArrayList<String> errors;

	public String message;

	public ApplicationException(ArrayList<String> errorList) {
		// TODO Auto-generated constructor stub

		this.errors=errorList;
	}

	public ApplicationException() {
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

		public String getMessage()

		{

		return message;

		}
		
		
}
