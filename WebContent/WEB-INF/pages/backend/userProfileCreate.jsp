<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserProfileCreate</title>

<link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.form-control{
	width:800px;
	margin:auto;
}
#yourData{
	font-size:45px;
	margin-top: 30px;
	margin-bottom:20px;
}
.form-group-lg{
	font-size: 25px;

}
button{
	margin:auto;
	width:100px;
	height:40px;
	font-size:23px;
}
input{
	margin:auto;
	width:100px;
	height:40px;
	font-size:23px;
}
body {
	text-align: center;
}

#user-photo {
	width: 300px;
	height: 300px;
	margin-top: 10px;
}

#photo {
	max-width: 100%;
	max-height: 350px;
}

.img-preview-box {
	text-align: center;
}

.img-preview-box>div {
	display: inline-block;;
	margin-right: 10px;
}

.img-preview {
	overflow: hidden;
}

.img-preview-box .img-preview-lg {
	width: 150px;
	height: 150px;
}

.img-preview-box .img-preview-md {
	width: 100px;
	height: 100px;
}

.img-preview-box .img-preview-sm {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
#div-left{
	width:1000px;
	line-height: 50px;
	padding:auto;
	float:left;
}
#div-right{
	width:200px;
	line-height: 50px;
	padding:auto;
	float:right;
}


</style>

</head>
<body>
	<jsp:include page="../include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<h1 id="yourData">您的資料</h1>
		<hr>
		<div class="container">
		<form id="formData">
		
			<div id="div-left" class="form-group form-group-lg">
				<div id="nameShow" class="user-data-tag-area">姓名:${userProfile.name}</div>
				<div id="nameSpan"><input type="text" class="form-control input-lg" name="name" id="name"></div>
			</div>
			
			
			<div id="div-right">
				<input type="button" onclick='showName()' class="btn btn-info" id="eName" value="修改">	
				<button class="btn btn-primary nameBut" id="up" onclick='editName()'>更新</button>
			</div>
			<hr>
			<div class="form-group form-group-lg">
				<div id="nickNameShow" class="user-data-tag-area">暱稱:${userProfile.nickName}</div>
				<div id="nicknameSpan"><input type="text" class="form-control input-lg" name="nickName" id="nickName"></div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showNickName()' class="btn btn-info" id="eNick" value="修改">
				<button class="btn btn-primary nickBut" onclick='editNickName()'>更新</button>
			</div>
			<hr>
			<div class="form-group form-group-lg">
				<div id="genderShow">性別:${userProfile.gender}</div>
				<div id="genderSpan">
					<input type="radio" name="gender" value="M" id="gender">男
					<input type="radio" name="gender" value="F" id="gender">女
				</div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showGender()' class="btn btn-info" id="eG" value="修改">
				<button class="btn btn-primary gBut" onclick='editGender()'>更新</button>
			</div>
			<hr>
			<div class="form-group form-group-lg">
				<div id="addressShow">地址:${userProfile.address}</div>
				<div id="addressSpan"><input type="text" class="form-control input-lg" name="address" id="address"></div>			
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showAddress()' class="btn btn-info" id="eAdd" value="修改">
				<button class="btn btn-primary addBut" onclick='editAddress()'>更新</button>
			</div>
			<hr>	
			<div class="form-group form-group-lg">
				<div id="phoneShow">電話:${userProfile.phone}</div>
				<div id="phoneSpan"><input type="text" class="form-control input-lg" name="phone" id="phone"></div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showPhone()' class="btn btn-info" id="ePh" value="修改">
				<button class="btn btn-primary pBut" onclick='editPhone()'>更新</button>
			</div>
			<hr>
			<div class="form-group form-group-lg">
				<div id="ageShow">年齡:${userProfile.age}</div>
				<div id="ageSpan"><input type="text" class="form-control input-lg" name="age" id="age"></div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showAge()' class="btn btn-info" id="eAge" value="修改">
				<button class="btn btn-primary aBut" onclick='editAge()'>更新</button>
			</div>
			<hr>
		</form>	
			<div id="imgShow" class="form-group form-group-lg">
			大頭貼:<img src="${userProfile.img}">
			</div>


			<button onclick='showImg()' class="btn btn-info" id="eImg">修改</button>
			<div id="imgSpan">
				<button class="btn btn-primary" data-target="#changeModal"
					data-toggle="modal">上傳圖片</button>
			</div>

			<div class="modal fade" id="changeModal" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title text-primary">
								<i class="fa fa-pencil"></i> 更換頭像
							</h4>
						</div>
						<div class="modal-body">
							<p class="tip-info text-center">未選擇圖片</p>
							<div class="img-container hidden">
								<img src="" alt="" id="photo">
							</div>
							<div class="img-preview-box hidden">
								<hr>
								<span>圖片預覽:</span>

								<div class="img-preview img-preview-lg"></div>
							</div>
						</div>
						<div class="modal-footer">
							<label class="btn btn-danger pull-left" for="photoInput">
								<input type="file" class="sr-only" id="photoInput"
								accept="image/*"> <span>打開圖片</span>
							</label>
							<button class="btn btn-primary disabled" disabled="true"
								onclick="sendPhoto();">提交</button>
							<button class="btn btn-close" aria-hidden="true"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			
			</div>
			

			<script type="text/javascript" src="<c:url value="/js/crop.js"/>"></script>

		</div>
	</main>

	<script>
		$(document).ready(function() {
			$('#nameSpan').hide();
			$('.nameBut').css("display", "none");
			$('#nicknameSpan').css("display", "none");
			$('.nickBut').css("display", "none");
			$('#genderSpan').css("display", "none");
			$('.gBut').css("display", "none");
			$('#addressSpan').css("display", "none");
			$('.addBut').css("display", "none");
			$('#phoneSpan').css("display", "none");
			$('.pBut').css("display", "none");
			$('#ageSpan').css("display", "none");
			$('.aBut').css("display", "none");
			$('#imgSpan').css("display", "none");

		});
		function showName() {
			$('#nameSpan').show();
			$('.nameBut').css("display", "block");
			$('#eName').hide();
		};
		function showNickName() {
			$('#nicknameSpan').css("display", "block");
			$('.nickBut').css("display", "block");
			$('#eNick').css("display", "none");
		};
		function showGender() {
			$('#genderSpan').css("display", "block");
			$('.gBut').css("display", "block");
			$('#eG').css("display", "none");
		};
		function showAddress() {
			$('#addressSpan').css("display", "block");
			$('.addBut').css("display", "block");
			$('#eAdd').css("display", "none");
		};
		function showPhone() {
			$('#phoneSpan').css("display", "block");
			$('.pBut').css("display", "block");
			$('#ePh').css("display", "none");
		};
		function showAge() {
			$('#ageSpan').css("display", "block");
			$('.aBut').css("display", "block");
			$('#eAge').css("display", "none");
		};
		function showImg() {
			$('#imgSpan').css("display", "block");
			$('#eImg').css("display", "none");
		};

		$.fn.serializeObject = function() {
			var formData = {};
			var formArray = this.serializeArray();
			for (var i = 0, n = formArray.length; i < n; ++i) {
				formData[formArray[i].name] = formArray[i].value;
			}
			return formData;
		}

		function editName() {

			var formdata = $('#formData').serializeObject();
			//console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			//console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveName",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {

					$('#nameShow').html('姓名: ' + data.name);
				}
			});
			$('#nameSpan').css("display", "none");
			$('.nameBut').css("display", "none");
			$('#eName').css("display", "block");
		}
		function editNickName() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/savenickName",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#nickNameShow').html('暱稱: ' + data.nickName);
				}
			});
			$('#nicknameSpan').css("display", "none");
			$('.nickBut').css("display", "none");
			$('#eNick').css("display", "block");
		}
		function editGender() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveGender",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#genderShow').html('性別: ' + data.gender);
				}
			});
			$('#genderSpan').css("display", "none");
			$('.gBut').css("display", "none");
			$('#eG').css("display", "block");
		}
		function editAddress() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveAddress",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#addressShow').html('地址: ' + data.address);
				}
			});
			$('#addressSpan').css("display", "none");
			$('.addBut').css("display", "none");
			$('#eAdd').css("display", "block");
		}

		function editPhone() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/savePhone",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#phoneShow').html('電話: ' + data.phone);
				}
			});
			$('#phoneSpan').css("display", "none");
			$('.pBut').css("display", "none");
			$('#ePh').css("display", "block");
		}

		function editAge() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveAge",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#ageShow').html('年齡: ' + data.age);
					$('.aBut').css("display", "none");
					$('#eAge').css("display", "block");
				}
			});
			$('#ageSpan').css("display", "none");

		}

		function editImg() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveImg",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					//$('#imgShow').html("Image: " + data.img);
				}
			});
			$('#imgSpan').css("display", "none");

		}
	</script>
</body>
</html>