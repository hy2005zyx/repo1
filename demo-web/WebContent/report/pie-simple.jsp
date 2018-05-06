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
		<input name="type" value="pie" type="hidden">
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
	    title : {
	        text: '2017月度销售量饼图',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ${nameList}
	    },
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            data:${valueList.toString().replaceAll("=",":")}
	        }
	    ]
	};
	myChart.setOption(option, true);
       </script>
</body>
</html>