<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
<style type="text/css">
.data-area {
	overflow: auto;
}

#auth-data-area button {
	margin-top: 20px;
}

.auth-fa-user {
	width: 32%;
	display: inline-block;
}

.auth-fa-user>i {
	display: block;
	width: 100%;
	text-align: center;
	font-size: 80px;
	color: black;
}

.auth-fa-user>div {
	font-size: 35px;
	border: 2px solid skyblue;
	border-radius: 10px;
	color: black;
}

#auth-data-area button {
	margin-top: 20px;
}

.auth-content-area {
	visibility: hidden;
}
</style>
<title>Insert title here</title>
</head>
<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
<body>
	<div id="main_back">
		<div class="data-area" id="auth-data-area">
			<div class="auth-fa-user">
				<i class="fa fa-user"></i>
				<div>一般會員</div>
				<ul>
					<li>會員個人資料修改</li>
					<li>文章區留言、分享</li>
					<li>購物商城商品購買</li>
					<li>聊天室</li>
					<li>網頁瀏覽</li>
				</ul>
				<button style="visibility: hidden">申請</button>
			</div>
			<div class="auth-fa-user">
				<i class="fas fa-user-tie"></i>
				<div>白金會員</div>
				<ul>
					<li>新增文章區發文</li>
					<li>新增會員文章修改</li>
					<li class="auth-content-area">xxx</li>
					<li class="auth-content-area">xxx</li>
					<li><b>升級費用：500 元</b></li>
				</ul>
				<button id="auth-pm-upgrade-btn">申請</button>
			</div>
			<div class="auth-fa-user hidden">
				<i class="fas fa-user-secret"></i>
				<div>鑽石會員</div>
				<ul>
					<li>新增購物商城刊登商品</li>
					<li>新增會員商品管理</li>
					<li class="auth-content-area">xxx</li>
					<li class="auth-content-area">xxx</li>
					<li><b>升級費用：2000 元</b></li>
				</ul>
				<button id="auth-dm-upgrade-btn">申請</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
			$("#member-profile").removeClass("d-none").addClass("d-block");
			})

	</script>
</body>
</html>