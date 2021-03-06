<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>博客</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shotcut icon" href="../favicon.ico" type="image/x-icon" />  
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/main.css" />
	<link type="text/css" rel="stylesheet" href="${context}/resource/styles/right-bar.css" />
	<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${context}/resource/scripts/main.js"></script>
  </head>
  <body class="main">
 			 <div class="head">
 				
 			 </div>
  			<div class="nav">
 			 			<ul>
		  	     			<li><a href="${context}/">首页</a></li>
		  	     			<li><a href="#">相册</a></li>
		  	     			<li><a href="#">关于我和她</a></li>
		  	     		</ul>
		  	     		<div class="search"><input type="text" size="100" placeholder="搜搜"/></div>
 			</div>
 			<!-- 
 			<div id="love-line">
 				<div class="love-timeline">
 					<div class="love-circle">相遇<span class="love-tips">2014-6-1</span></div>
 					<div class="love-circle p2">相识<span class="love-tips">2014-6-1</span></div>
 					<div class="love-circle p3">相知<span class="love-tips">2014-6-1</span></div>
 					<div class="love-circle p4">相恋<span class="love-tips">2014-6-1</span></div>
 					<div class="love-circle p5">相爱<span class="love-tips">2014-6-1</span></div>
 				</div>
 			</div>
 			-->
  	     <div class="content">
  	     	<!-- content-main-start -->
  	     	<div class="content-main">
  	     			<div class="recommend">
  	     				<h1><a href="#">最大的视频秀场，是怎么运行的？</a></h1>
  	     				<p class="re-su">天鸽如何销售、并让用户产生强烈的付费意愿呢？天鸽如何销售、并让用户产生强烈的付费意愿呢？天鸽如何销售、并让用户产生强烈的付费意愿呢？天鸽如何销售、并让用户产生强烈的付费意愿呢？天鸽如何销售、并让用户产生强烈的付费意愿呢？天鸽如何销售、并让用户产生强烈的付费意愿呢？</p>
  	     				<div class="recommend-img"><img src="/MyBlog/news-images/recommend.jpg" width="630"/></div>
  	     				<p class="re-tag">2014-06-30 07:08:00</p>
  	     			</div>
  	     			
  	     			 <!-- 博客的列表项 -->
  	     			  <c:forEach items="${psr.pages}" var = "page">
  	     			  	<div class="blog-item">
		  	     				<table width="100%">
		  	     					<tr>
			  	     					<td class="blog-item-img">
			  	     						<img src="/MyBlog/news-images/hello.jpg" width="220"/>
			  	     					</td>
			  	     					<td>
			  	     						<div style="padding-left:15px;">
					  	     					<h1 class="blog-title"><a href="${context}/pages/${page.pageId}.html" target="_blank">${page.pageTitle}</a></h1>
					  	     					<div class="blog-tips">
					  	     						<span>分类:&nbsp;study</span><span>&nbsp;${page.writeTime}</span>
					  	     					</div>
					  	     					<div>
					  	     						<p class="blog-content">${page.summary}<span>.....</span></p>
					  	     					</div>
				  	     					</div>
			  	     					</td>
		  	     					</tr>
		  	     				</table>
		  	     		 </div>
  	     			  </c:forEach>
	  	     		 	 <!-- 分页start -->
		  	     		 <div class="fenye-bottom">
		  	     		 	<c:if test="${psr != null}">
		  	     		 		<c:forEach items="${psr.alink}" var="link">
			  	     		 	<c:choose>
				  	     		 	<c:when test="${link.id == psr.currentPage}">
				  	     		 		<a href="${context}/page/${link.id}" class="currentPage">${link.title}</a>
				  	     		 	</c:when>
			  	     		 	<c:otherwise>
			  	     		 		<a href="${context}/page/${link.id}">${link.title}</a>
			  	     		 	</c:otherwise>
			  	     		 	</c:choose>
		  	     		 	</c:forEach>
		  	     		 		<span>共${psr.totalPages}页</span>
		  	     		 	</c:if>
		  	     		 </div>
	  	     		 <!-- 分页end -->
  	     		
  	     	</div>
  	     	<!-- content-main-end -->
  	     	<!-- 侧栏 start -->
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
  	     			<h1 class="title">最新评论</h1>
  	     			<dl>
	  	     			<%@ include file="../sp/last-comments.html" %>
  	     			</dl>
  	     		</div>
  	     		<!--  最多阅读 end -->
  	     	</div>
  	     	<!-- 侧栏 end -->
  	     		 <div style="clear:both;"></div>
  	     		 <div class="footer">copyright &copy;2014-6-1 <a href="/MyBlog/manage/">管理博客</a></div>
  	     	     <div class="return-to-top"></div>
		  </div>
  </body>
</html>
