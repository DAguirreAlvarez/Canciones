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
<title>Project Manager Dashboard</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="col-8">
				Hello,
				<c:out value="${user.getName() }"></c:out>
			</h1>
			<p class="text-end col-3 h3">
				<a href="/logout">Logout</a>
			</p>
		</div>

		<div>
			<h2>All Song Labs</h2>
			<div class="row">
				<h3 class="col-5">Song</h3>
				<h3 class="col-5"># of Collaborations / Collaborators</h3>
				<hr>
			</div>
			<c:forEach items="${canciones }" var="song">
				<div class="row">
					<div class="col-5">
						<a href="/songs/${song.getId() }" class="mb-0 h3"><c:out value="${song.getTitle() }"></c:out></a>
						<p>
							<c:out value="${song.getGenre() }"></c:out>
						</p>
					</div>
					<p class="h3 col-5"><c:out value="${song.getColaboraciones() }"></c:out> / 
					<c:out value="${song.getColaboradores().size() }"></c:out></p>
				</div>
			</c:forEach>
		</div>

		<a href="/songs/new" class="btn btn-secondary mt-5">&lt; new song</a>





	</div>
</body>
</html>