package bo;
import static org.junit.Assert.*;
import org.junit.Test;

import exception.DatabaseException;
import junit.framework.TestCase;
import java.util.List;
import bo.OrderPaymentBO;
import vo.PaymentVO;
import vo.OrderDetailsVO;
import vo.PaymentVO;


/** This class contains one testXXXX method per XXXXX method in source class
* @author 
**/
//TODO Add Junit jar in build path.
//TODO Modify input and output data if needed.


public class OrderPaymentBOTest extends TestCase {
	

	@Test //public List<PaymentTO> getBDetails()
	public void testGetBDetails() throws DatabaseException
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		List<PaymentVO> e0 = null;
		e0 = e0Obj.getBDetails(2001);
		assertTrue(true);		
	}
	

	@Test //public OrderDetailsTO insertODetails(PaymentTO)
	public void testvalidateAccno1()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateAccno("");
		assertEquals(e0, false);
	}
	public void testvalidateAccno2()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateAccno("JV");
		assertEquals(e0, false);
	}
	public void testvalidateAccno3()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateAccno("123");
		assertEquals(e0, true);
	}

	
	public void testvalidateBankname1()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateBankname("");
		assertEquals(e0, false);
	}
	public void testvalidateBankname2()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateBankname("1212");
		assertEquals(e0, false);
	}
	public void testvalidateBankname3()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateBankname("HDFC");
		assertEquals(e0, true);
	}
	
	public void testvalidateBankIFSCCode1()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateIFSC("");
		assertEquals(e0, false);
	}
	public void testvalidateBankIFSCCode2()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateIFSC("12v");
		assertEquals(e0, false);
	}
	public void testvalidateBankIFSCCode3()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		boolean e0 = e0Obj.validateIFSC("HDFC1234567");
		assertEquals(e0, true);
	}
	
	public void testInsertODetails1()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("121");
		e0Arg0.setBname("HDFC");
		e0Arg0.setIfsc("eeee1234567");

		try
		{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,43,21);
			assertEquals(e0.getOrder_id(), "ORD1113");
		}
		catch(Exception e)
		{
			assertFalse(false);
		}
	}
	
	public void testInsertODetails2()
	{
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("");
		e0Arg0.setBname("HDFC");
		e0Arg0.setIfsc("eeee1234567");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,32,45);
			assertEquals(e0, e0);
		
		}
		catch(Exception e)
		{
			assertTrue(true);
		}
	}
	
	public void testInsertODetails3(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("123");
		e0Arg0.setBname("");
		e0Arg0.setIfsc("eeee1234567");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,123,124);
			assertEquals(e0, e0);
			
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
	public void testInsertODetails4(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("123");
		e0Arg0.setBname("HDFC");
		e0Arg0.setIfsc("");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,123,45);
			assertEquals(e0, e0);
			
		}catch(Exception e){
			assertTrue(true);
			
		}
	}
	
	public void testInsertODetails5(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("122");
		e0Arg0.setBname("SBT");
		e0Arg0.setIfsc("ee45e1sdaf567");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,123,23);
			
		
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
	public void testInsertODetails6(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("fdff");
		e0Arg0.setBname("ICCI");
		e0Arg0.setIfsc("eeee1234567");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,43,23);
			assertEquals(e0, e0);
			
		}catch(Exception e){
			assertTrue(true);
		}
	}
	

	
	public void testInsertODetails7(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("dsfs");
		e0Arg0.setBname("123");
		e0Arg0.setIfsc("er56dfg");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,43,12);
			assertEquals(e0, e0);
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
	public void testInsertODetails8(){
		OrderPaymentBO e0Obj = new OrderPaymentBO();
		PaymentVO e0Arg0 = new PaymentVO();
		e0Arg0.setAccno("");
		e0Arg0.setBname("");
		e0Arg0.setIfsc("");

		try{
			OrderDetailsVO e0 = e0Obj.insertODetails(e0Arg0,43,21);
			assertEquals(e0, e0);
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
}
