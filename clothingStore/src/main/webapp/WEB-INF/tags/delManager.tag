<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<c:url value="/css/adminAdd.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<div class="workplace">
	<table>
		<tr class="userCard">
			<td colspan="4" class="titleName">
				<h3>Удалить менеджера</h3>
			</td>
		</tr>
		<tr class="userCard">
			<td class="title">
				<h3>Login</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required maxlength="60" placeholder="Введите логин" name="login"
				form="delManager"></td>
		</tr>
		<tr class="userCard">
			<td class="title">
				<h3>Password</h3>
			</td>
			<td class="first"><input type="password" class="nameProduct"
				required name="password" value="${size.name}" maxlength="25"
				placeholder="Введите пароль"
				form="delManager"></td>
		</tr>
		<tr class="lastTr">
			<td colspan="4"><c:url value="/admin/delManager.html"
					var="delManager" />
				<form action="${delManager}" name="delManager" method="post"
					id="delManager">
					<input type="submit" class="addButton" value="Удалить">
				</form></td>
		</tr>
	</table>
</div>
