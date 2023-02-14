$(document).ready(function() {

	$('input[type="radio"]').change(function() {

		if (this.value == 'easypaisa') {

			$('#easypaisaText').css('display', 'block');
		}
		else {
			$('#easypaisaText').css('display', 'none');
		}

	});
});


