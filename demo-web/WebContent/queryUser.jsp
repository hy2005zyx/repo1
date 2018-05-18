<%@page import="com.yc.teach.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${basePath }/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath }/easyui/themes/icon.css">
<script type="text/javascript" src="${basePath }/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath }/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>用户列表</title>
</head>
<body>
	<table class="easyui-datagrid" title="用户查询" data-options="
			rownumbers:true,
			singleSelect:true,
			iconCls:'icon-ok',
			url:'${basePath }/user.s?action=query',
			method:'post',
			fit:true,
			pagination:true,
			pageSize:5,
			pageList:[5,10,20,50]">
		<thead>
			<tr>
				<th data-options="field:'name',width:80">用户名</th>
				<th data-options="field:'email',width:100">Email</th>
				<th data-options="field:'tel',width:100">电话</th>
			</tr>
		</thead>
	</table>
</body>
</html>