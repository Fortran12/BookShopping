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

public class SearchBookDAO {
	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;
	private final static Logger logger= Logger.getLogger("SearchBookDAO");

	public ArrayList fetchSearchCriteria() throws DatabaseException{
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> price = new ArrayList<String>();
		ArrayList<String> language = new ArrayList<String>();
		ArrayList<String> binding = new ArrayList<String>();
		ArrayList<String> deliveryTime = new ArrayList<String>();
		DatabaseException de = new DatabaseException();
		ArrayList result = new ArrayList();
 
		/* Connection Establishe with database */
		try {
			conn = DbConfigurationUtil.getConnection();
		} catch (ClassNotFoundException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.DRIVER_NOT_FOUND, "resource/ErrorCode"));
		    logger.error("Database Driver not found in fetchSearchCriteria method");
			throw de;
		} catch (IOException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("IO Exception occured in fetchSearchCriteria method");
			throw de;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("Sql exception occured in fetchSearchCriteria method");
			throw de;
		}

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QueryConstant.SELECT_CAT_NAME);
			while (rs.next()) {
				category.add(rs.getString(1));
			}

			rs = stmt.executeQuery(QueryConstant.SELECT_PRICE);
			while (rs.next()) {
				price.add(rs.getFloat(1) + "");
			}

			rs = stmt
					.executeQuery(QueryConstant.SELECT_LANGUAGE);
			while (rs.next()) {
				language.add(rs.getString(1));
			}

			rs = stmt.executeQuery(QueryConstant.SELECT_BINDING);
			while (rs.next()) {
				binding.add(rs.getString(1));
			}

			rs = stmt
					.executeQuery(QueryConstant.SELECT_DELIVERY_TIME);
			while (rs.next()) {
				deliveryTime.add(rs.getString(1));
			}

			result.add(category);
			result.add(price);
			result.add(language);
			result.add(binding);
			result.add(deliveryTime);

		} catch (SQLException e) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("Sql exception occurred while retrieving search Criteria in fetchSearchCriteria method");
			throw de;
		}
		return result;
	}

	public ArrayList getBookDetails(String query, String category) throws DatabaseException {
		ArrayList result = new ArrayList();
		StringBuffer str = new StringBuffer(query);
		DatabaseException de = new DatabaseException();
		try {
			conn = DbConfigurationUtil.getConnection();
		} catch (ClassNotFoundException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.DRIVER_NOT_FOUND, "resource/ErrorCode"));
		    logger.error("Database driver not found in getBookDetails method");
			throw de;
		} catch (IOException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("IO Exception occured in getBookDetails");
			throw de;
		} catch (SQLException e1) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
			logger.error("Sql exception occured in getBookDetails");
			throw de;
		}
		try {
			stmt = conn.createStatement();
			if (!category.equals("all")) {
				rs = stmt
						.executeQuery(QueryConstant. SELECT_CAT_ID
								+ category + "'");
				rs.next();
				str.append("=" + rs.getInt(1) + "");
			}
			
			rs = stmt.executeQuery(str.toString());
			while (rs.next()) {
				AddBookVO vo = new AddBookVO();
				vo.setBookId(rs.getInt(1));
				vo.setBookName(rs.getString(2));
				vo.setLanguage(rs.getString(3));
				vo.setPrice(rs.getFloat(4));
				vo.setAvailability(rs.getInt(5));
				vo.setAuthorName(rs.getString(6));
				vo.setPublisherName(rs.getString(7));
				vo.setBinding(rs.getString(8));
				vo.setDeliveryTime(rs.getString(9));
				result.add(vo);
			}
			
		} catch (SQLException e) {
			de.setErrorMessage(PropertyUtil.getDatabaseMessage(ErrorConstant.SQL_EXCEPTION, "/resource/ErrorCode"));
		    logger.error("Sql exception occured while getting book details");
			throw de;
		}
		return result;
	}
}
