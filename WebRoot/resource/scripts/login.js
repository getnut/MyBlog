$(function (){
	center();
	$(window).resize(function(){
		center();
	});
	
	function center()
	{
		var login = $("#login");
		var outerWidth = $(window).width() / 2 - login.width() /2;
		var outerHeight = $(window).height() / 2 - login.height()/2;
		login.css("left",outerWidth).css("top",outerHeight);
	}
	center();
	$(".loginBtn").click(function(){
		var result = $("#loginForm").valid();
		if(result == true)//验证通过
		{
			var username = $("input[name='username']").val();
			var password = $("input[name='pwd']").val();
			$(this).attr("disabled","true").val("正在登录中......").addClass("grey");
			var that = this;
			var dotted = [];
			dotted.push(".");
			dotted.push(". .");
			dotted.push(". . .");
			dotted.push(". . . .");
			var index = 0;
			var inter = setInterval(function(){
				if(index == dotted.length){
					index = 0;
				}
				$(that).val("正在登录中"+ dotted[index]);
				index = index + 1;
			}, 500);
			$.ajax({
				type:"post",
				url:"/MyBlog/user",
				data:{
					username:username,
					password:password,
					action:"login"
				},
				success:function(jsonData){
					var data = $.parseJSON(jsonData);
					if(data["status"] == 1){//登录成功
						var url = data["data"]["url"];
						clearInterval(inter);
						document.location.href = url;
					}else if(data["status"] == 2){//登录失败
						var errorMessage = data["data"]["message"];
						show(errorMessage,$(".login")[0],"show");
					}
				},
				complete:function(){
					$(that).removeAttr("disabled","true").val("登录").removeClass("grey");
					clearInterval(inter);
				}
			});
		}
	});	 
	$("#loginForm").validate({
		rules:{
			username:{
				required:true,
				minlength:6,
				maxlength:20
			},
			pwd:{
				required:true,
				minlength:6,
				maxlength:20
			}
		},
		messages:{
			username:{
				required:"用户名不能为空",
				minlength:"用户名长度最短6位",
				maxlength:"用户名长度最长20位"
			},
			pwd:{
				required:"输入密码不能为空",
				minlength:"密码长度最短为6位",
				maxlength:"密码长度最长为20位"
			}
		},
		errorElement:"div"
	});
});


window.show = function(message,parent,className){
	if(typeof parent == "undefined")
	{
		parent = document.body;
	}
	var div = document.createElement("div");
	parent.appendChild(div);
	div.innerHTML = message;
	div.className = className;
	div.style.position = "absolute";
	div.style.top="0";
	var pW = $(parent).width();
	var dW =$(div).width();
	div.style.left = (pW-dW)/2+"px";
	setTimeout(function(){
		$(div).fadeOut("normal",function(){
			parent.removeChild(div);
		});
	},2000);
	
};



