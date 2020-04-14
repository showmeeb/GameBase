<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.barSpace {
	width: 100%;
	height: 100px;
	padding: 5px;
}
</style>

</head>
<body>
<div class="barSpace"></div>

<script>
var jsonResults = JSON.parse(JSON.stringify(${product}));

console.log(jsonResults.productName);
</script>


</body>
</html>