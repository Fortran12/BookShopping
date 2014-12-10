<%@page import="constant.SuccessConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="util.PropertyUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/BookShopping/css/register.css" rel="stylesheet" type="text/css"/>
<title>Success</title>
</head>
<body>
<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
%>
<div class="logo">
		<img src="/BookShopping/images/logo.png"
			style="width: 340px; height: 90px;padding-left:15%;" />
		<div class="header">Online Shopping</div>
	</div>
	
	<center>
<div class="result">
<font color="green">
<h3> <%=PropertyUtil.getSuccessMessage(SuccessConstant.REGISTER_MESSAGE,"/resource/SuccessCode")%> </h3>

<%
int cid = (Integer)application.getAttribute("id");
int caid =(Integer)application.getAttribute("caid");

 		out.println(" Customer ID is : " + cid);
 		out.println(" Cart ID is : " + caid);
 		//application.removeAttribute("id");
 		//application.removeAttribute("id");
 		%>
 		</font> 
 		<br>
 		<a href=/BookShopping/jsp/Login.jsp><font color="blue" font-size="4px">Click here to login again</font></a>
 		<br>
 		<br>
</div>
</center>
</body>
</html>