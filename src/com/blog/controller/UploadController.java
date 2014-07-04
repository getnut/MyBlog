package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.fileUpload(request, response);

	}

	private void fileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException
	
	{
		String context = this.getServletContext().getContextPath();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DiskFileItemFactory df = new DiskFileItemFactory();//磁盘对象
		df.setRepository(new File("d:/a")); //设置临时目录
		df.setSizeThreshold(1024*400); //8k的缓冲区,文件大于8K则保存到临时目录
		ServletFileUpload upload = new ServletFileUpload(df);//声明解析request的对象
		upload.setHeaderEncoding("UTF-8"); //处理文件名中文
		upload.setFileSizeMax(1024 * 1024 * 2);// 设置每个文件最大为2M
		upload.setSizeMax(1024 * 1024 * 10);// 一共最多能上传10M
		String path = getServletContext().getRealPath("/imgs");//获取文件要保存的目录
		File dir = new File(path);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		try {
		List<FileItem> list = upload.parseRequest(request);// 解析
		for (FileItem item: list) {
			if (item.isFormField()) {
				String ds = item.getString("UTF-8");//处理中文
				System.err.println("说明是:" + ds);
			} else {
				String imgName = item.getName();
				System.out.println("imgNam="+imgName);
				imgName = imgName.substring(imgName.lastIndexOf("\\") + 1);//解析文件名
				item.write(new File(path + "/" + imgName));
				out.write(jsonStr("0",context+"/imgs/"+imgName));
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			out.write(jsonStr("1","上传失败"));
		}
		
	}
	
	private String jsonStr(String errorCode,String url)
	{
		String json = "{\"error\" : #code,\"url\" : \"#file\"}";
		json = json.replace("#code", errorCode);
		json = json.replace("#file",url);
		return json;
	}
}
