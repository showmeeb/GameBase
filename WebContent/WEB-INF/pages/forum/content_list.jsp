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
<!-- btn js import -->
<%-- <script src="<c:url value="/js/content.js"/>"></script> --%>
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
<!-- update record likenum and unlikenum -->
<script>
$("#document").ready(function () {
	console.log("this is content.js2");
	
	/*record button clicked*/
	$(".btn_record").click(function(){
		console.log("record btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id");
		console.log(btn);
		
		update_record(btn);
	});
	
	/*update content button clicked*/
	$(".btn_update_content").click(function(){
		console.log("update record btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id")
		console.log(btn);
		var contentId = $(this).parents(".content_id").attr("id");
		console.log("content ID : "+contentId);
		update_content(btn,contentId);
	});
	
});
/*update like unlike number*/
function update_record(btn){	
	console.log("get btn value :"+btn);
	console.log("/forum_test/${forum.forumId}/${title.titleId}");
	$.ajax({
		url:'<c:url value="/forum_test/${forum.forumId}/${title.titleId}/record"/>',
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			clickedBTN:btn
		},
		success: function(response){
			console.log("success");
			console.log("response : "+response.record.record)
			$(".btn_record").removeClass("disabled");
			if(response.record.record === "like") {
				$("#like.btn_record").html('<i class="fas fa-thumbs-up fa-2x">'+response.title.likeNum+'</i>');
				$("#unlike.btn_record").addClass("disabled").html('<i class="far fa-thumbs-down fa-2x">'+response.title.unlikeNum+'</i>');
			}else if (response.record.record === "unlike"){
				$("#like.btn_record").addClass("disabled").html('<i class="far fa-thumbs-up fa-2x">'+response.title.likeNum+'</i>');
				$("#unlike.btn_record").html('<i class="fas fa-thumbs-down fa-2x">'+response.title.unlikeNum+'</i>');
			}else{
				$("#like.btn_record").html('<i class="far fa-thumbs-up fa-2x">'+response.title.likeNum+'</i>');
				$("#unlike.btn_record").html('<i class="far fa-thumbs-down fa-2x">'+response.title.unlikeNum+'</i>');
			}
			
		}
	})
}
/*update or delete content and reply*/
function update_content(btn,contentId){
// 	var contentId = $(this).parents(".content_id").attr("id");
// 	console.log("content ID : "+contentId);
	if(btn==='delete'){
		console.log("get btn value :"+btn);
		console.log("/forum_test/${forum.forumId}/${title.titleId}");
		$.ajax({
			url:'<c:url value="/forum_test/${forum.forumId}/${title.titleId}/'+contentId+'/update"/>',
			dataType:"json",
			type:"POST",
			cache:false,
			data:{
				clickedBTN:btn,
				contentId:contentId
			},
			success: function(response){
				console.log("success");	


				
			}
		})
	}
}
</script>
<style type="text/css">
a.disabled {
    pointer-events: none;
}
</style>
</head>

<body>
	<div id="contentList" class="article">
	<h1>主題：${forum.forumName}</h1>
	<br />

	<c:if test="${empty contentList}">
		<p>there is no article</p>
	</c:if>

	<c:if test="${not empty contentList}">

		<p>there is article content and reply</p>

		
			<div class="article_part">
				<h2>${title.titleName}</h2>

				<hr />
				<c:forEach items="${contentList}" var="item" varStatus="itemStatus">
					
					<div id="${item.contentId}" class="content_id">
					<!-- user data and update/post time -->	
					<div>				
					<!-- user img -->
					<div>
					<c:if test="${empty item.img}"><img src="<c:url value="/img/userIcon.png"/>" width="60" height="60"/></c:if>
					<c:if test="${not empty item.img}"><img src=${item.img} alt="" width="40" height="40"/></c:if>
					</div>
					<!-- user account -->
					<div>
					<span>
					user ID 		: ${item.userId}<br/>
					user Account 	: ${item.account}<br/>
					user NickName 	: ${item.nickName}<br/>
					content Id 		: ${item.contentId}<br/>
					</span>
					</div>
					<!-- time -->
					<div class="article_time_record">
						<i class="fas fa-pen-alt">Post Time : ${item.createTime}</i><br />
					</div>
					<div class="article_time_record">
						<i class="fas fa-pen-alt">Update Time : ${item.updateTime}</i><br />
					</div>
					<!-- delete article button -->
					<div class="article_icons">
						<a id="delete" class="btn_update_content" href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a>
					</div>
					<!-- update article button -->
					<div class="article_icons">
						<a id="update" class="btn_update_content" href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a>
					</div>					
					</div>
					<hr />
					
					<!-- content area -->
					<div class="content_content">
						<span>${item.content}</span><br />
					</div>
					<hr />
					<!-- icons input and time record -->
					<c:if test="${itemStatus.count==1 }">
					<div class="article_datas">
					<!-- ajax link record btns -->
					<div class="record_btns">
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
					</div>
<!-- 						like -->
<!-- 						<div class="article_icons"> -->
<%-- 							<i class="far fa-thumbs-up fa-2x">${title.likeNum}</i>  --%>
<%-- 							<i class="fas fa-thumbs-up fa-2x">${title.likeNum}</i> --%>
<!-- 						</div> -->
<!-- 						unlike -->
<!-- 						<div class="article_icons"> -->
<%-- 							<i class="far fa-thumbs-down fa-2x">${title.unlikeNum}</i> <i --%>
<%-- 								class="fas fa-thumbs-down fa-2x">${title.unlikeNum}</i> --%>
<!-- 						</div> -->

						<!-- click -->
						<div class="article_icons">
							<i class="far fa-eye fa-2x">${title.clickNum}</i> 
<%-- 							<i class="fas fa-eye fa-2x">${title.clickNum}</i><br /> --%>
						</div>
						<!-- create time -->
						<div class="article_time_record">
							<i class="fas fa-pen-alt fa-2x">Post Time : ${title.createTime}</i><br />
						</div>
						<!-- last reply time -->
						<div class="article_time_record">
							<i class="far fa-comment-dots fa-2x">Last Reply : ${title.lastReplyTime}</i><br /> 
<%-- 							<i class="fas fa-comment-dots fa-2x">Last Reply : ${title.lastReplyTime}</i><br /> --%>
						</div>
					</div>
					</c:if>
					<hr />
					</div>
					<!-- content end -->
					
				</c:forEach>
			</div>
	</c:if>
	</div>
	
<!-- update article button -->
<div class="article_icons">
<!-- 				<i class="fas fa-edit fa-2x"></i><br />  -->
	<a id="publish-btn" class="" href="javascript:void(0)">
	<i class="far fa-edit fa-2x"></i></a><br />
</div>
<!-- create article window -->
<%@ include file="article_editor.jsp"%>
<script type="text/javascript">
var url = '<c:url value="/forum_test/${forum.forumId}/${title.titleId}/add"/>';
var lo = 'content';
</script>
</body>
</html>