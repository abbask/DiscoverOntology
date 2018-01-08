$(document).ready(function(){

	$( "#examine" ).click(function() {

		var newGraphName ="";
		var newEndpoint = "";
		
		var oldGraphName ="";
		var oldEndpoint = "";		
		
		newGraphName = $('input#newGraphName').val();
		newEndpoint = $('input#newEndpoint').val();
		
		oldGraphName = $('input#oldGraphName').val();
		oldEndpoint = $('input#oldEndpoint').val();

		$.ajax({
			type: "POST",
			url: "Comparison",
			data: {
				action: "examine",
				newGraphName: newGraphName,
				newEndpoint: newEndpoint,
				oldGraphName: oldGraphName,
				oldEndpoint: oldEndpoint,
			},			
			success: function( data ) {

//				$('div.classCount').text("Number of Classes: " + data.classCount);
//				$('div.dataPropertyCount').text("Number of Data Properties: " + data.dataPropertyCount);
//				$('div.objectPropertyCount').text("Number of Object Properties: " + data.objectPropertyCount);
//				$('div.classInstance').text("Number of Individuals: " + data.classInstance);
//				$('div#resultDiv').text('');

			}
		});
	});

});