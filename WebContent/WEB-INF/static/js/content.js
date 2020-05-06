$("#document")
		.ready(
				function() {
					titleContentId = $(".content_content:first").attr(
							"contentId");
					forumId = $(".article_window").attr("forumId");
					titleId = $(".article_window").attr("titleId");
					console.log("title content id is " + titleContentId);
					if (window.sessionStorage.getItem("loginUser") != ""
							&& forumId != undefined && titleId != undefined) {
						/* identify which btn been clicked */
						userId = JSON.parse(window.sessionStorage
								.getItem("loginUser")).userId;
						console.log("userId is " + userId);
						queryRecord();
						open_btn();
					}

					/* record button clicked */
					$(".btn_record")
							.click(
									function() {
										console.log("record btn clicked");

										if (window.sessionStorage
												.getItem("loginUser") != "") {
											/* identify which btn been clicked */
											userId = JSON
													.parse(window.sessionStorage
															.getItem("loginUser")).userId;
											console.log("userId is " + userId);
											var btn = $(this).attr("id");
											console.log(btn);
											update_record(btn);
										} else {
											alert("請先登入 !");
											$(".login-area").removeClass(
													"hidden-window", 700);
											$("#shadow").fadeIn(700);
											// queryRecord();/*需在登入後ajax 加 fun*/
										}
									});

					/* update content button clicked */
					$(".btn_update_content").click(function() {
						console.log("update record btn clicked");
						/* identify which btn been clicked */
						var btn = $(this).attr("btn")
						console.log(btn);
						contentId = $(this).attr("contentId");
						console.log("content ID : " + contentId);
						update_content(btn, contentId);
					});

					/* author img clicked */
					$(".content_author_img").click(
							function() {
								console.log("author img clicked");

								// identifyLoginState();

								authorId = $(this).attr("id");
								userId = JSON.parse(window.sessionStorage
										.getItem("loginUser")).userId;
								var status = friendsStatus[authorId];

								console.log("userId :" + userId);
								console.log("friendsStatus[" + authorId + "] :"
										+ status);

								if (friendsStatus[authorId] == undefined) {
									queryfriend(userId, authorId);
								} else {
									$(
											".firends_area[authorId="
													+ authorId + "]")
											.removeClass("hidden-window");
								}
								console.log(friendsStatus);
							});

				});

// static variable and function
var friendsStatus = {};
var authorId;
var userId;
var forumId;
var titleId;
var contentId;
var titleContentId;

function open_btn() {
	console.log("open btn");
	userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
	$(".content_unit[userId=" + userId + "] .btn_update_content").attr(
			"hidden", false);
}

function update_record(btn) {
	console.log("update record");
	$.ajax({
		url : '/GameBase/forum_test/' + forumId + '/' + titleId + '/record',
		dataType : "json",
		type : "POST",
		cache : true,
		data : {
			clickedBTN : btn,
			userId : userId
		},
		success : function(response) {
			console.log("success");
			console.log("response : " + response.record.record)
			$(".btn_record").removeClass("disabled");
			if (response.record.record === "like") {
				$("#like.btn_record").html(
						'<i class="fas fa-thumbs-up fa-2x">'
								+ response.title.likeNum + '</i>');
				$("#unlike.btn_record").addClass("disabled").html(
						'<i class="far fa-thumbs-down fa-2x">'
								+ response.title.unlikeNum + '</i>');
			} else if (response.record.record === "unlike") {
				$("#like.btn_record").addClass("disabled").html(
						'<i class="far fa-thumbs-up fa-2x">'
								+ response.title.likeNum + '</i>');
				$("#unlike.btn_record").html(
						'<i class="fas fa-thumbs-down fa-2x">'
								+ response.title.unlikeNum + '</i>');
			} else {
				$("#like.btn_record").html(
						'<i class="far fa-thumbs-up fa-2x">'
								+ response.title.likeNum + '</i>');
				$("#unlike.btn_record").html(
						'<i class="far fa-thumbs-down fa-2x">'
								+ response.title.unlikeNum + '</i>');
			}
		}
	});
}

/* update or delete content and reply */
function update_content(btn, contentId) {
	if (btn === 'delete') {
		console.log("get btn value :" + btn);
		console.log("/forum_test/${forum.forumId}/${title.titleId}");
		$.ajax({
			url : '/GameBase/forum_test/' + forumId + '/' + titleId + '/'
					+ contentId + '/update',
			dataType : "json",
			type : "POST",
			cache : true,
			data : {
				clickedBTN : btn,
				contentId : contentId
			},
			success : function(response) {
				console.log("success");
				$(".content_content[contentId=" + contentId + "]").html(
						response.content.content);
			}
		})
	} else if (btn === 'update') {
		console.log("get btn value :" + btn);
		// get content data
		var econtent = $(".content_content[contentId=" + contentId + "]")
				.html();
		var etitle = $(".content_title h2").text();

		// set editor
		$("#articleTitle").val(etitle);
		editor.setData(econtent);
		// open editor
		$(".publish-area").removeClass("hidden-window", 700);
		$("#shadow").fadeIn(700);
		$("#submit").attr("hidden", true);
		$("#update_content").attr("hidden", false);

		// editor update button clicked
		$("#update_content").click(
				function() {
					$.ajax({
						url : '/GameBase/forum_test/' + forumId + '/' + titleId
								+ '/' + contentId + '/update',
						dataType : "json",
						type : "POST",
						cache : true,
						data : {
							clickedBTN : btn,
							contentId : contentId,
							newContent : editor.getData()
						},
						success : function(response) {
							console.log("success1");
							$(".content_content[contentId=" + contentId+ "]").html(response.content.content);
							// clear editor val
							$("#articleTitle").val("");
							editor.setData("");
							// close window
							$(".publish-area").addClass("hidden-window", 700);
							$("#shadow").fadeOut(700);
						}
					})
				});

	}
}
// login status identify ?
function identifyLoginState() {
	if (window.sessionStorage.getItem("loginUser") != "") {
		$(".publish-area").removeClass("hidden-window", 700);
		$("#shadow").fadeIn(700);
	} else {
		alert("請先登入 !");
		$(".login-area").removeClass("hidden-window", 700);
		$("#shadow").fadeIn(700);
	}
}

function queryRecord() {
	/* function find user's record after user login *//* 無法登入 */
	userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
	$.ajax({
		url : '/GameBase/forum_test/' + forumId + '/' + titleId
				+ '/queryRecord',
		dataType : "json",
		type : "POST",
		cache : false,
		data : {
			userId : userId
		},
		success : function(response) {
			if (response.record != undefined) {
				if (response.record.record === "like") {
					$("#like i").removeClass("far").addClass("fas");
					$("#unlike").addClass("disabled");
				} else if (response.record.record === "unlike") {
					$("#unlike i").removeClass("far").addClass("fas");
					$("#like").addClass("disabled");
				}
			}
		}
	});
}

function queryfriend(userId, authorId) {
	console.log("query friends");
	$.ajax({
		url : "/GameBase/queryfriends",
		dataType : "json",
		type : "POST",
		cache : true,
		data : {
			userId : userId,
			authorId : authorId
		},
		success : function(response) {
			console.log("success");
			// console.log("authorId is "+authorId);
			// console.log("response.friendInfo");
			// console.log(response.friendInfo);
			// console.log("response.friend");
			// console.log(response.friend);

			if (response.friendInfo.img == undefined) {
				// console.log("img is undefined");
				// console.log(response.friendInfo.img);
				response.friendInfo.img = "/GameBase/img/userIcon.png";
				// console.log(response.friendInfo.img);
			}
			// set friend form
			var friendform = Mustache.render(friendTemplate,
					response.friendInfo);
			$("body").append(friendform);

			// indentify friend data
			var rsStatus;
			if (response.friend == undefined) {
				console.log("friends is undefined");
				$(".firends_area[authorId=" + authorId + "]").children(
						".btn_delete_firends").addClass("hidden-window");
				rsStatus = "false";
			} else {
				console.log("is friends ");
				$(".firends_area[authorId=" + authorId + "]").children(
						".btn_add_friends").addClass("hidden-window");
				rsStatus = "true";
			}
			// put friend data into variable
			friendsStatus[authorId] = rsStatus;
			window.sessionStorage.setItem("friendsStatus", friendsStatus);
			// open window
			$(".firends_area[authorId=" + authorId + "]").removeClass(
					"hidden-window");
			// close btn on click
			$(".firends_area[authorId=" + authorId + "]").on(
					"click",
					".close-btn",
					function() {
						console.log("close btn clicked");
						$(".firends_area[authorId=" + authorId + "]").addClass(
								"hidden-window");
					});
			// add firend btn on click
			$(".firends_area[authorId=" + authorId + "]").on("click",
					".btn_add_friends", function() {
						console.log("add friend btn clicked");
						addfriend();
					});
		}
	});
}

function addfriend() {
	console.log("add friends");
	if (window.sessionStorage.getItem("loginUser") != "") {
		$
				.ajax({
					url : "/GameBase/addfriend/"
							+ JSON.parse(window.sessionStorage
									.getItem("loginUser")).userId,
					type : "POST",
					data : {
						authorId : $("#spanId").text()
					},
					success : function(data) {
						friendsStatus[authorId] = data.updatefriend;
						$(".firends_area[authorId=" + authorId + "]").children(
								".btn_delete_firends").removeClass(
								"hidden-window");
						$(".firends_area[authorId=" + authorId + "]").children(
								".btn_add_friends").addClass("hidden-window");

						$.ajax({
							url : "/GameBase/queryUserInfo/"
									+ JSON.parse(window.sessionStorage
											.getItem("loginUser")).userId,
							type : "POST",
							success : function(data) {
								if (data) {
									window.sessionStorage.setItem("loginUser",
											JSON.stringify(data));

									// set new friend list to chat room object
									var chatRoomObj = JSON
											.parse(window.sessionStorage
													.getItem("chatRoom"));
									chatRoomObj.friendsList = data.friendsList;

									window.sessionStorage.setItem("chatRoom",
											JSON.stringify(chatRoomObj));

									var friendsList = Mustache.render(
											chatRoomFriendsListTemplate,
											chatRoomObj);
									$("#chat-room-friends").html(friendsList);

									// get online list
									var data = JSON.parse(window.sessionStorage
											.getItem("loginUser"));
									sendWebSocketMessage({
										from : data.userId,
										to : [ 'regist' ],
										message : '',
										time : Date.now()
									});
								}

							}
						});
					}
				});
	}
}
var friendTemplate = '<div authorId="{{userId}}" class="firends_area popup-window hidden-window">'
		+ '<span id="spanId" style="display:none">{{userId}}</span>'
		+ '<i class="fas fa-times close-btn"></i>'
		+ '<h2>好友申請</h2>'
		+ '<div>'
		+ '<img src="{{img}}" width="100" height="100" class="content_head" />'
		+ '<table>'
		+ '<tr>'
		+ '<td>帳號:</td>'
		+ '<td>{{account}}</td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>暱稱:</td>'
		+ '<td>{{nickName}}</td>'
		+ '</tr>'
		+ '<td>Email:</td>'
		+ '<td>{{email}}</td>'
		+ '</tr>'
		+ '</table>'
		+ '</div>'
		+ '<div class="btn_add_friends">'
		+ '<button id="add">新增好友</button>'
		+ '</div>'
		+ '<div class="btn_delete_firends">'
		+ '<button id="delete">已加為好友</button>' + '</div>' + '</div>';
