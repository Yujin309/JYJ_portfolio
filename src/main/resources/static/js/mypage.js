


// 닉네임 체크
var my_nick_btn = 0;
$(function() {

	var pwJ = /^[A-Za-z0-9]{8,16}$/;
	// 비밀번호 정규식
	var nameJ = /^[가-힣]{2,6}$/;
	// 이메일 검사 정규식

	$(function() {

		$("#alert-success1").hide();
		$("#alert-danger1").hide();
		$("input").keyup(function() {
			var userPw = $("#userPw3").val();
			var userPw2 = $("#userPw4").val();
			if (userPw != "" || userPw2 != "") {
				if (userPw == userPw2) {
					$("#alert-success1").show();
					$("#alert-danger1").hide();
					$("#submit").removeAttr("disabled");
				} else {
					$("#alert-success1").hide();
					$("#alert-danger1").show();
					$("#submit").attr("disabled", "disabled");
				}
			}
		});
	});
	
	
	
	// idck 버튼을 클릭했을 때
	$("#my_nick_btn").click(function() {

		// userid 를 param.
		var nickName = $("#userNickName").val();

		$.ajax({
			async : true,
			type : 'POST',
			data : nickName,
			url : "nickNameCheck",
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log(data);
				if (data == 1) {

					alert("닉네임이 존재합니다. 다른 닉네임을 입력해주세요.");
					$("#divInputNick").addClass("has-error")
					$("#divInputNick").removeClass("has-success")
					$("#userNickName").focus();

				} else {
					alert("사용가능한 닉네임입니다.");
					$("#divInputNick").addClass("has-success")
					$("#divInputNick").removeClass("has-error")
					$("#userpwd").focus();
					// 아이디가 중복하지 않으면 id_btn = 1
					my_nick_btn = 0;

				}
			},
			error : function(error) {

				alert("error : " + error);
			}
		});
	});
});




















var acc = document.getElementsByClassName("accordion1");
var i;

for (i = 0; i < acc.length; i++) {
	
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    console.log("acc");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}









//이미지 미리보기
var sel_file;

$(document).ready(function(){
	$('#input_img').on("change", handleImgFileSelect);
});

function handleImgFileSelect(e){
	var files = e.target.files;
	var file = $('#input_img')[0].files[0];
	console.log(files);
	var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장 가능한 이미지 파일이 아닙니다");
				return false;
				
			}
			sel_file = f;
			
			var reader = new FileReader();
			reader.onload = function(e){
				$('#img').attr("src", e.target.result);
				
			}
			reader.readAsDataURL(f);
		});
		
	
}


//날짜 계산
$(document).ready(function(){

	
	Date.prototype.yyyymmdd = function() {
		var yyyy = this.getFullYear().toString();
		var mm = (this.getMonth() + 1).toString();
		var dd = this.getDate().toString();
		return  yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]);
   
	
	}	
	
	
	
	

//답변보기
	$(document).ready(function(){
		
		   $('.qnaAns').each(function(index){

			      var ans = $(this).data('answer');
			      var ansId = $(this).data('id');
			      console.log(ans)
			      
			      if(ans != null){
			         $('#' + ansId).append("답변완료");
			      } else{
			    	 
			      }
			      console.log(index)
			      showAns('#' + ansId)
			  })
		
	});
	
	
	
	
	
	
	
	function showAns(showTarget){
		   $(showTarget).on('click', function(e){
		      var showItem = $(e.target).closest('.myInq-list').find('#showAns');
		      showItem.text($(e.target).data('answer'))
		   })
		};
		
		
		

	$('#chefSearchInMy').on('keydown', function(event){ 
		if(event.keyCode == 13){
			chefSearch();
		    event.preventDefault();
		};
	});
	

   $('#btnChefSearchInMy').on('click', chefSearch);
		   
		   
function chefSearch(){
	   var inputSearchKey = $('#chefSearchInMy').val();
	   
	   $.ajax({
		      type : "POST",
		      url : "/searchMyFollow",
		      data : {followChef : inputSearchKey},
		      success : function(data) {

		    	  
		    	  
		      $('#myFollowResult > table').empty();
			  $('#myFollowResult').append('<table id="myFollowTable"><thead><tr><th></th><th>쉐프</th><th>레시피 수</th></tr></thead><tbody>');
			  
			  var searchedChef = new Array();
			  
			  var nickName, followC;

			  $.each(data, function(index, value){
				  
				  nickName = data[index].nickName;
				  followC = data[index].followC;
				  
				  
				  searchedChef[index] = '<tr id="myFollowMain" style="border-bottom: 1px solid #e6e7e8;"><td><a href="/chefInfo/1/'+nickName+
				  '"><img class="myFollowImg" src="'+ '/img/'+data[index].userPic+'" data-holder-redered="true" style="width: 80px; height: 80px; padding:10px; border-radius: 50px"></a></td><td><a href="/chefInfo/1/'+ nickName + '">' + nickName + '</a></td><td>' + followC
			            
		         });
			  $('#myFollowResult > table > tbody').append(searchedChef);
		      },
		      error : function(){
		         alert("chefSearch Error");
		      }
		   });
   }














$('#scrapedSearchInMy').on('keydown', function(event){
    if(event.keyCode == 13){
       myScrapedSearch();
       event.preventDefault();
    }
 });


   $('#btnScrapedSearchInMy').on('click', myScrapedSearch);
   
   
   function myScrapedSearch(){
	   var inputSearchKey = $('#scrapedSearchInMy').val();
	   
	   $.ajax({
		      type : "POST",
		      url : "/searchMyScrap",
		      data : {rcpTitle : inputSearchKey},
		      success : function(data) {
		    	  
		    	  $('#myScrapedResult').empty();
		    	  $('#myScrapedResult').append('<ul class="cont_list st3 st3_1"></ul>');
				  
				  var searchedTalk = new Array();
				  
				  $.each(data, function(index, value){
					  
					  var scrap_date = (new Date(data[index].scrapDate)).yyyymmdd();
					  
					  searchedTalk[index] = '<li style="width:250px;"><a class="thumbnail" href="/recipe/view?rcpCode='+data[index].rcpCode + '"><div class="thumbs_hb">'
					  +'<img class="myScrapedImg" src="'+ '/img/' + data[index].rcpPic + '"data-holder-rendered="true" style="width:198px;height:120px;"></div>'
					  +'<div class="caption"><h4><span class="jq_elips2">'+data[index].rcpTitle+'</span></h4>'	
					  +'<div class="home_review"> <p class="date">'+ scrap_date+'</p></div></div></a></li>';
				            
			         });
				  $('#myScrapedResult > ul').append(searchedTalk);
			      } ,
		      error : function(){
		         alert("scrapedSearch Error");
		      }
		   });
	   
   }
   
   
   
   
   
   
   
   $('#talkSearchInMy').on('keydown', function(event){
	    if(event.keyCode == 13){
	       talkSearch();
	       event.preventDefault();
	    }
	 });


	   $('#btnTalkSearchInMy').on('click', talkSearch);
	   
	   
	   function talkSearch(){
		   var inputSearchKey = $('#talkSearchInMy').val();
		   
		   $.ajax({
			      type : "POST",
			      url : "/searchMyTalk",
			      data : {talkCont : inputSearchKey},
			      success : function(data) {
			    	  $('#myTalkResult > table').remove();
			    	  $('#myTalkResult').append('<table id="myTalkTable"><thead><tr><th>토크 내용</th><th>날짜</th></tr></thead><tbody>');
				  
				  var searchedTalk = new Array();
				  
				  $.each(data, function(index, value){
					  
					  var talk_date = (new Date(data[index].talkDate)).yyyymmdd();
					  
					  searchedTalk[index] = '<tr id="myTalkMain"><td><div class="story-list"><a href="/talk/detail/'+ data[index].talkCode + '">' + data[index].talkCont + '</a></div></td><td>' + talk_date+'</td>';
				            
			         });
				  $('#myTalkResult > table > tbody').append(searchedTalk);
			      },
			      error : function(){
			         alert("talkSearch Error");
			      }
			   });
		   
	   }
   
   
   
   
   
   
   
   
   
   
   
   
   
	$('#comSearchInMy').on('keydown', function(event){ 
		if(event.keyCode == 13){
			allComSearch();
		    event.preventDefault();
		};
	});
   

   $('#btnComSearchInMy').on('click', allComSearch);
		
		
   
   function allComSearch(){

	   var inputSearchKey = $('#comSearchInMy').val(); 
	   var selectedV = document.getElementById("comSelect").options[document.getElementById("comSelect").selectedIndex].value;
	   
	 
	  
	   var  ctrlUrl="/searchMyAllCom";
	   var  paramData={talkCom : inputSearchKey};
	
	   
	   if(selectedV == "onlyTalk"){
		  
		   ctrlUrl="/searchMyTalkCom";
		 
	   }else if(selectedV == "onlyRcp"){
		   
		   ctrlUrl="/searchMyRcpCom";
		   paramData={rcpCom : inputSearchKey};
		   
	   }
		   
	  $.ajax({
	     type : "POST",
	     url : ctrlUrl,
	     data : paramData,
	     success : function(data) {

	    	 $('#myComResult > table').remove();
	    	 $('#myComResult').append('<table id="myComTable"><thead><tr><th>댓글 내용</th><th>날짜</th></tr></thead><tbody>'); 
	    	 
	    	 var searchedCom = new Array();
	           
	         var talkCode,talkCom;
	         $.each(data, function(index, value){
	        	 
	         	var com_date = (new Date(data[index].talkComDate)).yyyymmdd();
	         	
	            talkCode = data[index].talkCode;
	            talkCom = data[index].talkCom;
	            
	            if(selectedV == "onlyTalk"){
	            	searchedCom[index] = '<tr id="myAllComMain"><td><a href="/talk/detail/' + talkCode + '">'  + talkCom + '</a></td><td>' + com_date;

	            }else if(selectedV == "onlyRcp"){
	     		   com_date =  (new Date(data[index].rcpComDate)).yyyymmdd();
	     		   searchedCom[index] = '<tr id="myAllComMain"><td><a href="/recipe/view?rcpCode=' + data[index].rcpCode + '">'  + data[index].rcpCom + '</a></td><td>' + com_date;
	     	   
	            } else{
	            	if(talkCode.startsWith("T")){
	            		searchedCom[index] = '<tr id="myAllComMain"><td><a href="/talk/detail/' + talkCode + '">'  + talkCom + '</a></td><td>' + com_date;
	            		}else if(talkCode.startsWith("R")){
	            			searchedCom[index] = '<tr id="myAllComMain"><td><a href="/recipe/view?rcpCode=' + talkCode + '">'  + talkCom + '</a></td><td>' + com_date;
	            			} 
	            	}
	            }); 
	        
	         $('#myComResult > table > tbody').append(searchedCom);
	     },//success
	     error : function(){
	        alert("searchedTalkCom Error");
	         }
	    });
   };

   
   
   
   
 
function deleteMyRcp(){
	var button = document.getElementsByName('deleteMyRcpBtn');
	console.log(button);
	var key = a.value;
	console.log("key : " + key);
}





/*$('.myRecipeBtn').on('click', function(){
	window.location.reload();
	$('#mypageMain').load("/mypage/myRecipeIng");
	console.log("myRecipeIng");
});*/



/*$('.myFrequentBtn').on('click', function(){
   $('#mypageMain').load("/mypage/myFollow");
   console.log("myFrequentBtn");
});*/



/*$('.myActivityBtn').on('click', function(){
	$('#mypageMain').load("/mypage/myTalk");
	console.log("myActivityBtn");
});*/


/*$('.myInquiryBtn').on('click', function(){
	$('#mypageMain').load("/mypage/myInquiry");
	console.log("myInquiryBtn");
});*/


/*$('.myComBtn').on('click', function(){
	$('#mypageMain').load("/mypage/myCom");
	console.log("myComBtn");
});*/



/*$('.myInquiryList').on('click', function(){
	$('#mypageMain').load("/mypage/myInquiryList");
	console.log("myInquiryList");
});*/


/*$('.myWrittenBtn').on('click', function(){
	$('#mypageMain').load("/mypage/myWritten");
	console.log("myWritten");
});*/


/*$('.myScrapedBtn').on('click', function(){
	$('#mypageMain').load("/mypage/myScraped");
	console.log("myScraped");
});*/




$(document).ready(function(){
	
	 $('#sendInq').click(function(){
	      
	      var myInqTitle = $('#qnaTitle').val();
	      var myInqCont = $('#qnaCont').val();
	      
	      if(myInqTitle && myInqCont != null){
	    	  alert("정상적으로 접수되었습니다");
	    	  return true
	      } else{
	    	  alert("제목과 내용 모두 입력해주세요");
	    	  return false
	      }
	   });
	
})



});

