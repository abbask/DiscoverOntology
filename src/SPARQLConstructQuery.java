import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
/**
 * https://jena.apache.org/tutorials/rdf_api.html#ch-Writing%20RDF
 * @author abbas
 *
 */
public class SPARQLConstructQuery {
	
	public static void main(String[] args) {
		String service = "http://localhost:8890/sparql";
		
		Model model = ModelFactory.createDefaultModel();
		String queryString = "select distinct ?kocholo where {[] a ?kocholo} LIMIT 120";
		Query query = QueryFactory.create(queryString) ;
//		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(service, query);
		Model resultModel = qexec.execConstruct() ;
		qexec.close() ;
    }


}
