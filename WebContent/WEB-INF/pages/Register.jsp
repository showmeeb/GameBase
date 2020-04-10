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
<h4>Register</h4>
<form action="<c:url value="/registact"/>" method="POST">
	Account:<input type="text" name="account"/><br><div>${accerr}</div>
	Password:<input type="text" name="password"/><br><div>${pwderr}</div>
	email:<input type="text" name="email"/><br><div>${emailerr}</div>
	<input type="submit" value="submit"/>
</form>
</body>
</html>