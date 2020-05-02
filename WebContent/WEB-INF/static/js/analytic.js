$(document).ready(function(){
			//no-repeat-num第一格數字
			$.ajax({
				url : "GameBase/getIpWeek",
				dataType : "json",
				type : "POST",
				success : function(response) {
					console.log(response);
					console.log(response.length);
//					console.log(response.norepeat);
//					console.log(response.norepeat.length);
					
					var no_repeat=response.norepeat.length;
					var total_num=0;
					
					$("#no-repeat-num").html(no_repeat);
					for(var i=0;i<response.totalnum.length;i++){
						var num=response.totalnum[i].logtime ;
						total_num+=num;
						}
					$("#total-num").html(total_num);
					}
	
				})

			})