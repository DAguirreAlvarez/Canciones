<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
<title>Register | Log In</title>
</head>
<body>
	<div class="container">
		<div class="row mt-4">
			<div class="col-5 bg-light border offset-1">
				<h2>Register!</h2>
				<div>
					<form:errors path="user.*" class="text-danger"/>
				</div>
				<form:form method="post" action="/register" modelAttribute="user">
					<div>
						<form:label path="name">Name:</form:label>
						<form:input path="name" class="form-control"/>
					</div>
					<div>
						<form:label path="email">Email:</form:label>
						<form:input path="email" class="form-control"/>
					</div>
					
					<div>
						<form:label path="password">Password:</form:label>
						<form:input path="password" class="form-control" type="password"/>
					</div>
					<div>
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:input path="passwordConfirmation" class="form-control" type="password"/>
					</div>
					<form:button class="btn btn-secondary mt-4">Register</form:button>
					
					
				</form:form>
				<p class="text-success">
					<c:out value="${successRegister }"></c:out>
				</p>
			</div>
			<div class="col-4 bg-light border offset-1 h-50 pb-3">
				<h2>Login</h2>
				<div>
					<form:errors path="login.*" class="text-danger" />
				</div>
				<form:form method="POST" action="/login" modelAttribute="login">
					<div>
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" class="form-control" />
					</div>
					<div>
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control" />
					</div>
					<button type="submit" class="btn btn-success mt-4">Login</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>