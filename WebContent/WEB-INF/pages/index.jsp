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
This is index
<a href="<c:url value="/loginact"/>"></a>
<a href="<c:url value="/tradesystem"/>">登記商品</a><br>
<a href="<c:url value="/mainProduct"/>">商品管理</a><br>
</body>
</html>