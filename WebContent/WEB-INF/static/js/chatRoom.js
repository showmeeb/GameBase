/* ----------------------------------------------------------- */
/*  4. LOGIN BUTTON & ICON CONTROL
/* ----------------------------------------------------------- */
var userCenterUrl = "/GameBase/UserCenter";
function myFunction(a) {
	  console.log(a)    
	}
$(document).ready(function () {
	//**************popover**************
	$("[data-toggle='popover']").mouseover(function(){
		
		var userId = $(this).children("img").attr("id");

		//防止一直存取
		if(window.sessionStorage.getItem("uid="+userId) != null){
//			console.log(window.sessionStorage.getItem("uid="+userId));
			var oldUser = JSON.parse(window.sessionStorage.getItem("uid="+userId));
//			console.log("oldUser uid = "+oldUser.userId);
			var ocontent = "Account:"+oldUser.account+"<br>";
			ocontent += "userId:"+ oldUser.userId+"<br>";
			if (oldUser.img) {
				ocontent +="img:<img src='"+oldUser.img+"'><br>";
			}else{
				ocontent +="img:<img src='https://i.imgur.com/ke6wdHI.jpg'><br>";
			}
			//popover 沒有refresh 所以必須每次都dispose( Hides and destroys the popover )
			$("[data-toggle='popover']").popover('dispose').popover({title: "會員資料",trigger: "hover",placement: "bottom",html:true, content: ocontent, delay: {show: 200, hide: 1000}});
			
		}else{
			
			$.ajax({
				contentType: "application/json",
				url: "/GameBase/userInfo/"+userId,
				type:"GET",
				success:function(data){
					var fUInfo = data.fUserInfo;
					window.sessionStorage.setItem("uid="+userId,JSON.stringify(fUInfo));
					var ucontent = "Account:"+fUInfo.account+"<br>";
					ucontent += "userId:"+fUInfo.userId+"<br>";
					if (fUInfo.img) {
						ucontent +="img:<img src='"+fUInfo.img+"'><br>";
					}else{
						ucontent +="img:<img src='https://i.imgur.com/ke6wdHI.jpg'><br>";
					}
					$("[data-toggle='popover']").popover('dispose').popover({title: "會員資料",trigger: "hover",placement: "bottom" ,html:true, content: ucontent, delay: {show: 200, hide: 1000}});
				}
			});
			
		}

	});
	

	
  if (window.sessionStorage.getItem("loginUser") != "") {
    var login = JSON.parse(window.sessionStorage.getItem("loginUser"));
//    $(".dropdown button[name='dmb-u']").html(login.account);
    if (login.img) {
      $(".shot").removeClass("disable");
      $(".shot").attr("src", login.img).attr("id",login.userId);
    } else {
      $(".shot").removeClass("disable");
      $(".shot").attr("src", "https://i.imgur.com/ke6wdHI.jpg").attr("id",login.userId);;
    }
    $("#login-str").addClass("hidden-window", 700);
    $("#regiest-str").addClass("hidden-window", 700);
    $("#mainCenter").removeClass("hidden-window", 700);
    $("#logout-str").removeClass("hidden-window", 700);
    $("[data-toggle='popover']").removeClass("disable");
    if(login.rankId==4){
  	  $("#admin-broadcast").removeClass("hidden-window", 700);
    }
   

  }

  $("#login-str").click(function () {
    $(".login-area").css("height",450).removeClass("hidden-window", 700);
    $("#shadow").fadeIn(700);
  });
 
  $("#login-submit-btn").click(function () {
    userLogin();
  });

  $("#login-form .input-group input").keypress(function (e) {
    if (e.key == "Enter") {
      userLogin();
    }
  });

//**************Admin broadcast******************
  $("#admin-broadcast").click(function(){
  	console.log("got admin bro");
  	$(".admin-broadcast-area").removeClass("hidden-window", 700);
      $("#shadow").fadeIn(700);
  })
  
  // regist button
  $("#regiest-str").click(function () {
    $("#loggedin-list").fadeToggle(500);
    $(".regist-area").removeClass("hidden-window", 700);
    $("#shadow").fadeIn(700);
  });

  // logout button
  $("#logout-str").click(function () {
    $("#login-submit-btn").removeClass("disable");
    $(".shot").addClass("disable", 700,function(){
    	$(".shot").attr("src", "https://i.imgur.com/ke6wdHI.jpg");
    });
    
    $(".dropdown button[name='dmb-u']").html("會員系統");
    
    $("#logout-str").addClass("hidden-window", 700);
    $("#mainCenter").addClass("hidden-window", 700);
    $("#login-str").removeClass("hidden-window", 700);
    $("#regiest-str").removeClass("hidden-window", 700);
    $("#admin-broadcast").addClass("hidden-window", 700);
    $("[data-toggle='popover']").addClass("disable");
    // google logout
    googleSignOut();

    // get userNo for offline message use
    var userNo = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
    // send offline message to everyone
    sendWebSocketMessage({ from: userNo, to: ['logout'], message: '', time: Date.now() });

    $.ajax({
      url: "/GameBase/logout",
      type: "DELETE",
      success: function (data) {

        // clean session
        window.sessionStorage.setItem("loginUser", "");
        window.sessionStorage.setItem("chatRoom", "");
        window.sessionStorage.setItem("UserData", "");
        //console.log('success');

        // hide and clean chat room
        hideChatRoom(true);
        cleanChatRoom();
        disconnectChatRoom();
        //reset chatHistroy
        chatHistoryPage=1;

      }
    });
  });
  
  //forgetPwd button
  $("#forgetPwd").click(function(){
	  $(".login-area").addClass("hidden-window", 700);
	  $(".forgetPassword-area").removeClass("hidden-window",700);
  });

  $("#sendPwd").click(function(){
	 sendPwd(); 
  });
  
});

function userLogin() {

  var userAcc = $("#login-form input[name='account']").val();
  var pwd = $("#login-form input[name='password']").val();
  var saveValue = $("input[name='save']").is(":checked");
  
  $("#login-submit-btn").addClass("disable");
  // empty check
  if (userAcc != "" && pwd != "") {
    var formdata = $("#login-form").serializeObject();
    var UserData = JSON.stringify(formdata);

    //		 console.log("UserData:"+UserData);	 
    // for login AJAX operation use
    $.ajax({
      url: "/GameBase/loginAjax/" + saveValue,
      type: "POST",
      data: UserData,
      contentType: "application/json",
      success: function (data) {
        if (data.status == true) {
          console.log("img url:" + data.loginUser.img);
          // if user has snapshot , then use it
          if (data.loginUser.img) {
            $(".shot").removeClass("disable");
            $(".shot").attr("src", data.loginUser.img).attr("id",data.loginUser.userId);
          } else {
            $(".shot").removeClass("disable");
            $(".shot").attr("src", "https://i.imgur.com/ke6wdHI.jpg").attr("id",data.loginUser.userId);;
          }

          //show userAccount
//          $(".dropdown button[name='dmb-u']").html(data.loginUser.account);
          $("[data-toggle='popover']").removeClass("disable");
          
          // close login window
          $("#login-submit-btn").parent().addClass("hidden-window", 700);
          $("#shadow").fadeOut(700);

          // clear the form
          $(".input-group input").each(function () {
            $(this).val("");
          });

          $(".input-group").each(function () {
            $(this).removeClass("error-format accepted-format");
          });

          // get user information
          window.sessionStorage.setItem("loginUser", JSON.stringify(data.loginUser));
          console.log(window.sessionStorage.getItem("loginUser"));
          window.sessionStorage.setItem("UserData", JSON.stringify(data.UserData));
          console.log(window.sessionStorage.getItem("UserData"));
          // connect and show chat room
          connectChatRoom();
          $(".chat-room-area").show();

          // set chat room object
          chatRoom.friendsList = JSON.parse(window.sessionStorage.getItem("loginUser")).friendsList;
          window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoom));

          // set friend list to chat room
          var friendsList = Mustache.render(chatRoomFriendsListTemplate, chatRoom);
          $("#chat-room-friends").html(friendsList);

          // hide login-str & regist-str
          $("#login-str").addClass("hidden-window", 700);
          $("#regiest-str").addClass("hidden-window", 700);
          $("#logout-str").removeClass("hidden-window", 700);
          $("#mainCenter").removeClass("hidden-window", 700)
          
          if(data.loginUser.rankId==4){
        	  $("#admin-broadcast").removeClass("hidden-window", 700);
          }
          
        } else {
          $("#login-submit-btn").removeClass("disable",function(){
        	  alert("帳號或密碼不符合");
          });
          $("#login-area").css("height",500);
          $("#forgetPwd").removeClass("hidden-window").addClass("buttonL");
        }

      },
    });
  } else {
    alert("欄位不得為空");
    $("#login-submit-btn").removeClass("disable");
  }

}

function sendPwd(){
	var facc = $("#forgetPwd-form input[name='account']");
	var femail = $("#forgetPwd-form input[name='email']");
	if (facc != "" && femail != ""){
		var forgetform = $("#forgetPwd-form").serializeObject();
		var forgetData = JSON.stringify(forgetform);
		console.log(forgetData);
		$("#sendPwd img").css({ "opacity": "1" });
		$.ajax({
			url: "/GameBase/resetPwd",
		    type: "POST",
		    data: forgetData,
		    contentType: "application/json",
		    success: function (data){
		    	if(data.status){
		    		$(".forgetPassword-area").addClass("hidden-window", 700, function () {
		                $(".forget-reply-area").removeClass("hidden-window", 700);
		              });
		    		$("#sendPwd img").css({ "opacity": "0" });
		    	}else{
		    		$("#sendPwd img").css({ "opacity": "0" });
		    		alert("帳號或信箱錯誤");
		    	}
		    } 
		});
	}
	
}

/* ----------------------------------------------------------- */
/*
 * 5. LOGIN & REGIST WINDOWS CONTROL /*
 * -----------------------------------------------------------
 */
if (window.sessionStorage.getItem("loginUser") == undefined) {
  window.sessionStorage.setItem("loginUser", "");
} else if (window.sessionStorage.getItem("loginUser") != "") {
  $(document).ready(function () {
    connectChatRoom();
  });
}
$(document).ready(function () {

  $(".login-btn").click(function () {

    $(".login-area").removeClass("hidden-window", 700);
    $("#shadow").fadeIn(700);

  });

  $(".regist-btn").click(function () {
    $(this).parent().addClass("hidden-window", 700, function () {
      $(".regist-area").removeClass("hidden-window", 700);
    });
    $("#shadow").fadeIn(700);
  });


  // regist
  $("#regist-submit-btn").click(function() {
    userRegist();
  });

  $("#regist-form .input-group input").keypress(function (e) {
    if (e.key == "Enter") {
      userRegist();
    }
  });

  $("#regist-form .input-group input").keydown(function (e) {
    if (e.key == "F2") {
      // console.log("F2!!!");
      var account = "Yixuan168589";
      var email = "xuans8589@gmail.com";

      $("#regist-form .input-group input[name='account']").val(account);
      $("#regist-form .input-group input[name='email']").val(email);

      $("#regist-form .input-group input[name='account']").parent().addClass("accepted-format");
      $("#regist-form .input-group input[name='email']").parent().addClass("accepted-format");

    }
  });

  // auth code input
  $("#auth-code-submit-btn").click(function () {
    userAuthCodeCheck();
  });

  $("#auth-code-form .input-group input").keypress(function (e) {
    if (e.key == "Enter") {
      userAuthCodeCheck();
    }
  });

  $("#return-login-btn").click(function () {
    $(this).parent().addClass("hidden-window", 700, function () {
      $(".login-area").removeClass("hidden-window", 700);
    });
  });

  $(".forget-reply-area button").click(function () {
	    $(this).parent().addClass("hidden-window", 700, function () {
	      $(".login-area").removeClass("hidden-window", 700);
	    });
	  });
  
  $(".close-btn").click(function () {
    $(this).parent().addClass("hidden-window", 700);
    $(".login-area").css("height",450);
    $("#forgetPwd").removeClass("buttonL").addClass("hidden-window");
    $("#shadow").fadeOut(700);

    // clear the form
    $(".input-group input").each(function () {
      $(this).val("");
    });
    $(".input-group").each(function () {
      $(this).removeClass("error-format accepted-format");
    });

    history.pushState({ foo: "main" }, "", currentUrl);
  });


  // regist data format check
  $("#regist-form .input-group input[name='account']").change(function () {
    var re = /^[a-zA-Z]{1}[a-zA-Z0-9]{7,15}$/;

    if (re.test($(this).val())) { // accepted format
      $(this).parent().removeClass("error-format");
      $(this).parent().addClass("accepted-format");
    } else if ($(this).val() == "") {
      $(this).parent().removeClass("error-format accepted-format");
    } else { // not-accepted format
      $(this).parent().removeClass("accepted-format");
      $(this).parent().addClass("error-format");
      alert("帳號格式需求：\n1.開頭第一個字母須為大小寫英文字\n2.長度需8 - 16個字");
    };
  });

  $("#regist-form .input-group input[name='password']").change(function () {
    // empty password check area
    $("input[name='ck-pwd']").val("");

    var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z\d]{8,15}$/;

    if (re.test($(this).val())) { // accepted format
      $(this).parent().removeClass("error-format");
      $(this).parent().addClass("accepted-format");
    } else if ($(this).val() == "") {
      $(this).parent().removeClass("error-format accepted-format");
    } else { // not-accepted format
      $(this).parent().removeClass("accepted-format");
      $(this).parent().addClass("error-format");
      alert("密碼格式需求：\n1.開頭第一個字母須為大小寫英文字\n2.長度需8 - 16個字");
    };
  });

  $("#regist-form .input-group input[name='ck-password']").change(function () {
    var pwd = $(this).parent().prev().children("input[name='password']").val();

    if (pwd == $(this).val()) { // accepted format
      $(this).parent().removeClass("error-format");
      $(this).parent().addClass("accepted-format");
    } else if ($(this).val() == "") {
      $(this).parent().removeClass("error-format accepted-format");
    } else { // not-accepted format
      $(this).parent().removeClass("accepted-format");
      $(this).parent().addClass("error-format");
    };
  });
  /*
 * $("#regist-form .input-group input[name='name']").change(function(){ var
 * reC = /^[\u4E00-\u9FA5]{2,7}$/; var reE =
 * /^[A-Z]+\s{1}[A-Z]+(\s{0,1}[A-Z]+)*$/;
 * 
 * if(reC.test($(this).val()) || reE.test($(this).val())){ // accepted
 * format $(this).parent().removeClass("error-format");
 * $(this).parent().addClass("accepted-format"); } else if($(this).val() ==
 * ""){ $(this).parent().removeClass("error-format accepted-format"); } else { //
 * not-accepted format $(this).parent().removeClass("accepted-format");
 * $(this).parent().addClass("error-format");
 * alert("名稱格式需求：\n1.中文名字需2個字以上\n2.英文名字需比照護照格式全部大寫\n3.英文名字需兩個字節或三個字節"); };
 * });
 */

  $("#regist-form .input-group input[name='email']").change(function () {
    var reShort = /^[a-zA-Z]{1}[\w-]+@[a-z0-9]+\.[a-z]+$/;
    var reLong = /^[a-zA-Z]{1}[\w-]+@[a-z0-9]+\.[a-z]+\.[a-z]+$/;

    if (reShort.test($(this).val()) || reLong.test($(this).val())) { // accepted
      // format
      $(this).parent().removeClass("error-format");
      $(this).parent().addClass("accepted-format");
    } else if ($(this).val() == "") {
      $(this).parent().removeClass("error-format accepted-format");
    } else { // not-accepted format
      $(this).parent().removeClass("accepted-format");
      $(this).parent().addClass("error-format");
    };
  });

});

function userRegist() {
  // input value empty check
  var inputEmptyCheck = true;

  // check each input-group
  $("#regist-form .input-group input").each(function () {
    if ($(this).val() == "") {
      inputEmptyCheck = false;
    }
  });

  if (inputEmptyCheck) {
    // input value format check
    var inputCheck = true;

    // check each input-group
    $("#regist-form .input-group").each(function () {
      if (!($(this).hasClass("accepted-format"))) {
        inputCheck = false;
      }
    });

    if (inputCheck) {
      // disabled the regist button
      $("#regist-submit-btn").attr("disabled", true);

      // display loading gif
      $("#regist-submit-btn img").css({ "opacity": "1" });

      var formdata = $("#regist-form").serializeObject();
      // console.log("formdata="+formdata);
      var UserData = JSON.stringify(formdata);
      // console.log("UserData="+UserData);
      $.ajax({
        url: "/GameBase/registerAjax",
        type: "POST",
        data: UserData,
        contentType: "application/json",
        success: function (data) {
          if (data.status != 'failure') {
            // redirect to auth code input area
            $(".regist-area").addClass("hidden-window", 700, function () {
              $(".auth-code-input-area").removeClass("hidden-window", 700);
            });

            // get uID from regist form
            var uID = $("#regist-form input[name='account']").val();

            // clear the form
            $(".input-group input").each(function () {
              $(this).val("");
            });

            // set uID to auth code form
            $("#auth-code-form input[name='account']").val(uID);

            $(".input-group").each(function () {
              $(this).removeClass("error-format accepted-format");
            });

            // active the regist button
            $("#regist-submit-btn").attr("disabled", false);
            // hide loading gif
            $("#regist-submit-btn img").css({ "opacity": "0" });
          } else {
            alert(data.errorMsg);

            // active the regist button
            $("#regist-submit-btn").attr("disabled", false);
            // hide loading gif
            $("#regist-submit-btn img").css({ "opacity": "0" });
          }
        }
      });

    } else {
      alert("輸入資料格式有誤");
    }
  } else {
    alert("欄位不得為空");
  }

}

function userAuthCodeCheck() {
  var uID = $("#auth-code-form input[name='account']").val();
  var authCode = $("#auth-code-form input[name='authCode']").val();

  $.ajax({
    url: "/GameBase/Authcode/" + authCode,
    type: "GET",
    success: function (data) {

      if (data != 'failure') {
        $(".auth-code-input-area").addClass("hidden-window", 700, function () {
          $(".regist-reply-area").removeClass("hidden-window", 700);
        });
      } else {
        alert("輸入驗證碼格式有誤");
      }

    }
  });
}

/* ----------------------------------------------------------- */
/*
 * 6. BROWSER HISTORY CONTROL /*
 * -----------------------------------------------------------
 */

var currentUrl = location.href;


// if detect browser execute backward , reload the page , prevent cache
if (!!window.performance && window.performance.navigation.type === 2) {
  window.location.reload();
}


/* ----------------------------------------------------------- */
/*
 * 7. GOOGLE LOGIN CONTROL /*
 * -----------------------------------------------------------
 */
var googleUser = {};
var startApp = function () {
  gapi.load('auth2', function () {
    //		Retrieve the singleton for the GoogleAuth library and set up the client.
    auth2 = gapi.auth2.init({
      //      client_id: '703647999598-mtjqtb9jrnp6banoqnialqlhbppjc64h.apps.googleusercontent.com',
      client_id: '982957556355-9h99fuvvivi52g599iucre1v04ktheh0.apps.googleusercontent.com',
      cookiepolicy: 'single_host_origin',
      scope: 'profile email'
    });
    attachSignin(document.getElementById('google-login-btn'));
  });
};

function attachSignin(element) {
 // console.log("qqq");
  // bind click event to specific id button
  auth2.attachClickHandler(element, {},
    function (googleUser) {
      // get id token after login
      var id_token = googleUser.getAuthResponse().id_token;

      // connect motozone server
      $.ajax({
        url: "/GameBase/Users/GoogleLogin",
        type: "POST",
        data: { idTokenStr: id_token },
        success: function (data) {
          if (data) {
        	//console.log("goo userid:"+data.userId);
            // if user has snapshot , then use it
            if (data.img) {
              $(".shot").removeClass("disable", 700);
              $(".shot").attr("src", data.img).attr("id",data.userId);
            }

            // show user icon
            $(".login-btn").addClass("disable", 700, function () {
              $(".loggedin-icon").removeClass("disable", 700);
            });

            $("[data-toggle='popover']").removeClass("disable");
            
            // close login window
            $("#login-submit-btn").parent().addClass("hidden-window", 700);
            $("#shadow").fadeOut(700);

            // hide login-str & regist-str
            $("#login-str").addClass("hidden-window", 700);
            $("#regiest-str").addClass("hidden-window", 700);
            $("#logout-str").removeClass("hidden-window", 700);
            $("#mainCenter").removeClass("hidden-window", 700);
            
            // clear the form
            $(".input-group input").each(function () {
              $(this).val("");
            });

            $(".input-group").each(function () {
              $(this).removeClass("error-format accepted-format");
            });

            // get user information
            window.sessionStorage.setItem("loginUser", JSON.stringify(data));

            // connect and show chat room
            connectChatRoom();
            $(".chat-room-area").show();

            // set chat room object
            chatRoom.friendsList = JSON.parse(window.sessionStorage.getItem("loginUser")).friendsList;
            window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoom));

            // set friend list to chat room
            var friendsList = Mustache.render(chatRoomFriendsListTemplate, chatRoom);
            $("#chat-room-friends").html(friendsList);

            // if user at product post page then update form's action attribute
            //        				var productPostUrlTest = /^.*ProductPost.*$/;
            //        				
            //        				if(productPostUrlTest.test(location.href)){
            //        					// update AutoBuy productPost page
            //        		    		updateProductPostAttr(data.uNo,data.uName);
            //        				}

            // if user at AutoBuy publish page then update form's attribute
            //        				var autoBuyUrlTest = /^.*AutoBuy.*$/;
            //        				
            //        				if(autoBuyUrlTest.test(location.href)){
            //        					// update AutoBuy publish
            //        					updateAutoBuyPublishAttr(data.uNo);
            //        				}

            // show shopping cart
            //        				if(autoBuyFlag){
            //        					$("#shoppingCart").show(700);
            //        					updateCartLink(data.uNo);
            //        				}

          } else {
            alert("Google登入失敗");
          }
        }
      });
    }, function (error) {
      alert(JSON.stringify(error, undefined, 2));
    });
}

// google sign out function
function googleSignOut() {
  var auth2 = gapi.auth2.getAuthInstance();

  auth2.signOut().then(function () {
    console.log('User signed out.');
  });
}

// execute login button binding
$(document).ready(function () {
  startApp();
});

/* ----------------------------------------------------------- */
/*
 * 8. CHAT ROOM CONTROL /*
 * -----------------------------------------------------------
 */
var chatRoom = {
  friendsList: [],
  usersList: [],
  chatHistory: []
}
var chatHistory = {
  userNo: '',
  chatContent: []
}

if (window.sessionStorage.getItem("chatRoom") == undefined) {
  window.sessionStorage.setItem("chatRoom", "");
} else if (window.sessionStorage.getItem("chatRoom") != "") {
  chatRoom = JSON.parse(window.sessionStorage.getItem("chatRoom"));
}





$(document).ready(function () {

  // hide when user not login yet
  if (window.sessionStorage.getItem("loginUser") == "") {
    $(".chat-room-area").hide();
  } else {
    // set friend list to chat room
    var friendsList = Mustache.render(chatRoomFriendsListTemplate, chatRoom);
    $("#chat-room-friends").html(friendsList);
  }

  // chat room show button
  $("#chat-room-fn-area .fa-comments").click(function () {
    $(".chat-room-area").removeClass("chat-room-hide", 800);

    $(this).hide(700);
  });

  // chat room hide button
  $("#chat-room-fn-btn-area .fa-minus").click(function () {
    hideChatRoom();
  });

  // friend list toggle button
  $("#friend-list-toggle-btn").click(function () {

    // change icon and shrink the list
    if ($("#friend-list-toggle-btn i").hasClass("fa-chevron-right")) {
      $("#friend-list-toggle-btn i").removeClass("fa-chevron-right");
      $("#friend-list-toggle-btn i").addClass("fa-chevron-left");

      $(".chat-room-friend-list-area").removeClass("friend-list-show", 700);
    } else {
      $("#friend-list-toggle-btn i").removeClass("fa-chevron-left");
      $("#friend-list-toggle-btn i").addClass("fa-chevron-right");

      $(".chat-room-friend-list-area").addClass("friend-list-show", 700);
    }

  });


  // friend list click event
  $(".chat-room-friend").click(function () {

    var userObj = {};
    userObj.userId = $(this).children(".chat-room-user-id").text();
    userObj.account = $(this).children(".chat-room-friend-name").text();
    userObj.snapshot = $(this).children(".chat-room-friend-icon").attr("src");

    // in order to check is the user already in the user list
    var checkFlag = true;
    for (let user of chatRoom.usersList) {
      if (user.userId == userObj.userId) {
        checkFlag = false;
      }
    }

    // use check flag to determine render or not
    if (checkFlag) {
      // apeend new user to usersList in chat room object
      chatRoom.usersList[chatRoom.usersList.length] = userObj;

      var renderData = Mustache.render(chatRoomUsersTemplate, userObj);

      // append new user diaglog block to chat room
      $("#chat-room-users").append(renderData);
    }

  });

  // check user login
  // if already login then update chat room
  if (window.sessionStorage.getItem("loginUser") != "") {
    initChatRoom();
  }
  
  //broadcast

  $("#broadcast-submit-btn").click(function () {
	broadSend();
  });
  $("#admin-broadcast-form .input-group input").keypress(function (e) {
	    if (e.key == "Enter") {
	    	broadSend();
	    }
	  });
function broadSend(){
		
    var userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
//    var formData = $("#admin-broadcast-form").serializeObject();
//    var broad = JSON.stringify(formData);
//    console.log('broadcastData: ' + broadcastData);
    var broadcast = $("#admin-broadcast-form input[name='broadcast']").val();
//    console.log('broad: ' + broad);
    let formData = new FormData();
    formData.append('broadcast' , broadcast);
//    if(broadcast != undefined){
//    	console.log('broadcastMessage');
        $.ajax({
            url: "/GameBase/Broadcast/" + userId,
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
              if (data) {
                alert('發送成功');
                $("#broadcast-submit-btn").parent().addClass("hidden-window", 700);
                $("#shadow").fadeOut(700);
              }

            }

          });
}
});

// init chat room when change page
function initChatRoom() {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

  for (let user of chatRoomObj.usersList) {
    // append new user diaglog block to chat room
    $("#chat-room-users").append(Mustache.render(chatRoomUsersTemplate, user));
  }
  console.log('initChatRoom');
  //console.log(chatRoomObj);
}

// user list click event
function showChatContentArea(element) {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));
  // get loginUser object from sessionStorage
  var loginUserObj = JSON.parse(window.sessionStorage.getItem("loginUser"));

  var userNo = $(element).children(".chat-room-user-id").text();

  // get user object from user list in chat room object
  var userObj = "";

  for (let user of chatRoomObj.usersList) {
    if (user.userId == userNo) {
      console.log(user.userId);
      userObj = user;
    }
  }

  // empty check
  if (userObj != "") {
    var renderData = Mustache.render(chatRoomContentTemplate, userObj);

    $("#chat-room-content").html(renderData);
    
    // get history msg
    scrollMyMessage();
    
  }

  // fill chat area with chat history
  var chatContent;
  for (let singleChat of chatRoomObj.chatHistory) {
    if (singleChat.userNo == userNo) {
      chatContent = singleChat.chatContent;
    }
  }

  // check chat history exist
  if (chatContent != undefined) {
    for (let content of chatContent) {
      if (content.from == loginUserObj.userId) {
        console.log('content.url: ' + content.url);
        //            	console.log('content.time: ' + content.time);
        if (content.url != undefined) {// from me
          $("#chat-message-area").append(Mustache.render(ownFileTemplate, content));
        }
        else {
          $("#chat-message-area").append(Mustache.render(ownMsgTemplate, content));
        }
      } else { // from others
        if (content.url != undefined) {
          $("#chat-message-area").append(Mustache.render(replyFileTemplate, content));
        }
        else {
          $("#chat-message-area").append(Mustache.render(replyMsgTemplate, content));
        }
      }
      // move the scroll bar to the end
      $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
    }
  }
  else {
    // console.log("No history");
  }
}
// chat room content area close button control
function emptyChatContentArea() {
  // clean chat room content area
  $("#chat-room-content").html("");
}

function showUsersDiaglog(element) {
  chatHistoryPage = 1;
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

  // get user data from friend list
  var userObj = {};
  userObj.userId = $(element).children(".chat-room-user-id").text();
  userObj.account = $(element).children(".chat-room-friend-name").text();
  userObj.snapshot = $(element).children(".chat-room-friend-icon").attr("src");

  // in order to check is the user already in the user list
  var checkFlag = true;
  for (let user of chatRoomObj.usersList) {
    if (user.userId == userObj.userId) {
      checkFlag = false;
    }
  }

  // use check flag to determine render or not
  if (checkFlag) {
    // apeend new user to usersList in chat room object
    chatRoomObj.usersList[chatRoomObj.usersList.length] = userObj;

    // update chat room object in session
    window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));

    var renderData = Mustache.render(chatRoomUsersTemplate, userObj);

    // append new user diaglog block to chat room
    $("#chat-room-users").append(renderData);
    
  }

}

function showUserListFromMessage(msg, isHistory) {
		
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));
  // in order to check is the user already in the user list
  var checkFlag = true;
  for (let user of chatRoomObj.usersList) {
    //receiver 的好友清單ID == msg.from
    if (user.userId == msg.from) {
      checkFlag = false;

      // receiver update brief message
      //console.log('update brief message');
      user.message = msg.message;
      user.time = msg.time;
    }
  }
  // 如果sender不在userlist上，就從friendlist取出，render至userlist
  // use check flag to determine render or not
  if (checkFlag) {
    // get user data from friend list
    var userObj = {};
    userObj.userId = msg.from;
    userObj.message = msg.message;
    userObj.time = msg.time;
    for (let user of chatRoomObj.friendsList) {
      if (user.userId == userObj.userId) {
        userObj.account = user.account;
        userObj.snapshot = user.snapshot;
      }
    }

    if (userObj != undefined) {
      // apeend new user to usersList in chat room object
      chatRoomObj.usersList[chatRoomObj.usersList.length] = userObj;
    }

    // update chat room object in session
    window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));

    var renderData = Mustache.render(chatRoomUsersTemplate, userObj);

    // append new user diaglog block to chat room
    $("#chat-room-users").append(renderData);
  }

  // update chat room object in session
  window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));
}

function hideChatRoom(flag) {
  //console.log('hideChatRoom.hide0');
  // close friend list
  $(".chat-room-friend-list-area").removeClass("friend-list-show", 700, function () {
    // change button icon
    $("#friend-list-toggle-btn i").removeClass("fa-chevron-right");
    $("#friend-list-toggle-btn i").addClass("fa-chevron-left");

    console.log('hideChatRoom.hide1');
    // hide chat room area
    $(".chat-room-area").addClass("chat-room-hide", 600, function () {
      $("#chat-room-fn-area .fa-comments").show(700);
      console.log('hideChatRoom.hide2');

      // for logout use
      if (flag) {
        $(".chat-room-area").hide();
        console.log('hideChatRoom.hide3');
      }
    });
  });

}

function sendMessage(event) {
  if (event.key == "Enter") {
    // get chat room object from sessionStorage
    var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

    var className = $("#chat-message-area").attr("class");
    //userNo is 幾號聊天室
    var userNo = className.substring(className.indexOf("-") + 1);

    // get data from message input area
    var dateObj = new Date();
    var hours = '0' + dateObj.getHours();
    hours = hours.substring(hours.length - 2);
    var minutes = '0' + dateObj.getMinutes();
    minutes = minutes.substring(minutes.length - 2);
    var currentTime = hours + ':' + minutes;

    // set object for render message use
    var msgObj = {
      from: JSON.parse(window.sessionStorage.getItem("loginUser")).userId,
      to: [userNo],
      message: $("#chat-room-input").val(),
      //url: $("btn-file").val(),
      time: Date.now()
    }
    //console.log('msgObj.message: ' + msgObj.message);
    //console.log('msgObj.url: ' + msgObj.url);
    // set object for sending use
    sendWebSocketMessage(msgObj);

    // set formated time for print
    msgObj.time = currentTime;

    // update brief message in users list
    $(".chat-room-user").each(function () {
      if ($(this).children(".chat-room-user-id").text() == userNo) {
        $(this).children(".chat-user-brief-message").text($("#chat-room-input").val());
        $(this).children(".chat-user-message-time").text(currentTime);
      }
    });

    // update chat history in session (sender)
    var flag = true;
    for (let singleChat of chatRoomObj.chatHistory) {
      if (singleChat.userNo == userNo) {
        singleChat.chatContent[singleChat.chatContent.length] = msgObj;
        flag = false;
      }
      //console.log('sender flag: ' + flag);
    }

    if (flag) {
      var chatContent = {
        userNo: userNo,
        chatContent: [msgObj]
      }
      //console.log('sender flag: ' + flag);
      //console.log(chatContent)

      chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
    }

    // update user list
    for (let i = 0, len = chatRoomObj.usersList.length; i < len; i++) {
      if (chatRoomObj.usersList[i].userId == msgObj.to[0]) {
        chatRoomObj.usersList[i].message = msgObj.message;
        chatRoomObj.usersList[i].time = msgObj.time;
        // console.log("Should update here");
      }
    }


    window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));



    // show data from message input area
    $("#chat-message-area").append(Mustache.render(ownMsgTemplate, msgObj));

    // move the scroll bar to the end
    $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
    console.log('scrollL: ' + $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight")));

    // clean message input area
    $("#chat-room-input").val("");
  }
}

function sendMyFile(msgOutput, isHistory) {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

//  var className = $("#chat-message-area").attr("class");
  //userNo is 幾號聊天室
//  var userNo = className.substring(className.indexOf("-") + 1);
  var userNo = msgOutput.to

  // update brief message in users list
  $(".chat-room-user").each(function () {
    if ($(this).children(".chat-room-user-id").text() == userNo) {
      $(this).children(".chat-user-brief-message").attr("src");
      $(this).children(".chat-user-message-time").text(msgOutput.time);
    }
  });

  // update chat history in session (sender)
//  var flag = true;
//  for (let singleChat of chatRoomObj.chatHistory) {
//    if (singleChat.userNo == userNo) {
//  	  if(isHistory){
//		  singleChat.chatContent.unshift(msgOutput); 
//	  }else{
//		  singleChat.chatContent[singleChat.chatContent.length] = msgOutput; 
//	  }
//      flag = false;
//    }
//  }
//
//  if (flag) {
//    var chatContent = {
//      userNo: userNo,
//      chatContent: [msgOutput]
//    }
//
//    chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
//  }

  // update user list
  for (let i = 0, len = chatRoomObj.usersList.length; i < len; i++) {
    if (chatRoomObj.usersList[i].userId == msgOutput.to[0]) {
      chatRoomObj.usersList[i].message = msgOutput.url;
      chatRoomObj.usersList[i].time = msgOutput.time;
      // console.log("Should update here");
    }
  }
  

  // show data from message input area
if(isHistory){
	$("#chat-message-area").prepend(Mustache.render(ownFileTemplate, msgOutput));
}else{
	$("#chat-message-area").append(Mustache.render(ownFileTemplate, msgOutput));
	$("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
}
  

  // move the scroll bar to the end
//  $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
  // clean message input area
  $("#chat-room-input").val("");
}

function sendMyMessage(msgOutput, isHistory) {

//	console.log('sendMyMessage_msgOutput: ' + msgOutput);
	
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

//  var className = $("#chat-message-area").attr("class");
  //userNo is 幾號聊天室
//  var userNo = className.substring(className.indexOf("-") + 1);
  var userNo = msgOutput.to[0];
//  console.log("userNo[0] = " + userNo[0]);
  console.log("userNo = " + userNo);
//  console.log("userNo[0] == 1 = " + (userNo[0] == '1'));
  console.log("userNo == 1 = " + (userNo == '1'));

  // update brief message in users list
  $(".chat-room-user").each(function () {
    if ($(this).children(".chat-room-user-id").text() == userNo) {
      $(this).children(".chat-user-brief-message").text($("#chat-room-input").val());
      $(this).children(".chat-user-message-time").text(msgOutput.time);
    }
  });

  // update chat history in session (sender)
  var flag = true;
  for (let singleChat of chatRoomObj.chatHistory) {
//	  console.log("in loop");
	  
	  if (singleChat.userNo == userNo) {
//		  console.log("XXXXXXXXXXXXX");
		  
	  	  if(isHistory){
			  singleChat.chatContent.unshift(msgOutput); 
//			  console.log("test123456");
		  }else{
			  singleChat.chatContent[singleChat.chatContent.length] = msgOutput; 
		  }
	  	  
	      flag = false;
    }
  }

  if (flag) {
    var chatContent = {
      userNo: userNo,
      chatContent: [msgOutput]
    }
    chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
  }

  // update user list
  for (let i = 0, len = chatRoomObj.usersList.length; i < len; i++) {
    if (chatRoomObj.usersList[i].userId == msgOutput.to[0]) {
      chatRoomObj.usersList[i].message = msgOutput.message;
      chatRoomObj.usersList[i].time = msgOutput.time;
      // console.log("Should update here");
    }
  }

  window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));

  // show data from message input area
  if (msgOutput.url == undefined) {
	  if(isHistory){
		  $("#chat-message-area").prepend(Mustache.render(ownMsgTemplate, msgOutput));
	  }else{
		  $("#chat-message-area").append(Mustache.render(ownMsgTemplate, msgOutput)); 
	  }
  } else {
    sendMyFile(msgOutput, isHistory);
  }


  // move the scroll bar to the end
//  $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));

  // clean message input area
  $("#chat-room-input").val("");
}

function showSnackbarMessage(msg) {
//	  console.log('showsnack');
	  $("#snackbar").html(msg.message);
//	  console.log('html');
	  $("#snackbar").attr("class","show");
//	  console.log(setTimeout);
	  setTimeout(function () {  $("#snackbar").removeClass("show") }, 5000);
	}

function cleanChatRoom() {
  $("#chat-room-users").html("");
  $("#chat-room-content").html("");
  $("#chat-room-friends").html("");
  chatRoom.usersList = [];
}

// WebSocket-STOMP control function
var stompClient = null;

function connectChatRoom() {
  var socket = new SockJS('/GameBase/chat');
  stompClient = Stomp.over(socket);

  // console debug log setting (show / not show message)
  stompClient.debug = null;

  stompClient.connect({}, function (frame) {
    var url = stompClient.ws._transport.url;
    console.log('Connected: ' + frame);
    stompClient.subscribe('/regist/messages', function (msgOutput) {
      // 顯示上線清單
      console.log('regist/messages');
      showOnlineUsers(JSON.parse(msgOutput.body));
    });
    // topic and regist 只是慣用設定，後端沒有特別設定的話就是純廣播
    stompClient.subscribe('/topic/messages', function (msgOutput) {
      if (JSON.parse(msgOutput.body).from == undefined) {
        hideOfflineUser(JSON.parse(msgOutput.body));
      } else {
        showMessageOutput(JSON.parse(msgOutput.body), false);
      }
    });
    stompClient.subscribe('/topic/messages/broadcast', function (msgOutput) {
    console.log('subscribe_broadcast');
    	//  showMessageOutput(JSON.parse(msgOutput.body), false);
    	showSnackbarMessage(JSON.parse(msgOutput.body));
      });
    // /user原始碼-->/queue/message-{websocket session id}
    stompClient.subscribe('/user/queue/messages', function (msgOutput) {
      showMessageOutput(JSON.parse(msgOutput.body), false);
    });
    // subscribe History
    stompClient.subscribe('/user/queue/messages/history', function (msgOutput) {
        showMessageOutput(JSON.parse(msgOutput.body), true);
      });
    // get online list
    var data = JSON.parse(window.sessionStorage.getItem("loginUser"));
    sendWebSocketMessage({ from: data.userId, to: ['regist'], message: '', time: Date.now() });
  });
}

function disconnectChatRoom() {
  if (stompClient != null) {
    stompClient.disconnect();
    console.log("Disconnected");
  } else {
    console.log("Already Disconnected");
  }
}

function sendWebSocketMessage(msg) {
  stompClient.send("/app/chat", {}, JSON.stringify(msg));
  // stompClient.send("/app/chat", {}, JSON.stringify({'msg':'userLogin'}));
}

function showMessageOutput(msgOutput, isHistory) {
	console.log(msgOutput);
	var sender = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;

  // show in user list
  if (sender == msgOutput.from) {
	  console.log('My message');
	  sendMyMessage(msgOutput, isHistory);
  } else {
    showUserListFromMessage(msgOutput, isHistory);


    // get chat room object from sessionStorage
    var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

    // get user snapshot
    for (let friend of JSON.parse(window.sessionStorage.getItem("loginUser")).friendsList) {
      if (friend.userId == msgOutput.from) {
        msgOutput.snapshot = friend.snapshot;
      }
    }

    // update brief message and time in users list
    $(".chat-room-user").each(function () {
      if ($(this).children(".chat-room-user-id").text() == msgOutput.from) {
        $(this).children(".chat-user-brief-message").text(msgOutput.message);
        //$(this).children(".chat-user-brief-file").text(msgOutput.url);
        $(this).children(".chat-user-message-time").text(msgOutput.time);

      }
    });

    // add message into chat history (receiver)
    var flag = true;
    for (let singleChat of chatRoomObj.chatHistory) {
      if (singleChat.userNo == msgOutput.from) {
    	  if(isHistory){
    		  singleChat.chatContent.unshift(msgOutput); 
    	  }else{
    		  singleChat.chatContent[singleChat.chatContent.length] = msgOutput; 
    	  }
        flag = false;
      }
      //console.log('receiver flag: ' + flag);
    }

    if (flag) {
      var chatContent = {
        userNo: msgOutput.from,
        chatContent: [msgOutput]
      }
      //console.log('receiver flag: ' + flag);
      //console.log(chatContent)

      chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
    }

    // show data from message input area
    // msgOutput.from<---userId
    //    console.log('msgOutput.time: ' + msgOutput.time);
    if ($("#chat-message-area").hasClass("id-" + msgOutput.from)) {
      if (msgOutput.url == undefined) {
    	  if(isHistory){
    		  $("#chat-message-area").prepend(Mustache.render(replyMsgTemplate, msgOutput));
    	  }else{
    		  $("#chat-message-area").append(Mustache.render(replyMsgTemplate, msgOutput));
    	  }
      } else {
    	  if(isHistory){
    		  $("#chat-message-area").prepend(Mustache.render(replyFileTemplate, msgOutput));
    	  }else{
    		  $("#chat-message-area").append(Mustache.render(replyFileTemplate, msgOutput));  
    	  }
      }
      // move the scroll bar to the end
      $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
    }

    // update chat room object in session
    	window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));

    

  }
}
function showOnlineUsers(userList) {
  // close all online mark first
  $(".chat-room-friend .chat-room-friend-online-light").hide(500);

  // show specific online mark
  $(".chat-room-friend").each(function () {
    var userNo = $(this).children(".chat-room-user-id").text();
    // console.log(userNo);
    // console.log(userList);
    for (var user of userList) {
      if (userNo == user) {
        $(this).children(".chat-room-friend-online-light").show(500);
      }
    }
  });
}

function hideOfflineUser(user) {
  // hide specific online mark
  $(".chat-room-friend").each(function () {
    //console.log(this);
    var userNo = $(this).children(".chat-room-user-id").text();

    //console.log(userNo);
    //console.log(user);

    if (userNo == user) {
      $(this).children(".chat-room-friend-online-light").hide(500);
      //console.log('This is hideOffineUser');
    }
  });
}

// upload
function upload() {
  document.getElementById("btn-file").click();
}
// upload the image
function upfile() {
  var sender = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
  var className = $("#chat-message-area").attr("class");
  var receiver = className.substring(className.indexOf("-") + 1);
  let formData = new FormData();
  formData.append('file', $("#btn-file")[0].files[0]);
  formData.append('sender', sender);
  formData.append('receiver', receiver);

  $.ajax({
    url: "/GameBase/File",
    type: "POST",
    data: formData,
    contentType: false,
    processData: false,
    success: function (data) {
    }

  });
}

//scroll to load history
//for chat history loading use
//true for execute loading
//false for denied loading
var chatHistoryLoadLock = true;
var chatHistoryInit = true;

//chat history page start from page 2
var chatHistoryPage = 1;

function scrollMyMessage() {
 if (($("#chat-message-area").scrollTop() <= 50 && chatHistoryLoadLock) || chatHistoryInit) {
	 chatHistoryInit = false;
	 console.log('enter');
     var sender = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
     var className = $("#chat-message-area").attr("class");
     var receiver = className.substring(className.indexOf("-") + 1);
     //disable loading
     chatHistoryLoadLock = false;

     // show next page
     displayHistory(sender, receiver);
     chatHistoryPage++;
 }
}
function displayHistory(sender, receiver) {
 console.log('displayHistory');
 let formData = new FormData();
 formData.append('sender', sender);
 formData.append('receiver', receiver);
 
 console.log("sender = " + sender);
 console.log("receiver = " + receiver);
 
 $.ajax({
     url: "/GameBase/Query/" + chatHistoryPage,
     type: "POST",
     data: formData,
     contentType: false,
     processData: false,
     success: function (data) {
         if (data) {
             console.log('scroll_data: + ' + data);
             chatHistoryLoadLock = true;
         }
     }
 });
}


var chatRoomFriendsListTemplate = '{{#friendsList}}'
  + '<div class="chat-room-friend" onclick="showUsersDiaglog(this)">'
  + '<img class="chat-room-friend-icon" src="{{&snapshot}}{{^snapshot}}/GameBase/img/userIcon.png{{/snapshot}}" />'
  + '<div class="chat-room-friend-name">{{account}}</div>'
  + '<div class="chat-room-user-id">{{userId}}</div>'
  + '<div class="chat-room-friend-online-light"></div>'
  + '</div>'
  + '{{/friendsList}}';

var chatRoomUsersTemplate = '<div class="chat-room-user" onclick="showChatContentArea(this)" >'
  + '<img class="chat-user-icon" src="{{&snapshot}}{{^snapshot}}/GameBase/img/userIcon.png{{/snapshot}}" />'
  + '<div class="chat-user-name">{{account}}</div>'
  + '<div class="chat-room-user-id">{{userId}}</div>'
  + '<div class="chat-user-brief-message">{{message}}</div>'
  + '<div class="chat-user-message-time">{{time}}</div>'
  + '</div>';

var chatRoomContentTemplate = '<div id="chat-header-area">'
  + '<div id="chat-header-name">{{account}}</div>'
  + '<i class="fas fa-times" onclick="emptyChatContentArea()"></i>'
  + '</div>'
  + '<div id="chat-message-area" class="id-{{userId}}" onScroll="scrollMyMessage()"></div>'
  + '<div id="chat-tool-area">'
  + '<input type="file" id="btn-file" onchange="upfile()" style="display:none">'
  + '<i class="fas fa-upload upload-btn" onclick="upload()"></i>'
  + '</div>'
  + '<div id="chat-room-input-area">'
  + '<input type="text" id="chat-room-input" autocomplete="off" onkeypress="sendMessage(event)"/>'
  + '</div>';

var replyMsgTemplate = '<div class="chat-messages">'
  + '<img class="chat-message-user-icon" src="{{&snapshot}}{{^snapshot}}/GameBase/img/userIcon.png{{/snapshot}}"/>'
  + '<div class="chat-message">{{&message}}</div>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';

var ownMsgTemplate = '<div class="chat-messages own-messages">'
  + '<div class="chat-message">{{&message}}</div>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';

var replyFileTemplate = '<div class="chat-messages">'
  + '<img class="chat-message-user-icon" src="{{&snapshot}}{{^snapshot}}/GameBase/img/userIcon.png{{/snapshot}}"/>'
  + '<img class="chat-file" src="{{&url}}"/>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';

var ownFileTemplate = '<div class="chat-messages own-messages">'
  + '<img class="chat-file" src="{{&url}}"/>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';


// ******serializeObject*********************
$.fn.serializeObject = function () {
  var formData = {};
  var formArray = this.serializeArray();
  for (var i = 0, n = formArray.length; i < n; ++i) {
    formData[formArray[i].name] = formArray[i].value;
  }
  return formData;
};
// ************checkAcc********************
function checkAcc() {
  if ($("#regist-form .input-group input[name='account']").parent().hasClass("accepted-format")) {
    var acc = $("#regist-form .input-group input[name='account']").serializeObject();
    $.ajax({
      url: "/GameBase/checkAcc",
      data: acc,
      type: "GET",
      contentType: "application/json",
      success: function (status) {
        if (status.result) {
          $("#regist-form .input-group input[name='account']").parent().removeClass("accepted-format");
          $("#regist-form .input-group input[name='account']").parent().addClass("error-format");
          alert("此帳號已被使用");
        } else {
          $("#regist-form .input-group input[name='account']").parent().removeClass("error-format");
          $("#regist-form .input-group input[name='account']").parent().addClass("accepted-format");
        }
      }
    });
  }
}

