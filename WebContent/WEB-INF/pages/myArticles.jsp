<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bootstrap JavaScript CDN-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="<c:url value="/js/myArticles.js"/>"></script>
</head>

<body>
	<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		我的文章

		<div id="bar" class="">
			
				<input type="text" id="sBar" placeholder="請輸入關鍵字">  <select
						id="option" name="forum">
					<option value="0">依主題搜尋</option>
					<option value="1">英雄聯盟</option>
					<option value="2">魔獸世界</option>
					<option value="3">魔物獵人</option>
				</select>
				<input type="button" id="s" value="查詢" />
			
		</div>
		<div>
			<span id="sp1"></span>
		</div>

		<div id="rDiv">
			<table>
				<td>
					<table id="cTable" class="table">

					</table>
				</td>
				<td>
					<table id="rTable" class="table table-hover">

					</table>

				</td>	
			</table>
			<!-- <input type="button" id="add" value="新增"> -->
	        <span id="rMsg"></span>
	        <div>
		  		<input type="button" id="toDel" class="d-none" value="確定刪除">
     		</div>
	      
		</div>
	</main>

</body>
</html>