<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clothingStore</title>
</head>
<body>
	<u:profile login="${authorizedUser.login}" role="МЕНЕДЖЕР">
		<c:choose>
			<c:when test="${result != null}">
				<h3 class="result">${result}</h3>
			</c:when>
			<c:when test="${add}">
				<u:saveProductPlace />
			</c:when>
			<c:when test="${upd}">
				<u:chooseProduct action="updProduct" actionName="Изменить"
					urlAction="/manager/managerUpdForm.html" />
			</c:when>
			<c:when test="${del}">
				<u:chooseProduct action="delProduct" actionName="Удалить"
					urlAction="/manager/managerDel.html" />
			</c:when>
		</c:choose>
		<aside class="userActions">
			<ul>
				<li><c:url
						value="/manager/profileManager.html?addsProduct='true'"
						var="cartLink" /> <a href="${cartLink}" class="action">ДОБАВИТЬ
						ТОВАР</a>
				<li><c:url
						value="/manager/profileManager.html?updProduct='true'"
						var="cartLink" /> <a class="action" href="${cartLink}">ИЗМЕНИТЬ
						ТОВАР</a>
				<li><c:url
						value="/manager/profileManager.html?delProduct='true'"
						var="cartLink" /> <a class="action" href="${cartLink}">УДАЛИТЬ
						ТОВАР</a>
			</ul>
		</aside>
	</u:profile>
</body>
</html>