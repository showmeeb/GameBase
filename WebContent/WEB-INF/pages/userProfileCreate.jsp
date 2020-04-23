<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserProfileCreate</title>
<link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>

<style type="text/css">
    body{
        text-align: center;
    }
    #user-photo {
        width:300px;
        height:300px;
        margin-top: 10px;
    }
    #photo {
        max-width:100%;
        max-height:350px;
    }
    .img-preview-box {
        text-align: center;
    }
    .img-preview-box > div {
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
</style>

</head>
<body>
<h4>您的資料</h4>

<label>Name: ${userProfile.name}</label><button onclick='showName()'>修改</button>
<span id="nameSpan">
<form id="nameForm">
	Name:<input type="text" name="name" id="name" >
</form>
<button onclick='editName()'>更新</button>
</span>
<br>

<label>Nickname: ${userProfile.nickName}</label><button onclick='showNickName()'>修改</button>
<span id="nicknameSpan">
<form id="nickNameForm">
	Nickname:<input type="text" name="nickName" id="nickName">
</form>
<button onclick='editNickName()'>更新</button>
</span>
<br>

<label>Gender: ${userProfile.gender}</label><button onclick='showGender()'>修改</button>
<span id="genderSpan">
<form id="genderForm">
	Gender:<input type="radio" name="gender" value="m" id="gender" >男 <input type="radio" name="gender" value="f" id="gender">女
</form>
<button onclick='editGender()'>更新</button>
</span>
<br>

<label>Address: ${userProfile.address}</label><button onclick='showAddress()'>修改</button>
<span id="addressSpan">
<form id="addressForm">
	Address:<input type="text" name="address" id="address">
</form>
<button onclick='editAddress()'>更新</button>
</span>
<br>

<label>Phone: ${userProfile.phone}</label><button onclick='showPhone()'>修改</button>
<span id="phoneSpan">
<form id="phoneForm">
	Phone:<input type="text" name="phone" id="phone">
</form>
<button onclick='editPhone()'>更新</button>
</span>
<br>

<label>Age: ${userProfile.age}</label><button onclick='showAge()'>修改</button>
<span id="ageSpan">
<form id="ageForm">
	Age:<input type="text" name="age" id="age">
</form>
<button onclick='editAge()'>更新</button>
</span>
<br>

<label>Image: ${userProfile.img}</label><button onclick='showImg()'>修改</button>
<span id="imgSpan">
<form id="imgForm">
	Image:<input type="text" name="img" id="img">
</form>
<button onclick='editImg()'>更新</button>
</span>
<br>

<script type="text/javascript"></script>
<button class="btn btn-primary" data-target="#changeModal" data-toggle="modal">上傳圖片</button><br/>
<div class="user-photo-box">
    <img id="user-photo" src="<c:url value="/img/001.png"/>">  
</div>
<div class="modal fade" id="changeModal" tabindex="-1" role="dialog" aria-hidden="true">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title text-primary">
            <i class="fa fa-pencil"></i>
                        更換頭像
            </h4>
        </div>
        <div class="modal-body">
            <p class="tip-info text-center">
                未選擇圖片
            </p>
            <div class="img-container hidden">
                <img src="" alt="" id="photo">
            </div>
            <div class="img-preview-box hidden">
                <hr>
                <span>圖片預覽:</span>

                <div class="img-preview img-preview-lg">
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <label class="btn btn-danger pull-left" for="photoInput">
            <input type="file" class="sr-only" id="photoInput" accept="image/*">
            <span>打開圖片</span>
            </label>
            <button class="btn btn-primary disabled" disabled="true" onclick="sendPhoto();">提交</button>
            <button class="btn btn-close" aria-hidden="true" data-dismiss="modal">取消</button>
        </div>
    </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/js/crop.js"/>"></script>



<script>

$(document).ready(function(){
	$('#nameSpan').css("display","none");
	$('#nicknameSpan').css("display","none");
	$('#genderSpan').css("display","none");
	$('#addressSpan').css("display","none");
	$('#phoneSpan').css("display","none");
	$('#ageSpan').css("display","none");
	$('#imgSpan').css("display","none");
	
});
function showName(){
	$('#nameSpan').css("display","block");
};
function showNickName(){
	$('#nicknameSpan').css("display","block");
};
function showGender(){
	$('#genderSpan').css("display","block");
};
function showAddress(){
	$('#addressSpan').css("display","block");
};
function showPhone(){
	$('#phoneSpan').css("display","block");
};
function showAge(){
	$('#ageSpan').css("display","block");
};
function showImg(){
	$('#imgSpan').css("display","block");
};

$.fn.serializeObject= function(){
	var formData = {};
	var formArray = this.serializeArray();
	for (var i = 0, n = formArray.length; i < n; ++i) {
		formData[formArray[i].name] = formArray[i].value;
	}
	return formData;
}

function editName(){
	
	var formdata=$('#nameForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveName",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var name=$('#name').val(data);
	console.log(data);
	},
});
$('#nameSpan').css("display","none");
window.location.reload();
}
function editNickName(){
	
	var formdata=$('#nickNameForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/savenickName",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var nickname=$('#nickName').val(data);
	console.log(data);
	}
});
$('#nicknameSpan').css("display","none");
window.location.reload();
}
function editGender(){
	
	var formdata=$('#genderForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveGender",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var gender=$('#gender').val(data);
	console.log(data);
	}
});
$('#genderSpan').css("display","none");
window.location.reload();
}
function editAddress(){
	
	var formdata=$('#addressForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveAddress",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var address=$('#address').val(data);
	console.log(data);
	}
});
$('#addressSpan').css("display","none");
window.location.reload();
}

function editPhone(){
	
	var formdata=$('#phoneForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/savePhone",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var phone=$('#phone').val(data);
	console.log(data);
	}
});
$('#phoneSpan').css("display","none");
window.location.reload();
}

function editAge(){
	
	var formdata=$('#ageForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveAge",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var age=$('#age').val(data);
	console.log(data);
	}
});
$('#ageSpan').css("display","none");
window.location.reload();
}

function editImg(){
	
	var formdata=$('#imgForm').serializeObject();
	console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveImg",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	var img=$('#img').val(data);
	console.log(data);
	}
});
$('#imgSpan').css("display","none");
window.location.reload();
}
</script>
</body>
</html>