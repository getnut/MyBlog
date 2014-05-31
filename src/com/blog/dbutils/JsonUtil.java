package com.blog.dbutils;

import com.google.gson.Gson;
public class JsonUtil
{
	
    //将对象转化为json字符串
    public static String toJson(Object data)
    {
    	Gson json = new Gson();
		return json.toJson(data);
    }
    
    //将json字符串转化为对象
    public static <T>  T fromJson(String jsonString,Class<T> classType)
    {
    	Gson gson = new Gson();
    	return gson.fromJson(jsonString, classType);
    }
}
