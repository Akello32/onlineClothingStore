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
					<c:forEach items="${clothesSameSize}" var="clothes">
						<c:url value="/product.html" var="productUrl" />
						<a class="sizeWrap"> <input class="checkSize" id="${clothes.size}" type="radio"
							name="product" value="${clothes.identity}" /> <label
							class="size" for="${clothes.size}">${clothes.size}</label>
						</a>
					</c:forEach>
				</div>
				<c:choose>
					<c:when test="${not empty product.numbers}">
						<p class="numbers">Есть в наличии</p>
						<button class="orderButton">ЗАКАЗАТЬ</button>
					</c:when>
					<c:otherwise>
						<p class="numbers">Товар закончился</p>
						<button class="">ЗАКАЗАТЬ</button>
					</c:otherwise>
				</c:choose>
				<h1 class="descTitle">ОПИСАНИЕ</h1>
				<p class="descriptionGoods">${product.description}</p>
			</div>
		</div>
		<u:footer />
	</div>
</body>
</html>