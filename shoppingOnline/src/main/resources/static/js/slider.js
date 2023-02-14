

$(document).ready(function() {
	$('.slider').bxSlider({
		auto: true,
		autoControls: true,
		stopAutoOnClick: true,
		pager: true
	});
});


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

$(document).ready(function() {
	$('.fancybox').fancybox({
		openEffect: 'none',
		closeEffect: 'none',

		prevEffect: 'none',
		nextEffect: 'none',

		closeBtn: false,

		helpers: {
			title: {
				type: 'inside'
			},
			buttons: {}
		},

		afterLoad: function() {
			this.title = 'Image ' + (this.index + 1) + ' of ' + this.group.length + (this.title ? ' - ' + this.title : '');
		}
	});
});
