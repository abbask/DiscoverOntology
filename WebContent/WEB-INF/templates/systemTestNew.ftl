<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
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
      <a class="navbar-brand" href="#">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li><a href="/DiscoverOntology/SuiteTestList">Suite Test List</a></li>
      <li><a href="/DiscoverOntology/UnitTestList">Unit Test List</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>System Tests: New</h3>
	<p>You can add new system test.</p>

	<div>
		<form method="post" data-toggle="validator" role="form">   
		  <div class="form-group">
		    <label for="name">Name</label>
		    <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" placeholder="Enter name" required>
		    <small id="nameHelp" class="form-text text-muted">Please select name for your system test.</small>
		  </div>
		  <div class="form-group">
		    <label for="endPoint">Endpoint</label>
		    <input type="text" class="form-control" id="endPoint" name="endPoint" aria-describedby="endPointHelp" placeholder="Enter endPoint url" required>
		    <small id="endPointHelp" class="form-text text-muted">Please specify the end point url.</small>
		  </div>
		  <div class="form-check">
		  	<label for="graph">Graph</label>
		    <input type="text" class="form-control" id="graph" name="graph" placeholder="Enter graph name" required>
		    <small id="endPointHelp" class="form-text text-muted">Please specify the graph name.</small>
		  </div>
		  </br>
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a class="btn btn-default pull-right" href="/DiscoverOntology/SystemTestList" role="button">Cancel</a>

		</form>
	</div>
</div>

</body>
</html>