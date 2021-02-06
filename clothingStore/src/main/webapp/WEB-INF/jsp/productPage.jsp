<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url value="/css/catalog.css" var="cssUrl" />
<link rel="stylesheet" href="${cssUrl}">
</head>
<body>
<div class="page">
	<u:header name="${brand.name} ${type.name}" />
	
	
	
	<u:footer />
</div>
</body>
</html>