<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>



<form action="${basePath }/ssq/jsp/back/doUploadSsq.jsp" method="post" enctype="multipart/form-data">
	双色球中奖信息:
	<input type="file" name="ssq" />
	<br />
	<input type="submit" value="上传" />
</form>



<a href="${basePath }/ssq/jsp/back/doLottery.jsp">点击查看双色球</a>
<%@ include file="bottom.jsp"%>