package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Test {

	
	public static void main(String[] args) 
	{
		
		new Thread(){
			@Override
			public void run() {
				try {
					write();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				try {
					read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		System.out.println("------------------------------------");
	}
	
	
	public static void read() throws IOException
	{
		File file = new File("E:/1.txt");
		BufferedReader bw = new BufferedReader(new FileReader(file));
		int count = 4;
		for(int i = 0;i < count;i++)
		{
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String line = bw.readLine();
			System.out.println("读数据"+line);
		}
		bw.close();
	}
	public static void write() throws IOException
	{
		File file = new File("E:/1.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		int count = 4;
		for(int i = 0;i < count;i++)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bw.write("-----\n");
			System.out.println("写数据");
		}
		bw.close();
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
