package com.blog.entity;

public class Page
{
	private long pageId;
	
	private String pageTitle;
	
	private String pageContent;
	
	private String writeTime;
	
	private Classes clss;
	
	private String summary;
	
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getWriteTime()
	{
		return writeTime;
	}

	public void setWriteTime(String writeTime)
	{
		this.writeTime = writeTime;
	}



	public long getPageId()
	{
		return pageId;
	}

	public void setPageId(long pageId)
	{
		this.pageId = pageId;
	}

	public String getPageTitle()
	{
		return pageTitle;
	}

	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = pageTitle;
	}

	public String getPageContent()
	{
		return pageContent;
	}

	public void setPageContent(String pageContent)
	{
		this.pageContent = pageContent;
	}

	public Classes getClss()
	{
		return clss;
	}

	public void setClss(Classes clss)
	{
		this.clss = clss;
	}
}	
