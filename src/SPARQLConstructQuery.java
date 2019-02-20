import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
/**
 * https://jena.apache.org/tutorials/rdf_api.html#ch-Writing%20RDF
 * https://jena.apache.org/documentation/rdfconnection/
 * @author abbas
 *
 */
public class SPARQLConstructQuery {
	
	public static void main(String[] args) {
		RDFConnection conn0 = RDFConnectionRemote.create()
	            .destination("http://sparql.org/")
	            .queryEndpoint("sparql")
	            // Set a specific accept header; here, sparql-results+json (preferred) and text/tab-separated-values
	            // The default is "application/sparql-results+json, application/sparql-results+xml;q=0.9, text/tab-separated-values;q=0.7, text/csv;q=0.5, application/json;q=0.2, application/xml;q=0.2, */*;q=0.1" 
	            .acceptHeaderSelectQuery("application/sparql-results+json, application/sparql-results+xml;q=0.9")
	            .build();
	        
	        Query query = QueryFactory.create("SELECT * { BIND('Hello'as ?text) }");

	        // Whether the connection can be reused depends on the details of the implementation.
	        // See example 5.
	        try ( RDFConnection conn = conn0 ) { 
	            conn.queryResultSet(query, ResultSetFormatter::out);
	        }
    }


}
