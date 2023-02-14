$(document).ready(function() {

	$("#addToCart").on("click", function(e) {
		e.preventDefault();
		var href = $(this).attr("href");
		$("#okBtn").attr("href", href);
		$("#staticBackdrop").modal();
	});


});

