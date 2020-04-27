<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>welcome to article page</title>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Font Awesome icons -->
<script src="https://kit.fontawesome.com/83bb506b46.js"
	crossorigin="anonymous"></script>
<!-- Bootstrap -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<!-- editor improt -->
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<!-- ckfinder import -->
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
<!-- main style -->
<link href="<c:url value="/css/style.css"/>" rel="stylesheet">
<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- create_article.js import -->
<script src="<c:url value="/js/create_article.js"/>"></script>

</head>

<body>
	<h1>主題：${forum.forumName}</h1>
	<br />
	<div id="titleList">
		<c:if test="${articleList==null}">
			<p>there is no article</p>
		</c:if>

		<c:if test="${articleList!=null}">

			<p>there is articles</p>
			<c:forEach items="${articleList}" var="item" varStatus="itemStatus">
				<div class="article">
					<div class="article_part">
						<h2>
							<a href="<c:url value="/forum_test/${forumId}/${item.titleId}"/>">${item.titleName}</a>
						</h2>
						<hr />
						<div class="article_img">
						<c:if test="${empty item.firstFigure}"><img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png" height="100" width="100"></c:if>
						<c:if test="${not empty item.firstFigure}"><img alt="圖片提示字串" src="${item.firstFigure}" height="200"></c:if>
						</div>
						<div class="article_content">
							<span>${item.content}</span><br />
						</div>
					</div>
					<!-- icons input and time record -->
					<div class="article_part article_datas">
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
							<i class="far fa-eye fa-2x">${item.clickNum}</i><br/>
						</div>
						<!-- create time -->
						<div class="article_time_record">
							<i class="fas fa-pen-alt fa-2x">Post Time:${item.createTime}</i><br/>
						</div>
						<!-- last reply time -->
						<div class="article_time_record">
							<i class="far fa-comment-dots fa-2x">Last Reply:${item.lastReplyTime}</i><br/> 
						</div>
					</div>
				</div>
			</c:forEach>

		</c:if>
	</div>	
	<!-- update article button -->
	<div class="article_icons">
		<!-- 				<i class="fas fa-edit fa-2x"></i><br />  -->
		<a id="publish-btn" class="btn_update_forum" href="javascript:void(0)"><i
			class="far fa-edit fa-2x"></i></a><br />
	</div>
	<!-- create article window -->
	<%@ include file="article_editor.jsp"%>
<script type="text/javascript">
var url = '<c:url value="/forum_test/${forum.forumId}/add"/>';
var lo = 'title';
var forumId = ${forum.forumId};
</script>
</body>
</html>