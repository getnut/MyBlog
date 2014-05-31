package com.blog.entity;

public class Classes
{
	private long classId;
	
	private String className;
	
	private long count;

	public long getClassId()
	{
		return classId;
	}

	public void setClassId(long classId)
	{
		this.classId = classId;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public long getCount()
	{
		return count;
	}

	public void setCount(long count)
	{
		this.count = count;
	}

	@Override
	public String toString()
	{
		return "Classes [classId=" + classId + ", className=" + className
				+ ", count=" + count + "]";
	}
}
