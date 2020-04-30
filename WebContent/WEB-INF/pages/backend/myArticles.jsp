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

		<div id="bar" class="">
			
				<input type="text" id="sBar" placeholder="請輸入關鍵字">  <select
						id="option" name="forum">
					<option value="0">依主題搜尋</option>
					<option value="1">英雄聯盟</option>
					<option value="2">魔獸世界</option>
					<option value="3">魔物獵人</option>
				</select>
				<input type="button" id="s" value="查詢" />
			
		</div>
		<div>
			<span id="sp1"></span>
		</div>

		<div id="rDiv">
			<table>
				<td>
					<table id="cTable" class="table">

					</table>
				</td>
				<td>
					<table id="rTable" class="table table-hover">

					</table>

				</td>	
			</table>
			<!-- <input type="button" id="add" value="新增"> -->
	        <span id="rMsg"></span>
	        <div>
		  		<input type="button" id="toDel" class="d-none" value="確定刪除">
     		</div>
	      
		</div>
	</main>
	<script type="text/javascript">
		//    sideBar選項
		$(document).ready(function() {
			$("#member-content").removeClass("d-none").addClass("d-block");
		

		//按下所有文章顯示列表
		$(document).on("click","#myArticles",
				function() {
					var userId=$("#userId").text();
					console.log(userId);
					console.log(typeof userId);
					
							$.ajax({
								type : "POST",
								url : "GameBase/getMyArticles",
								dataType : "json",
								data:{
									id:userId
									},
								beforesend : function() {
									$('#rTable').html("");
									$('#cTable').html("");
									},
								success : function(response) {
									console.log("hh");
									var rTable = "<thead><tr><th>文章ID<th>主題<th>文章標題<th>發文時間</thead><tbody>";
									var cTable = "<thead><tr><th></thead><tbody>"
									for (let i = 0; i < response.articles.length; i++) {
									rTable += '<tr class="tr"><td scope="row">'+ (i+1);
									if (response.articles[i].forumId == 1) {
										rTable += "<td>英雄聯盟"
										} else if (response.articles[i].forumId == 2) {
										rTable += "<td>魔獸世界"
										} else if (response.articles[i].forumId == 3) {
										rTable += "<td>魔物獵人"
											}
									rTable += "<td>"+ response.articles[i].titleName;
									rTable += "<td>"+ response.articles[i].createTime;
									rTable += "<td>"+ response.articles[i].titleId;
									rTable += "<td>"+ response.articles[i].forumId;
									cTable += '<tr><td><input type="radio" class="del d-none" name="d" value="a[i].userId">'
										}
									$('#rTable').html(rTable);
									$('#cTable').html(cTable);
									$("#del").addClass("d-none").removeClass("d-block");
									$("#toDel").addClass("d-none").removeClass("d-block");
										},
												});
									})
									
									
				$(document).on("click","#s",function() {
				var forum = $("#option").val();
				var title = $("#sBar").val();
				var userId=$("#userId").text();
				console.log("forum:"+forum+"title:"+title+"userId:"+userId);
					$.ajax({
						type : "POST",
						url : "GameBase/getMemberArticles",
						dataType : "json",
						data : {
							forum : forum,
							title : title,
							id:userId
							},
						beforesend : function() {
							$('#rTable').html("");
							$('#cTable').html("");
							},
						success : function(response) {
							console.log(response);
							var rTable = "<thead><tr><th>文章ID<th>主題<th>文章標題<th>發文時間</thead><tbody>";
							var cTable = "<thead><tr><th></thead><tbody>"
						if (response.articles.length > 0) {
							for (let i = 0; i < response.articles.length; i++) {
							rTable += '<tr class="tr"><td scope="row">'+ (i+1);
							if (response.articles[i].forumId == 1) {
								rTable += "<td>英雄聯盟"
								} else if (response.articles[i].forumId == 2) {
								rTable += "<td>魔獸世界"
								} else if (response.articles[i].forumId == 3) {
								rTable += "<td>魔物獵人"
									}
							rTable += "<td>"+ response.articles[i].titleName;
							rTable += "<td>"+ response.articles[i].createTime;
							rTable += "<td>"+ response.articles[i].titleId;
							rTable += "<td>"+ response.articles[i].forumId;
							cTable += '<tr><td><input type="radio" class="del d-none" name="d" value="a[i].userId">'
								}
						
							$('#rTable').html(rTable);
							$('#cTable').html(cTable);
							$("#del").addClass("d-none").removeClass("d-block");
							$("#toDel").addClass("d-none").removeClass("d-block");
					
							}else{
								$('#rTable').html("");
								$('#cTable').html("");
								$('#rMsg').html("查無結果");
								$("#toDel").addClass(
										"d-none")
										.removeClass(
												"d-block");
									}
						}
						});
					})	
		})//ready結尾		
	</script>
</body>
</html>