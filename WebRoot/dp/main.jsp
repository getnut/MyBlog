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
  	     <div class="content">
  	     	<div class="head">
  	     		<a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
  	     	</div>
  	     	<div class="path" style="clear:both;"><a href="#">所有博客</a><em></em><a href="#">所有博客</a></div>
  	     	<div class="real-content">
  	     		<!--  -->
  	     			<div class="blog-list">
  	     			  <c:forEach items="${psr.pages}" var = "page">
  	     			  	<div class="blog-item">
		  	     				<table width="100%">
		  	     					<tr>
			  	     					<td class="blog-tag"><dl><dd class="time"><label></label><span>${page.writeTime}</span></dd><dd class="tags"><label></label><span>demo,demo2</span></dd><dd class="comment"><label></label><span>阅读次数(2033)</span></dd></dl></td>
			  	     					<td>
			  	     						<div>
					  	     					<h1 class="blog-title"><a href="${context}/blog/detail-${page.pageId}.html" target="_blank">${page.pageTitle}</a></h1>
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
				  	     		 		<a href="${context}/blog/list/${link.id}" class="currentPage">${link.title}</a>
				  	     		 	</c:when>
			  	     		 	<c:otherwise>
			  	     		 		<a href="${context}/blog/list/${link.id}">${link.title}</a>
			  	     		 	</c:otherwise>
			  	     		 	</c:choose>
		  	     		 	</c:forEach>
		  	     		 		<span>共${psr.totalPages}页</span>
		  	     		 	</c:if>
		  	     		 </div>
	  	     		 <!-- 分页end -->
  	     		
  	     	</div>
  	     	<div class="sidebar">
  	     		<!-- 分类start -->
  	 				<%@include file="../sp/classes.html" %>
  	     		<!-- 分类end -->
  	     		<!-- 最新文�start -->
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最多阅读</h1>
  	     			<dl>
	  	     			<dd><a href="#">数据结构(14)</a></dd>
	  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
	  	     			<dd><a href="#">设计模式(7)</a></dd>
  	     			</dl>
  	     		</div>
  	     		<div class="sidebar-common sidebar-common2">
  	     			<h1 class="title">最新评论</h1>
  	     			<dl>
	  	     			<dd><a href="#">数据结构(14)</a></dd>
	  	     			<dd><a href="#">BFS-广度优先搜索(2)</a></dd>
	  	     			<dd><a href="#">设计模式(7)</a></dd>
  	     			</dl>
  	     		</div>
  	     		<!--  最多阅读 end -->
  	     	</div>
  	     		<!--  -->
  	     	</div>
  	     		 <div class="footer">
		  	     	 		copyright &copy;2014-6-1 <a href="#">关于作我的中国行</a>
  	     	     </div>
			</div>
  </body>
</html>
