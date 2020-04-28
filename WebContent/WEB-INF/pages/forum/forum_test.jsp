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
<!-- ajax -->
<script>
$(document).ready(function(){
	var articleTitle = location.href.substring(location.href.lastIndexOf("GameBase/") + 9);
	console.log(articleTitle);
	
	console.log("document ready");

    $("#publish-btn").click(function(){
    	console.log("pulish btn");
		$(".publish-area").removeClass("hidden-window",700);
        $("#shadow").fadeIn(700);
        $(".publish-area .close-btn").click(function(){  
        	console.log("close btn clicked");
            // empty input area
            $("#forumName").val("");
            $("#forumFigure").val("");
            $("#previewImage").attr('src','');
            // close window
            $(".publish-area").addClass("hidden-window", 700);
            $("#shadow").fadeOut(700);     
        });
        
    });
	
	//figure preview
	$("#forumFigure").change(function() {
		
    	var fileReader = new FileReader();
    	
		fileReader.onload = function(e) {
		$("#previewImage").show();
		$("#previewImage").attr('src',e.target.result);
		}
        var imageFile = this.files[0];
        fileReader.readAsDataURL(imageFile);
	});
	//form submit
	$("#submit").click(function(){
		console.log("submit");
		//forumData
		var forumData = new FormData();
		var forumFigure = $("#forumFigure")[0].files[0];
		var forumName = $("#forumName").val();
		forumData.append("forumName", forumName);
		forumData.append("forumFigure",forumFigure);
		
		//ajax
		$.ajax({
			url:'<c:url value="/forum_test/add"/>',
			processData: false,		
			type:"POST",
			cache: false,
			contentType : false,
	        data:forumData,
			success:function(response) {
				
     			var txt = '<div class="forum" id="'+response.newForum.forumId+'">'+
     			'<h2>'+response.newForum.forumRank+'.<a href="<c:url value="/forum_test/'+response.newForum.forumId+'"/>">'+response.newForum.forumName+'</a></h2>'+
    			<!-- delete article button -->
    			'<div class="article_icons">'+
    			'<a id="'+response.newForum.forumId+'" class="btn_del_forum" href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a><br /> '+
    			'</div>'+
    			<!-- update article button -->
    			'<div class="article_icons">'+
    			'<a id="'+response.newForum.forumId+'" class="btn_update_forum" href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a><br />'+
    			'</div>'+
     			'<hr/>'+
     			'<div class="forum_img">'+
     			'<img alt="圖片提示字串" src='+response.newForum.forumFigure+' height="200">'+
     			'</div>'+
     			'<div class="forum_articles">'+
     				'<span>熱門熱門點閱文章::</span><br/>'+
//      				'<a>article1: 這是測試</a><br/>'+
//      				'<a>article2: 這是測試</a><br/>'+
//      				'<a>article3: 這是測試</a><br/>'+
     			'</div>'+
     			'</div>';
				$("#forumList").append(txt);
				$("#"+response.newForum.forumId+".forum").on("click",".btn_del_forum",del);
				$("#forumFigure").val("");
				$("#forumName").val("");
				$("#previewImage").attr('src','');
			}
		});
	});
	
	<!-- delete forum -->
	$(".btn_del_forum").click(del);
	function del(){
		console.log("delete forum, forum ID : "+$(this).attr("id"));
		var forumId=$(this).attr("id");
		$.ajax({
			url:'<c:url value="/forum_test/'+forumId+'/del"/>',
			type:"POST",
			cache: false,
	        data:{
	        	forumId:$(this).attr("id")
	        },
	        success:function(response) {
	        	console.log("delete success")
	        	$('#'+forumId).remove();
	        }
		});
	}
});
</script>



</head>
<body>
<%@ include file="../topBar.jsp"%>
	<!-- test forum display -->
	<div class="forum" id="forumtitle1">
		<h2>forum title</h2>
		<hr />
		<div class="forum_img">
			<img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png"
				height="200 px">
			<!-- 柴犬圖 -->
		</div>
		<div class="forum_articles">
			<span>熱門文章:</span><br /> <a>article1: 這是測試</a><br /> <a>article2:
				這是測試</a><br /> <a>article3: 這是測試</a><br />
		</div>
	</div>

	<hr>

	<!-- forum list -->
	<div class="forumListAndEditor">
		<div id="forumList">

			<c:forEach items="${forumList}" var="item" varStatus="itemStatus">
				<!-- 		<tr> -->
				<%-- 			<td>${itemStatus.count}</td><!-- title count --> --%>
				<%-- 			<td><a href="<c:url value="/forum/${item.forumName}"/>">${item.forumName}</td> --%>
				<%-- 			<td><img src="${item.forumFigure}"></td> --%>
				<!-- 		</tr> -->

				<div class="forum" id="${item.forumId}">
					<!-- forum name -->
					<h2>${itemStatus.count}.<a
							href="<c:url value="/forum_test/${item.forumId}"/>">${item.forumName}</a>
					</h2>
					<!-- delete article button -->
					<div class="article_icons">
						<a id="${item.forumId}" class="btn_del_forum"
							href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a><br />
						<!-- 				<i class="fas fa-trash-alt fa-2x"></i><br /> -->
					</div>
					<!-- update article button -->
					<div class="article_icons">
						<!-- 				<i class="fas fa-edit fa-2x"></i><br />  -->
						<a id="${item.forumId}" class="btn_update_forum"
							href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a><br />
					</div>
					<hr />

					<div class="forum_img">
						<img alt="圖片提示字串" src="${item.forumFigure}" height="200">
						<!-- 柴犬圖 -->
					</div>
					<div class="forum_articles">
						<span>熱門點閱文章:</span><br /> <a
							href="<c:url value="/forum_test/${item.forumId}/${item.titleId}"/>">${item.titleName}</a><br />
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- update article button -->
		<div class="article_icons">
			<!-- 				<i class="fas fa-edit fa-2x"></i><br />  -->
			<a id="publish-btn" class="btn_update_forum"
				href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a><br />
		</div>
		<div class="publish-area popup-window hidden-window">
			<form id="forumData" enctype="multipart/form-data">
				<i class="fas fa-times close-btn"></i>
				<table>
					<tr>
						<td>Forum Name:</td>
						<td><input type="text" id="forumName" name="forumName" /></td>
					</tr>
					<tr>
						<td>Forum Figure:</td>
						<td><input type="file" id="forumFigure" name="forumFigure" /></td>
					</tr>
				</table>
			</form>
			<button id="submit">Post New Forum</button>
			<img id="previewImage" alt="預覽圖" height="200px" />
		</div>

	</div>
</body>
</html>