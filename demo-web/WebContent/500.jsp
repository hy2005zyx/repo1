<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资源未找到</title>
</head>
<body style="margin: 200px;text-align: center;">
	系统繁忙，请稍后再试。
	<%
		exception.printStackTrace(System.err);
	%>
</body>
</html>