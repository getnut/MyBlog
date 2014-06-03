$(function (){
	/*博文列表效果*/
	$(".blog-item").hover(function(){
		$(this).css("background-color","#F1F2F6");
	},function(){
		$(this).css("background-color","#FFF");
	});//end
	var count = 0;
	/*登录窗口弹出*/
	$(".login-button").click(function(){
		var content = $(".login-content");
		var isopen = content.attr("isopen");
		//关闭的状态
		if(null == isopen)
		{
			content.show();
			content.attr("isopen","true");
			center(content);
			$.ajax({
				url:"/MyBlog/jt",
				method:"get",
				success:function(text){
					$(".l-c-r").empty().append(text); //编写包车页面与脚本
				}
			});
		}
	});//end
	//
	
	   $(".login-content .l-c-r").ajaxSend(function(e,xhr,od){
			   $(this).html("<img src='/MyBlog/resource/images/loading.gif'/>正在加载");   
       }); 
	//
	//登录窗口居中
	//登录关闭
	$(".login-content .close").click(function(){
		var content = $(".login-content");
		//起初关闭属性
		content.removeAttr("isopen");
		content.hide();
	});//end
	
	function center(tag){
		var width = tag.outerWidth();
		var height = tag.outerHeight();
		var windowWidth = $(window).width();
		var windowHeight = $(window).height();
		var left = (windowWidth-width)/2;
		var top = (windowHeight-height)/2;
		tag.css("left",left+"px").css("top",top+"px");
	}
});