<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/order.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<div class="page">
		<u:header name="КОРЗИНА" />
		<div class="place">
			<div class="cart">
				<div class="titles">
					<h2 class="title">Товар</h2>
					<h2 class="title">Размер</h2>
					<h2 class="title">Цена</h2>
					<h2 class="title">Кол-во</h2>
					<h2 class="title">Сумма</h2>
				</div>
				<div class="productInf">
					<c:forEach items="${products}" var="product">
						<img alt="image" class="productImg"
							src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}">
					</c:forEach>
				</div>
			</div>
			<aside class="totalAmount">
				<div class="blockAmount">
					<h2 class="amount">Товаров на сумму</h2>
					<p class="price">450</p>
					<h2 class="toPaid">К оплате</h2>
					<p class="price">450</p>
				</div>
			</aside>
		</div>
		<u:footer />
	</div>
</body>
</html>