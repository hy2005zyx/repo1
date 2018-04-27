<%@page import="com.yc.teach.bean.User"%>
<%@page import="com.yc.teach.common.util.DBHelper"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/mytld.tld" prefix="mt"%>
<%
	Date date = new Date();
	pageContext.setAttribute("now", date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<%
pageContext.setAttribute("list", DBHelper.find("select * from user",User.class));
%>
</head>
<body>
	<mt:selectBox items="1:一,2:A,3:B,4:C,5:E"/><br>
	<mt:fmtDate date="${now}" fmt="yyyy-MM-dd HH:mm:ss" /><br>
	<mt:page href="test.jsp" size="10" total="35"/>
</body>
</html>