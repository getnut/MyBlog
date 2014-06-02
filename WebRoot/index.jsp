<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   zhangleidfsdfsfsfs
    <title>33</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type = "text/javascript" src = "${context}/resource/scripts/jquery-1.8.3.js"></script>
	<script type = "text/javascript">
		function Request(name)
		{
			this.name = name;
			this.data = {};
		}
		Request.prototype.addData = function (name,data){
				this.data[name] = data;
		};

		$(function(){
			
			var request = new Request("fuck");
			request.addData("hello1","hello");
			request.addData("hello2","hello2");
			var string = JSON.stringify(request);
			$("#che").click(function (){
				$.ajax({
					method:"get",
					url:"jt",
					data:{
						"data":string,
						"fuck":Math.random()
					},
					success:function(text)
					{
						$("#content").html(text);
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
    <input type = "button" id = "che" value = "click" / >
    <div id = "content"></div>
  </body>
</html>


第二个人添加！
