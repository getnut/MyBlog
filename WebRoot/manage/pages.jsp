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
	<script type="text/javascript" src="${context}/resource/scripts/manage.js"></script>
  </head>
  <body>
  		<div class="head">
 				 <a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
 			 </div>
  			<div class="nav">
 			 			<ul class="nav-bar">
  							<li><a class="current-tab" href="${context}/manage/">文章管理</a></li>
  							<li><a  href="${context}/manage/class/">分类管理</a></li>
  							<li><a href="#">草稿箱</a></li>
  							<li><a href="#">回收站</a></li>
  							<li><a href="${context}/manage/add.jsp" target="_blank">写新文章</a></li>
  							<li><a href="#" target="_blank">缓存管理</a></li>
  							<li><a href="#" target="_blank">评论管理</a></li>
  							<li><a href="#" target="_blank">博客配置</a></li>
  						</ul>
 			</div>
  		<div class="content">
  			<div class="manage-content">
  				<table width="100%">
  					<tr><th>ID</th><th>标题</th><th>操作</th></tr>
  					<tbody>
	  					<!-- 一条博客 -->
	  	     			  <c:forEach items="${psr.pages}" var="page">
	  	     			  	<tr>
	  	     			  		<td>${page.pageId}</td>
	 	     					<td><a class = "manage-title" href="${context}/page/${page.pageId}.html" target="_blank">${page.pageTitle}(${page.writeTime})</a></td>
	 	     					<td><a href="${context}/manage/page/delete/${page.pageId}">删除</a>&nbsp;<a href="#">编辑</a>&nbsp;<a href="#">分类</a></td>		
			  	     		</tr>
	  	     			  </c:forEach>
  					</tbody>
  				</table>
  				<div class="fenye-bottom">
  						<c:if test="${psr != null}">
		  	     		 		<c:forEach items="${psr.alink}" var="link">
			  	     		 	<c:choose>
				  	     		 	<c:when test="${link.id == psr.currentPage}">
				  	     		 		<a href="${context}/manage/page/list/${link.id}" class="currentPage">${link.title}</a>
				  	     		 	</c:when>
			  	     		 	<c:otherwise>
			  	     		 		<a href="${context}/manage/page/list/${link.id}">${link.title}</a>
			  	     		 	</c:otherwise>
			  	     		 	</c:choose>
		  	     		 	</c:forEach>
		  	     		 		<span>共${psr.totalPages}页</span>
		  	     		 	</c:if>
               </div>
  			</div>
  			<div style="clear:both;"></div>
  	     		 <div class="footer">
		  	     	 		copyright &copy;2014-6-1 <a href="#">关于作者</a>
  	     		 </div>
  		</div>
  </body>
</html>
