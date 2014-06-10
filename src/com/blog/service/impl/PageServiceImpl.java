package com.blog.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.service.PageService;
import com.blog.dao.PageDao;
import com.blog.dbutils.DataSourceUtil;
import com.blog.dbutils.SystemConfigUtils;
import com.blog.entity.Alink;
import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;



public class PageServiceImpl implements PageService{
	
	private DataSource dataSource = null;
	private PageDao pageDao = null;
	
	public PageServiceImpl (DataSource dataSource,PageDao pageDao)
	{
		this.dataSource = dataSource;
		this.pageDao = pageDao;
	}
	//发表文章
	public boolean addPage(Page page)
	{
		boolean result = false;
		try
		{
			result = this.pageDao.addPage(page);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		return result;
	}
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
		PageSplitResult psr = new PageSplitResult();
		try
		{
			
			//总的行数
			int totalRows= this.pageDao.totalPages();
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
			List<Alink> alinks = this.getAlinks(currentPage, rowsPerPage, totalPages);
			List<Page> pages = this.pageDao.getPages(startRows,selectCount);
			psr.setAlink(alinks);
			psr.setPages(pages);
			psr.setTotalPages(totalPages);
			psr.setTotalRows(totalRows);
			psr.setCurrentPage(currentPage);
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		
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
	@Override
	public Page getPage(long pageId) {
		Page page = null;
		try
		{
			page = this.pageDao.getPage(pageId);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		return page;
	}

}

