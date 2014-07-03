<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${page.pageTitle}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shotcut icon" href="../favicon.ico" type="image/x-icon" />  
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/pageDetail.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/right-bar.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
  </head>
  <body>
  				<!--  head start -->
  				<div class="head">
 			 		<a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
 			 	</div>
  	     		<!--  head end -->
  	     		<!--  nav start -->
  					<div class="nav">
 			 			<ul>
		  	     			<li><a href="#">首页</a></li>
		  	     			<li><a href="#">相册</a></li>
		  	     			<li><a href="#">关于我和她</a></li>
  	     				</ul>
 			 		</div>
 			 	<!--  nav end -->
    	<div class="content">
  	     	<div class="content-main">
  	     	<div class="page-detail">
  	     		<h1 class="title">${page.pageTitle}</h1>
  	     		<h2 class="title">
  	     			分类:&nbsp;
  	     			<c:forEach items="${page.clses}" var = "cls">
  	     				<span>${cls.className}</span>
  	     			</c:forEach>
  	     		</h2>
  	     		<div class="page-d-r">${page.pageContent}</div>
  	     	</div>
  	     		 </div>
  	     		 <div class="sidebar">	
  	 			<!-- 分类start -->
  	     			<dl class="classes">
  	 					<%@ include file="../sp/classes.html" %>
  	 				</dl>
  	     		<!-- 分类end -->
  	     		<!-- 最新文�start -->
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最多阅读</h1>
  	     			<dl>
	  	     			<%@ include file="../sp/most-read.html" %>
  	     			</dl>
  	     		</div>
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">相关评论</h1>
  	     			<dl>
	  	     			<%@ include file="../sp/relative-comments.html" %>
  	     			</dl>
  	     		</div>
  	     		<!--  最多阅读 end -->
  	     	</div>
  	     		<div style="clear:both;"></div>
  	     		 <div class="footer">
		  	     	 		copyright &copy;2014-6-1 <a href="#">关于作者</a>
  	     			 </div>
			</div>
  </body>
</html>
