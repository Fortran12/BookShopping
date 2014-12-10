package bo;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import vo.SearchBookVO;
import java.util.ArrayList;
import bo.SearchBookBO;


/** This class contains one testXXXX method per XXXXX method in source class
* @author 
**/
//TODO Add Junit jar in build path.
//TODO Modify input and output data if needed.


public class SearchBookBOTest extends TestCase {
	

	@Test //public ArrayList setQuery(SearchBookVO)
	public void testSetQuery(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), false);
		}catch(Exception e){
			fail();
		}
	}
	
	
	public void testSetQuery1(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("100"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testSetQuery2(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("Marathi");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}

	public void testSetQuery3(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("Himalaya");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}
	
	
	public void testSetQuery4(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime(" 0 Days");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}
	
	
	public void testSetQuery5(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("Children and Teens");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), false);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testSetQuery6(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("471"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), false);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testSetQuery7(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("English");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("471"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), false);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testSetQuery8(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("English");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("Pearson");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}
	
	public void testSetQuery9(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("English");
		e0Arg0.setCategory("all");
		e0Arg0.setBinding("");
		e0Arg0.setDeliveryTime("4-5 Business Days");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), false);
		}catch(Exception e){
			fail();
		}
	}
	 
	public void testSetQuery10(){
		SearchBookBO e0Obj = new SearchBookBO();
		SearchBookVO e0Arg0 = new SearchBookVO();
		e0Arg0.setLanguage("");
		e0Arg0.setCategory("Children and Teens");
		e0Arg0.setBinding("Others");
		e0Arg0.setDeliveryTime("");
		e0Arg0.setPrice(Float.parseFloat("0.0"));
		

		try{
			ArrayList e0 = e0Obj.setQuery(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(), true);
		}catch(Exception e){
			fail();
		}
	}
	
   @Test //public ArrayList searchBookCriteria()
	public void testSearchBookCriteria(){
		SearchBookBO e0Obj = new SearchBookBO();
		try{
			ArrayList e0 = e0Obj.searchBookCriteria();
			//TODO Based on your need, provide necessary assertion condition
		assertEquals(e0.isEmpty(),false);
		}catch(Exception e){
			fail();
		}
	} 
	 

	
}
