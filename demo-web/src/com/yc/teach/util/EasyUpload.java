package com.yc.teach.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class EasyUpload {

	public static void upload(String savePath, HttpServletRequest request)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		if (savePath.endsWith("/") == false) {
			savePath += "/";
		}
		for (Part p : request.getParts()) {
			String filename = p.getSubmittedFileName();
			if (filename.trim().isEmpty() == false) {
				filename = savePath + filename;
				System.out.println("上传文件保存至：" + filename);
				p.write(filename);
			}
		}
	}

}
