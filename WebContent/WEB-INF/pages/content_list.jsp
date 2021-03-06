<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<style type="text/css">
a.disabled {
    pointer-events: none;
}
</style>
</head>
<body>
	<!-- top bar -->
	<jsp:include page="topBar.jsp" />
	
<%-- 	<c:if test="${loginUser.rankId>=2}"> --%>
<div class="content_reply_btn">
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
<%-- 	</c:if> --%>
	
	<div class="article_window" forumId="${forum.forumId}" titleId="${title.titleId}">
	
	<div class="article_forumName">
	<h1>${forum.forumName}</h1>
	</div>

	<c:if test="${empty contentList}">
		<p>there is no article</p>
	</c:if>

	<c:if test="${not empty contentList}">
			<div class="content_list">
							
				<c:forEach items="${contentList}" var="item" varStatus="itemStatus">
					<div contentId="${item.contentId}" userId="${item.userId}" class="content_unit">
					
					<!-- title -->
					<c:if test="${itemStatus.count==1 }">
					<div class="content_title">
					<h2>${title.titleName}</h2>
					</div>
					</c:if>
					
					<!-- user data and update/post time -->	
					<div class="content_author_data">				
					<!-- user img -->
					<div id="${item.userId}" class="content_author_img">
					
					<c:if test="${empty item.img}"><img src="<c:url value="/img/userIcon.png"/>" class="content_head"/></c:if>
					<c:if test="${not empty item.img}"><img src=${item.img} alt="" class="content_head"/></c:if>
					
					</div>
					<!-- user account -->
					<div class="content_author_info">
					<span>
					帳號ID   : ${item.userId}<br/>
					帳號名稱     : ${item.account}<br/>
					暱稱 	    : ${item.nickName}<br/>
					文章ID   : ${item.contentId}<br/>
					</span>
					</div>
					<!-- time -->
					<div class="content_time_record">
						<i class="fas fa-pen-alt"></i>發布時間 : ${item.createTime}<br />
					</div>
					<div class="content_time_record">
						<i class="fas fa-pen-alt"></i>更新時間 : ${item.updateTime}<br />
					</div>
					
					<!-- delete article button -->
					<div class="content_editor_btn" >
						<a btn="delete" class="btn_update_content" contentId="${item.contentId}" href="javascript:void(0)" hidden><i class="far fa-trash-alt fa-2x"></i></a>
					</div>
					<!-- update article button -->
					<div class="content_editor_btn" >
						<a btn="update" class="btn_update_content" contentId="${item.contentId}" href="javascript:void(0)" hidden><i class="far fa-edit fa-2x"></i></a>
					</div>					
					</div>
					
					<!-- content area -->
					<div contentId="${item.contentId}" class="content_content">
						${item.content}
					</div>
				
					<!-- icons input and time record -->
					<c:if test="${itemStatus.count==1 }">
					
					<div class="content_article_data">
					<!-- ajax link record btns -->
					
					<!-- user clicked like -->
					<c:if test="${record.record == 'like'}">
						<!-- like -->
						<div class="article_icons">
							<a id="like" class="btn_record" href="javascript:void(0)"><i class="fas fa-thumbs-up fa-2x">${title.likeNum}</i></a>
						</div>
						<!-- unlike -->
						<div class="article_icons">
							<a id="unlike" class="btn_record disabled" href="javascript:void(0)"><i class="far fa-thumbs-down fa-2x">${title.unlikeNum}</i></a>
						</div>
					</c:if>
					
					<!-- user clicked unlike -->
					<c:if test="${record.record == 'unlike'}">
						<!-- like -->
						<div class="article_icons">
							<a id="like" class="btn_record disabled" href="javascript:void(0)"><i class="far fa-thumbs-up fa-2x">${title.likeNum}</i></a>
						</div>
						<!-- unlike -->
						<div class="article_icons">
							<a id="unlike" class="btn_record" href="javascript:void(0)" disabled><i class="fas fa-thumbs-down fa-2x">${title.unlikeNum}</i></a>
						</div>
					</c:if>
					
					<!-- user cancel clicked or nothing -->
					<c:if test="${record.record == 'no' || empty record}">
						<!-- like -->
						<div class="article_icons">
							<a id="like" class="btn_record" href="javascript:void(0)"><i class="far fa-thumbs-up fa-2x">${title.likeNum}</i></a>
						</div>
						<!-- unlike -->
						<div class="article_icons">
							<a id="unlike" class="btn_record" href="javascript:void(0)"><i class="far fa-thumbs-down fa-2x">${title.unlikeNum}</i> </a>
						</div>
					</c:if>
					
					<!-- click -->
					<div class="article_icons">
						<i class="far fa-eye fa-2x">${title.clickNum}</i> 
					</div>
						
						<!-- create time -->
<!-- 						<div class="article_time_record"> -->
<%-- 							<i class="fas fa-pen-alt fa-2x"></i>Post Time : ${title.createTime}<br /> --%>
<!-- 						</div> -->
						<!-- last reply time -->
<!-- 						<div class="article_time_record"> -->
<%-- 							<i class="far fa-comment-dots fa-2x"></i>Last Reply : ${title.lastReplyTime}<br />  --%>
<!-- 						</div> -->
						
					</div>
					</c:if>
					
					</div>
					<!-- content end -->
					
				</c:forEach>
			</div>
	</c:if>
	</div>
	

<!-- create article window -->
<%@ include file="include/article_editor.jsp"%>
<script type="text/javascript">
var url = '<c:url value="/forum_test/${forum.forumId}/${title.titleId}/add"/>';
var lo = 'content';
</script>
</body>
</html>