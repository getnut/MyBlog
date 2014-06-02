package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonTest extends HttpServlet {



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
//		Gson gson = new Gson();
//		String jsonStr = request.getParameter("data");
//		Response res = gson.fromJson(jsonStr, Response.class);
//		System.out.println(res.getName());
//		Map<String,String> map = res.getData();
//		Set<Entry<String, String>> set = map.entrySet();
//		Iterator<Entry<String,String>> it  = set.iterator();
//		while(it.hasNext()){
//			Entry<String, String> e = it.next();
//			System.out.println(e.getKey()+"-"+e.getValue());
//			System.out.println(e.getValue().getClass());
//		}
		request.getRequestDispatcher("sp/head.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
