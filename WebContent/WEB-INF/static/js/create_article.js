let editor;

$(document).ready(function(){
    $("#publish-btn").click(function(){
    	console.log("pulish btn");
		$(".publish-area").removeClass("hidden-window",700);
        $("#shadow").fadeIn(700);
// if(window.sessionStorage.getItem("loginUser") != ""){
// $(".publish-area").removeClass("hidden-window",700);
// $("#shadow").fadeIn(700);
// } else {
// alert("請先登入 !");
    		
// $(".login-area").removeClass("hidden-window", 700);
// $("#shadow").fadeIn(700);
// }
        $(".close-btn").click(function(){  
        	console.log("close btn clicked");
            // empty input area
 			$("#articleTitle").val("");
 			$("#accountId").val("");
            editor.setData("");
            
            // close window
            $(".publish-area").addClass("hidden-window", 700);
            $("#shadow").fadeOut(700);     
        });
        
    });
    
	// editor submit
	$("#submit").click(function(){
		console.log("click submit");

		// get img source
		var jsonObj = {"urlList":[]};
		var imgSrc = "";
    	// get all image source in the edit block
        $(".publish-area img").each(function(){
        	jsonObj.urlList[jsonObj.urlList.length] = $(this).attr("src");
        });
        
        // get first image
        if(jsonObj.urlList.length != 0){
        	imgSrc = jsonObj.urlList[0];
        }
        
		// get all img-element's source from the editor block
		$(".publish-area img").each(function(){
			console.log("jsonObj.urlList ="+jsonObj.urlList+", jsonObj.urlList[jsonObj.urlList.length]= "+jsonObj.urlList[jsonObj.urlList.length]);
			console.log($(this).attr("src"));
			jsonObj.urlList[jsonObj.urlList.length] = $(this).attr("src");
        });		
		// get first img's src
		if(jsonObj.urlList.length != 0){
			console.log("first img's src: "+jsonObj.urlList[0]);
			imgSrc = jsonObj.urlList[0];
		}	
		// do function when editor has data and title has value
		if(editor.getData() && $("#articleTitle").val()){
			console.log("has data");
			console.log(editor.getData());
			console.log(url);
			// ajax send data to controller
			$.ajax({
				url:url,
				dataType:"json",
				type:"POST",
				cache: false,
	            data:{articleTitle: $("#articleTitle").val(),
	            	userId: $("#accountId").val(),
	            	firstFigure:imgSrc,
               		content: editor.getData()
               		},
         		success : function(response) {
         			if(lo==="title"){
         				window.location.href=window.location.href+'/'+response.newTitle.titleId;
         			}else{
         				console.log("new reply inserted");
            			/*ajac response*/
    					var txt = '<div>'+
    						'<span>'+
    						'user ID 		: '+response.newContent.userId+'<br/>'+
    						'user Account 	: '+response.account+'<br/>'+
    						'user NickName 	: '+response.nickName+'<br/>'+
    						'</span>'+
    						'<div class="article_time_record">'+
    						'<i class="fas fa-pen-alt">Post Time : '+response.newContent.createTime+'</i><br />'+
    						'</div>'+
    						'<div class="article_time_record">'+
    						'<i class="fas fa-pen-alt">Update Time : '+response.newContent.updateTime+'</i><br />'+
    						'</div>'+
    						'</div>'+
    						'<div class="article_icons">'+
    						'<a id="delete" class="btn_update_content" href="javascript:void(0)"><i class="far fa-trash-alt fa-2x"></i></a>'+
    						'</div>'+
    						'<div class="article_icons">'+
    						'<a id="update" class="btn_update_content" href="javascript:void(0)"><i class="far fa-edit fa-2x"></i></a>'+
    						'</div>'+
    						'<br />'+
    						'<div class="content_content">'+
    						'<span>'+response.newContent.content+'</span><br />'+
    						'</div><hr />';
    					$(".article_part").append(txt);
         			}
         			/* clear input value */
         			$("#articleTitle").val("");
         			$("#accountId").val("");
                    editor.setData("");
                    // close window
                    $(".publish-area").addClass("hidden-window", 700);
                    $("#shadow").fadeOut(700);  
        		}        			
			})
		}
		
	})
});

