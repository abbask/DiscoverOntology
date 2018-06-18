<!DOCTYPE html>
<html lang="en">
<head>
  <title>Unit Test List</title>
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
      <li><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>      
      <li class="active"><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>List of Unit Tests</h3>
	<p>You can see list of unit test and load them.</p>
	<div>
		<a href="UnitTestNew" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add Unit Test</a>
	
	</div>
	<div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">name</th>
		      <th scope="col">assert type</th>
		      <th scope="col">query</th>
		      <th scope="col">expected value</th>
		      <th scope="col">message</th>
		      <th scope="col">system test id</th>
		      
		    </tr>
		  </thead>
		  <tbody>
		  	<#list unitTests as unitTest>
				<tr>
					<td>
						${unitTest.name}
					</td>

					<td>
						${unitTest.assertType}
					</td>
					<td>
						${unitTest.query}
					</td>
					<td>
						${unitTest.expectedValue}
					</td>
					<td>
						${unitTest.message}
					</td>
					<td>
						${unitTest.systemTest.name}
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