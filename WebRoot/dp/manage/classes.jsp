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
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/manage.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/manage.js"></script>
  </head>
  
  <body>
  		<div class="head">
 				 <a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
 			 </div>
  			<div class="nav">
 			 			<ul class="nav-bar">
  							<li><a class="current-tab" href="${context}/manage/list-1.html">文章管理</a></li>
  							<li><a href="${context}/manage/class#list">分类管理</a></li>
  							<li><a href="${context}/manage/draft.html">草稿箱</a></li>
  							<li><a href="${context}/manage/withdraw.html">回收站</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">写新文章</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">缓存管理</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">评论管理</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">博客配置</a></li>
  						</ul>
 			</div>
  		<div class="content">
  			<div class="manage-content">
  				<table width="100%">
  					<tr><th>分类</th><th>数量</th><th>操作</th></tr>
  					<tbody>
	  					<!-- 一条博客 -->
	  	     			  <c:forEach items= "${classes}"  var="cls">
	  	     			  	<tr>
	 	     					<td>${cls.className}</td>
	 	     					<td>34</td>
	 	     					<td><a href="${context}/manage/class#delete#${cls.classId}">删除</a>&nbsp;<a href="#">编辑</a></td>		
			  	     		</tr>
	  	     			  </c:forEach>
  					</tbody>
  				</table>
  			</div>
  		</div>
  </body>
</html>
