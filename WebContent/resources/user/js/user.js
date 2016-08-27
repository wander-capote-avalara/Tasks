$(document).ready(function() {

    function emailValidator(email) {
        if (email.indexOf("@") == -1 || email.indexOf(".") == -1 ||
            email.indexOf("@") == 0 ||
            email.lastIndexOf(".") + 1 == email.length ||
            (email.indexOf("@") + 1 == email.indexOf("."))) {
            return false;
        }
        return true;
    }

    $("#register-submit").on("click", function() {
        var username = $("#register-form #username").val(),
            email = $("#register-form #email").val(),
            password = $("#register-form #password").val(),
            confirm_password = $("#confirm-password").val();

        if (username == "") {
            alert("Insert a username!");
            return false;
        } else if (email == "" || !emailValidator) {
            alert("Email should be real!");
            return false;
        } else if (password == "" || password != confirm_password) {
            alert("Fields password and confirm password may match!");
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
                    alert(r);
                },
                error: function(err) {
                    alert(err.responseText);
                }
            };

            ajax.post(cfg);
        }
    })

})