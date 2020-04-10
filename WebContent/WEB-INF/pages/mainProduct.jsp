<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style type="text/css">
#d1 {
	display: none;
}
</style>
</head>

<body>
	<div>
		<input type="text" id="se1" placeholder="請輸入想搜尋的商品"><button id="search">查詢</button><input type="button" id="query" value="所有商品">
	</div>

	<div id="d1">
		<form id="f1">
			<table id="t1">


			</table>
			<input type="button" id="add" value="新增">
		</form>
	</div>

	<script type="text/javascript">
		$(document)
				.on(
						"click",
						"#query",
						function() {
							$('#d1').toggle();
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
				url : "tradesystem/update",
				dataType : "json",
				type : "POST",
				data : {
					b : b
				},
				success : function(response) {
					console.log(response.t);
					if(response.t==true){
						alert("修改成功");
					}else{
						alert("修改失敗");
						}
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
					$tr.remove();
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

		$(document).on('click', '#add1', function() {

			}
		

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