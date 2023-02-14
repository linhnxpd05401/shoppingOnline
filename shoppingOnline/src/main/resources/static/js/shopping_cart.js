$(document).ready(function() {

	$(".minusButton").on("click", function(event) {
		event.preventDefault();
		decreaseQuantity($(this));
	})

	$(".plusButton").on("click", function(event) {
		event.preventDefault();
		increaseQuantity($(this));
	})


	$(".link-remove").on("click", function(e) {
		e.preventDefault();
		removeFromCart($(this));
	});

	updateTotal();
});


function decreaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) - 1;
	if (newQty > 0) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function increaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) + 1;
	if (newQty < 10) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function updateQuantity(productId, quantity) {
	url = "/site/cart/update/" + productId + "/" + quantity;

	$.ajax({
		type: "POST",
		url: url
	}).done(function(newSubtotal) {
		updateSubtotal(newSubtotal, productId);
		updateTotal();
	})
}

function updateSubtotal(newSubtotal, productId) {
	$("#subTotal" + productId).text(newSubtotal);
}

function updateTotal() {
	var total = 0.0;

	$(".subTotal").each(function(index, element) {
		total = total + parseFloat(element.innerHTML);
	});

	$("#totalAmount").text("$" + total)
}

function getTotal() {
	var total = 0.0;

	$(".subTotal").each(function(index, element) {
		total = total + parseFloat(element.innerHTML);
	});

	return total;
}

function removeFromCart(link) {
	url = link.attr("href");

	$.ajax({
		type: "POST",
		url: url
	}).done(function(response) {
		$("#staticBackdropLabel").text('Shopping Cart');
		if (response.includes("removed")) {
			$("#staticBackdrop").on("hide.bs.modal", function(e) {
				rowNumber = link.attr("rowNumer");
				removeProduct(rowNumber);
				updateTotal();
			})
		}
		$(".modal-body").text(response);
		$("#okBtn").text("Oke");
		$("#staticBackdrop").modal();
	}
	)
}

function removeProduct(rowNumber) {
	rowId = "row" + rowNumber;
	$("#" + rowId).remove();
}


