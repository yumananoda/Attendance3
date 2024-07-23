<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("editEmp.Jsp");
	String employeeInfo = (String)request.getAttribute("employeeInfo");
	System.out.println("employeeInfo:" + employeeInfo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員情報変更</title>
</head>
<body>
	<h1>従業員情報変更</h1>
	<p>以下の項目に変更したい情報をご入力の上、「変更」ボタンをクリックしてください。</p>
	<input id="employeeInfo" type="hidden" value=<%= employeeInfo %> />
	<form action="EditEmployeeInfoServlet" method="post" id="editForm">
		<table>
			<tr>
				<th>従業員番号</th>
				<td id=employeeCD></td>
			</tr>
			<tr>
				<th>氏名</th>
				<td id=name></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td id=email></td>
			</tr>
			<tr>
				<th>店舗名</th>
				<td id=storeName></td>
			</tr>
			<tr>
				<th>入社日</th>
				<td id=hireDate></td>
			</tr>
			<tr>
				<th>役職</th>
				<td id=position></td>
			</tr>
		</table>
		<input type="submit" value="変更">
	</form>
	<button type="reset" id="resetBtn">リセット</button>
	<script type="module" src="js/const.js" ></script>
	<script type="module" src="js/editEmployeeInfo.js" ></script>
</body>
</html>