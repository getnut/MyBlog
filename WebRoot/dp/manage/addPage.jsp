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
			<link href="${context}/fuwenben/themes/default/css/umeditor.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript"
			src="${context}/resource/scripts/jquery-1.8.3.js"></script>
		<script type="text/javascript"
			src="${context}/resource/scripts/addPage.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="${context}/fuwenben/umeditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="${context}/fuwenben/umeditor.min.js"></script>
		<script type="text/javascript"
			src="${context}/fuwenben/lang/zh-cn/zh-cn.js"></script>

	</head>

	<body>
		<div class="content">
			<div class="head">
				<a class="logo" href="#"><img
						src="${context}/resource/images/logo-.png" alt="张雷的博客" />
				</a>
			</div>

			<div class="add-content">
				<dl>
					<dt class="dl-c">
						<table width="100%">
							<tr>
								<td width="8%">
									<label>
										文章标题:
									</label>
								</td>
								<td>
									<input type="text" name="pageTitle" class="pageTitle" />
								</td>
							</tr>
						</table>
					</dt>
					<dd class="dl-c">
						<!--style给定宽度可以影响编辑器的最终宽度-->
						<script type="text/plain" id="myEditor" style="width:1023px;height:440px;">
    						
						</script>
					</dd>
					<dd class="dl-c"></dd>
				</dl>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('myEditor');
					um.addListener('blur', function() {
						$('#focush2').html('编辑器失去焦点了')
					});
					um.addListener('focus', function() {
						$('#focush2').html('')
					});
					//按钮的操作
					function insertHtml() {
						var value = prompt('插入html代码', '');
						um.execCommand('insertHtml', value)
					}
					function isFocus() {
						alert(um.isFocus())
					}
					function doBlur() {
						um.blur()
					}
					function createEditor() {
						enableBtn();
						um = UM.getEditor('myEditor');
					}
					function getAllHtml() {
						alert(UM.getEditor('myEditor').getAllHtml())
					}
					function getContent() {
						var arr = [];
						arr.push("使用editor.getContent()方法可以获得编辑器的内容");
						arr.push("内容为：");
						arr.push(UM.getEditor('myEditor').getContent());
						alert(arr.join("\n"));
					}
					function getPlainTxt() {
						var arr = [];
						arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
						arr.push("内容为：");
						arr.push(UM.getEditor('myEditor').getPlainTxt());
						alert(arr.join('\n'))
					}
					function setContent(isAppendTo) {
						var arr = [];
						arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
						UM.getEditor('myEditor').setContent('欢迎使用umeditor', isAppendTo);
						alert(arr.join("\n"));
					}
					function setDisabled() {
						UM.getEditor('myEditor').setDisabled('fullscreen');
						disableBtn("enable");
					}
				
					function setEnabled() {
						UM.getEditor('myEditor').setEnabled();
						enableBtn();
					}
				
					function getText() {
						//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
						var range = UM.getEditor('myEditor').selection.getRange();
						range.select();
						var txt = UM.getEditor('myEditor').selection.getText();
						alert(txt)
					}
				
					function getContentTxt() {
						var arr = [];
						arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
						arr.push("编辑器的纯文本内容为：");
						arr.push(UM.getEditor('myEditor').getContentTxt());
						alert(arr.join("\n"));
					}
					function hasContent() {
						var arr = [];
						arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
						arr.push("判断结果为：");
						arr.push(UM.getEditor('myEditor').hasContents());
						alert(arr.join("\n"));
					}
					function setFocus() {
						UM.getEditor('myEditor').focus();
					}
					function deleteEditor() {
						disableBtn();
						UM.getEditor('myEditor').destroy();
					}
					function disableBtn(str) {
						var div = document.getElementById('btns');
						var btns = domUtils.getElementsByTagName(div, "button");
						for ( var i = 0, btn; btn = btns[i++];) {
							if (btn.id == str) {
								domUtils.removeAttributes(btn, [ "disabled" ]);
							} else {
								btn.setAttribute("disabled", "true");
							}
						}
					}
					function enableBtn() {
						var div = document.getElementById('btns');
						var btns = domUtils.getElementsByTagName(div, "button");
						for ( var i = 0, btn; btn = btns[i++];) {
							domUtils.removeAttributes(btn, [ "disabled" ]);
						}
					}
</script>

			</div>
			<div style="clear: both;"></div>
			<div class="footer">
				copyright &copy;2014-6-1
				<a href="#">关于作者</a>
			</div>
		</div>
	</body>
</html>

