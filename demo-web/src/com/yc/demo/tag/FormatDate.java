package com.yc.demo.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class FormatDate extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Date value;
	private String format;

	@Override
	public int doStartTag() throws JspException {

		if (value == null || format == null) {
			return SKIP_BODY;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String ret = sdf.format(value);
			try {
				pageContext.getOut().print(ret);
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
