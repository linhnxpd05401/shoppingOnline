$(document).ready(function() {
	$(".minusButton").on("click", function(event) {
		event.preventDefault();
		productId = $(this).attr("pid");
		qtyInput = $("#quantity" + productId);
		newQty = parseInt(qtyInput.val()) - 1;
		if(newQty > 0) {
			qtyInput.val(newQty);
		}
	})
	
	$(".plusButton").on("click", function(event) {
		event.preventDefault();
		productId = $(this).attr("pid");
		qtyInput = $("#quantity" + productId);
		newQty = parseInt(qtyInput.val()) + 1;
		if(newQty < 10) {
			qtyInput.val(newQty);
		}
	})
});