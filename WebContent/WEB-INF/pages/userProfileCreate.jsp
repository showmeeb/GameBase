<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserProfileCreate</title>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<body>

<c:if test="${!empty ProfileId}">
<form action="<c:url value="/saveProfile"/>" method="POST">
	<input type="hidden" name="userId" value="${userProfile.userId}">
	Name:<input type="text" name="name" value="${userProfile.name}"><br>
	Nickname:<input type="text" name="nickName" value="${userProfile.nickName}"><br>
	Gender:<input type="radio" name=gender value="m">男 <input type="radio" name=gender value="f">女${userProfile.gender}"<br>
	Address:<input type="text" name="address" value="${userProfile.address }"><br>
	Phone:<input type="text" name="phone" value="${userProfile.phone }"><br>
	Age:<input type="text" name="age" value="${userProfile.age }"><br>
	Image:<input type="text" name="img"><img src="${userProfile.img}"><br>
<input type="submit">
</form>
</c:if>
<c:if test="${empty ProfileId}">
<form action="<c:url value="/saveProfile"/>" method="POST">
	<input type="hidden" name="userId" value="${UserData.userId}">	
	Name:<input type="text" name="name"><br>
	Nickname:<input type="text" name="nickName"><br>
	Gender:<input type="radio" name=gender value="m">男 <input type="radio" name=gender value="f">女<br>
	Address:<input type="text" name="address"><br>
	Phone:<input type="text" name="phone"><br>
	Age:<input type="text" name="age"><br>
	Image:<input type="text" name="img"><br>
	<input type="submit">
</form>
</c:if>
</body>

</html>