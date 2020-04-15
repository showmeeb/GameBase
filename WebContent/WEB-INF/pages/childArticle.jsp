<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>this is XXX forum page</title>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
			var myurl="/forum/"+$("#forumName").text()+"/"+articleTitle+"/add";
			console.log(myurl);
			//ajax send data to controller
			$.ajax({
				url:"/forum/"+$("#forumName").text()+"/"+articleTitle+"/add",
// 				dataType:"json",
				type:"POST",
				cache: false,
	            data:{articleTitle: $("#articleTitle").val(),
	            	accountId: $("#accountId").val(),
               		content: editor.getData()
               		},
         		success : function(response) {
        			console.log(response);

        		}        			
			})
		}
		
	})
});
	
</script>
</head>
<body>
	<h1 id="forumName">${forumName}</h1>
	<hr />
	<h2>標題:${mbList[0].boardTitle }</h2>

	<!-- ＸＸＸ版　文章表單 -->

	<c:if test="${childList==null}">
		<p>there is no article</p>
	</c:if>

	<c:if test="${childList!=null}">

		<p>there is articles</p>
		<hr />
		<c:forEach items="${childList}" var="item" varStatus="itemStatus">

			<div>
				<table>
					<tr>
						<td>this is a figure</td>
						<td>${item.boardTitle}<br />
							<p>this is a part of content ${item.content}</p></td>
					</tr>
					<tr>
						<td>click:</td>
						<td>?</td>
						<td>like:</td>
						<td>?</td>
						<td>unlike:</td>
						<td>?</td>
						<td>share:</td>
						<td>?</td>
					</tr>
					<tr>
						<td>author:</td>
						<td>?</td>
						<td>post time:</td>
						<td>${item.createTime}</td>
						<td>update time:</td>
						<td>${item.recordTime}</td>
					</tr>
				</table>

			</div>

		</c:forEach>
	</c:if>

	<hr />
	<!--輸入區 -->
	<br /> ${mycontent}
	<br />
	<hr />
	<h1>Classic editor</h1>
	<form>
		<table>
			<tr>
				<td><p>your account id:</p></td>
				<td><input type="text" id="accountId" name="accountId"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" id="articleTitle" name="articleTitle"
					value="${mbList[0].boardTitle }" disabled="disabled"></td>
			</tr>
		</table>
		<!-- CKeditor -->
		<textarea name="content" id="editor">
            ${mycontent}
        </textarea>
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
//         CKEDITOR.config.extraPlugins = 'myplugin,anotherplugin';
    	</script>

	</form>

	<button id="submit">reply</button>
</body>
</html>