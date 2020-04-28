<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>這是後台</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>

body {
	background-color: #EDF7F7;
}

#Analytics {
	margin: 10% 10% ;
}

.Analytic {
	width: 24%;
	height: 280px;
	float: left;
	border:1px black solid;
}

.num {
	height: 100px;
	border:1px black solid;
}
</style>

</head>
<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
<body>
	<div id="main_back">
		<div id="main" class="container">
			<div id="Analytics mt-2">
				<section id="no-repeat" class="Analytic mt-3">
					<h5>不重複瀏覽量</h5>
					<h1 id="no-repeat-num" class="num">-</h1>
					today
				</section>
				<section id="total" class="Analytic mt-3 ">
					<h5 class="mx-auto ">總瀏覽量</h5>
					<h1 id="total-num" class="num ">-</h1>
					total
				</section>
				<section id="bounce-rate" class="Analytic mt-3">
					<h5>跳出率</h5>
					<h1 id="bounce-rate-num" class="num">-</h1>
					bounce_rate
				</section>
				<section id="new-member" class="Analytic mt-3">
					<h5>新會員</h5>
					<h1 id="new-member-num" class="num">-</h1>
					new_member
				</section>

			</div>
			<div id="charts">
				<canvas id="chart"></canvas>
				charts
			</div>
			main<br>smaimain<br> main<br> main<br> main<br>
			main<br> main<br>main<br> main<br> main<br>
			main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>
			n main
		</div>
	</div>
</body>
</html>