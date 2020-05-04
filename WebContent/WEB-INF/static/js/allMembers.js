
		//載入畫面--顯示sidebar選項
		$(document).ready(function() {
			$("#admin-mamber").removeClass("d-none").addClass("d-block");
		})

		//載入畫面--顯示所有會員列表
		$(document)
				.on(
						"click",
						"#allmembers",
						function() {
							$
									.ajax({
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
												;
											}
											txt += "</tbody>";
											$('#rMsg').html("");
											$('#rTable').html(txt);
											$("#del").addClass("d-none")
													.removeClass("d-block");
											$("#toDel").addClass("d-none")
													.removeClass("d-block");
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
		$("#s")
				.click(
						function() {

							var rank = $("#option").val();
							var ac = $("#sBar").val();

							if (rank == 0) {
								console.log(rank);
								$
										.ajax({
											url : "GameBase/getuserbyacinallrank",
											datatype : "json",
											type : "POST",
											data : {
												ac : $("#sBar").val()
											},
											success : function(response) {
												console.log("aaa");
												var a = response.members

												var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

												for (let i = 0; i < response.members.length; i++) {
													txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
													txt += "<td>" + a[i].userId;
													txt += "<td>"
															+ a[i].account;
													txt += "<td>" + a[i].email;
													if (a[i].rankId == 2) {
														txt += "<td>" + "一般會員"
													} else if (a[i].rankId == 3) {
														txt += "<td>" + "高級會員"
													} else if (a[i].rankId == 4) {
														txt += "<td>" + "管理員"
													}
													;
												}
												txt += "</tbody>";
												$('#rTable').html(txt);
												$('#rMsg').html("");
												$("#del").addClass("d-none")
														.removeClass("d-block");
												$("#toDel").addClass("d-none")
														.removeClass("d-block");
											}

										})
							} else if (rank != 0) {
								console.log(rank);
								$
										.ajax({

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
													var txt = "<thead><tr><th><th>會員ID<th>帳號<th>email<th>會員等級 </thead><tbody>";

													for (let i = 0; i < response.members.length; i++) {
														txt += '<tr class="tr"><td scope="row"><input type="radio"  class="del d-none" name="d" value="a[i].userId">';
														txt += "<td>"
																+ a[i].userId;
														txt += "<td>"
																+ a[i].account;
														txt += "<td>"
																+ a[i].email;
														if (a[i].rankId == 2) {
															txt += "<td>"
																	+ "一般會員"
														} else if (a[i].rankId == 3) {
															txt += "<td>"
																	+ "高級會員"
														} else if (a[i].rankId == 4) {
															txt += "<td>"
																	+ "管理員"
														}
														;

													}
													txt += "</tbody>";
													$('#rMsg').html("");
													$('#rTable').html(txt);
													$("#del")
															.addClass("d-none")
															.removeClass(
																	"d-block");
													$("#toDel").addClass(
															"d-none")
															.removeClass(
																	"d-block");
												} else {
													$('#rTable').html("");
													$('#rMsg').html("查無結果");
													$("#toDel").addClass(
															"d-none")
															.removeClass(
																	"d-block");
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

					if (response.profile!=null) {
						console.log("has profile");
						$("#pName").html("名字 :" + response.profile.name);

					} else {
						console.log("no profile");
						$("#pName").html("尚未新增個人資訊");
					}
				}
			})
			//讓動態視窗顯示
			$("#mal-btn").click();
		})
