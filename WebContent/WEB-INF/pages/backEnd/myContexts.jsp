<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<input type="text" id="sBar" placeholder="請輸入標題搜尋"> <select
				id="option" name="op">
				<option value="0">選項</option>
				<option value="lol">英雄聯盟</option>
				<option value="wow">魔獸世界</option>
				<option value="wh">模物獵人</option>
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
			$("#member-content").removeClass("d-none").addClass("d-block");
		})

		//載入畫面--顯示所有會員列表
		$(document).on("click", "#myContext", function() {
			console.log("jjj");
			var id=1;
			$.ajax({
				url : "GameBase/getMyContent",
				dataType : "json",
				type : "POST",
				data : {
					id : id
				//id:${UserData.userId}
				},
				success : function(response) {
					console.log("ddd");
					console.log(response);
				}
			})

		})

		//刪除會員
		$(document).on("click", "#delmember", function() {

			if ($("#rMsg").text() == "") {
				$(".del").removeClass("d-none").addClass("d-block");
				$("#toDel").removeClass("d-none").addClass("d-block");
			} else {
			}

		})

		
		
		
	</script>

</body>
</html>