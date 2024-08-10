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
	String clockInTime = (String)request.getAttribute("clockInTime");
	System.out.println("clockInTime:" + clockInTime);
	String clockOutTime = (String)request.getAttribute("clockOutTime");
	System.out.println("clockOutTime:" + clockOutTime);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>打刻情報変更画面</title>
</head>
<body>
<form action="EditTimeRecordServlet" method="POST" id="editTimeRecordForm">
	<h1>実績変更-<%= name %>(従業員番号:<%= employeeCD %>)</h1>
	<input type="hidden"  id="recordCD" name="recordCD" value=<%= recordCD %> />
	<input type="hidden"  id="clockInTime" value=<%= clockInTime %> />
	<input type="hidden"  id="clockOutTime" value=<%= clockOutTime %> />
	<div id="dateArea">
		<p id="selectDate"></p>
		<input type="hidden" id="selectDateValue" name="selectDateValue" />
	</div>
	<div id="beforeTimeArea">
		<p>変更前</p>
		<input type="time" id="beforeClockInTime" name="beforeClockInTime" disabled />
		<input type="time" id="beforeClockOutTime" name="beforeClockOutTime" disabled />
	</div>
	<div id="afterTimeArea">
		<p>変更後</p>
		<input type="time" id="afterClockInTime" name="afterClockInTime" required />
		<input type="time" id="afterClockOutTime" name="afterClockOutTime" required />
	</div>
	<div id="error" class="error"></div>
	<input type="submit" value="変更" />
</form>
<script type="module" src="js/editTimeRecord.js"></script>
<script type="module" src="js/const.js" ></script>
</body>
</html>