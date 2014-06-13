<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>博客</title>
    <link rel="shotcut icon" href="../favicon.ico" type="image/x-icon" />  
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/main.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/main.js"></script>
  </head>
  <body class="main">
  	     <div class="content">
  	     	<div class="head">
  	     		<a class = "logo" href="#"><img src="${context}/resource/images/logo-.png"/></a>
  	     	</div>
  	     	<div class="path" style="clear:both;"><a href="#">所有博可</a><em></em><a href="#">所有博壳</a></div>
  	     	<div class="blog-list">
  	     		<h1 class="title">博客列表</h1>
  	     		<div class="blog-list-realcontent">
  	     			
  	     			  <!--start -->
  	     			  <c:forEach items="${psr.pages}" var = "page">
  	     			  	<div class="blog-item">
		  	     				<dl>
		  	     					<dt class="blog-title"><a href="${context}/blog/detail/${page.pageId}" target="_blank">${page.pageTitle}</a></dt>
		  	     					<dd>
		  	     						<p class="blog-content">${page.summary}</p>
		  	     					</dd>
		  	     					<dd class="item-bottom"><span>${page.writeTime}</span></dd>
		  	     				</dl>
		  	     		</div>
  	     			  </c:forEach>
		  	     		
  	     			 <!-- 一条博end-->
  	     			  
	  	     		 	 <!-- 分页start -->
		  	     		 <div class="fenye-bottom">
		  	     		 	<c:if test="${psr != null}">
		  	     		 	   
		  	     		 		<c:forEach items="${psr.alink}" var="link">
			  	     		 	<c:choose>
				  	     		 	<c:when test="${link.id == psr.currentPage}">
				  	     		 		<a href="${context}/blog/list/${link.id}" class="currentPage">${link.title}</a>
				  	     		 	</c:when>
			  	     		 	<c:otherwise>
			  	     		 		<a href="${context}/blog/list/${link.id}">${link.title}</a>
			  	     		 	</c:otherwise>
			  	     		 	</c:choose>
		  	     		 	</c:forEach>
		  	     		 		<span>一共${psr.totalPages}页</span>
		  	     		 	</c:if>
		  	     		 </div>
	  	     		 <!-- 分页end -->
  	     		</div>
  	     	</div>
  	     	<div class="sidebar">
  	     		<div class="add-page">
  	     			<ul>
  	     				<li><a href="${context}/manage/page-add.html"  target="_blank" class="add">添加新章</a></li>
  	     				<li><a href="${context}/manage/list/1"  target="_blank"  class="manage">管理博客</a></li>
  	     			</ul>
  	     		</div>
  	 			<!-- 分类start -->
  	     		<dl class="sidebar-common classes">
  	     			<dt>分类</dt>
  	     			<dd><a href="#">数据结构(14)</a></dd>
  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
  	     		</dl>
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最新文章</h1>
  	     			<dl>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     			</dl>
  	     		</div>
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最新评</h1>
  	     			<dl>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     				<dd><a href="#">文件上传利器SWFUpload入门简易</a></dd>
  	     			</dl>
  	     		</div>
  	     		<!--  最多阅� end -->
  	     	</div>
  	     		 <div style="clear:both;"></div>
  	     		 <div class="footer">
		  	     	 		copyright &copy;2014-6-1 <a href="#">关于作</a>
  	     			 </div>
			</div>
  	     	
  	     	 
  </body>
</html>
