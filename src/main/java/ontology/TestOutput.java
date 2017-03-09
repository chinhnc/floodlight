package ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestOutput {
	static final String inputFileName = "final.owl";
	
	public static String[] getInputFromKeyBoard() {
		ArrayList<ListOntology> hostF = readHostFromFile();
		
		int time = 10; // wait 10 seconds at most
		boolean checkString = false;
		String str = null;
		String[] Output = new String[3];
		
		while(!checkString){			
		    System.out.print("------******* Please Enter the request!! Input String: "); 
		    try {
		    	BufferedReader inPut = new BufferedReader(new InputStreamReader(System.in));
				
				long startTime = System.currentTimeMillis();
				
				while (((System.currentTimeMillis() - startTime) < time * 1000) && !inPut.ready()) {
				}
				
				if (inPut.ready()) {
					str = inPut.readLine();//Get String Input
				    
			    	for (ListOntology host : hostF) {
			    		if(host.getName().equals("Computer_Center")){
				        	Output[1]= host.getId();	
						}
						if (str.toLowerCase().contains(host.getPlace().toLowerCase())== true && str.toLowerCase().contains(host.getProvied().toLowerCase())== true) {
							Output[0]= host.getId();
							Output[2]= host.getDelay();
							
							checkString = true;//Input data True
			   			 }else {
			   				checkString = false;
			   			 }
			        }
				} else {
					checkString = true;//Not Enter Input data
				}
		    }catch (IOException e) {
			    System.err.println("Error: " + e);
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
	                for (Iterator i = obj.listSubClasses(true); i.hasNext(); ) {
	                    OntClass c = (OntClass) i.next();
	                    
						for (Iterator iter = c.listInstances(true); iter.hasNext(); ) {
							Individual individual = (Individual) iter.next();	
							
					        StmtIterator it = individual.listProperties();
					        
					        String[] Properties = new String[5];
					        
					        int $index = 1;
					        
					        while( it.hasNext() && $index <5) {
					            Statement s = (Statement) it.next();
					            
					            if (s.getObject().isLiteral()) {
					            	Properties[$index] = s.getLiteral().getLexicalForm().toString();
					            } else {
					            	Properties[$index] = s.getResource().getLocalName();
								}
					            
					            $index++;
							}
					        
					        ListOntology lh = new ListOntology((String) individual.getLocalName(), Properties[1], Properties[2], Properties[3], Properties[4]);
					        listOntologies.add(lh);
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
