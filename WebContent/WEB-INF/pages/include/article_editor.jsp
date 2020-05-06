<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--輸入區 -->
<hr />
<div class="publish-area popup-window hidden-window">
<i class="fas fa-times close-btn"></i>
<c:if test="${empty title.titleName}"><h2>發佈貼文</h2></c:if>
<c:if test="${not empty title.titleName}"><h2>回覆貼文</h2></c:if>
<br/>
	<form>
		<!-- User ID and Article Title -->
		<table>
			<c:if test="${not empty title.titleName}">
			<tr>
				<td><p>文章標題:</p></td>
				<td><input type="text" id="articleTitle" name="articleTitle" value="${title.titleName}" size="100" disabled="disabled"></td>
			</tr>
			</c:if>
			<!-- title is empty -->
			<c:if test="${empty title.titleName}">
			<tr>
				<td><p>文章標題:</p></td>
				<td><input type="text" id="articleTitle" name="articleTitle" size="50"></td>
			</tr>
			</c:if>
		</table>
		
		<!-- CKeditor -->
		<textarea name="content" id="testeditor"></textarea>

		<script>
		editor = ClassicEditor
            	.create( document.querySelector( '#testeditor' ),{
            	    mediaEmbed:{previewsInData:true},
            	    removePlugins: ['Heading'],
    	        	ckfinder: {uploadUrl: "/GameBase/figureupload"}
         		} )
         		.then( newEditor => {
                	editor = newEditor
                } )
            	.catch( error => {
                	console.error( error );
            	} );

   		</script>
	</form>
	<button id="submit" class="buttonL">發佈文章</button>
	<button id="update_content" class="buttonL" hidden="true">更新文章</button>
	<button id="reply_content" class="buttonL" hidden="true">回覆文章</button>
</div>