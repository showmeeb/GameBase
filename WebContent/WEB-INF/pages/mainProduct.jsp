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
<script src="<c:url value="/js/mainProduct.js"/>"></script>
<style>

</style>
</head>
<body>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<div id="bar">
			<input type="text" id="se1" placeholder="請輸入想搜尋的商品">
			<button id="search">查詢</button>
			<input type="button" id="query" value="所有商品">
		</div>
		<div>
			<span id="sp1"></span>
		</div>

		<div id="d1">
			<form id="f1">
				<table id="t1">
				<thead><tr><th>商品ID</th><th>商品影片</th><th>商品照片</th><th>商品名稱</th><th>商品類型</th><th>商品庫存</th><th>商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品熱銷度</th><th colspan='2'>設定</th></tr></thead>


				</table>
				<!-- <input type="button" id="add" value="新增"> -->
			</form>
		</div>
	</main>

</body>
</html>