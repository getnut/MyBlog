$(function (){
	var index = 1;
	$(".manage-content tbody tr").each(function(){
		if(index % 2 == 0){
			$(this).css("background-color","#ccc");
		}
		index++;
	});
	
	
	$(".manage-content tbody tr").hover(function(){
		$(this).css("background-color","#");
	},function(){
		
		
	});
});