		//    sideBar選項
		$(document).ready(function() {
			$("#member-content").removeClass("d-none").addClass("d-block");
		

		//按下所有文章顯示列表
		$(document).on("click","#myArticles",
				function() {
					var userId=$("#userId").text();
					console.log(userId);
					console.log(typeof userId);
					
							$.ajax({
								type : "POST",
								url : "GameBase/getMyArticles",
								dataType : "json",
								data:{
									id:userId
									},
								beforesend : function() {
									$('#rTable').html("");
									$('#cTable').html("");
									},
								success : function(response) {
									console.log("hh");
									var rTable = "<thead><tr><th>文章ID<th>主題<th>文章標題<th>發文時間</thead><tbody>";
									var cTable = "<thead><tr><th></thead><tbody>"
									for (let i = 0; i < response.articles.length; i++) {
									rTable += '<tr class="tr"><td scope="row">'+ (i+1);
									if (response.articles[i].forumId == 1) {
										rTable += "<td>英雄聯盟"
										} else if (response.articles[i].forumId == 2) {
										rTable += "<td>魔獸世界"
										} else if (response.articles[i].forumId == 3) {
										rTable += "<td>魔物獵人"
											}
									rTable += "<td>"+ response.articles[i].titleName;
									rTable += "<td>"+ response.articles[i].createTime;
									rTable += "<td>"+ response.articles[i].titleId;
									rTable += "<td>"+ response.articles[i].forumId;
									cTable += '<tr><td><input type="radio" class="del d-none" name="d" value="a[i].userId">'
										}
									$('#rTable').html(rTable);
									$('#cTable').html(cTable);
									$("#del").addClass("d-none").removeClass("d-block");
									$("#toDel").addClass("d-none").removeClass("d-block");
										},
												});
									})
									
									
				$(document).on("click","#s",function() {
				var forum = $("#option").val();
				var title = $("#sBar").val();
				var userId=$("#userId").text();
				console.log("forum:"+forum+"title:"+title+"userId:"+userId);
					$.ajax({
						type : "POST",
						url : "GameBase/getMemberArticles",
						dataType : "json",
						data : {
							forum : forum,
							title : title,
							id:userId
							},
						beforesend : function() {
							$('#rTable').html("");
							$('#cTable').html("");
							},
						success : function(response) {
							console.log(response);
							var rTable = "<thead><tr><th>文章ID<th>主題<th>文章標題<th>發文時間</thead><tbody>";
							var cTable = "<thead><tr><th></thead><tbody>"
						if (response.articles.length > 0) {
							for (let i = 0; i < response.articles.length; i++) {
							rTable += '<tr class="tr"><td scope="row">'+ (i+1);
							if (response.articles[i].forumId == 1) {
								rTable += "<td>英雄聯盟"
								} else if (response.articles[i].forumId == 2) {
								rTable += "<td>魔獸世界"
								} else if (response.articles[i].forumId == 3) {
								rTable += "<td>魔物獵人"
									}
							rTable += "<td>"+ response.articles[i].titleName;
							rTable += "<td>"+ response.articles[i].createTime;
							rTable += "<td>"+ response.articles[i].titleId;
							rTable += "<td>"+ response.articles[i].forumId;
							cTable += '<tr><td><input type="radio" class="del d-none" name="d" value="a[i].userId">'
								}
						
							$('#rTable').html(rTable);
							$('#cTable').html(cTable);
							$("#del").addClass("d-none").removeClass("d-block");
							$("#toDel").addClass("d-none").removeClass("d-block");
					
							}else{
								$('#rTable').html("");
								$('#cTable').html("");
								$('#rMsg').html("查無結果");
								$("#toDel").addClass(
										"d-none")
										.removeClass(
												"d-block");
									}
						}
						});
					})	
		})//ready結尾		
