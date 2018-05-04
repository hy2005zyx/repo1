package com.yc.teach.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class EasyUpload {

	public static String[] upload(String savePath, HttpServletRequest request)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		if (savePath.endsWith("/") == false) {
			savePath += "/";
		}
		//创建目录
		File dir = new File(savePath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		List<String> ret = new ArrayList<String>();
		for (Part p : request.getParts()) {
			String filename = p.getSubmittedFileName();
			if (filename != null && filename.trim().isEmpty() == false) {
				filename = savePath + filename;
				p.write(filename);
				System.out.println("上传文件保存至：" + filename);
				ret.add(filename);
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

}
