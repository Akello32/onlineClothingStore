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
							<c:url value="/category.html" var="categoryUrl" />
							<li class="punct">
								<form method="post" action="${categoryUrl}">
									<input class="categorySubmit" type="submit" name="category"
										value="${type.name}" />
								</form>
							</li>
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
							<li><input class="check" id="S" type="radio" name="size"
								value="S" form="showParam" /><label class="checkLable" for="S">S</label></li>
							<li><input class="check" id="M" type="radio" name="size"
								value="M" form="showParam" /><label class="checkLable" for="M">M</label></li>
							<li><input class="check" id="L" type="radio" name="size"
								value="L" form="showParam" /><label class="checkLable" for="L">L</label></li>
							<li><input class="check" id="XL" type="radio" name="size"
								value="XL" form="showParam" /><label class="checkLable"
								for="XL">XL</label></li>
							<li><input class="check" id="XXL" type="radio" name="size"
								value="XXL" form="showParam" /><label class="checkLable"
								for="XXL">XXL</label></li>
						</ul>
					</div>
					<div class="category">
						<h2 class="categoryName">БРЕНД</h2>
						<c:forEach items="${brands}" var="brand">
							<ul class="items">
								<li><input class="check" id="${brand.name}" type="radio"
									name="brand" value="${brand.identity}" form="showParam" /><label
									class="checkLable" for="${brand.name}">${brand.name}</label></li>
							</ul>
						</c:forEach>
					</div>
					<div class="category">
						<h2 class="categoryName">ЦВЕТ</h2>
						<ul class="items">
							<li><input class="check" id="Green" type="radio"
								name="color" value="Green" form="showParam" /><label
								class="checkLable" for="Green">ЗЕЛЕНЫЙ</label></li>
							<li><input class="check" id="Yellow" type="radio"
								name="color" value="Yellow" form="showParam" /><label
								class="checkLable" for="Yellow">ЖЕЛТЫЙ</label></li>
							<li><input class="check" id="Red" type="radio" name="color"
								value="Red" form="showParam" /><label class="checkLable"
								for="Red">КРАСНЫЙ</label></li>
							<li><input class="check" id="Blue" type="radio" name="color"
								value="Blue" form="showParam" /><label class="checkLable"
								for="Blue">СИНИЙ</label></li>
							<li><input class="check" id="Orange" type="radio"
								name="color" value="Orange" form="showParam" /><label
								class="checkLable" for="Orange">ОРАНЖЕВЫЙ</label></li>
							<li><input class="check" id="Gray" type="radio" name="color"
								value="Gray" form="showParam" /><label class="checkLable"
								for="Gray">СЕРЫЙ</label></li>
							<li><input class="check" id="Black" type="radio"
								name="color" value="Black" form="showParam" /><label
								class="checkLable" for="Black">ЧЕРНЫЙ</label></li>
							<li><input class="check" id="Purple" type="radio"
								name="color" value="Purple" form="showParam" /><label
								class="checkLable" for="Purple">ФИОЛЕТОВЫЙ</label></li>
							<li><input class="check" id="Brown" type="radio"
								name="color" value="Brown" form="showParam" /><label
								class="checkLable" for="Brown">КОРИЧНЕВЫЙ</label></li>
							<li><input class="check" id="White" type="radio"
								name="color" value="White" form="showParam" /><label
								class="checkLable" for="White">БЕЛЫЙ</label></li>
							<li><input class="check" id="Beige" type="radio"
								name="color" value="Beige" form="showParam" /><label
								class="checkLable" for="Beige">БЕЖЕВЫЙ</label></li>
							<li><input class="check" id="Pink" type="radio" name="color"
								value="Pink" form="showParam" /><label class="checkLable"
								for="Pink">РОЗОВЫЙ</label></li>
						</ul>
					</div>
					<div class="category">
						<h2 class="categoryName">ПОЛ</h2>
						<ul class="items">
							<li><input class="check" id="men" type="radio" name="gender"
								value="men" form="showParam" /><label class="checkLable"
								for="men">МУЖСКОЙ</label></li>
							<li><input class="check" id="woman" type="radio"
								name="gender" value="women" form="showParam" /><label
								class="checkLable" for="woman">ЖЕНСКИЙ</label></li>
						</ul>

					</div>
					<c:url value="/showByParam.html" var="paramUrl" />
					<form id="showParam" action="${paramUrl}" method="post">
						<button class="showButton">ПОКАЗАТЬ</button>
					</form>
				</div>
			</aside>

			<div class="photos">
				<c:forEach items="${clothes}" var="product">
					<div class="productCard">
						<c:url value="/product.html" var="productLink" />
						<form action="${productLink}" method="post">
							<button class="productButton" name="product"
								value="${product.identity}">
								<img class="productImg"
									src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}"
									alt="product" />
								<div class="productContent">
									<div class="productName">${product.brand.name}</div>
									<div class="productName">${product.name}</div>
									<div class="productPrice">${product.price}BYN</div>
								</div>
							</button>
						</form>
						<button class="orderButton">ЗАКАЗАТЬ</button>
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