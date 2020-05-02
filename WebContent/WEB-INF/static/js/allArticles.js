		$(document).ready(function() {
			$("#admin-content").removeClass("d-none").addClass("d-block");

			$(document).on("click","#s",function() {
				var forum = $("#option").val();
				var title = $("#sBar").val();

			$.ajax({
				type : "POST",
				url : "GameBase/getSomeArticles",
				dataType : "json",
				data : {
					forum : forum,
					title : title
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
					
					}
				});
			})
			

			$(document).on("click",	"#allArticles",	function() {
					$.ajax({
						type : "POST",
						url : "/GameBase/getAllArticles",
						dataType : "json",
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
								
								
							$(document).on("click", ".tr", function() {
								var forumid = $(this).children().eq(4).text();
								var articleid = $(this).children().eq(5).text();
								console.log(forumid+"~"+articleid);
								$("#iframe").attr("src","http://localhost:8080/GameBase/forum_test/"+forumid+"/"+articleid);
								iframe(forumid,articleid);
																})
		})


		
		$(document).on("click", "#delArticle", function() {

			if ($("#rMsg").text() == "") {
				$(".del").removeClass("d-none").addClass("d-block");
				$("#toDel").removeClass("d-none").addClass("d-block");
			}
		})
		

		//可以去掉 顯示順序還有問題
		function dtopBar(){
				console.log("delete----------------------");
				$("#iframe").contents().find('#topBar').addClass("d-none");
				showIframe();
			}
		function showIframe(){
 			$("#iframe").addClass("d-block").delay(5000).queue(function(){
 				$(this).removeClass("d-none").dequeue()});
			}
		
