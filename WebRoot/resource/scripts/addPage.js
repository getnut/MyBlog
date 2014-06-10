$(".fabiao").click(function(){
	var title = $(".pageTitle").val();
	var content = editor.html();
	var action = $("input[name='action']").val();
	var cls = $("input[name='cls']").val();
	var summary = $(".summary").val();
	$.ajax({
		//
		type:"post",
		url:"/MyBlog/BlogController",
		data:{
		 pageTitle:title,
		 pageContent:content,
		 action:action,
		 cls:cls,
		 summary:summary
		},
		success:function(text){
			
		}
		//
	});//ajax end!
});