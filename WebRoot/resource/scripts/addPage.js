KindEditor.ready(function(K) {
	$(".fabiao").click(function(){
		var title = $(".pageTitle").val();
		var content = editor.html();
		content = content.replace(/(\r\n|\s)/g, " ");//去掉空白字符
		content = K.escape(content);
		var action = $("input[name='action']").val();
		var summary = $(".summary").val();
		var clses = "";
		$("input[name='cls']").each(function(){
			var status = $(this).attr("checked");
			if(status === "checked")
			{
				if(clses == "")
				{
					clses+=$(this).val();
				}else{
					clses = clses+":"+$(this).val();
				}
			}
		});
		alert(clses);
		$.ajax({
			type:"post",
			url:"/MyBlog/manage/page/add",
			data:{
			 pageTitle:title,
			 pageContent:content,
			 cls:clses,
			 summary:summary
			},
			success:function(text){
				alert(text);
			}
		});//ajax end!
	});	
});


