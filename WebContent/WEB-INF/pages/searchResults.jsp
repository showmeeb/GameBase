<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.cardSize {
	width: 24.70%;
	margin: 1px;
	cursor: pointer;
}

.center {
	margin: auto;
}

.bg-rgb220 {
	background-color: rgb(220, 220, 220);
	margin-bottom: 10px;
}

.bg-rgb245 {
	background-color: rgb(245, 245, 245);
}

.card-title {
	font-size: 17px;
}

.card-subtitle {
	font-size: 13px;
}

.card-img-top {
	height: 250px;
}

.selectBarBtn {
	height: 99%;
	width: 90px;
}

.pageBnt {
	height: 99%;
	border: none;
}

.pageLi {
	margin-right: 3px;
}
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


	<script>

        var jsonResults = JSON.parse(JSON.stringify(${ results }));
		var cardResults = "";
		var pageItem=6;
		
		var pageNum = Math.ceil(jsonResults.length/pageItem); 

        for (i = 0; i < pageItem; i++) {
        	if(i<jsonResults.length){        		   
	           cardResults += '<div class="card cardSize" id="'+jsonResults[i].productId+'">';
	           cardResults += '<img class="card-img-top" src="'+jsonResults[i].productImg+'">'  					 //商品圖片
	           cardResults += '<div class="card-body">'
	           cardResults += '<h5 class="card-title cardsdd">'+jsonResults[i].productName+'</h5>' 				     //商品標題
	           cardResults += '<h6 class="card-subtitle mb-2 text-muted">NT$'+jsonResults[i].productPrice+'</h6>'	 //商品價格
		//     cardResults += '<p class="card-text">'+jsonResults[i].productInfo+'</p>'								 //商品介紹e
			   cardResults += '</div></div>'  
        }}
        $("#resultsTable").append(cardResults);

        
		var pageTxt = "";
		for (i=1;i<=pageNum;i++){
		pageTxt += '<li class="pageLi"><button id="'+i+'" class="btn btn-light pageBnt">'+i+'</button></li>'
		}
	    $("#pageUl").append(pageTxt);
		

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
		 	//      cardResults += '<p class="card-text">'+jsonResults[i].productInfo+'</p>'							 //商品介紹
		 		    cardResults += '</div></div>'  
	         }}
	         $("#resultsTable").html(cardResults);
			
		})
        
       // for(i=0;i){console.log(productId)}
		

        
        $(document).on('click','.card',function(){
            for (i in jsonResults) {
                if (jsonResults[i].productId == this.id) {
                    
                }
              }
        })
    </script>


</body>
</html>