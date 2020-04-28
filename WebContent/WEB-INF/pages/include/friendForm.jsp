<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="addfriend_${item.userId}"
	class="firends_area popup-window hidden-window">
	<i class="fas fa-times close-btn"></i>
	<h2>好友申請</h2>
	<!--user nickname and account-->
	<div>
		<c:if test="${empty item.img}">
			<img src="<c:url value="/img/userIcon.png"/>" width="100" height="100" />
		</c:if>
		<c:if test="${not empty item.img}">
			<img src=${item.img } alt="" width="100" height="100" /></c:if>
		<table>
			<tr>
				<td>帳號:</td>
				<td>${item.account}</td>
			</tr>
			<tr>
				<td>暱稱:</td>
				<td>${item.nickName}</td>
			</tr>
		</table>
		<!--  -->
		<c:forEach var="friends" items="${friends}">
			<c:if test="${friends.firendId==item.userId}">
			is friend
			</c:if>
		</c:forEach>

		<div class="btn_add_friends">
			<button id="add">新增好友</button>
		</div>
		<div class="btn_delete_firends">
			<button id="delete">刪除好友</button>
		</div>
	</div>
</div>
