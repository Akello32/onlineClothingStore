<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url value="/css/login.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<c:url value="/login.html" var="signIn" />
			<h2 class="inactive underlineHover">
				<a class="link" href="${signIn}">Sign In</a>
			</h2>
			<h2 class="active">Sign Up</h2>
			<c:url value="/signup.html" var="loginUrl" />
			<form method="post" action="${loginUrl}">
				<input type="text" id="login" class="fadeIn second" name="login"
					placeholder="login"> <input type="password" id="password"
					class="fadeIn third" name="password" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Sign Up">
			</form>

			<div id="formFooter">
				<c:url value="/index.html" var="main"></c:url>
				<a class="underlineHover" href="${main}">На главную</a>
			</div>

		</div>
	</div>
</body>
</html>