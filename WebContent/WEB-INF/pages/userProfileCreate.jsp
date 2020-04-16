<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserProfileCreate</title>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<body>
	<h4>您的資料</h4>
	<form action="<c:url value="/insertProfile"/>" method="post">
		<input type="hidden" name="userId" id="userId"
			value="${UserData.userId}" /> 
		<input type="hidden" name="profileId" id="profileId"
			value="${userProfile.profileId}" /> 

		
		Name:<input type="text" name="name" />${userProfile.name}<br>
		
		Nickname:<input type="text" name="nickName" />${userProfile.nickName}<br>
	
		Gender:<input type="radio" name="gender" id="male" value="m"><span class="gender"><label for="male">男</label></span>
		<input type="radio" name="gender" id="female" value="f"><span class="gender"><label for="female">女</label></span>
		${userProfile.gender}<br>
		Age:<input type="text" name="age"/>
		${userProfile.age}<br>
		Address:<input type="text" name="address"/>
		${userProfile.address}<br>
		Phone:<input type="text" name="phone" />
		${userProfile.phone}<br>
		Img:<input type="text" name="img" value="Img" />
		<input type="submit" id="update" value="update">
</form>

<button id="show">更改資料</button>
<script type="text/javascript">
	$(document).ready(function () {
		$("input").css("display","none");
		$(".gender").css("display","none");
	});
	
//	$.fn.serializeObject = function() {
	//	var formData = {};
	//	var formArray = this.serializeArray();
	//	for (var i = 0, n = formArray.length; i < n; ++i) {
	//		formData[formArray[i].name] = formArray[i].value;
	//	}
	//	return formData;
//	};
	
	$('#show').click(function(){
		$("input").css("display","block");
		$(".gender").css("display","block");
	})
		
//	$('#update').click(function(){
//		var form1=$('#f1').serializeObject();
//		console.log(form1);
//		var form = JSON.stringify(form1);
//		console.log(form);
	//	$.ajax({
	//		url:"insert",
	//		dataType : "json",
		//	type:"POST",
		//	data : {
	//			form : form
		//	},
		//	success : function(response) {
		//		console.log(response);
		//		}
	//	});
		
		//})
		
	
</script>
</body>

</html>