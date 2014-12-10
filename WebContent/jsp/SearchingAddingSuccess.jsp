<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="vo.CartDataVO"%>
<%@ page import="util.PropertyUtil"%>
<%@ page import="constant.SuccessConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books Added</title>
<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
<link href="/BookShopping/css/home.css" rel="stylesheet" type="text/css"/>


</head>
<body>
<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
if(session.getAttribute("Userid")==null) {
	      response.sendRedirect("/BookShopping/jsp/Login.jsp");
	      } else {
%>

<center>
<h2 align="center" style="margin-top:120px;">
				<font color="green">Success</font>
			</h2>
</center>
	<div id="nav">
		<a class="title" href="#"><img src="/BookShopping/images/logo.png"
			widtd="200px" height="80px" /> </a>
		<ul id="navigation">
			<li><a class="home" href="http://localhost:9002/BookShopping/SearchBookController?userlogin=login">Search and Add</a>
			</li>
			<li><a class="home"
					href="http://localhost:9002/BookShopping/viewController?edit=edit">View
						and Edit</a>
				</li>
				<li><a class="home"
					href="http://localhost:9002/BookShopping/OrderPaymentController?payment=payment">Make
						Payment</a>
				</li>
			<li><a class="home" href="http://localhost:9002/BookShopping/jsp/Logout.jsp">Logout</a>
			</li>
		</ul>
	</div>
	
	<div class="content">
		<center>
			<div class="result">
				<font size="4px" color="green">
                <br> 
				<% ArrayList<CartDataVO> vo = (ArrayList<CartDataVO>) session.getAttribute("addsuccess");
   for(CartDataVO value:vo) {
%>

				<%="\"" + value.getBookName() + "\"  " + PropertyUtil.getSuccessMessage(SuccessConstant.BOOK_ADDED_MESSAGE1,"/resource/SuccessCode") + " " %>
				<%= "\"" + value.getQuantity() + "\"  " + PropertyUtil.getSuccessMessage(SuccessConstant.BOOK_ADDED_MESSAGE2,"/resource/SuccessCode") %>
				<br>
				<% } %>
				 <br>
				 <br>
				 </font>
			</div>
		</center>
	</div>
	<%} %>
</body>
</html>