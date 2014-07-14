package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.jmx.jboss.JBoss4RMIRemoteMBeanScheduler;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import com.google.gson.Gson;

public class Test {


	public static void main(String[] args) throws SchedulerException 
	{
		Scheduler  fc = StdSchedulerFactory.getDefaultScheduler();
		
		fc.start();
		JodDeail jd = newJob();
		fc.shutdown();
	}
}


class MyJob implements Job
{

	@Override
	public void execute(JobExecutionContext jc) throws JobExecutionException {
		System.out.println("zhangleibaother!!!!!!!!");
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
