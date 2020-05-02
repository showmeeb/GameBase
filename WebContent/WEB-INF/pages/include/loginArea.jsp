
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- pop up window's background shadow -->
<div id="shadow"></div>
<!-- pop up window's background shadow for 2 stage pop up-->
<div id="secondShadow"></div>

<!-- Start login area -->
<div class="login-area popup-window hidden-window">
	<i class="fas fa-times close-btn"></i> <i class="fa fa-user"></i> <br />

	<i id="google-login-btn" class="fab fa-google"></i>
	<form id="login-form">
		<div class="input-group">
			<input type="text" name="account" id="account" value="${account} " required /> <span>帳號</span>
		</div>
		<div class="input-group">
			<input type="password" name="password" id="password" value="${password}" required /> <span>密碼</span>
		</div>

		<input type="checkbox" name="save" id="rm" /><label for="rm">Remember Me</label>

	</form>
	<button id="login-submit-btn" class="buttonL">登入</button>
	<button class="regist-btn buttonL">註冊</button>

</div>
<!-- End login area -->

<!-- Start regist area -->
<div class="regist-area popup-window hidden-window">
	<i class="fas fa-times close-btn"></i> <i class="fa fa-user"></i> <br />
	<form id="regist-form">
		<div class="input-group">
			<input type="text" name="account" required onblur="checkAcc()" /> <span>帳號</span>
		</div>
		<div class="input-group">
			<input type="password" name="password" required /> <span>密碼</span>
		</div>
		<div class="input-group">
			<input type="password" name="ck-password" required /> <span>確認密碼</span>
		</div>
		<div class="input-group">
			<input type="text" name="email" required /> <span>Email</span>
		</div>
	</form>
	<button id="regist-submit-btn">
		註冊<img src="<c:url value="/img/loading.gif"/>" />
	</button>
</div>
<!-- End regist area -->

<!-- Start auth code input area -->
<div class="auth-code-input-area popup-window hidden-window">
	<i class="fas fa-times close-btn hidden-window"></i> <i
		class="fab fa-keycdn"></i> <br />
	<form id="auth-code-form">
		<div class="input-group" style="display: none">
			<input type="text" name="account" required /> <span>帳號</span>
		</div>
		<div class="input-group">
			<input type="text" name="authCode" required /> <span>驗證碼</span>
		</div>
	</form>

	<button id="auth-code-submit-btn">送出驗證碼</button>
</div>
<!-- End auth code input area -->

<!-- Start regist-reply area -->
<div class="regist-reply-area popup-window hidden-window">
	<i class="fas fa-times close-btn"></i> <i class="fa fa-user"></i> <br />
	<div id="regist-msg">恭喜您，註冊成功！</div>
	<button id="return-login-btn">返回登入</button>
</div>
<!-- End regist-reply area -->

<!-- Start admin-broadcast area -->
<div class="admin-broadcast-area popup-window hidden-window">
	<i class="fas fa-times close-btn"></i><i class="fas fa-bullhorn"></i>
	<br/>
	<form id="admin-broadcast-form">
		<div class=input-group>
			<input type="text" name="broadcast" required /> <span>廣播</span>
		</div>
	</form>
		<button id="broadcast-submit-btn">
		發送<img src="<c:url value="/img/loading.gif"/>" />
	</button>
</div>
<!-- End admin-broadcast area -->