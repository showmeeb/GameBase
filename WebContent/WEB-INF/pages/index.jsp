<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	This is index

	<form action="<c:url value="/loginact"/>" method="Post">
		Account:<input type="text" name="account"><br>
		<div>${accerr}</div>
		<br> Password:<input type="password" name="password"><br>
		<div>${pwderr}</div>
		<br> <input type="submit">
	</form>
	<a href="<c:url value="/goregister"/>">註冊</a>
	<br>
	<br>
	<a href="<c:url value="/tradesystem"/>">登記商品</a>
	<a href="<c:url value="/mainProduct"/>">商品管理</a>
	<br>
	<a href="<c:url value="/goMsgBoard"/>">/goMsgBoard</a>
	<br />

</body>
</html>