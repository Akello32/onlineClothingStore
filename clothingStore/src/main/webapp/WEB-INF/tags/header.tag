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
			<c:url value="https://translate.yandex.by" var="loginUrl" />
			<a class="logIn" href="${loginUrl}">Log in</a><a class="signIn">Sign in</a>
		</div>
	</div>
	<div class="header-notFixed">
		<nav>
			<p>
				<c:url value="/index.html" var="aboutUrl" />
				<a class="section" href='${aboutUrl}'>О НАС</a>
				<c:url value="/catalog.html" var="catalogUrl" />
				<a class="section" href="${catalogUrl}">КАТАЛОГ</a> <a
					class="section">КОРЗИНА</a> <a class="section">ПРОФИЛЬ</a>
			</p>
		</nav>
	</div>
</div>
<div class="header-popUp">
	<p>${name}</p>
</div>