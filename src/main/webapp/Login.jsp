<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.Date, java.text.DateFormat" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>ログイン</title>
  </head>
  <body>
    <!--ログイン-->
    <form action="LoginServlet" method="POST">
      <div>
        <label for="employeeCD">従業員コード:</label>
        <input type="text" name="employeeCD" pattern="\d*">
      </div>
      <div>
        <label for="pass">パスワード:</label>
        <input type="password" id="pass" name="password" minlength="8" required />
      </div>
      <button type="submit" value="login" >ログイン</button>
      <p>
      <% if(request.getAttribute("message") != null){ %>
      <%= request.getAttribute("message") %>
      <% } %>
      </p>
    </form>
  </body>
</html>