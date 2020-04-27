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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9.10.12/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<style type="text/css">

#main_back {
	position: relative;
	top: 57px;
	right:6%;
	width: 75%;
	border:1px black solid;
}
</style>
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
<jsp:include page="include/backEndHomePage.jsp"></jsp:include>
<main id="main_back">
<h4>您的資料</h4>

<label id="nameShow">Name: ${userProfile.name}</label><button onclick='showName()'>修改</button>
<span id="nameSpan">
<form id="nameForm">
	Name:<input type="text" name="name" id="name" >
</form>
<button onclick='editName()'>更新</button>
</span>
<br>

<label id="nickNameShow">Nickname: ${userProfile.nickName}</label><button onclick='showNickName()'>修改</button>
<span id="nicknameSpan">
<form id="nickNameForm">
	Nickname:<input type="text" name="nickName" id="nickName">
</form>
<button onclick='editNickName()'>更新</button>
</span>
<br>

<label id="genderShow">Gender: ${userProfile.gender}</label><button onclick='showGender()'>修改</button>
<span id="genderSpan">
<form id="genderForm">
	Gender:<input type="radio" name="gender" value="m" id="gender" >男 <input type="radio" name="gender" value="f" id="gender">女
</form>
<button onclick='editGender()'>更新</button>
</span>
<br>

<label id="addressShow">Address: ${userProfile.address}</label><button onclick='showAddress()'>修改</button>
<span id="addressSpan">
<form id="addressForm">
	Address:<input type="text" name="address" id="address">
</form>
<button onclick='editAddress()'>更新</button>
</span>
<br>

<label id="phoneShow">Phone: ${userProfile.phone}</label><button onclick='showPhone()'>修改</button>
<span id="phoneSpan">
<form id="phoneForm">
	Phone:<input type="text" name="phone" id="phone">
</form>
<button onclick='editPhone()'>更新</button>
</span>
<br>

<label id="ageShow">Age: ${userProfile.age}</label><button onclick='showAge()'>修改</button>
<span id="ageSpan">
<form id="ageForm">
	Age:<input type="text" name="age" id="age">
</form>
<button onclick='editAge()'>更新</button>
</span>
<br>

<label id="imgShow">Image:<img src="${userProfile.img}"></label><button onclick='showImg()'>修改</button>
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
    <img id="user-photo" src="<c:url value="${userProfile.img}"/>">  
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
            <form id="imgForm">
            <button class="btn btn-primary disabled" disabled="true" onclick="sendPhoto()">提交</button>
            <button class="btn btn-close" aria-hidden="true" data-dismiss="modal">取消</button>
            
        </div>
    </div>
 
</div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/js/crop.js"/>"></script>

</main>

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
	//console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	//console.log(UserProfile);
$.ajax({
	url:"/GameBase/saveName",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	
	$('#nameShow').html('Name: '+data.name);
	}
});
$('#nameSpan').css("display","none");

}
function editNickName(){
	
	var formdata=$('#nickNameForm').serializeObject();
	//console.log(formdata);
	var UserProfile=JSON.stringify(formdata);
	//console.log(UserProfile);
$.ajax({
	url:"/GameBase/savenickName",
	data:UserProfile,
	type : "POST",
	contentType:"application/json",
	success:function(data){
	$('#nickNameShow').html('Nickname: ' + data.nickName);
	}
});
$('#nicknameSpan').css("display","none");

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
	$('#genderShow').html("Gender: " + data.gender);
	}
});
$('#genderSpan').css("display","none");

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
	$('#addressShow').html("Address: " + data.address);
	}
});
$('#addressSpan').css("display","none");
//window.location.reload();
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
	$('#phoneShow').html("Phone: " + data.phone);
	}
});
$('#phoneSpan').css("display","none");
//window.location.reload();
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
	$('#ageShow').html("Age: " + data.age);
	}
});
$('#ageSpan').css("display","none");
//window.location.reload();
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
	//$('#imgShow').html("Image: " + data.img);
	}
});
$('#imgSpan').css("display","none");
//window.location.reload();
}
</script>
</body>
</html>