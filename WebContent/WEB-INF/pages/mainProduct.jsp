<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style type="text/css">
#d1 {
	
}


</style>
</head>

<body>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<div id="bar">
			<input type="text" id="se1" placeholder="請輸入想搜尋的商品">
			<button id="search">查詢</button>
			<input type="button" id="query" value="所有商品">
		</div>
		<div>
			<span id="sp1"></span>
		</div>

		<div id="d1">
			<form id="f1">
				<table id="t1">


				</table>
				<!-- <input type="button" id="add" value="新增"> -->
			</form>
		</div>
	</main>
	<script type="text/javascript">
		function showtable(response) {
			$
					.ajax({
						async : false,
						type : "POST",
						url : "tradesystem/query",
						dataType : "json",
						beforesend : function() {
							$('#t1').html("");
						},
						success : function(response) {
							var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品類型<th>商品庫存<th>商品價錢<th>商品標籤<th>商品介紹<th colspan='2'>設定";
							for (let i = 0; i < response.length; i++) {
								txt += "<tr><td>" + response[i].productId;
								txt += "<td id='img'>";
								txt += "<td>" + response[i].productName;
								txt += "<td>" + response[i].productType;
								txt += "<td>" + response[i].inventory;
								txt += "<td>" + response[i].productPrice;
								txt += "<td>" + response[i].productTag;
								txt += "<td>" + response[i].productInfo;
								txt += '<td><input type="button" id="update" value="修改">';
								txt += '<td><input type="button" id="delete" value="刪除">';
							}
							$('#t1').html(txt);
						},
					});

		}

		$(document)
				.on(
						"click",
						"#query",
						function() {
							//$('#d1').toggle();
							$
									.ajax({
										url : "tradesystem/query",
										dataType : "json",
										type : "POST",
										success : function(response) {
											console.log(response);
											var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品類型<th>商品庫存<th>商品價錢<th>商品標籤<th>商品介紹<th colspan='2'>設定";
											for (let i = 0; i < response.length; i++) {
												txt += "<tr><td>"
														+ response[i].productId;
												txt += "<td id='img'>";
												txt += "<td>"
														+ response[i].productName;
												txt += "<td>"
														+ response[i].productType;
												txt += "<td>"
														+ response[i].inventory;
												txt += "<td>"
														+ response[i].productPrice;
												txt += "<td>"
														+ response[i].productTag;
												txt += "<td>"
														+ response[i].productInfo;
												txt += '<td><input type="button" id="update" value="修改">';
												txt += '<td><input type="button" id="delete" value="刪除">';
											}
											$('#t1').html(txt);
										}
									});
						})

		$(document).on('click', '#update', function() {
			$(this).attr({
				id : "done",
				value : "完成"
			});
			var $tr = $(this).parents("tr");

			$tr.find("td").not($("td:has(input)")).each(function() { //获取当前行所有除了含有button的td
				var $td = $(this);
				var _t = $td.text();
				var _w = $td.width();
				//var _h=$td.height();
				$td.html("");
				var $input = $("<input type='text'>");
				$input.appendTo($td).width(_w).val(_t);
			});
		});

		$(document).on('click', '#done', function() {
			$(this).attr({
				id : "update",
				value : "修改"
			});
			var $tr = $(this).parents("tr");
			var c = {};
			$tr.find("td input:text").each(function(i, e) {
				c[i] = e.value;
			})
			console.log(c);
			var b = JSON.stringify(c);
			console.log(b);
			$.ajax({
				async : false,
				url : "tradesystem/update",
				dataType : "json",
				type : "POST",
				data : {
					b : b
				},
				success : function(response) {
					console.log(response.t);
					if (response.t == true) {
						alert("修改成功");
					} else {
						alert("修改失敗");
					}
				},
				complete : function() {
					showtable();
				}
			});
			//var $tr=$this.parents("tr");

			//$tr.find("td").not($("td:has(input)")).each(function(){

			//});
		});

		$(document).on('click', '#delete', function() {
			var $tr = $(this).parents("tr");
			var d = $tr.find("td").eq(0).text();
			$.ajax({
				url : "tradesystem/delete",
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
					showtable();
				}
			});
		});

		$(document).on('click', '#add', function() {

			var txt = "<tr><td><input type='text'>";
			txt += "<td id='img'><input type='file'>";
			txt += "<td><input type='text'>";
			txt += "<td><input type='text'>";
			txt += "<td><input type='text'>";
			txt += "<td><input type='text'>";
			txt += "<td><input type='text'>";
			txt += "<td><input type='text'>";
			txt += '<td><input type="button" id="add1" value="送出">';
			txt += '<td><input type="button" id="delete" value="刪除">';
			$('#t1').append(txt);
		});
		$(document).on('click', '#add1', function() {
			$(this).attr({
				id : "update",
				value : "修改"
			});
			var $tr = $(this).parents("tr");
			var a = {};
			$tr.find("td input:text").each(function(i, e) {
				a[i] = e.value;
			})

			$.ajax({
				url : "add",
				dataType : "json",
				type : "POST",
				data : {
					a : a
				},
				success : function(response) {
					console.log(response);
					alert("新增成功");
				}

			});
		});

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
							txt += v.value + ",";
						});
						$('#sp1').text(txt);
					}
				});
			}
		})

		$(document)
				.on(
						'click',
						'#search',
						function() {
							console.log("search");
							var search = $('#se1').val();
							console.log(search);

							if (search != "" && search != null && search != " ") {
								$
										.ajax({
											url : "tradesystem/getSearch",
											datatype : "json",
											type : "POST",
											data : {
												search : search
											},
											beforesend : function() {
												$('#d1').show();
											},
											success : function(response) {
												console.log("yes1");
												console.log(response);
												console.log(response.length);
												var txt = "<tr><th>商品ID<th>商品照片<th>商品名稱<th>商品類型<th>商品庫存<th>商品價錢<th>商品標籤<th>商品介紹<th colspan='2'>設定";
												for (let i = 0; i < response.length; i++) {
													txt += "<tr><td>"
															+ response[i].productId;
													txt += "<td id='img'>"
															+ response[i].productImg;
													txt += "<td>"
															+ response[i].productName;
													txt += "<td>"
															+ response[i].productType;
													txt += "<td>"
															+ response[i].inventory;
													txt += "<td>"
															+ response[i].productPrice;
													txt += "<td>"
															+ response[i].productTag;
													txt += "<td>"
															+ response[i].productInfo;
													txt += '<td><input type="button" id="add1" value="送出">';
													txt += '<td><input type="button" id="delete" value="刪除">';
												}
												$('#t1').html(txt);
											}
										});
							}
							;
						})

		$.fn.serializeObject = function() {
			var formData = {};
			var formArray = this.serializeArray();
			for (var i = 0, n = formArray.length; i < n; ++i) {
				formData[formArray[i].name] = formArray[i].value;
			}
			return formData;
		};
		$(document).ready(function(){
			$("#admin-product").removeClass("d-none").addClass("d-block");
			})
	</script>
</body>
</html>