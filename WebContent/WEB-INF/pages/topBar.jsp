<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameBase遊戲基地</title>

<!-- Bootstrap CSS CDN-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Bootstrap JavaScript CDN-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<style>
.logo {
	width: 50px;
}

.topBarBGC {
	background-color: #e3f2fd;
}

.shot {
	border-radius: 50%;
}

body {
	background-color: wheat;
}

.indexImg {
	width: 100%;
}

.forumBGC {
	background-color: oldlace;
	margin: 5px;
}

.forumImg {
	width: 100%;
	margin: 5px;
}

.forumBorder {
	margin-top: 5px;
	border: 1px solid gray;
}

#resultsDiv {
	width: auto;
	display: table;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid black;
}

td {
	text-align: center;
}

.resultImg {
	width: 200px;
	height: 260px;
}

.mallImg {
	width: 100%;
}
</style>
</head>

<body>
	<nav class="navbar navbar-light topBarBGC">

		<!--LOGO-->
		<div class="col-md-2 column text-center">
			<a class="navbar-brand" href="<c:url value="/homePage"/>"> <img
				src="https://i.imgur.com/2pn8W06.png" width="80" height="80"
				class="d-inline-block align-top">
			</a>
		</div>

		<!--搜尋列-->
		<div class="col-md-6 column">
			<form action="<c:url value="/tagSearch"/>" method="get"
				class="form-inline">
				<select name="looking">
					<option value="forProduct">找商品</option>
					<option value="foForumr">找論壇</option>
				</select> <input class="form-control mr-sm-2 " type="search" placeholder="搜尋"
					aria-label="Search" name="keyword" value="${keyword}">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>

		<!--商城下拉選單-->
		<div class="col-md-1 column">
			<button class="btn btn-primary dropdown-toggle" type="button"
				id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">商城</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<a class="dropdown-item" href="#">登記商品</a> <a class="dropdown-item"
					href="#">商品管理</a> <a class="dropdown-item" href="<c:url value="/mallHome"/>">商城頁面</a> <a
					class="dropdown-item" href="#">圖片上傳</a>
			</div>
		</div>

		<!--討論區連結-->
		<div class="col-md-1 column">
			<a href="<c:url value="/forumHome"/>">
				<button class="btn btn-primary" type="submit">討論區</button>
			</a>
		</div>

		<div class="col-md-2 column">
			<div class="dropdown">

				<!-- 這邊放使用者大頭貼-->
				<span> <a href="#"><img
						src="https://i.imgur.com/K0RinSw.jpg" width="40" height="40"
						class="shot"></a>
				</span>


				<button class="btn btn-warning dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true">
					使用者名稱
					<!--使用動態產生-->
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="<c:url value="/gotologin"/>">登入</a>
					<a class="dropdown-item" href="#">個人資訊</a> <a class="dropdown-item"
						href="#">購物車</a> <a class="dropdown-item" href="#">文章管理</a> <a
						class="dropdown-item" href="<c:url value="/logout"/>">登出</a> <a
						class="dropdown-item" href="<c:url value="/gotoregister"/>">註冊</a>

				</div>
			</div>
		</div>

	</nav>

</body>
</html>