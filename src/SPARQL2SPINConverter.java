
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.topbraid.spin.arq.ARQ2SPIN;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.model.Select;
import org.topbraid.spin.system.SPINModuleRegistry;



/**
 * Converts between textual SPARQL representation and SPIN RDF model.
 */
public class SPARQL2SPINConverter 
{

	public static void main(String[] args) 
	{
		// Register system functions
		SPINModuleRegistry.get().init();
		
		// Create an empty OntModel
		final Model model = ModelFactory.createDefaultModel();
		
		String query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";
		//String query = "PREFIX ta:      <https://tutorial-academy.com/2015/spin#> PREFIX sp:      <http://spinrdf.org/sp#>  PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ( ( AVG ( ?value ) ) AS ?average ) ?sensor WHERE { 	?measurement 	rdf:type 		ta:Measurement .     ?measurement 	ta:timestamp 	?timestamp .     ?measurement ta:value 		?value .     ?measurement 	ta:sensor 		?sensor . } GROUP BY ( ?sensor ) ORDER BY DESC ( ?average ) ";
					
		Query arqQuery = ARQFactory.get().createQuery( model, query );
		ARQ2SPIN arq2SPIN = new ARQ2SPIN( model );
		
		Select sparqlQuery = (Select) arq2SPIN.createQuery( arqQuery, null );
		
//		System.out.println( "SPARQL Query:\n" + sparqlQuery );
//		
//		System.out.println( "\nSPIN Representation:" );
//		model.write( System.out, FileUtils.langTurtle );
//		
//		System.out.println( "\nSPIN Representation:" );
//		model.write( System.out, FileUtils.langXMLAbbrev );
		
		//String fileNameRDF = "result-simple.rdf";
		String fileNameOWL = "result-simple.owl";
		//FileWriter outRDF ;
		FileWriter outOWL ;
		try {
			//outRDF = new FileWriter( fileNameRDF );
			//model.write(outRDF, "RDF/XML");
			
			outOWL = new FileWriter( fileNameOWL );
			model.write(outOWL, "RDF/XML-ABBREV");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}