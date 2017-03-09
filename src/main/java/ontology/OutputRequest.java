package ontology;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class OutputRequest {
	static final String inputFileName = "final.owl";
	
	public static String[] getInputFromKeyBoard() {
		ArrayList<ListOntology> hostF = readHostFromFile();
		
		String[] Output = new String[3];
		
		while(Output[0] == null){
			Scanner scanner = new Scanner(System.in); 
			
		    System.out.print("------******* Please Enter the request!! Input String: "); 
		    
		    String str = scanner.nextLine();
			
			for (ListOntology host : hostF) {     
				if (str.toLowerCase().contains(host.getPlace().toLowerCase())== true && str.toLowerCase().contains(host.getProvied().toLowerCase())== true) {
					Output[0]= host.getId();
					Output[2]= host.getDelay();
	   			 }
				if(host.getName().equals("Computer_Center")){
		        	Output[1]= host.getId();	
					}
	        }
		}
		
		return Output;
	}
	
	public static ArrayList<ListOntology> readHostFromFile() {
		ArrayList<ListOntology> listOntologies = new ArrayList<ListOntology>();	
		
	    try {
	        //create the reasoning model using the base
	        OntModel inf = ModelFactory.createOntologyModel();
	        // use the FileManager to find the input file
	        InputStream in = FileManager.get().open(inputFileName);
	        
	        if (in == null){
	        	throw new IllegalArgumentException("File: " + inputFileName + " not found");
	        }
	        
	        inf.read(in, "");
	        
	        ExtendedIterator classes = inf.listClasses();
	        
	        ArrayList<Resource> results = new ArrayList<Resource>(); 
	        
	        ExtendedIterator  individuals = inf.listIndividuals();
	        
	        while (classes.hasNext()) {
	            OntClass obj = (OntClass) classes.next();
	            
	            if (obj.hasSubClass() ) {
	                for (Iterator i = obj.listSubClasses(true); i.hasNext();) {
	                    OntClass c = (OntClass) i.next();
	                    
						for (Iterator iter = c.listInstances(true);iter.hasNext();) {
							Individual individual=(Individual) iter.next();	
							
					        StmtIterator it = individual.listProperties();
					        
					        String[] Properties = new String[5];
					        
					        int $i =1;
					        
					        while( it.hasNext() && $i <5) {
					            Statement s = (Statement) it.next();
					            
					            if (s.getObject().isLiteral()) {
					            	Properties[$i] = s.getLiteral().getLexicalForm().toString();
					            } else {
					            	Properties[$i] = s.getResource().getLocalName();
								}
					            
					            $i++;
							}
					        
					        ListOntology lh = new ListOntology((String) individual.getLocalName(), Properties[1], Properties[2], Properties[3], Properties[4]);
					        listOntologies.add(lh);
						}	
	                }	    
	            } else {
	            	for (Iterator iter = obj.listInstances(true);iter.hasNext();) {
	            		Individual individual=(Individual) iter.next();
	            		//System.out.println(individual.getLocalName());	// in ra cac yeu cau can thiet					
	            		StmtIterator it = individual.listProperties();
	            		
	            		while( it.hasNext()) {
	            			Statement s = (Statement) it.next();    
	            		}
	            	}	
	            }      
	        }       
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
		return listOntologies;
	}
}
