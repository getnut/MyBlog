package com.blog.dbutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
public class ToHtml
{
	
	static
	{
		System.out.println("fumc!");
		Properties pro = new Properties();
		InputStream input = ToHtml.class.getClassLoader().getResourceAsStream("velocity.properties");
		try
		{
			pro.load(input);
			pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "E:/apache-tomcat-6.0.35/webapps/MyBlog/vm"); 
			Velocity.init(pro);
		} catch (IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				input.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	public static boolean toHtml(String temName,String toHtmlPath,Map<String,Object> map)
	{
		
		VelocityContext context = new VelocityContext();
		Set<Entry<String,Object>> sets = map.entrySet();
		Iterator<Entry<String,Object>> its = sets.iterator();
		while(its.hasNext())
		{
			Entry<String,Object> entry = its.next();
			context.put(entry.getKey(), entry.getValue());
		}
		//获取模板文件
		Template template = Velocity.getTemplate(temName);
		File file = new File(toHtmlPath);
		if(!file.getParentFile().exists())
		{
			file.getParentFile().mkdirs();
		}
		BufferedWriter writer = null;
		try
		{	
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),Charset.forName("UTF-8")));
			template.merge(context, writer);
		} catch (IOException e)
		{
			e.printStackTrace();
			
			return false;
		}finally
		{
			try
			{
				writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return true;	
	}
}
