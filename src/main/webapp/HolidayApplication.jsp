<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String employeeCD = (String)session.getAttribute("employeeCD"); 
System.out.println("employeeCD:" + employeeCD);
String name = (String)request.getAttribute("name"); 
System.out.println("name:" + name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給申請画面</title>
<link rel="stylesheet" href="css/tabMenu.css">
</head>
<body>
	<ul class="tab-button">
		<li class="tab tab-01 is-active" value="0">有給休暇</li>
		<li class="tab tab-02" value="1">無給休暇</li>
	</ul>
	<div class="tab-contents">
		<form action="HolidayApplicationConfirmServlet" method="post" id="HolidayForm">
			<div class="content tab-01 is-display">
				<table>
					<tr>
						<th>従業員コード</th>
						<td><input type="text" name="employeeCD" value="<%= employeeCD %>" readonly /></td>
					</tr>
					<tr>
						<th>氏名</th>
						<td><input type="text" name="name" value="<%= name %>" readonly /></td>
					</tr>
					<tr>
						<th>日付</th>
						<td><input type="date" id="applicationDate1" name="startDate" required />～
						<input type="date" id="applicationDate2" name="endDate" required /></td>
					</tr>
					<%-- <tr>
						<th>休暇種類</th>
						<td id="SelectHolidayKind">
							<div>
								<input type="radio" id="full" class="holidayKind" name="holidayKind" value="full" checked />
								<label for="full">全休</label>
							</div>
							<div>
								<input type="radio" id="half" class="holidayKind" name="holidayKind" value="half" />
								<label for="half">半休</label>
							</div>
							<div>
								<input type="radio" id="multiple" class="holidayKind" name="holidayKind" value="multiple" />
								<label for="multiple">連休取得</label>
							</div>
							<div id="multipleMenu">
								<input type="date" id="startDate" name="startDate" required />〜<input type="date" id="endDate" name="endDate" required />
							</div>
						</td>
					</tr> --%>
					<tr>
						<th>事由</th>
						<td><textarea name="reason" placeholder="申請理由を入力してください。" required /></textarea></td>
					</tr>
					<tr>
						<th>備考</th>
						<td><textarea name="note"></textarea></td>
					</tr>
				</table>
			</div>
			<div class="content tab-02">
				<table>
					<tr>
						<th>従業員コード</th>
					</tr>
					<tr>
						<th>氏名</th>
					</tr>
					<tr>
						<th>日付</th>
					</tr>
					<tr>
						<th>期間</th>
					</tr>
					<tr>
						<th>事由</th>
					</tr>
				</table>
			</div>
			<input type="submit" value="確認画面へ" />
		</form>
	</div>

	<script type="module" src="js/tabMenu.js" ></script>
	<script type="module" src="js/holidayApplication.js" ></script>
</body>
</html>