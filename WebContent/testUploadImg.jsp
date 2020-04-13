<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

	<form action="<c:url value="/uploadImg"/>" method="POST"
		enctype="multipart/form-data">
		<div>
			<input type="file" name="theFile" >
		</div>
		<div>
			<input type="submit" value="sned">
		</div>
	</form>
</body>
</html>