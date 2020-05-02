
		$(document).ready(function() {
			$('#nameSpan').hide();
			$('.nameBut').css("display", "none");
			$('#nicknameSpan').css("display", "none");
			$('.nickBut').css("display", "none");
			$('#genderSpan').css("display", "none");
			$('.gBut').css("display", "none");
			$('#addressSpan').css("display", "none");
			$('.addBut').css("display", "none");
			$('#phoneSpan').css("display", "none");
			$('.pBut').css("display", "none");
			$('#ageSpan').css("display", "none");
			$('.aBut').css("display", "none");
			$('#imgSpan').css("display", "none");

			$("#member-profile").removeClass("d-none").addClass("d-block");

		});
		function showName() {
			$('#nameSpan').show();
			$('.nameBut').css("display", "block");
			$('#eName').hide();
		};
		function showNickName() {
			$('#nicknameSpan').css("display", "block");
			$('.nickBut').css("display", "block");
			$('#eNick').css("display", "none");
		};
		function showGender() {
			$('#genderSpan').css("display", "block");
			$('.gBut').css("display", "block");
			$('#eG').css("display", "none");
		};
		function showAddress() {
			$('#addressSpan').css("display", "block");
			$('.addBut').css("display", "block");
			$('#eAdd').css("display", "none");
		};
		function showPhone() {
			$('#phoneSpan').css("display", "block");
			$('.pBut').css("display", "block");
			$('#ePh').css("display", "none");
		};
		function showAge() {
			$('#ageSpan').css("display", "block");
			$('.aBut').css("display", "block");
			$('#eAge').css("display", "none");
		};
		function showImg() {
			$('#imgSpan').css("display", "block");
			$('#eImg').css("display", "none");
		};

		$.fn.serializeObject = function() {
			var formData = {};
			var formArray = this.serializeArray();
			for (var i = 0, n = formArray.length; i < n; ++i) {
				formData[formArray[i].name] = formArray[i].value;
			}
			return formData;
		}

		function editName() {

			var formdata = $('#formData').serializeObject();
			//console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			//console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveName",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {

					$('#nameShow').html('姓名: ' + data.name);
				}
			});
			$('#nameSpan').css("display", "none");
			$('.nameBut').css("display", "none");
			$('#eName').css("display", "block");
		}
		function editNickName() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/savenickName",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#nickNameShow').html('暱稱: ' + data.nickName);
				}
			});
			$('#nicknameSpan').css("display", "none");
			$('.nickBut').css("display", "none");
			$('#eNick').css("display", "block");
		}
		function editGender() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveGender",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#genderShow').html('性別: ' + data.gender);
				}
			});
			$('#genderSpan').css("display", "none");
			$('.gBut').css("display", "none");
			$('#eG').css("display", "block");
		}
		function editAddress() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveAddress",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#addressShow').html('地址: ' + data.address);
				}
			});
			$('#addressSpan').css("display", "none");
			$('.addBut').css("display", "none");
			$('#eAdd').css("display", "block");
		}

		function editPhone() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/savePhone",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#phoneShow').html('電話: ' + data.phone);
				}
			});
			$('#phoneSpan').css("display", "none");
			$('.pBut').css("display", "none");
			$('#ePh').css("display", "block");
		}

		function editAge() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveAge",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					$('#ageShow').html('年齡: ' + data.age);
					$('.aBut').css("display", "none");
					$('#eAge').css("display", "block");
				}
			});
			$('#ageSpan').css("display", "none");

		}

		function editImg() {

			var formdata = $('#formData').serializeObject();
			console.log(formdata);
			var UserProfile = JSON.stringify(formdata);
			console.log(UserProfile);
			$.ajax({
				url : "/GameBase/saveImg",
				data : UserProfile,
				type : "POST",
				contentType : "application/json",
				success : function(data) {
					//$('#imgShow').html("Image: " + data.img);
				}
			});
			$('#imgSpan').css("display", "none");

		}
