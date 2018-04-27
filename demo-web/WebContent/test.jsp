<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/TestTld.tld" prefix="tt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试网页</title>
</head>
<%
	Date now = new Date();
	pageContext.setAttribute("now", now);
%>
<body>
	<tt:date value="${now}" format="yyyy年MM月dd日"/>
</body>
</html>