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
	    title : {
	        text: '2017年10月 彩票销售金额变化折线图',
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
        type: 'line'
    }]
};
myChart.setOption(option, true);
       </script>
</body>
</html>