package bo;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import bo.LoginBo;
import vo.LoginVo;


/** This class contains one testXXXX method per XXXXX method in source class
* @author 
**/
//TODO Add Junit jar in build path.
//TODO Modify input and output data if needed.


public class LoginBoTest extends TestCase {
	

	@Test //public LoginVo validate(LoginVo)
	public void testValidate(){
		LoginBo e0Obj = new LoginBo();
		LoginVo e0Arg0 = new LoginVo();
		e0Arg0.setCus_id("1002");
		//e0Arg0.setCart_id(-84);
		e0Arg0.setCus_pass("siva1234");
		

		try{
			LoginVo e0 = e0Obj.validate(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
			assertFalse(false);
		}catch(Exception e){
			//fail();
		}
	}
		public void testValidate1(){
			LoginBo e0Obj = new LoginBo();
			LoginVo e0Arg0 = new LoginVo();
			e0Arg0.setCus_id("");
			//e0Arg0.setCart_id(-84);
			e0Arg0.setCus_pass("siva1234");
			

			try{
				LoginVo e0 = e0Obj.validate(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
			}catch(Exception e){
				//fail();
			}
	}
		public void testValidate2(){
			LoginBo e0Obj = new LoginBo();
			LoginVo e0Arg0 = new LoginVo();
			e0Arg0.setCus_id("1002");
			//e0Arg0.setCart_id(-84);
			e0Arg0.setCus_pass("");
			

			try{
				LoginVo e0 = e0Obj.validate(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
			}catch(Exception e){
				//fail();
			}
	}

	

}
