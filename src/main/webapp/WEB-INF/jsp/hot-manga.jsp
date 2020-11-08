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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	body{
		background-color: black;
	}
</style>
</head>
<body>
	<form action="/home" method="get">
		<button class="btn btn-danger">HOME</button>
	</form>

	<blockquote class="blockquote text-center">
		<div class="header">
			<img alt="" src="${MANGA.img}" class="img-fluid"
				alt="Responsive image">
			<H1 class="mb-0">${MANGA.title}</H1>
		</div>
	</blockquote>

	<div class="content">
		<div class="button">
			<form action="/show" method="POST">
				<select name="chaper" class="custom-select mr-sm-2">
					<c:forEach begin="1" end="${MANGA.chap}" var="i">
						<option selected="selected" value="${i}">Chaper ${i}</option>
					</c:forEach>
				</select> <input type="submit" name="Submit" value="Go To Chap"
					class="btn btn-dark">
			</form>
		</div>
	</div>

</body>
</html>