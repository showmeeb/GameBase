//login & logout
$(document).ready(function(){
    $("#login-submit-btn").click(function(){
    	userLogin();
    });
    function userLogin() {
        var account = $("#login-form input[name='account']").val();
        var pwd = $("#login-form input[name='password']").val();
    console.log(account);
    console.log(pwd);
        // empty check
        if (account != "" && pwd != "") {
            // for login AJAX operation use
            $.ajax({
                url: "/GameBase/Users/" + account,
                type: "POST",
                data: {pwd:pwd},
                success: function (data) {
                	console.log('true');
                	console.log(data);
                    if (data) {

                        // clear the form
                        $(".input-group input").each(function () {
                            $(this).val("");
                            console.log(this);
                        });

                        $(".input-group").each(function () {
                            $(this).removeClass("error-format accepted-format");
                            console.log(this);
                        });

                        // get user information
                        window.sessionStorage.setItem("UserData", JSON.stringify(data));
//                        console.log(window.sessionStorage.getItem("UserData"));

                        // connect and show chat room
                        connect();
        }
    }
            });
            }
    }
        
                $("#logout").click(function(){
                	console.log('click logout');  
                                           
                        // get userId for offline message use
                    	var userId = JSON.parse(window.sessionStorage.getItem("UserData")).userId;
                    	// send offline message to everyone
//                		sendWebSocketMessage({from:userId,to:['logout'],message:'',time:Date.now()});
                        
                        $.ajax({
                    		url:"/GameBase/Users/" + JSON.parse(window.sessionStorage.getItem("UserData")).account,
                    	   type:"DELETE",
                    	success:function(data){
                    		console.log('logout success');	
                    				// clean session
                    				window.sessionStorage.setItem("UserData","");
                    				window.sessionStorage.setItem("chatRoom","");
                    				
                    				
                    				// hide and clean chat room
//                    				hideChatRoom(true);
//                    				cleanChatRoom();
                    		        disconnect();
                    			}
                    	});
                    });
});
//WebSocket Area
var stompClient = null;

function connect() {
  console.log('connect Chatroom');
  //連線SockJS的Endpoint
  var socket = new SockJS('/GameBase/chat');
  //使用Stomp子協議的WebSocket客戶端
  stompClient = Stomp.over(socket);
  console.log(socket);
  //console debug log setting (show / not show message)
  stompClient.debug = null;
  //連線WebSocket服務端
  stompClient.connect({}, function (frame) {
    var url = stompClient.ws._transport.url;
    console.log('Connected: ' + frame);
    //通過stompClient.subscribe訂閱/regist/messages 目標(destination)傳送的訊息
    //stompClient.subscribe('/regist/messages', function (msgOutput) {
    	//顯示上線的使用者
    	//showOnlineUsers(JSON.parse(msgOutput.body));
    //});
    //通過stompClient.subscribe訂閱/topic/messages 目標(destination)傳送的訊息
    //stompClient.subscribe('/topic/messages', function (msgOutput) {
      //if (JSON.parse(msgOutput.body).from == undefined) {
    	  //隱藏離線的使用者
    	  //hideOfflineUser(JSON.parse(msgOutput.body));
      //} else {
        //showMessageOutput(JSON.parse(msgOutput.body));
      //}
    //});
    //通過stompClient.subscribe訂閱/user/queue/messages 目標(destination)傳送的訊息
    stompClient.subscribe('/user/queue/messages', function (response) {
      showResponse(JSON.parse(response.body));
    });
    console.log('connect');
    // get online list
    //var data = JSON.parse(window.sessionStorage.getItem("UserData"));
    //sendWebSocketMessage({ from: data.userId, to: ['regist'], message: '', time: Date.now() });
  });
}

function disconnect() {
  if (stompClient != null) {
    stompClient.disconnect();
    console.log("Disconnected");
  } else {
    console.log("Already Disconnected");
  }
}
function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;
    stompClient.send("/app/chat", {}, 
      JSON.stringify({'from':from, 'text':text}));
    // set object for render message use
    var msgObj = {
      from: JSON.parse(window.sessionStorage.getItem("UserData")).userId,
      to: [userId],
      message: $("#chat-room-input").val(),
      time: Date.now()
    }
}
function showResponse(){
	var response = $("#response");
	response.append(message);
}