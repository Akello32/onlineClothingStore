<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<c:url value="/css/managerAdds.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<div class="workplace">
	<table>
		<tr class="productCard">
			<td class="title">
				<h3>Изображение</h3>
			</td>
			<c:if test="${product != null}">
				<td class="first"><img class="productImg" alt="image"
					src="${pageContext.request.contextPath}/img/BDimg/${product.imgPath}"></td>
			</c:if>
			<td class="first"><c:if test="${product != null}">
					<input type="file" name="photo" accept="image/*,image/jpeg"
						form="addProduct">
					<input type="hidden" name="imgPath" value="${product.imgPath}"
						form="addProduct" />
				</c:if> <c:if test="${product == null}">
					<td class="first"><input type="file" required name="photo"
						accept="image/*,image/jpeg" form="addProduct"></td>
				</c:if></td>
		</tr>
		<c:if test="${product != null}">
			<input type="hidden" name="productId" value="${product.identity}"
				form="addProduct" />
			<input type="hidden" name="sizeId" value="${size.identity}"
				form="addProduct" />
		</c:if>
		<tr class="productCard">
			<td class="title">
				<h3>Название</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required maxlength="60" value="${product.name}" name="nameProduct"
				form="addProduct"></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Описание</h3>
			</td>
			<td class="first"><textarea rows="5" cols="45" required
					form="addProduct" name="descriptionProduct">${product.description}</textarea></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Размер</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required name="sizeProduct" value="${size.name}" maxlength="10"
				form="addProduct"></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Бренд</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required name="brandProduct" value="${product.brand.name}"
				maxlength="40" form="addProduct"></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Цвет</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required name="colorProduct" value="${product.color}" maxlength="30"
				form="addProduct"></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Цена</h3>
			</td>
			<td class="first"><input type="number" min="1" required
				name="priceClothes" value="${product.price}" value="1"
				class="numberInput" form="addProduct" /></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Кол-во</h3>
			</td>
			<td class="first"><input type="number" min="1" required
				name="numbersClothes" value="${size.numbers}" value="1"
				class="numberInput" form="addProduct" /></td>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Пол</h3>
			</td>
			<td class="first"><p>
					<input class="check" name="genderProduct" required id="men"
						form="addProduct" required type="radio" value="men"> <label
						class="checkLable" for="men">МУЖСКОЙ</label>
				</p>
				<p>
					<input class="check" name="genderProduct" required id="woman"
						form="addProduct" required type="radio" value="women"> <label
						class="checkLable" for="woman">ЖЕНСКИЙ</label>
				</p>
		</tr>
		<tr class="productCard">
			<td class="title">
				<h3>Тип</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required name="typeProduct" value="${product.type.name}"
				maxlength="40" form="addProduct"></td>
		</tr>
		<tr class="lastTr">
			<td colspan="4"><c:url value="/manager/addsProduct.html"
					var="addProduct" />
				<form action="${addProduct}" accept-charset="utf-8"
					enctype="multipart/form-data" method="post" id="addProduct">
					<input type="submit" class="addButton" value="Сохранить">
				</form></td>
		</tr>
	</table>
</div>


