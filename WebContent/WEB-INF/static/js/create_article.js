let editor;

$(document).ready(function(){
	console.log("lo :"+lo);
//	open_editor();	
	
    $("#publish-btn").click(function(){
    	console.log("pulish btn");
    	
        //login status identify
        if(window.sessionStorage.getItem("loginUser") != ""){
        	userId = userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
        	$(".publish-area").removeClass("hidden-window",700);
        	$("#shadow").fadeIn(700);
        	} else {
        	alert("請先登入 !");       	    		
        	$(".login-area").removeClass("hidden-window", 700);
        	$("#shadow").fadeIn(700);
        	}
        
        $(".close-btn").click(function(){  
        	console.log("close btn clicked");
            // empty input area
 			$("#articleTitle").val("");
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
	            data:{
	            	articleTitle: $("#articleTitle").val(),
	            	userId: userId,
	            	firstFigure:imgSrc,
               		content: editor.getData()
               		},
         		success : function(response) {
         			if(lo==="title"){
         				window.location.href=window.location.href+'/'+response.newTitle.titleId;
         			}else{

         			}
         			/* clear input value */
         			$("#articleTitle").val("");        			
                    editor.setData("");
                    // close window
                    $(".publish-area").addClass("hidden-window", 700);
                    $("#shadow").fadeOut(700);  
        		}        			
			})
		}
		
	})
});
//static function

//user login open editor btn 
function open_editor() {
	console.log("open editor");
    if(window.sessionStorage.getItem("loginUser") != ""){
    	userId = userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
    	$(".article_create_btn").attr("hidden",false);
    } else {  
    	$(".article_create_btn").attr("hidden",true);
    }
}

//static  variable
var userId;

var createArticleTemplate = 
	'<div class="article_article">'
	+'<div class="article_name">'
	+'<h2>'
	+'<a href="<c:url value="/forum_test/${forumId}/${item.titleId}"/>">${item.titleName}</a>'
	+'</h2>'
	+'</div>'
	+'<div class="article_content">'
	+'<c:if test="${not empty item.firstFigure}">'
	+'<div class="article_content_img">'						
	+'<img alt="圖片提示字串" src="${item.firstFigure}" height="100">'
	+'</div>'
	+'</c:if>'
	+'<div class="article_content_content">'
	+'<span>${item.content}</span>'
	+'</div>'
	+'</div>'
	+'<div class=" article_datas">'
	+'<div class="article_icons">'
	+'<i class="far fa-thumbs-up fa-2x">${item.likeNum}</i>'
	+'</div>'
	+'<div class="article_icons">'
	+'<i class="far fa-thumbs-down fa-2x">${item.unlikeNum}</i>'
	+'</div>'
	+'<div class="article_icons">'
	+'<i class="far fa-eye fa-2x">${item.clickNum}</i>'
	+'</div>'
	+'<div class="article_time_record">'
	+'<i class="fas fa-pen-alt fa-2x"></i>Post Time:${item.createTime}'
	+'</div>'
	+'<div class="article_time_record">'
	+'<i class="far fa-comment-dots fa-2x"></i>Last Reply:${item.lastReplyTime}'
	+'</div>'
	+'</div>'
	+'</div>';

var createReplyTemplate;
