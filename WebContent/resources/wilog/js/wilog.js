$(function() {
	var dataTable = $('#dataTableLogs').DataTable({
		aLengthMenu : [ [ 100 ], [ 100 ] ],
		iDisplayLength : 100,
		sAjaxDataProp : "",
		sPaginationType : "full_numbers",
		processing : true,
		ajax : {
			url : "../rest/wi/getWILogs/" + 0,
			data : "id=" + 0,
			type : "GET"
		},
		select : {
			style : 'os',
			selector : 'td:first-child'
		},
		columns : [ {
			data : "id",
			className : "center"
		}, {
			data : "wi.name",
			className : "center"
		}, {
			data : "formatedDate",
			className : "center"
		}, {
            data: "wi.status",
            className: "center",
            mRender: function(data) {
                return getStatusName(data);
            }
		} ]
	});
	
	 function getStatusName(name){
		 switch(name){
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
})
