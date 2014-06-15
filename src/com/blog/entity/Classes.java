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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (classId ^ (classId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classes other = (Classes) obj;
		if (classId != other.classId)
			return false;
		return true;
	}
}
