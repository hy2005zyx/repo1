<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="com.yc.demo.ssq.jsp.entity.Lottery"%>
<%@page import="com.yc.demo.ssq.jsp.web.utils.*"%>
<%@page import="com.yc.demo.ssq.jsp.entity.PageBean"%>
<%@page import="com.yc.teach.util.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	
	UploadFiles uf=new UploadFiles();
	uf.setAllowedFilesList("txt");
	uf.setDeniedFilesList("");
	Map<String,String> map=uf.uploadFiles(pageContext);

	String ssqTxtPath = map.get("ssq_absolutepath");//图片
	File file =new File(  ssqTxtPath);
	FileReader in=null;
	if( !file.exists()){
		out.println("<script>alert('文件不存在');location.href='../index.jsp';</script>");
	}else{
		try{
			 in = new FileReader(file);
			StringBuilder sb = new StringBuilder();
			int len = 0;
			char[] cs = new char[1024];
			while((len=in.read(cs)) != -1){
				sb.append(new String(cs,0,len));
			}
			//System.out.println(  sb.toString() );
			InsertIntoDatabase iid=new InsertIntoDatabase();
			int result=iid.insert(sb.toString() );
			//System.out.println("添加成功:"+ result);
			out.println("<script>alert('添加成功，共添加了:"+ result+"条数据');location.href='../index.jsp';</script>");
		}catch( Exception ex){
			ex.printStackTrace();
		}finally{
			in.close();
		}
		
		
		

		
	}
	

	
%>