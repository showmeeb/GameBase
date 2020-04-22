<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://apis.google.com/js/api:client.js"></script>
<title>Insert title here</title>
</head>
<body>
<i id="google-login-btn" class="fab fa-google"></i>
<script>
var googleUser = {};
var startApp = function() {
  gapi.load('auth2', function(){
    // Retrieve the singleton for the GoogleAuth library and set up the client.
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
	
  // bind click event to specific id button
  auth2.attachClickHandler(element, {},
      function(googleUser) {
	  	  // get id token after login
          var id_token = googleUser.getAuthResponse().id_token;
          
          // connect motozone server
	      $.ajax({
	    	url:"/motozone/Users/GoogleLogin",
	       type:"POST",
	       data:{idTokenStr:id_token},
	    success:function(data){
			    	if(data){					
						// if user has snapshot , then use it
						if(data.snapshot){
							$(".loggedin-icon img").attr("src",data.snapshot);
						}
		
				        // get user information
				        window.sessionStorage.setItem("loginUser",JSON.stringify(data));
        				
					} else {
						alert("Google登入失敗");
					}
	    		}
	    });
      }, function(error) {
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
$(document).ready(function(){
	startApp();
});

</script>    
</body>
</html>