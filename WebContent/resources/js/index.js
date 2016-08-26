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
	setInterval(getUserInfo, 20000);
	
	
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
             url: "../rest/wi/getWIs/"+0,
             data:"id="+0,
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
                     return "<span><a class='link' data-toggle='modal' data-target='#Modal' onclick='edit("+id+")'><i class='fa fa-pencil-square-o fa-lg' aria-hidden='true'></i></a></span>" +
                        "<span><a class='link' data-toggle='modal' data-target='#Modal' onclick='showLog("+id+")'><i class='fa fa-info fa-lg' aria-hidden='true'></i></a></span>" +
                        "<span><a class='link' onclick='showModal("+id+")'><i class='fa fa-check-square-o fa-lg' aria-hidden='true'></i></a></span>";                
                 }
             }]
     });
	 
	 
	function showLog(id){
		$(".modal-title").html("Work Item Log")
		$(".modal-body").load("wilog/index.html");
	 }
	 
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
	 
	 $("#newWI").on("click", function(){
		 $(".modal-title").html("New Work item")
		 $(".modal-body").load("wi/index.html");
	 })
})