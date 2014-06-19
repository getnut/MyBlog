package com.test;



import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheDemo {
	public static void main(String[] args) throws InterruptedException {
		CacheManager cm = CacheManager.create("src/ehcache.xml");
		Cache cache = cm.getCache("classes");
		Element e = new Element("key1","hello");
		cache.put(e);
		Thread.sleep(1000);
		System.out.println("sleep 1000"+cache.get("key1"));
		Thread.sleep(2000);
		System.out.println("sleep 2000"+cache.get("key1"));
		Thread.sleep(3000);
		System.out.println("sleep 3000"+cache.get("key1"));
		Thread.sleep(4000);
		System.out.println("sleep 4000"+cache.get("key1"));
		Thread.sleep(2000);
		System.out.println("sleep 2000"+cache.get("key1"));
		Thread.sleep(6000);
		System.out.println("sleep 6000"+cache.get("key1"));
	}
}
