package com.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Test {
	public static void main(String[] args) {
		
	}
	
	public static void test()
	{
		
	}
	
}

class Person{
	private String name;
	
	private int age;
	
	private Address address;
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
class Address {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

class Response{
	private String name;
	
	private Map<String,String> data = new HashMap<String,String>();
	
	public void addData(String key,String o){
		this.data.put(key, o);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
}
