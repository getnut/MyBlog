<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加文章</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" rel="stylesheet"
			href="${context}/resource/styles/default.css" />
		<link type="text/css" rel="stylesheet"
			href="${context}/resource/styles/common.css" />
		<link type="text/css" rel="stylesheet"
			href="${context}/resource/styles/addPage.css" />
			
			<link href="${context}/kd/plugins/code/prettify.css"
			type="text/css" rel="stylesheet" />
			
		<script type="text/javascript"
			src="${context}/resource/scripts/jquery-1.8.3.js"></script>
		<script charset="utf-8" src="${context}/kd/kindeditor-min.js"></script>
		<script charset="utf-8" src="${context}/kd/lang/zh_CN.js"></script>
		<script charset="utf-8" src="${context}/kd/plugins/code/prettify.js"></script>
		<script type="text/javascript">

				var editor;
				KindEditor.ready(function(K) {
					editor = K.create('textarea[name="page-content"]', {
						allowFileManager : false,
						resizeType : 1,
						items:[
						        'source', '|','undo', 'redo', '|', 'preview', 'print', 'code', 'cut', 'copy', 'paste',
						        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						        'superscript', 'clearhtml', 'selectall', '|', 'fullscreen', '/',
						        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
						         'insertfile', 'table', 'hr', 'emoticons','anchor', 'link', 'unlink']
					});
					prettyPrint();
				});
				
				</script>
	</head>

	<body>
		<div class="content">
			<div class="head">
				<a class="logo" href="#"><img
						src="${context}/resource/images/logo-.png" alt="张雷的博客" />
				</a>
			</div>

			<div class="add-content">
			<form action = "/MyBlog/BlogController">
				<table width="100%">
					<tr class="dl-c">
						<td width="70px"><label>文章标题:</label></td><td><input type="text" name="pageTitle" class="pageTitle" /></td>
					</tr>
					<tr class="dl-c">
						<td>文章内容:</td><td><textarea name="page-content" style="width:100%;height:400px;visibility:hidden;" id="pc">ddd</textarea></td>
					</tr>
					<tr class="dl-c">
						<td>文章分类:</td><td class="classes">博客<input type="checkbox" name="cls" value="1"/></td>
					</tr>
					<tr class="dl-c">
						<td>文章摘要:</td><td class="page-sum"><textarea name="summary" class="summary"></textarea></td>
					</tr>
					<tr class="dl-c">
						<td>&nbsp;</td><td><div class="add-button"><input type="button" value="发表文章" class="fabiao"/><input type="button" value="保存文章"/><input type="button" value="舍弃"/></div></td>
					</tr>
					</table>
					<input type="hidden" name="action" value="blog-add"/>
				</form>
				<script type="text/javascript" src="${context}/resource/scripts/addPage.js"></script>
			</div>
			<div style="clear: both;"></div>
			<div class="footer">
				copyright &copy;2014-6-1
				<a href="#">关于作者</a>
			</div>
		</div>
	</body>
</html>

