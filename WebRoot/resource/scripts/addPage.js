KindEditor.ready(function(K) {
	$(".fabiao").click(function(){
		var title = $(".pageTitle").val();
		var content = editor.html();
		content = content.replace(/(\r\n|\s)/g, " ");//去掉空白字符
		content = K.escape(content);
		var action = $("input[name='action']").val();
		var cls = $("input[name='cls']").val();
		var summary = $(".summary").val();
		$.ajax({
			//
			type:"post",
			url:"/MyBlog/blog",
			data:{
			 pageTitle:title,
			 pageContent:content,
			 action:"add",
			 cls:cls,
			 summary:summary
			},
			success:function(text){
				
			}
			//
		});//ajax end!
	});	
});


