<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

<form action="<c:url value="/loginact"/>" method="POST">
	Account:<input type="text" name="account">${requestScope.accerr}
	<br>
	Password:<input type="password" name="password">${requestScope.pwderr}
	<br>
	<input type="submit" value="Login">${requestScope.loginerr}
</form>
</body>
</html>