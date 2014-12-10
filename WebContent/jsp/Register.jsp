
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.RegisterVo"%>
<%@page import="util.PropertyUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link href="/BookShopping/css/register.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<%
response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
response.addHeader("Pragma", "no-cache"); 
response.addDateHeader ("Expires", 0);
%>


	<%
		String addrval = null;
		RegisterVo rvo = (RegisterVo) request.getAttribute("valid");
	%>

	<div class="logo">
		<img src="/BookShopping/images/logo.png"
			style="width: 340px; height: 90px; padding-left: 15%;" />
		<div class="header">Online Shopping</div>
	</div>

	<center>
		<div class="login1">New User Registration Form</div>
		<div class="login2" class="header">
			<form name="register"
				action="http://localhost:9002/BookShopping/RegisterController"
				method="post" autocomplete="off" onsubmit="userregister.disabled = true; http://localhost:9002/BookShopping/RegisterController ">
				<font color="red">
					<%
						String msg = (String) request.getAttribute("error");
						if (msg != null)
							out.println(msg);
					%>
				</font>
				<center>
					<table cellpadding="5px">
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL1",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<td><input type="text" name="name"
								placeholder="eg.John Smith" autocomplete="off"
								value=<%if (rvo == null) {
				out.println(" ");
			} else {
				out.println(rvo.getCus_name());
			}%>>

								<%
									ArrayList<String> errors = new ArrayList<String>();
									boolean flag = false;
									if (request.getAttribute("errors") != null) {
										flag = true;
										errors = (ArrayList<String>) request.getAttribute("errors");

									}
								%><br> <font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(0));
 	}
 %>
							</font></td>
						</tr>
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL2",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<td><input type="password" name="userpassword"
								placeholder="eg.abc123a" autocomplete="off"
								value=<%if (rvo == null) {
				out.println(" ");
			} else {
				out.println(rvo.getCus_pass());
			}%>>
								<br>
							<font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(1));
 	}
 %>
							</font></td>
						</tr>
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL3",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<td><input type="text" name="dob"
								placeholder="eg.1991-MAY-21" autocomplete="off"
								value=<%if (rvo == null) {
				out.println(" ");
			} else {
				out.println(rvo.getCus_dob());
			}%>>
								<br>
							<font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(2));
 	}
 %>
							</font></td>
						</tr>
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL4",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<%
								if (rvo == null) {
									addrval = "";
								} else {
									addrval = (String) rvo.getCus_addr();
								}
							%>
							<td><textarea name="addr" type="text" autocomplete="off"
									placeholder="eg.Sipcot, Siruseri Chennai Taminadu-638881"><%=addrval%></textarea>
								<font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(3));
 	}
 %>
							</font></td>
						</tr>
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL5",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<td><input type="text" name="phno"
								placeholder="eg.7875191730" autocomplete="off"
								value=<%if (rvo == null) {
				out.println(" ");
			} else {
				out.println(rvo.getCus_cont());
			}%>>
								<font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(4));
 	}
 %>
							</font></td>
						</tr>
						<tr>
							<td><%=PropertyUtil.getJspConstant("REGISTER_LABEL6",
					"/resource/JspConstant")%><font
								color="red"><abbr>*</abbr> </font></td>
							<td><input type="text" name="mail"
								placeholder="eg. abc@gmail.com" autocomplete="off"
								value=<%if (rvo == null) {
				out.println(" ");
			} else {
				out.println(rvo.getCus_email());
			}%>>
								<font color="red"> <%
 	if (flag == true) {
 		out.println(errors.get(5));
 	}
 %>
							</font></td>
						</tr>


					</table>
					<center>
						<input type="submit" class="button" name="userregister"
							value="Register" style="margin-right: 10px;"> <input
							type="reset" class="button" name="reset" value="Reset"
							style="margin-left: 10px">
					</center>
		</div>
	</center>
	</form>
</body>
</html>