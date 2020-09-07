$(function() {
	inter();
	inter_num1();
});

function inter() {
	$('#myCarousel').carousel({
		interval:   4000
	});
}
function inter_num1() {
	$('#myCarousel_num1').carousel({
		interval:   4000
	});
}