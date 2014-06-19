package com.blog.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheData
{
	//
	public  static CacheManager cm = null;
	//classes分类缓存
	public static Cache classCache = null;
	
	//添加到缓存中
	public static void put(Cache cache,Object key,Object value)
	{
		Element element = new Element(key,value);
		cache.put(element);
	}
	public static<T> T get(Cache cache,Object key)
	{
		Element element = cache.get(key);
		if(element == null)
		{
			return null;
		}
		Object o = element.getValue();
		return (T)o;
	}
}
