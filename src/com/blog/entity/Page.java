package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

public class Page
{
	private long pageId;
	
	private String pageTitle;
	
	private String pageContent;
	
	private String writeTime;
	
	private List<Classes> clses = new ArrayList<Classes>();
	
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

	public List<Classes> getClses()
	{
		return clses;
	}

	public void setClses(List<Classes> clses)
	{
		this.clses = clses;
	}
}	
