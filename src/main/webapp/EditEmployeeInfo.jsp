<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("editEmp.Jsp");
	String employeeInfo = (String)request.getAttribute("employeeInfo");
System.out.println(employeeInfo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員情報変更</title>
</head>
<body>
	<input id="employeeInfo" type="hidden" value=<%= employeeInfo %> />
	<div id="empInfoDiv"></div>
	
	<script type="module" src="js/const.js" ></script>
	<script type="module" src="js/editEmployeeInfo.js" ></script>
</body>
</html>