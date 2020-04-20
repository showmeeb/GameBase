<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to forum page</title>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Font Awesome icons -->
<!-- <script src="https://kit.fontawesome.com/83bb506b46.js" crossorigin="anonymous"></script> -->

<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- ajax -->
<script>
$(document).ready(function(){
	var articleTitle = location.href.substring(location.href.lastIndexOf("GameBase/") + 9);
	console.log(articleTitle);
	
	console.log("document ready2");
	
	//form submit
	$("#submit").click(function(){
		console.log("submit");
		
		//ajax
		$.ajax({
			url:'<c:url value="/forum_test/add"/>',
			dataType:"json",
			type:"POST",
			cache: false,
	        data:{
	        	forumName: $("#forumName").val(),
	        	forumFigure: $("#forumFigure").val()
               	},
			success:function(response) {
     			var txt = '<div class="forum" id="forumtitle1">'+
     			'<h2>'+response.newForum.forumId+'.<a href="<c:url value="/forum_test/'+response.newForum.forumId+'"/>">'+response.newForum.forumName+'</a></h2>'+
     			'<hr/>'+
     			'<div class="forum_img">'+
     			'<img alt="圖片提示字串" src='+response.newForum.forumFigure+' height="100" width="100">'+
     			'</div>'+
     			'<div class="forum_articles">'+
     				'<span>熱門文章:</span><br/>'+
     				'<a>article1: 這是測試</a><br/>'+
     				'<a>article2: 這是測試</a><br/>'+
     				'<a>article3: 這是測試</a><br/>'+
     			'</div>'+
     			'</div>';
				$("#forumList").append(txt);
			}
		})
	});
});


</script>
</head>
<body>
	<!-- test forum display -->
	<div class="forum" id="forumtitle1">
		<h2>forum title</h2>
		<hr />
		<div class="forum_img">
			<img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png" height="200"
				width="200">
			<!-- 柴犬圖 -->
		</div>
		<div class="forum_articles">
			<span>熱門文章:</span><br /> <a>article1: 這是測試</a><br /> <a>article2:
				這是測試</a><br /> <a>article3: 這是測試</a><br />
		</div>
	</div>

	<hr>
	
	<!-- forum list -->
<div id="forumList">
	
	<c:forEach items="${forumList}" var="item" varStatus="itemStatus">
		<!-- 		<tr> -->
		<%-- 			<td>${itemStatus.count}</td><!-- title count --> --%>
		<%-- 			<td><a href="<c:url value="/forum/${item.forumName}"/>">${item.forumName}</td> --%>
		<%-- 			<td><img src="${item.forumFigure}"></td> --%>
		<!-- 		</tr> -->

		<div class="forum" id="forumtitle1">
			<h2>${itemStatus.count}.<a
					href="<c:url value="/forum_test/${item.forumId}"/>">${item.forumName}</a>
			</h2>
			<hr />
			<div class="forum_img">
				<img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png" height="100"
					width="100">
				<!-- 柴犬圖 -->
			</div>
			<div class="forum_articles">
				<span>熱門文章:</span><br /> <a>article1: 這是測試</a><br /> <a>article2:
					這是測試</a><br /> <a>article3: 這是測試</a><br />
			</div>
		</div>
	</c:forEach>
</div>

	<form>
		<table>
			<tr>
				<td>Forum Name:</td>
				<td><input type="text" id="forumName" name="forumName" /></td>
			</tr>
			<tr>
				<td>Forum Figure:</td>
				<td><input type="text" id="forumFigure" name="forumFigure" /></td>
			</tr>
		</table>
	</form>
	<button id="submit">Post New Forum</button>

	<hr />

</body>
</html>