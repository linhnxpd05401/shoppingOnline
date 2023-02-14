$(document).ready(function() {

	$("#buttonAddToCart").on("click", function(e) {
		e.preventDefault();
		quantity = $("#quantity" + productId).val();
		url = contextPath + "site/cart/addToCart/" + productId + "/" + quantity;
		$("#staticBackdropLabel").text('Shopping Cart');
		$("#staticBackdrop").modal('show');
		$("#okBtn").attr("href", url);
	});


});

