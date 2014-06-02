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