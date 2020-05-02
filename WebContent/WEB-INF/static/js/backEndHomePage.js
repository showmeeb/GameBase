
	$(document).ready(function(){
		console.log("a");			
		})
		
		$(".nav-link").click(function() {
			if ($(this).attr("id") == "21") {
				console.log("!");
				$("#admin-mamber").removeClass("d-none").addClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "22") {
				console.log("!");
				$("#admin-product").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "23") {
				console.log("!");
				$("#admin-content").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "24") {
				console.log("!");
				$("#admin-news").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "25") {
				console.log("!");
				$("#admin-ads").removeClass("d-none").addClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
			
				}
			if ($(this).attr("id") == "11") {
				console.log("!");
				$("#member-profile").removeClass("d-none").addClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "12") {
				console.log("!");
				$("#member-trade").removeClass("d-none").addClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-content").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
			if ($(this).attr("id") == "13") {
				console.log("!");
				$("#member-content").removeClass("d-none").addClass("d-block");
				$("#member-profile").addClass("d-none").removeClass("d-block");
				$("#member-trade").addClass("d-none").removeClass("d-block");
				$("#admin-mamber").addClass("d-none").removeClass("d-block");
				$("#admin-product").addClass("d-none").removeClass("d-block");
				$("#admin-content").addClass("d-none").removeClass("d-block");
				$("#admin-news").addClass("d-none").removeClass("d-block");
				$("#admin-ads").addClass("d-none").removeClass("d-block");
			}
		});

		$(".tag").click(function() {
			console.log("@@");
		});

		$(".btn-secondary").click(function() {
			if ($(this).attr("id") == "change-admin") {
				$("#admin-bar").removeClass("d-none");
				$("#member-bar").addClass("d-none");
			}
			;
			if ($(this).attr("id") == "change-member") {
				$("#member-bar").removeClass("d-none");
				$("#admin-bar").addClass("d-none");
			}
			;
		})

		$("#update-up").click(function(){
			var loginUser = JSON.parse(window.sessionStorage.getItem("loginUser"));
			console.log("loginUserID: " + loginUser.userId);
			var userId = loginUser.userId;
				
			$.ajax({
				url:'/GameBase/updateProfile/',
				type:'POST',
				success:function(data){
					window.location.href=data.url;
				}
			})
		})
		
//      $("#admin-bar").removeClass("d-none");
//			$("#member-bar").addClass("d-none");
//		}else{
//			$("#member-bar").removeClass("d-none");
//			$("#admin-bar").addClass("d-none");
//			}
