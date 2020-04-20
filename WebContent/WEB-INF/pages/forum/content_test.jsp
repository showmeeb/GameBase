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
<!-- forum style -->
<link href="<c:url value="/css/forumStyle.css"/>" rel="stylesheet">
<!-- editor improt -->
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
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
				url:articleTitle+"/add",
				dataType:"json",
				type:"POST",
				cache: false,
	            data:{articleTitle: $("#articleTitle").val(),
	            	userId: $("#accountId").val(),
               		content: editor.getData()
               		},
         		success : function(response) {
        			console.log(response);
        			/*ajac response*/
        								<!-- user data and update/post time -->
        			var txt = '<div>'+
            			'<span>userId : '+response.newContent.userId+'</span>'+
            			'<div class="article_time_record">'+
						'<i class="fas fa-pen-alt">Post Time : '+response.newContent.createTime+'</i><br />'+
						'</div>'+
						'<div class="article_time_record">'+
						'<i class="fas fa-pen-alt">Update Time : '+response.newContent.updateTime+'</i><br />'+
						'</div>'+
						'</div>'+
						'<br />'+
						<!-- content area -->
						'<div class="content_content">'+
						'<span>'+response.newContent.content+'</span><br />'+
						'</div><hr />';
					$("#publish-area").before(txt);

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

		<!-- 發文者區塊 -->
		<div class="content_Authordata"></div>

		<!-- 發文時間 修改時間  -->
		<div class="content_postdata">
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

		<!-- content part -->
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

		<!-- icons import : like, unlike, click, reply, delete, update -->
		<div class="article_part article_datas">
			<!-- like button -->
			<div class="article_icons">
				<i class="far fa-thumbs-up fa-2x">123</i> <i
					class="fas fa-thumbs-up fa-2x">132</i>
			</div>
			<!-- unlike button -->
			<div class="article_icons">
				<i class="far fa-thumbs-down fa-2x">123</i> <i
					class="fas fa-thumbs-down fa-2x">123</i>
			</div>
			<!-- click -->
			<!-- 可不要使用 -->
			<div class="article_icons">
				<i class="far fa-eye fa-2x">123123</i> <i class="fas fa-eye fa-2x">123123</i><br />
			</div>
			<!-- reply article button -->
			<div class="article_icons">
				<i class="fab fa-replyd fa-2x"></i><br />
			</div>
			<!-- delete article button -->
			<div class="article_icons">
				<i class="far fa-trash-alt fa-2x"></i><br /> <i
					class="fas fa-trash-alt fa-2x"></i><br />
			</div>
			<!-- update article button -->
			<div class="article_icons">
				<i class="fas fa-edit fa-2x"></i><br /> <i
					class="far fa-edit fa-2x"></i><br />
			</div>

		</div>
	</div>

	<!-- ================================================================================ -->
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
				
					<!-- user data and update/post time -->					
					<!-- user -->
					<div>
<%-- 					<img src=${profileList[item.userId].Img }> --%>
					<span>userId : ${item.userId}</span>
					<!-- time -->
					<div class="article_time_record">
						<i class="fas fa-pen-alt">Post Time : ${item.createTime}</i><br />
					</div>
					<div class="article_time_record">
						<i class="fas fa-pen-alt">Update Time : ${item.updateTime}</i><br />
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
					<div class="article_part article_datas">
						<!-- like -->
						<div class="article_icons">
							<i class="far fa-thumbs-up fa-2x">${title.likeNum}</i> <i
								class="fas fa-thumbs-up fa-2x">${title.likeNum}</i>
						</div>
						<!-- unlike -->
						<div class="article_icons">
							<i class="far fa-thumbs-down fa-2x">${title.unlikeNum}</i> <i
								class="fas fa-thumbs-down fa-2x">${title.unlikeNum}</i>
						</div>
						<!-- click -->
						<div class="article_icons">
							<i class="far fa-eye fa-2x">${title.clickNum}</i> <i
								class="fas fa-eye fa-2x">${title.clickNum}</i><br />
						</div>
						<!-- create time -->
						<div class="article_time_record">
							<i class="fas fa-pen-alt fa-2x">Post Time : ${title.createTime}</i><br />
						</div>
						<!-- last reply time -->
						<div class="article_time_record">
							<i class="far fa-comment-dots fa-2x">Last Reply : ${title.lastReplyTime}</i><br /> 
							<i class="fas fa-comment-dots fa-2x">Last Reply : ${title.lastReplyTime}</i><br />
						</div>
					</div>
					</c:if>
					<hr />
					<!-- content end -->
				</c:forEach>
			</div>

		

	</c:if>
	<!-- ================================================================================ -->
	<!--輸入區 -->
	<div id="publish-area" class="publish-area">
		<form>
			<!-- User ID and Article Title -->
			<table>
				<tr>
					<td><p>your account id:</p></td>
					<td><input type="text" id="accountId" name="accountId"></td>
				</tr>
				<tr>
					<td><p>article title:</p></td>
					<td><input type="text" id="articleTitle" name="articleTitle" disabled="disabled" value="${title.titleName}"></td>
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
	</div>
</body>
</html>