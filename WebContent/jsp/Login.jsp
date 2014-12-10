<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="util.PropertyUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book E Shopping Login</title>
<link href="/BookShopping/css/login.css" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<div class="logo">
		<img src="/BookShopping/images/logo.png"
			style="width: 350px; height: 100px;padding-left:15%;" />
		<div class="header">Online Shopping</div>
	</div>
	
		<center>
		   <div class="login1">
		   <h3> Log In</h3>
		   </div>
			<div class="login2">
				<form name="logindetails"
					action="http://localhost:9002/BookShopping/LoginController"
					method="post">
					<center>

						<%
							String msg = (String) request.getAttribute("error");
							if (msg != null) {%>
							<font color="red"><center>
							<%	out.println(msg);
							    System.out.println("value is :"+msg);
							}	%>
						</center>
						</font>
						<table cellpadding="5px">
								<td><%=PropertyUtil.getJspConstant("LOGIN_LABEL1",
					"/resource/JspConstant")%></td>
								<td ><input type="text" name="customerid" class="image">
							
									<%
										ArrayList<String> errors = new ArrayList<String>();
										boolean flag = false;
										if (request.getAttribute("errors") != null) {
											flag = true;
											errors = (ArrayList<String>) request.getAttribute("errors");

										}
									%> <br> <font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(0));
 	}
 %>
								</font>
								</td>

							</tr>


							<tr>
								<td><%=PropertyUtil.getJspConstant("LOGIN_LABEL2",
					"/resource/JspConstant")%></td>
								<td><input type="password" name="customerpassword"
									class="image1">
                                <br>
								<font color="red" align="left"> <%
 	if (flag == true) {
 		out.println(errors.get(1));
 	}
 %>
								</font></td>
							</tr>
						</table>
						
						<input type="submit" class="button" name="userlogin" value="Log In">
						<input type="reset" class="button" name="userreset" value="Reset">
                      <center> Not registered as yet? <font color="blue"><a href=/BookShopping/jsp/Register.jsp>Click here to register</a> </font> </center>
					</center>
					

				</form>
			</div>
		</center>
		<br>


	<div class="bottom"> &copy; 2014-2015  by YourBooks.com</div>
</body>
</html>