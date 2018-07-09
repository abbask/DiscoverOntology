/**
 * 
 */

$(window).on("load",function(){
	
	$('#runTest').click(function(){	
		
		system_test_id = $('#systemTest').val();
		system_test_text = $('#systemTest :selected').text();
		
		if (system_test_id != 0){
			
		    $.get("RunTest",{
		    	system_test_id : system_test_id
		    },
	
		    function(data, status){
		    	textTable = "<table class='table'>";
		    	textTable += "<thead><tr><th>ID</th><th>name</th><th>Status</th></tr></thead><tbody>";
		    	
		    	$.each(data, function( index, value ) {
		    		
		    		textTable += "<tr><td><span class='testToRun' unitTestID='" + value.ID + "'>" + value.ID + "</span></td><td>" + value.name + "</td><td><span unitTestID=" + value.ID + " class='statusID'><div class='loader'></div></span></td></tr>";
	    		  
	    		}); // $.each(obj, function( index, value ) 
		    	
		    	textTable += "</tbody></table>";
		    	
		    	$("#divTable").empty();
		    	$("#divTable").html(textTable);
		    	
		    	$(".testToRun").each(function(index){
		    		unit_test_id = $( this ).attr('unitTestID');
		    		
			    	$.get("RunUnitTest",{
			    		unit_test_id : unit_test_id
				    },
			
				    function(data, status){
				    	if (status == "success"){
				    		$.each($('.statusID'), function( index, value ) {
					    		if ($(value).attr('unittestid') == data){
					    			$(value).html("<span style='color:green' class='glyphicon glyphicon-ok'></span>");
					    		}
				    		});
				    	}
				    })
				    .fail(function(data) {
				    	console.log("data.responseJSON: " + data.responseJSON);
						$.each($('.statusID'), function( index, value ) {
							if ($(value).attr('unittestid') == data.responseJSON){
								$(value).html("<span style='color:red' class='glyphicon glyphicon-remove'></span>");
							}
						});
				    });
		    	});
		    	

		    	
		    }); // function(data, status){
	    
		} // if (system_test_id != 0){

	}); // #runTest click
	
	
});