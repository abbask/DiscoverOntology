import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.topbraid.spin.arq.ARQ2SPIN;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.model.Select;
import org.topbraid.spin.system.SPINModuleRegistry;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.FileUtils;

public class SPARQLtoOWL {
	
	public static void main (String[] args) {
		
		String ns = "http://spinrdf.org/sp#";
		
		// Register system functions
		SPINModuleRegistry.get().init();
		
		// Create an empty OntModel
//		final Model model = ModelFactory.createDefaultModel();
		
        String file = "oscar-4.owl";
        OntModel jenaModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        InputStream in = FileManager.get().open(file);
        jenaModel.read(in, null);
		
		String query = "select distinct ?Concept where {[] a ?Concept} LIMIT 100";
		//String query = "PREFIX ta:      <https://tutorial-academy.com/2015/spin#> PREFIX sp:      <http://spinrdf.org/sp#>  PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ( ( AVG ( ?value ) ) AS ?average ) ?sensor WHERE { 	?measurement 	rdf:type 		ta:Measurement .     ?measurement 	ta:timestamp 	?timestamp .     ?measurement ta:value 		?value .     ?measurement 	ta:sensor 		?sensor . } GROUP BY ( ?sensor ) ORDER BY DESC ( ?average ) ";					
		Query arqQuery = ARQFactory.get().createQuery( jenaModel, query );
		ARQ2SPIN arq2SPIN = new ARQ2SPIN( jenaModel );		
		Select sparqlQuery = (Select) arq2SPIN.createQuery( arqQuery, null );
		
		String query2 = "SELECT ?s WHERE { ?s ?p ?o } LIMIT 500";		//		
		Query arqQuery2 = ARQFactory.get().createQuery( jenaModel, query2 );
		ARQ2SPIN arq2SPIN2 = new ARQ2SPIN( jenaModel );		
		Select sparqlQuery2 = (Select) arq2SPIN2.createQuery( arqQuery2, null );

		
		//String fileNameRDF = "result-simple.rdf";
		String fileNameOWL = "result-simple.owl";
		//FileWriter outRDF ;
		FileWriter outOWL ;
		try {
			//outRDF = new FileWriter( fileNameRDF );
			//model.write(outRDF, "RDF/XML");
			
			outOWL = new FileWriter( fileNameOWL );
			jenaModel.write(outOWL, "RDF/XML-ABBREV");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileReader = "result-simple.owl";
        OntModel readerModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        System.out.println("Start reading...");
        InputStream inReader = FileManager.get().open(fileReader);
        readerModel.read(inReader, null);
        System.out.println("Start searching...");
        OntClass selectClass = jenaModel.getOntClass(ns + "Select");
		System.out.println("Select Class: " + selectClass.getLocalName());
//		System.out.println("Select Class: " + selectClass.getOntModel());
//		System.out.println("Select Class: " + selectClass.getModel());
		System.out.println("Select URL: " + selectClass.getURI());
		System.out.println("Select getVersionInfo: " + selectClass.getVersionInfo());
//		System.out.println("Select getId:" + selectClass.getId());
		System.out.println("Select getProfile: " + selectClass.getProfile());
		System.out.println("Select getRDFType: " + selectClass.getRDFType());
		System.out.println("Select getSeeAlso: " + selectClass.getSeeAlso());
		
		System.out.println("Done!");
	}

}
