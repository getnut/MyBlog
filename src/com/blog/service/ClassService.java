package com.blog.service;

import java.util.List;

import com.blog.entity.Classes;

public interface ClassService
{
	public long addClass(Classes cls);
	
	public boolean removeClass(long classId);
	
	public List<Classes> getAllClasses();
}
