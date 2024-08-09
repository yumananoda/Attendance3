<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.ApplicationBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="enums.HolidayStatusEnum" %>
<%@ page import="enums.PositionEnum" %>
<%@ page import="enums.StoreNameEnum" %>
<%
	ArrayList<ApplicationBean> applicationListOfUnapproved = (ArrayList<ApplicationBean>)request.getAttribute("applicationListOfUnapproved");
	System.out.println("applicationListOfUnapproved:" + applicationListOfUnapproved);
	ArrayList<ApplicationBean> applicationListOfApproved = (ArrayList<ApplicationBean>)request.getAttribute("applicationListOfApproved");
	System.out.println("applicationListOfApproved:" + applicationListOfApproved);
	ArrayList<ApplicationBean> applicationListOfRejected = (ArrayList<ApplicationBean>)request.getAttribute("applicationListOfRejected");
	System.out.println("applicationListOfRejected:" + applicationListOfRejected);
	
	if (!applicationListOfUnapproved.isEmpty()) {
        System.out.println("ArrayListは空ではないです");
    }else{
    	System.out.println("ArrayListは空です");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給申請承認画面</title>
<link rel="stylesheet" href="css/tabMenu.css">
</head>
<body>
<h1>有給申請承認画面</h1>
<ul class="tab-button">
	<li id="tab-01" class="tab tab-01 is-active" value="0">未承認</li>
	<li id="tab-02" class="tab tab-02" value="1">承認済</li>
	<li id="tab-03" class="tab tab-03" value="2">拒否済</li>
</ul>
<div class="tab-contents">
	<div class="content tab-01 is-display">
		<% if (!applicationListOfUnapproved.isEmpty()) { %>
			<table>
				<tr>
					<th></th>
					<th>氏名</th>
					<th>役職</th>
					<th>所属店舗</th>
					<th>取得希望日(期間)</th>
					<th>休暇種別</th>
					<th>事由</th>
					<th>申請日</th>
					<th>詳細</th>
					<th>処理</th>
				</tr>
				<% for(ApplicationBean item : applicationListOfUnapproved){  %>
					<% 
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
						String formattedDate = sdf.format(item.getDate());
						
						PositionEnum position = PositionEnum.getById(item.getPosition());
						String position2 = position.getLabel();
						System.out.println(position2);
						
						StoreNameEnum storeName = StoreNameEnum.getById(item.getStoreCD());
						String storeName2 = storeName.getLabel();
						
						HolidayStatusEnum holidayStatus = HolidayStatusEnum.getById(item.getHolidayStatus());
						String holidayStatus2 = holidayStatus.getLabel();
					%>
					<tr>
						<td></td>
						<td><%= item.getName() %></td>
						<td><%= position2 %></td>
						<td><%= storeName2 %></td>
						<td><%= item.getStartDate() %>(<%= item.getHolidayDays() %>日間)</td>
						<td><%= holidayStatus2 %></td>
						<td><%= item.getReason() %></td>
						<td><%= formattedDate %></td>
						<td><a href="#">詳細</a></td>
						<td>
							<form action="HolidayStatusApprovedServlet" method="post">
								<input type="hidden" name= "holidayCD" value=<%= item.getHolidayCD() %> />
								<input type="submit" value="承認" />
							</form>
							<form action="HolidayStatusRejectedServlet" method="post">
								<input type="hidden" name= "holidayCD" value=<%= item.getHolidayCD() %> />
								<input type="submit" value="拒否" />
							</form>
						</td>
					</tr>
				<% } %>
			<% }else{ %>
			<p>全ての有給申請の処理が完了しています。</p>
			<% } %>
		</table>
		<div>
		<%-- <p>チェックした項目全ての操作</p>
		<button>承認</button>
		<button>拒否</button> --%>
		</div>
	</div>
	<div class="content tab-02">
		<% if (!applicationListOfApproved.isEmpty()) { %>
			<table>
				<tr>
					<th></th>
					<th>氏名</th>
					<th>役職</th>
					<th>所属店舗</th>
					<th>取得希望日(期間)</th>
					<th>休暇種別</th>
					<th>事由</th>
					<th>申請日</th>
					<th>詳細</th>
					<th></th>
				</tr>
				<%  for(ApplicationBean item : applicationListOfApproved){ %>
				<% 
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					String formattedDate = sdf.format(item.getDate());
					
					PositionEnum position = PositionEnum.getById(item.getPosition());
					String position2 = position.getLabel();
					System.out.println(position2);
					
					StoreNameEnum storeName = StoreNameEnum.getById(item.getStoreCD());
					String storeName2 = storeName.getLabel();
					
					HolidayStatusEnum holidayStatus = HolidayStatusEnum.getById(item.getHolidayStatus());
					String holidayStatus2 = holidayStatus.getLabel();
				%>
					<tr>
						<td></td>
						<td><%= item.getName() %></td>
						<td><%= position2 %></td>
						<td><%= storeName2 %></td>
						<td><%= item.getStartDate() %>(<%= item.getHolidayDays() %>日間)</td>
						<td><%= holidayStatus2 %></td>
						<td><%= item.getReason() %></td>
						<td><%= formattedDate %></td>
						<td><a href="#">詳細</a></td>
						<td></td>
					</tr>
				<% } %>
			<% }else{ %>
				<p>現在までに承認した有給申請はありません。</p>
			<% } %>
		</table>
	</div>
	<div class="content tab-03">
		<% if (!applicationListOfRejected.isEmpty()) { %>
			<table>
				<tr>
					<th></th>
					<th>氏名</th>
					<th>役職</th>
					<th>所属店舗</th>
					<th>取得希望日(期間)</th>
					<th>休暇種別</th>
					<th>事由</th>
					<th>申請日</th>
					<th>詳細</th>
					<th></th>
				</tr>
				<%  for(ApplicationBean item : applicationListOfRejected){ %>
				<% 
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					String formattedDate = sdf.format(item.getDate());
					
					PositionEnum position = PositionEnum.getById(item.getPosition());
					String position2 = position.getLabel();
					System.out.println(position2);
					
					StoreNameEnum storeName = StoreNameEnum.getById(item.getStoreCD());
					String storeName2 = storeName.getLabel();
					
					HolidayStatusEnum holidayStatus = HolidayStatusEnum.getById(item.getHolidayStatus());
					String holidayStatus2 = holidayStatus.getLabel();
				%>
					<tr>
						<td></td>
						<td><%= item.getName() %></td>
						<td><%= position2 %></td>
						<td><%= storeName2 %></td>
						<td><%= item.getStartDate() %>(<%= item.getHolidayDays() %>日間)</td>
						<td><%= holidayStatus2 %></td>
						<td><%= item.getReason() %></td>
						<td><%= formattedDate %></td>
						<td><a href="#">詳細</a></td>
						<td></td>
					</tr>
				<% } %>
			<% }else{ %>
				<p>現在までに拒否した有給申請はありません。</p>
			<% } %>
		</table>
	</div>
</div>
<% session.removeAttribute("sessionApplicationList"); %>
<script type="module" src="js/tabMenu.js" ></script>
<script type="module" src="js/HolidayApproval.js" ></script>
</body>
</html>