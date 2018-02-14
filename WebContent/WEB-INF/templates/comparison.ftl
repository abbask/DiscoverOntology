<html>
<head>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="js/compareExamine.js"></script>

<link rel="stylesheet" href="css/style.css">	
  <title>Discovering Ontology - Comparing</title>
</head>
<body>

<div class="content">
	<div class="floatLeft largerForm">
		<form>
			<div><label for="newEndpoint">New Ontology End Point:</label><input placeholder="http://vulcan.cs.uga.edu:8890/sparql" type="text" id="newEndpoint" name="newEndpoint"></div>
			<div><label for="newGraphName">New Ontology  Graph Name:</label><input placeholder="<http://prokino.uga.edu>" type="text" id="newGraphName" name="newGraphName"></div>		
			<div><label for="oldEndpoint">Old Ontology End Point:</label><input placeholder="http://localhost:8890/sparql/" type="text" id="oldEndpoint" name="oldEndpoint"></div>
			<div><label for="oldGraphName">Old Ontology  Graph Name:</label><input placeholder="<http://prokino.uga.edu>" type="text" id="oldGraphName" name="oldGraphName"></div> 
		
			<div><label for="oldGraphName"></label><button id="examine" name="examine" type="button" >Examine</button><div>
		</form>  	
	</div>
	<div>
		<div class="classCount">Number of Classes: ${classCountCompare}</div>
		<div class="dataPropertyCount">Number of Data Properties: ${dataPropertiesCountCompare}</div>
		<div class="objectPropertyCount">Number of Object Properties: ${objectPropertiesCountCompare}</div>	
		<div class="classInstance">Number of Individuals: ${individualCountCompare}</div>	
		<div class="classInstance">Classes: ${classCompare}</div>	
	</div>
</div>

</body>
</html>