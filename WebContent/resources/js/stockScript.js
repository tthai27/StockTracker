function getWatchList(){
	
	$.ajax({
		url: "StockTracker/getWatchList.do",
		type: "GET",
		dataType: "json",
		success: function(response){
			var trHTML = "";
			$.each(response, function(i, value) {
//				console.log(value);
//				console.log(value.companyName);
//				console.log(value.symbol);
//				console.log(value.lastTradePrice);
			
				//
				
				var companyName = value.companyName
				var symbol = value.symbol;
				var lastTradePrice = value.lastTradePrice;
				var shares = value.sharesOwned;
				var marketValue = value.marketValue;
				var change = value.change;
				if(companyName != ''){
						trHTML+=' <tr> ';
						trHTML+='	<td>'+symbol+'</td>';
						trHTML+='	<td>'+companyName+'</td>';
						trHTML+='	<td>'+lastTradePrice+'</td>';
						if(change == "1"){
							trHTML+='<td><span style="color:green" class="glyphicon glyphicon-arrow-up"></span></td>';
						}else if(change == "-1"){
							trHTML+='<td><span style="color:red"class="glyphicon glyphicon-arrow-down"></span></td>';
						}else{
							trHTML+='<td><span class="glyphicon glyphicon-minus"></span></td>';
						}
						trHTML+='	<td>'+shares+'</td>';
						trHTML+='	<td>$'+marketValue+'</td>';
						trHTML+='	<td><span id="close" onclick="removeSymbol(\''+symbol+'\');this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode); return false;" style="color:red" class="glyphicon glyphicon-remove" ></span></td>';
						trHTML+='  </tr>';
				}
		     });
			$("#watchListTableDiv").html("");
			$("#watchListTableDiv").append(trHTML);
		},
		error: function(){
			
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}

function getSymbolInfo(){
	$("#resultDiv").hide();
	var symbol = $("#searchSymbolId").val();
	if(symbol == ''){
		return;
	}
	$.ajax({
		url: "StockTracker/getSymbolInfo.do",
		type: "POST",
		dataType: 'json',
		data: {
			"symbol" : symbol.toUpperCase()
		},
		success: function(data){
			
			$("#companyNameId").html(data.companyName);
			$("#symbolId").html(data.symbol);
			$("#priceId").html(data.lastTradePrice);
			$("#resultDiv").show();
		},
		error: function(){
			$("#resultDiv").show();
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}

function removeSymbol(symbol){
	
	$.ajax({
		url: "StockTracker/removeWatchList.do",
		type: "POST",
		dataType: 'text',
		data: {
			"symbol" : symbol.toUpperCase()
		},
		success: function(data){
			alert("removed "+symbol);
		},
		error: function(){
			
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}

function addWatchList(){
	$("#tickerErrorMsg").hide();
	var symbol = $("#searchSymbolId").val();
	if(symbol == ''){
		$("#tickerErrorMsg").show();
		return;
	}
	var shares = $("#sharesId").val();
	if(isNaN(shares) || shares == ""){
		shares = 0;
	}
	$.ajax({
		url: "StockTracker/addWatchList.do",
		type: "POST",
		dataType: 'text',
		data: {
			"symbol" : symbol.toUpperCase(),
			"shares" : shares
		},
		success: function(data){
			$("#searchSymbolId").val("");
			$("#sharesId").val("");
			$("#resultDiv").hide();
			getWatchList();
		},
		error: function(){
			
			  alert("We are sorry. Error ocurred! Please try it later.");
		}
	});
}
