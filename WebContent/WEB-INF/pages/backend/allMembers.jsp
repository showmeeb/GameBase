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
		<div id="bar">
			<input type="text" id="sBar" placeholder="請輸入帳號搜尋"> <select
				id="option" name="op">
				<option value="0">選項</option>
				<option value="2">一般會員</option>
				<option value="3">高級會員</option>
				<option value="4">管理員</option>
			</select>
			<button id="s">查詢</button>
		</div>
		<div id="rDiv">
			
				<table id="rTable" class="table table-hover">


				</table>
				<!-- <input type="button" id="add" value="新增"> -->
		<span id="rMsg"></span>
			<div>
				<input type="button" id="toDel" class="d-none" value="確定刪除">
			</div>
		</div>
		<!-- 彈跳視窗 -->
		<button id="mal-btn" class="btn btn-primary btn-lg d-none"
			data-toggle="modal" data-target="#myModal">开始演示模态框</button>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">帳號放這</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					</div>
					<div class="modal-body">
						<div id="pDiv" class="container-fluid">
							<div class="prfile">
								<h5 class="border-bottom">會員資訊</h5>
								<img alt="帳號" src="https://i.imgur.com/wZBlO1x.jpg"
									class="w-25 ml.auto">
								<div id="pName" class="d-block float-right"></div>
							</div>
							<div class="article clearfix">
								<h5 class="border-bottom">近期文章</h5>
								<table id="pArticle"></table>
							</div>
							<div class="context clearfix">
								<h5 class="border-bottom">近期回應</h5>
								<table id="pArticle"></table>
							</div>
							<!-- <input type="button" id="add" value="新增"> -->
							<span id="pMsg"></span>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">關閉</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</main>
	<script type="text/javascript">
		//載入畫面--顯示sidebar選項
		$(document).ready(function() {
			$("#admin-mamber").removeClass("d-none").addClass("d-block");
		})

		//載入畫面--顯示所有會員列表
		$(document)
				.on(
						"click",
						"#allmembers",
						function() {
							$
									.ajax({
										url : "/GameBase/getAllMembers",
										dataType : "json",
										type : "POST",
										success : function(response) {

											var a = response.members

											var txt = " <thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

											for (let i = 0; i < response.members.length; i++) {
												txt += '<tr class="tr"><td scope="row"><input type="radio" class="del d-none" name="d" value="a[i].userId">';
												txt += "<td>" + a[i].userId;
												txt += "<td>" + a[i].account;
												txt += "<td>" + a[i].email;
												if (a[i].rankId == 2) {
													txt += "<td>" + "一般會員"
												} else if (a[i].rankId == 3) {
													txt += "<td>" + "高級會員"
												} else if (a[i].rankId == 4) {
													txt += "<td>" + "管理員"
												}
												;
											}
											txt += "</tbody>";
											$('#rMsg').html("");
											$('#rTable').html(txt);
											$("#del").addClass("d-none")
													.removeClass("d-block");
											$("#toDel").addClass("d-none")
													.removeClass("d-block");
										}
									});

						})

		//刪除會員
		$(document).on("click", "#delmember", function() {

			if ($("#rMsg").text() == "") {
				$(".del").removeClass("d-none").addClass("d-block");
				$("#toDel").removeClass("d-none").addClass("d-block");
			} else {
			}

		})

		//搜尋會員
		$("#s")
				.click(
						function() {

							var rank = $("#option").val();
							var ac = $("#sBar").val();

							if (rank == 0) {
								console.log(rank);
								$
										.ajax({
											url : "GameBase/getuserbyacinallrank",
											datatype : "json",
											type : "POST",
											data : {
												ac : $("#sBar").val()
											},
											success : function(response) {
												console.log("aaa");
												var a = response.members

												var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

												for (let i = 0; i < response.members.length; i++) {
													txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
													txt += "<td>" + a[i].userId;
													txt += "<td>"
															+ a[i].account;
													txt += "<td>" + a[i].email;
													if (a[i].rankId == 2) {
														txt += "<td>" + "一般會員"
													} else if (a[i].rankId == 3) {
														txt += "<td>" + "高級會員"
													} else if (a[i].rankId == 4) {
														txt += "<td>" + "管理員"
													}
													;
												}
												txt += "</tbody>";
												$('#rTable').html(txt);
												$('#rMsg').html("");
												$("#del").addClass("d-none")
														.removeClass("d-block");
												$("#toDel").addClass("d-none")
														.removeClass("d-block");
											}

										})
							} else if (rank != 0) {
								console.log(rank);
								$
										.ajax({

											url : "GameBase/getuserbyacinonerank",
											datatype : "json",
											type : "POST",
											data : {
												rank : $("#option").val(),
												ac : $("#sBar").val()
											},
											success : function(response) {
												console.log("qqq");
												var a = response.members

												if (response.members.length > 0) {
													var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

													for (let i = 0; i < response.members.length; i++) {
														txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
														txt += "<td>"
																+ a[i].userId;
														txt += "<td>"
																+ a[i].account;
														txt += "<td>"
																+ a[i].email;
														if (a[i].rankId == 2) {
															txt += "<td>"
																	+ "一般會員"
														} else if (a[i].rankId == 3) {
															txt += "<td>"
																	+ "高級會員"
														} else if (a[i].rankId == 4) {
															txt += "<td>"
																	+ "管理員"
														}
														;

													}
													txt += "</tbody>";
													$('#rMsg').html("");
													$('#rTable').html(txt);
													$("#del")
															.addClass("d-none")
															.removeClass(
																	"d-block");
													$("#toDel").addClass(
															"d-none")
															.removeClass(
																	"d-block");
												} else {
													$('#rTable').html("");
													$('#rMsg').html("查無結果");
													$("#toDel").addClass(
															"d-none")
															.removeClass(
																	"d-block");
												}
											}

										})
							}
						})
		//點擊會員，彈跳會員資料						
		$(document).on("click", ".tr", function() {
			console.log("a" + $(this).children().eq(1).text());
			var id = $(this).children().eq(1).text();
			$.ajax({
				url : "GameBase/previewUserProfile",
				datatype : "json",
				type : "POST",
				data : {
					id : id
				},
				success : function(response) {
					console.log(response);

					if (response.profile!=null) {
						console.log("has profile");
						$("#pName").html("名字 :" + response.profile.name);

					} else {
						console.log("no profile");
						$("#pName").html("尚未新增個人資訊");
					}
				}
			})
			//讓動態視窗顯示
			$("#mal-btn").click();
		})
	</script>

</body>
</html>