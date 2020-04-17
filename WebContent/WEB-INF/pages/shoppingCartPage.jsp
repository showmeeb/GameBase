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
#st1 img {
	width: 100px
}
</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<h1>購物車</h1>
	<div id="st1">
		<table id="t1"></table>

	</div>

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModalCenter">結帳</button>

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
					<form>
						<div class="form-group">
							<label for="userName">姓名:</label> <input type="text"
								class="form-control" id="userName" aria-describedby="emailHelp">
						</div>
						<div class="form-group">
							<label for="userPhone">電話:</label> <input type="text"
								class="form-control" id="userPhone" aria-describedby="emailHelp">
						</div>
						<div class="form-group">
							<label for="userPhone">住址:</label> <input type="text"
								class="form-control" id="userPhone" aria-describedby="emailHelp">
						</div>
						<div class="form-row align-items-center">
							<div class="col-auto my-1">
								<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
								<select class="custom-select mr-sm-2"
									id="inlineFormCustomSelect">
									<option selected>付款方式</option>
									<option value="1">賣器官</option>
									<option value="2">信用卡</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<span id="total">總金額:</span>
					<button id="paybill" type="button" class="btn btn-primary">結帳</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">取消</button>

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function showtables(response) {
			var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品價錢<th>商品數量<th>總金額<th>編輯";
			for (let i = 0; i < response.length; i++) {
				txt += "<tr><td style='display:none'>"
						+ response[i].shoppingCartId;
				txt += "<td>" + response[i].productId;
				txt += "<td id='img'><img src='"+response[i].productImg+"'>";
				txt += "<td>" + response[i].productName;
				txt += "<td>" + response[i].productPrice;
				txt += "<td>" + response[i].amount;
				txt += "<td>" + (response[i].productPrice * response[i].amount)
				txt += "<td><input type='button' id='delete' value='移除'>"
			}
			$('#t1').html(txt);
		}

		$(document).ready(function() {
			var id = $
			{
				UserData.userId
			}
			;
			$.ajax({
				url : "shopping/showCartProduct",
				dataType : "json",
				type : "POST",
				data : {
					id : id
				},
				success : function(response) {
					console.log(response);
					showtables(response);
				}
			});

		})
		$(document).on('click', '#delete', function() {
			var $tr = $(this).parents("tr");
			var d = $tr.find("td").eq(0).text();
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
					} else {
						alert("刪除失敗");
					}

				},
				complete : function() {
					$(document).ready(function() {
						var id = $
						{
							UserData.userId
						}
						;
						$.ajax({
							url : "shopping/showCartProduct",
							dataType : "json",
							type : "POST",
							data : {
								id : id
							},
							success : function(response) {
								console.log(response);
								showtables(response);
							}
						});

					})
				}
			});
		});

		$(document).on('click', '#paybill', function() {
			var userId =${UserData.userId};
			var form 


			})
		
	</script>

</body>
</html>