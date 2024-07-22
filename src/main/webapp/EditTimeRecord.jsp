<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	System.out.println("called jsp");
 	int employeeCD = (int) request.getAttribute("employeeCD");
	System.out.println("employeeCD:" + employeeCD);
	String name = (String) request.getAttribute("name");
 	System.out.println("name:" + name);
 	int recordCD = (int) request.getAttribute("recordCD");
 	System.out.println("recordCD:" + recordCD);
 	String clockInTime = (String) request.getAttribute("clockInTime");
 	System.out.println("clockInTime:" + clockInTime);
 	String clockOutTime = (String) request.getAttribute("clockOutTime");
 	System.out.println("clockOutTime:" + clockOutTime);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>実績変更画面</title>
</head>
<body>
<form action="EditTimeRecordServlet" method="POST" id="editTimeRecordForm">
	<h1>実績変更</h1>
	<input type="hidden"  id="employeeCD" value=<%= employeeCD %> />
	<input type="hidden"  id="name" value=<%= name %> />
	<input type="hidden"  id="clockInTime" value=<%= clockInTime %> />
	<input type="hidden"  id="clockOutTime" value=<%= clockOutTime %> />
	<input type="hidden"  id="recordCD" name="recordCD" value=<%= recordCD %> />
	<div id="dateArea"></div>
	<div id="beforeTimeArea"></div>
	<div id="afterTimeArea"></div>
	<input type="submit" value="変更" />
</form>
<script type="module" src="js/editTimeRecord.js"></script>
<script type="module" src="js/const.js" ></script>
</body>
</html>