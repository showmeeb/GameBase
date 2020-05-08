<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
.resultTableP {
	text-align: center;
	word-break: break-all;
}


.resultTableP th td{
  width: 9%;
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

			
<!-- 				<span id="sp1">asd</span> -->
				<div class="col-6 align-self-center">
							<ul class="pagination" id="sp1"></ul>
				</div>


			<div id="myd1">
				<form id="myf1">
					<table class="resultTableP" id="myt1" border="1">
					</table>
					<!-- <input type="button" id="add" value="新增"> -->
				</form>
			</div>
		</div>
	</main>

	<script type="text/javascript">
	var cardResults;
	var array;
	var pageItem = 20;

    $(document).on("click", "#query",
    	      function () {
    	        $.ajax({
    	          url: "tradesystem/query",
    	          dataType: "json",
    	          type: "POST",
    	          
    	          success: function (response) {
    	            console.log(response);
    	            array=response;
    	            var txt = '<tr><th style="width:4%">商品ID</th><th>商品影片</th><th>商品照片</th><th style="width:16%">商品名稱</th><th style="width:5%">商品類型</th><th style="width:5%">商品庫存</th><th style="width:5%">商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品銷售量</th><th colspan="2">設定</th></tr>';
    	            for (let i = 0; i < 20; i++) {
    	              txt += "<tr><td>" + response[i].productId;
    	              txt += "<td>" + response[i].productVideo;
    	              txt += "<td>" + response[i].productImg;
    	              txt += "<td>" + response[i].productName;
    	              txt += "<td>" + response[i].productType;
    	              txt += "<td>" + response[i].inventory;
    	              txt += "<td>" + response[i].productPrice;
    	              txt += "<td>" + response[i].productTag;
    	              txt += "<td>" + response[i].productInfo;
    	              txt += "<td>" + response[i].searchFreq;
    	              txt += '<td><input type="button" id="update" value="修改">';
    	              txt += '<td><input type="button" id="delete" value="刪除">';
    	            }
    	            numPage(response);
    	            $('#myt1').html(txt);
    	          }
    	        });
    	      })

		$(document).on('click', '#update', function() {
			$(this).attr({
				id : "done",
				value : "完成"
			});
			var $tr = $(this).parents("tr");
			var idInput;
			$tr.find("td").not($("td:has(input)")).each(function() { //获取当前行所有除了含有button的td
				var $td = $(this);
				var _t = $td.text();
				var _w = $td.width();
				//var _h=$td.height();
				$td.html("");
				var $input = $("<input type='text'>");
				$input.appendTo($td).width(_w).val(_t);
				
				
			});
			$tr.find("td input:text").eq(0).prop('readonly','readonly');
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
					refresh()
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
					refresh()
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
			txt += "<td><input type='text'>";
			txt += '<td><input type="button" id="add1" value="送出">';
			txt += '<td><input type="button" id="delete" value="刪除">';
			$('#myt1').append(txt);
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

	
		
// 		$(document).on('keyup', '#se1', function() {
// 			var sh = $('#se1').val();
// 			if (sh != "" && sh != null && sh != " ") {
// 				$.ajax({
// 					url : "tradesystem/search",
// 					datatype : "json",
// 					type : "GET",
// 					data : {
// 						sh : sh
// 					},
// 					success : function(response) {
// 						console.log("yes");
// 						console.log(response);
// 						var txt = "";
// 						$.map(response, function(v, index) {
// 							txt += v.value + ",";
// 						});
// 						$('#sp1').text(txt);
// 					}
// 				});
// 			}
// 		})

		//關鍵字自動完成
			$.ajax({
				url : '<c:url value="/autoComplete"/>',
				type : "POST",
				success : function(data) {
					$("#se1").autocomplete({
						source :  function(req, response) {
						    var results = $.ui.autocomplete.filter(data, req.term);

						    response(results.slice(0, 10));
						   }
						
						
					});
				}
			});
		$.ajaxSetup({
				 contentType: "application/x-www-form-urlencoded; charset=utf-8"
				});

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
											type : "POST",
											
											data : {
												search : search
											},
											beforesend : function() {
												$('#d1').show();
											},
											success : function(response) {
												array=response;
												
												console.log(response);
// 												response=JSON.parse(re);
												console.log("yes1");
												console.log(response);
												console.log(response.length);
												var txt = '<tr><th style="width:4%">商品ID</th><th>商品影片</th><th>商品照片</th><th style="width:16%">商品名稱</th><th style="width:5%">商品類型</th><th style="width:5%">商品庫存</th><th style="width:5%">商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品銷售量</th><th colspan="2">設定</th></tr>';
							    	            for (let i = 0; i < 20; i++) {
							    	              txt += "<tr><td>" + response[i].productId;
							    	              txt += "<td>" + response[i].productVideo;
							    	              txt += "<td>" + response[i].productImg;
							    	              txt += "<td>" + response[i].productName;
							    	              txt += "<td>" + response[i].productType;
							    	              txt += "<td>" + response[i].inventory;
							    	              txt += "<td>" + response[i].productPrice;
							    	              txt += "<td>" + response[i].productTag;
							    	              txt += "<td>" + response[i].productInfo;
							    	              txt += "<td>" + response[i].searchFreq;
							    	              txt += '<td><input type="button" id="update" value="修改">';
							    	              txt += '<td><input type="button" id="delete" value="刪除">';
							    	            }
							    	            numPage(response);
							    	            $('#myt1').html(txt);
											}
										});
							}
							;
						})
						
						
		$(document).on('click', ".pageBnt", function() {
			let toPage = this.id - 1;
			$("#myt1").html(changePage(array, toPage));
				
			
		})
						
		function numPage(response) {
			var pageNum = Math.ceil(response.length / 20);
			
			var pageTxt = "";
			
			for (i = 1; i <= pageNum; i++) {
				pageTxt += '<li class="pageLi"><button id="'+i+'" class="btn btn-light pageBnt">'
						+ i + '</button></li>'
			}
			$("#sp1").html(pageTxt);
			
		}

		function changePage(array, toPage) {
			console.log("array123")
			console.log(array)
			cardResults ="";
			var cardResults = '<tr><th style="width:4%">商品ID</th><th>商品影片</th><th>商品照片</th><th style="width:16%">商品名稱</th><th style="width:5%">商品類型</th><th style="width:5%">商品庫存</th><th style="width:5%">商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品銷售量</th><th colspan="2">設定</th></tr>';
			for (i = 0 + pageItem * toPage; i < pageItem + pageItem * toPage; i++) {
				if (i < array.length) {
            	cardResults += "<tr><td>" + array[i].productId;
            	cardResults += "<td>" + array[i].productVideo;
            	cardResults += "<td>" + array[i].productImg;
            	cardResults += "<td>" + array[i].productName;
            	cardResults += "<td>" + array[i].productType;
            	cardResults += "<td>" + array[i].inventory;
            	cardResults += "<td>" + array[i].productPrice;
            	cardResults += "<td>" + array[i].productTag;
            	cardResults += "<td>" + array[i].productInfo;
            	cardResults += "<td>" + array[i].searchFreq;
            	cardResults += '<td><input type="button" id="update" value="修改">';
            	cardResults += '<td><input type="button" id="delete" value="刪除">';
				}
            }
			return cardResults;

		}
		function showtable(response){
			 var txt = '<tr><th style="width:4%">商品ID</th><th>商品影片</th><th>商品照片</th><th style="width:16%">商品名稱</th><th style="width:5%">商品類型</th><th style="width:5%">商品庫存</th><th style="width:5%">商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品銷售量</th><th colspan="2">設定</th></tr>';
	            for (let i = 0; i < 20; i++) {
	              txt += "<tr><td>" + response[i].productId;
	              txt += "<td>" + response[i].productVideo;
	              txt += "<td>" + response[i].productImg;
	              txt += "<td>" + response[i].productName;
	              txt += "<td>" + response[i].productType;
	              txt += "<td>" + response[i].inventory;
	              txt += "<td>" + response[i].productPrice;
	              txt += "<td>" + response[i].productTag;
	              txt += "<td>" + response[i].productInfo;
	              txt += "<td>" + response[i].searchFreq;
	              txt += '<td><input type="button" id="update" value="修改">';
	              txt += '<td><input type="button" id="delete" value="刪除">';
	            }
	            $('#myt1').html(txt);
			}

		 function refresh() {
 	        $.ajax({
 	          url: "tradesystem/query",
 	          dataType: "json",
 	          type: "POST",
 	          
 	          success: function (response) {
 	            console.log(response);
 	            array=response;
 	            var txt = '<tr><th style="width:4%">商品ID</th><th>商品影片</th><th>商品照片</th><th style="width:16%">商品名稱</th><th style="width:5%">商品類型</th><th style="width:5%">商品庫存</th><th style="width:5%">商品價錢</th><th>商品標籤</th><th>商品介紹</th><th>商品銷售量</th><th colspan="2">設定</th></tr>';
 	            for (let i = 0; i < 20; i++) {
 	              txt += "<tr><td>" + response[i].productId;
 	              txt += "<td>" + response[i].productVideo;
 	              txt += "<td>" + response[i].productImg;
 	              txt += "<td>" + response[i].productName;
 	              txt += "<td>" + response[i].productType;
 	              txt += "<td>" + response[i].inventory;
 	              txt += "<td>" + response[i].productPrice;
 	              txt += "<td>" + response[i].productTag;
 	              txt += "<td>" + response[i].productInfo;
 	              txt += "<td>" + response[i].searchFreq;
 	              txt += '<td><input type="button" id="update" value="修改">';
 	              txt += '<td><input type="button" id="delete" value="刪除">';
 	            }
 	            numPage(response);
 	            $('#myt1').html(txt);
 	          }
 	        });
 	      }

		$.fn.serializeObject = function() {
			var formData = {};
			var formArray = this.serializeArray();
			for (var i = 0, n = formArray.length; i < n; ++i) {
				formData[formArray[i].name] = formArray[i].value;
			}
			return formData;
		};
		$(document).ready(function() {
			$("#admin-product").removeClass("d-none").addClass("d-block");
		})
		
	</script>


</body>
</html>