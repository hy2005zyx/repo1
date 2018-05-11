<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/mytld.tld" prefix="mt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
</head>
<body>
	<%
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				out.print(i + "*" + j + "=" + i * j+"\t");
			}
			out.print("<br>");
		}
	%>
</body>
</html>