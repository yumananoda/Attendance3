<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.SelectEmployeeBean" %>
<%
	ArrayList<SelectEmployeeBean> selectEmployees = (ArrayList<SelectEmployeeBean>)session.getAttribute("selectEmployee");
	ArrayList<SelectEmployeeBean> selectRetireEmployees = (ArrayList<SelectEmployeeBean>)session.getAttribute("selectRetireEmployees");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員選択</title>
<link rel="stylesheet" href="css/checkboxSelected.css">
</head>
<body>
	<div>
		<label><input type="checkbox" id="retireCheck" />退職済</label>
		<div id="employed" class="checkbox selected">
			<%  for(SelectEmployeeBean selectEmployee : selectEmployees){ %>
			<table>
				<tr>
					<th>従業員コード</th>
					<th>氏名</th>
					<th></th>
				</tr>
				<tr>
					<td><%= selectEmployee.getEmployeeCD() %></td>
					<td><%= selectEmployee.getName() %></td>
					<td><a href="DispShiftRegisterServlet?employeeCD=<%= selectEmployee.getEmployeeCD() %>">固定シフト登録</a></td>
					<td><a href="DispShiftChangeServlet?employeeCD=<%= selectEmployee.getEmployeeCD() %>">日時指定シフト変更</a></td>
					<td><a href="DispEditEmployeeInfoServlet?employeeCD=<%= selectEmployee.getEmployeeCD() %>">従業員情報変更</a></td>
					<td><a href="DispTimeRecordServlet?employeeCD=<%= selectEmployee.getEmployeeCD() %>&name=<%= selectEmployee.getName() %>">勤怠状況</a></td>
				</tr>
			</table>
			<% } %>
		</div>

		<div id="retired" class="checkbox">
			<%  for(SelectEmployeeBean selectRetireEmployee : selectRetireEmployees){ %>
			<table>
				<tr>
					<th>従業員コード</th>
					<th>氏名</th>
					<th></th>
				</tr>
				<tr>
					<td><%= selectRetireEmployee.getEmployeeCD() %></td>
					<td><%= selectRetireEmployee.getName() %></td>
					<td><a href="DispEditEmployeeInfoServlet?employeeCD=<%= selectRetireEmployee.getEmployeeCD() %>">従業員情報変更</a></td>
					<td><a href="DispTimeRecordServlet?employeeCD=<%= selectRetireEmployee.getEmployeeCD() %>&name=<%= selectRetireEmployee.getName() %>">勤怠状況</a></td>
				</tr>
			</table>
			<% } %>
		</div>
	</div>	
	<script type="module" src="js/selectEmployee.js" ></script>
</body>
</html>
