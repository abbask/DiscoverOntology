package edu.uga.discoverontology.datastore; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

import edu.uga.discoverontology.model.MyConcept;
import edu.uga.discoverontology.model.MyProperty;

public class DataStoreConnection {
	private String serviceURI = "http://localhost:8890/sparql/";
	private String graphName ="<http://prokino.uga.edu>";		
	
//	public DataStoreConnection() {
//				
//	}

	public String getServiceURI() {
		return serviceURI;
	}

	public void setServiceURI(String serviceURI) {
		this.serviceURI = serviceURI;
	}

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public DataStoreConnection(String serviceURI, String graphName) {
		if (serviceURI != null) 
			this.serviceURI = serviceURI;
		if (graphName != null)
			this.graphName = graphName;
		
	}

	private ArrayList<String> executeQuery(String queryString) {
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		ArrayList<String> rs = new ArrayList<String>();
		try {					

			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();							
			while (results.hasNext()){	
				rs.add(results.nextSolution().getResource("s").toString());				
			}

		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return rs;
	}	

	//showClasses	
	public Map<String, Object> restoreAllClasses () {

		//		String queryString = "SELECT DISTINCT ?className ?classLabel ?superClass FROM <http://prokino.uga.edu> WHERE { ?className rdf:type owl:Class. optional {?className rdfs:label ?classLabel.} optional {?className rdfs:subClassOf ?superClass.} FILTER regex(str(?className),'prokino') } ORDER BY ?className";
		String queryString = "SELECT DISTINCT ?className ?classLabel ?superClass COUNT(DISTINCT ?entity) as ?count FROM " + graphName + " WHERE { ?className rdf:type owl:Class. ?entity rdf:type ?className. optional {?className rdfs:label ?classLabel.} optional {?className rdfs:subClassOf ?superClass.} FILTER regex(str(?className),'prokino') } GROUP BY ?className ?classLabel ?superClass ORDER BY ?className";
		System.out.println(queryString);
		
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		MyConcept concept;
		Map<String, Object> returnResult = new HashMap<String, Object>();
		ArrayList<MyConcept> conceptList = new ArrayList<MyConcept>();

		try {					

			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	

			while (results.hasNext()){

				concept = new MyConcept();
				QuerySolution soln = results.nextSolution();

				concept.setClassName(soln.getResource("className").getLocalName());				

				concept.setClassLabel(soln.getLiteral("classLabel") != null ? soln.getLiteral("classLabel").toString() : "");

				if (soln.getResource("superClass") != null){
					concept.setSuperClass(soln.getResource("superClass").getLocalName());
				}
				else{
					concept.setSuperClass("");
				}								
				conceptList.add(concept);

				concept.setCount(soln.getLiteral("count").getInt());

			}

			returnResult.put("classes", conceptList);

		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return returnResult;
	}

	//listAllInfo, updateAllInfo
	public Map<String, Object> countClasses () {

		String queryString = "SELECT count(?className) as ?count FROM " + graphName + " WHERE { ?className rdf:type owl:Class. optional {?className rdfs:label ?classLabel.} optional {?className rdfs:subClassOf ?superClass.} FILTER regex(str(?className),'prokino') }";
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;		
		Map<String, Object> returnResult = new HashMap<String, Object>();		
		
		
		try {					
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	
			int count=0; 
			while (results.hasNext()){
				QuerySolution soln = results.nextSolution();
				count =soln.getLiteral("count").getInt();
			}
			returnResult.put("classCount", count);
		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return returnResult;
	}
	
	//listAllInfo, updateAllInfo
	public Map<String, Object> countDataProperty () {

		String queryString = "SELECT count(?name) as ?count FROM " + graphName  + " WHERE { ?name rdf:type owl:DatatypeProperty optional {?name rdfs:domain ?o. ?o owl:unionOf ?l. {?l rdf:first ?domain.} UNION {?l rdf:rest ?rest. ?rest rdf:first ?domain}} optional {?name rdfs:domain ?domain} optional {?name rdfs:range ?range} } ";
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;		
		Map<String, Object> returnResult = new HashMap<String, Object>();		

		try {					
//			System.out.println(serviceURI);
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	
			int count=0; 
			while (results.hasNext()){

				QuerySolution soln = results.nextSolution();
				count =soln.getLiteral("count").getInt();

			}
			
			returnResult.put("dataPropertyCount", count);
		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return returnResult;
	}
	
	//listAllInfo, updateAllInfo
	public Map<String, Object> countObjectProperty () {

		String queryString = "SELECT count(?name) as ?count FROM " + graphName + " WHERE { ?name rdf:type owl:ObjectProperty optional {?name rdfs:domain ?o. ?o owl:unionOf ?l. {?l rdf:first ?domain.} UNION {?l rdf:rest ?rest. ?rest rdf:first ?domain}} optional {?name rdfs:domain ?domain} optional {?name rdfs:range ?range} } ";
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;		
		Map<String, Object> returnResult = new HashMap<String, Object>();		

		try {					
//			System.out.println(serviceURI);
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	
//			System.out.println("ob" + queryString);
			int count=0; 
			while (results.hasNext()){

				QuerySolution soln = results.nextSolution();
				count =soln.getLiteral("count").getInt();

			}

			
			returnResult.put("objectPropertyCount", count);
		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return returnResult;
	}

	
	//showObjectProperties
	public Map<String, Object> restoreAllObejectProperties () {
		
		
		String queryString = "SELECT DISTINCT ?domain ?name ?range COUNT(?object) as ?count FROM " + graphName + "  WHERE { ?name rdf:type owl:ObjectProperty optional { ?name rdfs:domain ?o. ?o owl:unionOf ?l.  {?l rdf:first ?domain. } UNION {?l rdf:rest ?rest. ?rest rdf:first ?domain}} optional {?name rdfs:domain ?domain} optional {?name rdfs:range ?range. ?range rdf:type owl:Class} ?subject ?name ?object} GROUP By ?name ?domain ?range ORDER BY ?name";
		
		System.out.println(queryString);
		
		QueryEngineHTTP qeHTTP = null;

		
		ResultSet results = null;

		
		MyProperty prop;
		Map<String, Object> returnResult = new HashMap<String, Object>();
		ArrayList<MyProperty> propertytList = new ArrayList<MyProperty>();

		try {					

			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );

			
			results = qeHTTP.execSelect();	

			
			
			int count = 0;
			
			List<MyProperty> lstDomain = new LinkedList<MyProperty>();				
			
			
			while (results.hasNext()){
				prop = new MyProperty();
				QuerySolution soln = results.nextSolution();


				if (soln.getResource("name") != null){

					prop.setName(soln.getResource("name").getLocalName());

				}
				else{
					prop.setName("");
				}

				if (soln.getResource("domain") != null){

					prop.setDomain(soln.getResource("domain").getLocalName());

				}
				else{
					prop.setDomain("");
				}

				if (soln.getResource("range") != null){

					prop.setRange(soln.getResource("range").getLocalName());

				}
				else{
					prop.setRange("");
				}
				
				prop.setCount(soln.getLiteral("count").getInt());
				
//				for (MyProperty p : lstDomain){
//					
//
//					if (p.getName().equalsIgnoreCase(prop.getName())&& 
//							p.getDomain().equalsIgnoreCase(prop.getDomain()) &&
//							p.getRange().equalsIgnoreCase(prop.getRange())){
//						prop.setCountDomain(p.getCountDomain());
//						break;
//					}
//				}
//				
////				prop.setCountDomain(soln.getLiteral("countDomain").getInt());
//				
//				for (MyProperty p : lstRange){
//					
//
//					if (p.getName().equalsIgnoreCase(prop.getName()) && 
//							p.getDomain().equalsIgnoreCase(prop.getDomain()) &&
//							p.getRange().equalsIgnoreCase(prop.getRange())){
//						prop.setCountRange(p.getCountRange());
//						break;
//					}
//				}

				count++;
				propertytList.add(prop);				
			}

			returnResult.put("objectProperties", propertytList);
			returnResult.put("objectPropertyCount", count);

		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();	

		
		return returnResult;
	}

	//showDataProperties	
	public Map<String, Object> restoreAllDataProperties () {

		//String queryString = "SELECT DISTINCT ?domain ?name ?range COUNT(?name) as ?count FROM <http://prokino.uga.edu> WHERE { ?name rdf:type owl:DatatypeProperty optional {?name rdfs:domain ?domain} optional {?name  rdfs:range ?range} } ORDER BY ?name ?domain ?range";
		String queryString = "SELECT DISTINCT ?domain ?name ?range FROM " + graphName + " WHERE { ?name rdf:type owl:DatatypeProperty optional {?name rdfs:domain ?o. ?o owl:unionOf ?l. {?l rdf:first ?domain.} UNION {?l rdf:rest ?rest. ?rest rdf:first ?domain}} optional {?name rdfs:domain ?domain} optional {?name rdfs:range ?range} } ORDER BY ?name ?domain ?range";

		System.out.println(queryString);
		
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		MyProperty prop;
		Map<String, Object> returnResult = new HashMap<String, Object>();
		ArrayList<MyProperty> propertytList = new ArrayList<MyProperty>();

		try {					

			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	
			int count = 0;
			while (results.hasNext()){
				prop = new MyProperty();
				QuerySolution soln = results.nextSolution();


				if (soln.getResource("name") != null){

					prop.setName(soln.getResource("name").getLocalName());

				}
				else{
					prop.setName("");
				}

				if (soln.getResource("domain") != null){

					prop.setDomain(soln.getResource("domain").getLocalName());

				}
				else{
					prop.setDomain("");
				}


				if (soln.getResource("range") != null){

					prop.setRange(soln.getResource("range").getLocalName());

				}
				else{
					prop.setRange("");
				}

				count++;
				propertytList.add(prop);				
			}

			returnResult.put("dataProperties", propertytList);
			returnResult.put("dataPropertyCount", count);

		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();		
		return returnResult;
	}
	
	//listAllInfo, updateAllInfo
	public Map<String, Object> restoreInstancesOfClasses () {

		String queryString = "SELECT COUNT(DISTINCT ?entity) as ?count FROM " + graphName + " WHERE{ ?entity rdf:type ?class. ?class rdf:type owl:Class. }";
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;

		Map<String, Object> returnResult = new HashMap<String, Object>();


		try {					

			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
//			System.out.println(serviceURI);
			results = qeHTTP.execSelect();	


			while (results.hasNext()){

				QuerySolution soln = results.nextSolution();
								
				returnResult.put("classInstance", soln.getLiteral("count").getValue());			

			}
		}
		catch (Exception e) {
			e.printStackTrace();		
		}		
		qeHTTP.close();	
		return returnResult;
	}

	
//	///???
//	public Map<String, Object> restoreInstancesOfObjectProperties () {
//
//		String queryString = "SELECT COUNT(?entity) as ?count FROM " + graphName + " WHERE { ?name rdf:type owl:ObjectProperty. ?entity a ?name  } ";
//
//		QueryEngineHTTP qeHTTP = null;
//		ResultSet results = null;
//
//		Map<String, Object> returnResult = new HashMap<String, Object>();
//
//
//		try {					
//
//			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
//
//			results = qeHTTP.execSelect();	
//
//
//			while (results.hasNext()){
//
//				QuerySolution soln = results.nextSolution();
//
//
//				returnResult.put("PropertyInstance", soln.getLiteral("count").getValue());
//
//
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();		
//		}		
//		qeHTTP.close();		
//		return returnResult;
//	}

}
