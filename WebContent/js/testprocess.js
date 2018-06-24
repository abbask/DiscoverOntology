/**
 * 
 */

$(window).on("load",function(){
	
	$('#runTest').click(function(){	
		
		system_test_id = $('#systemTest').val();
		system_test_text = $('#systemTest :selected').text();
		
		if (system_test_id != 0){
			
		    $.get("RunTest",
	
		    function(data, status){
		    	alert(data);
		    });
	    
		}

	});
	
	
});