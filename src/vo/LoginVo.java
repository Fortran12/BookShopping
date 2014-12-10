package vo;

public class LoginVo {
	private String cus_id;
	private String cus_pass;
	private int cart_id;
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cartId) {
		cart_id = cartId;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cusId) {
		cus_id = cusId;
	}
	public String getCus_pass() {
		return cus_pass;
	}
	public void setCus_pass(String cusPass) {
		cus_pass = cusPass;
	}
}
