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
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style type="text/css" id="infomation">
.nav-item {
	position: relative;
}

.navbar-collapse ul li a.nav-link:before {
	position: absolute;
	bottom: -5px;
	left: 0;
	width: 100%;
	height: 2px;
	background: transparent;
	content: '';
	opacity: 0;
	-ms-transition: opacity 0.3s, -webkit-transform 0.3s;
	-webkit-transition: opacity 0.3s, -webkit-transform 0.3s;
	transition: opacity 0.3s, transform 0.3s;
	-ms-transform: translateY(10px);
	-webkit-transform: translateY(10px);
	transform: translateY(10px);
}

.navbar-collapse ul li:hover a.nav-link:before {
	opacity: 1;
	-ms-transform: translateY(0px);
	-webkit-transform: translateY(0px);
	transform: translateY(0px);
	bottom: 0px;
	background: #dd4343;
}

.dropdown-item:hover, .dropdown-item:focus {
	color: #ffffff;
	text-decoration: none;
	background-color: #dd4343;
}

.dropdown-menu {
	border: 0px;
}

/* General Styles*/
body {
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	height: 100vh;
}
</style>

<style type="text/css" id="mycss">
body {
	background-color: black;
	padding: 30px
}

.row {
	padding-left: 30%;
}

.mb-0 {
	color: white;
}
</style>

<style type="text/css" id="comment">
/*
    Image credits:
    uifaces.com (http://uifaces.com/authorized)
*/
#login {
	display: none;
}

.login, .logout {
	position: absolute;
	top: -3px;
	right: 0;
}

.page-header {
	position: relative;
}

.reviews {
	color: #555;
	font-weight: bold;
	margin: 10px auto 20px;
}

.notes {
	color: #999;
	font-size: 12px;
}

.media .media-object {
	max-width: 120px;
}

.media-body {
	position: relative;
}

.media-date {
	position: absolute;
	right: 25px;
	top: 25px;
}

.media-date li {
	padding: 0;
}

.media-date li:first-child:before {
	content: '';
}

.media-date li:before {
	content: '.';
	margin-left: -2px;
	margin-right: 2px;
}

.media-comment {
	margin-bottom: 20px;
}

.media-replied {
	margin: 0 0 20px 50px;
}

.media-replied .media-heading {
	padding-left: 6px;
}

.btn-circle {
	font-weight: bold;
	font-size: 12px;
	padding: 6px 15px;
	border-radius: 20px;
}

.btn-circle span {
	padding-right: 6px;
}

.embed-responsive {
	margin-bottom: 20px;
}

.tab-content {
	padding: 50px 15px;
	border: 1px solid #ddd;
	border-top: 0;
	border-bottom-right-radius: 4px;
	border-bottom-left-radius: 4px;
}

.custom-input-file {
	overflow: hidden;
	position: relative;
	width: 120px;
	height: 120px;
	background: #eee
		url('https://s3.amazonaws.com/uifaces/faces/twitter/walterstephanie/128.jpg');
	background-size: 120px;
	border-radius: 120px;
}

input[type="file"] {
	z-index: 999;
	line-height: 0;
	font-size: 0;
	position: absolute;
	opacity: 0;
	filter: alpha(opacity = 0);
	-ms-filter: "alpha(opacity=0)";
	margin: 0;
	padding: 0;
	left: 0;
}

.uploadPhoto {
	position: absolute;
	top: 25%;
	left: 25%;
	display: none;
	width: 50%;
	height: 50%;
	color: #fff;
	text-align: center;
	line-height: 60px;
	text-transform: uppercase;
	background-color: rgba(0, 0, 0, .3);
	border-radius: 50px;
	cursor: pointer;
}

.custom-input-file:hover .uploadPhoto {
	display: block;
}
</style>
</head>
<body>

	<div class="menu">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="/home">Home</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link" href="/hot">Hot</a></li>
					<li class="nav-item"><a class="nav-link" href="/follow">Follow Manga</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Category </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">Action</a> <a
								class="dropdown-item" href="#">Another action</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Something else here</a>
						</div></li>
				</ul>
			</div>
		</nav>
	</div>


	<blockquote class="blockquote text-center">
		<div class="header">
			<img alt="" src="${IMAGE}" class="img-fluid" alt="Responsive image">
			<H1 class="mb-0">${TITLE}</H1>
		</div>
	</blockquote>

	<div class="content">
		<div class="button">
			<form action="/show" method="POST" name="chap">
				<select name="chaper" class="custom-select mr-sm-2" onchange="chap.submit()">
					<c:forEach begin="1" end="3" var="i">
					<option selected="selected" value="${i}">Chaper ${i}</option>
					</c:forEach>
				</select>
			</form>
		</div>
	</div>



	<div class="comment">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1" id="logout">
				<div class="page-header">
					<h3 class="reviews">Leave your comment</h3>
				</div>
				<div class="comment-tabs">
					<ul class="nav nav-tabs" role="tablist">
						<li class="active"><a href="#comments-logout" role="tab"
							data-toggle="tab"><h4 class="reviews text-capitalize">Comments</h4></a></li>
						<li><a href="#add-comment" role="tab" data-toggle="tab"><h4
									class="reviews text-capitalize">Add comment</h4></a></li>
					</ul>
					
					<div class="tab-content">
						<div class="media-body">
							<div class="well well-lg">
								<c:forEach items="${COMMENT}" var="comment">
									<h4 class="media-heading text-uppercase reviews">${comment.name}</h4>
									<ul class="media-date text-uppercase reviews list-inline">
												<li>${comment.day}</li>
												<!-- <li class="mm">09</li>
												<li class="aaaa">2014</li> -->
											</ul>
									<p class="media-comment">${comment.comment}</p>
								</c:forEach>
							</div>
						</div>
					</div>
					
	
					<div class="tab-pane" id="add-comment">
						<form action="/add-comment" method="post" class="form-horizontal"
							id="commentForm" role="form">
							<input type="hidden" name="Id" value="${IdManga}">
							<div class="form-group" style="padding-left: 20px">
								<label for="name" class="col-sm-2 control-label">Your Name</label>
								<input style="width: 400px" type="text" name="name_comment" class="form-control">
							</div>
							<div class="form-group">
								<label for="comment" class="col-sm-2 control-label">Comment</label>
								<div class="col-sm-10">
										<input style="height: 100px" type="text" name="comment" class="form-control" id="addComment" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input class="btn btn-success btn-circle text-uppercase"
										type="submit" id="submitComment" value="SUBMIT COMMENT">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>