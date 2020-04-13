<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameBase</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- javaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Bootstrap -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- WebSocket library -->
<script src="https://d1fxtkz8shb9d2.cloudfront.net/sockjs-0.3.4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- mustache Template Engine library -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/3.0.1/mustache.min.js"></script>
<!-- main js -->
<script src="<c:url value="/js/main.js"/>"></script>
<!-- main style -->
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
</head>
<body>
<h1>${UserData.account}</h1>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="#">GameBase</a>

		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">商城</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<c:url value="/tradesystem"/>">登記商品</a>
					<a class="dropdown-item" href="<c:url value="/mainProduct"/>">商品管理</a>
					<a class="dropdown-item" href="#"></a>
				</div></li>
			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">會員系統</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<c:url value="/gologin"/>">登入</a> <a
						class="dropdown-item" href="<c:url value="/goregister"/>">註冊</a> <a
						class="dropdown-item" href="#">管理個人資料</a>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/forum"/>">討論區</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/topBar"/>">TopBar</a></li>
		</ul>
	</nav>
	<br>
	<!-- login and regist pop up windows (with shadow) -->
   	<%@ include file="include/loginArea.jsp" %>
	<!-- Start Chat Room Area -->
	<%@ include file="include/chatRoom.jsp"%>
	<!-- End Chat Room Area -->
</body>
</html>