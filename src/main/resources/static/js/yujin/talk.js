$(function() {
	updateTalk();
	deleteTalk();

	comment();

	deleteCom();
	talkSave();

	/* com_Delete(); */
	// com_update();
	comDelete();
	// newCom();

})

// 게시판 삭제 기능
function deleteTalk() {
	$('#btnDelete').on('click', function() {
		console.log('aaa')
		$(location).attr('href', '/talk/delete/' + $('#talk-code').val())
		alert('삭제가 완료되었습니다.')
	})
}

function updateTalk() {
	$('#saveBtn').on('click', function() {
		alert('수정 되었습니다.')
	})
}

// 게시 목록

function comment() {
	$('#btn_comment').on('click', function() {
		console.log($('#talk-code').val());
		$.ajax({
			url : "/com/comInsert",
			type : "POST",
			data : {
				talkCode : $('#talk-code').val(),
				talkComCode : $('#talkComCode').val(),
				userId : $('#userId').val(),
				talkCom : $('#talk-comment').val()
			}
		}).done(function(data) {
			alert('등록되었습니다');
			location.reload();
		}).fail(function(request, error, status) {
			console.log("code:" + request.status + "\n");
			console.log("msg:" + request.responseText + "\n");
			console.log("error:" + error + "\n");
			alert('comment insert fail');
		})
	})
}

/* 댓글 삭제 */
function deleteCom() {
	$('#btn_Delete').on('click', function() {
		console.log('aaa')
		$(location).attr('href', '/com/delete/' + $('#talkcom-code').val())
		alert('삭제가 완료되었습니다.')
	})
}
// 이미지 등록
function talkSave() {
	$('#btnSave').on('click', function() {
		var file = $('#talk-file')[0].files[0];
		var fileName = '';
		if (file != null)
			fileName = file.name;
		else if (file == null)
			fileName = $('#talk-img').data('img');

		var forData = new FormData();
		forData.append("imgFile", file);
		$.ajax({
			url : '/com/talk/upload',
			type : 'post',

			data : formData,
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
			cache : false
		}).done(function() {
			alert('등록되었습니다')
		}).fail(function() {
			alert('upload fail')
		})
	})
}

function comDelete() {

	$(".comment-delete-btn").click(function(e) {
		var com = $(e.target).attr('value')
		console.log(com)
		if (confirm($(this).attr('value') + " 삭제하시겠습니까?")) {

			$.ajax({
				method : "post",
				url : '/com/deletCom/' + com,
				data : {
					talkComCode : com
				},
				success : function(result) {
					if (result == "success") {
						alert("삭제되었습니다");
						location.reload();
					}
				}
			})

		}

	})

};

// 페이징
$(document).ready(function() {

	var cc = 120;
	for (var i = 50; i < cc; i++) {
		$('#' + i).hide();
	}
});

$('.page').click(function(event) {
	var k = event.target.text;
	console.log(k);
	for (var i = 0; i < $('.page').last().text() * 50; i++) {
		$('#' + i).hide();
	}
	for (var i = 0; i < 50; i++) {
		$('#' + ((parseInt(k) - 1) * 50 + parseInt(i))).show();
		console.log(k + i);
	}
});

// 스크롤
jQuery(document).ready(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			$('#backToTop').fadeIn(500);
		} else {
			$('#backToTop').fadeOut('slow');
		}
	});
	$('#backToTop').click(function(e) {
		e.preventDefault();
		$('html, body').animate({
			scrollTop : 0
		}, 200);
	});
});
