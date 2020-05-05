
		//載入畫面--顯示sidebar選項
		$(document).ready(function() {
			$("#admin-member").removeClass("d-none").addClass("d-block");
		

		//載入畫面--顯示所有會員列表
		$(document).on("click","#allmembers",function() {
			$.ajax({
					url : "/GameBase/getAllMembers",
					dataType : "json",
					type : "POST",
					success : function(response) {

						var a = response.members

						var txt = " <thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

						for (let i = 0; i < response.members.length; i++) {
							txt += '<tr class="tr"><td scope="row"><input type="radio" class="del d-none" name="d" value="a[i].userId">';
							txt += "<td>" + a[i].userId;
							txt += "<td>" + a[i].account;
							txt += "<td>" + a[i].email;
							if (a[i].rankId == 2) {
								txt += "<td>" + "一般會員"
								} else if (a[i].rankId == 3) {
								txt += "<td>" + "高級會員"
								} else if (a[i].rankId == 4) {
								txt += "<td>" + "管理員"
							}
						}
						txt += "</tbody>";
						$('#rMsg').html("");
						$('#rTable').html(txt);
						$("#del").addClass("d-none").removeClass("d-block");
						$("#toDel").addClass("d-none").removeClass("d-block");
					}
				});
			})

		//刪除會員
		$(document).on("click", "#delmember", function() {

			if ($("#rMsg").text() == "") {
				$(".del").removeClass("d-none").addClass("d-block");
				$("#toDel").removeClass("d-none").addClass("d-block");
			} else {
			}

		})

		//搜尋會員
		
		$(document).on("click","#s",function(){		
			var rank = $("#option").val();
			var ac = $("#sBar").val();
			console.log(rank);
				if (rank == 0) {
					$.ajax({
						url : "GameBase/getuserbyacinallrank",
						datatype : "json",
						type : "POST",
						data : {
						ac : $("#sBar").val()
					    },
						success : function(response) {
							console.log("aaa");
							var a = response.members

							var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級</thead><tbody>";

							for (let i = 0; i < response.members.length; i++) {
								txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
								txt += "<td>" + a[i].userId;
								txt += "<td>"+ a[i].account;
								txt += "<td>" + a[i].email;
								if (a[i].rankId == 2) {
									txt += "<td>" + "一般會員"
									} else if (a[i].rankId == 3) {
									txt += "<td>" + "高級會員"
									} else if (a[i].rankId == 4) {
									txt += "<td>" + "管理員"
									}
								}
								txt += "</tbody>";
								$('#rTable').html(txt);
								$('#rMsg').html("");
								$("#del").addClass("d-none").removeClass("d-block");
								$("#toDel").addClass("d-none").removeClass("d-block");
							}

						})
					} else if (rank != 0) {
						$.ajax({

							url : "GameBase/getuserbyacinonerank",
							datatype : "json",
							type : "POST",
							data : {
								rank : $("#option").val(),
								ac : $("#sBar").val()
								},
							success : function(response) {
								console.log("qqq");
								var a = response.members

								if (response.members.length > 0) {
									var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級</thead><tbody>";

									for (let i = 0; i < response.members.length; i++) {
										txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
										txt += "<td>"+ a[i].userId;
										txt += "<td>"+ a[i].account;
										txt += "<td>"	+ a[i].email;
										if (a[i].rankId == 2) {
												txt += "<td>"+ "一般會員"
												} else if (a[i].rankId == 3) {
												txt += "<td>"+ "高級會員"
												} else if (a[i].rankId == 4) {
												txt += "<td>"+ "管理員"
												}
											}
											txt += "</tbody>";
											$('#rMsg').html("");
											$('#rTable').html(txt);
											$("#del").addClass("d-none").removeClass("d-block");
											$("#toDel").addClass("d-none").removeClass("d-block");
									} else {
										$('#rTable').html("");
										$('#rMsg').html("查無結果");
										$("#toDel").addClass("d-none").removeClass("d-block");
										}
									}

								})
							}
						})
		//點擊會員，彈跳會員資料						
		$(document).on("click", ".tr", function() {
			console.log("a" + $(this).children().eq(1).text());
			var id = $(this).children().eq(1).text();
			$.ajax({
				url : "GameBase/previewUserProfile",
				datatype : "json",
				type : "POST",
				data : {
					id : id
				},
				success : function(response) {
					console.log(response);
					$("#myModalLabel").text(response.userdata.account)
					if(response.articles.length>0){
						$("#articleNum").text("文章數 : "+response.articles.length);
						}else{
							$("#articleNum").text("文章數 : 0");
						}
					if(response.contents.length>0){
						$("#contentNum").text("回應數 : "+response.contents.length);
						}else{
							$("#contentNum").text("回應數 : 0");
						}
					
					if (response.profile!=null) {
						console.log("has profile");
						if(response.profile.nickName==null){
							$("#pName").html("名字 : 尚未更新");
							}else {
							$("#pName").html("暱稱 :" + response.profile.nickName);
						} 												
					}else{
						$("#pName").html("尚未新增個人資訊");
						console.log("no profile");
					}					
					if(response.profile.img!=null){
						$("#photo").attr("src",response.profile.img);
					}
				}
				
			})
			//讓動態視窗顯示
			$("#mal-btn").click();
		})
		
		//權限頁面
		$("#changeRank").click(function(){
			$("#bar").addClass("d-none");
			console.log("修改會員權限");
			$.ajax({
				url:"GameBase/getUserWithRank",
				dataType:"json",
				type:"POST",
				success:function(response){
					console.log(response);
					var a = response.members
					txt = "<thead><tr><th><th>會員ID<th>帳號<th>會員等級 <th>升級日期</thead><tbody>";

					for (let i = 0; i < response.members.length; i++) {
						txt += '<tr><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
						txt += "<td>"+ a[i].userId;
						txt += "<td>"+ a[i].account;
						if (a[i].rankId == 2) {
							txt += "<td>"+ "一般會員"
							} else if (a[i].rankId == 3) {
							txt += "<td>"+ "高級會員"
							}
						txt += "<td id='updateDate'>";
						}
						txt += "</tbody>";
						$('#rMsg').html("");
						$('#rankTable').html(txt);
						$("#del").addClass("d-none").removeClass("d-block");
						$("#toDel").addClass("d-none").removeClass("d-block");
					}
				})
			})
		})
