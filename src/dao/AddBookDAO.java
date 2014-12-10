package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import constant.ErrorConstant;
import constant.QueryConstant;
import exception.DatabaseException;

import util.DbConfigurationUtil;
import util.PropertyUtil;
import vo.AddBookVO;
import vo.CartDataVO;

public class AddBookDAO {

	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;
	private final static Logger logger= Logger.getLogger("SearchBookDAO");

	synchronized public ArrayList addBook(ArrayList<CartDataVO> vo, int cartId) throws DatabaseException {
		ArrayList<CartDataVO> list = new ArrayList<CartDataVO>();
		ArrayList<AddBookVO> cartData = new ArrayList<AddBookVO>();
		ArrayList returnData = new ArrayList();
		
		DatabaseException de = new DatabaseException();
		try {
			conn = DbConfigurationUtil.getConnection();
		} catch (ClassNotFoundException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.DRIVER_NOT_FOUND, "resource/ErrorCode"));
			logger.error("Database Driver not found in addBook method");
			throw de;
		} catch (IOException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("IO Exception occured in addBook method");
			throw de;
		} catch (SQLException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("Sql exception occured in addBook method");
			throw de;
		}

		try {
			stmt = conn.createStatement();
			for (CartDataVO value : vo) {
				CartDataVO result = new CartDataVO();

				/*************
				 * Obtain the availability from book_details for particular
				 * Book_id
				 ********************/
				rs = stmt.executeQuery(QueryConstant.SELECT_AVAILABILITY2
						+ value.getBookId() + "");

				rs.next();
				/*************
				 * Update availability as availability = availability -
				 * quantity_needed if availability > 0
				 **************/
				int available = rs.getInt(1);
				if (available > 0) {
					stmt.executeUpdate(QueryConstant.UPDATE_AVAILABILITY2
							+ (available - value.getQuantity())
							+ " where book_id=" + value.getBookId() + "");
				}

				rs = stmt.executeQuery(QueryConstant.SELECT_QUANTITY_NEEDED2
						+ value.getBookId() + " and cart_id=" + cartId + "");

				/*************
				 * If same book is selected next time then update existing
				 * quantity_needed
				 *************/
				if (rs.next()) {
					int quantity = rs.getInt(1);
				
					stmt.executeUpdate(QueryConstant.UPDATE_QUANTITY_NEEDED2
							+ (quantity + " + " + value.getQuantity())
							+ " where book_id =" + value.getBookId()
							+ " and cart_id = " + cartId);
				}

				/************ Otherwise insert another record with new Data *******************/
				else {
					stmt.executeUpdate(QueryConstant.INSERT_CART_DETAILS2
							+ cartId + "," + value.getBookId() + ","
							+ value.getQuantity() + ")");
				}

				/************ Set the return data *******************************/
				rs = stmt.executeQuery(QueryConstant.SELECT_BOOK_NAME2
						+ value.getBookId() + "");
				rs.next();
				result.setBookName(rs.getString(1));
				result.setQuantity(value.getQuantity());
				list.add(result);
			}
			conn.commit();
			rs = stmt.executeQuery(QueryConstant.SEARCH_BOOKBO_QUERY);
			while (rs.next()) {
				AddBookVO data = new AddBookVO();
				data.setBookId(rs.getInt(1));
				data.setBookName(rs.getString(2));
				data.setLanguage(rs.getString(3));
			    data.setPrice(rs.getFloat(4));
				data.setAvailability(rs.getInt(5));
			    data.setAuthorName(rs.getString(6));
				data.setPublisherName(rs.getString(7));
				data.setBinding(rs.getString(8));
				data.setDeliveryTime(rs.getString(9));
				cartData.add(data);
			}
			
			returnData.add(cartData);
			returnData.add(list);
			
		} catch (SQLException e) {
			System.out.println(e);
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("Sql exception occured in addBook method");
			throw de;
		}
		return returnData;
	}
}
