<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
.titleW {
	width: 250px;
	height: 50px;
}

.articleW {
	width: 800px;
	padding: 0 2% 0 2%;
}

.otherL {
	height: 100px;
}

.tableMargin {
	margin: 10px auto;
	text-align: center;
	cursor: pointer;
	color: white;
}
</style>

</head>

<body>
	<jsp:include page="topBar.jsp" />

	<div class="container-fluid">
		<div class="row-fluid" id="resultDiv"></div>
	</div>

	<jsp:include page="footer.jsp" />
	
	<script>
	var jsonResults=JSON.parse(JSON.stringify(${results}));

	var txt="";

	
	for ( i =0;i<jsonResults.length;i++){
		txt+='<table class="tableMargin bg-dark" border="1" id="n'+jsonResults[i].forumId+'n'+jsonResults[i].titleId+'"><tr>';
		txt+='<td class="titleW ">'+jsonResults[i].titleName+'</td>'
		txt+='<td rowspan="2" class="articleW">'+jsonResults[i].content+'</td></tr><tr>';
		txt+='<td class="otherL"><div>'+jsonResults[i].clickNum+'</div>'
		txt+='<div>'+jsonResults[i].createTime+'</div></td></tr></table>'		
		}
	$("#resultDiv").html(txt);

	$(document).on('click', '.tableMargin', function() {
		let toForum = (this.id).split("n")[1];
		let toTitle = (this.id).split("n")[2];
		window.location = "/GameBase/forum_test/"+toForum+"/"+toTitle;
	})
	</script>
</body>
</html>