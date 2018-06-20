<!DOCTYPE html>
<html lang="en">
<head>
  <title>Unit Test New</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script type='text/javascript'>
	$(window).on("load",function(){
	     $(function(){
	     
	     	//var person = {firstName:"John", lastName:"Doe", age:46};
			//document.getElementById("demo").innerHTML = person["firstName"];
			
			list = [];
	     
	     	type = 1;
	         $('#type').change(function(){
	         	
			    type = $(this).val();
			    
			    $('#value').val('');
		    	$('#subject').val('');
		    	$('#predicate').val('');
		    	$('#object').val('');
		    	$('#formValue').val('');
		    	$('#formAssertType').val('');
				$('#formSubject').val( '');
				$('#formPredicate').val('');
				$('#formObject').val('');
			    
			    if (type == 1){
			    	$('.visibility1').css('visibility','visible');
			    	$('.visibility2').css('visibility','hidden');
			    	
			    }
			    else if (type == 2){
			    	$('.visibility1').css('visibility','hidden');
			    	$('.visibility2').css('visibility','visible');
			    }
			    
			});// change
			
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
	     })
	});
	
	</script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li ><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>Unit Tests: New</h3>
	<p>You can add new unit test.</p>

	<div>
		<form method="post" data-toggle="validator" role="form">   
		  <div class="form-group">
		    <label for="name">Name</label>
		    <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" placeholder="Enter name" required>
		    <small id="nameHelp" class="form-text text-muted">Please select name for your system test.</small>
		  </div>
		  <div class="form-group">
		    <label for="query">Query</label>
		    <input type="text" class="form-control" id="query" name="query" aria-describedby="queryHelp" placeholder="Enter SPARQL query" required>
		    <small id="queryHelp" class="form-text text-muted">Please specify the SPARQL query.</small>
		  </div>
		  		  		
		  <div class="form-group">
			<!-- Button trigger modal -->
			<button id="expectedValueModalBut" type="button" class="btn btn-danger" data-toggle="modal" data-target="#expectedValueModal">
			  Expected Values
			</button>
		
			<!-- Modal -->
			<div class="modal fade" id="expectedValueModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="expectedValueModalLabel">Expected Value</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	 <div class="form-check form-group">
					  	<label for="type">Expected Value type</label>
					    <select class="form-control" id="type" name="type">
					    	<option value="1">Scalar</option>
					    	<option value="2">Triple</option>				    		
					    </select>
					  </div>	
					  <div class="form-group visibility1" style="visibility: visible">
					    <label for="assertType">Assert Type</label>
					    <select class="form-control" id="assertType" name="assertType">
					    	<option value="1">EQUAL</option>
					    	<option value="2">LESS</option>
					    	<option value="3">GREATER</option>				    		
					    </select>
					  </div>
					  <div class="form-group visibility1" style="visibility: visible">
					    <label for="value">Value</label>
					    <input type="text" class="form-control" id="value" name="value" placeholder="Enter value">
					  </div>
					  
					  <div class="form-group visibility2" style="visibility: hidden">
					    <label for="subject">Subject</label>
					    <input type="text" class="form-control" id="subject" name="subject" placeholder="Enter subject">
					  </div>
					  <div class="form-group visibility2" style="visibility: hidden">
					    <label for="predicate">Predicate</label>
					    <input type="text" class="form-control" id="predicate" name="predicate" placeholder="Enter predicate">
					  </div>
					  <div class="form-group visibility2" style="visibility: hidden">
					    <label for="object">Object</label>
					    <input type="text" class="form-control" id="object" name="object" placeholder="Enter object">
					  </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button id="modalSave" type="button" class="btn btn-primary">Save changes</button>
			      </div>
			    </div>
			  </div>
			</div>
		  </div>
		  
		  <div id="tableDiv" class="form-group">
		    
		  </div>
		  
		  <div class="form-group">
		  	<input id="formValue" name="formValue" type="hidden" value=""/>		  	
		  </div>
		  <div class="form-group">
		  	<input id="formTripple" name="formTripple" type="hidden" value=""/>		  	
		  </div>
		  <div class="form-group">
		  	<input id="formAssertType" name="formAssertType" type="hidden" value=""/>		  	
		  </div>
		  
		  <div class="form-group">
		    <label for="message">Message</label>
		    <input type="text" class="form-control" id="message" name="message" aria-describedby="messageHelp" placeholder="Enter a message" required>
		    <small id="messageHelp" class="form-text text-muted">Please specify a message.</small>
		  </div>
		  
		  <div class="form-check">
		  	<label for="systemTest">System Test</label>
		    <select class="form-control" id="systemTest" name="systemTest">
		    	<#list systemTests as systemTest>
		    		<option value="${systemTest.ID}">${systemTest.name}</option>
		    	<#else>
		    		
		    	</#list>
		    </select>
		    <small id="systemTestHelp" class="form-text text-muted">Please select system test.</small>
		  </div>
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a class="btn btn-default pull-right" href="/DiscoverOntology/UnitTestList" role="button">Cancel</a>

		</form>
	</div>
</div>

</body>
</html>