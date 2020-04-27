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
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- javaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Bootstrap js -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- WebSocket library -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
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
<link href="<c:url value="/css/topBar.css"/>" rel="stylesheet">

<style>
</style>

</head>

<body>
	<nav class="navbar navbar-light topBarFixed " id="topBar">

		<!--LOGO-->
		<div class="col-md-4 column ">
			<a class="navbar-brand" href="<c:url value="/"/>"> <img
				src="https://i.imgur.com/7oJSy01.png" width="400"
				class="d-inline-block align-top">
			</a>
		</div>

		<!--搜尋列-->
		<div class="col-md-4 column ">
			<form action="<c:url value="/tagSearch"/>" method="get"
				class="form-inline">
				<select id="lookingFor" name="looking">
					<option value="forProduct">找商品</option>
					<option value="foForumr">找文章</option>
				</select> <input class="form-control search-input" type="search"
					placeholder="搜尋" aria-label="Search" name="keyword"
					value="${keyword}" id="searchInput">
				<button class="btn btn-outline-light search-submit-btn topBarBtn"
					type="submit">Search</button>
			</form>
		</div>

		<!--商城下拉選單-->
		<div class="col-md-1 column">
			<button class="btn btn-primary dropdown-toggle topBarBtn"
				type="button" id="dropdownMenuButton" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="true">商城</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<a class="dropdown-item" href="<c:url value="/mallHome"/>">商城首頁(子敬)</a>
				<a class="dropdown-item" href="<c:url value="/tradesystem"/>">登記商品</a>
				<a class="dropdown-item" href="<c:url value="/mainProduct"/>">商品管理</a>
				<a class="dropdown-item" href="<c:url value="/shoppingPage"/>">商城頁面</a>
				<a class="dropdown-item" href="<c:url value="/test"/>">圖片上傳</a>

			</div>
		</div>

		<!--討論區連結-->
		<div class="col-md-1 column">
			<a href="<c:url value="/forum_test"/>">
				<button class="btn btn-primary topBarBtn" type="submit">討論區</button>
			</a>
		</div>

		<div class="col-md-2 column">
			<div class="dropdown">

				<!-- 這邊放使用者大頭貼-->
				<span> <a href="#"><img
						src="https://i.imgur.com/ke6wdHI.jpg" width="40" height="40"
						class="shot disable"></a>
				</span>


				<button class="btn btn-warning dropdown-toggle userNameBtn"
					type="button" id="dropdownMenuButton" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true">
					使用者名稱
					<!--使用動態產生-->
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<!-- Ajax -->
					<a class="dropdown-item" id="login-str" href="#">登入</a> <a
						class="dropdown-item" id="regiest-str" href="#">註冊</a> <a
						class="dropdown-item hidden-window" id="logout-str" href="#">登出</a>

					<!-- original -->

					<a class="dropdown-item">--This line down is
						Original--${UserData.account}</a> <a class="dropdown-item" href="#"
						id="update-up">管理個人資料</a>



				</div>
			</div>
		</div>

	</nav>


	<!-- login and regist pop up windows (with shadow) -->
	<%@ include file="include/loginArea.jsp"%>
	<!-- Start Chat Room Area -->
	<%@ include file="include/chatRoom.jsp"%>
	<!-- End Chat Room Area -->

	<script>
		//關鍵字自動完成
		$.ajax({
			url : '<c:url value="/autoComplete"/>',
			type : "POST",
			success : function(data) {
				window.sessionStorage.setItem("myData", JSON.stringify(data));
				$(".search-input").autocomplete({
					source : data
				});
			}
		});

		$("select#lookingFor").change(function() {
			var autoCompleteData = JSON.parse(window.sessionStorage.getItem("myData"));
			if ($(this).val() != "forProduct") {
				$(".search-input").autocomplete({
					source : ""
				});
			} else {
				$(".search-input").autocomplete({
					source : autoCompleteData
				})
			}

		});

		$("#update-up").click(
				function() {
					var loginUser = JSON.parse(window.sessionStorage
							.getItem("loginUser"));
					console.log("loginUserID: " + loginUser.userId);
					var userId = loginUser.userId;

					$.ajax({
						url : '/GameBase/updateProfile/' + userId,
						type : 'POST',
						success : function(data) {
							window.location.href = data.url;
						}
					})
				})
	</script>

</body>
</html>