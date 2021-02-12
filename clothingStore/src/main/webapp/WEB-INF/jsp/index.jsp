<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/about.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="page">
		<c:set var="name" value="О НАС" />
		<u:header name="${name}" />
		<c:url value="/img/people.jpeg" var="img1" />
		<c:url value="/img/jeans.jpg" var="img2" />
		<div id="image">
			<img id="img1" src="${img1}"><img src="${img2}">
		</div>
		<div id="about">
			<p>STYLE — это магазин лайфстайл одежды в центре
				Минска. Более более 1000 наименований удобной и максимально комфортной одежды для катания на
				скейте, для активного отдыха, которая подчеркивает современный и стильный образ.</p>
		</div>
		<u:footer />
	</div>
</body>
</html>