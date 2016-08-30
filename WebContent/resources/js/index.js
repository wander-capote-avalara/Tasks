function getStatusName(name) {
    switch (name) {
        case 0:
            return "To do";
        case 1:
            return "In progress";
        case 2:
            return "Done";
        case 3:
            return "Stopped";
        default:
            return "Something went wrong";
    }
}

function doneWi(wi){
	 $(".modal-title").html("Confirm work item")
	 $(".modal-body").load("wi/confirm.html", function() {
	        $("#id").val(wi[0].id);
	        $("#selectUser").val(wi[0].user.id);
	        $("#wiName").val(wi[0].name);
	        $("#eEffort").val(wi[0].estimated_effort);
	        $("#wiDesc").val(wi[0].description);
	        $("#effort").val(wi[0].effort == "00:00:00" ? "" : wi[0].effort);
	        $("#dPercentage").val(wi[0].deviation_percentage);
	 })
}

function showLog(id) {
    $(".modal-title").html("Work Item Log")
    $(".modal-body").load("wilog/index.html", function() {
        var dataTable = $('#dataTableLogs').DataTable({
            aLengthMenu: [
                [100],
                [100]
            ],
            iDisplayLength: 100,
            sAjaxDataProp: "",
            sPaginationType: "full_numbers",
            processing: true,
            ajax: {
                url: "../rest/wi/getWILogs/" + id,
                data: "id=" + id,
                type: "GET"
            },
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
            columns: [{
                data: "id",
                className: "center"
            }, {
                data: "wi.name",
                className: "center"
            }, {
                data: "formatedDate",
                className: "center"
            }, {
                data: "changed_status",
                className: "center",
                mRender: function(data) {
                    return getStatusName(data);
                }
            }]
        });
    });
}

function edit(id, isEdit) {
	alert(id);
    var cfg = {
        type: "GET",
        url: "../rest/wi/getWIs/?id="+id+"&status="+4+"&showAll="+false,
        success: function(wi) {
           isEdit ? loadEdit(wi) : doneWi(wi);
        },
        error: function(e) {
            alert(e.responseText);
        }
    };
    ajax.post(cfg);
}

function loadEdit(wi) {
    $(".modal-title").html("Edit Work Item");
    $(".modal-body").load("wi/index.html", function() {
        $("#id").val(wi[0].id);
        $("#selectUser").val(wi[0].user.id);
        $("#wiName").val(wi[0].name);
        $("#eEffort").val(wi[0].estimated_effort);
        $("#wiDesc").val(wi[0].description);
        $("#wiStatus").val(wi[0].status);
        $("#effort").val(wi[0].effort == "00:00:00" ? "" : wi[0].effort);
        $("#dPercentage").val(wi[0].deviation_percentage);
    });
}

$(document).ready(function() {

    var getUserInfo = function() {
        var cfg = {
            type: "POST",
            url: "../rest/user/getUserInfo",
            success: function(userInfo) {
                $("#emailUser").html(userInfo.username);
            },
            error: function(e) {
                window.location = "../index.html";
            }
        };
        ajax.post(cfg);
    }
    getUserInfo();
    setInterval(getUserInfo, 20000);
    
    $("#allWI").on("click",function(){
    	dataTable.ajax.url("../rest/wi/getWIs/?id="+0+"&status="+4+"&showAll="+true);
        dataTable.ajax.reload(null, true);
    	$("#wiFilter").attr("disabled", "disabled");
    	$(".more").addClass('hidden');
     	$(".moreAll").removeClass('hidden');
    })
    
    $("#aTM").on("click", function(){
    	 dataTable.ajax.url("../rest/wi/getWIs/?id="+0+"&status="+0+"&showAll="+false);
         dataTable.ajax.reload(null, true);
     	$("#wiFilter").removeAttr("disabled");
     	$(".more").removeClass('hidden');
    	$(".moreAll").addClass('hidden');
    })

    $("#wiFilter").change(function(){
        dataTable.ajax.url("../rest/wi/getWIs/?id="+0+"&status="+this.value+"&showAll="+false);
        dataTable.ajax.reload(null, true);
    })
    
	    window.dataTable = $('#dataTable')
	        .DataTable({
	            aLengthMenu: [
	                [10, 20, 100],
	                [10, 20, 100]
	            ],
	            iDisplayLength: 10,
	            sAjaxDataProp: "",
	            sPaginationType: "full_numbers",
	            processing: true,
	            ajax: {
	                url: "../rest/wi/getWIs/?id="+0+"&status="+0+"&showAll="+false,
	                type: "GET"
	            },
	            select: {
	                style: 'os',
	                selector: 'td:first-child'
	            },
	            columns: [{
	                data: "id",
	                className: "center"
	            }, {
	                data: "user.username",
	                className: "center"
	            }, {
	                data: "name",
	                className: "center"
	            }, {
	                data: "estimated_effort",
	                className: "center",
	            }, {
	                data: "description",
	                className: "center",
	            }, {
	                data: "status",
	                className: "center",
	                mRender: function(data) {
	                    return getStatusName(data);
	                }
	            }, {
	                data: "effort",
	                className: "center",
	            }, {
	                data: "deviation_percentage",
	                className: "center",
	            }, {
	                data: "id",
	                className: "center",
	                bSortable: false,
	                mRender: function(id) {
	                    return "<span class='more'><a class='link' title='Edit work item' data-toggle='modal' data-target='#Modal' onclick='edit("+id+","+true+")'><i class='fa fa-pencil-square-o fa-lg' aria-hidden='true'></i></a></span>" +
	                        "<span class='more'><a class='link'  title='Show work item log' data-toggle='modal' data-target='#Modal' onclick='showLog("+id+")'><i class='fa fa-info fa-lg' aria-hidden='true'></i></a></span>"+
	                        "<span class='more'><a class='link'  title='Confirm work item' data-toggle='modal' data-target='#Modal' onclick='edit("+id+","+false+")'><i class='fa fa-check-square-o fa-lg' aria-hidden='true'></i></a></span>"+
	                        "<span class='hidden moreAll'><i class='fa fa-ban fa-lg' title='Not editable!' aria-hidden='true'></i></span>";
	                }
	            }]
	        });

    $("#newWI").on("click", function() {
        $(".modal-title").html("New Work item")
        $(".modal-body").load("wi/index.html");
    })
})