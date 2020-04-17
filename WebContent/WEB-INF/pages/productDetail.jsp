<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<c:url value="/css/product_responsive.css"/>"
	rel="stylesheet">
<link href="<c:url value="/css/product.css"/>" rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>

<style>
.barSpace {
	width: 100%;
	height: 100px;
	padding: 5px;
}
</style>



</head>
<body>

	<jsp:include page="topBar.jsp" />

	<div class="barSpace"></div>

	<div class="product_details">
		<div class="container">
			<div class="row details_row">

				<!-- Product Image -->
				<div class="col-lg-6">
					<div class="details_image">
						<div class="details_image_large" id="details_image_large"></div>
						<div
							class="details_image_thumbnails d-flex flex-row align-items-start justify-content-between">

						</div>
					</div>
				</div>

				<!-- Product Content -->
				<div class="col-lg-6">
					<div class="details_content">
						<div class="details_name" id="details_name"></div>

						<div class="details_price" id="details_price">價格：</div>

						<!-- In Stock -->
						<div class="in_stock_container">
							<div class="availability">還剩下：</div>
							<span id="inventory"></span>
						</div>
						<div class="details_text">
							<p id="details_text"></p>
						</div>

						<!-- Product Quantity -->
						<div class="product_quantity_container">
							<div class="product_quantity clearfix">
								<span>數量</span> <input id="quantity_input" type="text"	disabled="disabled" value="1">
								<div class="quantity_buttons">
									<div id="quantity_inc_button"
										class="quantity_inc quantity_control" id="quantity_inc_button">
										+<i class="fa fa-chevron-up" aria-hidden="true"></i>
									</div>
									<div id="quantity_dec_button"
										class="quantity_dec quantity_control">
										-<i class="fa fa-chevron-down" aria-hidden="true"></i>
									</div>
								</div>
							</div>
							<div class="button cart_button">
								<a href="#">加入購物車</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
var jsonResults = JSON.parse(JSON.stringify(${product}));

$("#details_name").append(jsonResults.productName);
$("#details_price").append(jsonResults.productPrice);
$("#inventory").append(jsonResults.inventory+"件");
$("#details_text").append(jsonResults.productInfo);
$("#details_image_large").append("<img src='"+jsonResults.productImg+"'>");

$("#quantity_inc_button").click(function () {
	if ($("#quantity_input").val() < parseInt(jsonResults.inventory)) {
		$("#quantity_input").val(parseInt($("#quantity_input").val()) + 1)
	}
})

$("#quantity_dec_button").click(function () {
	if ($("#quantity_input").val() > 1 ) {
		$("#quantity_input").val(parseInt($("#quantity_input").val()) - 1)
	}
})
</script>


</body>
</html>