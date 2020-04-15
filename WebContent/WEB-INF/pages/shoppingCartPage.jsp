<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<h1>購物車</h1>
	<div id="st1">
		<table id="t1"></table>
	</div>
	<script type="text/javascript">

	function showtables(response) {
		var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品價錢<th>商品數量<th>總金額";
		for (let i = 0; i < response.length; i++) {
			txt += "<tr><td>" + response[i].productId;
			txt += "<td id='img'><img src='"+response[i].productImg+"'>";
			txt += "<td>" + response[i].productName;
			txt += "<td>" + response[i].productPrice;
			txt += "<td>" + response[i].amount;
			txt +=	"<td>" +(response[i].productPrice*response[i].amount)
		}
		$('#t1').html(txt);		
	}
	
		$(document).ready(function(){
			var id = 2;
			$.ajax({
				url:"shopping/showCartProduct",
				dataType : "json",
				type : "POST",
				data:{id:id},
				success:function(response){
					console.log(response);
				showtables(response);
					}
				});

			})
			
			

	</script>
	
</body>
</html>