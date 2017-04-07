function getWatchList(){

	$.ajax({
		url: "/getWatchList.do",
		type: "GET",
		dataType: "JSON",
		success: function(response){
			alert(response);
		
		},
		error: function(){
			
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}

function getSymbolInfo(){

	$.ajax({
		url: "StockTracker/getSymbolInfo.do",
		type: "POST",
		data: {
			"symbol" : "ACAD"
		},
		success: function(response){
			alert(response);
		
		},
		error: function(){
			
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}