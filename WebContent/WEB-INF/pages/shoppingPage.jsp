<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
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

<title>Hello, world!</title>
<style type="text/css">
#du1 img {
	width: 90px
}

#du1 li {
	border: 1px solid black;
	margin: 0px 10px 0px 10px;
}
#st1 img {width:150px}
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
					<select id="se1">
					</select>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div id="du1">
		<ul class="nav justify-content-center">
			<li id="swp" class="nav-item" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/ilWFjYW.png"></li>
			<li id="psp" class="nav-item"  role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/chcSF3h.png"></li>
			<li id="pcp" class="nav-item"  role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/pnzStW7.png"></li>
			<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">Disabled</a></li> -->
		</ul>
	</div>
	<div id="st1">
		<table id="t1"></table>
	</div>

	<script type="text/javascript">
	function showtable(response) {
						var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品類型<th>商品庫存<th>商品價錢<th>商品標籤<th>商品介紹";
						for (let i = 0; i < response.length; i++) {
							txt += "<tr><td>" + response[i].productId;
							txt += "<td id='img'><img src='"+response[i].productImg+"'>";
							txt += "<td>" + response[i].productName;
							txt += "<td>" + response[i].productType;
							txt += "<td>" + response[i].inventory;
							txt += "<td>" + response[i].productPrice;
							txt += "<td>" + response[i].productTag;
							txt += "<td>" + response[i].productInfo;
						}
						$('#t1').html(txt);		
	}
		
		$(document).on('click', '#swp', function() {
			var type = "switch";
			$.ajax({
				url : "shopping/switchProduct",
				datatype : "text",
				type : "POST",
				data : {
					type : type
				},
				success : function(response) {
					console.log(response);
					var response = JSON.parse(JSON.stringify(response))
					console.log(response);
					console.log("yes");
					showtable(response);
				}
			});
		})
		$(document).on('click', '#psp', function() {
			var type = "PS";
			$.ajax({
				url : "shopping/switchProduct",
				datatype : "text",
				type : "POST",
				data : {
					type : type
				},
				success : function(response) {
					console.log(response);
					var response = JSON.parse(JSON.stringify(response))
					console.log(response);
					console.log("yes");
					showtable(response);
				}
			});
		})
		$(document).on('click', '#pcp', function() {
			var type = "pc";
			$.ajax({
				url : "shopping/switchProduct",
				datatype : "text",
				type : "POST",
				data : {
					type : type
				},
				success : function(response) {
					console.log(response);
					var response = JSON.parse(JSON.stringify(response))
					console.log(response);
					console.log("yes");
					showtable(response);
				}
			});
		})
		
		$(document).on('keyup', '#se1', function() {
			var sh = $('#se1').val();
			if (sh != "" && sh != null && sh != " ") {
				$.ajax({
					url : "tradesystem/search",
					datatype : "json",
					type : "GET",
					data : {
						sh : sh
					},
					success : function(response) {
						console.log("yes");
						console.log(response);
						var txt = "";
						$.map(response, function(v, index) {
							txt +="<option>"+v.value;
						});
						$('#se1').html(txt);
					}
				});
			}
		})
	</script>

</body>
</html>