<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員登録</title>
</head>
<body>
    <h2>従業員登録</h2>
    <form id="registerForm">
        <div class="input-group">
            <label for="employeeName">名前:</label>
            <input type="text" name="name" id="name" required>
        </div>
        <div class="input-group">
            <label for="email">メールアドレス:</label>
            <input type="email" name="email" id="email" required>
        </div>
        <div id="position" class="radio-group"></div>
        <div>
            <label for="hireDate">入社日:</label>
            <input type="date" id="hireDate" name="hireDate" required>
        </div>
        <button type="submit" id="addemployee">追加</button>
        <button id="registerbtn">登録</button>
        <div id="error" class="error"></div>
    </form>
    <div id="registerUserList"></div>
    <script type="text/javascript" src="https://cdn.emailjs.com/dist/email.min.js"></script>
    <script type="module" src="js/const.js" ></script>
    <script type="module" src="js/employeeRegister.js" ></script>
</body>
</html>