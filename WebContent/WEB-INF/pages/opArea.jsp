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
<!-- <link -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
<link href="<c:url value="/css/opArea.css"/>" rel="stylesheet">

<title>Insert title here</title>
</head>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
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
				<button class="buttonL" style="visibility: hidden">申請</button>
			</div>
			<div class="auth-fa-user">
				<i class="fas fa-user-tie"></i>
				<div>高級會員</div>
				<ul>
					<li>新增修改討論區主題</li>
					<li>新增修改討論區文章</li>
					<li>商城購物八折</li>
					<br/>
					<li><b>升級費用：500 元</b></li>
				</ul>
				<button class="buttonL" id="auth-pm-upgrade-btn">申請</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#member-profile").removeClass("d-none").addClass("d-block");

		$("#auth-pm-upgrade-btn").click(function(){			
			var userId=$("#userId").text();
			console.log(userId)
			
			$.ajax({
				type : "POST",
				url : "GameBase/MemberUpgradeRank",
				dataType : "text",
				data:{
					userId:userId
				},
				success : function(response) {
						console.log(response);
						document.write(response);
					
				}
			})
		})	
	})

	</script>
</body>
</html>