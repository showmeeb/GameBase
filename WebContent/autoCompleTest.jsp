<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
</head>
<body>

	<div class="ui-widget">
		<label for="tags">Suggestions: </label>
		<input id="tags">
  	</div>

 	<script>
    var availableTags;
    $.ajax({
		url: '<c:url value="/autoComple"/>',
		type: "POST",
		success: function(data) {
			$("#tags").autocomplete({
				source: "${autoComple}"
			});
		}
    });    
  </script> 
	
</body>
</html>