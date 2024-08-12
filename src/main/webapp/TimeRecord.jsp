<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.TimeRecordsBean" %>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	String name = (String)request.getAttribute("name");
	String timeRecords = (String)request.getAttribute("timeRecords");
	String breaks = (String)request.getAttribute("breaks");
	String shift = (String)request.getAttribute("shift");
	String holiday = (String)request.getAttribute("holiday");
	System.out.println("timeRecords:" + timeRecords);
	System.out.println("breaks:" + breaks);
	System.out.println("shift:" + shift);
	System.out.println("holiday:" + holiday);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>勤怠状況</title>
</head>
<body>
    <h1>勤怠状況 - <%= name %>(従業員コード:<%= employeeCD %>)</h1>
    <div>
        <span class="btn-monthMove prev">＜</span>
        <span id="year"></span>
        <span id="month"></span>
        <span class="btn-monthMove next">＞</span>
    </div>
    
    <div id="timeRecordArea"></div>
        <input id="employeeCD" type="hidden" value=<%= employeeCD %> /> 
        <input id="name" type="hidden" value=<%= name %> /> 
        <input id="timeRecordHolder" type="hidden" value=<%= timeRecords %> /> 
        <input id="breaksHolder" type="hidden" value=<%= breaks %> /> 
        <input id="shiftHolder" type="hidden" value=<%= shift %> /> 
        <input id="holidayHolder" type="hidden" value=<%= holiday %> />
    <div id="totalDataArea"></div>
    <script type="module" src="js/timeRecord.js"></script>
    <script type="module" src="js/const.js" ></script>
</body>
</html>