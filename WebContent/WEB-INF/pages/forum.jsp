<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>this is forum list</title>
</head>
<body>

	<br />
	<!-- forum list -->
	<table>
		<c:forEach items="${forumList}" var="item" varStatus="itemStatus">
			<tr>
				<td>${item.id}</td>
				<td><a href="<c:url value="/forum/${item.forumName}"/>">${item.forumName}</td>
				<td>${item.forumFigure}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>