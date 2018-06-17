<!DOCTYPE html>
<html lang="en">
<head>
  <title>System Unit List</title>
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
      <li ><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li><a href="/DiscoverOntology/SuiteTestList">Suite Test List</a></li>
      <li class="active"><a href="/DiscoverOntology/UnitTestList">Unit Test List</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>List of System Tests</h3>
	<p>You can see list of system test and load them.</p>
	<div>
		<a href="UnitTestNew" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add Unit Test</a>
	
	</div>
	<div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Name</th>
		      <th scope="col">Query</th>
		      <th scope="col">Assert Type</th>
		      <th scope="col">Expected Value</th>
		      <th scope="col">Message</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list unitTests as unitTest>
				<tr>
					<td>
						${unitTest.name}
					</td>
					<td>
						${unitTest.query}
					</td>
					<td>
						${unitTest.assertType}
					</td>
					<td>
						${unitTest.expectedValue}
					</td>
					<td>
						${unitTest.message}
					</td>
				</tr>
			<#else>
			  <tr>
			  	<td colspan="4">
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