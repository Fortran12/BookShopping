<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="vo.successVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.PropertyUtil"%>
<%@ page import="constant.SuccessConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/BookShopping/css/home.css" type="text/css" />
<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/BookShopping/css/table.css"
	type="text/css" />
<title>Update Success</title>
</head>
<body>
<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
if(session.getAttribute("Userid")==null) {
	      response.sendRedirect("/BookShopping/jsp/Login.jsp");
	      }
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
				<font color="green">Success</font>
			</h2>			

				<%

ArrayList<successVO> success_list = new ArrayList<successVO>();
ArrayList<String> errors = new ArrayList<String>();





if(session.getAttribute("success") !=null)
{
	success_list=(ArrayList<successVO>) session.getAttribute("success");
	%>
	<div class="content">
		<center>
			<div class="result">
	        <font size="4px" color="green">
				<br>
<% 
for(successVO v : success_list)
{ %>

				<!-- td><%=v.getBook_id() %></td> -->
				<%=PropertyUtil.getSuccessMessage(SuccessConstant.UPADTE_CART_MESSAGE1,"resource/SuccessCode")%>
				<%=v.getBook_name()%>
				<%=PropertyUtil.getSuccessMessage(SuccessConstant.UPADTE_CART_MESSAGE2,"resource/SuccessCode")%>
				<%=v.getQty() %>
				<%=PropertyUtil.getSuccessMessage(SuccessConstant.UPADTE_CART_MESSAGE3,"resource/SuccessCode")%><br>
				<%} %>
				<br> 
				<br>
				</font>
				</div>
</center>
				<%}
				%>

				<%
				if(request.getAttribute("errors") !=null)
				{
					errors=(ArrayList<String>) request.getAttribute("errors");
					//out.println(errors.get(0));

		int i=0;
	if(errors.get(i) != null)
		{%>
				<font color="red"> <%=errors.get(i) %> </font> <br>
				<%i++; }} %>
		
</body>
</html>