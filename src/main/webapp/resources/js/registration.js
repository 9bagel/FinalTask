	$(document).ready(function(){
		var isLoginValid = false;
		var isPasswordValid = false;
		var isEmailValid = false;
		var isPasswordRepeatValid = false;

		$("#login").keyup(function() {
			isLoginValid = validateInput($("#login"), $("#loginHintInvalid"), "^[A-Za-z0-9]{5,20}$");
		});
		$("#email").keyup(function() {
			isEmailValid = validateInput($("#email"), $("#emailHintInvalid"), "\\w+@\\w+\\.\\w+");
		});
		$("#password").keyup(function() {
			isPasswordValid = validateInput($("#password"), $("#passwordHintInvalid"), "^.{8,15}$");
		});

		$("#passwordRepeat").keyup(function() {
        			isPasswordRepeatValid = validatePasswordRepeat($("#password"), $("#passwordRepeat"), $("#passwordRepeatHintInvalid"));
        		});

		$("#signUpForm").submit(function(event){
			event.preventDefault();
			if (isLoginValid && isPasswordValid && isEmailValid && isPasswordRepeatValid) {
				$("#signUpForm").unbind('submit').submit();
			}
		});
	});