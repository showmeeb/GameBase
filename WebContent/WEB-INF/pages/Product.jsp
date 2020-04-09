<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
	<div>
		<input type="button" id="query" value="q">
	</div>

	<div id="d1">
		<form id="f1">
			<table id="t1">


			</table>
			<input type="button" id="add" value="新增">
		</form>
	</div>

<script type="text/javascript">
		
			$(document).on("click","#query",function(){
				$('#d1').toggle();
				$.ajax({
					url:"query",
					dataType : "json",
					type:"POST",
					success:function(response){
						console.log(response);
							var txt = "<tr><th>商品編號<th>商品照片<th>商品名稱<th>商品價錢<th>商品分類<th>商品介紹<th>商品出版<th colspan='2'>設定";
							for(let i=0;i<response.length;i++){
								txt +="<tr><td>"+response[i].itemId;
								txt +="<td id='img'>";
								txt +="<td>"+response[i].itemName;
								txt +="<td>"+response[i].itemPrice;
								txt +="<td>"+response[i].itemCategory;
								txt +="<td>"+response[i].itemIntro;
								txt +="<td>"+response[i].itemPublisher;
								txt +='<td><input type="button" id="update" value="修改">';
								txt +='<td><input type="button" id="delete" value="刪除">';
								}	
							$('#t1').html(txt);
						}
					});
			})

			$(document).on('click','#update',function(){
			$(this).attr({id:"done",value:"完成"});
			var $tr=$(this).parents("tr"); 

			$tr.find("td").not($("td:has(input)")).each(function(){ //获取当前行所有除了含有button的td
		        var $td=$(this);
		        var _t=$td.text();
		        var _w=$td.width();
		        //var _h=$td.height();
		        $td.html("");
		        var $input=$("<input type='text'>");
		        $input.appendTo($td).width(_w).val(_t);
		    });
			});

		$(document).on('click','#done',function(){
			$(this).attr({id:"update",value:"修改"});
			var $tr = $(this).parents("tr");
			var c ={};
			$tr.find("td input:text").each(function(i,e){
					c[i]=e.value;
				})
			console.log(c);
			var b=JSON.stringify(c);
			console.log(b);
			$.ajax({
				url:"update",
				dataType : "json",
				type:"POST",
				data:{b:b},
				success:function(response){
					console.log(response);
					alert("修改成功");
					}
				
			});
			//var $tr=$this.parents("tr");

			//$tr.find("td").not($("td:has(input)")).each(function(){
			
	    	//});
		});
		
		$(document).on('click','#delete',function(){
			var $tr = $(this).parents("tr");
			var d=$tr.find("td").eq(0).text();
			$.ajax({
				url:"delete",
				dataType : "json",
				type:"POST",
				data:{d:d},
				success:function(response){
					console.log(response);
					alert("刪除成功");
					},
				complete:function(){
					$tr.remove();
					}
			});
			});



		$(document).on('click','#add',function(){
			
			var txt ="<tr><td><input type='text'>";
			txt +="<td id='img'><input type='file'>";
			txt +="<td><input type='text'>";
			txt +="<td><input type='text'>";
			txt +="<td><input type='text'>";
			txt +="<td><input type='text'>";
			txt +="<td><input type='text'>";
			txt +='<td><input type="button" id="add1" value="送出">';
			txt +='<td><input type="button" id="delete" value="刪除">';
			$('#t1').append(txt);
		});
		$(document).on('click','#add1',function(){
			$(this).attr({id:"update",value:"修改"});
			var $tr = $(this).parents("tr");
			var a ={};
			$tr.find("td input:text").each(function(i,e){
				a[i]=e.value;
			})

			$.ajax({
				url:"add",
				dataType : "json",
				type:"POST",
				data:{a:a},
				success:function(response){
					console.log(response);
					alert("新增成功");
					}
				
			});
			});




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

</script>
</body>
</html>