<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
	
</script>
<meta charset="UTF-8">
<style>
.barSpace {
	width: 100%;
	height: 100px;
	padding: 5px;
}

#resultsDiv {
	width: auto;
	display: table;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid black;
}

td {
	text-align: center;
}

img {
	width: 200px;
	height: 260px;
}
</style>
</head>
<body>

	<jsp:include page="topBar.jsp" />

	<div class="barSpace"></div>
	<div id=resultsDiv>
		<table id=resultsTable></table>
	</div>

   <script>
        $("#looking").val('${looking}');

        var jsonResults = JSON.parse(JSON.stringify(${ results }));
        var txt = "";
        var listNumber = Math.ceil(jsonResults.length / 5);

        for (i = 0; i < jsonResults.length; i++) {
            if (i % 5 == 0) { txt += "<tr>" }
            txt += "<td><a href='/GameBase/productDetail?prodId="+jsonResults[i].productId+"'><img src='" + jsonResults[i].productImg + "'>";  
            txt += "<div>" + jsonResults[i].productName + "</div>";
            txt += "<div>" + jsonResults[i].productPrice + "</div></a>";
        }
        $(resultsTable).append(txt);

    </script>


</body>
</html>