package com.yc.teach.common.tag;

import javax.servlet.jsp.JspException;

public class PageTag extends SingleTag {

	private static final long serialVersionUID = 1L;

	private int total;
	private int size;
	private String href = "";
	private int allPage;
	private int currentPage;

	@Override
	public int doStartTag() throws JspException {
		if (total <= 0 || size <= 0) {
			return SKIP_BODY;
		}
		//计算最后一页的页数
		allPage = total / size + 1;
		//输出分页链接
		while (currentPage < allPage) {
			printPage();
		}
		return EVAL_BODY_INCLUDE;
	}

	private void printPage() throws JspException {
		String pageName = currentPage == 0 ? "首页" : currentPage == allPage - 1 ? "尾页" : (currentPage + 1 + "");
		currentPage ++;
		println("<a href='%s?page=%s&size=%s'>%s</a>", href, currentPage, size, pageName);
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
