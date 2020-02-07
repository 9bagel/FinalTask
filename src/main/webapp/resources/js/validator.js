	function validateInput(input, hintInvalid, pattern){

		if (!input.val().match(pattern)) {
			input.removeClass("valid invalid").addClass("invalid");
			hintInvalid.removeClass("invisible visible").addClass("visible");
			return false;
		}else {
			input.removeClass("valid invalid").addClass("valid");
			hintInvalid.removeClass("invisible visible").addClass("invisible");
			return true;
		}
	}
	function allowSubmit(){
		if (isLoginValid && isPasswordValid) {
			$("#buttonSubmit").attr("disabled", false);
		}else{
			$("#buttonSubmit").attr("disabled", true);
		}
	}
	$(document).ready(function () {
		$("#buttonSubmit").attr("disabled", true);
		var isLoginValid = false;
		var isPasswordValid = false;
	});