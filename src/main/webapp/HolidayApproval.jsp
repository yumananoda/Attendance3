<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.ApplicationBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%
	ArrayList<ApplicationBean> applicationList = (ArrayList<ApplicationBean>)request.getAttribute("applicationList");
	/* System.out.println(applicationList.get(0).getName()); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給承認画面</title>
</head>
<body>
<div>
	<p>チェックした項目全ての操作</p>
	<button>承認</button>
	<button>拒否</button>
</div>
<ul class="tab-button">
	<li class="tab tab-01 is-active" value="0">未承認</li>
	<li class="tab tab-02" value="1">承認済</li>
	<li class="tab tab-03" value="3">拒否済</li>
</ul>
<div class="tab-contents">
	<div class="content tab-01 is-display">
		<table>
			<tr>
				<th></th>
				<th>氏名</th>
				<th>役職</th>
				<th>所属店舗</th>
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
					<td><input type="checkbox" /></td>
					<td><%= item.getName() %></td>
					<td><%= item.getPosition() %></td>
					<td><%= item.getStoreCD() %></td>
					<td><%= item.getStartDate() %>(<%= item.getHolidayDays() %>日間)</td>
					<td><%= item.getApprovalStatus() %></td>
					<td><%= item.getReason() %></td>
					<td><%= formattedDate %></td>
					<td><a href="#">詳細</a></td>
					<td><button>承認</button><button>拒否</button></td>
				</tr>
			<% } %>
		</table>
	</div>
	<div class="content tab-02"></div>
	<div class="content tab-03"></div>
</div>
</body>
</html>