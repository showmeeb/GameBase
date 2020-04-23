<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<title>GameBase</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<!-- Bootstrap css-->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- jQuery UI library -->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
	integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
	crossorigin="anonymous"></script>
<!-- javaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Bootstrap js -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- WebSocket library -->
<script src="https://d1fxtkz8shb9d2.cloudfront.net/sockjs-0.3.4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- mustache Template Engine library -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/3.0.1/mustache.min.js"></script>
<!-- google login -->
<script src="https://apis.google.com/js/api:client.js"></script>
<!-- chatRoom js -->
<script src="<c:url value="/js/chatRoom.js"/>"></script>
<!-- main style -->
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">

</head>
<body>
	<%
		String account = "";
		String password = "";

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if ("account".equals(name)) {
					account = cookie.getValue();
				} else if ("password".equals(name)) {
					password = cookie.getValue();
				}
			}
		}
	%>



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
					<a class="dropdown-item" href="<c:url value="/shoppingPage"/>">商城頁面</a>

					<a class="dropdown-item" href="<c:url value="/test"/>">圖片上傳</a> <a
						class="dropdown-item" href="#"></a> <a class="dropdown-item"
						href="#"></a>

				</div></li>
			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">會員系統</a>
				<div class="dropdown-menu">
					<!-- Ajax -->
					<a class="dropdown-item" id="login-str" href="#">登入</a> <a
						class="dropdown-item" id="regiest-str" href="#">註冊</a> <a
						class="dropdown-item hidden-window" id="logout-str" href="#">登出</a>
					<!-- original -->
					<a class="dropdown-item">--This line down is Original--</a>
					<c:if test="${empty UserData.userId}">
						<a class="dropdown-item" href="<c:url value="/gotologin"/>">登入</a>
					</c:if>
					<c:if test="${empty UserData.userId}">
						<a class="dropdown-item" href="<c:url value="/gotoregister"/>">註冊</a>
					</c:if>
					<c:if test="${!empty UserData.userId}">
						<c:if test="${!empty ProfileId}">
							<a class="dropdown-item"
								href="<c:url value="/updateProfile/${UserData.userId}"/>">修改個人資料</a>
						</c:if>
						<c:if test="${empty ProfileId}">
							<a class="dropdown-item"
								href="<c:url value="/createProfile/${UserData.userId}"/>">新增個人資料</a>
						</c:if>
					</c:if>
					<c:if test="${!empty UserData.userId}">

						<a class="dropdown-item" href="<c:url value="/logout"/>">登出</a>
					</c:if>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/forum_test"/>">討論區</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/topBar"/>">TopBar</a></li>
		</ul>
	</nav>
	<br>
	<!-- login and regist pop up windows (with shadow) -->
	<%@ include file="include/loginArea.jsp"%>
	<!-- Start Chat Room Area -->
	<%@ include file="include/chatRoom.jsp"%>
	<!-- End Chat Room Area -->
</body>
</html>