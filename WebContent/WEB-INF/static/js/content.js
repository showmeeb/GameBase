$("#document").ready(function () {
	console.log("this is content.js2");
	
	/*record button clicked*/
	$(".btn_record").click(function(){
		console.log("record btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id");
		console.log(btn);
	});
	
	/*update content button clicked*/
	$(".btn_update_content").click(function(){
		console.log("update content btn clicked");
		/*identify which btn been clicked */
		var btn = $(this).attr("id")
		console.log(btn);
		
		update_content();
	});
	
});

function update_content(btn){
	
}