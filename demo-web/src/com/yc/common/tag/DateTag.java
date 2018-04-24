package com.yc.common.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport {

	private Date date;
	private String fmt = "yyyy-MM-dd";

	@Override
	public void doTag() throws JspException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		getJspContext().getOut().print(sdf.format(date));
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFmt() {
		return fmt;
	}

	public void setFmt(String fmt) {
		this.fmt = fmt;
	}

	
	public static void main(String[] args) {

	}
}
