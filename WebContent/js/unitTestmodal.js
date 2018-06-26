	$(window).on("load",function(){
	     $(function(){
	     
	     	//var person = {firstName:"John", lastName:"Doe", age:46};
			//document.getElementById("demo").innerHTML = person["firstName"];
			
			list = [];
			listofvars = [];
	     	
			//modalSave
			$('#modalSave').click(function(){	
			
				if (type == 1){
				
					list.length = 0;
					$('#formValue').val( $('#value').val());
					$('#formAssertType').val( $('#assertType').val());
					
			    	tableHtml = "<table class='table' id='expectedValueTable'><tr><th>Scalar Value</th></tr>";
			    	$('#tableDiv').empty();
					tableHtml += "<tr><td>" +  $('#value').val() + "</td><tr>";
					tableHtml += "</table>";
					
					$('#tableDiv').html(tableHtml);
				
				}
				else{ // when triple
					count = list.length
					
					var triple = {
						"id" : count,
						"subject": $('#subject').val(),
						"predicate": $('#predicate').val(),
						"object" : $('#object').val()
					};
					
					$('#subject').val('');
					$('#predicate').val('');
					$('#object').val('');
					
					list[count] = triple;
					
					//var obj  = JSON.parse(list);
					//console.log(list);
					//console.log("---");
					//console.log(obj);
					
					var myJSON = JSON.stringify(list);
					
					$('#formTripple').val(myJSON);
					
					var text = $('#formTripple').val();
					console.log(text);
					var obj = JSON.parse(text);
					
			    	tableHtml = "<table class='table' id='expectedValueTable'><tr><th>id</th><th>subject</th><th>predicate</th><th>object</th><th></th></tr>";
			    	$('#tableDiv').empty();
			    	$.each( list, function( key, value ) {
					  tableHtml += "<tr><td>" + value.id + "</td><td>" + value.subject + "</td><td>" + value.predicate + "</td><td>" + value.object + "</td><td><span id='removeTriple' idValue='" + value.id + "' class='glyphicon glyphicon-remove' style='color:red'></span><td></tr>";
					});
					tableHtml += "</table>";
					
					$('#tableDiv').html(tableHtml);
				
				}
				
				$('#expectedValueModalBut').addClass('btn-primary').removeClass('btn-danger');
				
				$('#expectedValueModal').modal('toggle');
    			return false;
			
			});	
			
			$(document).on('click','#removeTriple',function(){
				index = $(this).attr('idValue');
				list.splice(index, 1);
				$('#fromTripple').val(list);
					
		    	tableHtml = "<table class='table' id='expectedValueTable'><tr><th>id</th><th>subject</th><th>predicate</th><th>object</th><th></th></tr>";
		    	$('#tableDiv').empty();
		    	$.each( list, function( key, value ) {
				  tableHtml += "<tr><td>" + value.id + "</td><td>" + value.subject + "</td><td>" + value.predicate + "</td><td>" + value.object + "</td><td><span id='removeTriple' idValue='" + value.id + "' class='glyphicon glyphicon-remove' style='color:red'></span><td></tr>";
				});
				tableHtml += "</table>";
				
				$('#tableDiv').html(tableHtml);
				
				if (list.length == 0 ) {
					$('#expectedValueModalBut').addClass('btn-danger').removeClass('btn-primary');
					$('#tableDiv').empty();	
				}
				
			});
			
			$('#expectedValueModal').on('shown.bs.modal', function (e) {
				query = $('#query').val();
				
				//check the query
				startIndex = query.toUpperCase().indexOf("SELECT") + 6 ;
				endIndex = query.toUpperCase().indexOf("WHERE");
				
				term = query.substring(startIndex, endIndex).trim();
				console.log(term);
				counter = 0;
				
				for (var i = 0; i < term.length; i++) {
				  
				  if ( term.charAt(i) == "?" ){
				  	console.log(term.indexOf(" ", i+1));
				  	console.log(i+1);
				  	listofvars[counter] = term.substring(i+1, term.indexOf(" ", i+1));
				  	console.log(listofvars[counter]);
				  	counter++;
				  }
				}
				//console.log(listofvars.length);
				//console.log(listofvars);
				if ( listofvars.length > 1 ) { // not scalar
					//modal-body
					type = 2;
					//HERE
				}
				else{  // scalar
					type =1 ;
					str = '<div class="form-group"><label for="assertType">Assert Type</label><select class="form-control" id="assertType" name="assertType"><option value="1">EQUAL</option><option value="2">LESS</option><option value="3">GREATER</option></select></div>';
					str += '<div class="form-group"><label for="value">Value</label><input type="text" class="form-control" id="value" name="value" placeholder="Enter value"></div>';
					$('.modal-body').html(str);	
					$('#expectedValueModalLabel').val('Expected Value - Scalar');
				}
				
			});
	     });
	});