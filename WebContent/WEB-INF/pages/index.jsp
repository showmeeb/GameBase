<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
</style>

</head>
<body>
	<jsp:include page="topBar.jsp" />

	<div class="container-fluid text-center">
		<!-- 首頁幻燈片 -->
		<div class="row clearfix ">
			<div class="col-md-12 column">
				<div id="myCarousel" class="carousel slide">
					<!-- 幻燈片指標 -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- 幻燈片項目 -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="https://i.imgur.com/gUY9Qby.jpg" alt="First slide"
								class="carouselImg">
						</div>
						<div class="item">
							<img src="https://i.imgur.com/aTY0qJD.jpg" alt="Second slide"
								class="carouselImg">
						</div>
						<div class="item">
							<img src="https://i.imgur.com/L35gUJO.jpg" alt="Third slide"
								class="carouselImg">
						</div>
					</div>
					<!-- 幻燈片導航 -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
		<hr>

		<div class="row clearfix">
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>
			<div class="col-md-12 column">人氣商品</div>

		</div>

		<hr>

		<div class="row clearfix">
			<div class="col-md-12 column">新聞爬蟲</div>
		</div>
		<hr>
	</div>

	<jsp:include page="footer.jsp" />
	<!-- login and regist pop up windows (with shadow) -->
	<%@ include file="include/loginArea.jsp"%>
	<!-- Start Chat Room Area -->
	<%@ include file="include/chatRoom.jsp"%>
	<!-- End Chat Room Area -->


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