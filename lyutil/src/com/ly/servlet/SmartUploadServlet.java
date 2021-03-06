package com.ly.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.ly.util.BeanUtils;

public abstract class SmartUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 定义上传的路径
	protected String PATH;
	// 允许上传的文件
	protected String ALLOWED;
	// 拒绝上传的文件
	protected String DENIED;
	// 总大小
	protected int TOTALMAXSIZE;
	// 单个文件的总大小
	protected int SINGLESIZE;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		SmartUpload su = new SmartUpload();
		//初始化
		/**
		 * 初始化针对Servlet化的改造
		 */
		try {
			su.initialize(getServletConfig(), req, resp);
			// 添加参数Map
			Map<String, String> params = new HashMap<String, String>();
			req.setAttribute("params", params);

			// 允许上传的文件类型
			su.setAllowedFilesList(ALLOWED);
			su.setDeniedFilesList(DENIED);
			// 文件编码集
			su.setCharset("utf-8");
			// 最大大小
			su.setTotalMaxFileSize(TOTALMAXSIZE);
			// 单个文件的最大大小
			su.setMaxFileSize(SINGLESIZE);
			java.io.File pathDir = new java.io.File(getServletContext().getRealPath(PATH));
			if (pathDir.exists() == false) {
				if (pathDir.mkdirs() == false) {
					throw new IOException("创建上传文件夹失败！路径：" + PATH);
				}
			}
			// 开始上传
			su.upload();
			for (@SuppressWarnings("unchecked")
			Enumeration<String> e = su.getRequest().getParameterNames(); e.hasMoreElements();) {
				String pname = e.nextElement();
				String value = su.getRequest().getParameter(pname);
				params.put(pname, value);
			}
			if (su.getFiles() != null && su.getFiles().getCount() > 0) {
				// 得到上传的文件
				Files fs = su.getFiles();
				// 得到文件的所有信息
				@SuppressWarnings("unchecked")
				Collection<File> col = fs.getCollection();
				for (File f : col) {
					// 判断文件在上传的过程中是否丢失
					if (!f.isMissing()) {
						// 拼接文件路径，文件名加入当前时间戳+文件大小，低成本不重复策略
						long id = System.currentTimeMillis() + f.getSize();
						String fname = PATH + "/" + id + f.getFileName();
						// 保存 SAVE_VIRTUAL web根目录
						f.saveAs(fname, SmartUpload.SAVE_VIRTUAL);
						params.put(f.getFieldName(), fname);
						System.out.println("文件字段 " + f.getFieldName() + "（" + f.getFileName() + "） 已经保存至 " + fname);
					}
				}
			} else {
				System.err.println("没有上传的文件!");
			}
		} catch (SecurityException e) {
			throw e;
		} catch (SmartUploadException e) {
			throw new ServletException(e);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void init() throws ServletException {
		PATH = (String) BeanUtils.notNull(getInitParameter("PATH"), "/upload");
		ALLOWED = (String) BeanUtils.notNull(getInitParameter("ALLOWED"), "gif,jpg,bmp,png,txt,doc,xls");
		DENIED = (String) BeanUtils.notNull(getInitParameter("DENIED"), "");
		TOTALMAXSIZE = (Integer) BeanUtils.notNull(getInitParameter("TOTALMAXSIZE"), 20 * 1024 * 1024);
		SINGLESIZE = (Integer) BeanUtils.notNull(getInitParameter("SINGLESIZE"), 5 * 1024 * 1024);
	}

}
