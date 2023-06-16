<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
<title>Create a course</title>
</head>
<body>
	<div class="container">
		<h1>Start a new Song!</h1>
		<div>
			<form:errors path="song.*" class="text-danger" />
		</div>
		<form:form action="/songs/new" method="post" modelAttribute="song">
			<p class="row w-50 mt-4 mb-2">
				<form:label path="title" class="col-5">Song Title: </form:label>
				<form:input path="title" class="col-5"/>
			</p>
			<p class="row w-50 mt-3">
				<form:label path="genre" class="col-5">Genre: </form:label>
				<form:input path="genre" class="col-5"/>
			</p>
			<p class="row w-50 mt-3">
				<form:label path="lyrics" class="col-5">Add your Lyrics: </form:label>
				<form:textarea path="lyrics" class="col-5"/>
			</p>
			<form:button class="btn btn-secondary mt-3">Submit</form:button>
		</form:form>
		
		<a href="/home">Cancel</a>
	</div>
</body>
</html>