<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
	<div id="formdiv" style="height: 5%">
		<form action="${basePath }/report.servlet" method="post">
		<input name="type" value="2" type="hidden">
		年度：<select name="year" style="height: 25px">
			<c:forEach begin="2013" end="2017" var="y">
				<option value="${y}" ${param.year==y?"selected":"" }>${y}年</option>
			</c:forEach>
		</select>
		<input type="submit" value="  统 计  " style="height: 25px">
		</form>
	</div>
	<div id="container" style="height: 95%"></div>
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
	        text: '${param.year}年月销售数据对比柱状图',
	        subtext: "统计指定年度，最高月销售额，最低月销售额，平均月销售额的柱状图",
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