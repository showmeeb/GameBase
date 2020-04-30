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

<style type="text/css">

</style>

</head>

<body>
	<jsp:include page="topBar.jsp" />

<hr>
	<div class="container-fluid text-center">
		<!-- 首頁幻燈片 -->
		<div class="container-fluid">
			<div class="carousel slide" data-ride="carousel" id="carousel-demo">
				<ol class="carousel-indicators">
					<li data-target="#carousel-demo" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-demo" data-slide-to="1"></li>
					<li data-target="#carousel-demo" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="carouselImg" src="https://i.imgur.com/gUY9Qby.jpg"
							alt="">
					</div>
					<div class="carousel-item">
						<img class="carouselImg" src="https://i.imgur.com/aTY0qJD.jpg"
							alt="">
					</div>
					<div class="carousel-item">
						<img class="carouselImg" src="https://i.imgur.com/L35gUJO.jpg"
							alt="">
					</div>

					<a href="#carousel-demo" class="carousel-control-prev"
						data-slide="prev"> <span class="carousel-control-prev-icon"></span>
					</a> <a href="#carousel-demo" class="carousel-control-next"
						data-slide="next"> <span class="carousel-control-next-icon"></span>
					</a>
				</div>
			</div>
		</div>
		<hr>

		<div class="row column">
			<div class="col-md-12 column">
			<h3>熱門商品</h3>
					<table class="resultCenter" id="resultsTable"></table>
			</div>
		</div>
		<hr>

		<div class="row clearfix">
			<div class="col-md-12 column">新聞爬蟲</div>
		</div>
		<hr>
	</div>
	<div id="snackbar"></div>

	<jsp:include page="footer.jsp" />



	<script type="text/javascript">
		//改變幻燈片停留時間
		$('.carousel').carousel({
			interval : 3000
		})

		$.ajax({
			url : '<c:url value="/searchFreq"/>',
			type : "GET",
	        dataType:'json',
			success : function(jsonResults) {

		        var txt = "";
		        for (i = 0; i < 5; i++) {
		            if (i % 5 == 0) { txt += "<tr>" }
		            txt += "<td><a href='/GameBase/tagSearch?looking=forProduct&keyword="+jsonResults[i].productName+"'><img class='resultImg' src='" + jsonResults[i].productImg + "'>";  
		            txt += "<div>" + jsonResults[i].productName + "</div>";
		            txt += "<div>" + jsonResults[i].productPrice + "</div></a>";
		        }
		        $("#resultsTable").append(txt);
			}
		});

		
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