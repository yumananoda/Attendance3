<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String employeeCD = (String)session.getAttribute("employeeCD"); 
System.out.println("employeeCD:" + employeeCD);
String name = (String)session.getAttribute("name"); 
System.out.println("name:" + name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/sideMenu.css" />
</head>
<body>
  <div class="container">

  <div class="sidebar">
    <ul>
      
    </ul>
    <ul>
      <li class="list active">
        <a href="#">
          <span class="icon"><ion-icon name="calendar-number-outline"></ion-icon></span>
          <span class="title">勤怠状況</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="document-text-outline"></ion-icon></span>
          <span class="title">有給申請</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="key-outline"></ion-icon></span>
          <span class="title">パスワード変更</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="checkbox-outline"></ion-icon></span>
          <span class="title">有給承認</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="person-add-outline"></ion-icon></span>
          <span class="title">従業員登録</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="people-outline"></ion-icon></span>
          <span class="title">従業員選択</span>
        </a>
      </li>
      <li class="list">
        <a href="#">
          <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
          <span class="title">ログアウト</span>
        </a>
      </li>
    </ul>
  </div>
  </div>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>     
</body>
</html>