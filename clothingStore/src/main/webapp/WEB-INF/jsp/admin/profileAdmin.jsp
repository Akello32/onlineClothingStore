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
	<u:profile login="${authorizedUser.login}" role="Администратор">
		<c:choose>
			<c:when test="${result != null}">
				<h3 class="result">${result}</h3>
			</c:when>
			<c:when test="${add}">
				<u:addManager />
			</c:when>
			<c:when test="${del}">
				<u:delManager />
			</c:when>
		</c:choose>
		<aside class="userActions">
			<ul>
				<li><c:url
						value="/admin/profileAdmin.html?addsManager='true'"
						var="cartLink" /> <a href="${cartLink}" class="action">ДОБАВИТЬ
						МЕНЕДЖЕРА</a>
				<li><c:url
						value="/admin/profileAdmin.html?delManager='true'"
						var="cartLink" /> <a class="action" href="${cartLink}">УДАЛИТЬ
						МЕНЕДЖЕРА</a>
			</ul>
		</aside>
	</u:profile>
</body>
</html>