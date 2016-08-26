$(document).ready(function() {
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
				 var html="<select id='selectUser' class='form-control' required>";
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
	
			
			$("#newWIForm .btn-primary").on("click", function(){
				var newWI = {};
					newWI.user = {};	
					newWI.id = $("#id").val(),
					newWI.user.id = $("#selectUser").val(),
					newWI.name = $("#wiName").val(),
					newWI.estimated_effort = $("#eEffort").val() ? $("#eEffort").val()+":00": "00:00:00",
					newWI.description = $("#wiDesc").val(),
					newWI.status = $("#wiStatus").val(),
					newWI.effort = $("#effort").val() ? $("#effort").val()+":00": "00:00:00",
					newWI.deviation_percentage = $("#dPercentage").val() ? $("#dPercentage").val()+":00": "00:00:00";
					
					var cfg = {
							type : "POST",
							url : "../rest/wi/add/",
							data : newWI,
							success : function(msg) {
								alert(msg);
								$("#newWIForm .btn-default").click();
							},
							error : function(e) {
								alert(e);
							}
						};
						ajax.post(cfg); 			
			})
})