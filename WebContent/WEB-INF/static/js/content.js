
$("#document").ready(function () {
	console.log("this is content.js");

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
		
//		identifyLoginState();
		
		authorId = $(this).attr("id");
		userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
		var status = friendsStatus[authorId];
		
		console.log("userId :"+userId);
		console.log("friendsStatus["+authorId+"] :"+status);
		
		if( friendsStatus[authorId] == undefined ){
			queryfriend(userId, authorId);
		}else{
			$(".firends_area[authorId="+authorId+"]").removeClass("hidden-window");
		}
		console.log(friendsStatus);	
	});
	
	

});

//static variable and function
var friendsStatus = {};
var authorId;
var userId;

function update_content(btn){
	var forumId = JSON.parse(window.sessionStorage.getItem("forum")).forumId;
	var titleId = JSON.parse(window.sessionStorage.getItem("title")).titleId;
	console.log("get btn value :"+btn);
	console.log('/forum_test/'+forumId+'/'+titleId);
	$.ajax({
		url:'/GameBase/forum_test/'+forumId+'/'+titleId+'/btn',//XX
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

// login status identify ?
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
		url:"/GameBase/queryfriends",
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			userId:userId,
			authorId:authorId
		},
		success: function(response){
			console.log("success");
//			console.log("authorId is "+authorId);
//			console.log("response.friendInfo");
//			console.log(response.friendInfo);
//			console.log("response.friend");
//			console.log(response.friend);
			
			if(response.friendInfo.img == undefined){
//				console.log("img is undefined");
//				console.log(response.friendInfo.img);
				response.friendInfo.img = "/GameBase/img/userIcon.png";
//				console.log(response.friendInfo.img);
			}
			//set friend form
			var friendform = Mustache.render(friendTemplate,response.friendInfo);
			$("body").append(friendform);
			

			//indentify friend data
			var rsStatus;
			if(response.friend == undefined){
				console.log("friends is undefined");	
				$(".firends_area[authorId="+authorId+"]").children(".btn_delete_firends").addClass("hidden-window");
				rsStatus = "false";
			}else{
				console.log("is friends ");	
				$(".firends_area[authorId="+authorId+"]").children(".btn_add_friends").addClass("hidden-window");
				rsStatus = "true";
			}
			// put friend data into variable
			friendsStatus[authorId] = rsStatus;			
			window.sessionStorage.setItem("friendsStatus", friendsStatus);
			//open window
			$(".firends_area[authorId="+authorId+"]").removeClass("hidden-window");
			//close btn on click
			$(".firends_area[authorId="+authorId+"]").on("click",".close-btn",function(){
				console.log("close btn clicked");
				$(".firends_area[authorId="+authorId+"]").addClass("hidden-window");
			});
			//add firend btn on click
			$(".firends_area[authorId="+authorId+"]").on("click",".btn_add_friends",function(){
				console.log("add friend btn clicked");
				addfriend();
			});
		}
	});
}

function addfriend(){
	console.log("add friends");	
	console.log(userId);
	console.log(authorId);
	$.ajax({
		url:"/GameBase/addfriend",
		dataType:"json",
		type:"POST",
		cache:false,
		data:{
			userId:userId,
			authorId:authorId
		},
		success: function(response){
			console.log("success");
			//
			friendsStatus[authorId] = response.updatefriend;
			$(".firends_area[authorId="+authorId+"]").children(".btn_delete_firends").removeClass("hidden-window");
			$(".firends_area[authorId="+authorId+"]").children(".btn_add_friends").addClass("hidden-window");
		}
	});
}

var friendTemplate =
		'<div authorId="{{userId}}" class="firends_area popup-window hidden-window">'
		+'<i class="fas fa-times close-btn"></i>'
		+'<h2>好友申請</h2>'
		+'<div>'
		+'<img src="{{img}}" width="100" height="100" />'
		+'<table>'
		+'<tr>'
		+'<td>帳號:</td>'
		+'<td>{{account}}</td>'
		+'</tr>'
		+'<tr>'
		+'<td>暱稱:</td>'
		+'<td>{{nickName}}</td>'
		+'</tr>'
		+'<td>Email:</td>'
		+'<td>{{email}}</td>'
		+'</tr>'
		+'</table>'
		+'</div>'
		+'<div class="btn_add_friends">'
		+'<button id="add">新增好友</button>'
		+'</div>'
		+'<div class="btn_delete_firends">'
		+'<button id="delete">已加為好友</button>'
		+'</div>'
		+'</div>';
