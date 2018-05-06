<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("basePath", application.getContextPath());
%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
	<div id="container" style="height: 100%"></div>
	<script type="text/javascript" src="${basePath}/report/js/echarts.min.js"></script>
	<script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
option = {
		color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    title : {
	        text: '2017年10月 彩票头等奖数量变化柱状图',
	        x:'center'
	    },
    xAxis: {
        type: 'category',
        data: ${nameList}
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: ${valueList},
        type: 'bar'
    }]
};
myChart.setOption(option, true);
       </script>
</body>
</html>