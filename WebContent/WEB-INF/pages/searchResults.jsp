<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

</style>
</head>
<body>

	<jsp:include page="topBar.jsp" />


	<div class="container">
		<div class="row">
			<div class="col-3 column text-center">ps、switch、pc</div>
			<div class="col-9 column">
				<div class="container">
					<div class="row bg-rgb220 ">
						<div class="col-6 align-self-center">
							篩選
							<button class="btn btn-light selectBarBtn">最熱銷</button>
							<button class="btn btn-light selectBarBtn">最高價</button>
							<button class="btn btn-light selectBarBtn">最低價</button>

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
	<jsp:include page="footer.jsp" />

	<!-- 商品細節彈出視窗 -->
	<div class="modal" tabindex="-1" role="dialog" id="productDetial">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">商品標題</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">×</button>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-6 column gray-boder">
								<img src="https://i.imgur.com/Nt5Bn7u.png"
									class="modal-width theImg">
							</div>
							<div class="col-6 column gray-boder">
								<div class="row row-height thePrice">這邊放價格</div>
								<div
									class="row row-height align-items-end justify-content-center">
									<button class="btn-warning number-btn decrease">-</button>
									<span class="buy-quantity text-center">1</span>
									<button class="btn-warning number-btn increase">+</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary bottom-bnt"
						data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary bottom-bnt">加入購物車</button>
				</div>
			</div>
		</div>
	</div>


	<script>

		//解析資料
        var jsonResults = JSON.parse(JSON.stringify(${ results }));
		var cardResults = "";
		var pageItem=8; //每頁商品數量設定
		
		var pageNum = Math.ceil(jsonResults.length/pageItem); 

		//初始化填入第一頁資料
        for (i = 0; i < pageItem; i++) {
        	if(i<jsonResults.length){        		   
	           cardResults += '<div class="card cardSize" id="'+jsonResults[i].productId+'">';
	           cardResults += '<img class="card-img-top" src="'+jsonResults[i].productImg+'">'  					 //商品圖片
	           cardResults += '<div class="card-body">'
	           cardResults += '<h5 class="card-title cardsdd">'+jsonResults[i].productName+'</h5>' 				     //商品標題
	           cardResults += '<h6 class="card-subtitle mb-2 text-muted">NT$'+jsonResults[i].productPrice+'</h6>'	 //商品價格
			   cardResults += '</div></div>'  
        }}
        $("#resultsTable").append(cardResults);

        
        //創建分頁按鈕
		var pageTxt = "";
		for (i=1;i<=pageNum;i++){
		pageTxt += '<li class="pageLi"><button id="'+i+'" class="btn btn-light pageBnt">'+i+'</button></li>'
		}
	    $("#pageUl").append(pageTxt);
		

	    //分頁運作邏輯
		$(".pageBnt").click(function(){
			let toPage=this.id-1;
			cardResults = "";
			
	        for (i = 0+pageItem*toPage; i < pageItem+pageItem*toPage; i++) {
	        	if(i<jsonResults.length){
	        		cardResults += '<div class="card cardSize" id="'+jsonResults[i].productId+'">';
		            cardResults += '<img class="card-img-top" src="'+jsonResults[i].productImg+'">'  					 //商品圖片
		            cardResults += '<div class="card-body">'
		            cardResults += '<h5 class="card-title cardsdd">'+jsonResults[i].productName+'</h5>' 				 //商品標題
		            cardResults += '<h6 class="card-subtitle mb-2 text-muted">NT$'+jsonResults[i].productPrice+'</h6>'	 //商品價格
		 		    cardResults += '</div></div>'  
	         }}
	         $("#resultsTable").html(cardResults);
			
		})
        
		

        //彈出視窗
        $(document).on('click','.card',function(){
            for (i in jsonResults) {
                if (jsonResults[i].productId == this.id) {
                	$(".modal-title").html(jsonResults[i].productName);
                	$(".thePrice").html(jsonResults[i].productPrice);
                	$(".theImg").attr('src',jsonResults[i].productImg);
                	$(".buy-quantity").html(1);
                	
                	$('#productDetial').modal('show');
                }
              }
        })
        
        //彈出視窗-購物數量增減
        $(".number-btn").click(function(){
        	if($(this).hasClass("increase")&&$(".buy-quantity").html()<9){
        		$(".buy-quantity").html(parseInt($(".buy-quantity").html())+1)
        		
        	}else if($(this).hasClass("decrease")&&$(".buy-quantity").html()>1){
        		$(".buy-quantity").html(parseInt($(".buy-quantity").html())-1)
        	}
        })
    </script>


</body>
</html>