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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
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
<!-- jquery.cookie js -->
<script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<!-- Font Awesome icons -->
<script src="https://kit.fontawesome.com/83bb506b46.js" crossorigin="anonymous"></script>

<!-- editor improt -->
<script	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<!-- ckfinder import -->
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
	<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- create_article.js import -->
<script src="<c:url value="/js/create_article.js"/>"></script>
<script src="<c:url value="/js/content.js"/>"></script>

<!-- main style -->

<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
<link href="<c:url value="/css/topBar.css"/>" rel="stylesheet">

<style>
    .foldableBar {
      background-color: grey;
      border-radius: 25px;
      margin-left: 35%;
      position: absolute;
      opacity: 1;
    }

    .foldableBarHide {
      margin-left: 90%;
      position: absolute;
      opacity: 0;
      display: none;
    }

    .showBar {
      animation: show 1s 1;
      animation-fill-mode: forwards;
      background-color: grey;
      border-radius: 25px;
    }

    @keyframes show {
      from {
        margin-left: 83%;
        position: absolute;
        opacity: 0;
      }

      to {
        margin-left: 35%;
        position: absolute;
        opacity: 1;
      }
    }

    .hideBar {
      animation: hide 1s 1;
      animation-fill-mode: forwards;
      background-color: grey;

    }

    @keyframes hide {
      from {
        margin-left: 35%;
        position: absolute;
        opacity: 1;

      }

      to {
        margin-left: 90%;
        position: absolute;
        opacity: 0;
        display: none;
      }
    }

    .maskBar {
      background-color: black;
      position: absolute;
      width: 16.5%;
      height: 50px;
      margin-left: 83%;
      z-index: 5;
    }

    .z-index10 {
      z-index: 10;
    }

    .foldBtn {
      position: absolute;
      margin-left: -5%;
      background-color: white;
      border-radius: 25px 0 0 25px;
    }

    .openBtn {
      position: absolute;
      margin-left: 83%;
      background-color: white;
      border-radius: 0 25px 25px 0;
      z-index: 12;
    }

</style>
</head>

<body>
	<nav class="navbar navbar-light topBarFixed " id="topBar">

		<!--LOGO-->
		<div class="col-md-3 column ">
			<a class="navbar-brand" href="<c:url value="/"/>"> <img
				src="https://i.imgur.com/7oJSy01.png" width="350"
				class="d-inline-block align-top">
			</a>
		</div>

		<!-- 遮罩片 -->
		<div class="maskBar"></div>

		<!-- 開啟摺疊按鈕 -->
		<button class="btn openBtn" id="openBarBtn">=</button>

		<!-- 可折疊列 -->
		<div class="col-md-6 row foldableBar " id="foldBar">
			<button class="btn foldBtn" id="foldBarBtn">>></button>			
			<!--搜尋列-->
			<div class="col-md-6 column ">
				<form action="<c:url value="/tagSearch"/>" method="get"
					class="form-inline">
					<select id="lookingFor" name="looking">
						<option value="forProduct">找商品</option>
						<option value="foForumr">找文章</option>
					</select> <input class="form-control" type="search" placeholder="搜尋"
						aria-label="Search" name="keyword" value="${keyword}"
						id="searchInput" style="width: 200px">
					<button class="btn btn-outline-light search-submit-btn"
						type="submit">Search</button>
				</form>
			</div>
			<!--討論區連結-->
			<div class="col-md-5 column ">
				<a href="<c:url value="/forum_test"/>">
					<button class="btn btn-primary topBarBtn " type="submit">討論區</button>
				</a> <a class=""
					href="<c:url value="/tagSearch?looking=forProduct&keyword=ps4" />">
					<button class="btn btn-primary topBarBtn" type="submit">商城</button>
				</a> <a href="<c:url value="/orderPage"/>">
					<button class="btn btn-primary topBarBtn " type="submit">訂單查詢</button>
				</a> <a href="<c:url value="/shoppingCartPage"/>">
					<button class="btn btn-primary topBarBtn " type="submit">購物車</button>
				</a>
			</div>
		</div>	
		<!-- 會員系統 -->
		<div class="col-md-2 column">
			<div >
				<!-- 這邊放使用者大頭貼-->
				<c:choose>
					<c:when test="${empty sessionScope.loginUser }">
					<!-- before log in --> 
					<div class="login-btn"><i class="fa fa-user" ></i><span>Login</span></div>
					<!-- after log in -->
					<div class="loggedin-icon disable"><img src="<c:url value="/img/userIcon.png"/>" class="shot disable" alt="user icon" /></div>
					</c:when>
					<c:otherwise>
						<!-- before log in --> 						
						<div class="login-btn disable"><i class="fa fa-user" ></i><span>Login</span></div>
						<!-- after log in -->
						<c:choose>
							<c:when test="${empty sessionScope.loginUser.img}">
								<div class="loggedin-icon"><img src="<c:url value="/img/userIcon.png"/>" class="shot disable" alt="user icon" /></div>
							</c:when>
							<c:otherwise>
								<div class="loggedin-icon"><img src="${sessionScope.loginUser.img}" class="shot disable" alt="user icon"/></div>
							</c:otherwise>
							</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="loggedin-list">
		    <div class="loggedin-list-item" id="user-center-btn">會員中心</div>
		    <div class="loggedin-list-item hidden-window" id="admin-broadcast">管理員廣播</div>
		    <div class="loggedin-list-item" id="logout-str">登出</div>
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
      url: '<c:url value="/autoComplete"/>',
      type: "POST",
      success: function (data) {
        window.sessionStorage.setItem("myData", JSON.stringify(data));
        $(".search-input").autocomplete({
          source: data
        });
      }
    });

    $("select#lookingFor").change(
      function () {
        var autoCompleteData = JSON.parse(window.sessionStorage
          .getItem("myData"));
        if ($(this).val() != "forProduct") {
          $(".search-input").autocomplete({
            source: ""
          });
        } else {
          $(".search-input").autocomplete({
            source: autoCompleteData
          })
        }

      });

    if ("${looking}" == "foForumr") {
      $("#lookingFor").val("foForumr")
    }


    //以下為foldBar狀態判斷
    if ($.cookie("foldableBarState") == null) {
      $.cookie("foldableBarState", "1")
    }

    $("#foldBarBtn").click(function () {
      $("#foldBar").removeClass("showBar foldableBar foldableBarHide");
      $("#foldBar").addClass("hideBar");

      $.cookie("foldableBarState", "0");
    })


    $("#openBarBtn").click(function () {
      if ($.cookie("foldableBarState") == "0") {
        $.cookie("foldableBarState", "1");
      } else { $.cookie("foldableBarState", "0"); }

      if ($("#foldBar").hasClass("showBar") || $("#foldBar").hasClass("foldableBar")) {
        $("#foldBar").removeClass("showBar foldableBar foldableBarHide");
        $("#foldBar").addClass("hideBar");

      } else {
        $("#foldBar").addClass("showBar");
        $("#foldBar").removeClass("hideBar foldableBar foldableBarHide");

      }

    })

    if (parseInt($.cookie("foldableBarState"))) {
      $("#foldBar").addClass("foldableBar");
      $("#foldBar").removeClass("foldableBarHide");

    } else {
      $("#foldBar").addClass("foldableBarHide");
      $("#foldBar").removeClass("foldableBar");
    }
	</script>

</body>
</html>