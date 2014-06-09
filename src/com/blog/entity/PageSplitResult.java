package com.blog.entity;

import java.util.ArrayList;
import java.util.List;



public class PageSplitResult
{
	//文章列表
	private List<Page> pages = null;
	//总共多少页
	private int totalPages;
	//总的条数
	private int totalRows;
	//返回分页按钮
	private List<Alink> alink = new ArrayList<Alink>();
	//当前页
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public List<Alink> getAlink() {
		return alink;
	}
	public void setAlink(List<Alink> alink) {
		this.alink = alink;
	}
}
