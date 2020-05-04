//index
//改變幻燈片停留時間
$('.carousel').carousel({
	interval : 3000
})

$
		.ajax({
			url : '<c:url value="/searchFreq"/>',
			type : "GET",
			dataType : 'json',
			success : function(jsonResults) {

				var txt = "";
				for (i = 0; i < 5; i++) {
					if (i % 5 == 0) {
						txt += "<tr>"
					}
					txt += "<td><a href='/GameBase/tagSearch?looking=forProduct&keyword="
							+ jsonResults[i].productName
							+ "'><img class='resultImg' src='"
							+ jsonResults[i].productImg + "'>";
					txt += "<div>" + jsonResults[i].productName + "</div>";
					txt += "<div>" + jsonResults[i].productPrice + "</div></a>";
				}
				$("#resultsHotTable").append(txt);
			}
		});

/*
 * $(window).scroll(function(){ var navH = $("#topBar").offset().top; var scroH =
 * $(this).scrollTop(); if(scroH>=navH){ $("#topBar").addClass("topBarFixed")
 * $("#topBar").removeClass("topBar")
 * 
 * }else{ $("#topBar").addClass("topBar")
 * $("#topBar").removeClass("topBarFixed") console.log("ff"); } })
 */

$(document).ready(function() {
	console.log("page ready");
	$.ajax({
		url : "GameBase/getip",
		type : "POST",
		contentType : "application/json",
		success : function(response) {
			console.log(response)
		}
	})
})