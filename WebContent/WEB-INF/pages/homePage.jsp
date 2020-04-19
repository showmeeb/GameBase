<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



</head>
<body>
	<jsp:include page="topBar.jsp" />



	<div class="container-fluid text-center">
		
		<hr> <!-- 首頁大圖 -->
		<div class="row clearfix ">
			<div class="col-md-12 column">
				<img src="https://i.imgur.com/R3OR56P.jpg" class="indexImg">
			</div>
		</div>
		<hr>
		
		<div class="row clearfix">
			<div class="col-md-12 column">人氣商品</div>
		</div>

		
		<hr>
		
		<div class="row clearfix">
			<div class="col-md-12 column">新聞爬蟲</div>
		</div>
		<hr>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>