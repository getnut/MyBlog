<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文章管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shotcut icon" href="../favicon.ico" type="image/x-icon" />  
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/manage.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/manage.js"></script>
  </head>
  
  <body>
  		<div class="manage">
  			<ul class="nav-bar">
  				<li><a href="${context}/manage/list/1">文章管理</a></li>
  				<li><a class="current-tab" href="${context}/manage/class/list">分类管理</a></li>
  				<li><a href="${context}/manage/manage-draft.html">草稿箱</a></li>
  				<li><a href="${context}/manage/manage-withdraw.html">回收站</a></li>
  				<li><a href="${context}/manage/page-add.html" target="_blank">写新文章</a></li>
  			</ul>
  			<div class="manage-content">
  				<table width="100%">
  					<tr><th>分类</th><th>数量</th><th>操作</th></tr>
  					<tbody>
	  					<!-- 一条博客 -->
	  	     			  <c:forEach items= "${classes}"  var="cls">
	  	     			  	<tr>
	 	     					<td>${cls.className}</td>
	 	     					<td>34</td>
	 	     					<td><a href="${context}/manage/class/delete/${cls.classId}">删除</a>&nbsp;<a href="#">编辑</a></td>		
			  	     		</tr>
	  	     			  </c:forEach>
  					</tbody>
  				</table>
  			</div>
  		</div>
  </body>
</html>
