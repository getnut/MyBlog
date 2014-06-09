package com.blog.dbutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public static void main(String[] args) {
		String number = null;
		System.out.println(Validation.CanChangeToDigit(number));
	}
	
	public static boolean CanChangeToDigit(String number)
	{
		if(number == null) return false;
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	public static String NotNull(String source,String def)
	{
		if(source == null)
		{
			source = def;
		}
		return source;
	}
}
