<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>this is forum list</title>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Font Awesome icons -->
<script src="https://kit.fontawesome.com/83bb506b46.js" crossorigin="anonymous"></script>
</head>
<body>
<i class="fa fa-pencil-square-o" aria-hidden="true"></i><!-- 編輯  -->
<i class="fa fa-plus" aria-hidden="true"></i><!-- 新增  -->
	<br />
	<!-- forum list -->
	<table id="forumTable">
		<c:forEach items="${forumList}" var="item" varStatus="itemStatus">
			<tr>
				<td>${item.id}</td>
				<td><a href="<c:url value="/forum/${item.forumName}"/>">${item.forumName}</td>
				<td>${item.forumFigure}</td>
			</tr>
		</c:forEach>

		<!-- new forum -->
		<!-- 				<tr> -->
		<%-- 					<td>${newForum.id}</td> --%>
		<%-- 					<td><a href="<c:url value="/forum/${newForum.forumName}"/>">${newForum.forumName}</td> --%>
		<%-- 					<td>${newForum.forumFigure}</td> --%>
		<!-- 				</tr> -->
		
	</table>

	<!-- insert new forum  -->
	<hr>
	<p>insert new forum</p>
	<div id=inputNewForum>
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
			<button id="submit">Post New Forum</button>
		</form>
	</div>
	<!-- include -->
	<%@include file="testuploadimg.jsp"%>
	
	<!-- create new article -->
<script>
$(document).ready(function(){
	
	//editor submit	
	$("#submit").click(function(){
		console.log("submit");
		
		
		//do function when editor has data and title has value
// 		if($("#forumName").val() && $("#forumFigure").val()){
			
			//ajax send data to controller
			$.ajax({
				url:"/forum/add",
				dataType:"json",
				type:"POST",
				cache: false,
	            data:{forumName: $("#forumName").val(),
	            	forumFigure: $("#forumFigure").val()
               		},
         		success : function(response) {
         			var txt = '<tr><td>'+response.newForum.id+'</td><td><a href="<c:url value="/forum/'
         				+response.newForum.forumName+'"/>">'+response.newForum.forumName+'</td><td>'
         				+response.newForum.forumFigure+'</td></tr>';
					$("#forumTable").append(txt);
         		}	
			})
// 		}
		
	})
});
	
</script>
</body>
</html>