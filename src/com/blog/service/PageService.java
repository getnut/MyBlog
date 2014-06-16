package com.blog.service;

import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;

public interface PageService {
	
	//增加文章
	public  boolean addPage(Page page);
	//获得第currentPage页数据
	public PageSplitResult getPages(int currentPage);//curPage表示当前的页数
	//获得文章
	public Page getPage(long pageId);
	//删除文章
	public boolean deletePage(long pageId);
}
