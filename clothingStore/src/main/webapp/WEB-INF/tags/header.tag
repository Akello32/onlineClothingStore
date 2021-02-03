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
		<span>
			<c:url value="/img/logo.png" var="logo"/>
			<img id="logo" src="${logo}">
		</span>
	</div>
	<div class="header-notFixed">
		<nav>
			<p>
				<c:url value="/catalog.jsp" var="jspUrl" />
				<a href='${jspUrl}'>О НАС</a> <a href="">КАТАЛОГ</a>
				 <a>КОРЗИНА</a> <a>ПРОФИЛЬ</a>
			</p>
		</nav>
	</div>
</div>
<div class="header-popUp">
	<p>${name}</p>
</div>