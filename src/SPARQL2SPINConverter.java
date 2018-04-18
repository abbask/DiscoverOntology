
import java.io.FileWriter;
import java.io.IOException;

import org.topbraid.spin.arq.ARQ2SPIN;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.model.Select;
import org.topbraid.spin.system.SPINModuleRegistry;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileUtils;

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

					
		Query arqQuery = ARQFactory.get().createQuery( model, query );
		ARQ2SPIN arq2SPIN = new ARQ2SPIN( model );
		Select sparqlQuery = (Select) arq2SPIN.createQuery( arqQuery, null );
		
		System.out.println( "SPARQL Query:\n" + sparqlQuery );
		System.out.println( "\nSPIN Representation:" );
		System.out.println( "\nSPIN Representation:" );
		model.write( System.out, FileUtils.langTurtle );
		
		String fileNameRDF = "result-simple.rdf";
		String fileNameOWL = "result-simple.owl";
		FileWriter outRDF ;
		FileWriter outOWL ;
		try {
			outRDF = new FileWriter( fileNameRDF );
			model.write(outRDF, "RDF/XML");
			
			outOWL = new FileWriter( fileNameOWL );
			model.write(outOWL, "RDF/XML-ABBREV");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}