/**
 * 
 */
$(function() {
	$("#slider-range").slider({
		range: true,
		min: 0,
		max: 10000,
		values: [1000, 3000],
		slide: function(event, ui) {
			$("#amount").val("Rs." + ui.values[0] + " - Rs." + ui.values[1]);
		}
	});
	$("#amount").val("Rs." + $("#slider-range").slider("values", 0) +
		" - Rs." + $("#slider-range").slider("values", 1));
});