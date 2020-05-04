<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
#st1 img {
	width: 350px;
	height: 230px;
	margin: 0;
}

#st1 {
	width: 100%;
	height: 800px;
	margin-left: 0;
}

#st1 table {
	text-align: center;
	width: 1000px;
}

#st1 td {
	vertical-align: middle;
	padding: 0;
}

#dt1 {
	margin: auto;
	width: 1000px;
	position: relative;
	top: 0;
}

div, tr, td, th {
	margin: 0;
}

#nav-div {
	background: rgba(20%, 20%, 20%, 0.4);
	bottom: 0;
	position: fixed;
	width: 100%;
	height: 90px;
}

#sp1 {
	display: flex;
	vertical-align: middle;
}
#nameResult{
	color: red;
	display: inline;
}
#phoneResult{
	color: red;
	display: inline;
}

#emailResult{
	color: red;
	display: inline;
}

.inputNum{text-align: center; }
</style>
</head>
<body>
	<!-- TOPBAR -->
	<jsp:include page="topBar.jsp" />


	<!-- 顯示購物商品區塊 -->
	<div id="st1">

		<div id="dt1">
			<h1>購物車</h1>
			<table style="background: rgba(50%, 50%, 50%, 0.3)">
				<thead>
					<tr>
						<th>商品ID
						<th>商品照片
						<th>商品名稱
						<th>商品價錢
						<th>商品數量
						<th>總金額
						<th>編輯
				</thead>
				<tbody id="t1"></tbody>
			</table>
		</div>

		<!-- 付款模擬框 -->
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
								<label for="orderName">姓名:</label> <input type="text"
									class="form-control" name="orderName" id="orderName"
									aria-describedby="emailHelp"><span id = "nameResult"></span>
									<small id="emailHelp" class="form-text text-muted">請輸入中文或英文名子  長度不能超過20  不能包含符號!!</small>
							</div>
							<div class="form-group">
								<label for="orderPhone">電話:</label> <input type="text"
									class="form-control" name="orderPhone" id="orderPhone"
									aria-describedby="emailHelp"><span id = "phoneResult"></span>
								<small id="emailHelp" class="form-text text-muted">輸入手機電話或家機電話  家機電話需加區碼   不能包含符號!!</small>
							</div>
							<div class="form-group">
								<label for="orderAddress">住址:</label> <input type="text"
									class="form-control" name="orderAddress" id="orderAddress"
									aria-describedby="emailHelp">
							</div>

							<div class="form-group">
								<label for="orderEmail">信箱:</label> <input type="email"
									class="form-control" name="orderEmail" id="orderEmail"
									placeholder="name@example.com"><span id = "emailResult"></span>
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
		<!-- 結帳的BAR -->
	</div>
	<div id="nav-div">
		<div style="position: fixed; bottom: 10px; right: 80px">
			<span style="color: red;">總金額&nbsp;&nbsp;:&nbsp;&nbsp;</span><span
				id="total" style="color: red;"></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="paybillF" style="width: 200px;"
				class="btn btn-primary btn-lg " type="button" data-toggle="modal"
				data-target="#exampleModalCenter">結&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帳</button>
		</div>
	</div>

	<script type="text/javascript">
//-----------------*********變數區*********-------------------
		var t = false;
		var check1=false;
		var check2=false;
		var check3=false;
		var check4=false;
//-----------------*********測試區*********-------------------
		//沒效果 控制model 呼叫前或後的函數
// 		$("#exampleModalCenter").on("show.bs.modal", function(e) {
// 			console.log('顯示視窗前呼叫');
// 		});
// 		$('#exampleModalCenter').on('hidden.bs.modal fade', function(e) {
// 			alert("asd");
// 		})

// 		if ($('#exampleModalCenter').hasClass('model')) {
// 			console.log('視窗目前是開啟的狀態..');
// 		}

//-----------------*********網頁開始函數區*********-------------------
//-----------------抓取使用者資料-------------------	
		$(document).ready(
				function() {

					var user;
					if (window.sessionStorage.getItem("loginUser") == "") {
						var response = [];
						for (var i = 0; i < localStorage.length; i++) {
							response.push(JSON.parse(localStorage
									.getItem(localStorage.key(i))));
						}
						console.log("response");
						console.log(response);
						showtables(response);
					} else {
						user = JSON.parse(window.sessionStorage
								.getItem("loginUser"));

						var id = user.userId;
						$.ajax({
							url : "shopping/showCartProduct",
							dataType : "json",
							type : "POST",
							data : {
								id : id
							},
							success : function(response) {
								console.log("資料回應:" + response);
								showtables(response);
							},
							complete : function() {
								total();

							}
						});

					}

					total();
				})
//-----------------*********網頁結束函數區*********-------------------
		$(window).bind('beforeunload', function() {
			var t = false;
			if (window.sessionStorage.getItem("loginUser") != "") {
				var c = [];
				var x = {};
				var data;
				$('#t1').find('.upd').each(function(i, e) {

					let j;
					let k;
					if (i % 2 == 0) {
						x[i] = e.innerHTML;

					} else {

						k = e.value;
						c.push({
							"shoppingCartId" : x[i - 1],
							"amount" : k
						});
					}
				});
				data = JSON.stringify(c);

				$.ajax({
					url : "shopping/shoppingCartUpdate",
					dataType : "json",
					data : {
						data : data
					},
					type : "post",
					success : function(response) {
						console.log(response);
						if (respone == "false") {
							return '提示資訊';
						}

					}
				});
			} else {
				var x = {};
				$('#t1').find('.localupd').each(function(i, e) {
					console.log(e);
					let j;
					let k;
					if (i % 2 == 0) {
						x[i] = e.value;
						console.log(x[i]);
					} else {
						j = localStorage.getItem(e.innerHTML);
						k = JSON.parse(j);
						k.amount = x[i - 1];
						k = JSON.stringify(k);
						localStorage.setItem(e.innerHTML, k);
					}
				});

			}
			if (t == true) {
				return '提示資訊';
			}

		});
		
//-----------------*********觸發按鈕區*********-------------------
//-----------------輸入數量改變-------------------	
// 		$(document).on('change', '#quantity_input', function() {
// 			$tr = $(this).parents("tr");
// 			var num = $tr.find("input[id='quantity_input']");
// 			console.log(num.val());
// 			if (num.val() < 1) {
// 				num.val(1);
// 			}
// 			var num1 = $tr.find("#quantity_input").val();
// 			var price = $tr.find("td[id='oriPrice']").text();
// 			console.log(price);
// 			$tr.find("td[id='money']").html(price * num1);
// 			total()

// 		})
//-----------------輸入數量改變_加號-------------------	
		$(document).on('click', '#plus', function() {

			$tr = $(this).parents("tr");
			var num = $tr.find("input[id='quantity_input']");
			num.val(parseInt(num.val()) + 1);
			var num1 = num.val();
			var price = $tr.find("td[id='oriPrice']").text();

			$tr.find("td[id='money']").html(price * num1);
			total()

		})
//-----------------輸入數量改變_減號-------------------	
		$(document).on('click', '#noplus', function() {

			$tr = $(this).parents("tr");
			var num = $tr.find("input[id='quantity_input']");
			if (num.val() > 1) {
				num.val(parseInt(num.val()) - 1)
			}
			var num1 = num.val();
			var price = $tr.find("td[id='oriPrice']").text();

			$tr.find("td[id='money']").html(price * num1);
			total()

		})

//-----------------刪除商品-------------------	

		$(document).on('click', '#delete', function() {
			var $tr = $(this).parents("tr");
			if (window.sessionStorage.getItem("loginUser") == "") {
				var d = $tr.find("td").eq(8).text();
				console.log(d);
				localStorage.removeItem(d);
				$tr.remove();
			} else {
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
//-----------------結帳BAR_確認金額-------------------	
		$(document).on('click', '#paybillF', function() {
			var a = total();
			if (a > 30000) {
				alert("信用卡刷卡最大金額不得超過3萬元台幣");
			}
		})
//-----------------結帳BAR_結帳資料輸入-------------------	
		$(document).on('click', '#paybill', function() {

			var items = [];
			$(document).find('span[id="item"]').each(function(i, e) {
				items[i] = e.innerHTML;
			})

			var a = $('#f1').serializeObject();
			var totalPrice = $('#total').html();

			if (window.sessionStorage.getItem("loginUser") == "") {
				a['userId'] = 1;
			} else {
				user = JSON.parse(window.sessionStorage.getItem("loginUser"));
				a['userId'] = user.userId;
			}
			a['orderPrice'] = totalPrice;
			var form = JSON.stringify(a);
			var items1 = "[" + items + "]";
			console.log("a:" + a);
			console.log("total:" + total);
			console.log("form:" + form);
			console.log("items1:" + items1);
			var a = total();
			if (a > 30000) {
				alert("信用卡刷卡最大金額不得超過3萬元台幣");
			} else {
				console.log("bbbb");
				$.ajax({
					url : "shoppingCart/payBill",
					dataType : "text",
					data : {
						form : form,
						items1 : items1
					},
					type : "POST",
					success : function(response) {
						console.log(response);
						document.write(response);
					}
				});
			}
		})
//-----------------檢查輸入資料輸入-------------------	

$(document).on('blur','#orderName',function(){
	var name=$('#orderName').val();
	console.log(name.length);
	if(name!=""||name.length<10){
		  for (var i = 0; i < name.length; i++) {
			  
                  if ((name.charCodeAt(i) >= 33 && name.charCodeAt(i) <= 47) || (name.charCodeAt(i) >= 58 && name.charCodeAt(i) <= 64)) {      //符號
                      $('#nameResult').html("不能有符號");
                      $("#orderName").focus($('#orderName').val(""));
                  }
                  else if (name.charCodeAt(i) >= 48 && name.charCodeAt(i) <= 57) { //數字
                	  $('#nameResult').html("不能有數字");
                	  $("#orderName").focus($('#orderName').val(""));
                  }

              }
		}

	})
	
$(document).on('blur','#orderPhone',function(){
	var phone=$('#orderPhone').val();

	if(phone==""||phone.length!=10){
		$('#phoneResult').html("長度小於10");
		$("#orderPhone").focus($('#orderPhone').val(""));
		
		}
	else{
		console.log("else");
		for (var i = 0; i < phone.length; i++) {
			if (phone.charCodeAt(i) >= 48 && phone.charCodeAt(i) <= 57) {      //符號
				$('#phoneResult').html("OK");
	            
	        }
			else{
				$('#phoneResult').html("不能為數字以外的字元");
				break;
				}
			}

		}
	console.log("finish");
	
	})
		
//-----------------*********函數區*********-------------------
//-----------------顯示金額-------------------			
		function total() {
			var a = 0;
			$('tr').find("td[id='money']").each(function(i, e) {
				console.log("e:" + e.innerHTML);
				a += parseInt(e.innerHTML);
			})
			console.log("a:" + a);

			$('#total').html(a);
			return a;
		}
//-----------------顯示商品-------------------	
		function showtables(response) {
			var txt = "";
			for (let i = 0; i < response.length; i++) {
				var item = {
					productId : response[i].productId,
					productName : response[i].productName,
					productPrice : response[i].productPrice,
					amount : response[i].amount
				}
				console.log(response[i].lsId);

				txt += "<tr><td class='upd' style='display:none'>"
						+ response[i].shoppingCartId;
				txt += "<td style='display:none'><span id='item'>"
						+ JSON.stringify(item);
				txt += "<td>" + response[i].productId;
				txt += "<td id='img'><img src='"+response[i].productImg+"'>";
				txt += "<td>" + response[i].productName;
				txt += "<td id='oriPrice'>" + response[i].productPrice;
				txt += "<td>"
						+ "<span id='sp1'><button id='noplus' type='button' class='btn btn-outline-secondary btn-sm'>-</button>";
				txt += "<input style='width: 35px;' id='quantity_input' class='upd localupd inputNum' readonly type='text' value='"+ response[i].amount+"'>";
				txt += "<button id='plus' type='button' class='btn btn-outline-secondary btn-sm'>+</button></span>";
				txt += "<td id='money'>"
						+ (response[i].productPrice * response[i].amount)
				txt += "<td id='lsId' class='localupd' style='display:none'>"
						+ response[i].lsId;
				txt += "<td><botton id='delete' class='btn btn-danger'>移除</botton>"

			}
			$('#t1').html(txt);
		}
		
//-----------------序列化轉成Json物件形式-------------------	
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