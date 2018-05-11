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
<script type="text/javascript">
</script>
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
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=line&year=2017&month=10'">销售金额变化折线图</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=bar&year=2017&month=10'">头等奖数量变化柱状图</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=pie&year=2017'">总年度销售金额饼图</a></li>
			<li>-----下面是练习题-----</li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=1&year=2017&month=10'"
					title="统计指定月份，在最近5年内的销售额变化的曲线图">历年指定月份销售额折线图？</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=2&year=2016'"
					title="统计指定年度，最高月销售额，最低月销售额，平均月销售额的柱状图">年度月销售数据对比柱状图？</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=3&year=2016'"
					title="统计指定年度，各个季度的销售总额的饼图">季度销售额统计饼图？</a></li>
			<li><a href="#" onclick="contentFrame.src='../report.servlet?type=4&year=2016'"
					title="统计指定年度，各个中奖号码出现的次数的饼图">中奖号码出现次数饼图？</a></li>
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