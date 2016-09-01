$(document).ready(function() {
	
	toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-center",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
	
	var url = document.URL.split('?')[1];
	if(url == "invalid")
		Command: toastr["error"]("Private area!", "Error");
	else if(url == "incorrect")
		Command: toastr["error"]("Email or password incorrect!", "Error");
	
    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
    function emailValidator(email) {
    	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    	return re.test(email) ? true : false;
    }

    $("#register-submit").on("click", function() {
        var username = $("#register-form #username").val(),
            email = $("#register-form #email").val(),
            password = $("#register-form #password").val(),
            confirm_password = $("#confirm-password").val();

        if (username == "") {
        	Command: toastr["error"]("Insert a username!", "Error");
            return false;
        } else if (!emailValidator(email)) {
        	Command: toastr["error"]("Insert a valid email!", "Error");
            return false;
        } else if (password == "" || password != confirm_password) {
        	Command: toastr["error"]("Fields password and confirm password must match!", "Error");
            return false;
        } else {
            var newUser = new Object();

            newUser.email = email;
            newUser.password = password;
            newUser.confirm_password = confirm_password;
            newUser.username = username;

            cfg = {
                url: "rest/user/add",
                data: newUser,
                success: function(r) {
            		Command: toastr["success"](r, "Success");
            		document.getElementById("register-form").reset();
                },
                error: function(err) {
                	Command: toastr["error"](err.responseText, "Error");
                }
            };

            ajax.post(cfg);
        }
    })

})