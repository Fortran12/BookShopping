package bo;

import java.util.ArrayList;
import java.util.HashMap;

import constant.ErrorConstant;

import dao.AddBookDAO;
import exception.BusinessException;
import exception.DatabaseException;

import util.PropertyUtil;
import vo.CartDataVO;

public class AddBookBO {

	/********* It sends selected book to DAO *********/
	public ArrayList<CartDataVO> addBook(ArrayList<CartDataVO> vo,
			HashMap<Integer, Integer> map, int cartId) throws DatabaseException {

		AddBookDAO dao = new AddBookDAO();
		return dao.addBook(vo, cartId);

	}

	/********* Validate the selected book quantity *********/
	public ArrayList validateBookData(ArrayList<CartDataVO> vo,
			HashMap<Integer, Integer> map) throws BusinessException {
		ArrayList list = new ArrayList();
		BusinessException be = new BusinessException();
	
		boolean flag = false;
		
		for (CartDataVO value : vo) {
			CartDataVO result = new CartDataVO();
			result.setBookId(value.getBookId());
			result.setQuantity(value.getQuantity());
			if (value.getQuantity() > map.get(value.getBookId())) {
				result.setErrorMessage(PropertyUtil.getErrorMessage(ErrorConstant.QUANTITY_GREATER,"resource/ErrorCode"));
				result.setFlag(true);
				flag = true;
			
			}
			else if(value.getQuantity()== -999){
		    	result.setFlag(true);
		    	result.setTextQuantity(value.getTextQuantity());
		    	result.setErrorMessage(PropertyUtil.getErrorMessage(ErrorConstant.NON_DIGIT_QUANTITY,"resource/ErrorCode"));
				flag = true;
			
			} 
			else if(value.getQuantity() <= 0 || value.getQuantity() ==0) {
				result.setFlag(true);
		    	result.setErrorMessage(PropertyUtil.getErrorMessage(ErrorConstant.INVALID_DIGIT_QUANTITY,"resource/ErrorCode"));
				flag = true;
			
			}
			list.add(result);
		
		}
		list.add(flag);
		be.setError(list);
		return be.getError();
	}
}
