<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.ApplicationBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%
	ArrayList<ApplicationBean> applicationList = (ArrayList<ApplicationBean>)request.getAttribute("applicationList");
	System.out.println(applicationList.get(0).getName());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給承認画面</title>
</head>
<body>
	<table>
		<tr>
		    <th>氏名</th>
		    <th>役職</th>
		    <th>取得希望日(期間)</th>
		    <th>休暇種別</th>
		    <th>事由</th>
		    <th>申請日</th>
		    <th>詳細</th>
		    <th>処理</th>
		</tr>
		<%  for(ApplicationBean item : applicationList){ %>
			<%
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		    String formattedDate = sdf.format(item.getDate());
			%>
			<tr>
				<td><%= item.getName() %></td>
				<td><%= item.getPosition() %></td>
				<td><%= item.getStartDate() %></td>
				<td><%= item.getApprovalStatus() %></td>
				<td><%= item.getReason() %></td>
				<td><%= formattedDate %></td>
				<td><a href="#">詳細</a></td>
				<td><%= item.getName() %></td>
			</tr>
		<% } %>
	</table>
</body>
</html>