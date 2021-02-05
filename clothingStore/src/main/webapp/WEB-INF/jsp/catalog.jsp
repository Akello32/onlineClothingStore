<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/catalog.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="page">
		<u:header name="КАТАЛОГ" />
		<section class="place">
			<aside id="side">
				<header class="title">
					<h1>КАТЕГОРИИ</h1>
				</header>
				<div class="catalog">
					<c:forEach items="${types}" var="type">
						<ul class="catalogParametrs">
							<li class="punct">${type.name}</li>
						</ul>
					</c:forEach>
				</div>
				<header class="title">
					<h1>ПАРАМЕТРЫ</h1>
				</header>
				<div class="parametrs">
					<div class="category">
						<h2 class="categoryName">РАЗМЕР</h2>
						<ul class="items">
							<li>S</li>
							<li>M</li>
							<li>L</li>
							<li>XL</li>
							<li>XXL</li>
						</ul>
					</div>
					<div class="category">
						<h2 class="categoryName">БРЕНД</h2>
						<c:forEach items="${brands}" var="brand">
							<ul class="items">
								<li>${brand.name}</li>
							</ul>
						</c:forEach>
					</div>
					<div class="category">
						<h2 class="categoryName">ЦВЕТ</h2>
						<ul class="items">
							<li>ЗЕЛЕНЫЙ</li>
							<li>ЖЕЛТЫЙ</li>
							<li>КРАСНЫЙ</li>
							<li>СИНИЙ</li>
							<li>ГОЛУБОЙ</li>
							<li>ОРАНЖЕВЫЙ</li>
							<li>СЕРЫЙ</li>
							<li>ЧЕРНЫЙ</li>
							<li>ФИОЛЕТОВЫЙ</li>
							<li>КОРИЧНЕВЫЙ</li>
							<li>БЕЛЫЙ</li>
							<li>БЕЖЕВЫЙ</li>
							<li>РОЗОВЫЙ</li>
						</ul>
					</div>
					<button class="showButton">ПОКАЗАТЬ</button>
				</div>
			</aside>

			<div class="photos">
				<c:forEach items="${clothes}" var="product">
					<div class="productCard">
						<img class="productImg"
							src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}"
							alt="product" />
						<div class="productContent">
							<div class="productName">${product.brand.name}</div>
							<div class="productName">${product.type.name}</div>
							<div class="productPrice">${product.price}BYN</div>
						</div>
						<button class="order">ЗАКАЗАТЬ</button>
						<a class="productLink"></a>
					</div>
				</c:forEach>
			</div>
		</section>

		<u:footer />
	</div>

	<c:url value="/js" var="javascriptUrl" />
	<script type="text/javascript"
		src="${javascriptUrl}/categoriesHandler.js"></script>
</body>
</html>