<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.cardSize {
	width: 24.99%;
	height: margin:1px;
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
	width: 90px;
	height: 40px;
	margin: 5px 2px 5px 2px;
}
</style>
</head>
<body>

	<jsp:include page="topBar.jsp" />

	<div class="container ">
		<div class="row">
			<div class="col-3 column text-center">ps、switch、pc</div>
			<div class="col-9 column">
				<div class="container">
					<div class="row bg-rgb220 "> 
						<div class="col-10 align-items-center">
							篩選
							<button class="btn btn-light selectBarBtn">最熱銷</button>
							<button class="btn btn-light selectBarBtn">最高價</button>
							<button class="btn btn-light selectBarBtn">最低價</button>

						</div>
						<div class="col-2 text-right">- [1] +</div>
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
		var txt = "";
 

        for (i = 0; i < jsonResults.length; i++) {
   
           txt += '<div class="card cardSize">';
           txt += '<img class="card-img-top" src="'+jsonResults[i].productImg+'">'  					 //商品圖片
	       txt += '<div class="card-body">'
	       txt += '<h5 class="card-title cardsdd">'+jsonResults[i].productName+'</h5>' 							 //商品標題
	       txt += '<h6 class="card-subtitle mb-2 text-muted">NT$'+jsonResults[i].productPrice+'</h6>'	 //商品價格
	//     txt += '<p class="card-text">'+jsonResults[i].productInfo+'</p>'								 //商品介紹
		   txt += '</div></div>'  
        }
        $("#resultsTable").append(txt);

    </script>


</body>
</html>