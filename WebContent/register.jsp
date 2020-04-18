<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Register</h1>
<form action="<c:url value="/registact"/>" method="POST">
	Account:<input type="text" name="account" maxlength="20"/>${accerr}<br>
	Password:<input type="text" name="password"/>${pwderr}<br>
	email:<input type="text" name="email"/>${emailerr}<br>
	<input type="submit" value="submit"/>${registererr}
</form>
</body>
</html>