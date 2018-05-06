<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="com.yc.demo.ssq.jsp.entity.Lottery"%>
<%@page import="com.yc.demo.ssq.jsp.web.utils.*"%>
<%@page import="com.yc.demo.ssq.jsp.entity.PageBean"%>
<%@page import="com.yc.teach.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<%
	PageBean pb = (PageBean) session.getAttribute("PageBean");
	List<Lottery> list = pb.getList();
	int pagesize = pb.getPagesize();
%>
<table border="1" cellpadding="0" cellspacing="0" width="900" height="400">
  <tr>
    <th>开奖日期</th>
    <th>期号</th>
    <th>红1</th>
    <th>红2</th>
    <th>红3</th>
    <th>红4</th>
    <th>红5</th>
    <th>红6</th>
    <th>蓝</th>
    <th>销售金额</th>
    <th>一等奖</th>
    <th>二等奖</th>
  </tr>
 <%
    if(  list!=null&& list.size()>0){
		for(int i = 0;i < list.size(); i ++){   // 共 list.size() 行数据
			%>
			<tr style="text-align:center" >
			<td><%=list.get(i).getOpendate() %></td>
	    	<td><%=list.get(i).getNum() %></td>
	    	<td><%=list.get(i).getRedone() %></td>
	    	<td><%=list.get(i).getRedtwo() %></td>
	    	<td><%=list.get(i).getRedthree() %></td>
	    	<td><%=list.get(i).getRedfour() %></td>
	    	<td><%=list.get(i).getRedfive() %></td>
	    	<td><%=list.get(i).getRedsix() %></td>
	    	<td><%=list.get(i).getBlue() %></td>
	    	<td><%=list.get(i).getSale() %></td>
	    	<td><%=list.get(i).getFirstprize() %></td>
	    	<td><%=list.get(i).getSecondeprize() %></td>
			</tr>
			<%
		} 
    }
 %>
</table>

<br/><br/>
<div>
	共 <%=pb.getTotalpage() %>页 <%=pb.getTotal() %>条记录
	<a href="back/doLottery.jsp?pages=1&pagesize=<%=pb.getPagesize()%>">首页</a>
	<a href="back/doLottery.jsp?pages=<%=pb.getPrepage() %>&pagesize=<%=pagesize%>">上一页</a>
	<a href="back/doLottery.jsp?pages=<%=pb.getNextpage() %>&pagesize=<%=pagesize%>">下一页</a>
	<a href="back/doLottery.jsp?pages=<%=pb.getTotalpage() %>&pagesize=<%=pagesize%>">末页</a>
	当前第  <%=pb.getPages() %>页/每页<%=pb.getPagesize() %>
	到 <input type="text" name="pages" value="10" onblur="gotopage(  this.value   )"/>页
	
	
</div>


<script>
	function gotopage( pages ){
		location.href="back/doLottery.jsp?pages="+pages;
	}
</script>

<%@ include file="../bottom.jsp"%>