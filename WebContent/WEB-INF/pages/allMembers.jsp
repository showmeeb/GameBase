<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style type="text/css">
#main_back {
	position: relative;
	top: 57px;
	left: 230px;
	width: 75%;
}
</style>
</head>
<body>
	<main id="main_back">
		<div id="bar">
			<input type="text" id="sBar" placeholder="搜尋">
			<button id="s">查詢</button>
		</div>
		<div id="rDiv">
			<form id="rForm">
				<table id="rTable">


				</table>
				<!-- <input type="button" id="add" value="新增"> -->
			</form>
		</div>
	</main>
	<script type="text/javascript">
	$(document)
	.ready(
			function() {
				$.ajax({
					url : "/GameBase/getAllMembers",
					dataType:"json",
					type : "POST",
					success : function(response) {
						console.log(response.members.length);
						console.log("sWWs");
						console.log(response);
var a=response.members
						
						var txt = "<tr><th>會員ID<th>帳號<th>email<th>會員等級";


						for (let i = 0; i < response.members.length; i++) {
							txt += "<tr><td>" + response.members[i].userId;
							txt += "<td>" + response.members[i].account;
							txt += "<td>" + response.members[i].email;
							txt += "<td>" + response.members[i].rankId;
							txt += '<td><input type="button" id="update" value="修改">';
							txt += '<td><input type="button" id="delete" value="刪除">';
						}
						$('#rTable').html(txt);
					}
				});
	})

		$(document).ready(function() {
			$("#admin-mamber").removeClass("d-none").addClass("d-block");
		})
	</script>

</body>
</html>