	$(document).ready(function(){
		var isLoginValid = false;
		var isPasswordValid = false;
		$("#login").keyup(function() {
			isLoginValid = validateInput($(this), $("#loginHintInvalid"), "^[A-Za-z0-9]{5,20}$");
		});
		$("#password").keyup(function() {
			isPasswordValid = validateInput($(this), $("#passwordHintInvalid"), "^.{8,15}$");
		});

		$("#loginForm").submit(function(event){
			event.preventDefault();
			if (isLoginValid && isPasswordValid) {
				$("#loginForm").unbind('submit').submit();
			}
		});
	});