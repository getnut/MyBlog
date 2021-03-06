<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加文章</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/addPage.css" />
	</head>
	<body>
			 <div class="head">
 				 <a class = "logo" href="#"><img src="${context}/resource/images/logo-.png" alt="张雷的博客"/></a>
 			 </div>
  			<div class="nav">
 			 			<ul class="nav-bar">
 			 				<li><a href="${context}/">首页</a></li>
  							<li><a  href="${context}/manage/">文章管理</a></li>
  							<li><a class="current-tab" href="${context}/manage/class/">分类管理</a></li>
  							<li><a href="#">草稿箱</a></li>
  							<li><a href="#">回收站</a></li>
  							<li><a href="#" target="_blank">写新文章</a></li>
  							<li><a href="#" target="_blank">缓存管理</a></li>
  							<li><a href="#" target="_blank">评论管理</a></li>
  							<li><a href="#" target="_blank">博客配置</a></li>
  						</ul>
 			</div>
		<div class="content">
			<div class="add-content">
			<form>
				<table width="100%"  style="border-collapse: collapse;">
					<tr class="dl-c">
						<td class="add-left" width="70px"><label>文章标题:</label></td><td class="add-right"><input type="text" name="pageTitle" class="pageTitle" /></td>
					</tr>
					<tr class="dl-c">
						<td class="add-left">文章内容:</td><td class="add-right"><textarea name="page-content" style="width:100%;height:400px;visibility:hidden;" id="pc"></textarea></td>
					</tr>
					<tr class="dl-c">
						<td class="add-left">文章分类:</td>
						<td class="add-classes add-right">
							<c:forEach items="${classes}" var="cls">
								<span style="padding:5px;"><input type="checkbox" name="cls" value="${cls.classId}"/><label>${cls.className}</label></span>
							</c:forEach>
						</td>
					</tr>
					<tr class="dl-c">
						<td class="add-left">文章摘要:</td><td class="page-sum add-right"><textarea name="summary" class="summary"></textarea></td>
					</tr>
					<tr class="dl-c">
						<td>&nbsp;</td><td><div class="add-button "><input type="button" value="发表文章" class="fabiao"/><input type="button" value="保存文章"/><input type="button" value="舍弃"/></div></td>
					</tr>
					</table>
					<input type="hidden" name="action" value="blog-add"/>
				</form>
				
			</div>
			<div style="clear: both;"></div>
			<div class="footer">copyright &copy;2014-6-1</div>
		</div>
		<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
		<script charset="utf-8" src="${context}/kd/kindeditor-min.js"></script>
		<script charset="utf-8" src="${context}/kd/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${context}/resource/scripts/jquery.blockUI.js"></script>
		<script type="text/javascript">
				var editor;
				KindEditor.ready(function(K) {
					editor = K.create('textarea[name="page-content"]', {
						themeType : 'simple',
						allowFileManager :false,
						resizeType : 1,
						allowImageUpload:true,
						uploadJson : '../../manage/upload',// 相对于当前页面的路径
						items:[
						        'source', '|','undo', 'redo', '|', 'preview', 'print', 'code', 'cut', 'copy', 'paste',
						        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						        'superscript', 'clearhtml', 'selectall', '|', 'fullscreen', '/',
						        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','multiimage',
						         'insertfile', 'table', 'hr', 'emoticons','anchor', 'link', 'unlink']
					});
				});	
		</script>
		<script type="text/javascript" src="${context}/resource/scripts/addPage.js"></script>
	</body>
</html>

