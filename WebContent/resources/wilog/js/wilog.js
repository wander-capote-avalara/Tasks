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
			data : "formatedDate",
			className : "center"
		}, {
			data : "changed_status",
			className : "center"
		} ]
	});
})
