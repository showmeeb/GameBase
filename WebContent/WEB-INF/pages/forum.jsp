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

	<!-- new forum -->
		<tr>
			<td>${newForum.id}</td>
			<td><a href="<c:url value="/forum/${newForum.forumName}"/>">${newForum.forumName}</td>
			<td>${newForum.forumFigure}</td>
		</tr>
	</table>

	<!-- insert new forum  -->
	<hr>
	<p>insert new forum</p>
	<form action="<c:url value="/forum/add"/>" method="post">
		<table>
			<tr>
				<td>Forum Name:</td>
				<td><input type="text" name="forumName" /></td>
			</tr>
			<tr>
				<td>Forum Figure:</td>
				<td><input type="text" name="forumFigure" /></td>
			</tr>
		</table>
		<input type="submit" value="submit" />
	</form>
</body>
</html>