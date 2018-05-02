<%@page import="com.yc.teach.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户列表</title>
</head>
<%
	pageContext.setAttribute("list",
			DBHelper.findAll("select * from user"));
%>
<body>
	<ol>
		<c:forEach items="${list}" var="u">
			<li>${u.name}</li>
		</c:forEach>
	</ol>
</body>
</html>