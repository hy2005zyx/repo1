package com.yc.teach.util;

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
		List<String> ret = new ArrayList<String>();
		for (Part p : request.getParts()) {
			String filename = p.getSubmittedFileName();
			if (filename.trim().isEmpty() == false) {
				filename = savePath + filename;
				p.write(filename);
				System.out.println("上传文件保存至：" + filename);
				ret.add(filename);
			}
		}
		return ret.toArray(new String[ret.size()]);
	}

}
