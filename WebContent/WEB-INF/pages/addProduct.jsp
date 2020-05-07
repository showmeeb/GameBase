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
<script src="<c:url value="/js/addProduct.js"/>"></script>
</head>
<style type="text/css">
#dh{display: none}

</style>
</head>
<body>
    <jsp:include page="include/backEndHomePage.jsp"></jsp:include>
<main id="main_back">
<!-- 切換註冊商品<button id="gameMode" value="game">遊戲</button> -->
<h1>新增商品</h1>
<div id ="dg">

	<form id="form1">
		<div>
			圖片<input type="file" id="pImg" name="productImg">
		</div>
		<div>
			影片網址<input type="text" id="pVideo" name="productVideo">
		</div>
		<div>
			商品名稱<input type="text" id="pName" name="productName">
		</div>
		<div>
			商品類型<input type="text" id="pType" name="productType" value="game" readonly>
		</div>
		<div>
			庫存<input type="text" id="pInventory" name="inventory">
		</div>
		<div>
			商品價錢<input type="text" id="pPrice" name="productPrice">
		</div>
		<div>
			商品標籤<input type="text" id="pTag" name="productTag">
		</div>
		<div>
			遊戲介紹<input type="text" id="pInfo" name="productInfo">
		</div>
<!-- 		<div> -->
<!-- 			熱銷度<input type="text" id="pInfo" name="searchFreq"> -->
<!-- 		</div> -->
		<div>
			遊戲類型 <select id="gType" name="gameType">
						<option>角色扮演</option>
						<option>射擊</option>
						<option>策略</option>
						<option>愛情</option>
						<option>羞羞臉的遊戲</option>
				  </select>
		</div>
		<div>
			遊戲平台<input type="text" id="gPlatform" name="gamePlatform">
		</div>
		<div>
			遊戲分級<input type="text" id="gLevel" name="gameLevel">
		</div>
		<div>
			<input type="button" id="s1" value="送出">
		</div>
	</form>
</div>
<div id ="dh">
	<form id="form2">
		<div>
			圖像<input type="file" id="pImg" name="img">
		</div>
		<div>
			商品名稱<input type="text" id="pName" name="productName">
		</div>
		<div>
			商品類型<input type="text" id="pType" name="productType" value="host">
		</div>
		<div>
			商品數量<input type="text" id="pInventory" name="inventory">
		</div>
		<div>
			商品價錢<input type="text" id="pPrice" name="productPrice">
		</div>
		
		<div>
			商品介紹<input type="text" id="pInfo" name="productInfo">
		</div>
		
		<div>
			<input type="button" id="s1" value="送出">
		</div>
	</form>
</div>
</main>
	
</body>
</html>