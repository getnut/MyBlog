<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>33</title>
    <link type="text/css" rel="stylesheet" href="${context}/resource/styles/default.css" />
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
					 cache:false,
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
    <input type = "button" id = "che" value = "click" />
    <div id = "content"></div>
  </body>
</html>
come from master!