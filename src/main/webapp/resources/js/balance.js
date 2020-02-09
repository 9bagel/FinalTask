	$(document).ready(function(){
		var isBalanceValid = false;
		$("#amount").keyup(function() {
			isBalanceValid = validateInput($(this), $("#balanceHintInvalid"), "^[1-9][0-9]*$");
		});

		$("#balanceForm").submit(function(event){
			event.preventDefault();
			if (isBalanceValid) {
				$("#balanceForm").unbind('submit').submit();
			}
		});
	});