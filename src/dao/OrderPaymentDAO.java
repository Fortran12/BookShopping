package dao;

import interfaces.IOrderPaymentInterface;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.QueryConstant;
import exception.DatabaseException;
import vo.OrderDetailsVO;
import vo.PaymentVO;
import util.DbConfigurationUtil;
import util.PropertyUtil;

public class OrderPaymentDAO implements IOrderPaymentInterface 
{
	private final static Logger LOG = Logger.getLogger("OrderPaymentDAO");
	Connection conn;
	PreparedStatement ps;
	PreparedStatement ps1;
	PreparedStatement ps3;
	PreparedStatement ps4;
	ResultSet rs;
	ResultSet rs1;
	ResultSet rs3;
	Statement stmt;
	int count=1000;
	
	ArrayList<String> errors=new ArrayList<String>();
	
	 
	/* 
	* Function to retrieve book details from the database.
	* This function checks whether there exists any entry of books in cart.
	* If exist, book details are fetched . Else it throws an exception.
	* It returns a List of type PaymentTO with the calculated cost for each entry.
	* (non-Javadoc)
	* @see interfaces.IOrderPaymentInterface#getBookDetails()
	*/
	 
	public List<PaymentVO> getBookDetails(int cartId) throws DatabaseException 
	{
		
		int cart_id=cartId; // HARDCODED
		List<PaymentVO> bookList=new ArrayList<PaymentVO>();
		
	 try
	 {
		conn = DbConfigurationUtil.getConnection(); 				//Connection Established.
		LOG.info("OrderPaymentDAO Class to get Book Details");
		
		ps=conn.prepareStatement(QueryConstant.CART_ID_EXISTS);		// Query executed to check whether the cart is empty or not.
		ps.setInt(1,cart_id);
		rs=ps.executeQuery();
		LOG.info("OrderPaymentDAO Class to get Book Details : Cart Id existence checked");
		
		if(rs.next())
		{
			ps1=conn.prepareStatement(QueryConstant.GET_BOOK_DETAILS);	// Query executed to retrieve book details from cart.
			ps1.setInt(1,cart_id);
			rs1=ps1.executeQuery();
			LOG.info("OrderPaymentDAO Class to get Book Details : Book Details retrieval Successful");
			
			while(rs1.next())
			{
				PaymentVO pto =new PaymentVO();	
				pto.setBook_id(rs1.getInt(1));				
				pto.setBook_name(rs1.getString(2));			
				int quantity=rs1.getInt(3);
				pto.setQuantity(quantity);					
				float price=rs1.getFloat(4);
				pto.setPrice(rs1.getFloat(4));				
				float cost=quantity*price;
				pto.setCost(cost);							
				bookList.add(pto);				// List accommodated with PaymentTO object.
			}
		}
	
	} 
	 catch (Exception e) 
	 {
			DatabaseException dexp =new DatabaseException();	
			errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
			LOG.info("OrderPaymentDAO Class to get Book Details : DatabaseException "+errors);
			dexp.setErrors4(errors);
			throw dexp;
	 } 
	 finally 
	 {
			try{
				conn.commit();
				conn.close();
				} 
			catch (SQLException e) 
			{
				DatabaseException dexp =new DatabaseException();	
				errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
				LOG.info("OrderPaymentDAO Class to get Book Details : DatabaseException "+errors);
				dexp.setErrors4(errors);
				throw dexp;

			}
	}
	LOG.info("OrderPaymentDAO Class to get Book Details : Book List "+ bookList);
	return bookList;		// Book Details returned as list of type PaymentTO.
 }
	 
	/* 
	* Function to insert order details on successful validation into tables.
	* This function inserts order details into 2 tables(order_details & payment_details).
	* This function also deletes the entry from cart(cart_details).
	* It returns a OrderDetailsTO object with generated order id, total payment and order status.
	* (non-Javadoc)
	* @see interfaces.IOrderPaymentInterface#insertOrderDetails(PaymentTO payto).
	*/
	
 public OrderDetailsVO insertOrderDetails(PaymentVO payto, int cartid, int cust_id) throws DatabaseException
 {
	
	 	boolean flag=false;
	 	int customer_id=cust_id; // HARDCODED
		int cart_id=cartid; // HARDCODED
		
	 	float sum=payto.getTotal();
	 	
	 	List<PaymentVO> bookDetails=new ArrayList<PaymentVO>();
		bookDetails=getBookDetails(cart_id);
		
		String odr="ORD";
		String msg="ORD PLACED";
		
		StringBuffer str=new StringBuffer();
		
		int insert1 = 0;
		int insert2 = 0;
		
		OrderDetailsVO odto=new OrderDetailsVO();
		odto.setCustomer_id(customer_id); 					// CustomerId set
		
		try
	 {
		conn = DbConfigurationUtil.getConnection(); 	//Connection Established
		LOG.info("OrderPaymentDAO Class to get Order Details");
		
		ps3=conn.prepareStatement(QueryConstant.ORDER_COUNT);	// Query for determining the number of rows to generate order id. 
		rs3=ps3.executeQuery();
		
		while(rs3.next())
		{
			count++;
		}
		String order_id=str.append(odr).append(count).toString();	
		LOG.info("OrderPaymentDAO Class to get Order Details : Order Id generated");
		
		// Query for inserting order details to order_details table.
		String odate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		ps1=conn.prepareStatement(QueryConstant.INSERT_ORDER);		
	
		ps1.setInt(1, customer_id);
		ps1.setString(2, order_id);
		ps1.setString(3, odate);
		ps1.setString(4, msg);
		ps1.setFloat(5, sum);
		insert2=ps1.executeUpdate();
		
		if(insert2==0)						// Insertion Unsuccessful. Exception Thrown.
		{
			LOG.info("OrderPaymentDAO Class to get Order Details : values not inserted to order_details table");
			flag=false;
		}
		else								// Values inserted Successfully
		{
			LOG.info("OrderPaymentDAO Class to get Book Details : values inserted to order_details table");
			flag=true;
			odto.setOrder_date(odate);							
			odto.setOrder_status(msg);							
			odto.setTotal(sum);									
		}
		if(flag==false)
		{
			DatabaseException dexp =new DatabaseException();	
			errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
			LOG.info("OrderPaymentDAO Class to get Order Details(order_details) : DatabaseException "+errors);
			dexp.setErrors4(errors);
			throw dexp;
		}
		
		// Query for inserting order details to payment_details table.
		ps=conn.prepareStatement(QueryConstant.INSERT_PAYMENT);		
		Iterator<PaymentVO> itr=bookDetails.iterator();
		while(itr.hasNext())
		{
			PaymentVO pto=itr.next();	
			ps.setString(1, order_id);				
			ps.setInt(2, pto.getBook_id());			
			ps.setString(3, pto.getBook_name());	
			ps.setInt(4, pto.getQuantity());		
			ps.setFloat(5, pto.getPrice());			
			insert1 = ps.executeUpdate();
			if(insert1==0)								// Insertion Unsuccessful. Exception Thrown.
			{
				LOG.info("OrderPaymentDAO Class to get Order Details : values not inserted to payment_details table");
				flag=false;
			}
			else								  		// Values inserted Successfully
			{
				LOG.info("OrderPaymentDAO Class to get Order Details : values inserted to payment_details table");
				flag=true;
				odto.setOrder_id(order_id);						//OrderId set
			}
			if(flag==false)
			{
				DatabaseException dexp =new DatabaseException();	
				errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
				LOG.info("OrderPaymentDAO Class to get Order Details(payment_details) : DatabaseException "+errors);
				dexp.setErrors4(errors);
				throw dexp;
			}
		}
		
		// Query for deleting order details from cart. ie, cart_details table.
		ps4=conn.prepareStatement(QueryConstant.DELETE_CART);
		ps4.setInt(1,cart_id);
		int d=ps4.executeUpdate();
		if(d==0)					// Deletion Unsuccessful. Exception Thrown.
		{
			LOG.info("OrderPaymentDAO Class to get Order Details : values not deleted from cart_details table");
			flag=false;
		}
		else						// Values deleted Successfully
		{
			LOG.info("OrderPaymentDAO Class to get Order Details : values deleted from cart_details table");
			flag=true;
		}
		
		if(flag==false)
		{
			DatabaseException dexp =new DatabaseException();	
			errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
			LOG.info("OrderPaymentDAO Class to get Order Details(cart_details) : DatabaseException "+errors);
			dexp.setErrors4(errors);
			throw dexp;
		}

	 }
	 catch (Exception e) 
	 {
			DatabaseException dexp =new DatabaseException();	
			errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
			LOG.info("OrderPaymentDAO Class to get Order Details : DatabaseException "+errors);
			dexp.setErrors4(errors);
			throw dexp;
	 } 
	 finally 
	 {
			try{
				conn.commit();
				conn.close();
				} 
			catch (SQLException e) 
			{
				DatabaseException dexp =new DatabaseException();	
				errors.add(PropertyUtil.getDatabaseMessage(ErrorConstant.FATAL_ERROR, "/resource/ErrorCode"));
				LOG.info("OrderPaymentDAO Class to get Order Details : DatabaseException "+errors);
				dexp.setErrors4(errors);
				throw dexp;

			}
	}
	LOG.info("OrderPaymentDAO Class to get Order Details : OrderDetailTO return success "+odto);
	return odto; 
 }

}