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

<c:if test="${!empty ProfileId}">
<form action="<c:url value="/saveProfile"/>" method="POST"  enctype="multipart/form-data">
	<input type="hidden" name="userId" value="${userProfile.userId}">
	Name:<input type="text" name="name" value="${userProfile.name}"><br>
	Nickname:<input type="text" name="nickName" value="${userProfile.nickName}"><br>
	Gender:<input type="radio" name=gender value="m">男 <input type="radio" name=gender value="f">女<span>${userProfile.gender}</span><br>
	Address:<input type="text" name="address" value="${userProfile.address }"><br>
	Phone:<input type="text" name="phone" value="${userProfile.phone }"><br>
	Age:<input type="text" name="age" value="${userProfile.age }"><br>
	Image:<input type="text" name="img"><img src="${userProfile.img}"><br>
	<input type="submit">
</form>
<script type="text/javascript"></script>



</c:if>
<c:if test="${empty ProfileId}">
<form action="<c:url value="/saveProfile"/>" method="POST">
	<input type="hidden" name="userId" value="${UserData.userId}">	
	Name:<input type="text" name="name"><br>
	Nickname:<input type="text" name="nickName"><br>
	Gender:<input type="radio" name=gender value="m">男 <input type="radio" name=gender value="f">女<br>
	Address:<input type="text" name="address"><br>
	Phone:<input type="text" name="phone"><br>
	Age:<input type="text" name="age"><br>
	Image:<input type="text" name="img"><br>
	<input type="submit">
</form>
</c:if>
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


</body>
</html>