<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="${context}/resource/scripts/main.js"></script>
  </head>
  
  <body>
  		<div class="manage">
  			<ul class="nav-bar">
  				<li><a class="current-tab" href="${context}/manage/list/1.html">文章管理</a></li>
  							<li><a href="${context}/manage/class/list">分类管理</a></li>
  							<li><a href="${context}/manage/draft.html">草稿箱</a></li>
  							<li><a href="${context}/manage/withdraw.html">回收站</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">写新文章</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">缓存管理</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">评论管理</a></li>
  							<li><a href="${context}/manage/add.html" target="_blank">博客配置</a></li>
  			</ul>
  			<div class="manage-content">
  				
  			</div>
  		</div>
  </body>
</html>
