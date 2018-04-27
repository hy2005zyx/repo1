package com.yc.teach.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CheckboxTag extends SimpleTagSupport {

	private Object items;

	public void setItems(Object items) {
		this.items = items;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		getJspBody().invoke(out);
		if (items instanceof String) {
			for (String item : ((String) items).split(",")) {
				out.println(String.format("<input type='checkbox' value='%s'>%s", item, item));
			}
		}
	}

}
