<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>this is ${forumName} forum page</title>
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
</head>
<body>
	<h1>主題：${forumName}</h1>
	<br />
	
	<!-- ＸＸＸ版　文章表單 -->
	
	<c:if test="${mbList==null}">
		<p>there is no article</p>
	</c:if>
	
	<c:if test="${mbList!=null}">
	
		<p>there is articles</p>
	<hr/>	
		<c:forEach items="${mbList}" var="item" varStatus="itemStatus">
		
			<div>
				<table>
					<tr>
						<td>this is a figure</td>
						<td><a href="<c:url value="/forum/${forumName}/${item.id}"/>">${item.boardTitle}</a><br />
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
					<tr>
						<td>最新回覆時間:</td>
						<td>?</td>
					</tr>
				</table>

			</div>
			
		</c:forEach>
	</c:if>
	
		<hr />
	<!--輸入區 -->
	<hr />
	<form action="<c:url value="/forum/${forumName}/add"/>" method="post">
		<table>
			<tr>
				<td><p>your account id:</p></td>
				<td><input type="text" name="accountId"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" name="articleTitle"></td>
			</tr>
		</table>

		<textarea name="content" id="editor">
<%--             ${mycontent} --%>
        </textarea>
		<p>
			<input type="submit" value="create new article">
		</p>
	</form>


	<script>
        ClassicEditor
            .create( document.querySelector( '#editor' ),{
                mediaEmbed:{
                	previewsInData:true
                    }
                } )
            .catch( error => {
                console.error( error );
            } );
        CKEDITOR.config.extraPlugins = 'myplugin,anotherplugin';
    </script>
<%-- 	<a href="<c:url value="/forum/${forumName}/add"/>">insert new parent article</a> --%>
</body>
</html>