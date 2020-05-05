<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>這是後台</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>	
<!-- 
<link href="<c:url value="/css/analytic.css"/>" rel="stylesheet">
<script src="<c:url value="/js/analytic.js"/>"></script>
 -->
 <style type="text/css">
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
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
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
				<section id="all-money" class="Analytic mt-3">
					<h5>售出總金額</h5>
					<h1 id="all-money-num" class="num">-</h1>
					bounce_rate
				</section>
				<section id="new-member" class="Analytic mt-3">
					<h5>新會員</h5>
					<h1 id="new-member-num" class="num">-</h1>
					new_member
				</section>

			</div>
			<div id="chart-container" class="container">
				<div id="chart" style="width:95%; height:400px;"></div>
			</div>
			
			
			main<br>main<br> main<br> main<br> main<br>
			main<br> main<br>main<br> main<br> main<br>
			main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>main<br>
			n main
		</div>
	</div>
<script type="text/javascript">



$(document).ready(function(){

	//第一.二格數字
	$.ajax({
		url : "GameBase/getIpWeek",
		dataType : "json",
		type : "POST",
		success : function(response) {
		
			var no_repeat=response.norepeat.length;
			var total_num=0;
			
			$("#no-repeat-num").html(no_repeat);
			for(var i=0;i<response.totalnum.length;i++){
				var num=response.totalnum[i].logtime ;
				total_num+=num;
				}
			if(total_num!=0){
				$("#total-num").html(total_num);
				}
			}

		})

		$.ajax({
			url : "orderPage/getMoneyWeek",
			dataType : "json",
			type : "POST",
			success : function(response) {
				var total_num=0;
				for(var i=0;i<response.length;i++){
					var num=response[i].orderPrice ;
					total_num+=num;
					}
				if(total_num!=0){
				$("#all-money-num").html(total_num);
				}
					}
			})

		$.ajax({
			url : "GameBase/getNewMemberWeek",
			dataType : "json",
			type : "POST",
			success : function(response) {
			var member_num=0;
//			console.log(response)
//				console.log("newuser"+response.users)
			total_num=response.users.length;
			if(total_num!=0){
				$("#new-member-num").html(total_num);
				}
			}
		})
//折線圖			
$.ajax({
	url:"GameBase/getIpDay",
	dataType : "json",
	type : "POST",
	success : function(response) {
		// 圖表配置
		var options = {
		    chart: {
		        type: 'line'      //指定圖表的類型，默認是折線圖（line）
		    },
		    title: {
		        text: '總瀏覽量' // 標題
		    },
		    xAxis: {
		    	categories:response.day
		    },
		    yAxis: {
		        title: {
		            text: '每日流量'    // y 軸標題
		        }
		    },
		    series: [{                //數據列
		        name: '總人數',          //數據名
				data: response.time
		    }]
		};
		var chart = Highcharts.chart('chart', options);

	}
})

		
		
		   




			
	})//End of ready
</script>
</body>
</html>