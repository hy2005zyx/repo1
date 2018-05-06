<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<title>主页</title>
<style>
header {
	background-color: #eee;
	padding: 5px 20px;
	height: 5%
}

aside {
	float: left;
	width: 20%;
	height: 400px;
	background-color: #ccc;
	height: 88%
}

article {
	float: left;
	width: 80%;
	height: 400px;
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
		欢迎来到彩票统计系统
		<span style="float: right;">
		<a href="${basePath}">退出</a>
		</span>
	</header>
	<aside>
		<ul>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=line'">销售金额变化折线图</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=bar'">头等奖数量变化柱状图</a></li>
			<li><a href="#" onclick="contentFrame.src='pie-simple.html'">总年度季度销售金额饼图</a></li>
			<li><a href="#" onclick="contentFrame.src='pie-simple.html'">饼图</a></li>
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