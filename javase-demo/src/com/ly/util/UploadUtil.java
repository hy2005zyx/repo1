package com.ly.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class UploadUtil {
	//定义上传的路径
	private String PATH = "upload";
	//允许上传的文件
	private String ALLOWED = "gif,jpg,bmp,doc,png";
	//禁止上传的文件
	private String DENIED = "exe,bat,jsp,html,js";
	//总大小
	private int TOTALMAXSIZE = 20 * 1024 * 1024;
	//单个文件的总大小
	private int SINGLESIZE = 5 * 1024 * 1024;
	
	public UploadUtil(String pATH, String aLLOWED, String dENIED, int tOTALMAXSIZE, int sINGLESIZE) {
		super();
		PATH = pATH;
		ALLOWED = aLLOWED;
		DENIED = dENIED;
		TOTALMAXSIZE = tOTALMAXSIZE;
		SINGLESIZE = sINGLESIZE;
	}
	
	public UploadUtil() {
		super();
	}
	
	//文件上传封装                                               因为su的实例化需要pageContext，这是属于网页的，因此我们传进来
	public Map<String,String> uploadFiles(PageContext pagecontext) throws ServletException, IOException, SQLException, SmartUploadException{
		SmartUpload su=new SmartUpload();
		Map<String, String> params = new HashMap<String, String>();
		//初始化
		su.initialize(pagecontext);
		//允许上传的文件类型
		su.setAllowedFilesList(ALLOWED);
		//禁止上传的文件类型
		su.setDeniedFilesList(   DENIED );
		//文件编码集
		su.setCharset("utf-8");
		//最大大小
		su.setTotalMaxFileSize(TOTALMAXSIZE);
		//单个文件的最大大小
		su.setMaxFileSize(SINGLESIZE);
		//开始上传
		su.upload();
		// 从su中取出封装request
		Request request = su.getRequest(); // 注意这个reqeust请求对象是smartupload提供
		Enumeration et=request.getParameterNames(); //获取请求中的所有表单元素信息
		String str;
		//保存值   先取出数据，表单传过来的
		while(et.hasMoreElements()){
			str=String.valueOf(et.nextElement());
			params.put(str,request.getParameter(str));
		}
		if (su.getFiles() != null && su.getFiles().getCount() > 0) {
			//得到上传的文件
			Files fs = su.getFiles();
			//得到文件的所有信息
			Collection<File> col = fs.getCollection();
			String fname;
			String picPath="";
			int i=1;
			for (File f : col) {
				if (!f.isMissing()) { // 判断文件在上传的过程中是否丢失
					// 文件名有可能重复，所以不能用原文件名，必须重生成一个新文件名，
					fname = PATH+"/"+new Date().getTime() + ""+ new Random().nextInt(10000)+"."+f.getFileExt(); // 取得原文件的后缀名

					// 保存
					f.saveAs(fname, SmartUpload.SAVE_VIRTUAL);
					picPath+=fname;
					
					params.put("picPath"+i,picPath); // 取出最后存的文件名，保存到params
					i++;
					picPath="";
				}
			}
			//picPath+=picPath.substring(0,picPath.lastIndexOf(",")); //去掉最后的那个逗号
			
		}
		return params;
	}
	
}
