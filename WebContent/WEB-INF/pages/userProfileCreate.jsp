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
<link rel="stylesheet"
	href="<c:url value="/css/userProfileCreate.css"/>">
<script src="<c:url value="/js/userProfileCreate.js"/>"></script>


</head>
<body>
	<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
	<main id="main_back">
		<h1 id="yourData">您的資料</h1>
		<hr>
		<div class="container">
		<form id="pwd-form">
		<div class="form-group form-group-lg">
				<div id="pwdShow" class="user-data-tag-area">密碼:********</div>
				<div id="pwdSpan">新密碼<input type="password" class="form-control input-lg" name="password" id="pwd"><br>
					確認密碼<input type="password" class="form-control input-lg" name="pwd2" id="pwd2">
				</div>
			</div>
			<div >
				<input type="button" class="btn btn-info pwdBtn" value="修改">
				<button class="btn btn-primary pwdUpdate">更新</button>
			</div>
		</form>
		<hr>
		<form id="formData">
			<div class="form-group form-group-lg">
				<div id="nameShow" class="user-data-tag-area">姓名:${userProfile.name}</div>
				<div id="nameSpan"><input type="text" class="form-control input-lg" name="name" id="name"></div>
			</div>
			<div class="user-data-btn-area">
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
				<div id="phoneShow">手機號碼:${userProfile.phone}</div>
				<div id="phoneSpan"><input type="text" class="form-control input-lg" name="phone" id="phone" placeholder="Ex:0955667777" onblur="checkPhone()" maxlength="10"></div>
				<div id="phoneErr"></div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showPhone()' class="btn btn-info" id="ePh" value="修改">
				<button id="edP" class="btn btn-primary pBut" onclick='editPhone()'>更新</button>
			</div>
			<hr>
			<div class="form-group form-group-lg">
				<div id="ageShow">年齡:${userProfile.age}</div>
				<div id="ageSpan"><input type="text" class="form-control input-lg" name="age" id="age" placeholder="Ex:25(最小為10歲 最多100歲)" onblur="checkAge()" maxlength="3"></div>
				<div id="ageErr"></div>
			</div>
			
			<div class="user-data-btn-area">
				<input type="button" onclick='showAge()' class="btn btn-info" id="eAge" value="修改">
				<button id="edA" class="btn btn-primary aBut" onclick='editAge()'>更新</button>
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


</body>
</html>