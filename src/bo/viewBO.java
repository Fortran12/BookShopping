package bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import controller.viewController;

import vo.QuantityErrorVO;
import vo.successVO;
import vo.viewVO;

import dao.viewDAO;
import exception.BusinessException;

public class viewBO {
	public static final Logger LOG = Logger.getLogger("ViewBO");
	ArrayList result=new ArrayList();
	successVO svo = new successVO();
	QuantityErrorVO error=new QuantityErrorVO();
	ArrayList<successVO> a=null;
	public ArrayList viewBook(int cart_id) {
		//recieves cart id and passes it to dao for retirvg vals corresp to that id
		viewDAO dao = new viewDAO();
		LOG.info("ViewBO : Recieves cart id and passes it to DAO for retreiving values corresponding to that id");
		return dao.viewBook(cart_id);
	}
	

	public ArrayList<successVO> validate(ArrayList<viewVO> v,
			String[] books,  String[] quantity, int length, int cart_id) throws   BusinessException {

		int avail = 0;
		int qty;
		viewDAO dao = new viewDAO();
		viewVO vo = new viewVO();
		viewController vc = new viewController();
		ArrayList<String> err = new ArrayList<String>();
		int i=0;
		for(i=0;i<length;i++) {
		for (viewVO v1 : v) {
			System.out.println(v1.getBookId()+" "+books[i]);
			if (v1.getBookId() == Integer.parseInt(books[i])) {
			
				if (!quantity[i].matches("[0-9]{1,}") || Integer.parseInt(quantity[i]) > v1.getAvailability()) {
					svo.setFlag(true);
				} 
	
				if(v1.getAvailability() == 0 && v1.getQuantity() < Integer.parseInt(quantity[i]))
				{
					svo.setFlag(true);
				}
				
				
				
			}
		}
		}
		
		if(svo.isFlag()){
		    BusinessException BE =new BusinessException();
		    throw BE;
		} else {
			viewDAO da = new viewDAO();
	 a=da.viewQ(books, quantity,length,cart_id);
		}
		
		return a;
	
	}
}
