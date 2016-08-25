$(document).ready(function() {
	
	var getUserInfo = function() {
		var cfg = {
			type : "POST",
			url : "../rest/user/getUserInfo",
			success : function(userInfo) {
				$("#emailUser").html(userInfo.username);
			},
			error : function(e) {
				window.location="../index.html";
			}
		};
		ajax.post(cfg);
	}
	getUserInfo();
	setInterval(getUserInfo, 5000);
	
})