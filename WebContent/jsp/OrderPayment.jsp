<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.PaymentVO"%>
<%@page import="vo.OrderDetailsVO"%>
<%@page import="java.util.Iterator"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/BookShopping/css/home.css" type="text/css" />
<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/BookShopping/css/payment.css"
	type="text/css" />
<title><%=PropertyUtil.getJspConstant("PAYMENT_HEADING", "/resource/JspConstant")%></title>
</head>
<body>
	<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
if(session.getAttribute("Userid")==null) {
	      response.sendRedirect("/BookShopping/jsp/Login.jsp");
	      System.out.println("In payment order cache");
	      } else {
%>
	<div id="nav">
		<a class="title" href="#"><img src="/BookShopping/images/logo.png"
			widtd="200px" height="80px" /> </a>
		<ul id="navigation">
			<li><a class="home"
				href="http://localhost:9002/BookShopping/SearchBookController?userlogin=edit">Search
					and Add</a></li>
			<li><a class="home"
				href="http://localhost:9002/BookShopping/viewController?edit=edit">View
					and Remove</a></li>
			<li><a class="home"
				href="http://localhost:9002/BookShopping/OrderPaymentController?payment=payment">Make
					Payment</a></li>
			<li><a class="home" href="http://localhost:9002/BookShopping/jsp/Logout.jsp">Logout</a></li>
		</ul>
	</div>

<center>
<h2 align="center" style="margin-top:120px;">
				<font color="0000FF"><%=PropertyUtil.getJspConstant("PAYMENT_HEADING", "/resource/JspConstant")%></font>
			</h2>
</center>

	<% 
	PaymentVO pto=new PaymentVO();
	List<String> dnerror = new ArrayList<String>();
	if(session.getAttribute("derror")!=null)
	{
		dnerror = (List<String>) session.getAttribute("derror");
	}
	if(session.getAttribute("elist")!=null)
	{
		pto=(PaymentVO)session.getAttribute("elist");
	}
	ArrayList<String> errorList=new ArrayList<String>();
	if(session.getAttribute("error")!=null)
	{
		errorList=(ArrayList<String>)session.getAttribute("error");
	}
	if(request.getAttribute("bookList")!=null)
	{
			List<PaymentVO> bookList=(List<PaymentVO>)request.getAttribute("bookList");
			if(bookList.isEmpty())
			{
				%>
				<center>
				<div class="no-match">
			<font color="red"> 
			<%=PropertyUtil.getJspConstant("PAYMENT_INVALID", "/resource/JspConstant")%></font> 
	</div>
	</center>
	<% 
			}
			else
			{
			Iterator<PaymentVO> itr=bookList.iterator();
%>
		<center>

	<form name="orderpay"
		action="http://localhost:9002/BookShopping/OrderPaymentController"
		method="post" autocomplete="off" onsubmit="makepayment.disabled = true; http://localhost:9002/BookShopping/OrderPaymentController">
		<center>
			

			<%
		if(!dnerror.isEmpty())
		{
			if(dnerror.get(0)!=null)
			{
		%>
			<h4>
				<font color="red"><%=dnerror.get(0)%></font>
			</h4>
			<% 
			}
		}
		%>

<div class="login1">
			<table border="1">
				<tr>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN2", "/resource/JspConstant")%></td>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN3", "/resource/JspConstant")%></td>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN11", "/resource/JspConstant")%></td>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN4", "/resource/JspConstant")%></td>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN12", "/resource/JspConstant")%></td>
				</tr>


				<%
	float sum=0;
	while(itr.hasNext())
		{
			PaymentVO book=(PaymentVO)itr.next();	
			sum=sum+book.getCost();
			
	%>
				<tr>
					<td><%=book.getBook_id()%></td>
					<td><%=book.getBook_name()%></td>
					<td><%=book.getQuantity()%></td>
					<td><%=book.getPrice()%></td>
					<td><%=book.getCost()%></td>
				</tr>
				<%
		}
		
%>
				<tr>
					<td colspan="4"><%=PropertyUtil.getJspConstant("TABLE_COLUMN17", "/resource/JspConstant")%></td>
					<td><%=sum%></td>
			</table>

			<table>
				<tr>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN13", "/resource/JspConstant")%><font
						color="red"><abbr>*</abbr>
					</font>
					</td>
					<td><input type="text" placeholder="eg.7234567890" autocomplete="off" name="accno"
						value="<%=pto.getAccno()%>">
					</td>
					<td>
						<% 
if(!errorList.isEmpty())
{
%><font color="red"> <% 
out.print(errorList.get(0));
}
%>
					</font></td>
				</tr>
				<tr>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN14", "/resource/JspConstant")%><font
						color="red"><abbr>*</abbr>
					</font>
					</td>
					<td><input type="text" placeholder="eg.AXIS" autocomplete="off" name="bname"
						value="<%=pto.getBname()%>">
					</td>
					<td>
						<% 
if(!errorList.isEmpty())
{
%><font color="red"> <% 
out.print(errorList.get(1));
}
%>
					</font></td>
				</tr>
				<tr>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN15", "/resource/JspConstant")%><font
						color="red"><abbr>*</abbr>
					</font>
					</td>
					<td><input type="text" placeholder="eg.abcd1234567" autocomplete="off" name="bcode" value="<%=pto.getIfsc()%>">
					</td>
					<td>
						<% 
if(!errorList.isEmpty())
{
%><font color="red"> <% 
out.print(errorList.get(2));
}
%>
					</font></td>
				</tr>
				<tr>
					<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN16", "/resource/JspConstant")%></td>
					<td><input type="text" name="amt" value="<%=sum %>"
						readonly="readonly" />
					</td>
					<br>
					<br>
				</tr>
			</table>
			</div>
			</center>
			
			<input type="submit" class="button"
				value="<%=PropertyUtil.getJspConstant("PAY_BUTTON", "/resource/JspConstant")%>"
				name="makepayment" />
			<%
			}
	}  %>
		</center>
		<br>
		<br>
</form>
<%} %>
</body>
</html>