/**
 * 
 */

function chooseFile(fileInput) {
	if (fileInput.files && fileInput.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('.img-display').attr('src', e.target.result);
		}
		reader.readAsDataURL(fileInput.files[0]);
	}
}

$("document").ready(function() {
	$("table #deleteBtn").on("click", function(e) {
		e.preventDefault();
		var href = $(this).attr("href");
		$("#deleteModal #delRef").attr("href", href);
		$("#deleteModal").modal();
	});

});