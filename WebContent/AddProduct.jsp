<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style type="text/css">
#dh{display: none}

</style>
</head>
<body>
切換註冊商品<button id="gameMode" value="game">遊戲</button>
<div id ="dg">
	<form id="form1">
		<div>
			圖像<input type="file" id="pImg" name="img">
		</div>
		<div>
			遊戲名稱<input type="text" id="pName" name="productName">
		</div>
		<div>
			商品類型<input type="text" id="pType" name="productType" value="game">
		</div>
		<div>
			商品數量<input type="text" id="pInventory" name="inventory">
		</div>
		<div>
			商品價錢<input type="text" id="pPrice" name="productPrice">
		</div>
		
		<div>
			遊戲介紹<input type="text" id="pInfo" name="productInfo">
		</div>
		<div>
			遊戲類型 <select id="gTag" name="gameTag">
						<option>角色扮演</option>
						<option>射擊</option>
						<option>策略</option>
						<option>愛情</option>
						<option>羞羞臉的遊戲</option>
				  </select>
		</div>
		<div>
			遊戲平台<input type="text" id="gPlatform" name="gamePlatform">
		</div>
		<div>
			遊戲分級<input type="text" id="gLevel" name="gameLevel">
		</div>
		<div>
			<input type="button" id="s1" value="送出">
		</div>
	</form>
</div>
<div id ="dh">
	<form id="form2">
		<div>
			圖像<input type="file" id="pImg" name="img">
		</div>
		<div>
			商品名稱<input type="text" id="pName" name="productName">
		</div>
		<div>
			商品類型<input type="text" id="pType" name="productType" value="host">
		</div>
		<div>
			商品數量<input type="text" id="pInventory" name="inventory">
		</div>
		<div>
			商品價錢<input type="text" id="pPrice" name="productPrice">
		</div>
		
		<div>
			商品介紹<input type="text" id="pInfo" name="productInfo">
		</div>
		
		<div>
			<input type="button" id="s1" value="送出">
		</div>
	</form>
</div>
	<script type="text/javascript">
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


	
	</script>
</body>
</html>