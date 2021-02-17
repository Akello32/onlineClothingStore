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
							<td><p>${product.size}</p></td>
							<td><p>${product.price}</td>
							<c:set var="number" value="${product.numbers}" scope="page" />
							<td><input type="number" min="1" max="${number}" value="1"
								class="numberInput" /></td>
							<td><p class="finalPrice">${product.price}</td>
							<c:url value="/buyer/deleteProduct?delete=${product.identity}"/>
							<td class="deleteButton"><a href="">&#9746;</a></td>
						</tr>
					</c:forEach>
				</table>
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