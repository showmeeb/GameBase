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
        	var formData = $('#imgForm').serializeObject();
        	formData.append('theFile', blob);
        	var UserProfile = JSON.stringify(formData);
            $('#user-photo').attr('src',URL.createObjectURL(blob));
            $('#changeModal').modal('hide');
            $.ajax('http://localhost:8080/GameBase/uploadImg', {
                method: "POST",
                data: UserProfile,
                contentType:false,
                processData: false,
                success: function(data) {
                	console.log(data)
                	if(data){
                		Swal.fire({
                  		  title: '上傳成功!',
                  		  icon: 'success',
                  		})
                	}else{
                		Swal.fire({
                  		  title: '上傳失敗...',
                  		  icon: 'error',
                  		})
                		
                	}
                	
                },
                error: function() {
                	
                }
            });
        });
    	
    }

    $(function(){
        initCropperInModal($('#photo'),$('#photoInput'),$('#changeModal'));
    });