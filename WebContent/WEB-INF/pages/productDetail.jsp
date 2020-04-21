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

</style>



</head>
<body>


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
						


	<script>

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