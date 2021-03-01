<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/profileUser.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<u:profile login="${authorizedUser.login}" role="ПОКУПАТЕЛЬ">
		<div class="workplace">
			<c:forEach items="${orderedClothes}" var="product">
				<div class="productCard">
					<c:url value="/product.html?product=${product.clothes.identity}"
						var="productLink" />
					<a href="${productLink}" class="productLink"><img
						class="productImg"
						src="${pageContext.request.contextPath}/img/BDimg/${product.clothes.imgPath}"
						alt="product" />
						<div class="productContent">
							<div class="productName">${product.clothes.brand.name}</div>
							<div class="productName">${product.clothes.name}</div>
							<div class="productPrice">${product.clothes.price}BYN</div>
						</div>
						<ul class="orderInfo">
							<li class="orderContent">Заказ № ${product.order.identity}</li>
							<li class="orderContent">${product.size.name}</li>
							<li class="orderContent">Сумма заказа:
								${product.order.price}</li>
						</ul> </a>
				</div>
			</c:forEach>
		</div>
		<aside class="userActions">
			<ul>
				<li><c:url value="/img/clipboard.png" var="listImg" /><img
					alt="list" src="${listImg}"> <c:url value="" var="listImg" />
					<c:url value="/buyer/purchase.html" var="cartLink" /> <a
					href="${cartLink}" class="action">ПОКУПКИ</a>
				<li><c:url value="/img/shopping-cart.png" var="cartImg" /><img
					alt="list" src="${cartImg}"> <c:url value="/buyer/order.html"
						var="cartLink" /> <a class="action" href="${cartLink}">ТЕКУЩИЙ
						ЗАКАЗ</a>
			</ul>
		</aside>
	</u:profile>
</body>
</html>