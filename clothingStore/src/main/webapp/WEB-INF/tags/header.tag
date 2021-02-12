<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="name" required="true" type="String"%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<c:url value="/css/header.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<div class="header">
	<div class="header-fixed">
		<c:url value="/img/logo.png" var="logo" />
		<img id="logo" src="${logo}" />
		<div class="registration">
			<c:url value="/login.html" var="loginUrl" />
			<a class="logIn" href="${loginUrl}">Log in</a>
			<c:url value="/signup.html" var="signUrl" />
			<a class="signIn" href="${signUrl}">Sign in</a>
		</div>
	</div>
	<div class="header-notFixed">
		<nav class="navBar">
			<c:url value="/index.html" var="aboutUrl" />
			<a class="section" href='${aboutUrl}'>О НАС</a>
			<c:url value="/catalog.html" var="catalogUrl" />
			<a class="section" href="${catalogUrl}">КАТАЛОГ</a>
			<c:forEach items="${menu}" var="menu">
				<c:url value="${menu.url}" var="itemUrl" />
				<c:choose>
					<c:when test="${menu.name eq 'КОРЗИНА'}">
						<form action="${itemUrl}" method="post" id="cartButton">
							<button class="sectionButton">${menu.name}</button>
						</form>
					</c:when>
					<c:otherwise>
						<a class="section" href="${itemUrl}">${menu.name}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</nav>
	</div>
</div>
<div class="header-popUp">
	<p>${name}</p>
</div>