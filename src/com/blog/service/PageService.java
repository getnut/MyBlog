package com.blog.service;

import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;

public interface PageService {
	public  boolean addPage(Page page);
	public PageSplitResult getPages(int currentPage);//curPage表示当前的页数
	public Page getPage(long pageId);
	public boolean deletePage(long pageId);
}
