$(document).ready(function(){
	
	console.log("document ready");
	
	selected_forumId;
	selected_forumName;
	selected_forumFigure;
	userId = JSON.parse(window.sessionStorage.getItem("loginUser")).userId;
	
	$(".forum_editor_btn").attr("hidden",false);
	
	//open forum editor
    $("#publish-btn").click(function(){
    	console.log("pulish btn");
    	//判斷登入狀況
        if(window.sessionStorage.getItem("loginUser") != ""){
       	 $("#publish-area").removeClass("hidden-window",700);
       	 $("#shadow").fadeIn(700);
       	 $("#submit").attr("hidden",false);
       	 } else {
       	 alert("請先登入 !");       	    		
       	 $(".login-area").removeClass("hidden-window", 700);
       	 $("#shadow").fadeIn(700);
       	 }       
    });
    
    //forum editor close btn
    $(".forum_close_btn").click(close_window);

    
	//editor forum btn clciked
    $(".forum_update_btn").click(function(){
    	console.log("forum_update_btn");
		$(".forum_editor").removeClass("hidden-window",700);
        $("#shadow").fadeIn(700);   
        $("#submit").attr("hidden",true);
        $("#update").attr("hidden",false);
        
        //find select value id, name, figure 
        selected_forumId = $(this).attr("forumId");
        console.log(selected_forumId);
        selected_forumName = $(".forumdata[forumid='"+selected_forumId+"'] .fname").text();
        selected_forumFigure = $(".forumdata[forumid='"+selected_forumId+"'] .ffigure").text();
        console.log(selected_forumName);
        console.log(selected_forumFigure);

        $("#forumName").val(selected_forumName);		        
        $("#previewImage").attr('src',selected_forumFigure);
        
        //test
        var test = $(".forum_forum[forumid="+selected_forumId+"] .forum_name a").text();
        console.log("test2");
        console.log(test);
    });
	    
	//update figure btn clicked 
	$("#update").click(update_forum);
	
	//figure preview
	$("#forumFigure").change(function() {
		
    	var fileReader = new FileReader();
    	
		fileReader.onload = function(e) {
		$("#previewImage").show();
		$("#previewImage").attr('src',e.target.result);
		}
        var imageFile = this.files[0];
        fileReader.readAsDataURL(imageFile);
	});
	
	//form editor submit
	$("#submit").click(function(){
		console.log("submit");
		//forumData
		var forumData = new FormData();
		var forumFigure = "";
		if( $("#forumFigure")[0].files[0] != null ){
			forumFigure = $("#forumFigure")[0].files[0];
			console.log(forumFigure);
			}
		var forumName = $("#forumName").val();
		forumData.append("forumName", forumName);
		forumData.append("forumFigure",forumFigure);
		
		//ajax
		$.ajax({
			url:'/GameBase/forum_test/add/',
			processData: false,		
			type:"POST",
			cache: false,
			contentType : false,
	        data:forumData,
			success:function(response) {        	            
 	            var newForum = Mustache.render(newForumTemplate, response.newForum);
	            $("#forum_list").append(newForum);
		        	        
				$(".forum_forum[forumid="+response.newForum.forumId+"]")
				.on("click",".forum_del_btn",del_forum)
				.on("click",".forum_update_btn",update_forum);
				//reset create forum form value
				$("#forumFigure").val("");
				$("#forumName").val("");
				$("#previewImage").attr('src','');
		        // close window
				close_window(); 
			}
		});
	});
	
	// delete forum 
	$(".forum_del_btn").click(del_forum);

});

//variable
var selected_forumId;
var selected_forumName;
var selected_forumFigure;
var userId;

//close forum editor
function close_window(){  
	console.log("close btn clicked");
    // empty input area
    $("#forumName").val("");
    $("#forumFigure").val("");
    $("#previewImage").attr('src','');
    // close window
    $(".forum_editor").addClass("hidden-window", 700);
    $("#shadow").fadeOut(700);
    $("#submit").attr("hidden",false);
    $("#update").attr("hidden",true);
}

//update forum
function update_forum(){
	//forumData
	var forumData = new FormData();
	var forumFigure = null;
	var forumName = $("#forumName").val();	

	if( $("#forumFigure")[0].files[0] != null ){
		forumFigure = $("#forumFigure")[0].files[0];
		console.log(forumFigure);
		}	
	
	forumData.append("forumFigure",forumFigure);
	forumData.append("forumName", forumName);
	
	$.ajax({
		url:"/GameBase/forum_test/"+selected_forumId+"/update",				
		processData: false,		
		type:"POST",
		cache: false,
		contentType : false,
        data:forumData,
		success:function(response){
	        console.log("update success");
			//update data to js
			selected_forumName = response.forum.forumName;
			selected_forumFigure = response.forum.forumFigure;
	        console.log(selected_forumName);
	        console.log(selected_forumFigure);
	        // empty input area				
	        $("#forumName").val("");
	        $("#forumFigure").val("");
	        $("#previewImage").attr('src','');
	        // close window
			close_window();
	        //update data
			$(".forum_forum[forumid="+selected_forumId+"] .forum_name a").text(selected_forumName);
	        $(".forum_forum[forumid="+selected_forumId+"] .forum_img img").attr("src",selected_forumFigure);
	        $(".forumdata[forumid='"+selected_forumId+"'] .fname").text(selected_forumName);
	        $(".forumdata[forumid='"+selected_forumId+"'] .ffigure").text(selected_forumFigure);
		}
	});
}
//delete forum 
function del_forum(){
	console.log("delete forum, forum ID : "+$(this).attr("id"));
	var forumId=$(this).attr("forumId");
	$.ajax({
		url:'/GameBase/forum_test/'+forumId+'/del',
		type:"POST",
		cache: false,
        data:{
        	forumId:forumId
        },
        success:function(response) {
        	console.log("delete success")
        	$(".forum_forum[forumId="+forumId+"]").remove();
        }
	});
}

var newForumTemplate =
	'<div class="forum_forum" forumId="{{forumId}}">'
	+'<div class="forum_title_bar">'
	+'<div class="forum_name">'
	+'<h2>'
	+'{{forumRank}}.'
	+'<a href="/GameBase/forum_test/{{forumId}}">{{forumName}}</a>'
	+'</h2>'
	+'</div>'
	+'<div class="forum_editor_btn" >'
	+'<a forumId="{{forumId}}" class="forum_del_btn" href="javascript:void(0)">'
	+'<i class="far fa-trash-alt fa-2x"></i>'
	+'</a>'
	+'<br />'
	+'</div>'
	+'<div class="forum_editor_btn" >'
	+'<a forumId="{{forumId}}" class="forum_update_btn" href="javascript:void(0)">'
	+'<i class="far fa-edit fa-2x"></i>'
	+'</a>'
	+'<br />'
	+'</div>'
	+'</div>'
	+'<div class="forum_content">'
	+'<div class="forum_img">'
	+'<img alt="圖片提示字串" src="{{forumFigure}}" height="320">'
	+'</div>'
	+'<div class="forum_articles">'
	+'<span>熱門點閱文章:</span><br />'
	+'<a href="/GameBase/forum_test/{{forumId}}/{{titleId}}">{{titleName}}</a>'
	+'<br />'
	+'</div>'
	+'</div>'		
	+'<div class="forumdata" forumId="{{iforumId}}" hidden>'
	+'<div class="fname">{{forumName}}</div>'
	+'<div class="ffigure">{{forumFigure}}</div>'
	+'</div>'				
	+'</div>';
