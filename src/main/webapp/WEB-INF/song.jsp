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
<title>Create a Task</title>
</head>
<body>
	<div class="container">
		<div>
			<h1 class="mt-4"><strong><c:out value="${cancion.getTitle() }"></c:out></strong> </h1>
			<h2 class="h1">(Started by <c:out value="${cancion.getStarter().getName() }"></c:out>)</h2>
			<h2 class="mt-3">Genre: <c:out value="${cancion.getGenre() }"></c:out></h2>
		</div>
		<h2 class="mt-4">Lyrics:</h2>
		<div class="w-50">
			<p class="w-75 m-4"><c:out value="${cancion.getLyrics() }"></c:out> </p>	
		</div>
		<a href="/songs/${cancion.getId() }/edit" class="btn btn-secondary mt-3">&lt; Contribute</a>
		<hr>
		<h2>Collaborators</h2>
		<c:forEach items="${cancion.getColaboradores() }" var="colaboradores">
			<p class="mb-1"><c:out value="${colaboradores.getName() }"></c:out> </p>
		</c:forEach>
	</div>
</body>
</html>