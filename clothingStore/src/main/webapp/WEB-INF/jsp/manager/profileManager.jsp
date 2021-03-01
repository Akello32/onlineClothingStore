<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
<c:url value="/css/profileManager.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
	<u:profile login="${authorizedUser.login}" role="МЕНЕДЖЕР">
		<div class="workplace">
			<table>
				<tr class="titleName">
					<th>Товар</th>
					<th>Размер</th>
					<th>Цена</th>
					<th>Кол-во</th>
				</tr>
				<%-- 			<c:forEach items="${products}" var="product">
 --%>
				<%-- <input type="hidden" name="orderedClothes"
					value="${product.identity}" form="orderButton" />
				<input type="hidden" name="sizeOrderedClothes"
					value="${product.sizes[0].identity}" form="orderButton" /> --%>
				<tr class="productCard">
					<td class="first">
						<%-- <img alt="image" class="productImg"
						src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}"> --%>
						<h3>Изображение</h3>
						<div class="contentName">
							<p>название товара</p>
							<p class="brandF">бренд</p>
						</div>
					</td>
					<td><p>размер</p></td>
					<td><p>цена</td>
					<td><input type="number" min="1" name="numbersClothes" max=""
						value="1" class="numberInput" form="orderButton" /></td>
				</tr>
				<%-- 			</c:forEach>
 --%>
			</table>
		</div>
		<aside class="userActions">
			<ul>
				<li><c:url value="/buyer/purchase.html" var="cartLink" /> <a
					href="${cartLink}" class="action">ДОБАВИТЬ ТОВАР</a>
				<li><c:url value="/buyer/order.html" var="cartLink" /> <a
					class="action" href="${cartLink}">ИЗМЕНИТЬ ТОВАР</a>
				<li><c:url value="/buyer/order.html" var="cartLink" /> <a
					class="action" href="${cartLink}">УДАЛИТЬ ТОВАР</a>
			</ul>
		</aside>
	</u:profile>
</body>
</html>