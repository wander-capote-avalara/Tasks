$(document).ready(function() {
	$(document).ready(function(){
		var cfg = {
				type : "GET",
				url : "../rest/user/getUser/",
				success : function(users) {
					showUsers(users)
				},
				error : function(e) {
					alert(e);
				}
			};
			ajax.post(cfg);
			
			function showUsers(listUser){
				 var html="<select id='selectUser' class='form-control' >";
				 if(listUser == null || listUser.lenght == 0){
					 html += "<option value='0'>No users</option>"
				 }else{
					 for (var x = 0; x < listUser.length; x++) {
						html += "<option value="+listUser[x].id+">"+listUser[x].username+"</option>";
					}
				 }
				 html += "</select>";
				 $("#selectUsers").html(html);
			 }
	})
})