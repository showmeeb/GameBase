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
	width:94%;
	margin:0 auto ;

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
	width:94%;
	margin:2% auto ;
}

#productArea,#memberArea{
	background-color: #FFFFFF;
	display:inline-block;
	width:30%;
	margin:2% 0;
	border-radius:10px;
}


 </style>
</head>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
<body>
<div id="main_back">
		<h2>分析</h2>
		<h4>網站各種類數據</h4>
		<br>
		<br>
		<br>
	
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
	<div class="row justify-content-around align-items-center" >	
<!-- 		<div id="articleArea" class="d=inline-block float-left"> -->
<!-- 			<h5 id="articleName" class="name border-bottom p-4">下面要放文章分析</h5> -->
<!-- 			<ul class="list-group list-group-flush"> -->
<!--   				 <li id="totalArticle" class="list-group-item">總文章量 : </li> -->
<!--  				 <li id="avgArticle" class="list-group-item">每日平均發文量 : </li> -->
<!--  				 <li id="avgContent" class="list-group-item">每日平均回應數 :</li> -->
<!--  				 <li id="avgContentRate" class="list-group-item">平均回應率 :</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
		
		<div id="memberArea" class="d=inline-block ">
			<h5 id="memberName" class="name border-bottom p-4">會員分析</h5>
			<ul class="list-group list-group-flush">
  				 <li id="totalMember" class="list-group-item">會員人數 : </li>
 				 <li id="MemPosted" class="list-group-item">發文人數 : </li>
 				 <li id="payByMember" class="list-group-item">購買人數 : </li>
 				 <li id="payByguest" class="list-group-item">非會員購買人數 : </li>
			</ul>
		</div>
		
		<div id="productArea" class="d=inline-blockt float-right">
			<h5 id="productName" class="name border-bottom p-4">商品分析</h5>
			<div id="productChart" style="height:200px"></div>
		</div>
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
		var Chart = {
		    chart: {
		        type: 'line',//指定圖表的類型，默認是折線圖（line）
		        backgroundColor:'#FFFFFF',
		        borderRadius:10,
		        renderTo:"productArea"
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
		var chart = Highcharts.chart('chart', Chart);

	}
})
 
var totalMember=$("#totalMember").text();
var postMember=$("#postMember").text();
var payByMember=$("#payByMember").text();
var payByguest=$("#payByguest").text();
var MemPosted=$("#MemPosted").text();

	$.ajax({
		url:"GameBase/analizeData",
		dataType : "json",
		type : "POST",
		success : function(response){
			console.log(response);

			totalMember+=response.members;
			MemPosted+=response.MemPosted;
			payByMember+=response.MemOr;
			payByguest+=response.notMemOr;
	
			$("#totalMember").text(totalMember);
			$("#MemPosted").text(MemPosted);
			$("#payByMember").text(payByMember);
			$("#payByguest").text(payByguest);	

			// 圓餅圖
			   var chart = {
				       plotBackgroundColor: null,
				       plotBorderWidth: null,
				       plotShadow: false
				   };
				   var title = {
				      text: ''   
				   };      
				   var tooltip = {
				      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				   };
				   var plotOptions = {
				      pie: {
				         allowPointSelect: true,
				         cursor: 'pointer',
				         dataLabels: {
				            enabled: true,
				            format: '<b>{point.name}數量</b>: {point.y}',
				            style: {
				               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				            }
				         }
				      }
				   };
				   var series= [{
				      type: 'pie',
				      name: '商品比例',
				      data: [
				         ['xbox',   response.NumOfXbox],
				         ['PS4',      response.getNumOfPs4],
				         ['SWITCH',    response.NumOfSwitch]    
				 ]
				   }];     
				      
				   var json = {};   
				   json.chart = chart; 
				   json.title = title;     
				   json.tooltip = tooltip;  
				   json.series = series;
				   json.plotOptions = plotOptions;
				   var productChart = Highcharts.chart('productChart', json);

				
				
			} 
	
		})	
		
		   




			
	})//End of ready
</script>
</body>
</html>