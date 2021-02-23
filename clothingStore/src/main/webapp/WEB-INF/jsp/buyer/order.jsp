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
				<c:choose>
					<c:when test="${empty products}">
						<h1 class="emptyCart">КОРЗИНА ПУСТА:(</h1>
					</c:when>
					<c:otherwise>
						<table>
							<tr class="titleName">
								<th>Товар</th>
								<th>Размер</th>
								<th>Цена</th>
								<th>Кол-во</th>
								<th>Сумма</th>
								<th></th>
							</tr>
							<c:forEach items="${products}" var="product">
								<tr class="productCard">
									<td class="first"><img alt="image" class="productImg"
										src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}">
										<div class="contentName">
											<p>${product.name}</p>
											<p class="brandF">${product.brand.name}</p>
										</div></td>
									<td><p>${product.sizes[0].name}</p></td>
									<td><p>${product.price}</td>
									<td><input type="number" min="1"
										max="${product.sizes[0].numbers}" value="1"
										class="numberInput" /></td>
									<td><p class="finalPrice">${product.price}</td>
									<c:url
										value="/buyer/deleteProduct.html?productId=${product.identity}&sizeId=${product.sizes[0].identity}"      
										var="deleteUrl" />
									<td><a href="${deleteUrl}" class="deleteButton">&#9746;</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<aside class="totalAmount">
				<div class="blockAmount">
					<h2 class="amount">Товаров на сумму</h2>
					<p class="totalPrice"></p>
					<h2 class="toPaid">К оплате</h2>
					<p class="totalPrice"></p>
					<c:url value="" var="paramUrl" />
					<form action="${paramUrl}" method="post" class="formButton">
						<button class="payButton">ОПЛАТИТЬ</button>
					</form>
				</div>
			</aside>
		</div>
		<u:footer />
	</div>
	<c:url value="/js" var="javascriptUrl" />
	<script type="text/javascript"
		src="${javascriptUrl}/productCardListener.js"></script>
</body>
</html>