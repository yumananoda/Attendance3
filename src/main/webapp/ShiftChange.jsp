<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int employeeCD = (int)request.getAttribute("employeeCD");
	String shift = (String)request.getAttribute("shift");
	String exceptionShift = (String)request.getAttribute("exceptionShift");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/modal.css">
<title>日時指定シフト変更</title>
</head>
<body>
	<form id="shiftChangeForm" action="ShiftChangeServlet" method="post">
		<input id="employeeCD" type="hidden" name="employeeCD" value=<%= employeeCD %> />
		<input id="shift" type="hidden" value=<%= shift %> />
		<input id="exceptionShift" type="hidden" value=<%= exceptionShift %> />
		
		<div>
			<select id="application_category" name="application_category" required >
				<option value="">--申請区分を選択してください--</option>
				<option value="addShift">シフトに追加</option>
				<option value="removeShift">シフトから削除</option>
				<option value="changeTime">シフトの時間変更</option>
			</select>
		</div>

		<div id="selectChengeDate">
			<input type="date" id="changeDete" name="changeDete" required />
			<input type="time" id="changeTimeStart" name="changeTimeStart" required />~
			<input type="time" id="changeTimeEnd" name="changeTimeEnd" required />
		</div>

		
		<div id="error">
			<% if(request.getAttribute("message") != null){ %>
			<%= request.getAttribute("message") %>
			<% } %>
		</div>
		<div id="btnArea" class="btnArea">
			<input type="reset" value="リセット" />
			<button id="registerBtn">登録</button>
		</div>
		<div id="changeHistory">
			<div id="addShift"></div>
			<div id="removeShift"></div>
			<div id="changeShift"></div>
		</div>
	</form>
	<div id="layer" class="layer">
		<div class="modal">
			<button id="closeBtn" class="close-button">×</button>
			<div class="modal__content">
				変更が完了しました。<br>
				続けて登録・削除を行う場合は左上の×ボタンを押してください。
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