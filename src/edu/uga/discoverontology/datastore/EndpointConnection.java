package edu.uga.discoverontology.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;


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
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		
//		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			
			int index = queryString.toUpperCase().indexOf("WHERE");
			
			if (index == -1) {
				logger.error("EndpointConnection.executeQuery : Query is not formed correctly.");
				return list;
			}
			
			queryString = queryString.substring(0, index) + " FROM <" + graphName + "> " + queryString.substring(index, queryString.length());
			
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			
			results = qeHTTP.execSelect();	

			List<String> varNames = results.getResultVars();	
			

			while (results.hasNext()){	
				HashMap<String, String> rs = new HashMap<String, String>();
				QuerySolution soln = results.nextSolution();

				for (String varName : varNames) {
					rs.put(varName, soln.getLiteral(varName).getString());
					//System.out.println("getValue :" + soln.getLiteral(varName).getValue());
					//System.out.println("getString: " + soln.getLiteral(varName).getString());
					//System.out.println("getDatatypeURI: " + soln.getLiteral(varName).getDatatypeURI());
				}
				list.add(rs);
			}

		}
		catch (Exception e) {
//			e.printStackTrace();
			
			logger.error(e.getMessage(), e);
			qeHTTP.close();		
			return list;
		}		
		logger.info("EndpointConnection.executeQuery : retrieved result.");
		qeHTTP.close();		
		return list;
	}	
	
	public int executeQueryForScalar(String queryString) {
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		
		int count=0; 
		
		try {
			
			int index = queryString.toUpperCase().indexOf("WHERE");
			
			if (index == -1) {
				logger.error("EndpointConnection.executeQuery : Query is not formed correctly.");
				return count;
			}
			
			queryString = queryString.substring(0, index) + " FROM <" + graphName + "> " + queryString.substring(index, queryString.length());
			
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			
			System.out.println("queryString: " + queryString);
			
			
			results = qeHTTP.execSelect();	
			
			System.out.println("varnames: " + results.getResultVars().size());
			System.out.println(results.getResultVars().get(0));
			while (results.hasNext()){
				QuerySolution soln = results.nextSolution();
				count =soln.getLiteral(results.getResultVars().get(0)).getInt();
			}
			

		}
		catch (Exception e) {
			e.printStackTrace();	
			logger.error(e.getMessage(), e);
		}		
		logger.info("EndpointConnection.executeQuery : retrieved result.");
		qeHTTP.close();		
		return count;
	}	
	
}
