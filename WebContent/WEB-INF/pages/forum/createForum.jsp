<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>create new forum</title>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Font Awesome icons -->
<script src="https://kit.fontawesome.com/83bb506b46.js" crossorigin="anonymous"></script>
<!-- upload img css and impot -->
<link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
	<h1>Create New Forum</h1>
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
		<button id="submit">Post</button>
	</form>
	<script>
		$(document).ready(function() {

		//editor submit	
		$("#submit").click(function() {
			console.log("submit");
			//do function when editor has data and title has value
			// 		if($("#forumName").val() && $("#forumFigure").val()){

			//ajax send data to controller
			$.ajax({url : "/forum/add",
				dataType : "json",
				type : "POST",
				cache : false,
				data : {forumName : $("#forumName").val(),
						forumFigure : $("#forumFigure").val()
						},
				success : function(response) {
					var txt = '<tr><td>'+ response.newForum.id
						+ '</td><td><a href="<c:url value="/forum/'
         				+response.newForum.forumName+'"/>">'
						+ response.newForum.forumName
						+ '</td><td>'
						+ response.newForum.forumFigure
						+ '</td></tr>';
					$("#forumTable").append(txt);
				}
			})
		})
	});
	</script>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var initCropperInModal = function(img, input, modal){
        var $image = img;
        var $inputImage = input;
        var $modal = modal;
        var options = {
            aspectRatio: 1,
            viewMode: 2,
            preview: '.img-preview'
        };
        var saveData = {};
        var URL = window.URL || window.webkitURL;
        var blobURL;
        $modal.on('show.bs.modal',function () {
            if(!$inputImage.val()){
                $inputImage.click();
            }
        }).on('shown.bs.modal', function () {
            $image.cropper( $.extend(options, {
                ready: function () {
                    if(saveData.canvasData){
                        $image.cropper('setCanvasData', saveData.canvasData);
                        $image.cropper('setCropBoxData', saveData.cropBoxData);
                    }
                }
            }));
        }).on('hidden.bs.modal', function () {
            saveData.cropBoxData = $image.cropper('getCropBoxData');
            saveData.canvasData = $image.cropper('getCanvasData');
            $image.cropper('destroy').attr('src',blobURL);
        });
        if (URL) {
            $inputImage.change(function() {
                var files = this.files;
                var file;
                if (!$image.data('cropper')) {
                    return;
                }
                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {
    
                        if(blobURL) {
                            URL.revokeObjectURL(blobURL);
                        }
                        blobURL = URL.createObjectURL(file);
                        $image.cropper('reset').cropper('replace', blobURL);
                        $('.img-container').removeClass('hidden');
                        $('.img-preview-box').removeClass('hidden');
                        $('#changeModal .disabled').removeAttr('disabled').removeClass('disabled');
                        $('#changeModal .tip-info').addClass('hidden');
    
                    } else {
                        window.alert('請選擇一個圖像文件！');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).addClass('disabled');
        }
    }

    var sendPhoto = function(){
    	$('#photo').cropper('getCroppedCanvas',{
            width:300,
            height:300
        }).toBlob(function(blob){
        	var formData = new FormData();
        	formData.append('theFile', blob);
            $('#user-photo').attr('src',URL.createObjectURL(blob));
            $('#changeModal').modal('hide');
            $.ajax('uploadImg', {
                method: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function() {
                	Swal.fire({
                		  title: '上傳成功!',
                		  icon: 'success',
                		  timer: 1500,
                		})
                },
                error: function() {
                	Swal.fire({
                		  title: '上傳失敗...',
                		  icon: 'error',
                		  timer: 1500
                		})
                }
            });
        });
    	
    }

    $(function(){
        initCropperInModal($('#photo'),$('#photoInput'),$('#changeModal'));
    });
</script>
</body>
</html>