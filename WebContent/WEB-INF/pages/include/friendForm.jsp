<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="">

	<h2>好友申請</h2>
	<!--user nickname and account-->
	<div>
		<img src="${contentList[i].img}" alt="" width="100" height="100" />
		<table>
			<tr>
				<td>帳號:</td>
				<td>${contentList[i].account}</td>
			</tr>
			<tr>
				<td>暱稱:</td>
				<td>${contentList[i].nickName}</td>
			</tr>
		</table>
		<!--  -->
		<c:forEach items="${friends}">
			<c:if test="${items.firendId==contentList[i].userId}">

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