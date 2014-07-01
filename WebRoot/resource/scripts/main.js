$(function(){
	//return to the top
	$(window).bind("scroll",function(){
		$(".return-to-top").show();
	});
	$(".return-to-top").bind("click",function(){
		window.scrollTo(0,0);
	});
});