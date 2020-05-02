<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="<c:url value="/js/allMembers.js"/>"></script>
</head>

<body>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
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
	
</body>
</html>