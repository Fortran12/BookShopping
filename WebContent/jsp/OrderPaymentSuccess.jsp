<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.PaymentVO"%>
<%@page import="vo.OrderDetailsVO"%>
<%@page import="java.util.Iterator"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="util.PropertyUtil"%>
<%@ page import="constant.SuccessConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/BookShopping/css/home.css" type="text/css" />
<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
<title>Order Payment Success</title>
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
					and Add</a>
			</li>
			<li><a class="home"
				href="http://localhost:9002/BookShopping/viewController?edit=edit">View
					and Remove</a>
			</li>
			<li><a class="home"
				href="http://localhost:9002/BookShopping/OrderPaymentController?payment=payment">Make
					Payment</a>
			</li>
			<li><a class="home" href="http://localhost:9002/BookShopping/jsp/Logout.jsp">Logout</a>
			</li>
		</ul>
	</div>

<center>
<h2 align="center" style="margin-top:120px;">
				<font color="green">Success</font>
			</h2>
</center>

	<%
		if (session.getAttribute("orderDetails") != null) {
			OrderDetailsVO odto = (OrderDetailsVO) session
					.getAttribute("orderDetails");
	%><div class="content">
		<center>
			<div class="result">
			<font color="green" size="4px">
				<br>
				<%=PropertyUtil.getSuccessMessage(
						SuccessConstant.PAYMENT_SUCCESS_MESSAGE1,
						"/resource/SuccessCode")%>
				<br>
				<%=PropertyUtil.getSuccessMessage(
						SuccessConstant.PAYMENT_SUCCESS_MESSAGE2,
						"/resource/SuccessCode")%>
				<%=odto.getTotal()%>
				<%=PropertyUtil.getSuccessMessage(
						SuccessConstant.PAYMENT_SUCCESS_MESSAGE3,
						"/resource/SuccessCode")%>
				<br>
				<%=PropertyUtil.getSuccessMessage(
						SuccessConstant.PAYMENT_SUCCESS_MESSAGE4,
						"/resource/SuccessCode")%>
				<%=odto.getOrder_id()%>
				<%=PropertyUtil.getSuccessMessage(
						SuccessConstant.PAYMENT_SUCCESS_MESSAGE5,
						"/resource/SuccessCode")%>
						<br> <br>
						</font>
			</div>
		</center>
	</div>
	<%
		}
	%>
</body>
</html>
