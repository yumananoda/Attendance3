<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.TimeRecordsBean" %>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	String name = (String)request.getAttribute("name");
	System.out.println(name);
	String timeRecords = (String)session.getAttribute("timeRecords");
	String shift = (String)session.getAttribute("shift");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/sample.css" />
</head>
<body>
    <h1>sample</h1>
    <div class="timeRecord-title">
    <span class="btn-monthMove prev">＜</span>
    <span id="year">2018</span>
    <span id="month">11</span>
    <span class="btn-monthMove next">＞</span>
    </div>
    
    <div id="timeRecord"></div>
    <input id="employeeCD" type="hidden" value=<%= employeeCD %> /> 
    <input id="name" type="hidden" value=<%= name %> /> 
    <input id="dataHolder" type="hidden" value=<%= timeRecords %> /> 
    <input id="shiftHolder" type="hidden" value=<%= shift %> /> 
    <div id="totalData"></div>
    <script type="module" src="js/timeRecord.js"></script>
    <script type="module" src="js/const.js" ></script>
</body>
</html>