<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.4/croppie.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.4/croppie.css">

</head>

<body>
    <div class="actions">
        <button class="file-btn">
            <span>上傳</span>
            <input type="file" id="upload" value="選擇圖片文件" />
        </button>
        <div class="crop">
            <div id="upload-demo"></div>
            <button class="upload-result">裁剪</button>
        </div>
        <div id="result"></div>
    </div>

    <script>
        $(function () {
            var $uploadCrop;

            function readFile(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $uploadCrop.croppie('bind', {
                            url: e.target.result
                        });
                    }

                    reader.readAsDataURL(input.files[0]);
                }
                else {
                    alert("Sorry - you're browser doesn't support the FileReader API");
                }
            }

            $uploadCrop = $('#upload-demo').croppie({
                viewport: {
                    width: 200,
                    height: 200,

                },
                boundary: {
                    width: 300,
                    height: 300
                }
            });

            $('#upload').on('change', function () {
                $(".crop").show();
                readFile(this);
            });
            $('.upload-result').on('click', function (ev) {
                $uploadCrop.croppie('result', 'canvas').then(function (resp) {
                    popupResult({
                        src: resp
                    });
                });
            });

            function popupResult(result) {
                var html;
                if (result.html) {
                }
                if (result.src) {
                    html = '<img src="' + result.src + '" />';
                }
                $("#result").html(html);
            }
        });

    </script>
</body>
</html>