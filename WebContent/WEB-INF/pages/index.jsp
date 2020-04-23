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