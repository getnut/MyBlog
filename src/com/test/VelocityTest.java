package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


@SuppressWarnings("serial")
public class VelocityTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String realPath = this.getServletContext().getRealPath("/");
		PrintWriter out = response.getWriter();
		Properties pro = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("velocity.properties");
		pro.load(input);
		pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, realPath+"vm"); 
		input.close();
		Velocity.init(pro);
		System.out.println(realPath+"vm");
		VelocityContext context = new VelocityContext();
		context.put("title", "HelloWorld");
		List<Student> students = new ArrayList<Student>();
		for(int i = 0;i < 20;i++)
		{
			Student student = new Student();
			student.setName("student"+i);
			students.add(student);
		}
		context.put("author", "arthinking");
		context.put("students", students);
		
		//获取模板文件
		Template template = Velocity.getTemplate("main.vm");
		
		File file = new File(realPath+"main.html");
		if(!file.exists())
		{
			file.createNewFile();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		template.merge(context, writer);
		writer.close();
		request.getRequestDispatcher("main.html").forward(request, response);
	}
}


