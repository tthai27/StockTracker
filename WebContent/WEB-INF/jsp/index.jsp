<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<c:set var="context" value="${pageContext.request.contextPath}" />
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>My Stock Portfolio</title>

    <!-- Bootstrap core CSS -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom CSS -->
    <link href="${context}/resources/css/stockportfolio.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--     <link href="${context}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> --%>

    <!-- Custom styles for this template -->
    <link href="${context}/resources/css/jumbotron-narrow.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	

  </head>

  <body>

    <div class="container">
      <div class="header clearfix">
        <h3 class="text-muted">My Stock Portfolio</h3>
      </div>
	  
	<div class="container">
		<div class="row">    
			<div class="col-xs-8 col-xs-offset-2">
				<div class="input-group">
					<input type="hidden" name="search_param" value="all" id="search_param">         
					<input type="text" class="form-control" name="x" id="searchSymbolId" placeholder="Search Symbol...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="getSymbolInfo()"><span class="glyphicon glyphicon-search"></span></button>
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" id="resultDiv" style="display:none">     
	  <table class="table">
		<thead>
		  <tr>
			<th id="companyNameId"></th>
			<th id="symbolId"></th>
			<th id="priceId"></th>
			<th><input type="hidden" name="shares" value="all" id="sharesIdhidden"></th>
			<th><input type="text" class="form-control" name="x" id="sharesId" value="0" placeholder="Enter Shares"></th>
			<th><span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="addWatchList()"><span class="glyphicon glyphicon-plus"></span></button>
				</span></th>
		  </tr>
		  <br>
		</thead>
	</table>
	</div>

	
	<div class="container">
	  <h2>Watch List</h2>                                                                                      
	  <div class="table-responsive">          
	  <table class="table">
		<thead>
		  <tr>
			<th data-field="fruit" data-sortable="true">Symbol</th>
			<th data-sortable="true">Company Name</th>
			<th>Current Price</th>
			<th>Change</th>
			<th># of Shares Owned</th>
			<th>Market Value</th>
			<th>Remove</th>
		  </tr>
		</thead>
		<tbody id="watchListTableDiv">
	
<!-- 		  <tr> -->
<!-- 			<td>ACAD</td> -->
<!-- 			<td>Acadia Pharmaceuticals</td> -->
<!-- 			<td>$34.00</td> -->
<!-- 			<td>0</td> -->
<!-- 			<td></td> -->
<!-- 			<td><span id='close' onclick='this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode); return false;' class="glyphicon glyphicon-remove"></span></td> -->
<!-- 		  </tr> -->
<!-- 		  <tr> -->
<!-- 			<td>AMZN</td> -->
<!-- 			<td>Amazon.com</td> -->
<!-- 			<td>$910.00</td> -->
<!-- 			<td>0</td> -->
<!-- 			<td></td> -->
<!-- 			<td><span id='close' onclick='this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode); return false;' class="glyphicon glyphicon-remove"></span></td> -->
<!-- 		  </tr> -->
<!-- 		  <tr> -->
<!-- 			<td>AUPH</td> -->
<!-- 			<td>Aurinia Pharmaceuticals</td> -->
<!-- 			<td>$6.75</td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td><span id='close' onclick='this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode); return false;' class="glyphicon glyphicon-remove"></span></td> -->
<!-- 		  </tr> -->
		</tbody>
	  </table>
	  </div>
	</div>
	
	<div class="row">

      <footer class="footer">
        <p>&copy; Tony Thai</p>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!--     <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${context}/resources/js/stockScript.js"></script>		
<script type="text/javascript" >
		$(document).ready(function(){
			getWatchList();
			setInterval(function(){ 
				getWatchList(); 
			}, 5000);
			
		});

</script>
  </body>
</html>
