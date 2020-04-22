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
<script type="text/javascript">

function showOrder(response){
	for(let i=0;i < response.length; i++){
	var txt = "<tr>";
	txt += "<th scope='row'>"+i+1+"</th>";
	txt += "<td>"+response[i].orderId;
	txt += "<td>"+response[i].uuId;
	txt += "<td>"+response[i].orderName;
	txt += "<td>"+response[i].orderPhone;
	txt += "<td>"+response[i].orderAddress;
	txt += "<td>"+response[i].orderPrice;
	txt += "<td>"+response[i].orderDate;
	txt += "<td>"+response[i].payStatus;
	
	}
	$('#tb1').append(txt);
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
				showOrder(response);
			}
			});
		})


</script>

</body>
</html>