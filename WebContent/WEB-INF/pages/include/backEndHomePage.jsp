<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
* {
	font-family: 微軟正黑體;
}

body {
	background-color: #EDF7F7;
}

#header {
	background-color: lightgray;
	position: fixed;
}

#logo {
	display: inline-block;
	border-right: 1px solid black;
	padding: 2px 10px;
}

#sidebar {
	position: fixed;
	top: 120px;
	width:220px;	
	height: 100%;
	background-color: #313637;
}

ul {
	list-style-type: none;
}

#back {
	width:200px;
	margin-left: 10px;
}
b{
color:#E3DCC2;
}
#main_back {
	position: relative;
	top: 50px;
	left: 15%;
	width: 75%;

}
</style>

<body>
<jsp:include page="../topBar.jsp"></jsp:include>
	<!-- Sidebar -->
	<nav id="sidebar" class="container py-3 fixed-left">
		<div id="sidebar-header" class="sidebar-header border-bottom ">
			<h2>
				<b id="backEnd">後臺系統</b>
				<b id="memberCenter">會員中心</b>
			</h2>
		</div>
		<table id="analytic-tag" class="border-bottom py-3 d-block"
			cellpadding="10">
			<tr>
				<td><a class="tag" href="analytic">分析</a></td>
			</tr>
		</table>
		<ul id="member-bar" class="nav float-left">
					<li class="nav-item px-0 text-muted">
						<a id="11"class="nav-link" href="#">個人資訊</a>
						<ul id="member-self" class="d-none">
							<li><a id="update-up" href="#">個人資料</a></li>
							<li><a class="tag" href="<c:url value="/goOp"/>">升級</a></li>
							<li><a id="myFriend" class="tag" href="#">好友</a></li>
						</ul>
					</li>

					<li class="nav-item px-0 text-muted">
						<a id="13"class="nav-link" href="#">文章管理</a>
						<ul id="member-article" class="d-none">
							<li><a id="myArticles" class="tag" href="myArticles">我的文章</a></li>
							<li><a class="tag" href="#">刪除</a></li>
							<li><a class="tag" href="#">修改文章</a></li>
						</ul>
					</li>
				</ul>
			<ul id="admin-bar" class="nav float-left ">
					<li class="nav-item px-0 text-muted">
						<a id="21"class="nav-link" href="#">會員管理</a>
						<ul id="admin-member" class="d-none">
							<li><a id="allmembers" class="tag" href="allMembers">會員列表</a></li>
						<!-- 	<li><a id="changeRank" class="tag" href="#">權限</a></li>   -->
						</ul>
					</li>
					<li class="nav-item px-0 text-muted">
						<a id="22"class="nav-link" href="#">商品管理</a>
						<ul id="admin-product" class="d-none">
							<li><a class="tag" href="mainProduct">所有商品</a></li>
							<li><a class="tag" href="#">所有交易</a></li>
							<li><a class="tag" href="tradesystem">新增商品</a></li>
						</ul>
					</li>
					<li class="nav-item px-0 text-muted">
						<a id="23"class="nav-link" href="#">文章管理</a>
						<ul id="admin-article" class="d-none">
							<li><a id="allArticles" class="tag" href="allArticles">所有文章</a></li>
							<li><a id="delArticle" class="tag" href="#">刪除文章</a></li>
						</ul>
					</li>
				</ul>

		<div id="back" class="sidebar-header border-top fixed-bottom py-2">
			<h2><b><a id="11"class="nav-link" href="/GameBase">回到首頁</a></b></h2>
		</div>
	</nav>
	
	<div id="userId" class="d-none">${loginUser.userId}</div>
	<script>	
	var homePage="/GameBase"
	$(document).ready(function(){
		if (window.sessionStorage.getItem("loginUser") == ""){
			 location.href = homePage;
			}
		var loginUser = JSON.parse(window.sessionStorage.getItem("loginUser"));
		var rankId = loginUser.rankId;

			
		$("#openBarBtn").addClass("d-none");
		$(".shoppingCartBtn").addClass("d-none");
		$("#foldBar").addClass("d-none");


		if(rankId==4){
			$("#member-bar").addClass("d-none");
			$("#memberCenter").addClass("d-none");
			}else{
			$("#admin-bar").addClass("d-none");
			$("#backEnd").addClass("d-none");
			$("#analytic-tag").addClass("d-none").removeClass("d-block");
			}
		
		$(".nav-link").click(function() {
			$(this).next().removeClass("d-none").addClass("d-block");
			$(this).parent().siblings().children("ul").removeClass("d-block").addClass("d-none");			
			
		});


		$("#update-up").click(function(){
			var loginUser = JSON.parse(window.sessionStorage.getItem("loginUser"));
			console.log("loginUserID: " + loginUser.userId);
			var userId = loginUser.userId;
				
			$.ajax({
				url:'/GameBase/updateProfile/',
				type:'POST',
				success:function(data){
					window.location.href=data.url;
				}
			})
		})
	})	
//      $("#admin-bar").removeClass("d-none");
//			$("#member-bar").addClass("d-none");
//		}else{
//			$("#member-bar").removeClass("d-none");
//			$("#admin-bar").addClass("d-none");
//			}

		
	</script>
</body>
</html>