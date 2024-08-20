<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	String shift = (String)request.getAttribute("shift");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/modal.css">
<title>日時指定シフト変更</title>
</head>
<body>
	<input id="employeeCD" type="hidden" value=<%= employeeCD %> />
	<input id="shift" type="hidden" value=<%= shift %> />

	<div id="selectChengeDate">
		<input type="date" />
	</div>
	
	<div id="error">
		<% if(request.getAttribute("message") != null){ %>
		<%= request.getAttribute("message") %>
		<% } %>
	</div>
	<div id="btnArea" class="btnArea">
		<button id="resetBtn">リセット</button>
		<button id="registerBtn">追加</button>
		<button id="registerBtn">登録</button>
	</div>
	<div id="layer" class="layer">
		<div class="modal">
			<button id="closeBtn" class="close-button">×</button>
			<div class="modal__content">
				変更が完了しました。
				<div>
					<a href="DispSelectEmployeeServlet">従業員選択画面へ</a><br>
					<a href="DispClockServlet">トップへ</a>
				</div>
			</div>
		</div>
	</div>
	<script type="module" src="js/const.js" ></script>
	<script type="module" src="js/shiftChange.js" ></script>
</body>
</html>