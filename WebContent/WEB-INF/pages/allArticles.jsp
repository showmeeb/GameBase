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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/fetch/3.0.0/fetch.min.js"></script>
<script src="<c:url value="/js/allArticles.js"/>"></script>

</head>

<body>
	<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<div id="table" class="w-50">
			<div id="bar">
				<form>
					<input type="text" id="sBar" placeholder="請輸入關鍵字"> <select
						id="option" name="forum">
						<option value="0">依主題搜尋</option>
						<option value="1">英雄聯盟</option>
						<option value="2">魔獸世界</option>
						<option value="3">魔物獵人</option>
					</select>
					<!-- 					<button id="s">查詢</button> -->
					<input type="button" id="s" value="查詢" />
				</form>
			</div>

			<div id="rDiv" class="float-left">
				<table>

					<td>
						<table id="rTable" class="table table-hover">

						</table>
					</td>
				</table>
				<!-- <input type="button" id="add" value="新增"> -->
				<span id="rMsg"></span>
				<div>
					<input type="button" id="toDel" class="d-none btn"
						data-toggle="modal" data-target="#myModal" value="確定刪除">
				</div>
				<div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example">
						<ul class="pagination" id="pageid">
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<div id="forum" class="float-right w-50">
			<iframe id="iframe" class="d-none w-100"
				style="height: 600px; pointer-events: none;" onload="dtopBar()"></iframe>
		</div>


		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">確定刪除</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					</div>
					<div class="modal-body">
						<div id="pDiv" class="container-fluid">
							<div class="prfile">
								<h5 id="forumname"></h5>
								<h5 id="articlename"></h5>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal"
								id="sureToDel">刪除</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</div>
		<button type="button" id="modalbtn" class="btn btn-primary d-none"
			data-toggle="modal" data-target="#exampleModal"></button>
	</main>

</body>
</html>