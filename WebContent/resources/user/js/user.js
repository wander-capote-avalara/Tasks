$(document).ready(function() {
	
	function emailValidator(email) {
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1
				|| email.indexOf("@") == 0
				|| email.lastIndexOf(".") + 1 == email.length
				|| (email.indexOf("@") + 1 == email.indexOf("."))) {
			return false;
		}
		return true;
	}	
	
	$("#register-submit").on("click", function(){		
		var email = $("#register-form #email").val(),
			password = $("#register-form #password").val(),
			confirm_password = $("#confirm-password").val();
		
		if(email == "" || !emailValidator){
			alert("Email should be real!");
			return false;
		}else if(password == "" || password != confirm_password){
			alert("Fields password and confirm password may match!");
			return false;
		}else{
			alert("hellyeah");
			
			var newUser = {};
			
			newUser.email = email;
			newUser.password = password;
			newUser.confirm_password = confirm_password;
			
			$.ajax({
				type:"POST",
				url:"rest/user/add",
				data:newUser
			}).done(function(r){
				alert(r);
			})
			
		}
	})
	
})