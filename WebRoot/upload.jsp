<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	
  </head>
  
  <body>
    	<%
    		String url = request.getLocalAddr();
    		System.out.println(url);
    		InputStream input = request.getInputStream();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    		String line = null;
    		if((line = reader.readLine())!= null)
    		{
    			System.out.println(line);
    		}
    	%>
  </body>
</html>
