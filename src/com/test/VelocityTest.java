package com.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;

@SuppressWarnings("serial")
public class VelocityTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		VelocityEngine ve = new VelocityEngine();
		String realPath = this.getServletContext().getRealPath("/");
		ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, realPath);
		ve.init();
		VelocityContext context = new VelocityContext();
		Template t = ve.getTemplate("main.html");
		File file = new File("E:/1.html");
		if(!file.exists())
		{
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		t.merge(context,writer);
	}
}
