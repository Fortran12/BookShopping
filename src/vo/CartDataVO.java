package vo;

public class CartDataVO {

	private int bookId;
	private int quantity;
	private String bookName;
	private String errorMessage;
	private String textQuantity;
	private boolean flag;
	
	
	public String getTextQuantity() {
		return textQuantity;
	}

	public void setTextQuantity(String textQuantity) {
		this.textQuantity = textQuantity;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
