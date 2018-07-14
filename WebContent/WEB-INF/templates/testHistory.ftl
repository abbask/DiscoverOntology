<!DOCTYPE html>
<html lang="en">
<head>
  <title>Test History</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/DiscoverOntology/OntologyTest">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>      
      <li><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      <li class="active"><a href="/DiscoverOntology/TestHistory">History</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>Test History</h3>

	<div style="float: right;">
		<form class="form-inline" method="post">
		  <div class="form-group mx-sm-3 mb-2">
		    <label for="systemTest">Number of records:</label>
		    <select class="form-control" id="numberofRows" name="numberofRows">
		    	<option value="5">5</option>
		    	<option value="5">10</option>
		    	<option value="5">15</option>
		    	<option value="5">20</option>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary mb-2">Go</button>
		</form>
	</div>

	<div style="float: left;">
		<#list systemHistorys as systemHistory>
			<div class="panel panel-default">
			  <div class="panel-heading">${systemHistory.mySystemTest.name} - ${systemHistory.date} </div>
			  <div class="panel-body">
			    <#list systemHistory.unitTestHistorys as unitTestHistory>
			    	 <p> name: ${unitTestHistory.myUnitTest.name} - status: ${unitTestHistory.status} - message: ${unitTestHistory.message} </p><br/>
			    	 
			    </#list>
			  </div>
			</div>
		</#list>
	</div>
</div>

</body>
</html>