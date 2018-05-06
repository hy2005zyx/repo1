package com.yc.demo.ssq.jsp.entity;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 4198516371292530899L;
	private Integer pages;
	private Integer pagesize;
	private Long totalpage;
	private Long total;
	private List<T> list;

	public Integer getPrepage() {
		return (pages > 1 ? pages - 1 : pages);
	}

	public Integer getNextpage() {
		return (pages < totalpage ? pages + 1 : pages);
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Long getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Long totalpage) {
		this.totalpage = totalpage;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
