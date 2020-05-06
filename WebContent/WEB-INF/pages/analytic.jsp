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

#Analytics{
	background-color: #FFFFFF;
	border-radius:10px;

}
.Analytic {
	width: 24.7%;
	display:inline-block;
	background-color: #FFFFFF;
	border-radius:10px;
}
.num {

	height: 120px;
	text-align:center;
	line-height:120px;
}

.h5{
	text-align:center;
}

#chartArea{
	background-color: #FFFFFF;
	border-radius:10px;
	margin:2% 0 0 0;
}

#articleArea,#productArea{
	background-color: #FFFFFF;
	display:inline-block;
	width:46%;
	margin:2% 0;
	border-radius:10px;
}

 </style>
</head>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
<body>
	<div id="main_back">
	
		<div id="Analytics" >
			<div id="total" class="h5 Analytic ">
				<h1 id="total-num" class="num ">-</h1>
				<h5>拜訪次數</h5>
				
			</div>
			<div id="no-repeat" class="h5 Analytic">
				<h1 id="no-repeat-num" class="num border-left ">-</h1>
				<h5>不重複訪客</h5>
			
			</div>
			<div id="all-money" class="h5 Analytic">					
				<h1 id="all-money-num" class="num border-left">-</h1>
				<h5>總金額</h5>
				
			</div>
			<div id="new-member" class="h5 Analytic">					
				<h1 id="new-member-num" class="num border-left ">-</h1>
				<h5>新會員</h5>
				
			</div>
		</div>		
		
		<div id="chartArea">	
			<h5 id="chartName" class="name border-bottom p-4">網站瀏覽次數</h5>
			<div id="chart" style="height:400px;"></div>
		</div>	
		
		<div id="articleArea" class="d=inline-block">
			<h5 id="articleName" class="name border-bottom p-4">下面要放文章分析</h5>
		</div>
		
		<div id="productArea" class="d=inline-block float-right">
			<h5 id="productName" class="name border-bottom p-4">下面要放商品分析</h5>
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
					if(response[i].payStatus==1){
					total_num+=num;
					}
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
		        type: 'line',//指定圖表的類型，默認是折線圖（line）
		        backgroundColor:'#FFFFFF',
		        borderRadius:10,
		        renderTo:"chartArea"
			},
		    title: {
		        text: '' // 標題
		    },
		    xAxis: {
		    	categories:response.day,
		    },
		    yAxis: {
		        gridLineColor: '#ffffff',
		        title: {
		            text: '每日流量',    // y 軸標題
			    }
		    },
		    series: [{                //數據列
		        name: '重複次數',          //數據名
				data: response.time
		    },{
		    	name: '不重複人數',          //數據名
				data: response.noTimes
			    }]
		};
		var chart = Highcharts.chart('chart', options);

	}
})

		
		
		   




			
	})//End of ready
</script>
</body>
</html>