<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="og:url" content="http://52.191.198.56:8080/GameBase" />
    <meta property="og:type" content="website" />
    <meta property="og:title" content="Your Website Title" />
    <meta property="og:description" content="Your description" />
    <meta property="og:image" content="https://www.your-domain.com/path/image.jpg" />
    <!-- (fb)使用 og:*** 中繼標籤調整您的連結預覽；og:url 和 data-href 應使用相同的網址。 -->
</head>
<body>
<!-- fb分享,data-href 改fb要分享的網址 -->
<div id="fb-root"></div>
    <script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v6.0&appId=220888409177683&autoLogAppEvents=1"></script>
    <div class="fb-share-button" data-href="http://52.191.198.56:8080/GameBase" data-layout="button_count"
        data-size="large"><a target="_blank"
            href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse"
            class="fb-xfbml-parse-ignore">分享</a></div>
<!-- data-href 改fb要分享的網址 -->

<!-- line分享,data-url 改line要分享的網址 -->
    <div class="line-it-button" data-lang="zh_Hant" data-type="share-a" data-ver="3" data-url="http://52.191.198.56:8080/GameBase" data-color="default" data-size="large" data-count="true" style="display: none;"></div>
 <script src="https://d.line-scdn.net/r/web/social-plugin/js/thirdparty/loader.min.js" async="async" defer="defer"></script>
</body>
</html>
