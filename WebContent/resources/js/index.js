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

function edit(id) {
    $(".modal-title").html("Edit Work Item");
    var cfg = {
        type: "GET",
        url: "../rest/wi/getWIs/?id=" + id,
        success: function(wi) {
            loadEdit(wi);
        },
        error: function(e) {
            alert(e);
        }
    };
    ajax.post(cfg);
}

function loadEdit(wi) {
    $(".modal-body").load("wi/index.html", function() {
        $("#id").val(wi[0].id);
        $("#selectUser").val(wi[0].user.id);
        $("#wiName").val(wi[0].name);
        $("#eEffort").val(wi[0].estimated_effort);
        $("#wiDesc").val(wi[0].description);
        $("#wiStatus").val(wi[0].status);
        $("#effort").val(wi[0].effort == "00:00:00" ? "" : wi[0].effort);
        $("#dPercentage").val(wi[0].deviation_percentage == "00:00:00" ? "" : wi[0].deviation_percentage);
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


    $("#wiFilter").change(function(){
        showWiWithFilter(this.value);
    })
    
    function showWiWithFilter(status){
    
	    var dataTable = $('#dataTable')
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
	                url: "../rest/wi/getWIs/?id="+0+"&status="+status,
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
	                    return "<span><a class='link' data-toggle='modal' data-target='#Modal' onclick='edit(" + id + ")'><i class='fa fa-pencil-square-o fa-lg' aria-hidden='true'></i></a></span>" +
	                        "<span><a class='link' data-toggle='modal' data-target='#Modal' onclick='showLog(" + id + ")'><i class='fa fa-info fa-lg' aria-hidden='true'></i></a></span>";
	                }
	            }]
	        });
    }
    showWiWithFilter(33);

    $("#newWI").on("click", function() {
        $(".modal-title").html("New Work item")
        $(".modal-body").load("wi/index.html");
    })
})