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
<script src="https://kit.fontawesome.com/83bb506b46.js" crossorigin="anonymous"></script>
<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- editor improt -->
<script src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<!-- create new article -->
<script>
let editor;

$(document).ready(function(){

	var articleTitle = location.href.substring(location.href.lastIndexOf("/") + 1);
	console.log(articleTitle);
	
	//editor submit	
	$("#submit").click(function(){
		console.log("click submit");
		//get form's inputs and transform to json form
// 		var a=$(this.form).serializeObject();
// 		console.log(a);
// 		var form = JSON.stringify(a);
		
		//get url (forum name = article title =article location)

		//get img source
		var jsonObj = {"urlList":[]};
		var imgSrc = "";
		
		//get all img-element's source from the editor block
		$(".publish-area img").each(function(){
			console.log("jsonObj.urlList ="+jsonObj.urlList+", jsonObj.urlList[jsonObj.urlList.length]= "+jsonObj.urlList[jsonObj.urlList.length]);
			console.log($(this).attr("src"));
			jsonObj.urlList[jsonObj.urlList.length] = $(this).attr("src");
        });
		
		//get first img's src
		if(jsonObj.urlList.length != 0){
			console.log("first img's src: "+jsonObj.urlList[0]);
			imgSrc = jsonObj.urlList[0];
		}
		
		//do function when editor has data and title has value
		if(editor.getData() && $("#articleTitle").val()){
			console.log("has data");
			console.log(editor.getData());
			var myurl="/forum/"+articleTitle+"/add";
			console.log(myurl);
			//ajax send data to controller
			$.ajax({
				url:'<c:url value="/forum_test/${forum.forumId}/add"/>',
				dataType:"json",
				type:"POST",
				cache: false,
	            data:{articleTitle: $("#articleTitle").val(),
	            	userId: $("#accountId").val(),
               		content: editor.getData()
               		},
         		success : function(response) {
         			/*ajax response*/
        			var txt = '<div class="article"><div class="article_part"><h2>'+
						'<a href="<c:url value="/forum_test/${forumId}/'+
						response.newTitle.titleId+'"/>">'+response.newTitle.titleName+'</a></h2><hr/>'+
						'<div class="article_img">'+
						'<img alt="圖片提示字串" src='+response.newTitle.firstFigure+
						'height="100" width="100"></div><div class="article_content">'+
						'<span>'+response.newContent.content+'</span><br /></div></div><div class="article_part article_datas">'+
						'<div class="article_icons"><i class="far fa-thumbs-up fa-2x">'+
						response.newTitle.likeNum+'</i> <i class="fas fa-thumbs-up fa-2x">'+
						response.newTitle.likeNum+'</i></div><div class="article_icons">'+
						'<i class="far fa-thumbs-down fa-2x">'+response.newTitle.unlikeNum+
						'</i> <i class="fas fa-thumbs-down fa-2x">'+response.newTitle.unlikeNum+
						'</i></div><div class="article_icons"><i class="far fa-eye fa-2x">'+
						response.newTitle.clickNum+'</i><i class="fas fa-eye fa-2x">'+response.newTitle.clickNum+
						'</i><br/></div><div class="article_time_record">'+
						'<i class="fas fa-pen-alt fa-2x">Post Time : '+response.newTitle.createTime+'</i><br />'+
						'</div>'+
						<!-- last reply time -->
						'<div class="article_time_record">'+
						'<i class="far fa-comment-dots fa-2x">Last Reply : '+response.newTitle.lastReplyTime+'</i><br />'+
						'<i class="fas fa-comment-dots fa-2x">Last Reply : '+response.newTitle.lastReplyTime+'</i><br />'+
						'</div>'+
						'</div>'+
						'</div>';
					$("#titleList").append(txt);			
        		}        			
			})
		}
		
	})
});
	
</script>
</head>

<body>
	<!-- test article title dispaly -->
	<div class="article">
		<div class="article_part">
			<h2>article title : this is title for test</h2>
			<hr />
			<div class="article_img">
				<img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png" height="100"
					width="100">
				<!-- 柴犬圖 -->
			</div>
			<div class="article_content">
				<span>沃爾夫勒姆表示，這些更新步對應於我們對時間的一般概念，即宇宙時鐘的滴答作響。
					將一條規則重複應用於一組抽象實體，得到的連接（即鏈接這組實體的關係圖）對應於空
					間結構。因此空間不只是一組無法分辨的點，而是一個以無比複雜的模式連接的點網絡， 它可以復現物質和能量，以及被統稱爲物理學定律的關係。</span><br />
			</div>
		</div>
		<!-- icons input and time record -->
		<div class="article_part article_datas">
			<!-- like -->
			<div class="article_icons">
				<i class="far fa-thumbs-up fa-2x">123</i> <i
					class="fas fa-thumbs-up fa-2x">132</i>
			</div>
			<!-- unlike -->
			<div class="article_icons">
				<i class="far fa-thumbs-down fa-2x">123</i> <i
					class="fas fa-thumbs-down fa-2x">123</i>
			</div>
			<!-- click -->
			<div class="article_icons">
				<i class="far fa-eye fa-2x">123123</i> <i class="fas fa-eye fa-2x">123123</i><br />
			</div>
			<!-- create time -->
			<div class="article_time_record">
				<i class="fas fa-pen-alt fa-2x">Post Time : 2020-04-19 07:07:07</i><br />
			</div>
			<!-- last reply time -->
			<div class="article_time_record">
				<i class="far fa-comment-dots fa-2x">Last Reply : 2020-04-19
					07:07:07</i><br /> <i class="fas fa-comment-dots fa-2x">Last Reply
					: 2020-04-19 07:07:07</i><br />
			</div>
		</div>
	</div>
	<!-- test 2 -->
	<div class="article">
		<div class="article_part">
			<h2>article title : this is title for no img</h2>
			<hr />
			<div class="article_content article_content_noimg">
				<span>沃爾夫勒姆表示，這些更新步對應於我們對時間的一般概念，即宇宙時鐘的滴答作響。
					將一條規則重複應用於一組抽象實體，得到的連接（即鏈接這組實體的關係圖）對應於空
					間結構。因此空間不只是一組無法分辨的點，而是一個以無比複雜的模式連接的點網絡， 它可以復現物質和能量，以及被統稱爲物理學定律的關係。</span><br />
			</div>
		</div>
		<!-- icons input and time record -->
		<div class="article_part article_datas">
			<!-- like -->
			<div class="article_icons">
				<i class="far fa-thumbs-up fa-2x">123</i> <i
					class="fas fa-thumbs-up fa-2x">132</i>
			</div>
			<!-- unlike -->
			<div class="article_icons">
				<i class="far fa-thumbs-down fa-2x">123</i> <i
					class="fas fa-thumbs-down fa-2x">123</i>
			</div>
			<!-- click -->
			<div class="article_icons">
				<i class="far fa-eye fa-2x">123123</i> <i class="fas fa-eye fa-2x">123123</i><br />
			</div>
			<!-- create time -->
			<div class="article_time_record">
				<i class="fas fa-pen-alt fa-2x">Post Time : 2020-04-19 07:07:07</i><br />
			</div>
			<!-- last reply time -->
			<div class="article_time_record">
				<i class="far fa-comment-dots fa-2x">Last Reply : 2020-04-19
					07:07:07</i><br /> <i class="fas fa-comment-dots fa-2x">Last Reply
					: 2020-04-19 07:07:07</i><br />
			</div>
		</div>
	</div>
	<!-- ================================================================================ -->
	<h1>主題：${forum.forumName}</h1>
	<br />
	<div id="titleList">
	<c:if test="${titleList==null}">
		<p>there is no article</p>
	</c:if>

	<c:if test="${titleList!=null}">
	
		<p>there is articles</p>
		<c:forEach items="${titleList}" var="item" varStatus="itemStatus">
			<div class="article">
				<div class="article_part">
					<h2>
						<a href="<c:url value="/forum_test/${forumId}/${item.titleId}"/>">${item.titleName}</a>
					</h2>
					<hr />
					<div class="article_img">
						<img alt="圖片提示字串" src="https://i.imgur.com/8g2jFuM.png"
							height="100" width="100">
						<!-- 柴犬圖 -->
					</div>
					<div class="article_content">
						<span>content...</span><br />
					</div>
				</div>
				<!-- icons input and time record -->
				<div class="article_part article_datas">
					<!-- like -->
					<div class="article_icons">
						<i class="far fa-thumbs-up fa-2x">${item.likeNum}</i> <i
							class="fas fa-thumbs-up fa-2x">${item.likeNum}</i>
					</div>
					<!-- unlike -->
					<div class="article_icons">
						<i class="far fa-thumbs-down fa-2x">${item.unlikeNum}</i> <i
							class="fas fa-thumbs-down fa-2x">${item.unlikeNum}</i>
					</div>
					<!-- click -->
					<div class="article_icons">
						<i class="far fa-eye fa-2x">${item.clickNum}</i> 
						<i class="fas fa-eye fa-2x">${item.clickNum}</i><br />
					</div>
					<!-- create time -->
					<div class="article_time_record">
						<i class="fas fa-pen-alt fa-2x">Post Time : ${item.createTime}</i><br />
					</div>
					<!-- last reply time -->
					<div class="article_time_record">
						<i class="far fa-comment-dots fa-2x">Last Reply : ${item.lastReplyTime}</i><br /> 
						<i class="fas fa-comment-dots fa-2x">Last Reply : ${item.lastReplyTime}</i><br />
					</div>
				</div>
			</div>
		</c:forEach>
	
	</c:if>
	</div>
	<!-- ================================================================================ -->
	<!--輸入區 -->
	<hr />
	<div class="publish-area article">
		<form>
			<!-- User ID and Article Title -->
			<table>
				<tr>
					<td><p>your account id:</p></td>
					<td><input type="text" id="accountId" name="accountId"></td>
				</tr>
				<tr>
					<td><p>article title:</p></td>
					<td><input type="text" id="articleTitle" name="articleTitle"></td>
				</tr>
			</table>
			<!-- CKeditor -->
			<textarea name="content" id="editor"></textarea>

			<script>
		editor = ClassicEditor
            	.create( document.querySelector( '#editor' ),{
            	    mediaEmbed:{
            	    	previewsInData:true
          	        }
         		} )
         		.then( newEditor => {
                	editor = newEditor
                } )
            	.catch( error => {
                	console.error( error );
            	} );

   		</script>
		</form>
		<button id="submit">Post Your Article</button>
	</div>
</body>
</html>