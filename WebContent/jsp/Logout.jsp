<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
<link href="/BookShopping/css/logout.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
if(session.getAttribute("Userid")==null) {
	      response.sendRedirect("/BookShopping/jsp/Login.jsp");
	      }
session.invalidate(); 
 %> 
</br>
	</br>
	<center>
	<div class="message">
	<table align="center" width="100%" style="border-color: #E5E6E6;"
		border="0">

		<tr>
			<td> <img src="/BookShopping/images/ok.png"> </td> 
			<td>
				<p style="padding-left: 20px;">
				  <h4> <font color=green> You have successfully logged out of <b>  Online Book Shopping Portal  </b>  </font>  </h4>
				</p>



				<p style="padding-left: 20px;">
					<h4> <a href="/BookShopping/jsp/Login.jsp">Click here</a> <font color=green> to login again </font> </h4>
				</p>
              

			</td>

		</tr>
	</table>
	</div>
	</center>

</body>
</html>