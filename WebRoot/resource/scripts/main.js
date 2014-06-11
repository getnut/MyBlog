$(function (){
	/*博文列表效果*/
	$(".blog-item").hover(function(){
		$(this).css("border","2px solid #0099FF");
	},function(){
		$(this).css("border","2px solid #fff").css("border-bottom","2px dotted #ddd");
	});//end
});