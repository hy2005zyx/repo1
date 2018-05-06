<%@page import="java.util.ArrayList"%>
<%@page import="com.yc.demo.ssq.jsp.entity.Lottery"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.demo.ssq.jsp.entity.PageBean"%>
<%@page import="com.yc.teach.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int pages = 1;
	int pagesize = 10;
	if(request.getParameter("pages") != null){
		pages = Integer.parseInt( request.getParameter("pages") );
	}
	if(request.getParameter("pagesize") != null){
		pagesize = Integer.parseInt( request.getParameter("pagesize") );
	}
	PageBean pb = new PageBean();
	pb.setPages(pages);
	pb.setPagesize(pagesize);
	
	String sql01 = "select count(*) as total from lottery where 1=1  ";
	long total = Long.parseLong(DBHelper.findAll(sql01).get(0).get("total").toString());
	pb.setTotal(total);
	pb.setTotalpage((total%pagesize==0?total/pagesize:total/pagesize+1));
	
	//if( pages<=0|| pages>pb.getTotalpage()){
	//	out.println("<script></script>");
	//}

	String sql02 = "select * from lottery order by opendate limit ?,?";
	int start = (pages - 1) * pagesize;
	List<Object> params = new ArrayList<Object>();
	params.add(start);
	params.add(pagesize);
	List<Lottery> list = DBHelper.find(sql02, Lottery.class, params.toArray());
	//System.out.println(list);
	pb.setList(list);
	
	session.setAttribute("PageBean", pb);
		
	response.sendRedirect("lottery.jsp");
%>