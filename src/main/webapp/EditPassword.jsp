<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String password = (String)request.getAttribute("password");
	int employeeCD = (int)request.getAttribute("employeeCD");
	String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更画面</title>
<link rel="stylesheet" href="css/editPassword.css">
</head>
<body>
	<h1>パスワード変更</h1>
	<% if(message != null){ %>
		<div><%= message %></div>
	<% } %>
	<form action=EditPasswordServlet method="post">
		<input type="hidden" name="correct" value="<%= password %>" />
		<input type="hidden" name="employeeCD" value="<%= employeeCD %>" />
		<table>
			<tr>
				<th><label for="current">現在のパスワード</label></th>
				<td>
					<input type="password" id="currentPassword" class="inputPassword" name="current" />
					<img src="img/eye-solid.svg" alt="Eye Icon" id="current-eyeIcon" class=eye_Icon />
				</td>
			</tr>
			<tr>
				<th><label for="new">新しいパスワード</label></th>
				<td>
					<input type="password" id="new" class="inputPassword" name="new" placeholder="8文字以上入力してください" minlength="8" required />
					<img src="img/eye-solid.svg" alt="Eye Icon" id="new-eyeIcon" class=eye_Icon />
					<p id="disallow"></p>
					<p id="number_word">8文字以上:-</p>
					<nobr id="Alphabetic">英字:-</nobr>
					<nobr id="number">数値:-</nobr>
					<nobr id="special_characters">特殊文字:-</nobr>
				</td>
			</tr>
			<tr>
				<th><label for="newRe">新しいパスワード(確認用)</label></th>
				<td>
					<input type="password" id="newRe" class="inputPassword" placeholder="新しいパスワードを再度入力してください" required />
					<img src="img/eye-solid.svg" alt="Eye Icon" id="re-eyeIcon" class=eye_Icon />
					<p id="match_check"></p>
				</td>
			</tr>
		</table>
		<input type="submit" id="btn" value="確認画面へ" disabled />
	</form>
	<div>
		<h4>パスワードの設定時の注意点</h4>
		<p>・パスワードは8文字以上で設定してください。</p>
		<p>・英字(半角大文字・小文字)および数字もしくは一部記号が使用できます。</p>
		<p>※使用可能な記号:@$!%*?&.</p>
		<p>・英字、数字および記号をそれぞれ1文字以上使用してください。</p>

	</div>
	<script type="module" src="js/editPassword.js" ></script>
</body>
</html>