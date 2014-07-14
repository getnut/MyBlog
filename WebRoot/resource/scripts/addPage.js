KindEditor.ready(function(K) {
	$(".fabiao").click(function(){
		var title = $(".pageTitle").val();
		var content = editor.html();
		content = content.replace(/(\r\n|\s)/g, " ");//去掉空白字符
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
		$.blockUI({message:"正在添加文章....."}); 
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
				$.unblockUI({ 
	                onUnblock: function(){ 
					 $.growlUI('提示', '添加成功!');	
					} 
	            });
			},
			error:function(){
				$.unblockUI({ 
	                onUnblock: function(){ 
					 $.growlUI('提示', '添加失败!');	
					} 
	            });
			}
		});//ajax end!
	});	
});


