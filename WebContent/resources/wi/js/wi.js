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
	
    var cfg = {
        type: "GET",
        url: "../rest/user/getUser/",
        success: function(users) {
            showUsers(users)
        },
        error: function(e) {
    		Command: toastr["error"](e.responseText, "Error");
        }
    };
    ajax.post(cfg);

    function showUsers(listUser) {
        var html = "<select id='selectUser' class='form-control' required>";
        if (listUser == null || listUser.lenght == 0) {
            html += "<option value='0'>No users</option>"
        } else {
            for (var x = 0; x < listUser.length; x++) {
                html += "<option value=" + listUser[x].id + ">" + listUser[x].username + "</option>";
            }
        }
        html += "</select>";
        $("#selectUsers").html(html);
    }


    $("#newWIForm .btn-primary").on("click", function() {
        var msg="";
	        newWI = {};
	        newWI.user = {};
	        
	        newWI.id = $("#id").val(),
            newWI.user.id = $("#selectUser").val(),
            newWI.name = $("#wiName").val(),
            newWI.estimated_effort = getDates($("#eEffort").val()),
            newWI.description = $("#wiDesc").val(),
            newWI.status = $("#wiStatus").val(),
            newWI.effort = getDates($("#effort").val()),
            newWI.deviation_percentage = getDates($("#dPercentage").val());
	        
        msg = requiredWI(newWI);
        if(msg != "")
        	return false;
        
        var cfg = {
            type: "POST",
            url: "../rest/wi/add/",
            data: newWI,
            success: function(msg) {
        		Command: toastr["success"](msg, "Success");
                $("#newWIForm .btn-default").click();
            },
            error: function(e) {
        		Command: toastr["error"](e.responseText, "Error");
            }
        };
        ajax.post(cfg);
    })

    function getDates(dates) {
        var regex1 = /\d{2}:\d{2}:\d{2}/,
            regex2 = /\d{2}:\d{2}/;
        if (regex1.test(dates))
            return dates;
        else if (regex2.test(dates))
            return dates + ":00";
        else
            return "00:00:00"
    }


    $("#wiStatus").change(function() {
            if (this.value == "2")
                $("#effort").removeAttr("disabled")
            else
                $("#effort").attr("disabled", "disabled");
        })
        
    function requiredWI(wi){
    	var msg="";
    	
    	if(+wi.user == 0)
    		msg+="Select a user;";
    	else if(wi.name == "")
    		msg+="Insert a work item name;";
    	else if(wi.estimated_effort == "00:00:00")
    		msg+="Insert a valid estimated effort;";
    	else if(wi.status == 2 && wi.effort == "00:00:00")
    		msg+="Insert a valid effort time;";
    			
    	return msg;
    }
    
    //@ sourceURL=dynamicScript.js
})