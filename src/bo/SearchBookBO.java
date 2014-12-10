package bo;

import java.util.ArrayList;

import constant.QueryConstant;

import vo.SearchBookVO;

import dao.SearchBookDAO;
import exception.DatabaseException;

public class SearchBookBO {

	/************* Populate the initial search criteria from database *********/
	public ArrayList searchBookCriteria() throws DatabaseException {
		SearchBookDAO dao = new SearchBookDAO();
		return dao.fetchSearchCriteria();
	}

	/********* Sets search query according to selection *********************/
	public ArrayList setQuery(SearchBookVO vo) throws DatabaseException {
		StringBuffer query = new StringBuffer(QueryConstant.SEARCH_BOOKBO_QUERY);
		String category = vo.getCategory();
		String price = vo.getPrice() + "";
		String language = vo.getLanguage();
		String binding = vo.getBinding();
		String deliverytime = vo.getDeliveryTime();
		boolean flag = false;

		if (!price.equals("0.0")) {
			if (true) {
				query = query.append(" where price=" + Float.parseFloat(price)
						+ "");
				flag = true;
			} /*else {
				query = query.append(" where price=" + Float.parseFloat(price)
						+ "");
				flag = true;
			}*/
		}

		if (!language.equals("")) {
			if (flag) {
				query = query.append(" and language='" + language + "'");
			} else {
				query = query.append(" where language='" + language + "'");
				flag = true;
			}
		}
		

		if (!binding.equals("")) {
			if (flag) {
				query = query.append(" and binding='" + binding + "'");
			} else {
				query = query.append(" where binding='" + binding + "'");
				flag = true;
			}
		}

		if (!deliverytime.equals("")) {
			if (flag) {
				query = query.append(" and delivery_time='" + deliverytime
						+ "'");
			} else {
				query = query.append(" where delivery_time='" + deliverytime
						+ "'");
				flag = true;
			}
		}

		if ((!category.equals("all")) && price.equals("0.0")
				&& language.equals("") && binding.equals("")
				&& deliverytime.equals("")) {
			query = query.append(" where cat_id");
		} else {
			if (!category.equals("all")) {
				query.append(" and cat_id");
			}
		}
 
		// System.out.println(query);
		SearchBookDAO dao = new SearchBookDAO(); 
		return dao.getBookDetails(query.toString(), category);

	}

}
