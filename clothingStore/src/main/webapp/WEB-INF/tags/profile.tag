<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@attribute name="login" required="true" type="String"%>
<%@attribute name="role" required="true" type="String"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/profile.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="page">
		<u:header name="ПРОФИЛЬ" />
		<div class="place">
			<div class="userWindow">
				<div class="userImage">
					<c:url value="/img/user.png" var="profileImg" />
					<img alt="profile" src="${profileImg}">
					<h1 class="login">${login}</h1>
				</div>
				<h2 class="role">${role}</h2>
				<c:url value="/logout.html" var="logout" />
				<a href="${logout}" class="logOut">ВЫЙТИ</a>
			</div>
			<jsp:doBody />
		</div>
		<u:footer />
	</div>
</body>
</html>