package com.blog.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.lang.SystemUtils;

import com.blog.service.PageService;
import com.blog.dao.ClassDao;
import com.blog.dao.PageClassDao;
import com.blog.dao.PageDao;
import com.blog.dao.PageQuery;
import com.blog.dbutils.DataSourceUtil;
import com.blog.dbutils.HtmlUtil;
import com.blog.dbutils.SystemConfigUtils;
import com.blog.dbutils.ToHtml;
import com.blog.dbutils.TransactionManager;
import com.blog.entity.Alink;
import com.blog.entity.Classes;
import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;



public class PageServiceImpl implements PageService{
	
	private DataSource dataSource = null;
	private PageDao pageDao = null;
	private PageClassDao pageClassDao = null;
	private TransactionManager transaction = null;
	private PageQuery pageQuery = null;
	private ClassDao classDao = null;
	public PageServiceImpl ()
	{
		
	}
	//添加文章
	public boolean addPage(Page page)
	{
		boolean result = false;
		try
		{
			this.transaction.start();
			long pageId = this.pageDao.addPage(page);
			List<Classes> clses = page.getClses();
			page.setPageId(pageId);
			Classes cls = null;
			
			for(int i = 0;i < clses.size();i++)
			{	
				cls = clses.get(i);
				this.pageClassDao.addPageClass(pageId,cls.getClassId());
				cls.setClassName(this.classDao.getClass(cls.getClassId()).getClassName());
			}
			//生成静态的页面
			Map<String,Object> map = new HashMap<String,Object>();
			String tmp = HtmlUtil.decodeHtml(page.getPageContent());
			System.out.println("tmp="+tmp);
			page.setPageContent(tmp);
			map.put("page", page);
			map.put("context",SystemConfigUtils.getSystemConfigValue("context"));
			result = ToHtml.toHtml("/vm/page-detail.vm",SystemConfigUtils.getSystemConfigValue("realPath")+"/pages/"+page.getPageId()+".html",map);
			//如果生成失败
			if(!result)
			{
				throw new SQLException();
			}
			this.transaction.commit();
			result = true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			this.transaction.rollback();
		}finally
		{
			this.transaction.close();
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
	//获取第currentPage页的文章
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
			List<Page> pages = this.pageQuery.getPages(startRows, selectCount);
			System.out.println("size:"+pages.size());
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
	
	//获得文章
	public Page getPage(long pageId) {
		Page page = null;
		try
		{
			page = this.pageQuery.getPage(pageId);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		return page;
	}
	@Override
	public boolean deletePage(long pageId) {
		boolean result = false;
		try
		{
			this.transaction.start();
			this.pageDao.deletePage(pageId);
			this.pageClassDao.deleteByPageId(pageId);
			result = true;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		return result;
	}
	//set和get方法
	
	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public PageDao getPageDao()
	{
		return pageDao;
	}

	public void setPageDao(PageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public PageClassDao getPageClassDao()
	{
		return pageClassDao;
	}

	public void setPageClassDao(PageClassDao pageClassDao)
	{
		this.pageClassDao = pageClassDao;
	}

	public TransactionManager getTransaction()
	{
		return transaction;
	}

	public void setTransaction(TransactionManager transaction)
	{
		this.transaction = transaction;
	}

	public PageQuery getPageQuery()
	{
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery)
	{
		this.pageQuery = pageQuery;
	}

	public ClassDao getClassDao()
	{
		return classDao;
	}

	public void setClassDao(ClassDao classDao)
	{
		this.classDao = classDao;
	}	
	
}

