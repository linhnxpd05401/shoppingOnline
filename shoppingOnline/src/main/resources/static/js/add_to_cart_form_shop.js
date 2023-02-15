$(document).ready(function() {

	$(".buttonAddToCart").on("click", function(e) {
		e.preventDefault();
		var href = $(this).attr("href");
		$("#okBtn").attr("href", href);
		$("#staticBackdrop").modal();
	});


});

