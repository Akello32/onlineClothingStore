<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="action" required="true" type="String"%>
<%@attribute name="urlAction" required="true" type="String"%>
<%@attribute name="actionName" required="true" type="String"%>

<head>
<meta charset="UTF-8">
<c:url value="/css/managerChoose.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<div class="workplace">
	<div class="contentPhotos">
		<c:forEach items="${clothes}" var="product">
			<c:set value="${clothes[0].identity}" var="prevPage" scope="page"></c:set>
			<div class="productCard">
				<c:url value="/product.html?product=${product.identity}"
					var="productLink" />
				<a href="${productLink}" class="productLink"><img
					class="productImg"
					src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}"
					alt="product" />
					<div class="productContent">
						<div class="productName">${product.brand.name}</div>
						<div class="productName">${product.name}</div>
						<div class="productPrice">${product.price}BYN</div>
					</div> </a>
				<ul class="sizesMenu">
					<li class="chooseSize">Выберите размер</li>
					<c:forEach items="${product.sizes}" var="size">
						<c:if test="${size.numbers != 0}">
							<c:set var="count" value="1" scope="page" />
							<li class="sizeElem"><input class="checkSize"
								id="size${size.identity}" autocomplete="off" type="radio"
								name="sizeProduct${product.identity}" value="${size.identity}"
								form="action" /><label class="checkLable checkSizeLable"
								for="size${size.identity}">${size.name}</label></li>
						</c:if>
					</c:forEach>
				</ul>
				<input class="checkOrder" type="submit" name="product" form="action"
					value="${product.identity}" id="${product.identity}" /> <label
					class="orderButton block" for="${product.identity}">${actionName}</label>
				<c:url
					value="${urlAction}?${action}='true'"
					var="updLink" />
				<form class="form" id="action" action="${updLink}" method="post"></form>
			</div>
			<c:set value="${product.identity}" var="nextPage" scope="page"></c:set>
		</c:forEach>
	</div>
	<div class="pagination">
		<c:if test="${prevBul}">
			<c:url
				value="/manager/profileManager.html?previous=${prevPage}&${action}='true'"
				var="prevLink" />
		</c:if>
		<c:url value="/img/left-arrow.png" var="prevImg" />
		<a href="${prevLink}"> <img src="${prevImg}" class="changePage"
			alt="previous" />
		</a>
		<c:if test="${nextBul}">
			<c:url
				value="/manager/profileManager.html?next=${nextPage}&${action}='true'"
				var="nextLink" />
		</c:if>
		<c:url value="/img/right-arrow.png" var="nextImg" />
		<a href="${nextLink}"><img src="${nextImg}" class="changePage"
			alt="next" /></a>
	</div>
</div>


