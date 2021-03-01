<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<c:url value="/css/login.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<h2 class="active">
				Sign In
			</h2>
			<c:url value="/signup.html" var="signIn" />
			<h2 class="inactive underlineHover">
				<a class="link" href="${signIn}">Sign Up</a>
			</h2>

			<c:url value="/login.html" var="loginUrl" />
			<form method="post" action="${loginUrl}">
				<input type="text" id="login" class="fadeIn second" name="login"
					placeholder="login"> <input type="text" id="password"
					class="fadeIn third" name="password" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Log in">
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				 <c:url value="/index.html" var="main"></c:url>
				<a class="underlineHover" href="${main}">На главную</a>
			</div>

		</div>
	</div>
</body>
</html>