<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">


</head>

<body>
	<!-- top bar -->
	<jsp:include page="topBar.jsp" />
	

	<!-- forum title bar -->
<div class="article_create_btn">
	<nav class="navbar navbar-expand-sm bg-light forum_topbar">
		<ul class="nav justify-content-end">
			<!-- update article button -->
			<li class="nav-item">
				<a id="publish-btn" class="btn_update_forum" href="javascript:void(0)">
					<i class="far fa-edit fa-2x"></i>
				</a>
			</li>

		</ul>
	</nav>
</div>

<div class="article_window">
	
	<div class="article_forumName">
	<h1>${forum.forumName}</h1>
	</div>
	
	<div id="titleList" class="article_list">
		<c:if test="${articleList==null}">
		<!-- 沒文章時 顯示甚麼? 可加東西 -->
		</c:if>

		<c:if test="${articleList!=null}">
			<c:forEach items="${articleList}" var="item" varStatus="itemStatus">
				<div class="article_article">
				
					<div class="article_name">
						<h2>
							<a href="<c:url value="/forum_test/${forumId}/${item.titleId}"/>">${item.titleName}</a>
						</h2>
					</div>
					
					<div class="article_content">
					
						<!-- has first img -->
						<c:if test="${not empty item.firstFigure}">
						<div class="article_content_img">						
						<img alt="圖片提示字串" src="${item.firstFigure}" height="100">
						</div>
						</c:if>
						
						<div class="article_content_content">
							<span>${item.content}</span>
						</div>
					</div>
					
					
					<!-- icons input and time record -->
					<div class=" article_datas">
						<!-- like -->
						<div class="article_icons">
							<i class="far fa-thumbs-up fa-2x">${item.likeNum}</i>
						</div>
						<!-- unlike -->
						<div class="article_icons">
							<i class="far fa-thumbs-down fa-2x">${item.unlikeNum}</i>
						</div>
						<!-- click -->
						<div class="article_icons">
							<i class="far fa-eye fa-2x">${item.clickNum}</i>
						</div>
						<!-- create time -->
						<div class="article_time_record">
							<i class="fas fa-pen-alt fa-2x"></i>Post Time:${item.createTime}
						</div>
						<!-- last reply time -->
						<div class="article_time_record">
							<i class="far fa-comment-dots fa-2x"></i>Last Reply:${item.lastReplyTime}
						</div>
					</div>
					
				</div>
			</c:forEach>

		</c:if>
		
	</div>	
	
</div>
	<!-- update article button -->
<!-- 	<div class="article_icons"> -->
<!-- 						<i class="fas fa-edit fa-2x"></i><br />  -->
<!-- 		<a id="publish-btn" class="btn_update_forum" href="javascript:void(0)"><i -->
<!-- 			class="far fa-edit fa-2x"></i></a><br /> -->
<!-- 	</div> -->
	<!-- create article window -->
	<%@ include file="include/article_editor.jsp"%>
	


	
	
<script type="text/javascript">
var url = '<c:url value="/forum_test/${forum.forumId}/add"/>';
var lo = 'title';
var forumId = ${forum.forumId};
</script>
</body>
</html>