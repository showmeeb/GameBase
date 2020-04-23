$("#document").ready(function () {
	console.log("this is content.js2");
	
	/*record button clicked*/
	$(".btn_record").click(function(){
		console.log("record btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id");
		console.log(btn);
		
		update_content(btn);
	});
	
	/*update content button clicked*/
	$(".btn_update_content").click(function(){
		console.log("update content btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id")
		console.log(btn);
		
		update_content(btn);
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
	})
}