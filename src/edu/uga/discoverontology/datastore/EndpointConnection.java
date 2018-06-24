package edu.uga.discoverontology.datastore;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

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
	
	

	public ArrayList<ArrayList<String>> executeQuery(String queryString) {
		QueryEngineHTTP qeHTTP = null;
		ResultSet results = null;
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		try {
			
			int index = queryString.toUpperCase().indexOf("WHERE");
			
			if (index == -1) {
				logger.error("EndpointConnection.executeQuery : Query is not formed correctly.");
				return list;
			}
			
			queryString = queryString.substring(0, index) + " FROM <" + graphName + "> " + queryString.substring(index, queryString.length());
//			System.out.println("queryString: " + queryString);
			qeHTTP = new QueryEngineHTTP(serviceURI, queryString );
			results = qeHTTP.execSelect();	
			
			Iterator<String> iter = results.nextSolution().varNames();
			ArrayList<String> varNames = new ArrayList<>();
			
			while(iter.hasNext()) {
				varNames.add(iter.next());
			}
			
			
			while (results.hasNext()){	
				ArrayList<String> rs = new ArrayList<>();
				for (String varName : varNames) {
					rs.add(results.nextSolution().getResource(varName).toString());		
				}
				list.add(rs);
			}

		}
		catch (Exception e) {
			e.printStackTrace();	
			logger.error(e.getMessage(), e);
		}		
		logger.info("EndpointConnection.executeQuery : retrieved result.");
		qeHTTP.close();		
		return list;
	}	
	
	
}
