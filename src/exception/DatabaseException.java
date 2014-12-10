package exception;

import java.util.ArrayList;

public class DatabaseException extends Exception {
	public String errorMessage;
	public String errors;

	String message;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DatabaseException(String message)

	{

		this.message = message;

	}

	public DatabaseException() {

	}

	public String getErrors() {

		return errors;

	}

	public void setErrors(String errors) {

		this.errors = errors;

	}

	public String getMessage()

	{

		return message;

	}
	
	public ArrayList<String> errors4;
	String message4;
	
	public ArrayList<String> getErrors4() {
		return errors4;
	}
	public void setErrors4(ArrayList<String> errors4) {
		this.errors4 = errors4;
	}
	
	public void setMessage(String message4) {
		this.message4 = message4;
	}
	

	
	public String getMessage4()
	{
		return message4;
	}

}
