package edu.uga.discoverontology.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.log4j.Logger;


public class EndpointConnection {
	
	final static Logger logger = Logger.getLogger(EndpointConnection.class);
	
	private String serviceURI;
	private String graphName;	
	
	public EndpointConnection() {
	}

	public EndpointConnection(String serviceURI, String graphName) {
		this.serviceURI = serviceURI;
		this.graphName = graphName;
	}
	
	

	public ArrayList<HashMap<String, String>> executeQueryForCol(String queryString) {
		QueryExecution qexec = QueryExecutionFactory.sparqlService(serviceURI, queryString);
		
		
//		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			
			int index = queryString.toUpperCase().indexOf("WHERE");
			
			if (index == -1) {
				logger.error("EndpointConnection.executeQuery : Query is not formed correctly.");
				return list;
			}
			
			queryString = queryString.substring(0, index) + " FROM <" + graphName + "> " + queryString.substring(index, queryString.length());
			
			ResultSet rs = qexec.execSelect();	

			List<String> varNames = rs.getResultVars();	
			

			while (rs.hasNext()){	
				HashMap<String, String> map = new HashMap<String, String>();
				QuerySolution soln = rs.nextSolution();

				for (String varName : varNames) {
					map.put(varName, soln.getLiteral(varName).getString());

				}
				list.add(map);
			}

		}
		catch (Exception e) {
//			e.printStackTrace();
			
			logger.error(e.getMessage(), e);
			qexec.close();		
			return list;
		}		
		logger.info("EndpointConnection.executeQuery : retrieved result.");
		qexec.close();			
		return list;
	}	
	
	public int executeQueryForScalar(String queryString) {
		QueryExecution qexec = QueryExecutionFactory.sparqlService(serviceURI, queryString);
		
		
		int count=0; 
		
		try {
			
			int index = queryString.toUpperCase().indexOf("WHERE");
			
			if (index == -1) {
				logger.error("EndpointConnection.executeQuery : Query is not formed correctly.");
				return count;
			}
			
			queryString = queryString.substring(0, index) + " FROM <" + graphName + "> " + queryString.substring(index, queryString.length());
			
			ResultSet rs = qexec.execSelect();	
			
			System.out.println("varnames: " + rs.getResultVars().size());
			System.out.println(rs.getResultVars().get(0));
			while (rs.hasNext()){
				QuerySolution soln = rs.nextSolution();
				count =soln.getLiteral(rs.getResultVars().get(0)).getInt();
			}
			

		}
		catch (Exception e) {
			e.printStackTrace();	
			logger.error(e.getMessage(), e);
		}		
		logger.info("EndpointConnection.executeQuery : retrieved result.");
		qexec.close();	
		return count;
	}	
	
}
