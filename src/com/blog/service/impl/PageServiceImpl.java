package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.blog.service.PageService;
import com.blog.dbutils.SystemConfigUtils;
import com.blog.entity.Alink;
import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;



public class PageServiceImpl implements PageService{
	
	private List<Alink> getAlinks(int currentPage,int rowsPerPage,int totalPages)
	{
		//分页连接列表
		List<Alink> alinks = new ArrayList<Alink>();
		int prePage = -1;//上一页
		int nextPage = -1;//下一页
		//首页
		int firstPage = -1;
		//尾页
		int tailPage = -1;
		if(currentPage > 1)
		{
			prePage = currentPage - 1;
			firstPage = 1;
		}
		if(currentPage < totalPages)
		{
			nextPage = currentPage + 1;
			tailPage = totalPages;
		}
		/*前台显示的分页框*/
		int startPage; //当前页面所在组的起始页面
		int endPage; //当前页面所在组的尾页面
		int preGroupPage; //上一组页面的起始页面
		int nextGroupPage; // 下一组页面的起始页面
		int groupPagesCount = Integer.parseInt(SystemConfigUtils.getSystemConfigValue("groupPagesCount"));
		
		int tmp = (int)((currentPage - 1) / groupPagesCount);
		
		startPage = tmp * groupPagesCount + 1;
		
		if(startPage + groupPagesCount - 1 > totalPages)
		{
			endPage = totalPages;
		}else
		{
			endPage = startPage + groupPagesCount - 1;
		}
		
		preGroupPage = -1;
		if(startPage > 1){
			preGroupPage = startPage - 1;
		}
		nextGroupPage = -1;
		if(endPage < totalPages){
			nextGroupPage = endPage + 1;
		}
		/*开始组装返回的数据*/
		//首页
		this.createAlink(firstPage, "首页", alinks);
		//上一页
		this.createAlink(prePage, "<", alinks);
		//start ...
		this.createAlink(preGroupPage, "...", alinks);
		
		for (int i = startPage;i <= endPage;i++)
		{
			this.createAlink(i, String.valueOf(i), alinks);
		}
		//end ....
		this.createAlink(nextGroupPage, "...", alinks);
		//下一页
		this.createAlink(nextPage, ">", alinks);
		//尾页
		this.createAlink(tailPage, "尾页", alinks);
		return alinks;
	}
	public PageSplitResult getPages(int currentPage)//curPage表示当前的页数
	{
		
		//总的行数
		int totalRows= 3403;
		//每页的条数
		int rowsPerPage = Integer.parseInt(SystemConfigUtils.getSystemConfigValue("pages"));

		//总的页数
		int totalPages = totalRows % rowsPerPage == 0 ? (int)(totalRows/rowsPerPage):(int)(totalRows/rowsPerPage)+1;
		if(currentPage < 1 || currentPage > totalPages)
		{
			currentPage = 1;
		}
		if(totalPages == 0)
		{
			return null;
		}
		//在数据库的起始行
		int startRows = (currentPage - 1) * rowsPerPage + 1;//在数据库中的开始行
		int selectCount;
		if(startRows + rowsPerPage - 1 > totalRows)
		{
			selectCount = (totalRows - startRows) + 1;
		}else
		{
			selectCount = rowsPerPage;
		}
		PageSplitResult psr = new PageSplitResult();
		List<Alink> alinks = this.getAlinks(currentPage, rowsPerPage, totalPages);
		List<Page> pages = new ArrayList<Page>();
		psr.setAlink(alinks);
		psr.setPages(pages);
		psr.setTotalPages(totalPages);
		psr.setTotalRows(totalRows);
		psr.setCurrentPage(currentPage);
		return psr;
	}
	
	private void createAlink(int id,String title,List<Alink> alinks)
	{
		if(id != -1)
		{
			Alink a = new Alink();
			a.setId(id);
			a.setTitle(title);
			alinks.add(a);
		}
	}
	public static void main(String[] args) {
		
	}

}

