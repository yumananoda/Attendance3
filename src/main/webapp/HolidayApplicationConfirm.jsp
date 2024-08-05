<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	System.out.println("employeeCD:" + employeeCD);
	String name = (String)request.getAttribute("name");
	Date date = (Date)request.getAttribute("date");
	String reason = (String)request.getAttribute("reason");
	String note = (String)request.getAttribute("note");
	System.out.println("note:" + note);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給申請確認画面</title>
</head>
<body>
	<form action="HolidayApplicationServlet" method="post">
	<input type="hidden" name="employeeCD" value=<%= employeeCD %> />
		<div>
			<label form="name">氏名:</label>
			<input type="text" id="name" name="name" value=<%= name %> readonly />
		</div>
		<div>
			<label form="date">申請日:</label>
			<input type="text" id="date" name="date" value=<%= date %> readonly />
		</div>
		<div>
			<label form="reason">申請理由:</label>
			<input type="text" id="reason" name="reason" value=<%= reason %> readonly />
		</div>
		<div>
			<label form="note">備考:</label>
			<% if(note != ""){ %>
				<input type="text" id="note" name="note" value=<%= note %> readonly />
			<% }else{ %>
				 <input type="text" id="note" name="note" readonly />
			<% } %>
		</div>
		<input type="submit" value="申請する" />
	</form>
</body>
</html>