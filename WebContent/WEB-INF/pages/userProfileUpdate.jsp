<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserProfileCreate</title>
</head>
<body>
	<h4>請填入您欲修改的資料</h4>
	<form action="<c:url value="/updateProfileAct"/>" method="post">
		Name:<input type="text" name="name" value=""/><br>
		Nickname:<input type="text" name="nickname" value=""/><br>
		Gender:${userProfile.gender}<br>
		Address:<input type="text" name="address"/><br>
		Phone:<input type="text" name="phone"/><br>
		image:<input type="text" name="img"/><br>
		<input type="submit">
	</form>
</body>
</html>