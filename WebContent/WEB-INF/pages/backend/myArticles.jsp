<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
	<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		我的文章

		<div id="bar" class="w-50">
			<form>
				<input type="text" id="se1" placeholder="請輸入關鍵字"> <select
					name="forum">
					<option value="0">依主題搜尋</option>
					<option value="LOL">英雄聯盟</option>
					<option value="WOW">魔獸世界</option>
					<option value="MH">魔物獵人</option>
				</select>
				<button id="search">查詢</button>
			</form>
		</div>
		<div>
			<span id="sp1"></span>
		</div>

		<div id="d1">

			<table id="t1" class="table w-50">


			</table>
			<!-- <input type="button" id="add" value="新增"> -->

		</div>
	</main>
	<script type="text/javascript">
		//    sideBar選項
		$(document).ready(function() {
			$("#member-content").removeClass("d-none").addClass("d-block");
		})

		//按下所有文章顯示列表
		$(document)
				.on(
						"click",
						"#myArticles",
						function() {
							console.log("aaa");
							$
									.ajax({
										type : "POST",
										url : "/GameBase/getMyArticles",
										dataType : "json",
										beforesend : function() {
											$('#t1').html("");
										},
										success : function(response) {
											console.log(response);
											var txt = "<tr><th>文章ID<th>文章標題<th>發文時間<th colspan='2'>";
											for (let i = 0; i < response.articles.length; i++) {

												txt += "<tr><td>"
														+ response.articles[i].titleId;
												txt += "<td>"
														+ response.articles[i].titleName;
												txt += "<td>"
														+ response.articles[i].createTime;
												txt += '<td><input type="button" id="delete" class="d-none" value="刪除">';
											}
											$('#t1').html(txt);
										},
									});
						})
						
						
	</script>
</body>
</html>