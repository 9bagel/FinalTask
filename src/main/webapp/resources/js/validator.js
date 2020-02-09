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
	};

	function validatePasswordRepeat(password, passwordRepeat, hintInvalid){

    		if (password.val() != passwordRepeat.val()) {
    			passwordRepeat.removeClass("valid invalid").addClass("invalid");
    			hintInvalid.removeClass("invisible visible").addClass("visible");
    			return false;
    		}else {
    			passwordRepeat.removeClass("valid invalid").addClass("valid");
    			hintInvalid.removeClass("invisible visible").addClass("invisible");
    			return true;
    		}
    	};