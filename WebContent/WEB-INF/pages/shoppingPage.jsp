<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>



<body>
<!-- TopBAR -->
	<jsp:include page="topBar.jsp" />
	
	<div id="imgb1" onclick="window.open('https://www.unchartedthegame.com/en-us/','asd','_blank')"></div>
	<div id="imgb2" onclick="window.open('https://www.playstation.com/cht-tw/games/death-stranding-ps4/','asd','_blank')"></div>
<!-- 顯示商品區塊 -->
	<div id="dt1">
		<form>
			<table id="t1">
			</table>
		</form>
	</div>
<!-- 模擬框 -->
	<div class="modal fade" id="d1" tabindex="-1" role="dialog"
		aria-labelledby="d1" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div class="modal-content"
				style="width: 800px; height: 600px; display: flex; justify-content: flex-start; padding: 3px; margin: auto;">
				<div id="d4" class="modal-body"></div>
			</div>
		</div>
	</div>

<!-- 顯示商品排版區塊 -->
	<div class="container">
		<div class="row">
			<div class="col-3 column text-center" style="padding: 0;">
				<div id="switchBar" style="background-color:rgba(80%, 80%, 80%, 0.2);">
					<ul class="nav justify-content-center">
						<li id="swp" class="nav-item" role="button" tabindex="0"
							aria-pressed="true"><img
							src="https://i.imgur.com/ilWFjYW.png"></li>
						<li id="psp" class="nav-item" role="button" tabindex="0"
							aria-pressed="true"><img
							src="https://i.imgur.com/chcSF3h.png"></li>
						<li id="xboxp" class="nav-item" role="button" tabindex="0"
							aria-pressed="true"><img
							src="https://i.imgur.com/ZfTAY8U.png"></li>
					</ul>
					<hr>
					<div id="switchDiv">
						<div class="custom-control custom-checkbox ">
						  <input type="checkbox" class="custom-control-input" id="customSwitch1" value="動作">
						  <label class="custom-control-label" for="customSwitch1">動&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch2" value="運動">
						  <label class="custom-control-label" for="customSwitch2">運&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;動</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch3" value="射擊">
						  <label class="custom-control-label" for="customSwitch3">射&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;擊</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch4" value="冒險">
						  <label class="custom-control-label" for="customSwitch4">冒&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;險</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch5" value="競速">
						  <label class="custom-control-label" for="customSwitch5">競&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;速</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch6" value="驚悚">
						  <label class="custom-control-label" for="customSwitch6">驚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;悚</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch7" value="益智">
						  <label class="custom-control-label" for="customSwitch7">益&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;智</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch8" value="限制級">
						  <label class="custom-control-label" for="customSwitch8">限&nbsp;&nbsp;制&nbsp;級</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch9" value="策略模擬">
						  <label class="custom-control-label" for="customSwitch9">策略模擬</label>
						</div>
						<div class="custom-control custom-checkbox">
						  <input type="checkbox" class="custom-control-input" id="customSwitch10" value="角色扮演">
						  <label class="custom-control-label" for="customSwitch10">角色扮演</label>
						</div>
					</div>
				</div>

			</div>
			<div class="col-9 column">
				<div class="container">
					<div class="row bg-rgb220 ">
						<div class="col-6 align-self-center">
							篩選
							<button id="hotSale" class="btn btn-light selectBarBtn">最熱銷</button>
							<button id="fromHigher" class="btn btn-light selectBarBtn">最高價</button>
							<button id="fromLower" class="btn btn-light selectBarBtn">最低價</button>

						</div>
						<div class="col-6 align-self-center">
							<ul class="pagination justify-content-center" id="pageUl"></ul>
						</div>
					</div>
				</div>

				<div class="container">
					<div class="row" id="resultsTable"></div>
				</div>
			</div>
		</div>
	</div>
	
<!-- FOOTER -->
	<jsp:include page="footer.jsp" />


	<script type="text/javascript">
//-----------------*********變數區*********-------------------
	var u = window.sessionStorage.getItem("loginUser");
	var pageItem = 8;
	var array;
	var oriArray;
	var filterType=[];
	var user = {
			checkUser : function() {
				if (u == "") {
					alert("尚未登入會員");
				}
			}
		}
//-----------------*********測試區*********-------------------


	



//-----------------*********初始化區*********-------------------
		window.onload = function() {
			console.log(window.sessionStorage.getItem("loginUser"));
			//user.checkUser();
			// 			 user.getRankId();
			// 			 user.getUserId();
			//swich();
			//  			console.log("switch");

			// 				ps();
			//  			console.log("ps");
		}
//-----------------******************觸發按鈕區******************-------------------	
//-----------------控制模擬框顯示函數-------------------
//沒效果 控制model 呼叫前或後的函數
	$("#d1").on("show.bs.modal", function(e) {
		console.log('顯示視窗前呼叫');
	});
//關閉模擬框  關閉影片
	$('#d1').on('hidden.bs.modal fade', function(e) {
		$('#youtubeX').remove();
		console.log('關閉視窗前呼叫');
	})

//-----------------控制過濾器-------------------
	$(document).on('change','.custom-control-input',function(){
			  
			  if ($(this).is(':checked')) {
			    filterType.push($(this).attr('value'));
			    filterTypeArray(filterType,array);
			  } else {
			    filterType.splice(filterType.indexOf($(this).attr('value')),1);
			    if(filterType.length==0){
			    	filterTypeArray(filterType,array);
				    }else{
			    filterTypeArray(filterType,array);
				    }
			  }
 			  
			console.log("動作");
			console.log(filterType);
		})

//-----------------滑鼠移入移出_點擊圖片跑出影片-------------------	
$(document).on("mouseenter","#videoDiv",function(){	
		$("[data-toggle='popover']").popover('show'); 
	
		console.log("移入");
		});
	$(document).on("mouseout","#videoDiv",function(){	
		$("[data-toggle='popover']").popover('hide'); 
	
		console.log("移出");
		});
//-----------------排序按鈕_熱銷-------------------	

		$(document).on('click', "#hotSale", function() {
// 			resetFilter();
			console.log(array);
			for (var i = 0, l = oriArray.length; i < l; ++i) {
				oriArray = oriArray.sort(function(a, b) {
					return a.searchFreq < b.searchFreq ? 1 : -1;
				});
			}
			console.log(oriArray);
			showCard(oriArray);

		})
//-----------------排序按鈕_從高價-------------------	
		$(document).on('click', "#fromHigher", function() {
// 			resetFilter();
			console.log(array);
			for (var i = 0, l = oriArray.length; i < l; ++i) {
				oriArray = oriArray.sort(function(a, b) {
					return a.productPrice < b.productPrice ? 1 : -1;
				});
			}
			console.log(oriArray);
			showCard(oriArray);

		})
//-----------------排序按鈕_從低價-------------------	
		$(document).on('click', "#fromLower", function() {
// 			resetFilter();
			console.log(array);
			for (var i = 0, l = oriArray.length; i < l; ++i) {
				oriArray = oriArray.sort(function(a, b) {
					return a.productPrice > b.productPrice ? 1 : -1;
				});
			}
			console.log(oriArray);
			showCard(oriArray);
			

		})
//-----------------分頁切換按鈕-------------------	
		$(document).on('click', ".pageBnt", function() {
			let toPage = this.id - 1;
			if(filterType.length>0){
				$("#resultsTable").html(changePage(oriArray, toPage));
				}
			else{
			$("#resultsTable").html(changePage(array, toPage));
				}
			
		})
//-----------------模擬框  輸入數量改變-------------------	
		$(document).on('change', '#quantity_input', function() {
			if ($("#quantity_input").val() < 1) {
				$("#quantity_input").val(1);
			}
			var num = $("#quantity_input").val();
			var price = $('#oriPrice').text();

			$("#tprice").html(price * num);

		})
//-----------------模擬框  輸入數量改變_加號-------------------	
		$(document).on('click', '#plus', function() {
			$("#quantity_input").val(parseInt($("#quantity_input").val()) + 1)
			var num = $("#quantity_input").val();
			var price = $('#oriPrice').text();

			$("#tprice").html(price * num);

		})
//-----------------模擬框  輸入數量改變_減號-------------------	
		$(document).on(
				'click',
				'#noplus',
				function() {
					if ($("#quantity_input").val() > 1) {
						$("#quantity_input").val(
								parseInt($("#quantity_input").val()) - 1)
					}
					var num = $("#quantity_input").val();
					var price = $('#oriPrice').text();

					$("#tprice").html(price * num);

				})

//-----------------模擬框  顯示商品細節-------------------	
		$(document).on('click', '#productDetail', function() {
			console.log(swich);
			var $img = $(this).find('img');
			var s = $img.attr("alt");
			var a = JSON.parse(s);
			$('#d4').html(showprodetail(a));

		})
//-----------------模擬框  顯示商品影片-------------------	
		$(document)
				.on(
						'click',
						'#youtube',
						function() {
							var a = $('#youtube').attr("alt");
							var video = JSON.parse(a);
							console.log(a);
							$('#youtube').attr({
								id : "youtube1"
							});

							$('#mbody')
									.append(
											"<div id='youtubeX'>"
													+ "<iframe id='video' src='" + video + "' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></div>");

						})
//-----------------模擬框  切換格式  (影片/介紹)-------------------	
		$(document).on('click', '#youtube1', function() {
			$('#youtube1').attr({
				id : "youtube"
			});
			$('#youtubeX').remove();

		})
//-----------------切換商品類型  (SWITCH/PS4/XBOX)-------------------	
		$(document).on('click', '#swp', function() {
			resetFilter();
			array = swich();
		})
		$(document).on('click', '#psp', function() {
			resetFilter();
			array = ps();
		})
		$(document).on('click', '#xboxp', function() {
			resetFilter();
			array = xbox();
		})
//-----------------商品加入購物車-------------------	
		$(document).on('click', '#addProduct1', function() {

			var a = $('#quantity_input').val();
			var b = $('#itemdetail').text();
			var b1 = JSON.parse(b)
			b1.amount = a;
			b = JSON.stringify(b1);
			console.log(b);
			var user;
			if (u == "") {
				console.log("localStorage.length");
				console.log(localStorage.length);
				let i = localStorage.length + 1;
				b1['lsId'] = i;
				b = JSON.stringify(b1);
				console.log(b);
				localStorage.setItem(i, b);
				alert("放入成功");
			} else {
				user = JSON.parse(u);
				var userId = user.userId;
				console.log("userId");
				console.log(userId);
				$.ajax({
					async : false,
					url : "shopping/addProduct",
					dataType : "json",
					type : "POST",
					data : {
						userId : userId,
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
//-----------------切換頁面_訂單-------------------	

		$(document).on('click', '#order', function() {
			location.assign("orderPage");

		})
//-----------------切換頁面_購物車-------------------	
		$(document).on('click', '#shopcart', function() {
			location.assign("shoppingCartPage");

		})
		
//-----------------*********function*********-------------------
//-----------------切換switch-------------------
		function swich() {
									var type = "switch";
									var array;
									$.ajax({
												async: false,
												url : "shopping/switchProduct",
												datatype : "json",
												type : "POST",
												data : {
													type : type
												},
												success : function(response) {
													console.log(response);
													showCard(response);
													numPage(response);
													array=response;
// 													console.log("asdasd");
// 													console.log(array);
													//showtable(response);
												},
												complete : function() {
													

												}
											});
				
									return array;
		}
//-----------------切換PS-------------------
		function ps() {
			var type = "PS";
			var array;
			$.ajax({
						async: false,
						url : "shopping/switchProduct",
						datatype : "text",
						type : "POST",
						data : {
							type : type
						},
						success : function(response) {

							var response = JSON.parse(JSON
									.stringify(response));
							console.log(response);
							showCard(response);
							numPage(response);
							array=response;
							//showtable(response);
						},
						complete : function() {
						

						}
					});
			return array;
		}
//-----------------切換XBOX-------------------
		function xbox() {
			var type = "xbox";
			var array;
			$.ajax({
						async: false,
						url : "shopping/switchProduct",
						datatype : "text",
						type : "POST",
						data : {
							type : type
						},
						success : function(response) {
							console.log(response);
							var response = JSON.parse(JSON
									.stringify(response));
							showCard(response);
							numPage(response);
							array=response;
							//showtable(response);
						},
						complete : function() {
						

						}
					});
			return array;

		}
//-----------------顯示分頁數量-------------------
		function numPage(response) {
			var pageNum = Math.ceil(response.length / pageItem);

			var pageTxt = "";
			for (i = 1; i <= pageNum; i++) {
				pageTxt += '<li class="pageLi"><button id="'+i+'" class="btn btn-light pageBnt">'
						+ i + '</button></li>'
			}
			$("#pageUl").html(pageTxt);
		}
//-----------------切換分頁-------------------
		function changePage(array, toPage) {

			console.log(array)
			cardResults = "";

			for (i = 0 + pageItem * toPage; i < pageItem + pageItem * toPage; i++) {
				if (i < array.length) {
					var s = {
						productId : array[i].productId,
						productVideo : array[i].productVideo,
						productName : array[i].productName,
						productImg : array[i].productImg,
						productType : array[i].productType,
						inventory : array[i].inventory,
						productPrice : array[i].productPrice,
						productTag : array[i].productTag,
						productInfo : array[i].productInfo,
						amount : 1
					}

					cardResults += '<div class="card cardSize" id="productDetail" role="button" tabindex="0"aria-pressed="true" data-toggle="modal"data-target="#d1">';
					cardResults += "<img class='card-img-top' src='"
							+ array[i].productImg + "'alt='"
							+ JSON.stringify(s) + "'>"; //商品圖片
					cardResults += '<div class="card-body">';
					cardResults += '<h5 class="card-title cardsdd">'
							+ array[i].productName + '</h5>'; //商品標題
					cardResults += '<h6 class="card-subtitle mb-2 text-muted">NT$'
							+ array[i].productPrice + '</h6>'; //商品價格

					cardResults += '</div></div>';
				}
			}
			return cardResults;

		}
//-----------------顯示商品 (table模式)-------------------
		// 		function showtable(response) {
		//			var txt = "<tr><th>#<th>商品照片<th>商品名稱";

		//			for (let i = 0; i < response.length; i++) {
		//				var s = {
		//					productId : response[i].productId,
		//					productVideo : response[i].productVideo,
		//					productName : response[i].productName,
		//					productImg : response[i].productImg,
		//					productType : response[i].productType,
		//					inventory : response[i].inventory,
		//					productPrice : response[i].productPrice,
		//					productTag : response[i].productTag,
		//					productInfo : response[i].productInfo,
		//					amount : 1
		//				};
		//				//var a=JSON.stringify(s);
		//				//console.log("a:"+a);
		//				txt += "<tr><td>" + response[i].productId;
		//				txt += "<td id='img'><span id='productDetail' role='button' tabindex='0'aria-pressed='true' data-toggle='modal'data-target='#d1'> <img src='"
		//						+ response[i].productImg
		//						+ "' alt='"
		//						+ JSON.stringify(s) + "'></span>";
		//				txt += "<td>" + response[i].productName;

		//			}
		//			$('#t1').html(txt);

		//		}
//-----------------顯示商品細節  (模擬框)-------------------
		function showprodetail(response) {
			var txt = "";
			txt += "<div ><button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>X</span></button></div>"
			txt += "<div id='videoDiv' data-container='body' data-toggle='popover' data-placement='bottom' data-content='點擊照片能顯示遊戲影片'>";
			txt += "<img id='youtube'role='button' tabindex='0' aria-pressed='true' alt='"
					+ JSON.stringify(response.productVideo)
					+ "' src='"
					+ response.productImg + "'></div>";
			txt += "<div style='float: right;width:400px;height:450px;margin:6px 0px 10px 0px;' ><div class='modal-header'><h5 class='modal-title'>"
					+ response.productName + "</h5></div>";
			txt += "<div id='mbody' style='height:330px;'class='modal-body'>";
			txt += "<p class='card-text'>" + response.productInfo + "</p>";
			txt += "<p class='card-text'>" + response.productTag + "</p>";
			txt += "</div>";
			txt += "<div style='margin-bottom:0px;align:left;' class='modal-footer'>";
			txt += "<span>數量:<button id='noplus' type='button' class='btn btn-outline-secondary btn-sm'>-</button>";
			txt += "<input style='width: 40px;' id='quantity_input' class='inputNum' readonly type='text' value='1'>";
			txt += "<button id='plus' type='button' class='btn btn-outline-secondary btn-sm'>+</button></span>";
			txt += "金額:<span id='oriPrice' style='display:none'>"
					+ response.productPrice + "</span>"
					+ "<p id='tprice' class='card-text'>"
					+ response.productPrice + "</p></div>";
			txt += "</div>";
			txt += "<div style='width:760px;'class='modal-footer'><span id='itemdetail' style='display:none'>"
					+ JSON.stringify(response)
					+ "</span>"
					+ "<button id='addProduct1' type='button' class='btn btn-primary' data-dismiss='modal'>加入購物車</button></div>";
			return txt;

		}
//-----------------顯示商品  (Card模式)-------------------
		function showCard(response) {
			var cardResults = "";
			for (let i = 0; i < response.length; i++) {
				var s = {
					productId : response[i].productId,
					productVideo : response[i].productVideo,
					productName : response[i].productName,
					productImg : response[i].productImg,
					productType : response[i].productType,
					inventory : response[i].inventory,
					productPrice : response[i].productPrice,
					productTag : response[i].productTag,
					productInfo : response[i].productInfo,
					amount : 1
				}

				cardResults += '<div class="card cardSize" id="productDetail" role="button" tabindex="0"aria-pressed="true" data-toggle="modal"data-target="#d1">';
				cardResults += "<img class='card-img-top' src='"
						+ response[i].productImg + "'alt='" + JSON.stringify(s)
						+ "'>"; //商品圖片
				cardResults += '<div class="card-body">';
				cardResults += '<h5 class="card-title cardsdd">'
						+ response[i].productName + '</h5>'; //商品標題
				cardResults += '<h6 class="card-subtitle mb-2 text-muted">NT$'
						+ response[i].productPrice + '</h6>'; //商品價格
				//     cardResults += '<p class="card-text">'+jsonResults[i].productInfo+'</p>'								 //商品介紹e
				cardResults += '</div></div>';
			}
			$("#resultsTable").html(cardResults);
		}
		
//-----------------過濾條件-------------------		
		function filterTypeArray(filterType,array){	
//	 		console.log(filterType.length);
//	 		console.log(array);
			oriArray= array;
			for (var j=0;j<filterType.length;j++){
				for (var i = 0; i< array.length; i++) {
					oriArray = oriArray.filter(function(item){
						console.log(item.productTag);
						console.log(filterType[j]);
						if((item.productTag.search(filterType[j]))!=-1){
							return item;
							}
						    
					});
				}
			}

			console.log(oriArray);
			numPage(oriArray);
			showCard(oriArray);
			}
//-----------------清空過濾器選項-------------------
		function resetFilter(){
			filterType.length=0;
			$('.custom-control-input').prop("checked",false)

			}
			
//-----------------??-------------------
		var jsonResults = JSON.parse(JSON.stringify(${ results }));
		console.log(jsonResults);
		showCard(jsonResults);
		numPage(jsonResults);
		array = jsonResults;
		
	</script>

</body>

</html>