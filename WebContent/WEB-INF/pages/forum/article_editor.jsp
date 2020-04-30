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
				<td><p>your account id:</p></td>
				<td><input type="text" id="accountId" name="accountId"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" id="articleTitle" name="articleTitle" value="${title.titleName}" disabled="disabled"></td>
			</tr>
			</c:if>
			<!-- title is empty -->
			<c:if test="${empty title.titleName}">
			<tr>
				<td><p>your account id:</p></td>
				<td><input type="text" id="accountId" name="accountId"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" id="articleTitle" name="articleTitle"></td>
			</tr>
			</c:if>
		</table>
		
		<!-- CKeditor -->
		<textarea name="content" id="testeditor"></textarea>

		<script>
		editor = ClassicEditor
            	.create( document.querySelector( '#testeditor' ),{
            	    mediaEmbed:{previewsInData:true	},
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
	<button id="submit">Post</button>
	<button id="update" class="hidden-window">Update</button>
</div>