	var img;
	

	$(document).on('click','#gameMode',function(){
		$('#gameMode').attr({id:"hostMode",value:"host"}).html("主機");
		$('#dg').hide();
		$('#dh').show();
		
		})
		
	$(document).on('click','#hostMode',function(){
		$('#hostMode').attr({id:"gameMode",value:"game"}).html("遊戲");
		$('#dh').hide();
		$('#dg').show();
		})
		
	$(document).on('change','#pImg',function(){
		var formData = new FormData();
		formData.append('img', $('#pImg')[0].files[0]);
		console.log($('#pImg')[0].files[0]);
		console.log(formData);
		$.ajax({
		    url: 'tradesystem/img',
		    type: 'POST',
		    cache: false,
		    data:formData,
		    processData: false,
		    contentType: false,
		    success:function(respose){
		    	img=respose;
		    	console.log(img);
		    }
		})

	})

	$(document).on('click','#s1',function(){
		var a=$(this.form).serializeObject();
//		var formData = new FormData();
//		formData.append('img', $('#pImg')[0].files[0]);
//		console.log($('#pImg')[0].files[0]);
//		console.log(formData);
		a.productImg = img;
		
		var form = JSON.stringify(a);
		console.log(form);
		$.ajax({
			url:"tradesystem/add",
			data : {form : form},
			dataType : "json",
			type : "POST",
			success : function(response) {
				console.log(response.t);
				if(response.t==true){
					alert("登記成功");
					}
				else{
					alert("登記失敗");
					}
			}


			});
		})
	
	
	$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
		$(document).ready(function(){
			$("#admin-product").removeClass("d-none").addClass("d-block");
			})