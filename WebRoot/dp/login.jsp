<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/common.css" />
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
		<link type="text/css" rel="stylesheet" href="${context}/resource/styles/login.css" />
		<script type="text/javascript" src="${context}/resource/scripts/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${context}/resource/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${context}/resource/scripts/login.js"></script>
	</head>
	<body>
		<div id="login_main">
			<!-- 登录的模块start-->
			<div id="login">
				<form action="${context}/UserAction" method="post" id="loginForm">
					<dl>
						<dt>登录</dt>
						<dd><span class="label username"></span><input type="text" name="username" class="txt" id="username" /></dd>
						<dd><span class="label pwd"></span><input type="password" name="pwd" class="txt" id="pwd" /></dd>
						<c:if test="${message != null}">	
							<dd class="tips">
								<c:out value = "${requestScope.message}" />
							</dd>
						</c:if>
						<dd style="padding:20px;">
							<input type="hidden" name="action" value="login"/>
							<input type="submit" value="确定" class="button" id="sub" />
						</dd>
					</dl>
				</form>
			</div>
			<!-- 登录的模块end-->
		</div>

	</body>
</html>
