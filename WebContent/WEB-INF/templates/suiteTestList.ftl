<!DOCTYPE html>
<html lang="en">
<head>
  <title>Suite Test List</title>
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
	<h3>List of System Tests</h3>
	<p>You can see list of system test and load them.</p>
	<div>
		<a href="SuiteTestNew" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add Suite Test</a>
	
	</div>
	<div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">name</th>
		      
		      <th scope="col">tests</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list suiteTests as suiteTest>
				<tr>
					<td>
						${suiteTest.name}
					</td>

					<td>
						
					</td>
				</tr>
			<#else>
			  <tr>
			  	<td colspan="2">
			  		No data found
			  	</td>
			  </tr>
			</#list>
			

		  </tbody>
		</table>
	</div>
</div>

</body>
</html>