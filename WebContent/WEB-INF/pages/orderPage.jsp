<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
img{width: 50px }
</style>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
<style type="text/css">
#du1 img {
	width: 70px
}


#total{color:red}
</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input id="se1" class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span id="order" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/MflYSUa.jpg"></span>
			<span id="shopcart" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/fzG8Ocj.png"></span>
		</div>
	</nav>
	
	<div id="du1">
		<ul class="nav justify-content-center">
			<li id="pay" class="nav-item" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/3LdR5vh.jpg"></li>
			<li id="history" class="nav-item"  role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/rQOseOf.png"></li>
			<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">Disabled</a></li> -->
		</ul>
	</div>
<h1>訂單紀錄</h1>


	<table class="table table-hover">
  <thead class="thead-dark">
    <tr>
      <th scope="col">No.</th>
      <th scope="col">orderId</th>
      <th scope="col">uuId</th>
	  <th scope="col">orderName</th>
	  <th scope="col">orderPhone</th>
	  <th scope="col">orderAddress</th>
	  <th scope="col">orderPrice</th>
	  <th scope="col">orderDate</th>
	  <th scope="col">payStatus</th>
    </tr>
  </thead>
  <tbody id="tb1">

  </tbody>
</table>

<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div id="payform" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">訂單明細</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">商品ID</th>
					      <th scope="col">商品名稱</th>
					      <th scope="col">商品價錢</th>
					      <th scope="col">商品數量</th>
					      <th scope="col">總價</th>
					    </tr>
					  </thead>
					  <tbody id="tb2">
					   
					  </tbody>
					  <tfoot >
					  	<tr>
					  		<th scope="row"></th>
							      <td></td>
							      <td></td>
							      <td></td>
							      <td></td>
							      <td id="total"></td>

					  	</tr>
					  </tfoot>
					</table>
				</div>
				<div class="modal-footer">
					
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

//[{ a: 1},{ b: 2},{ c: 3}].reduce(function(result, item) {
//	  var key = Object.keys(item)[0]; //first property: a, b, c
//	  result[key] = item[key];
//	  return result;
//	}, {});

function showOrder(response){
	
	for(let i=0;i < response.length; i++){
		var array =[];
		
		let j = 1;
		while (Array.isArray(response[i][j])) {
			
		var b=response[i][j].reduce(function(result, item) {
			  var key = Object.keys(item)[0]; //first property: a, b, c
			  result[key] = item[key];
			  return result;
			});
		console.log("123");
		console.log(b);
		array.push(b);
		  j++;
		}
		console.log(array);
	var arrayS =JSON.stringify(array);
		console.log(arrayS);
		
	var txt = "<tr id='orderDetail' data-toggle='modal' data-target='#exampleModalCenter'>";
	txt += "<th scope='row'>"+i+1+"</th>";
	txt += "<td><span id='orderDetail1' style='display:none'>"+arrayS;
	txt += "<td>"+response[i].orderId;
	txt += "<td>"+response[i].uuId;
	txt += "<td>"+response[i].orderName;
	txt += "<td>"+response[i].orderPhone;
	txt += "<td>"+response[i].orderAddress;
	txt += "<td>"+response[i].orderPrice;
	txt += "<td>"+response[i].orderDate;
	txt += "<td>"+response[i].payStatus;
	$('#tb1').append(txt);
	}
	
}

function showOrderDetaile(response){
	var txt="";
	var total=0;
	for (let i=0;i<response.length; i++){
	txt += "<tr>";
	txt += "<th scope='row'>"+(i+1)+"</th>";
	txt += "<td>"+response[i].productId;
	txt += "<td>"+response[i].productName;
	txt += "<td>"+response[i].productPrice;
	txt += "<td>"+response[i].amount;
	txt += "<td id='money'>"+(response[i].amount*response[i].productPrice);
	total += response[i].amount*response[i].productPrice
	}
	$('#tb2').html(txt);
	$('#total').html(total);
}
	$(document).ready(function(){
		var id = 0;
		$.ajax({
			url:"orderPage/showOrder",
			dataType:"json",
			data:{id:id},
			type:"POST",
			success:function(response){
				console.log(response);
				console.log(response[0][1]);
				console.log(response[0][2]);
				showOrder(response);
			}
			});
		})
		
	$(document).on('click','#orderDetail',function(){
			console.log("asd");
			var a=$(this).find("span").text();
			var b=JSON.parse(a);
			//var a=$tr.find("span").text();
			console.log(b);
			showOrderDetaile(b);


		
		})


</script>

</body>
</html>