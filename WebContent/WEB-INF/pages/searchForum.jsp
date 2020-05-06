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
.ulMargin{
margin-left:45%;
display: block;
text-align:center;
}
</style>

</head>

<body>
	<jsp:include page="topBar.jsp" />

	<div class="container-fluid">
	<div>
	<ul class="pagination ulMargin" id="pageid">
	</ul>
	</div>
		<div class="row-fluid" id="resultDiv"></div>
	</div>
	

	<jsp:include page="footer.jsp" />
	
	<script>
    const pageid = document.getElementById('pageid');   	
    (function getText() {
    var results = JSON.stringify(${results});
    window.sessionStorage.setItem("aaa",results);
    var jsonData =JSON.parse(window.sessionStorage.getItem("aaa"));
	var obj = Object.keys(jsonData).map(function(_) { return jsonData[_]; });
	pagination(obj,1);
    })();

    function pagination(data,nowPage) {

    	  const dataTotal = data.length;
    	  const perpage = 5;
    	  const pageTotal = Math.ceil(dataTotal / perpage);       	  
    	  let currentPage = nowPage;       	  
    	  if (currentPage > pageTotal) {
    		    currentPage = pageTotal;
    		  }
    	  const minData = (currentPage * perpage) - perpage + 1 ;
    	  const maxData = (currentPage * perpage) ;
    	  const data1 = [];
    	  data.forEach((item, index) => {
				const num = index + 1;
			if ( num >= minData && num <= maxData) {
  			data1.push(item);
			}
			})
			const page = {
    		pageTotal,
			currentPage,
			hasPage: currentPage > 1,
			hasNext: currentPage < pageTotal,
			}
    	  displayData(data1);
    	  pageBtn(page);
    	}
    
    function displayData(data1) {
    	var txt="";

    	for ( i =0;i<data1.length;i++){
    		txt+='<table class="tableMargin bg-dark" border="1" id="n'+data1[i].forumId+'n'+data1[i].titleId+'"><tr>';
    		txt+='<td class="titleW ">'+data1[i].titleName+'</td>'
    		txt+='<td rowspan="2" class="articleW">'+data1[i].content+'</td></tr><tr>';
    		txt+='<td class="otherL"><div>'+data1[i].clickNum+'</div>'
    		txt+='<div>'+data1[i].createTime+'</div></td></tr></table>'		
    		}
    	$("#resultDiv").html(txt);
    	}
    
    function pageBtn (page){
    	  let str = '';
    	  const total = page.pageTotal;
    	  
    	  if(page.hasPage) {
    		    str += "<li ><a href='#' data-page='"+(page.currentPage - 1) +"'>" + "&laquo;" + "</a></li>";
    		  } else {
    		    str += "<li disabled><span>" + "&laquo;" + "</span></li>";
    		  }
    	  
    	  for(let i = 1; i <= total; i++){
    		  if(Number(page.currentPage) === i) {
    		  	str += "<li class='active'><a href='#' data-page='" + i + "'>" + i + "</a></li>";
    		  } else {
    			str += "<li><a href='#' data-page='" + i + "'>" + i + "</a></li>";
    		  }
    	  }
    	  if(page.hasNext) {
    		  	str += "<li ><a href='#' data-page='"+ ( Number(page.currentPage)+1) +"'>" + "&raquo;" + "</a></li>";
    		  } else {
    			str += "<li disabled><span>" + "&raquo;" + "</span></li>";
    		  }
    	 
    	  $(pageid).html(str);
    	}
   
    function switchPage(e){
    	  e.preventDefault();
    	  if(e.target.nodeName !== 'A') return;
    	  const page = e.target.dataset.page;
    	  console.log("第"+page+"頁");
    	  var jsonData =JSON.parse(window.sessionStorage.getItem("aaa"));
      	  var obj = Object.keys(jsonData).map(function(_) { return jsonData[_]; });
    	  pagination(obj, page);
    	}
  	pageid.addEventListener('click', switchPage);
/*	var jsonResults=JSON.parse(JSON.stringify(${results}));

	var txt="";

	
	for ( i =0;i<jsonResults.length;i++){
		txt+='<table class="tableMargin bg-dark" border="1" id="n'+jsonResults[i].forumId+'n'+jsonResults[i].titleId+'"><tr>';
		txt+='<td class="titleW ">'+jsonResults[i].titleName+'</td>'
		txt+='<td rowspan="2" class="articleW">'+jsonResults[i].content+'</td></tr><tr>';
		txt+='<td class="otherL"><div>'+jsonResults[i].clickNum+'</div>'
		txt+='<div>'+jsonResults[i].createTime+'</div></td></tr></table>'		
		}
	$("#resultDiv").html(txt);
*/
	$(document).on('click', '.tableMargin', function() {
		let toForum = (this.id).split("n")[1];
		let toTitle = (this.id).split("n")[2];
		window.location = "/GameBase/forum_test/"+toForum+"/"+toTitle;
	})
	</script>
</body>
</html>