<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime" %>
<%
String employeeCD = (String)session.getAttribute("employeeCD"); 
System.out.println("employeeCD:" + employeeCD);

LocalDateTime clockIn = (LocalDateTime)session.getAttribute("clockIn"); 
System.out.println("clockIn:" + clockIn);

LocalDateTime breakIn = (LocalDateTime)session.getAttribute("breakIn"); 
System.out.println("breakIn:" + breakIn);

LocalDateTime breakOut = (LocalDateTime)session.getAttribute("breakOut"); 
System.out.println("breakOut:" + breakOut);

int isAdmin = (int)session.getAttribute("isAdmin"); 
System.out.println("isAdmin:" + isAdmin);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>打刻</title>
<link rel="stylesheet" href="css/home.css" />
</head>
<body>
<div class="sidebar">
	<% if(isAdmin == 1){ %>
		<jsp:include page="ManagerSideMenu.jsp" />
	<% }else{ %>
		<jsp:include page="EmployeeSideMenu.jsp" />
	<% } %>
</div>
<!--時刻表示-->    
<div class="contents">
	<div class="timeBox">
		<p id="realtime"></p>
		<script>
			function twoDigit(num) {
			let ret;
			if( num < 10 ) 
			ret = "0" + num; 
			else 
			ret = num; 
			return ret;
			}
			function showClock() {
			let nowTime = new Date();
			let nowHour = twoDigit( nowTime.getHours() );
			let nowMin  = twoDigit( nowTime.getMinutes() );
			let nowSec  = twoDigit( nowTime.getSeconds() );
			let msg = nowHour + ":" + nowMin + ":" + nowSec;
			document.getElementById("realtime").innerHTML = msg;
			}
			setInterval('showClock()',1000);
		</script>
	</div>

	<div class="buttonArea">
		<div class="buttonWrap1">
			<form action="ClockInServlet" class="clockIn" method="POST">
				<input type="hidden" id="employeeCD" name="employeeCD" value="<%=employeeCD %>">
			<% if(clockIn != null){ %> 
				<button class="styled" type="submit" value="clock-in" disabled>出勤</button>
				<% }else{ %>
				<button class="styled" type="submit" value="clock-in" >出勤</button>
				<% } %>
			</form>

			<form action="ClockOutServlet" class="clockOut" method="POST">
				<input type="hidden" id="employeeCD" name="employeeCD" value="<%=employeeCD %>">
				<%  if(clockIn == null || breakIn != null){ %>
				<button class="styled" type="submit" value="clock-out" disabled>退勤</button>
				<% }else{ %>
				<button class="styled" type="submit" value="clock-out" >退勤</button>
				<% } %>
			</form>
		</div>

		<div class="buttonWrap2">
			<form action="BreakInServlet" class="breakIn" method="POST">
				<input type="hidden" id="employeeCD" name="employeeCD" value="<%=employeeCD %>">
				<% if(clockIn == null || breakIn != null){ %> 
				<button class="styled" type="submit" value="break-in" disabled>休憩</button>
				<% }else{ %>
				<button class="styled" type="submit" value="break-in">休憩</button>
				<% } %>
			</form>

			<form action="BreakOutServlet" class="breakOut" method="POST">
				<input type="hidden" id="employeeCD" name="employeeCD" value="<%=employeeCD %>">
				<% if(clockIn == null || breakIn == null){ %>
				<button class="styled" type="submit" value="break-out" disabled>復帰</button>
				<% }else{ %>
				<button class="styled" type="submit" value="break-out" >復帰</button>
				<% } %>
			</form>
		</div>
	</div>
</div>
<%-- <a href = "DispTimeRecordServlet?employeeCD=<%= employeeCD %>">勤怠状況</a>
<a href = "DispHolidayApplicationServlet">有給申請</a>
<a href = "DispEditPasswordServlet">パスワード変更</a>
<a href = "DispHolidayApprovalServlet">有給承認</a>
<a href = "EmployeeRegister.jsp">従業員登録</a>
<a href = "DispSelectEmployeeServlet">従業員選択</a> --%>
</body>
</html>