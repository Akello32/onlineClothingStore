<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothesStore</title>
<c:url value="/css/header.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="header">
		<div class="header-fixed">
			<span>Logo Here</span>
		</div>
		<div class="header-notFixed">
			<nav>
				<p>
					<a>О НАС</a> <a>КАТАЛОГ</a> <a>КОРЗИНА</a> <a>ПРОФИЛЬ</a>
				</p>
			</nav>
		</div>
		<div class="header-popUp">
			<a>О НАС</a>
		</div>
	</div>
	<!-- 	
	
	
	<div id="logoArea">
		<p>LOGO</p>
	</div>
		<header id="header">
			<nav>
				<p><a>О НАС</a></p>
				<p><a>КАТАЛОГ</a></p>
				<p><a>КОРЗИНА</a></p>
				<p><a>ПРОФИЛЬ</a></p>
			</nav>
		</header> -->
</body>
</html>