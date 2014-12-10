package constant;

public final class QueryConstant {

	
	//login
	public static final String USER_EXISTS= "select cart_id from customer_details where cust_id=? and password=?";
//register
	public static final String VALIDATE_REG="select cust_id,cart_id from  customer_details where email=?";
	public static final String INSERT_USER="insert into customer_details values(seq_cust_id.nextval,seq_cart_id.nextval,?,?,?,?,?,?)";
	
	
	 public static final String SELECT_BOOK_NAME2 = "select book_name from book_details where book_id=";
     public static final String INSERT_CART_DETAILS2 = "insert into cart_details values(";
     public static final String UPDATE_QUANTITY_NEEDED2 = "update cart_details set quantity_needed=";
     public static final String SELECT_QUANTITY_NEEDED2 = "select quantity_needed from cart_details where book_id=";
     public static final String UPDATE_AVAILABILITY2 = "update book_details set availability=";
	 public static final String SELECT_AVAILABILITY2 =  "select availability from book_details where book_id=";
	
	 public static final String SEARCH_BOOKBO_QUERY = "select book_id, book_name, language, price, availability, author_name, publisher_name"
			                                           + ", binding, delivery_time from book_details";
	 public static final String SELECT_CAT_NAME = "select cat_name from category";
	 public static final String SELECT_PRICE = "select distinct price from book_details";
	 public static final String SELECT_LANGUAGE = "select distinct language from book_details";
	 public static final String SELECT_BINDING = "select distinct binding from book_details";
	 public static final String SELECT_DELIVERY_TIME = "select distinct delivery_time from book_details";
	 public static final String SELECT_CAT_ID = "select cat_id from category where cat_name='";
	 public static final String SELECT_AVAILABILITY =  "select availability from book_details where book_id=?";
     public static final String UPDATE_AVAILABILITY = "update book_details set availability=? where book_id=?";
     public static final String SELECT_QUANTITY_NEEDED = "select quantity_needed from cart_details where book_id=?";
     public static final String UPDATE_QUANTITY_NEEDED = "update cart_details set quantity_needed=? where book_id=?";
     public static final String SELECT_BOOK_NAME = "select book_name from book_details where book_id=?";

      
     public static final String UPDATE_BOOK_QUERY1 = "update book_details set availability=0 where book_id=?";
	 public static final String UPDATE_BOOK_QUERY2="update book_details set availability=? where  book_id=?";
	 public static final String UPDATE_GET_BOOKNAME="select book_name from book_details where book_id=?";
	 public static final String UPDATE_CART="update cart_details set quantity_needed=? where  book_id=?";
	 public static final String GET_BOOK_DETAIL="select a.book_id,a.cat_id,a.book_name,a.price,a.availability,a.binding," +
	 											"a.language,a.author_name,a.publisher_name,b.quantity_needed from book_details a," +
	 											" cart_details b where a.book_id=b.book_id and b.cart_id=?";

	 
     public static final String CART_ID_EXISTS= "select * from cart_details where cart_id=?";
 	public static final String GET_BOOK_DETAILS="select b.book_id,b.book_name,c.quantity_needed," +
 												"b.price from book_details b,cart_details c " +
 												"where c.cart_id=? and c.book_id=b.book_id";
 	public static final String ORDER_COUNT="select * from order_details";
 	public static final String INSERT_ORDER="insert into order_details(cust_id,order_id,order_date," +
 											"order_status,total) values(?,?,?,?,?)";
 	public static final String INSERT_PAYMENT="insert into payment_details(order_id,book_id," +
 											  "book_name,quantity,price) values(?,?,?,?,?)";
 	
 	public static final String DELETE_CART="delete from cart_details where cart_id=?";
}
