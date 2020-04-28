$("#document").ready(function () {
	console.log("this is content.js2");
	
//	/* record button clicked */
//	$(".btn_record").click(function(){
//		console.log("record btn clicked");
//		/* identify which btn been clicked */
//		var btn = $(this).attr("id");
//		console.log(btn);
//		
//		update_content(btn);
//	});
//	
//	/* update content button clicked */
//	$(".btn_update_content").click(function(){
//		console.log("update content btn clicked");
//		/* identify which btn been clicked */
//		var btn = $(this).attr("id")
//		console.log(btn);
//		
//		update_content(btn);
//	});
	
	/*author img clicked*/
	$(".userId").click(function(){
		console.log("author img clicked");
		var authorId = $(this).attr("id");
		var userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
		console.log("userId :"+userId);
		queryfriend(userId, authorId);
		$(".close-btn").click(function(){
			$("#addfriend_"+userId).addClass("hidden-window");
		});
	});
	
});

function update_content(btn){
	console.log("get btn value :"+btn);
	console.log("/forum_test/${forum.forumId}/${title.titleId}");
	$.ajax({
		url:'<c:url value="/forum_test/${forum.forumId}/${title.titleId}/btn"/>',
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			clcickedBTN:btn
		},
		success: function(response){
			console.log("success");
			console.log("response : "+response.record.record)
		}
	});
}

// login status identify
function identifyLoginState(){    
    if(window.sessionStorage.getItem("loginUser") != ""){
    	 $(".publish-area").removeClass("hidden-window",700);
    	 $("#shadow").fadeIn(700);
    	 } else {
    	 alert("請先登入 !");       	    		
    	 $(".login-area").removeClass("hidden-window", 700);
    	 $("#shadow").fadeIn(700);
    	 }
}
function queryfriend(userId, authorId){
	console.log("query friends");	
	$.ajax({
		url:'<c:url value="/queryfriends"/>',
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			userId:userId,
			authorId:authorId
		},
		success: function(response){
			console.log("success");	
			$("body").append(friendform);
		}
	});
}

function addfriend(){
	console.log("add friends");	
	$.ajax({
		url:'<c:url value="/addfriend"/>',
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			userId:userId,
			friendId:friendId
		},
		success: function(response){
			console.log("success");
			//
			var friendStatus = response.friendStatus;
			
		}
	});
}

//friends template
//var firendTemplate =
//'<div id="addfriend_${item.userId}" class="firends_area popup-window">'
//	'<i class="fas fa-times close-btn"></i>'
//	'<h2>好友申請</h2>'
//	<!--user nickname and account-->
//	'<div>'
//		'<c:if test="${img}">'
//			'<img src="<c:url value="/img/userIcon.png"/>" width="100" height="100" />'
//		'</c:if>'
//		'<c:if test="${img}">'
//			'<img src=${img} alt="" width="100" height="100" /></c:if>'
//		'<table>'
//			'<tr>'
//				'<td>帳號:</td>'
//				'<td>${account}</td>'
//			'</tr>'
//			'<tr>'
//				'<td>暱稱:</td>'
//				'<td>${nickName}</td>'
//			'</tr>'
//		'</table>'
//		'<c:forEach var="friends" items="${friends}">'
//			'<c:if test="${friends.firendId==item.userId}">'
//			'is friend'
//			'</c:if>'
//		'</c:forEach>'
//		'${friendStatus?${addfriendbtn}:${delfriendbrn}}'
//	'</div>'
//'</div>';

var friendform =`
<div id="addfriend_${item.userId}"
	class="firends_area popup-window">
	<i class="fas fa-times close-btn"></i>
	<h2>好友申請</h2>
	<!--user nickname and account-->
	<div>
//		<c:if test="${empty item.img}">
//			<img src="<c:url value="/img/userIcon.png"/>" width="100" height="100" />
//		</c:if>
//		<c:if test="${not empty item.img}">
//			<img src=${item.img } alt="" width="100" height="100" /></c:if>
		<table>
			<tr>
				<td>帳號:</td>
				<td>${item.account}</td>
			</tr>
			<tr>
				<td>暱稱:</td>
				<td>${item.nickName}</td>
			</tr>
		</table>
//		<c:forEach var="friends" items="${friends}">
//			<c:if test="${friends.firendId==item.userId}">
//			is friend
//			</c:if>
//		</c:forEach>

		<div class="btn_add_friends">
			<button id="add">新增好友</button>
		</div>
		<div class="btn_delete_firends">
			<button id="delete">刪除好友</button>
		</div>
	</div>
</div>
`;
	
const addfriendbtn = `
		<div class="btn_add_friends">
			<button id="add">新增好友</button>
		</div>
`;
var delfriendbtn =`
		<div class="btn_delete_firends">
			<button id="delete">刪除好友</button>
		</div>
`;
	