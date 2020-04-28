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
<style type="text/css">
#d1 {
	
}
</style>
</head>

<body>
	<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<div id="table" class="w-50">
			<div id="bar">
				<form>
					<input type="text" id="sBar" placeholder="請輸入關鍵字"> <select
						id="option" name="forum">
						<option value="0">依主題搜尋</option>
						<option value="1">英雄聯盟</option>
						<option value="2">魔獸世界</option>
						<option value="3">魔物獵人</option>
					</select>
<!-- 					<button id="s">查詢</button> -->
					<input type="button" id="s" value="查詢"/>
				</form>
			</div>

			<div id="rDiv" class="float-left">
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
		</div>
		<div id="page" class="float-right">

		</div>
	</main>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#admin-content").removeClass("d-none").addClass("d-block");

			$(document).on(
					"click",
					"#s",
					function() {

						var forum = $("#option").val();
						var title = $("#sBar").val();

						$.ajax({
									type : "POST",
									url : "GameBase/getSomeArticles",
									dataType : "json",
									data : {
										forum : forum,
										title : title
									},
									success : function(response) {
												console.log("hh");
																var txt = "<thead><tr><th><th>文章ID<th>文章標題<th>發文時間<th colspan='2'></thead><tbody>";
										for (let i = 0; i < response.articles.length; i++) {
											txt += '<tr class="tr"><td scope="row"><input type="radio" class="del d-none" name="d" value="a[i].userId">';
											txt += "<td>"
													+ response.articles[i].titleId;
											txt += "<td>"
													+ response.articles[i].titleName;
											txt += "<td>"
													+ response.articles[i].createTime;

										}
										txt += "</tbody>";
										$('#rTable').html(txt);
										$("#del").addClass("d-none")
													.removeClass("d-block");
											$("#toDel").addClass("d-none")
													.removeClass("d-block");
										}
								});
		})

		$(document)
				.on(
						"click",
						"#allArticles",
						function() {
							;
							$
									.ajax({
										type : "POST",
										url : "/GameBase/getAllArticles",
										dataType : "json",
										beforesend : function() {
											$('#rTable').html("");
											$('#cTable').html("");
										},
										success : function(response) {
											console.log("hhh2");
											console.log(response);
											var rTable = "<thead><tr><th>文章ID<th>文章標題<th>發文時間</thead><tbody>";
											var cTable = "<thead><tr><th></thead><tbody>"
											for (let i = 0; i < response.articles.length; i++) {

												rTable += '<tr class="tr"><td scope="row">'
														+ response.articles[i].titleId;
												rTable += "<td>"
														+ response.articles[i].titleName;
												rTable += "<td>"
														+ response.articles[i].createTime;
												cTable += '<tr><td><input type="radio" class="del d-none" name="d" value="a[i].userId">'

											}
											$('#rTable').html(rTable);
											$('#cTable').html(cTable);
											$("#del").addClass("d-none")
													.removeClass("d-block");
											$("#toDel").addClass("d-none")
													.removeClass("d-block");
										},
									});
						})

		
		});

		$(document).on("click", "#delArticle", function() {

			if ($("#rMsg").text() == "") {
				$(".del").removeClass("d-none").addClass("d-block");
				$("#toDel").removeClass("d-none").addClass("d-block");
			} else {
			}

		})
	</script>
</body>
</html>