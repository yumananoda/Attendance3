<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	System.out.println("called jsp");
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
	<input type="time" value="clockInTime" />
</body>
</html>