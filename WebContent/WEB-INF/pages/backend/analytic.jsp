<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>這是後台</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<style>
* {
	font-family: 微軟正黑體;
}

#main {
	width: 80%;
	


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
<body>
<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
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
					<h5>銷售額</h5>
					<h1 id="bounce-rate-num" class="num">-</h1>
					
Sales
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
			main<br>s mainmaimain<br> main<br> main<br> main<br>
			main<br> main<br>main<br> main<br> main<br>
			main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>
			n main
		</div>
	</div>
</body>
</html>