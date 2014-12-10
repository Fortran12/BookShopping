	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%@page import="vo.*"%>
	<%@page import="java.util.*"%>
	<%@ page import="util.PropertyUtil"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="/BookShopping/css/home.css" type="text/css" />
	<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/BookShopping/css/table.css"
		type="text/css" />
	<title></title>
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
		<%

ArrayList<String> errors = new ArrayList<String>();

%>
<center>
<font color="0000FF"> <h2 style="margin-top:130px;"> View and Edit Books </h2> </font> 
</center>		
		<div class="content">
		
			<center>
				<form name="f1"
					action="http://localhost:9002/BookShopping/viewController"
					method="post" autocomplete="off">
					<!-- Row Data -->
	
					<% if(session.getAttribute("view")!=null)
	
	{
	
	List<viewVO> bookList=(List<viewVO>)session.getAttribute("view");
	
	if(bookList.isEmpty())
	
	{
	
	%>
	               <div class="no-match">
					<center>
	
						
	
							<font color="red"> <%=PropertyUtil.getJspConstant("REMOVE_INVALID", "/resource/JspConstant")%></font>
	
						
	
					</center>
	</div>
					<% 
	
	}
	
	else if(session.getAttribute("view")!= null)
	
	{
	
	int i=1; 
	
	ArrayList<viewVO> a=(ArrayList<viewVO>) session.getAttribute("view");
	
	
	%>
	
				
	<font color="red">
								<%
	
	
	if(session.getAttribute("errors") !=null)
	
	{
	
	out.println(session.getAttribute("errors"));

	}
 %>
	</font>
				
				<div class="CSS_Table_Example">
						<table>
	
							<tr>
	                             
	
								<td> <b><%=PropertyUtil.getJspConstant("TABLE_COLUMN2","/resource/JspConstant")%> </b></td>
								<td><b> <%=PropertyUtil.getJspConstant("TABLE_COLUMN18","/resource/JspConstant")%></b></td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN3","/resource/JspConstant")%></b></td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN4","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN8","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN5","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN6","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN7","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN10","/resource/JspConstant")%></b> </td>
								<td><b><%=PropertyUtil.getJspConstant("TABLE_COLUMN11","/resource/JspConstant")%></b> </td>
	                             
	                      		</tr>
							
							<% for(viewVO vo : a)

{%>
							
								<td>
									<%out.println(vo.getBookId());%>
								</td>
								<td>
									<%out.println(vo.getCat_id());%>
								</td>
								<td>
									<%out.println(vo.getBookName());%>
								</td>
								<td>
									<%out.println(vo.getPrice());%>
								</td>
								<td>
									<%out.println(vo.getBinding());%>
								</td>
								<td>
									<%out.println(vo.getLanguage());%>
								</td>
								<td>
									<%out.println(vo.getAuthorName());%>
								</td>
								<td>
									<%out.println(vo.getPublisherName());%>
								</td>
								<td>
									<%out.println(vo.getAvailability());%>
								</td>
								<td><input type="textbox" name='<%= vo.getBookId()%>'
									value="<%out.println(vo.getQuantity());%>">
								</td>
							</tr>
							<% i++;
				}
				%>
						</table>
					</div>
					<br>
	
					<center>
						<input type="submit" name="update" class="button"
							value="<%=PropertyUtil.getJspConstant("UPDATECART_BUTTON","/resource/JspConstant")%>" />
					</center>
					<%
				}
				}
	%>
				</form>
			</center>
		</div>
	<%} %>
	</body>
	</html>