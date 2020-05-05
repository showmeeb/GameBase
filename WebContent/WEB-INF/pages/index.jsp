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
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">

.t-title-w{width: 50%}
.t-forum-w{width: 20%}
.t-click-w{width: 20%}
.forumTr{ cursor: pointer; }


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
						<a href="https://www.playstation.com/cht-tw/" target="_blank"><img
							class="carouselImg" src="https://i.imgur.com/w6nSLjE.jpg" alt="">
						</a>
					</div>
					<div class="carousel-item">
						<a href="https://www.nintendo.tw/index.html" target="_blank">
							<img class="carouselImg" src="https://i.imgur.com/0ZA9xul.jpg"
							alt="">
						</a>
					</div>
					<div class="carousel-item">
						<a href="https://www.xbox.com/zh-TW" target="_blank"> <img
							class="carouselImg" src="https://i.imgur.com/K4ON0Pv.png" alt="">
						</a>
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

		<div class="row column ">
		<div class="col-md-1 column"></div>
			<div class="col-md-5 column">
				<h3>熱門商品</h3>
				<table class="resultCenter justify-content-center" id="productHotTable"></table>
			</div>
			<div class="col-md-5 column ">
				<h3>熱門文章</h3>
				<table class="resultCenter table table-hover" id="forumHotTable"></table>
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
					dataType : 'json',
					success : function(jsonResults) {
						var txt = "";
						for (i = 0; i < 3; i++) {
							txt += "<td><a href='/GameBase/tagSearch?looking=forProduct&keyword="
									+ jsonResults[i].productName
									+ "'><img class='resultImg' src='" + jsonResults[i].productImg + "'>";
							txt += "<div>" + jsonResults[i].productName
									+ "</div>";
							txt += "<div>" + jsonResults[i].productPrice
									+ "元 【" + jsonResults[i].searchFreq
									+ "件】</div></a>";
						}
						$("#productHotTable").append(txt);
					}
				});
		
		
		$.ajax({
			url : '<c:url value="/searchArticleClick"/>',
			type : "GET",
			dataType : 'json',
			success : function(resultArticle) {
				
				var ftxt = "";
				
				ftxt += "<thead class='thead-dark'><tr><th>標題</th><th>討論版</th><th>點擊數</th></tr></thead>"
					for (i = 0; i < 6; i++) {
						ftxt += "<tr class='forumTr' id='n"+ resultArticle[i].forumId+"n"+ resultArticle[i].titleId+"'><td class='t-title-w'>"+ resultArticle[i].titleName +"</td><td class='t-forum-w'>"+ resultArticle[i].forumName +"</td><td class='t-click-w'>"+ resultArticle[i].clickNum	+"</td></tr>"
					}
				
				$("#forumHotTable").html(ftxt);
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

		$(document).ready(function() {
			console.log("page ready");
			$.ajax({
				url : "GameBase/getip",
				type : "POST",
				contentType : "application/json",
				success : function(response) {
					console.log(response)
				}
			})
		})
		
		$(document).on('click', '.forumTr', function() {
		let toForum = (this.id).split("n")[1];
		let toTitle = (this.id).split("n")[2];


		window.location = "/GameBase/forum_test/"+toForum+"/"+toTitle;
		})

		
	</script>
</body>
</html>