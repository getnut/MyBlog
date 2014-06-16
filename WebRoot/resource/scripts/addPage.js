KindEditor.ready(function(K) {
	$(".fabiao").click(function(){
		var title = $(".pageTitle").val();
		var content = editor.html();
		content = content.replace(/(\r\n|\s)/g, " ");//去掉空白字符
		content = K.escape(content);
		var action = $("input[name='action']").val();
		var clses = "";
		alert($(document.forms[0]).serialize());
		$("input[name='cls']").each(function(){
			var status = $(this).attr("checked");
			if(status === "checked")
			{
				clses+=$(this).val();
			}
		});
		alert(clses);
		var summary = $(".summary").val();
		$.ajax({
			//
			type:"post",
			url:"/MyBlog/blog",
			data:{
			 pageTitle:title,
			 pageContent:content,
			 action:"add",
			 cls:clses,
			 summary:summary
			},
			success:function(text){
				
			}
			//
		});//ajax end!
	});	
});


