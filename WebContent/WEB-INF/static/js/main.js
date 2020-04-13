//login
$(document).ready(function(){
    $("#login-submit-btn").click(function(){
    	userLogin();
    });
});

function userLogin() {
    var userID = $("#login-form input[name='account']").val();
    var pwd = $("#login-form input[name='password']").val();
console.log(userID);
console.log(pwd);
    // empty check
    if (userID != "" && pwd != "") {
        // for login AJAX operation use
        $.ajax({
            url: "/GameBase/Users/" + userID,
            type: "POST",
            data: {pwd:pwd},
            success: function (data) {
            	console.log('true');
                if (data) {
                    // close login window
//                    $("#login-submit-btn").parent().addClass("hidden-window", 700);
//                    $("#shadow").fadeOut(700);

                    // clear the form
                    $(".input-group input").each(function () {
                        $(this).val("");
                    });

                    $(".input-group").each(function () {
                        $(this).removeClass("error-format accepted-format");
                    });

                    // get user information
                    window.sessionStorage.setItem("UserData", JSON.stringify(data));
                    console.log(window.sessionStroage.getItem("UserData"));
                    console.log('UserData');

                    // connect and show chat room
                    connectChatRoom();
                    $(".chat-room-area").show();

                    // set chat room object
                    chatRoom.friendsList = JSON.parse(window.sessionStorage.getItem("UserData")).friendsList;
                    window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoom));

                    // set friend list to chat room
                    var friendsList = Mustache.render(chatRoomFriendsListTemplate, chatRoom);
                    $("#chat-room-friends").html(friendsList);
                    }
                },error: function(){
                	console.log('false');
                }
        });
    }
}
//chatRoom Area
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
  if (window.sessionStorage.getItem("UserData") == "") {
	  //|| window.sessionStorage.getItem("UserData") == undefined
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
    userObj.uNo = $(this).children(".chat-room-user-no").text();
    userObj.uName = $(this).children(".chat-room-friend-name").text();
    userObj.snapshot = $(this).children(".chat-room-friend-icon").attr("src");

    // in order to check is the user already in the user list
    var checkFlag = true;
    for (let user of chatRoom.usersList) {
      if (user.uNo == userObj.uNo) {
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
  if (window.sessionStorage.getItem("UserData") != "") {
	  //&& window.sessionStorage.getItem("UserData") != undefined
    initChatRoom();
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


}

// user list click event
function showChatContentArea(element) {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));
  // get loginUser object from sessionStorage
  var UserDataObj = JSON.parse(window.sessionStorage.getItem("UserData"));


  var userNo = $(element).children(".chat-room-user-no").text();

  // get user object from user list in chat room object
  var userObj = "";

  for (let user of chatRoomObj.usersList) {
    if (user.uNo == userNo) {
      userObj = user;
    }
  }

  // empty check
  if (userObj != "") {
    var renderData = Mustache.render(chatRoomContentTemplate, userObj);

    $("#chat-room-content").html(renderData);
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
      if (content.from == UserDataObj.uNo) { // from me
        $("#chat-message-area").append(Mustache.render(ownMsgTemplate, content));
      } else { // from others
        $("#chat-message-area").append(Mustache.render(replyMsgTemplate, content));
      }
    }

    // move the scroll bar to the end
    $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
  } else {
    //    	console.log("No history");
  }

}

// chat room content area close button control
function emptyChatContentArea() {
  // clean chat room content area
  $("#chat-room-content").html("");
}

function showUsersDiaglog(element) {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

  // get user data from friend list
  var userObj = {};
  userObj.uNo = $(element).children(".chat-room-user-no").text();
  userObj.uName = $(element).children(".chat-room-friend-name").text();
  userObj.snapshot = $(element).children(".chat-room-friend-icon").attr("src");

  // in order to check is the user already in the user list
  var checkFlag = true;
  for (let user of chatRoomObj.usersList) {
    if (user.uNo == userObj.uNo) {
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

function showUserListFromMessage(msg) {
  // get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

  // in order to check is the user already in the user list
  var checkFlag = true;
  for (let user of chatRoomObj.usersList) {
    if (user.uNo == msg.from) {
      checkFlag = false;

      // update brief message
      user.message = msg.message;
      user.time = msg.time;
    }
  }

  // use check flag to determine render or not
  if (checkFlag) {
    // get user data from friend list
    var userObj = {};
    userObj.uNo = msg.from;
    userObj.message = msg.message;
    userObj.time = msg.time;
    for (let user of chatRoomObj.friendsList) {
      if (user.uNo == userObj.uNo) {
        userObj.uName = user.uName;
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

  // close friend list
  $(".chat-room-friend-list-area").removeClass("friend-list-show", 700, function () {
    // change button icon
    $("#friend-list-toggle-btn i").removeClass("fa-chevron-right");
    $("#friend-list-toggle-btn i").addClass("fa-chevron-left");

    // hide chat room area
    $(".chat-room-area").addClass("chat-room-hide", 600, function () {
      $("#chat-room-fn-area .fa-comments").show(700);

      // for logout use
      if (flag) {
        $(".chat-room-area").hide();
      }
    });
  });

}

function sendMessage(event) {
  if (event.key == "Enter") {
    // get chat room object from sessionStorage
    var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

    var className = $("#chat-message-area").attr("class");
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
      from: JSON.parse(window.sessionStorage.getItem("UserData")).uNo,
      to: [userNo],
      message: $("#chat-room-input").val(),
      time: Date.now()
    }

    // set object for sending use
    sendWebSocketMessage(msgObj);

    // set formated time for print
    msgObj.time = currentTime;

    // update brief message in users list
    $(".chat-room-user").each(function () {
      if ($(this).children(".chat-room-user-no").text() == userNo) {
        $(this).children(".chat-user-brief-message").text($("#chat-room-input").val());
        $(this).children(".chat-user-message-time").text(currentTime);
      }
    });

    // update chat history in session
    var flag = true;
    for (let singleChat of chatRoomObj.chatHistory) {
      if (singleChat.userNo == userNo) {
        singleChat.chatContent[singleChat.chatContent.length] = msgObj;
        flag = false;
      }
    }

    if (flag) {
      var chatContent = {
        userNo: userNo,
        chatContent: [msgObj]
      }

      chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
    }

    // update user list
    for (let i = 0, len = chatRoomObj.usersList.length; i < len; i++) {
      if (chatRoomObj.usersList[i].uNo == msgObj.to[0]) {
        chatRoomObj.usersList[i].message = msgObj.message;
        chatRoomObj.usersList[i].time = msgObj.time;
        //				console.log("Should update here");
      }
    }

    window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));

    // show data from message input area
    $("#chat-message-area").append(Mustache.render(ownMsgTemplate, msgObj));

    // move the scroll bar to the end
    $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));

    // clean message input area
    $("#chat-room-input").val("");
  }
}

function cleanChatRoom() {
  $("#chat-room-users").html("");
  $("#chat-room-content").html("");
  $("#chat-room-friends").html("");
  chatRoom.usersList = [];
}

//WebSocket
var stompClient = null;

function connectChatroom() {
  var socket = new SockJS('GameBase/chat');
  stompClient = Stomp.over(socket);

  stompClient.debug = null;

  stompClient.connect({}, function (frame) {
    var url = stompClient.ws._transport.url;
    //console.log('Connected: ' + frame);
    stompClient.suscribe('/regist/messages', function (msgOutput) {
      showOnlineUsers(JSON.parse(msgOutput.body));
    });

    stompClient.subscribe('/topic/messages', function (msgOutput) {
      if (JSON.parse(msgOutput.body).from == undefined) {
        hideOfflineUser(JSON.parse(msgOutput.body));
      } else {
        showMessageOutput(JSON.parse(msgOutput.body));
      }
    });

    stompClient.subscribe('/user/queue/messages', function (msgOutput) {
      showMessageOutput(JSON.parse(msgOutput.body));
    });

    // get online list
    var data = JSON.parse(window.sessionStorage.getItem("UserData"));
    sendWebSocketMessage({ from: data.uNo, to: ['regist'], message: '', time: Date.now() });
  });
}

function disconnectChatroom() {
  if (stompClient != null) {
    stompClient.disconnect();
    console.log("Disconnected");
  } else {
    console.log("Already Disconnected");
  }
}

function sendWebSocketMessage(msg) {
  stompClient.send("/app/chat", {}, JSON.stringify(msg));
}

function showMessageOutput(msgOutput) {
  //show in user list
  showUserListFromMessage(msgOutput)

  //get chat room object from sessionStorage
  var chatRoomObj = JSON.parse(window.sessionStorage.getItem("chatRoom"));

  //get user snapshot
  for (let friend of JSON.parse(window.sessionStorage.getItem("UserData")).friendsList) {
    if (friend.uNo == msgOutput.from) {
      msgOutput.snapshot = friend.snapshot;
    }
  }

  //update brief message and time in users list
  $(".chat-room-user").each(function () {
    if ($(this).children(".chat-room-user-no").text() == msgOutput.from) {
      $(this).children(".chat-user-brief-message").text(msgOutput.message);
      $(this).children(".chat-user-message-time").text(msgOutput.time);
    }
  });

  //add message into chat history
  var flag = true;
  for (let singleChat of chatRoomObj.chatHistory) {
    if (singleChat.userNo == msgOutput.from) {
      singleChat.chatContent[singleChat.chatContent.length] = msgOutput;
      flag = false;
    }
  }
  if (flag) {
    var chatContent = {
      userNo: msgOutput.from,
      chatContent: [msgOutput]
    }

    chatRoomObj.chatHistory[chatRoomObj.chatHistory.length] = chatContent;
  }

  // show data from message input area
  if ($("#chat-message-area").hasClass("userNo-" + msgOutput.from)) {
    $("#chat-message-area").append(Mustache.render(replyMsgTemplate, msgOutput));

    // move the scroll bar to the end
    $("#chat-message-area").scrollTop($("#chat-message-area").prop("scrollHeight"));
  }

  // update chat room object in session
  window.sessionStorage.setItem("chatRoom", JSON.stringify(chatRoomObj));
}

function showOnlineUsers(userList) {
  // close all online mark first
  $(".chat-room-friend .chat-room-friend-online-light").hide(500);

  // show specific online mark
  $(".chat-room-friend").each(function () {
    var userNo = $(this).children(".chat-room-user-no").text();

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
    var userNo = $(this).children(".chat-room-user-no").text();
    //		console.log(userNo);

    if (userNo == user) {
      $(this).children(".chat-room-friend-online-light").hide(500);
    }
  });
}
//chat room friend list template
//<div class="chat-room-friend">
//	<img class="chat-room-friend-icon" src="img/userIcon.png" />
//	<div class="chat-room-friend-name">我GM</div>
//	<div class="chat-room-user-no">100</div>
//	<div class="chat-room-friend-online-light"></div>
//</div>

var chatRoomFriendsListTemplate = '{{#friendsList}}'
  + '<div class="chat-room-friend" onclick="showUsersDiaglog(this)">'
  + '<img class="chat-room-friend-icon" src="{{&snapshot}}{{^snapshot}}/motozone/img/userIcon.png{{/snapshot}}" />'
  + '<div class="chat-room-friend-name">{{uName}}</div>'
  + '<div class="chat-room-user-no">{{uNo}}</div>'
  + '<div class="chat-room-friend-online-light"></div>'
  + '</div>'
  + '{{/friendsList}}';

// chat room users template
//<div class="chat-room-user" onclick="showChatContentArea(this)" >
//    <img class="chat-user-icon" src="img/userIcon.png" />
//    <div class="chat-user-name">名字名字名字名字名字名字名字</div>
//    <div class="chat-room-user-no">0</div>
//    <div class="chat-user-brief-message">
//        訊息訊息訊息訊息訊息訊息訊息訊息
//    </div>
//    <div class="chat-user-message-time">
//        下午13:03
//    </div>
//</div>

var chatRoomUsersTemplate = '<div class="chat-room-user" onclick="showChatContentArea(this)" >'
  + '<img class="chat-user-icon" src="{{&snapshot}}{{^snapshot}}/motozone/img/userIcon.png{{/snapshot}}" />'
  + '<div class="chat-user-name">{{uName}}</div>'
  + '<div class="chat-room-user-no">{{uNo}}</div>'
  + '<div class="chat-user-brief-message">{{message}}</div>'
  + '<div class="chat-user-message-time">{{time}}</div>'
  + '</div>';

// char room content area template
//<div id="chat-header-area">
//    <div id="chat-header-name">
//        名字在這名字在這名字在這名字在這名字在這名字在這
//    </div>
//    <i class="fas fa-times" onclick="emptyChatContentArea()"></i>
//</div>
//<div id="chat-message-area">
//    <div class="chat-messages">
//        <img class="chat-message-user-icon" src="img/userIcon.png"/>
//        <div class="chat-message">
//            安安安安安安安
//        </div>
//        <div class="chat-time">
//            下午 13:03
//        </div>
//    </div>
//    <div class="chat-messages own-messages">
//        <img class="chat-message-user-icon" src="img/userIcon.png"/>
//        <div class="chat-message">
//            安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安
//        </div>
//        <div class="chat-time">
//            下午 13:03
//        </div>
//    </div>
//</div>
//<div id="chat-room-input-area">
//    <input type="text" id="chat-room-input"/>
//</div>

var chatRoomContentTemplate = '<div id="chat-header-area">'
  + '<div id="chat-header-name">{{uName}}</div>'
  + '<i class="fas fa-times" onclick="emptyChatContentArea()"></i>'
  + '</div>'
  + '<div id="chat-message-area" class="userNo-{{uNo}}"></div>'
  + '<div id="chat-room-input-area">'
  + '<input type="text" id="chat-room-input" autocomplete="off" onkeypress="sendMessage(event)"/>'
  + '</div>';


//chat message template
//<div id="chat-message-area">
//<div class="chat-messages">
//    <img class="chat-message-user-icon" src="img/userIcon.png"/>
//    <div class="chat-message">
//       	 安安安安安安安
//    </div>
//    <div class="chat-time">
//        	下午 13:03
//    </div>
//</div>
//<div class="chat-messages own-messages">
//    <img class="chat-message-user-icon" src="img/userIcon.png"/>
//    <div class="chat-message">
//       	 安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安安
//    </div>
//    <div class="chat-time">
//        	下午 13:03
//    </div>
//</div>
//</div>

var replyMsgTemplate = '<div class="chat-messages">'
  + '<img class="chat-message-user-icon" src="{{&snapshot}}{{^snapshot}}/motozone/img/userIcon.png{{/snapshot}}"/>'
  + '<div class="chat-message">{{&message}}</div>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';

var ownMsgTemplate = '<div class="chat-messages own-messages">'
  + '<div class="chat-message">{{&message}}</div>'
  + '<div class="chat-time">{{time}}</div>'
  + '</div>';