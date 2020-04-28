<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>

<style>
img {
	width: 50px
}
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
	width: 60px
}

#du1 li {
	margin: 0px 20px 0px 20px;
}



#d4 img {width:300px;height:450px;}

div {
	border: 1px solid grey ;
}

iframe {
	width:400px;height:330px;
}

#youtubeX{
			width:400px;height:330px;

			position: absolute;
			top: 0px;
  			right: 0px;

}
#t1{
	border:1px solid green;
    width:600px;
}
#dt1{
		width:600px;
		margin:auto;
		right:250px;
		border:1px solid red;

}

#dt1 img{
		width:150px;
		

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
			<span id="order" role="button" tabindex="0" aria-pressed="true"><img
				src="https://i.imgur.com/MflYSUa.jpg"></span> <span id="shopcart"
				role="button" tabindex="0" aria-pressed="true"><img
				src="https://i.imgur.com/fzG8Ocj.png"></span>
		</div>
	</nav>
	

	<div id="du1" style="background-color:#D0D0D0">
		<ul class="nav justify-content-center">
			<li id="swp" class="nav-item" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/ilWFjYW.png"></li>
			<li id="psp" class="nav-item" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/chcSF3h.png"></li>
			<li id="pcp" class="nav-item" role="button" tabindex="0"
				aria-pressed="true"><img src="https://i.imgur.com/pnzStW7.png"></li>
			<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">Disabled</a></li> -->
		</ul>
	</div>
	
	<!-- <nav  class="navbar navbar-expand-lg fixed-bottom navbar-light bg-light" style="height: 90px">
	<div class="container-fluid" style="position:absolute;height:90px">
		<a class="navbar-brand" href="#"></a>
	  	<div style="position:relative; margin-right:100px;">
  		<form class="form-inline "> 
    		<button id="" style="width:200px;" class="btn btn-outline-success btn-lg"  type="button" data-toggle="modal"
		data-target="#exampleModalCenter">結&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帳</button>
	
  		</form>
  		</div>
  	</div>
	</nav> -->
	
	<!--  測試中 <div id="st1">
		<table id="t1">
			<div style="width:1500px; height:550px">
				<ul style="display: flex; justify-content: flex-start;">
					<li><div style="width: 28rem; display: flex; justify-content: flex-start; padding: 3px">
							<div Style="width: 19rem; float:left; border:1px solid red"><img src="https://i.imgur.com/MFhKz0M.jpg?1" ></div>
							
							<div style="float: right">
								<h5 class="card-title">薩俺達</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
									<p class="card-text">1899</p>
							</div>
						</div>
					</li>
					<li><div style="width: 28rem; display: flex; justify-content: flex-start; padding: 3px">
							<div Style="width: 19rem; float:left; border:1px solid red"><img src="https://i.imgur.com/MFhKz0M.jpg?1" ></div>
							
							<div style="float: right">
								<h5 class="card-title">薩俺達</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
									<p class="card-text">1899</p>
							</div>
						</div>
					</li>
					<li><div style="width: 28rem; display: flex; justify-content: flex-start; padding: 3px">
							<div Style="width: 19rem; float:left; border:1px solid red"><img src="https://i.imgur.com/MFhKz0M.jpg?1" ></div>
							
							<div style="float: right">
								<h5 class="card-title">薩俺達</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
									<p class="card-text">1899</p>
							</div>
						</div>
					</li>
					<li><div style="width: 28rem; display: flex; justify-content: flex-start; padding: 3px">
							<div Style="width: 19rem; float:left; border:1px solid red"><img src="https://i.imgur.com/MFhKz0M.jpg?1" ></div>
							
							<div style="float: right">
								<h5 class="card-title">薩俺達</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
									<p class="card-text">1899</p>
							</div>
						</div>
					</li>
				</ul>
			</div>

		</table>
	</div>-->
	
						
	
	<div id="dt1">
		<form >
			<table id="t1">
			</table>		
		</form>
	</div>
	
	
	<div id="d1" >
		<div id="d2">
			<div id="d3" style="width: 800px ;height: 600px;display: flex; justify-content: flex-start; padding: 3px;margin:auto;">
				<div id="d4" >
					
				</div>
			</div>
		</div>
	</div>

	<nav id="nav-div" style="bottom:0; position:fixed;width:1550px;height:90px; padding:0">
	<div id="nav-div1" class="container-fluid" style="height:90px;margin: 0">
		
	  	<div style="position:fixed;bottom:10px;right:80px">
  		<span style="color: red;">總金額&nbsp;&nbsp;:&nbsp;&nbsp;</span><span id="total"style="color: red;"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    		<button id="paybillF" style="width:200px;" class="btn btn-outline-success btn-lg"  type="button" data-toggle="modal"
		data-target="#exampleModalCenter">結&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帳</button>
  		</div>
  	</div>
	</nav>


	<script type="text/javascript">
	var u = window.sessionStorage.getItem("loginUser");


	window.onload = function(){
		console.log(window.sessionStorage.getItem("loginUser"));
		user.checkUser();
		user.getRankId();
		user.getUserId();
		console.log(user.userId);
		console.log(user.rankId);
		
		}

		var user ={
				userId:999,
				rankId:999,
				checkUser: function (){
					if(u==""){
						alert("尚未登入會員");
					}
					},

				getUserId:function () {
							if(u==""){
								user.userId = 888;
							}
							user.userId = JSON.parse(u).userId;
							return user.userId;
							},

				getRankId:function () {
					if(u==""){
						user.rankId = 888;
					}
					user.rankId = JSON.parse(u).rankId;
					return user.rankId;
					}
		}
				
		$(document).on('change', '#quantity_input', function() {
			if($("#quantity_input").val()<1){
				$("#quantity_input").val(1);
				}
			var num=$("#quantity_input").val();
			var price = $('#oriPrice').text();
			
			$("#tprice").html(price*num);
			
		})		
				
		$(document).on('click', '#plus', function() {
			$("#quantity_input").val(parseInt($("#quantity_input").val()) + 1)
			var num=$("#quantity_input").val();
			var price = $('#oriPrice').text();
			
			$("#tprice").html(price*num);
			
		})
		
		$(document).on('click', '#noplus', function() {
			if ($("#quantity_input").val() > 1) {
                $("#quantity_input").val(parseInt($("#quantity_input").val()) - 1)
            }
			var num=$("#quantity_input").val();
			var price = $('#oriPrice').text();
			
			$("#tprice").html(price*num);
			
		})
	
	function showprodetail(response){
		var txt="";
			txt+="<div ><button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>X</span></button></div>"
			//txt+="<div style='width: 750px; display: flex; justify-content: flex-start; padding: 3px'>";
			txt+="<div style='width:350px;height:450px; float:left;margin:30px 0px 10px 0px; text-align: center;'>";
			txt+="<img id='youtube'role='button' tabindex='0' aria-pressed='true' alt='"+JSON.stringify(response.productVideo)+"' src='"+response.productImg+"'></div>";
			txt+="<div style='float: right;width:400px;height:450px;margin:6px 0px 10px 0px;' ><div class='modal-header'><h5 class='modal-title'>"+response.productName+"</h5></div>";
			txt+="<div id='mbody' style='height:330px;'class='modal-body'>";
			//txt+="<div id='youtube1' ><iframe src='"+url+"'></iframe></div>";
			txt+="<p class='card-text'>"+response.productInfo+"</p>";
			txt+="<p class='card-text'>"+response.productTag+"</p>";
			txt+="</div>";
			txt+="<div style='margin-bottom:0px;align:left;' class='modal-footer'>";
			txt+="<span>數量:<button id='noplus' type='button' class='btn btn-outline-secondary btn-sm'>-</button>";
			txt+="<input style='width: 40px;' id='quantity_input' type='text' value='1'>";
			txt+="<button id='plus' type='button' class='btn btn-outline-secondary btn-sm'>+</button></span>";
			txt+="金額:<span id='oriPrice' style='display:none'>"+response.productPrice+"</span>"+"<p id='tprice' class='card-text'>"+response.productPrice+"</p></div>";
			txt+="</div>";
			txt+="<div style='width:760px;'class='modal-footer'><span id='itemdetail' style='display:none'>"+JSON.stringify(response)+"</span>"+"<button id='addProduct1' type='button' class='btn btn-primary' data-dismiss='modal'>加入購物車</button></div>";
			return txt;
		}
		
	function showtable(response) {
		
						var txt = "<tr><th>#<th>商品照片<th>商品名稱";
						
						for (let i = 0; i < response.length; i++) {
							var s = {productId:response[i].productId,
									productVideo:response[i].productVideo,
									 productName:response[i].productName,
									 	productImg:response[i].productImg,
										 productType:response[i].productType,
											 inventory:response[i].inventory,
												 productPrice:response[i].productPrice,
													 productTag:response[i].productTag,
														 productInfo:response[i].productInfo,
														 	amount:1
									};
							//var a=JSON.stringify(s);
							//console.log("a:"+a);
							txt += "<tr><td>" + response[i].productId;
							txt += "<td id='img'><span id='productDetail' role='button' tabindex='0'aria-pressed='true' data-toggle='modal'data-target='#d1'> <img src='"+response[i].productImg+"' alt='"+JSON.stringify(s)+"'></span>";
							txt += "<td>" + response[i].productName;
						
						}
						$('#t1').html(txt);	
							
		}
		$(document).on('click', '#productDetail', function() {
			var $tr = $(this).parents("tr");
			var s=$tr.find("img").attr("alt");
			var a=JSON.parse(s);
			$('#d4').html(showprodetail(a));
			
		})
		$(document).on('click', '#youtube', function() {
					var a=$('#youtube').attr("alt");
					var video =JSON.parse(a);
					//console.log(a);
					$('#youtube').attr({id:"youtube1"});
					
					$('#mbody').append("<div id='youtubeX'>"+"<iframe id='video' src='"+video+"' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></div>");

					})
		$(document).on('click', '#youtube1', function() {
			$('#youtube1').attr({id:"youtube"});
			$('#youtubeX').remove();

			})
	
		
		$(document).on('click', '#swp', function() {

			var type = "switch";
			$.ajax({
				url : "shopping/switchProduct",
				datatype :"json",
				type : "POST",
				data : {
					type : type
				},
				success :function(response) {
					console.log(response);
					showtable(response);
				},
				complete:function(){
					$('#d1').addClass("modal fade").attr({"id":"d1","tabindex":"-1","role":"dialog","aria-labelledby":"exampleModalCenterTitle","aria-hidden":"true"});
					$('#d2').addClass("modal-dialog modal-xl modal-dialog-centered").attr({"role":"document"});
					$('#d3').addClass("modal-content");
					$('#d4').addClass("modal-body");

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
					
					var response = JSON.parse(JSON.stringify(response));
					console.log(response);
					showtable(response);
				},
				complete:function(){
					$('#d1').addClass("modal fade").attr({"tabindex":"-1","role":"dialog","aria-labelledby":"exampleModalCenterTitle","aria-hidden":"true"});
					$('#d2').addClass("modal-dialog modal-xl modal-dialog-centered").attr({"role":"document"});
					$('#d3').addClass("modal-content");
					$('#d4').addClass("modal-body");

					

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
					var response = JSON.parse(JSON.stringify(response));
					showtable(response);
				},
				complete:function(){
					$('#d1').addClass("modal fade").attr({"tabindex":"-1","role":"dialog","aria-labelledby":"exampleModalCenterTitle","aria-hidden":"true"});
					$('#d2').addClass("modal-dialog modal-xl modal-dialog-centered").attr({"role":"document"});
					$('#d3').addClass("modal-content");
					$('#d4').addClass("modal-body");


					}
			});
		})


		$(document).on('click', '#addProduct1',function() {

			var a=$('#quantity_input').val();
			var b = $('#itemdetail').text();
			var b1=JSON.parse(b)
			b1.amount=a;
			b=JSON.stringify(b1);
			console.log(b);
			var user ;
			if(u==""){
			console.log("localStorage.length");
			console.log(localStorage.length);
			let i=localStorage.length+1;
			b1['lsId']=i;
			b=JSON.stringify(b1);
			console.log(b);
			localStorage.setItem(i,b);
			alert("放入成功");
				}
			else{
						user =JSON.parse(u);
						var userId=user.userId;
						console.log("userId");
						console.log(userId);
						$.ajax({
							async : false,
							url : "shopping/addProduct",
							dataType : "json",
							type : "POST",
							data : {
								userId:userId,
								b : b
							},
							success : function(response) {
								console.log(response.t);
								if (response.t == true) {
									alert("放入成功");
								} else {
									alert("放入失敗");
								}
							}
						});
				}
			
			
		})
		


		$(document).on('click', '#order', function() {
			location.assign("orderPage");
		})

		$(document).on('click', '#shopcart', function() {
			location.assign("shoppingCartPage");
		})
		//$('#shopcart').click(function(){
		//	window.location.href("/shoppingCartPage");
		//	})
		
	</script>

</body>
</html>