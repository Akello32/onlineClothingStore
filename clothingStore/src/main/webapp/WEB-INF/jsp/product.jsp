<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/product.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="page">
		<u:header name="${product.brand.name} ${product.name}" />

		<div class="place">
			<div class="imageBlock">
				<img class="productImg" alt="image"
					src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}">
			</div>

			<div class="description">
				<h1 class="productTitle">${product.type.name}
					${product.brand.name} ${product.name}</h1>
				<p class="brand">Бренд: ${product.brand.name}</p>
				<h2 class="price">${product.price}BYN</h2>
				<c:choose>
					<c:when test="${product.gender eq 'men'}">
						<p class="gender">Пол: мужской</p>
					</c:when>
					<c:otherwise>
						<p class="gender">Пол: женский</p>
					</c:otherwise>
				</c:choose>
				<div class="sizeBlock">
					<c:forEach items="${sizes}" var="size">
						<c:url value="/product.html" var="productUrl" />
						<c:choose>
							<c:when test="${size.numbers != 0}">
								<a class="sizeWrap"> <input class="checkSize"
									id="${size.identity}" required form="cartButton" type="radio"
									autocomplete="off" type="radio"
									name="sizeProduct${product.identity}" value="${size.identity}"
									form="cartButton" /> <label class="size"
									for="${size.identity}">${size.name}</label>
								</a>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
				<p class="numbers"></p>
				<button form="cartButton" value="${product.identity}" name="product"
					class="orderButton">ЗАКАЗАТЬ</button>
				<h1 class="descTitle">ОПИСАНИЕ</h1>
				<p class="descriptionGoods">${product.description}</p>
			</div>
		</div>
		<u:footer />
	</div>
	<c:url value="/js" var="javascriptUrl" />
	<script type="text/javascript" src="${javascriptUrl}/productButton.js"></script>
</body>
</html>