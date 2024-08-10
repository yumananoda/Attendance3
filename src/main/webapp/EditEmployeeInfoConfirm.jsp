<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="enums.PositionEnum" %>
<%@ page import="enums.StoreNameEnum" %>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	System.out.println("employeeCD:" + employeeCD);
	String name = (String)request.getAttribute("name");
	String email = (String)request.getAttribute("email");
	String storeCD = (String)request.getAttribute("storeName");
	int storeCD2 = Integer.parseInt(storeCD);
	String hireDate = (String)request.getAttribute("hireDate");
	String positionCD = (String)request.getAttribute("position");
	int positionCD2 = Integer.parseInt(positionCD);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員情報変更確認画面</title>
</head>
<body>
	<form action="EditEmployeeInfoServlet" method="post">
		<div>
			<label form="name">従業員番号:</label>
			<input type="text" name="employeeCD" value=<%= employeeCD %> readonly />
		</div>
		<div>
			<label form="name">氏名:</label>
			<input type="text" id="name" name="name" value=<%= name %> readonly />
		</div>
		<div>
			<label form="email">メールアドレス:</label>
			<input type="text" id="email" name="email" value=<%= email %> readonly />
		</div>
		<div>
			<%
			StoreNameEnum storeName = StoreNameEnum.getById(storeCD2);
			String storeName2 = storeName.getLabel();
			%>
			<label form="storeName">所属店舗:</label>
			<input type="text" id="storeName" value=<%= storeName2 %> readonly />
			<input type="hidden" name="storeName" value=<%= storeCD2 %> />
		</div>
		<div>
			<label form="hireDate">入社日:</label>
			<input type="text" id="hireDate" name="hireDate" value=<%= hireDate %> readonly />
		</div>
		<div>
			<%
			PositionEnum position = PositionEnum.getById(positionCD2);
			String position2 = position.getLabel();
			%>
			<label form="position">役職:</label>
			<input type="text" id="position" value=<%= position2 %> readonly />
			<input type="hidden" name="position" value=<%= positionCD2 %> />
		</div>
		<input type="submit" value="申請する" />
	</form>
</body>
</html>