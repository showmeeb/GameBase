<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
.sendMail img {display: none}
</style>
<title>Register Page</title>
</head>
<body>
<div class="sendMail"><img src="<c:url value="/img/ajax-loader.gif"/>" /></div>
<div class="register">
	<form id="reg_form">
		<div class="reg_input">
			<span>帳號:</span>
			<input type="text" name="account" required onblur="checkAcc()"/>
			<span id="ae" class="form-status"></span>
		</div>
		<div class="reg_input">
			<span>密碼:</span>
			<input type="password" name="password" required/>
			<span id="pe1" class="form-status"></span>
		</div>
		<div class="reg_input">
			<span>密碼(檢查):</span>
			<input type="password" name="password_c" required/>
			<span id="pe2" class="form-status"></span>
		</div>
		<div class="reg_input">
			<span>信箱:</span>
			<input type="email" name="email" required/>
			<span id="ee" class="form-status"></span>
		</div>
	</form>
	<button id="reg_sub_btn" onclick="register()">註冊</button>
</div>

<script type="text/javascript">
	function register(){
		var inputEmptyCheck = true;
		$("#reg_form .reg_input input").each(function(){
			if($(this).val() == ""){
				inputEmptyCheck = false;
				}
			});
		if(inputEmptyCheck){
			var inputCheck = true;
			$("#reg_form .reg_input .form-status").each(function(){
				if(!($(this).text() == "OK")){
					console.log($(this).text());
					inputCheck = false;
					}
				});
			}
		if(inputCheck){
			$("#reg_sub_btn").attr("disabled", true);
			var formdata = $("#reg_form").serializeObject();
			var UserData = JSON.stringify(formdata);
			$(".register").fadeOut("slow");
			
			$.ajax({
				url:"/GameBase/registerAjax",
				data: UserData,
				type : "POST",
				contentType : "application/json",
				success : function(data){
						if(data.status){
							$(".sendMail").html(data.sendPage);
							}
						}
				});
			}
		}
	
	$.fn.serializeObject = function () {
  		var formData = {};
  		var formArray = this.serializeArray();
   		for(var i = 0, n = formArray.length; i < n; ++i){
    		formData[formArray[i].name] = formArray[i].value;
  			}
   		return formData;
		};

	$(document).ajaxStart(function() {
			$(".sendMail img").show();
		});
		
	$(document).ajaxSuccess(function() {
			$(".sendMail img").hide();
		});

	$("input[name='account']").change(function(){
		 var re = /^[a-zA-Z]{1}[a-zA-Z0-9]{7,15}$/;
		 if(re.test($(this).val())){
			 $(this).siblings("#ae").html("OK");
			 }
		 else if($(this).val() == ""){
			 $(this).siblings("#ae").html("<em>帳號不得為空</em>");
			 }
		 else{
			 $(this).siblings("#ae").html("<em>格式不符,第一個字須為英文字母,長度至少8-16</em>");
			 }
		});

	$("input[name='password']").change(function(){
		 var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z\d]{8,15}$/;
		 if(re.test($(this).val())){
			 $(this).siblings("#pe1").html("OK");
			 }
		 else if($(this).val() == ""){
			 $(this).siblings("#pe1").html("<em>密碼不得為空</em>");
			 }
		 else{
			 $(this).siblings("#pe1").html("<em>格式不符,需包含大小寫英文字母與數字,長度至少8-16</em>");
			 }
		});

	$("input[name='password_c']").change(function(){
		 var pwd=$(this).parent().prev().children("input[name='password']").val();
		 if(pwd == $(this).val()){
			 $(this).siblings("#pe2").html("OK");
			 }
		 else if($(this).val() == ""){
			 $(this).siblings("#pe2").html("<em>不得為空</em>");
			 }
		 else{
			 $(this).siblings("#pe2").html("<em>與密碼不符,請重新輸入</em>");
			 }
		});

	$("input[name='email']").change(function(){
		 var re = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		 if( re.test($(this).val())){
			 $(this).siblings("#ee").html("OK");
			 }
		 else if($(this).val() == ""){
			 $(this).siblings("#ee").html("<em>不得為空</em>");
			 }
		 else{
			 $(this).siblings("#ee").html("<em>格式錯誤,請重新輸入</em>");
			 }
		});
		
	function checkAcc(){
		if($("#ae").text() == "OK"){
			var acc = $("input[name='account']").serializeObject();
			$.ajax({
				url:"/GameBase/checkAcc",
				data:acc,
				type:"GET",
				contentType : "application/json",
				success:function(status){
					if(status.result){
						a=false;
						$("#ae").html("帳號已被註冊,請換一個");
						}
					}
				});
			}
		}

</script>
</body>
</html>