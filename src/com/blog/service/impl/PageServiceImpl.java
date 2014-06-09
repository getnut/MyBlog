package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.blog.service.PageService;
import com.blog.dbutils.SystemConfigUtils;
import com.blog.entity.Page;
public class PageServiceImpl implements PageService{
	
	
	public List<Alink> getPages(int currentPage)//curPage表示当前的页数
	{
		//模拟数据
		int totalRows= 200;//总的行数
		
		int rowsPerPage = Integer.parseInt(SystemConfigUtils.getSystemConfigValue("pages"));//每页的条数
		int totalPages = totalRows / rowsPerPage;
		System.out.println("一共的页数"+totalPages);

		if(0 != totalRows % rowsPerPage)
		{
			totalPages = totalPages + 1;
		}
		List<Alink> alinks = new ArrayList<Alink>();
		int startRows = (currentPage - 1) * rowsPerPage + 1;//在数据库中的开始行
		int prePage = -1;//上一页
		
		int nextPage = -1;//下一页
		
		if(currentPage > 1)
		{
			prePage = currentPage - 1;
		}
		if(currentPage < totalPages)
		{
			nextPage = currentPage + 1;
		}
		
		/**ddd*/
		int startPage; //当前页面所在组的起始页面
		int endPage; //当前页面所在组的尾页面
		int preGroupPage; //上一组页面的起始页面
		int nextGroupPage; // 下一组页面的起始页面
		int groupPagesCount = 5;
		//首页
		int firstPage = -1;
		//尾页
		int tailPage = -1;
		
		if(totalPages > 0)
		{
			firstPage = 1;
			tailPage = totalPages;
		}
		
		int tmp = currentPage % groupPagesCount;
		if(tmp == 0)
		{
			tmp = groupPagesCount;
		}
		startPage = currentPage - tmp + 1;//决定是否有....
		endPage =  startPage + groupPagesCount - 1;//决定时候有....


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
		this.createAlink(prePage, "上一页", alinks);
		//start ...
		this.createAlink(preGroupPage, "...", alinks);
		
		for (int i = startPage;i <= endPage;i++)
		{
			this.createAlink(i, String.valueOf(i), alinks);
		}
		//end ....
		this.createAlink(nextGroupPage, "...", alinks);
		//下一页
		this.createAlink(nextPage, "下一页", alinks);
		//尾页
		this.createAlink(tailPage, "尾页", alinks);
		return alinks;
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
		PageServiceImpl ps = new PageServiceImpl();
		for(int i = 1;i <= 20;i++)
		{
			System.out.println("当前的页数"+i);
			List<Alink> alinks = ps.getPages(i);
			for (int j = 0;j < alinks.size();j++)
			{
				Alink a = alinks.get(j);
				System.out.print(a.getTitle()+" | ");
			}
			System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
		}
	}

}
class Alink
{
	private String title;
	private int id;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
class SplitePage{
	private int totalPages;//总共页数
	
	private int current;//当前页
	
	private int prePage;//上一页
	
	private int nextPage;//下一页
	
	private List<Page> pages;
	
	public SplitePage(){
		
	}
}
