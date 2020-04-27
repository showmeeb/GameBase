<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	top: 57px;
	width: 15%;
	height: 100%;
	background-color: #465362;
}

ul {
	list-style-type: none;
}

#back {
	width: 12%;
	margin-left: 10px;
}

#main_back {
	position: relative;
	top: 57px;
	left: 20%;
	width: 75%;
	border: 1px black solid;
}
</style>

<body>
	<header>
		<div class="container-fluid">
			<nav class="navbar fixed-top border-bottom navbar-light bg-white ">
				<a class="navbar-brand border-right px-2 "
					href="http://localhost:8080/GameBase/"> <img
					src="https://i.imgur.com/QnN4T0v.png" class="d-inline align-top"
					alt="">
				</a>
				<h4>你好,${sessionScope.UserData.account}</h4>
				<div class="btn-group fixed-sm-left" role="group"
					aria-label="Basic example">
					<button type="button" id="change-admin" class="btn btn-secondary">管理者</button>
					<button type="button" id="change-member" class="btn btn-secondary">會員</button>
				</div>
				<ul id="member-bar" class="nav float-left">
					<li class="nav-item px-0 text-muted"><a id="11"
						class="nav-link" href="#">個人資訊</a></li>
					<li class="nav-item px-0 text-muted"><a id="12"
						class="nav-link" href="#">交易管理</a></li>
					<li class="nav-item px-0 text-muted"><a id="13"
						class="nav-link" href="#">文章管理</a></li>
				</ul>
				<ul id="admin-bar" class="nav float-left ">
					<li class="nav-item px-0 text-muted"><a id="21"
						class="nav-link" href="allMembers">會員管理</a></li>
					<li class="nav-item px-0 text-muted"><a id="22"
						class="nav-link" href="#">商品管理</a></li>
					<li class="nav-item px-0 text-muted"><a id="23"
						class="nav-link" href="#">文章管理</a></li>
					<li class="nav-item px-0 text-muted"><a id="24"
						class="nav-link" href="#">新聞管理</a></li>
					<li class="nav-item px-0 text-muted"><a id="25"
						class="nav-link" href="#">廣告管理</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- Sidebar -->
	<nav id="sidebar" class="container py-3 fixed-left">
		<div id="sidebar-header" class="sidebar-header border-bottom ">
			<h2>
				<b>後臺系統</b>
			</h2>
		</div>
		<table id="analytic-tag" class="border-bottom py-3 d-block"
			cellpadding="10">
			<tr>
				<td><a class="tag" href="analytic">分析</a></td>
			</tr>
		</table>
		<table id="admin-mamber" class="d-none" cellpadding="10">
			<tr>
				<td><a id="allmembers" class="tag" href="#">所有會員</a></td>
			</tr>
			<tr>
				<td><a id="delmember" class="tag" href="#">刪除</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">修改權限</a></td>
			</tr>
		</table>
		<table id="admin-product" class="d-none" cellpadding="10">
			<tr>
				<td><a class="tag" href="mainProduct">所有商品</a></td>
				<!-- mainProduct -->
			</tr>
			<tr>
				<td><a class="tag" href="#">所有交易</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="tradesystem">新增商品</a></td>
				<!-- tradesystem -->
			</tr>

		</table>
		<table id="admin-content" class="d-none" cellpadding="10">
			<tr>
				<td><a class="tag" href="#">所有文章</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">刪除文章</a></td>
			</tr>

		</table>
		<table id="admin-news" class="d-none" cellpadding="10">
			<tr>
				<td><a class="tag" href="#">新聞總覽</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">刪除新聞</a></td>
			</tr>
		</table>
		<table id="admin-ads" class="d-none" cellpadding="10">
			<tr>
				<td><a class="tag" href="#">廣告總覽</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">刪除廣告</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">新增廣告</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">修改廣告</a></td>
			</tr>
		</table>
		<table id="member-profile" class="d-none" cellpadding="10">
			<tr>
				<td><a id="myProfile" class="tag" href="#">個人資料</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">升級</a></td>
			</tr>
			<tr>
				<td><a id="myFriend" class="tag" href="#">好友</a></td>
			</tr>
		</table>
		<table id="member-trade" class="d-none" cellpadding="10">
			<tr>
				<td><a id="myOrders" class="tag" href="#">交易紀錄</a></td>
			</tr>
		</table>
		<table id="member-content" class="d-none" cellpadding="10">
			<tr>
				<td><a id="myArticles" class="tag" href="#">我的文章</a></td>
			</tr>
			<tr>
				<td><a id="myContext" class="tag" href="myContexts">我的回應</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">刪除文章</a></td>
			</tr>
			<tr>
				<td><a class="tag" href="#">修改文章</a></td>
			</tr>
		</table>
		<div id="back" class="sidebar-header border-top fixed-bottom py-2">
			<h2>回到首頁</h2>
		</div>
	</nav>


	<script>
	$(document).ready(function(){
		console.log("a");	
		
		})
		
		$(".nav-link").click(function() {
			if ($(this).attr("id") == "21") {
				console.log("!");
				$("#admin-mamber").removeClass("d-none").addClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "22") {
				console.log("!");
				$("#admin-product").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "23") {
				console.log("!");
				$("#admin-content").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "24") {
				console.log("!");
				$("#admin-news").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "25") {
				console.log("!");
				$("#admin-ads").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "11") {
				console.log("!");
				$("#member-profile").removeClass("d-none").addClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "12") {
				console.log("!");
				$("#member-trade").removeClass("d-none").addClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "13") {
				console.log("!");
				$("#member-content").removeClass("d-none").addClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
		});

		$(".tag").click(function() {
			console.log("@@");
		});

		$(".btn-secondary").click(function() {
			if ($(this).attr("id") == "change-admin") {
				$("#admin-bar").removeClass("d-none");
				$("#member-bar").addClass("d-none");
			}
			;
			if ($(this).attr("id") == "change-member") {
				$("#member-bar").removeClass("d-none");
				$("#admin-bar").addClass("d-none");
			}
			;
		})


//判斷進入後台的腳色 
//$("#admin-bar").removeClass("d-none");
//			$("#member-bar").addClass("d-none");
//		}else{
//			$("#member-bar").removeClass("d-none");
//			$("#admin-bar").addClass("d-none");
//			}

		

	</script>
</body>
</html>