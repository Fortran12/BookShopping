<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.AddBookVO"%>
<%@ page import="vo.CartDataVO"%>
<%@ page import="util.PropertyUtil"%>
<%@ page import="constant.*"%>
<%@ page import="exception.DatabaseException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search and Add to Cart</title>
<link rel="stylesheet" href="/BookShopping/css/home.css" type="text/css" />
<link href="/BookShopping/css/Menu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/BookShopping/css/table.css"
	type="text/css" />
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
	<%!ArrayList list;
	ArrayList<String> value;
	AddBookVO vo;%>

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
			<li><a class="home"
				href="http://localhost:9002/BookShopping/jsp/Logout.jsp">Logout</a>
			</li>
		</ul>
	</div>
	<div class="content1">
		<center>
			<form
				name"search" action="http://localhost:9002/BookShopping/SearchBookController"
				metdod="post" autocomplete="on">
				<%
						if (request.getAttribute("DbError") != null) {
					%>
				<font color="red"> <%=request.getAttribute("DbError")%></font>
				<%
						} else {
					%>


				<table border="0">
					<tr>
						<td colspan="6" class="page-header"><center> <font color="blue"><h3 style="margin-top:30px;"> Search
								Books </h3> </font></center></td>
					</tr>
					<tr>
						<td><select name="category">
								<option value="all"><%=PropertyUtil.getJspConstant("CATEGORY_DEFAULT",
						"/resource/JspConstant")%></option>
								<%
									list = (ArrayList) session.getAttribute("searchCriteria");
										//list = (ArrayList) request.getAttribute("searchCriteria");
										value = (ArrayList<String>) list.get(0);
										for (String str : value) {
								%>
								<option value='<%=str%>'><%=str%></option>
								<%
									}
								%>
						</select></td>
						<td><select name="price">
								<option value=""><%=PropertyUtil.getJspConstant("PRICE_DEFAULT",
						"/resource/JspConstant")%></option>
								<%
									value = (ArrayList<String>) list.get(1);
										for (String str : value) {
								%>
								<option value='<%=str%>'><%=str%></option>
								<%
									}
								%>
						</select></td>
						<td><select name="language">
								<option value=""><%=PropertyUtil.getJspConstant("LANGUAGE_DEFAULT",
						"/resource/JspConstant")%></option>
								<%
									value = (ArrayList<String>) list.get(2);
										for (String str : value) {
								%>
								<option value='<%=str%>'><%=str%></option>
								<%
									}
								%>
						</select></td>
						<td><select name="binding">
								<option value=""><%=PropertyUtil.getJspConstant("BINDING_DEFAULT",
						"/resource/JspConstant")%></option>
								<%
									value = (ArrayList<String>) list.get(3);
										for (String str : value) {
								%>
								<option value='<%=str%>'><%=str%></option>
								<%
									}
								%>
						</select></td>
						<td><select name="deliverytime">
								<option value=""><%=PropertyUtil.getJspConstant("DELIVERY_TIME_DEFAULT",
						"/resource/JspConstant")%></option>
								<%
									value = (ArrayList<String>) list.get(4);
										for (String str : value) {
								%>
								<option value='<%=str%>'><%=str%></option>
								<%
									}
								%>
						</select></td>
						<td><input type="submit" name="search" class="button"
							value='<%=PropertyUtil.getJspConstant("SEARCH_BUTTON",
						"/resource/JspConstant")%>' />
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	<br>
	<%if (session.getAttribute("NoSelection") != null) {
		%>
	<center>
		<font color="red"><%=PropertyUtil.getErrorMessage(
								ErrorConstant.SELECTION_EMPTY,
								"/resource/ErrorCode")%></font>
	</center>
	<%
						
	session.removeAttribute("NoSelection");
	} else if(session.getAttribute("Invalid Entry") != null) { %>
	<font color="red">
		<center>Please check quantity needed field to proceed ahead</center> </font>
	<%
	session.removeAttribute("Invalid Entry");
	} %>
	<%
					if (application.getAttribute("searchResult") != null) {
							ArrayList<AddBookVO> list = (ArrayList<AddBookVO>) application
									.getAttribute("searchResult");
							HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
							if (!list.isEmpty()) {
				%>
	<form name="result"
		action="http://localhost:9002/BookShopping/SearchBookController"
		method="post" autocomplete="off"
		onsubmit="addcart.disabled = true; http://localhost:9002/BookShopping/SearchBookController ">
		<center>
			<div class="CSS_Table_Example">
				<table>
					<tr>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN1",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN2",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN3",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN4",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN5",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN6",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN7",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN8",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN9",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN10",
								"/resource/JspConstant")%></td>
						<td><%=PropertyUtil.getJspConstant("TABLE_COLUMN11",
								"/resource/JspConstant")%></td>
					</tr>
					<%
								for (AddBookVO vo : list) {
							%>
					<tr>

						<%
									if (vo.getAvailability() == 0) {
								%>
						<td><input type="checkbox" name="items"
							value='<%=vo.getBookId()%>' disabled />
						</td>
						<%
									} else if (session.getAttribute("result") != null) {
														boolean flag = true;
														ArrayList<CartDataVO> error = (ArrayList<CartDataVO>) session
																.getAttribute("result");
														for (CartDataVO message : error) {
															if (message.getBookId() == vo.getBookId()) {
								%>
						<td><input type="checkbox" name="items"
							value='<%=vo.getBookId()%>' <%="checked"%> />
						</td>
						<%
									flag = false;
																break;
															}
														}
														if (flag) {
								%>
						<td><input type="checkbox" name="items"
							value='<%=vo.getBookId()%>' />
						</td>
						<%
									}
													} else {
								%>
						<td><input type="checkbox" name="items"
							value='<%=vo.getBookId()%>' />
						</td>
						<%
									}
								%>
						<td><%=vo.getBookId()%></td>
						<td><%=vo.getBookName()%></td>
						<td><%=vo.getPrice()%></td>
						<td><%=vo.getLanguage()%></td>
						<td><%=vo.getAuthorName()%></td>
						<td><%=vo.getPublisherName()%></td>
						<td><%=vo.getBinding()%></td>
						<td><%=vo.getDeliveryTime()%></td>
						<td><%=vo.getAvailability()%></td>

						<%
									if (vo.getAvailability() == 0) {
								%>
						<td><input type="text" name='<%=vo.getBookId()%>'
							value='<%=PropertyUtil.getJspConstant(ErrorConstant.OUT_OF_STOCK,
								"/resource/ErrorCode")%>'
							disabled />
						</td>
						<%
									} else if (session.getAttribute("result") != null) {
														boolean flag = true;
														ArrayList<CartDataVO> error = (ArrayList<CartDataVO>) session
																.getAttribute("result");
														for (CartDataVO message : error) {
															if (message.getBookId() == vo.getBookId()) {
															
																if (message.isFlag()) {
																	if (message.getQuantity() == -999) {
								%>
						<td><input type="text" name='<%=vo.getBookId()%>'
							value='<%=message
														.getTextQuantity()%>' /> <br>
							<font color="red"> <%=message
														.getErrorMessage()%> </font>
						</td>
						<%
									flag = false;
																		break;
																	} else {
								%>
						<td><input type="text" name='<%=vo.getBookId()%>'
							value='<%=message.getQuantity()%>' /><br> <font color="red">
								<%=message
														.getErrorMessage()%> </font>
						</td>
						<%
									flag = false;
																		break;
																	}
																} else {
								%>
						<td><input type="text" name='<%=vo.getBookId()%>'
							value='<%=message.getQuantity()%>' />
						</td>
						<%
									flag = false;
																	break;
																}
															}
														}
														if (flag) {
								%>
						<td><input type="text" name='<%=vo.getBookId()%>' />
						</td>
						<%
									}
								%>
						<%
						            
									} else {
								%>

						<td><input type="text" name='<%=vo.getBookId()%>' />
						</td>
						<%
									}
													map.put(vo.getBookId(), vo.getAvailability());
								%>

					</tr>
					<%
								}
											application.setAttribute("availability", map);
							%>
				</table>
			</div>
			<input type="submit" name="addcart" class="button"
				value='<%=PropertyUtil.getJspConstant("ADDCART_BUTTON",
								"/resource/JspConstant")%>' />
			<br> <br>
		</center>
	</form>
	<%
					} else {
				%>
	<center>
		<br> <br>
		<div class="no-match">
			<font color="red"> <%=PropertyUtil.getErrorMessage(
								ErrorConstant.SEARCH_NOT_FOUND,
								"/resource/ErrorCode")%> </font>
		</div>
	</center>
	<%
					}
						}
						}
	      }
				%>

</body>
</html>