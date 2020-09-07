<<<<<<< HEAD
$(function(){
	selectedIngr();
	ingrSearch();
	$('#ingrSearchInRefri').on('change', ingrSearch);
	 $('#btnSearch').on('click',ingrSearch);
})

function ingrSearch(){
		    var key = $('#ingrSearchInRefri').val();
		    console.log("key : "+key);
		   
		    var ingrList = $('#ingrList > li');

		    $.each(ingrList, function(index, item){	
		        var title = $(this).find(".tit-area .tit").text();
		        console.log("title :  "+title);
		       
		        if( title.includes(key) ){
		            $(this).show();
		        }else{
		            $(this).hide();
		        }
		    });
}

function selectedIngr(){
	$().on("click","", function(event){
		var ingrName = $(event.target).text();
		
		var target = $(event.target);
		
		if(target.hassClass()){
			target.removeClass();
			
			$('#selectedIngr>?:contains('+ingrName+')').remove();
			$('input[value"'+ingrName+'"]').remove();
		} else{
			$(this).addClass('');
			$("#selectedIngr").append(
					'<p>'+ingrName+'</p>'
					+'<input type="hidden" name="ingrName" value="'+ingrName+'">'
			)
		}
		
	})
}
=======
$(function() {
	//chosung();
	// selectedIngr();
	ingrSearch();
	$('#ingrSearchInRefri').on('change', ingrSearch);
	//$('#btnIngrSearch').on('click', ingrSearch);
	enterkey(); /* 도대체 여기는 왜자꾸 콘솔에서 에러 뜨냐구.. */
})

/* 엔터쳤을 때 페이지이동 없이 처리하기 위해 */
function enterkey() {
	if (event.keyCode == 13) {
		ingrSearch();
		event.preventDefault();
	};
}

function ingrSearch() {
	$('#btnIngrSearch').on('click', function(){
		var key = $('#ingrSearchInRefri').val();
		console.log("key:" + key);
		
		
		$.ajax({
			type : "POST",
			url : "/searched",
			data : {
				ingrName : key
			},
			dataType : 'JSON',
			success : function(data) {
				var result = $('#searchedIngr');
				$.each(data, function(index, value){
					var list = '<p>' + value + '</p>';
					if(index == 0) result.html(list);
					else result.append(list);
				});
				//$('#searchedIngr').text(data);
			},
			error : function() {
				alert("Search Error");
			}
		});
	})

}

//function chosung(){
//	$.ajax({
//		url : "/chosung",
//		type : "GET",
//		success : function(){
//			$('#ingrList').text();
//		},
//		error : function(){
//			alert("Chosung Error");
//		}
//	});
//}

//function selectedIngr() {
//	$()
//			.on(
//					"click",
//					"",
//					function(event) {
//						var ingrName = $(event.target).text();
//
//						var target = $(event.target);
//
//						if (target.hassClass()) {
//							target.removeClass();
//
//							$('#selectedIngr>?:contains(' + ingrName + ')')
//									.remove();
//							$('input[value"' + ingrName + '"]').remove();
//						} else {
//							$(this).addClass('');
//							$("#selectedIngr")
//									.append(
//											'<p>'
//													+ ingrName
//													+ '</p>'
//													+ '<input type="hidden" name="ingrName" value="'
//													+ ingrName + '">')
//						}
//
//					})
//}
>>>>>>> 5f36cad292ac7c36220df6734476ef1d622881c4
