<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'showPageDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shotcut icon" href="../favicon.ico" type="image/x-icon" />  
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/pageDetail.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
  </head>
  
  <body>
    	<div class="content">
  	     	<div class="head">
  	     		<a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
  	     	</div>
  	     	<div class="page-detail">
  	     		<h1 class="title">${page.pageTitle}</h1>
  	     		<h2 class="title">分类:&nbsp;<span>${page.clss.className}</span><span>suanfa</span></h2>
  	     		<div class="page-d-r">${page.pageContent}</div>
  	     	</div>
  	     	<div class="sidebar">
  	     		<div class="add-page">
  	     			<ul>
  	     			<li><a href="${context}/manage/addPage.html"  target="_blank" class="add">添加新文章</a></li>
  	     				<li><a href="${context}/manage/manage.html"  target="_blank"  class="manage">管理博客</a></li>
  	     			</ul>
  	     		</div>
  	 			<!-- 分类start -->
  	     		<dl class="sidebar-common classes">
  	     			<dt>分类</dt>
  	     			<dd><a href="#">数据结构(14)</a></dd>
  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
  	     			<dd><a href="#">设计模式(7)</a></dd>
  	     			<dd><a href="#">计算机网络(2)</a></dd>
  	     			<dd><a href="#">数据结构(14)</a></dd>
  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
  	     			<dd><a href="#">设计模式(7)</a></dd>
  	     			<dd><a href="#">计算机网络(2)</a></dd>
  	     			<dd><a href="#">数据结构(14)</a></dd>
  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
  	     			<dd><a href="#">设计模式(7)</a></dd>
  	     			<dd><a href="#">计算机网络(2)</a></dd>
  	     		</dl>
  	     		<!-- 分类end -->
  	     		<!--  最新文章 start -->
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最新文章</h1>
  	     			<dl>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     			</dl>
  	     		</div>
  	     		<!--  最新文章 end -->
  	     		<!--  最多阅读 start -->
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最新评论</h1>
  	     			<dl>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易教程</a></dd>
  	     			</dl>
  	     		</div>
  	     		<!--  最多阅读  end -->
  	     	</div>
  	     		 <div style="clear:both;"></div>
  	     		 <div class="footer">
		  	     	 		copyright &copy;2014-6-1 <a href="#">关于作者</a>
  	     			 </div>
			</div>
  </body>
</html>
