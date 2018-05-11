<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="centerParent">
<head>
<meta charset="UTF-8">
<title>资源未找到</title>
<link rel="stylesheet" href="${basePath }/css/ly.css" />
</head>
<body class="center">
	系统繁忙，请稍后再试。
	<%
		exception.printStackTrace(System.err);
	%>
</body>
</html>