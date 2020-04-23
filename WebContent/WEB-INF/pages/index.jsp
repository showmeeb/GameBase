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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
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
</head>
<body>
	<jsp:include page="topBar.jsp" />

	<div class="container-fluid text-center">
		<!-- 首頁幻燈片 -->
		<div class="row clearfix ">
			<div class="col-md-12 column">
				<div id="myCarousel" class="carousel slide">
					<!-- 幻燈片指標 -->
					<ul class="carousel-indicators">
						<li data-target="#demo" data-slide-to="0" class="active"></li>
						<li data-target="#demo" data-slide-to="1"></li>
						<li data-target="#demo" data-slide-to="2"></li>
					</ul>
					<!-- 幻燈片項目 -->
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="https://i.imgur.com/gUY9Qby.jpg" class="carouselImg">
						</div>
						<div class="carousel-item">
							<img src="https://i.imgur.com/aTY0qJD.jpg" class="carouselImg">
						</div>
						<div class="carousel-item">
							<img src="https://i.imgur.com/L35gUJO.jpg" class="carouselImg">
						</div>
					</div>
					<!-- 幻燈片導航 -->
					<a class="carousel-control-prev" href="#demo" data-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</a> <a class="carousel-control-next" href="#demo" data-slide="next">
						<span class="carousel-control-next-icon"></span>
					</a>
				</div>
			</div>
		</div>
		<hr>

		<div class="row clearfix">
			<div class="col-md-12 column">人氣商品</div>
		</div>
		<hr>

		<div class="row clearfix">
			<div class="col-md-12 column">新聞爬蟲</div>
		</div>
		<hr>
	</div>

	<jsp:include page="footer.jsp" />



	<script type="text/javascript">
		//改變幻燈片停留時間
		$('.carousel').carousel({
			interval : 3000
		})

		/*
		$(window).scroll(function(){
		var navH = $("#topBar").offset().top;
		var scroH = $(this).scrollTop();
		if(scroH>=navH){
		    $("#topBar").addClass("topBarFixed")
		    $("#topBar").removeClass("topBar")

		}else{
		    $("#topBar").addClass("topBar")
		    $("#topBar").removeClass("topBarFixed")
		    console.log("ff");	}
		})
		 */
	</script>
</body>
</html>