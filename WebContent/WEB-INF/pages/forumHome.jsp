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
	<!-- 所有討論版 -->
	<div class="container">
		<div class="row forumBGC">
			<div class="col-md-12 column ">

				<div class="row clearfix forumBorder">
					<div class="col-md-6 column">
						<img src="https://i.imgur.com/srTJQB4.jpg" class="forumImg">
					</div>
					<div class="col-md-3 column align-self-center">
						<h3>
							<a href="#">魔物獵人：冰原</a>
						</h3>
					</div>
					<div class="col-md-3 column">這邊放熱門文章</div>
				</div>

				<div class="row clearfix forumBorder">
					<div class="col-md-6 column">
						<img src="https://i.imgur.com/HBmbtTS.jpg" class="forumImg">
					</div>
					<div class="col-md-3 column align-self-center">
						<h3>
							<a href="#">惡靈古堡3</a>
						</h3>
					</div>
					<div class="col-md-3 column">這邊放熱門文章</div>
				</div>

				<div class="row clearfix forumBorder">
					<div class="col-md-6 column">
						<img src="https://i.imgur.com/tipbIrM.jpg" class="forumImg">
					</div>
					<div class="col-md-3 column align-self-center">
						<h3>
							<a href="#">動物森友會</a>
						</h3>
					</div>
					<div class="col-md-3 column">這邊放熱門文章</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>