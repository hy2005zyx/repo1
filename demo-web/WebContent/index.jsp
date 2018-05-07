<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<title>测试主页</title>
<style>
header {
	background-color: #eee;
	padding: 5px 20px;
	height: 5%
}

aside {
	float: left;
	width: 20%;
	background-color: #ccc;
	height: 88%
}

article {
	float: left;
	width: 80%;
	background-color: #ddd;
	height: 88%
}

footer {
	background-color: #eee;
	text-align: center;
	height: 5%
}

iframe {
	width: 100%;
	height: 100%;
	border: 0px
}
</style>
</head>
<body style="height: 100%; margin: 0">
	<header>
		欢迎来演示系统 <span style="float: right;"> </span>
	</header>
	<aside>
		<ul>
			<li><a href="login/back/login.jsp">AJAX用户登录</a></li>
			<li><a href="ssq/jsp/index.jsp">双色球数据上传</a></li>
			<li><a href="report/report.jsp">双色球图形报表</a></li>
			<li><a href="queryUser.jsp">用户查询</a></li>
			<li><a href="upload.jsp">文件上传</a></li>
			<li><a href="test.jsp">测试页面</a></li>
		</ul>
	</aside>
	<article>
		<iframe id="contentFrame"></iframe>
	</article>
	<footer>
		<small>版权所有：源辰公司</small>
	</footer>
</body>
</html>