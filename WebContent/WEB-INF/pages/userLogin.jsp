<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="982957556355-9h99fuvvivi52g599iucre1v04ktheh0.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<%
		String account="";
		String password="";
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				String name=cookie.getName();
				if("account".equals(name)){
					account=cookie.getValue();
				}else if("password".equals(name)){
					password=cookie.getValue();
				}
			}
		}
	%>
	<form action="<c:url value="/loginact"/>" method="POST">
		Account:<input type="text" name="account">${requestScope.accerr}
		<br> Password:<input type="password" name="password">${requestScope.pwderr}
		<br> <input type="submit" value="Login">${requestScope.loginerr}<input type="checkbox" name="save"/>Remember Me
	</form>
	<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
	<a href="#" οnclick="signOut();">Sign out</a>

	<script>
		function onSignIn(googleUser) {
			// 客户端如果有需要的话可以通过profile来获取用户信息
			var profile = googleUser.getBasicProfile();
			// 传回后台验证，并获取userid
			var id_token = googleUser.getAuthResponse().id_token;
			console.log("ID Token: " + id_token);
			var xhr = new XMLHttpRequest();
			xhr.open('POST', 'googleVerify');
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xhr.onload = function(data) {
				if(eval(data)){
					$(location).attr('href','<c:url value="/index"/>');
				}
			};
			xhr.send('idtokenstr=' + id_token);
		};

		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function() {
				console.log('User signed out.');
			});
		}
	</script>
</body>
</html>