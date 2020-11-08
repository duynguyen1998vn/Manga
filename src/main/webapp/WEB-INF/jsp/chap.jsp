<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	background-color: black;
}

.header {
	overflow:
}

nav {
	margin-left: 35%;
}

option {
	height: 30px;
}
</style>
</head>
<body>
	<form action="/home" method="get">
		<button class="btn btn-danger">HOME</button>
	</form>
	<nav class="nav nav-tabs">
		<div class="button">
			<form action="/back" method="POST">
				<input type="submit" value="Back Chap" class="btn btn-success"
					class="nav-link"> <input type="hidden" value="${chap}"
					name="chaper">
			</form>
		</div>


		<div class="button">
			<form action="/show" method="POST">
				<select name="chaper">
					<c:forEach begin="1" end="${chap}" var="i">
						<option selected="selected" value="${i}">Chaper ${i}</option>
					</c:forEach>
				</select> <input type="submit" name="Submit" value="Go To Chap">
			</form>
		</div>

		<div class="button">
			<form action="/next" method="POST">
				<input type="submit" value="Next Chap" class="btn btn-primary"
					class="nav-link"> <input type="hidden" value="${chap}"
					name="chaper">
			</form>
		</div>
	</nav>
	<div class="content">
		<c:forEach items="${Manga}" var="manga">
			<div class="text-center">
				<img
					alt="https://i.pinimg.com/originals/2d/fe/a1/2dfea1867eb8efae0b81f621135fa171.jpg"
					src="${manga}" class="img-thumbnail" />
			</div>
		</c:forEach>
	</div>

	<nav class="nav nav-tabs">
		<div class="button">
			<form action="/back" method="POST">
				<input type="submit" value="Back Chap" class="btn btn-success"
					class="nav-link"> <input type="hidden" value="${chap}"
					name="chaper">
			</form>
		</div>
		<div class="button">
			<form action="/show" method="POST">
				<select name="chaper">
					<c:forEach begin="1" end="${chap}" var="i">
						<option selected="selected" value="${i}">Chaper ${i}</option>
					</c:forEach>
				</select> <input type="submit" name="Submit" value="Go To Chap">
			</form>
		</div>

		<div class="button">
			<form action="/next" method="POST">
				<input type="submit" value="Next Chap" class="btn btn-primary"
					class="nav-link"> <input type="hidden" value="${chap}"
					name="chaper">
			</form>
		</div>
	</nav>
	<form action="/home" method="get">
		<button class="btn btn-danger">HOME</button>
	</form>
</body>
</html>