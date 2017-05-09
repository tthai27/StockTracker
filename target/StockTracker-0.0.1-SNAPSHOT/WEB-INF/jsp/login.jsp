<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<title>Login</title>

    <link rel="icon" href="../../favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom CSS -->
    <link href="${context}/resources/css/stockportfolio.css" rel="stylesheet">

    <link href="${context}/resources/css/jumbotron-narrow.css" rel="stylesheet">
</head>
<body>

 <div class="container">
		 <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		      <font color="red">
		        Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		      </font>
		</c:if>
		<form name="loginForm" action="authenticateUser" method="post">
			User-name<input type="text" name="username" class="form-control"/><br />
			Password <input type="password" name="password" class="form-control"/> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
<!-- 			<input type="submit" value="Submit" > -->
			<a href="#" onclick="document.forms['loginForm'].submit(); return false;" class="btn btn-primary btn-lg" >Login</a>
		</form>
	</div>
</body>
</html>



