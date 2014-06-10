package com.blog.entity;

import java.util.HashMap;



/*ajax的响应对象*/
public class AjaxResponse
{
	private int status;//响应的结果状态，比如登录，可能成功，可能失败
	private int responseType;//响应的类型
	private HashMap<String,Object> data = new HashMap<String, Object>();//绑定数据
	
	private AjaxResponse(){}
	public void addData(String key,Object data)
	{
		this.data.put(key, data);
	}
	public void removeData(String key,Object data)
	{
		this.data.remove(data);
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getResponseType()
	{
		return responseType;
	}
	public void setResponseType(int responseType)
	{
		this.responseType = responseType;
	}
	public HashMap<String, Object> getData()
	{
		return data;
	}
	public void setData(HashMap<String, Object> data)
	{
		this.data = data;
	}
	public static AjaxResponse getInstance()
	{
		return new AjaxResponse();
	}
}