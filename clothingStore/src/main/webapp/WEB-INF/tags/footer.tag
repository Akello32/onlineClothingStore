<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<c:url value="/css/footer.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<footer>
	<c:url value="/img/logo-white.png" var="logo" />
	<img id="logo1" src="${logo}">
	<div id="contacts">
		<p>КОНТАКТЫ</p>
		<p>MAIL: matmux23@gmail.com</p>
		<p>TG: matvei</p>
		<p>8 (029)123-45-67</p>
	</div>
</footer>