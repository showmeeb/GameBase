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
<!-- <link -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
<!-- editor improt -->
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<!-- ckfinder import -->
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
<!-- main style -->
<%-- <link href="<c:url value="/css/style.css"/>" rel="stylesheet"> --%>
<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- forum list js -->
<script src="<c:url value="/js/forumList.js"/>"></script>
<!-- ajax -->
<!-- <script>
$(document).ready(function(){
	
	console.log("document ready");
	//open forum editor
    $("#publish-btn").click(function(){
    	
    	console.log("pulish btn");
        if(window.sessionStorage.getItem("loginUser") != ""){
       	 $("#publish-area").removeClass("hidden-window",700);
       	 $("#shadow").fadeIn(700);
       	 } else {
       	 alert("請先登入 !");       	    		
       	 $(".login-area").removeClass("hidden-window", 700);
       	 $("#shadow").fadeIn(700);
       	 }       
    });
    //forum editor close btn
    $(".forum_close_btn").click(close_window);
    function close_window(){  
    	console.log("close btn clicked");
        // empty input area
        $("#forumName").val("");
        $("#forumFigure").val("");
        $("#previewImage").attr('src','');
        // close window
        $(".forum_editor").addClass("hidden-window", 700);
        $("#shadow").fadeOut(700);
        $("#submit").removeClass("hidden");
        $("#update").addClass("hidden");
    }
	//update btn
	var o_forumId;
	    $(".btn_update_forum").click(function(){
    	console.log("btn_update_forum");
		$(".forum_editor").removeClass("hidden-window",700);
        $("#shadow").fadeIn(700);   
        $("#submit").addClass("hidden");
        $("#update").removeClass("hidden");
        //find oringinal value
        o_forumId = $(this).attr("id");
		$.ajax({
			url:'<c:url value="/forum_test/findforum/'+o_forumId+'"/>',					
			dataType:"json",
			type:"POST",
			cache: false,				        
			success:function(response){
		        $("#forumName").val(response.forum.forumName);		        
		        $("#previewImage").attr('src',response.forum.forumFigure);		        		        
			}
			});
    });
	//update fun
	$("#update").click(function(){
		//forumData
		var forumData = new FormData();
		var forumFigure = "";
		if( $("#forumFigure")[0].files[0] != null ){
			forumFigure = $("#forumFigure")[0].files[0];
			console.log(forumFigure);
			}
		var forumName = $("#forumName").val();		
		forumData.append("forumName", forumName);
		forumData.append("forumFigure",forumFigure);
		
		$.ajax({
			url:'<c:url value="/forum_test/'+o_forumId+'/update"/>',					
			processData: false,		
			type:"POST",
			cache: false,
			contentType : false,
	        data:forumData,
			success:function(response){
		        // empty input area
		        $("#forumName").val("");
		        $("#forumFigure").val("");
		        $("#previewImage").attr('src','');
		        // close window
				close_window();
		        //update data
		        location.reload();
			}
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
		var forumFigure = "";
		if( $("#forumFigure")[0].files[0] != null ){
			forumFigure = $("#forumFigure")[0].files[0];
			console.log(forumFigure);
			}
		var forumName = $("#forumName").val();
		forumData.append("forumName", forumName);
		forumData.append("forumFigure",forumFigure);
		
		//ajax
		$.ajax({
			url:'/GameBase/forum_test/add"/',
			processData: false,		
			type:"POST",
			cache: false,
			contentType : false,
	        data:forumData,
			success:function(response) {
				
				var newForum ='<div class="forum_list" id="'+response.newForum.forumId+'">'+
				'<div class="forum_title_bar">'+
				'<div class="forum_title_name">'+
				'<h2>'+
				+response.newForum.forumRank+'.<a href="<c:url value="/forum_test/'+response.newForum.forumId+'"/>">'+response.newForum.forumName+'</a>'+
				'</h2>'+
				'</div>'+
				'<div class="forum_btn">'+
				'<a id="'+response.newForum.forumId+'" class="btn_del_forum" href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a><br />'+
				'</div>'+
				'<div class=forum_btn>'+
				'<a id="'+response.newForum.forumId+'" class="btn_update_forum" href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a><br />'+
				'</div>'+
				'<c:if test="">'+
				'<div class="article_icons">'+
				'<a id="'+response.newForum.forumId+'" class="btn_del_forum" href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a><br />'+
				'</div>'+
				'<div class="article_icons">'+
				'<a id="'+response.newForum.forumId+'" class="btn_update_forum" href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a><br />'+
				'</div>'+
				'</c:if>'+
				'</div>'+
				'<div class="forum_content">'+
				'<div class="forum_img">'+
				'<img alt="圖片提示字串" src="'+response.newForum.forumFigure+'" height="320">'+
				'</div>'+
				'<div class="forum_articles">'+
				'<span>熱門點閱文章:</span><br /> <a href="<c:url value="/forum_test/'+response.newForum.forumId+'/'+response.newForum.titleId+'"/>">'+response.newForum.titleName+'</a><br />'+
				'</div>'+
				'</div>'+
				'</div>';
					        	            
// 	            var newforum = Mustache.render(newForumTemplate, response);
	            $("#forum_list").append(newForum);
		        
		        
				$("#"+response.newForum.forumId+".forum").on("click",".btn_del_forum",del);
				$("#forumFigure").val("");
				$("#forumName").val("");
				$("#previewImage").attr('src','');
		        // close window
				close_window(); 
			}
		});
	});
 -->	
	<!-- delete forum -->
<!-- 	$(".btn_del_forum").click(del);
	function del(){
		console.log("delete forum, forum ID : "+$(this).attr("id"));
		var forumId=$(this).attr("id");
		$.ajax({
			url:'/GameBase/forum_test/'+forumId+'/del',
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


</script> -->
</head>
<body>
	<!-- top bar -->
	<%@ include file="topBar.jsp"%>
	
	<c:if test="${loginUser.rankId==2}"><!-- rank = 2 可編輯-->
	<!-- forum title bar -->
	<nav class="navbar navbar-expand-sm bg-light forum_topbar">
		<ul class="nav justify-content-end">
			<!-- update article button -->
			<li class="nav-item">
				<a id="publish-btn" class="nav-link" href="javascript:void(0)">
					<i class="far fa-plus-square fa-2x"></i>
				</a>
			</li>
<!-- 			<li class="nav-item"> hello manager<br/></li> -->
		</ul>
	</nav>
	</c:if>
	<!-- forum list -->
	<div class="forumListAndForumEditor">
		<div id="forum_list" class="forum_list">

			<c:forEach items="${forumList}" var="item" varStatus="itemStatus">

				<div class="forum_forum" forumId="${item.forumId}">

					<div class="forum_title_bar">
						<!-- forum name -->
						<div class="forum_name">
							<h2>
								${itemStatus.count}.
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
							<a href="<c:url value="/forum_test/${item.forumId}/${item.titleId}"/>">${item.titleName}</a>
							<br />
						</div>
					</div>
									
					<div class="forumdata" forumId="${item.forumId}" hidden>
					<div class="fname">${item.forumName}</div>
					<div class="ffigure">${item.forumFigure}</div>
					</div>				
				</div>				
			</c:forEach>
		</div>


		<div id="publish-area" class="forum_editor popup-window hidden-window">
			<form id="forumData" enctype="multipart/form-data">
				<i id="forum_close_btn" class="fas fa-times forum_close_btn"></i>
				<table>
					<tr>
						<td>討論區名稱:</td>
						<td><input type="text" id="forumName" name="forumName" /></td>
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
</body>
</html>