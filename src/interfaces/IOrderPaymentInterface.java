package interfaces;

import java.util.List;

import exception.DatabaseException;

import vo.OrderDetailsVO;
import vo.PaymentVO;

public interface IOrderPaymentInterface {

	List<PaymentVO> getBookDetails(int cartId) throws DatabaseException;

	OrderDetailsVO insertOrderDetails(PaymentVO payto,int cart_id,int cust_id) throws DatabaseException;

}
