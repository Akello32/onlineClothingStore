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
				<h3>Добавить менеджера</h3>
			</td>
		</tr>
		<tr class="userCard">
			<td class="title">
				<h3>Login</h3>
			</td>
			<td class="first"><input type="text" class="nameProduct"
				required maxlength="60" name="login"
				form="addManager"></td>
		</tr>
		<tr class="userCard">
			<td class="title">
				<h3>Password</h3>
			</td>
			<td class="first"><input type="password" class="nameProduct"
				required name="password" value="${size.name}" maxlength="25"
				form="addManager"></td>
		</tr>
		<tr class="lastTr">
			<td colspan="4"><c:url value="/admin/addsManager.html"
					var="addManager" />
				<form action="${addManager}" name="addManager" method="post" id="addManager">
					<input type="submit" class="addButton" value="Сохранить">
				</form></td>
		</tr>
	</table>
</div>
