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
<style type="text/css">
body{
padding: 0;
}
#st1 img {
	width: 130px;border: 1px solid blue; margin:0;
}
#st1 {
	width: 1520px;height:800px;margin-left:0; 
	position: relative;
	background-image:url('https://i.imgur.com/1u2eosf.jpg');
	background-size:100%;
	background-color:#1C1C1C;
	}
#st1 table{
	
	text-align: center;
	width: 1000px;
	height:450px;
	}
#st1 td{
	vertical-align:middle;
	padding: 0;
	}
#dt1{
	margin:auto;
	width: 1000px;
	position: relative;
	top:0;

	}

div,tr,td {

	margin:0;
	}
#imgb1 {
	background:url('');
	display:block;
  	width:100px;
  	height:100px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 89px;
  	right: 80px;
	
	}
#imgb3 {
	background:url('');
	display:block;
  	width:100px;
  	height:100px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 89px;
  	right: 0px;
	
	}
#imgb4 {
	background:url('');
	display:block;
  	width:100px;
  	height:100px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 89px;
  	right: 130px;
	
	}
#imgb2 {
	background:url('');
	display:block;
  	width:300px;
  	height:200px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 0px;
  	right: 1200px;
	
	}
#imgb5 {
	background:url('');
	display:block;
  	width:300px;
  	height:300px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 250px;
  	right: 1270px;
	
	}
#imgb6 {
	background:url('');
	display:block;
  	width:150px;
  	height:450px;
	background-repeat:no-repeat;
	position: fixed;
	bottom: 270px;
  	right: 20px;
	
	}
#nav-div{background: rgba(10%,10%,10%,0.9)}

#paybillF{}
</style>
</head>
<body >
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<jsp:include page="topBar.jsp" />
	
	<div id="imgb1"></div>
	<div id="imgb3"></div>
	<div id="imgb4"></div>
	<div id="imgb5"></div>
	<div id="imgb6"></div>


	<div id="st1" class="text-white">
	
		<div id="dt1" >
		<h1>購物車</h1>
		<table id="t1" style="background:rgba(5%,5%,5%,0.7) "></table>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div id="payform" class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">付款資訊</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="f1">
						<div class="form-group">
							<label for="userName">姓名:</label> <input type="text"
								class="form-control" name="orderName" aria-describedby="emailHelp">
						</div>
						<div class="form-group">
							<label for="userPhone">電話:</label> <input type="text"
								class="form-control" name="orderPhone" aria-describedby="emailHelp">
						</div>
						<div class="form-group">
							<label for="userPhone">住址:</label> <input type="text"
								class="form-control" name="orderAddress" aria-describedby="emailHelp">
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					
					<button id="paybill" type="button" class="btn btn-primary">結帳</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">取消</button>

				</div>
			</div>
		</div>
	</div>
	
	</div>
		<div id="nav-div" style="bottom:0; position:fixed;width:1550px;height:90px;">
	  	<div style="position:fixed;bottom:10px;right:80px">
  		<span style="color: red;">總金額&nbsp;&nbsp;:&nbsp;&nbsp;</span><span id="total"style="color: red;"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    		<button id="paybillF" style="width:200px;" class="btn btn-outline-success btn-lg"  type="button" data-toggle="modal"
		data-target="#exampleModalCenter">結&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帳</button>
  		</div>
	</div>
	
	<script type="text/javascript">

	$("#exampleModalCenter").on("show.bs.modal",function(e){
			console.log('顯示視窗前呼叫');
		});
	$('#exampleModalCenter').on('hidden.bs.modal fade', function (e) {
	alert("asd");
	})
	
	if($('#exampleModalCenter').hasClass('model')){
	  console.log('視窗目前是開啟的狀態..');
	}


	

		function total(){
		var a=0;
		$('tr').find("td[id='money']").each(function(i, e) {
			console.log("e:"+e.innerHTML);
			a+=parseInt(e.innerHTML);
			})
		console.log("a:"+a);
		
		$('#total').html(a);
		return a;
		}
	
		function showtables(response) {
			var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品價錢<th>商品數量<th>總金額<th>編輯";
			for (let i = 0; i < response.length; i++) {
				var item ={
						productId:response[i].productId,
						productName:response[i].productName,
						productPrice:response[i].productPrice,
						amount:response[i].amount
						}
				console.log(response[i].lsId);
				txt += "<tr><td style='display:none'>"
						+ response[i].shoppingCartId;
				txt += "<td style='display:none'><span id='item'>"+JSON.stringify(item);
				txt += "<td>" + response[i].productId;
				txt += "<td id='img'><img src='"+response[i].productImg+"'>";
				txt += "<td>" + response[i].productName;
				txt += "<td id='oriPrice'>" + response[i].productPrice;
				txt += "<td>"+"<span>數量:<button id='noplus' type='button' class='btn btn-outline-secondary btn-sm'>-</button>";
				txt += "<input style='width: 40px;' id='quantity_input' type='text' value='"+ response[i].amount+"'>";
				txt	+= "<button id='plus' type='button' class='btn btn-outline-secondary btn-sm'>+</button></span>";
				txt += "<td id='money'>" + (response[i].productPrice * response[i].amount)
				txt += "<td id='lsId' style='display:none'>"+response[i].lsId;
				txt += "<td><botton id='delete' class='btn btn-danger'>移除</botton>"
			}
			$('#t1').html(txt);
		}

		$(document).on('change', '#quantity_input', function() {
			$tr=$(this).parents("tr");
			var num=$tr.find("input[id='quantity_input']");
			console.log(num.val());
			if(num.val()<1){
				num.val(1);
				}
			var num1=$tr.find("#quantity_input").val();
			var price = $tr.find("td[id='oriPrice']").text();
			console.log(price);
			$tr.find("td[id='money']").html(price*num1);
			total()
			
		})

		$(document).on('click', '#plus', function() {
			$tr=$(this).parents("tr");
			var num=$tr.find("input[id='quantity_input']");
			num.val(parseInt(num.val()) + 1);
			var num1=num.val();
			var price = $tr.find("td[id='oriPrice']").text();
			
			$tr.find("td[id='money']").html(price*num1);
			total()
			
		})
		
		$(document).on('click', '#noplus', function() {
			$tr=$(this).parents("tr");
			var num=$tr.find("input[id='quantity_input']");
			if (num.val() > 1) {
				num.val(parseInt(num.val()) - 1)
            }
			var num1=num.val();
			var price = $tr.find("td[id='oriPrice']").text();
			
			$tr.find("td[id='money']").html(price*num1);
			total()
			
		})

		
		$(document).ready(function() {
				
				var user;
				if(window.sessionStorage.getItem("loginUser")==""){
					var response = [];
					for(var i=0; i<localStorage.length;i++){
						  response.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
						}
					console.log("response");
					console.log(response);
					showtables(response);
				}else{
					user=JSON.parse(window.sessionStorage.getItem("loginUser"));

					var id =user.userId;
					$.ajax({
						url : "shopping/showCartProduct",
						dataType : "json",
						type : "POST",
						data : {
							id : id
						},
						success : function(response) {
							console.log("資料回應:"+response);
							showtables(response);
						},
						complete : function() {
							total();
							
						}
					});
					
				}
			
					
			total();
		})
		
		$(document).on('click', '#delete', function() {
			var $tr = $(this).parents("tr");
			if(window.sessionStorage.getItem("loginUser")==""){
				var d = $tr.find("td").eq(8).text();
				console.log(d);
				localStorage.removeItem(d);
				$tr.remove();
			}
			else{
				var d = $tr.find("td").eq(0).text();
				console.log(d);
				$.ajax({
					url : "shopping/deleteCartProduct",
					dataType : "json",
					type : "POST",
					data : {
						d : d
					},
					success : function(response) {
						console.log(response);
						if (response.t == true) {
							alert("刪除成功");
							$tr.remove();
							total();
						} else {
							alert("刪除失敗");
						}
					}
					})
			}
			});
		
		$(document).on('click', '#paybillF', function() {
			var a=total();
			if(a>30000){
				alert("信用卡刷卡最大金額不得超過3萬元台幣");
				}
			})
		$(document).on('click', '#paybill', function() {
			
			var items =[];
			$(document).find('span[id="item"]').each(function(i,e){
					items[i]=e.innerHTML;
				})

			var a = $('#f1').serializeObject();
			var totalPrice = $('#total').html();

			if(window.sessionStorage.getItem("loginUser")==""){
					a['userId']=999999;
				}else{
					user=JSON.parse(window.sessionStorage.getItem("loginUser"));
					a['userId']=user.userId;
				}
			a['orderPrice']=totalPrice;
			var form = JSON.stringify(a);
			var items1= "["+items+"]";
			console.log("a:"+a);
			console.log("total:"+total);
			console.log("form:"+form);
			console.log("items1:"+items1);
			var a=total();
			if(a>30000){
				alert("信用卡刷卡最大金額不得超過3萬元台幣");
				}else{
					console.log("bbbb");
					$.ajax({
						url :"shoppingCart/payBill",
						dataType : "text",
						data:{form:form,items1:items1},
						type : "POST",
						success : function(response) {
							console.log(response);
							document.write(response);
						}
					});
					}
			})
			
			
			$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
			};
		
	</script>

</body>
</html>