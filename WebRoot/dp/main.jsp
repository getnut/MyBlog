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
  	     		<a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
  	     		<p class="logo-text">姑娘姑娘伢!<a href="#">&nbsp;&nbsp;<img style="width:15px;height:15px;" src="${context}/resource/images/click-heart.png"/></a><span class="count">(3424)</span></p>
  	     	</div>
  	     	<div class="path" style="clear:both;"><a href="#">所有博客</a><em></em><a href="#">所有博客</a></div>
  	     	<div class="blog-list">
  	     		<h1 class="title">博客列表</h1>
  	     		<div class="blog-list-realcontent">
  	     			
  	     			  <!-- 一条博客 -->
		  	     		<div class="blog-item">
		  	     				<dl>
		  	     					<dt class="blog-title"><a href="#">文件上传利器SWFUpload入门简易教程</a></dt>
		  	     					<dd>
		  	     						<p class="blog-content">凡做过网站开发的都应该知道表单file的确鸡肋。Ajax解决了不刷新页面提交表单，但是却没有解决文件上传不刷新页面，当然也有其它技术让不刷新页面而提交文件，该技术主要是利用隐藏的iFrame，较Ajax要麻烦许多，而且其提交方式依然在底层是使用的表单file，这里我们不详谈。而且如果是提交较小的文件，我们能接受，如果提交的文件较大，我们便要忍受很长的等待时间，而浏览器却没有任何提示，我们也没有</p>
		  	     					</dd>
		  	     					<dd class="item-bottom"><span>2012-22-2</span></dd>
		  	     				</dl>
		  	     		</div>
  	     			 <!-- 一条博客 -->
  	     			  <!-- 一条博客 -->
		  	     		<div class="blog-item">
		  	     				<dl>
		  	     					<dt class="blog-title"><a href="#">文件上传利器SWFUpload入门简易教程</a></dt>
		  	     					<dd>
		  	     						<p class="blog-content">凡做过网站开发的都应该知道表单file的确鸡肋。Ajax解决了不刷新页面提交表单，但是却没有解决文件上传不刷新页面，当然也有其它技术让不刷新页面而提交文件，该技术主要是利用隐藏的iFrame，较Ajax要麻烦许多，而且其提交方式依然在底层是使用的表单file，这里我们不详谈。而且如果是提交较小的文件，我们能接受，如果提交的文件较大，我们便要忍受很长的等待时间，而浏览器却没有任何提示，我们也没有</p>
		  	     					</dd>
		  	     					<dd class="item-bottom"><span>2012-22-2</span></dd>
		  	     				</dl>
		  	     		</div>
  	     			 <!-- 一条博客 -->
	  	     		 	 <!-- 分页start -->
		  	     		 <div class="blog-bottom">
		  	     		 	<c:if test="${psr != null}">
		  	     		 		<c:forEach items="${psr.alink}" var="link">
			  	     		 	<c:choose>
				  	     		 	<c:when test="${link.id == psr.currentPage}">
				  	     		 		<a href="${context}/BlogController?action=blog-list&page=${link.id}" class="currentPage">${link.title}</a>
				  	     		 	</c:when>
			  	     		 	<c:otherwise>
			  	     		 		<a href="${context}/BlogController?action=blog-list&page=${link.id}">${link.title}</a>
			  	     		 	</c:otherwise>
			  	     		 	</c:choose>
		  	     		 	</c:forEach>
		  	     		 	</c:if>
		  	     		 </div>
	  	     		 <!-- 分页end -->
  	     		</div>
  	     	</div>
  	     	<div class="sidebar">
  	     		<div class="add-page">
  	     			<ul>
  	     				<li><a href="${context}/dp/manage/add.jsp" class="add">添加新文章</a></li>
  	     				<li><a href="${context}/dp/manage/manage.jsp" class="manage">管理博客</a></li>
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
