<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>this is XXX forum page</title>
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
						<td><a href="<c:url value="/${forumName}/${item.id}"/>">${item.boardTitle}</a><br />
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
	<br /> ${mycontent}
	<br />
	<hr />
	<h1>Classic editor</h1>
	<form action="XXXcontroller" method="post">
		<table>
			<tr>
				<td><p>your account id:</p></td>
				<td><input type="text" name="accountId"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" name="articletitle"></td>
			</tr>
			<tr>
				<td><p>article parent id:</p></td>
				<td><input type="text" name="articleParentId"></td>
			</tr>
			<tr>
				<td><p>article location:</p></td>
				<td><input type="text" name="articlelocation"></td>
			</tr>
		</table>

		<textarea name="content" id="editor">
            ${mycontent}
        </textarea>
		<p>
			<input type="submit" value="Submit">
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
</body>
</html>