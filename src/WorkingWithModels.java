

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;

public class WorkingWithModels {
	
	public static void main(String[] args) {
		
		
		String namespace = "http://www.semanticweb.org/Word#";
        String file = "result-simple.owl";
        OntModel jenaModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        InputStream in = FileManager.get().open(file);
        jenaModel.read(in, null);
        //OntClass Noun = jenaModel.getOntClass(namespace + "Noun");
        OntClass Noun = jenaModel.createClass(namespace + "Noun");
        
        Individual Organization = Noun.createIndividual(namespace + "Organization");
        jenaModel.add(Organization, RDF.type, Noun);
        FileWriter out;
		try {
			out = new FileWriter("result-simple.owl");
			jenaModel.getWriter("RDF/XML-ABBREV").write(jenaModel, out, namespace);
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
