<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/mytld.tld" prefix="mt" %>
<%
	Date date = new Date();
	pageContext.setAttribute("now", date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
</head>
<body>
<mt:fmtDate date="${now}" fmt="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>