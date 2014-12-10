package bo;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import vo.RegisterVo;
import bo.RegisterBo;


/** This class contains one testXXXX method per XXXXX method in source class
* @author 
**/
//TODO Add Junit jar in build path.
//TODO Modify input and output data if needed.


public class RegisterBoTest extends TestCase {
	

	@Test //public RegisterVo validateCustomer(RegisterVo)
	public void testValidateCustomer(){
		RegisterBo e0Obj = new RegisterBo();
		RegisterVo e0Arg0 = new RegisterVo();
		e0Arg0.setCus_id(1001);
		e0Arg0.setCus_pass("dhaar123");
		e0Arg0.setCus_name("dharashini");
		e0Arg0.setCus_dob("1991-May-19");
		e0Arg0.setCus_addr("chennsai");
		e0Arg0.setCus_cont("9443870163");
		e0Arg0.setCus_email("dhdar@gmail.com");
		e0Arg0.setCard_id(2001);
		

		try{
			RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
			//TODO Based on your need, provide necessary assertion condition
		 assertFalse(false);
		}catch(Exception e){
			
			//fail();
		}
	}
		public void testValidateCustomername1(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("");
			e0Arg0.setCus_name("");
			e0Arg0.setCus_dob("1991-May-19");
			e0Arg0.setCus_addr("chennsai");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("dhdar@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertTrue(true);
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println(e);
				//assertTrue(true);
			}
	}
	
		public void testValidateCustomername2(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("dhaar123");
			e0Arg0.setCus_name("152");
			e0Arg0.setCus_dob("1991-May-19");
			e0Arg0.setCus_addr("chennsai");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("www@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertEquals(e0, e0);
			}catch(Exception e){
				//e.printStackTrace();
				System.out.println(e);
				assertTrue(true);
			}
	}
	
		public void testValidateCustomerpass1(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("@TRU#%$^");
			e0Arg0.setCus_name("ramya");
			e0Arg0.setCus_dob("1991-May-19");
			e0Arg0.setCus_addr("chennsai");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("dhdar@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertTrue(true);
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println(e);
				//assertTrue(true);
			}
	}
	
		public void testValidateCustomerpass2(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("fj");
			e0Arg0.setCus_name("ramya");
			e0Arg0.setCus_dob("1991-May-19");
			e0Arg0.setCus_addr("chennsai");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("dhdar@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertTrue(true);
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println(e);
				//assertTrue(true);
			}
	}
	
		public void testValidateCusdate(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("ramya123");
			e0Arg0.setCus_name("ramya");
			e0Arg0.setCus_dob("12-31-1991");
			e0Arg0.setCus_addr("fdnfnm");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("dhdar@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertTrue(true);
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println(e);
				//assertTrue(true);
			}
	}
		public void testValidateCusdate1(){
			RegisterBo e0Obj = new RegisterBo();
			RegisterVo e0Arg0 = new RegisterVo();
			e0Arg0.setCus_id(1002);
			e0Arg0.setCus_pass("ramya123sdfjh");
			e0Arg0.setCus_name("ramya");
			e0Arg0.setCus_dob("2015-JAN-07");
			e0Arg0.setCus_addr("fdnfnm");
			e0Arg0.setCus_cont("9443870163");
			e0Arg0.setCus_email("dhdar@gmail.com");
			e0Arg0.setCard_id(2002);
			

			try{
				RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
				//TODO Based on your need, provide necessary assertion condition
			assertTrue(true);
			}catch(Exception e){
				//e.printStackTrace();
				//System.out.println(e);
				//assertTrue(true);
			}
		}
			public void testValidateCusdate2(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh");
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("");
				e0Arg0.setCus_addr("fdnfnm");
				e0Arg0.setCus_cont("9443870163");
				e0Arg0.setCus_email("dhdar@gmail.com");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCusaddr(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh");
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("1991-SEP-09");
				e0Arg0.setCus_addr("");
				e0Arg0.setCus_cont("9443870163");
				e0Arg0.setCus_email("dhdar@gmail.com");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCuscont(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh");
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("1991-SEP-09");
				e0Arg0.setCus_addr("fjglg");
				e0Arg0.setCus_cont("");
				e0Arg0.setCus_email("dhdar@gmail.com");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCuscont1(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh");
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("1991-SEP-09");
				e0Arg0.setCus_addr("fjglg");
				e0Arg0.setCus_cont("@13453464876970");
				e0Arg0.setCus_email("dhdar@gmail.com");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCusemail(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh");
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("1991-SEP-09");
				e0Arg0.setCus_addr("fjglg");
				e0Arg0.setCus_cont("1234567890");
				e0Arg0.setCus_email("");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCusemail1(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_id(1002);
				e0Arg0.setCus_pass("ramya123sdfjh"); 
				e0Arg0.setCus_name("ramya");
				e0Arg0.setCus_dob("1991-SEP-09");
				e0Arg0.setCus_addr("fjglg");
				e0Arg0.setCus_cont("1234567890");
				e0Arg0.setCus_email("dfjghk");
				e0Arg0.setCard_id(2002);
				

				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertTrue(true);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
			public void testValidateCusdate7(){
				RegisterBo e0Obj = new RegisterBo();
				RegisterVo e0Arg0 = new RegisterVo();
				e0Arg0.setCus_pass("asdfghjk");
				e0Arg0.setCus_name("nishant"); 
				e0Arg0.setCus_dob("1991-OCT-25");
				e0Arg0.setCus_addr("muzaffarpur");
				e0Arg0.setCus_cont("8880820511");
				e0Arg0.setCus_email("nsr20@gmail.com");
				try{
					RegisterVo e0 = e0Obj.validateCustomer(e0Arg0);
					//TODO Based on your need, provide necessary assertion condition
				assertFalse(false);
				}catch(Exception e){
					//e.printStackTrace();
					//System.out.println(e);
					//assertTrue(true);
				}
	}
	
}
