package com.yc.teach.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class SingleTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected String name;
	protected String attr;

	protected void println(String str, Object... params) throws JspException {
		try {
			pageContext.getOut().println(String.format(str, params));
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttr(String attr) {
		this.attr = attr;
	}

	public void setId(String id) {
		this.id = id;
	}

}
