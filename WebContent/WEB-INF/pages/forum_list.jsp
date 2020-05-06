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
	
	<c:if test="${loginUser.rankId>=4}"><!-- rank = 2 可編輯-->
		<!-- forum title bar -->
	<div class="forum_create-btn">
	<nav class="navbar navbar-expand-sm bg-light forum_topbar">
		<ul class="nav justify-content-end">
			<!-- update article button -->
			<li class="nav-item">
				<a id="publish-btn" class="nav-link" href="javascript:void(0)">
					<i class="far fa-plus-square fa-2x"></i>
				</a>
			</li>
		</ul>
	</nav>
	</div>
	</c:if>
	<c:if test="${loginUser.rankId<3}">
	<!-- forum title bar -->
	<div class="forum_create-btn" hidden>
	<nav class="navbar navbar-expand-sm bg-light forum_topbar">
		<ul class="nav justify-content-end">
			<!-- update article button -->
			<li class="nav-item">
				<a id="publish-btn" class="nav-link" href="javascript:void(0)">
					<i class="far fa-plus-square fa-2x"></i>
				</a>
			</li>
		</ul>
	</nav>
	</div>
	</c:if>

	<!-- forum list -->
	<div class="forumListAndForumEditor">
		<div id="forum_list" class="forum_list">

			<c:forEach items="${forumList}" var="item" varStatus="itemStatus">
				<c:if test="${item.clickRN == 1}">
				<div class="forum_forum" forumId="${item.forumId}" forumRank="${item.forumRank}">

					<div class="forum_title_bar">
						<!-- forum name -->
						<div class="forum_name">
							<h2>
								${item.forumRank}.
								<a href="<c:url value="/forum_test/${item.forumId}"/>">${item.forumName}</a>
							</h2>
						</div>
						<!-- delete forum button -->
						<div class="forum_editor_btn" hidden="true">
							<a forumId="${item.forumId}" class="forum_del_btn" href="javascript:void(0)">
							<i class="far fa-trash-alt fa-2x"></i>
							</a>
							<br />
						</div>
						<!-- update forum button -->
						<div class="forum_editor_btn" hidden="true">
							<a forumId="${item.forumId}" class="forum_update_btn" href="javascript:void(0)">
							<i class="far fa-edit fa-2x"></i>
							</a>
							<br />
						</div>
					</div>


					<div class="forum_content">

						<div class="forum_img">
							<img alt="圖片提示字串" src="${item.forumFigure}" height="320">
						</div>
						<div class="forum_articles">
							<span>熱門點閱文章:</span><br /> 
							<c:forEach items="${forumList}" var="topArticle">
							<c:if test="${item.forumId==topArticle.forumId}">
							<a href="<c:url value="/forum_test/${topArticle.forumId}/${topArticle.titleId}"/>">${topArticle.titleName}</a>
							<br />
							</c:if>
							</c:forEach>
						</div>
					</div>
									
					<div class="forumdata" forumId="${item.forumId}" hidden>
					<div class="fname">${item.forumName}</div>
					<div class="ffigure">${item.forumFigure}</div>
					</div>				
				</div>
				</c:if>				
			</c:forEach>
		</div>


		<div id="publish-area" class="forum_editor popup-window hidden-window">
			<form id="forumData" enctype="multipart/form-data">
				<i id="forum_close_btn" class="fas fa-times forum_close_btn"></i>
				<table>
					<tr>
						<td>討論區名稱:</td>
						<td><input type="text" id="forumName" name="forumName" size="40"/></td>
					</tr>
					<tr>
						<td>討論區圖片:</td>
						<td><input type="file" id="forumFigure" name="forumFigure" /></td>
					</tr>
				</table>
			</form>
			<button id="submit" class="buttonL" hidden="true">建立新的討論區</button>
			<button id="update" class="buttonL" hidden="true">更新討論區</button>			
			<img id="previewImage" alt="預覽圖" height="315" />
		</div>

	</div>
<script type="text/javascript">
var lo = 'forum';
</script>
</body>
</html>