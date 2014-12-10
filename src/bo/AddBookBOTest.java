package bo;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import vo.CartDataVO;
import bo.AddBookBO;
import java.util.ArrayList;
import java.util.HashMap;


/** This class contains one testXXXX method per XXXXX method in source class
* @author 
**/
//TODO Add Junit jar in build path.
//TODO Modify input and output data if needed.


public class AddBookBOTest extends TestCase {
	

	@Test //public ArrayList validateBookData(ArrayList<CartDataVO>,HashMap<Integer,Integer>)
	public void testValidateBookData(){
		AddBookBO e0Obj = new AddBookBO();
		ArrayList<CartDataVO> e0Arg0 = new ArrayList<CartDataVO>();
		CartDataVO vo = new CartDataVO();
		vo.setBookId(10013);
		vo.setQuantity(18);
		e0Arg0.add(vo);
		HashMap<Integer,Integer> e0Arg1 = new HashMap<Integer,Integer>();
		e0Arg1.put(10013,11);
		try{
			ArrayList e0 = e0Obj.validateBookData(e0Arg0,e0Arg1);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.contains(true), true);
		}catch(Exception e){
			System.out.println(e.getMessage());
			fail();
		}
	}
	 
	public void testValidateBookData1(){
		AddBookBO e0Obj = new AddBookBO();
		ArrayList<CartDataVO> e0Arg0 = new ArrayList<CartDataVO>();
		CartDataVO vo = new CartDataVO();
		vo.setBookId(10013); 
		vo.setQuantity(-999);
		e0Arg0.add(vo);
		HashMap<Integer,Integer> e0Arg1 = new HashMap<Integer,Integer>();
		e0Arg1.put(10013,11);
		try{
			ArrayList e0 = e0Obj.validateBookData(e0Arg0,e0Arg1);
			System.out.println(e0.contains(true));
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.contains(true), true);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testValidateBookData2(){
		AddBookBO e0Obj = new AddBookBO();
		ArrayList<CartDataVO> e0Arg0 = new ArrayList<CartDataVO>();
		CartDataVO vo = new CartDataVO();
		vo.setBookId(10013); 
		vo.setQuantity(-23);
		e0Arg0.add(vo);
		HashMap<Integer,Integer> e0Arg1 = new HashMap<Integer,Integer>();
		e0Arg1.put(10013,11);
		try{
			ArrayList e0 = e0Obj.validateBookData(e0Arg0,e0Arg1);
			System.out.println(e0.contains(true));
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.contains(true), true);
		}catch(Exception e){
			fail();
		}
	}
	
	@Test //public ArrayList<CartDataVO> addBook(ArrayList<CartDataVO>,HashMap<Integer,Integer>,int)
	public void testAddBook(){
		AddBookBO e0Obj = new AddBookBO();
		CartDataVO e0Arg0Com0 = new CartDataVO();
		e0Arg0Com0.setErrorMessage("");
		e0Arg0Com0.setFlag(false);
		e0Arg0Com0.setTextQuantity("Zq");
		e0Arg0Com0.setQuantity(1);
		e0Arg0Com0.setBookId(10005);
		e0Arg0Com0.setBookName("3v2LJ1ramkWNB9urY");
		

		ArrayList<CartDataVO> e0Arg0 = new ArrayList<CartDataVO>();
		e0Arg0.add(e0Arg0Com0);
		HashMap<Integer,Integer> e0Arg1 = new HashMap<Integer,Integer>();
		e0Arg1.put(10005, 15);
		try{
			ArrayList<CartDataVO> e0 = e0Obj.addBook(e0Arg0,e0Arg1,40001);
			//TODO Based on your need, provide necessary assertion condition
			CartDataVO vo = e0.get(0);
		assertEquals(vo.getQuantity(),1);
		}catch(Exception e){
			fail();
		}
	}

}
