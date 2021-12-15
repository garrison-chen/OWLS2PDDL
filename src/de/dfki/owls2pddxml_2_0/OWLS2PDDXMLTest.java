package de.dfki.owls2pddxml_2_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import converter.*;
import de.dfki.pddxml.relaxer.Domain;
import de.dfki.pddxml.relaxer.Problem;
import de.dfki.xplan.XPlan;
import dfki.xplan.XPlanJNI;

import javax.swing.JTextField;

import org.semanticweb.owl.model.OWLException;

public class OWLS2PDDXMLTest {


	// File locations
	
	public static final String PDDXML_DOMAIN_PRINT_LOCATION = "/tmp/domain.xml";
	public static final String PDDXML_PROBLEM_PRINT_LOCATION = "/tmp/problem.xml";
	public static final String PDDL_DOMAIN_PRINT_LOCATION = "/tmp/domain.pddl";
	public static final String PDDL_PROBLEM_PRINT_LOCATION = "/tmp/problem.pddl";
	
	/*
	public static final String INITIAL_ONTOLOGY = "/mnt/E6742FA6742F7887/Users/Anthony/Desktop/9Mar2021-owls2pddl/23Feb2021-setup/initRDFXML.owl";
	public static final String GOAL_ONTOLOGY = "/mnt/E6742FA6742F7887/Users/Anthony/Desktop/9Mar2021-owls2pddl/23Feb2021-setup/goalOntoRDFXML.owl";
	public static final String SERVICES_DIRECTORY = "/mnt/E6742FA6742F7887/Users/Anthony/Desktop/9Mar2021-owls2pddl/23Feb2021-setup/services";
	public static final String TEMP_SERVICES_DIRECTORY = "/mnt/E6742FA6742F7887/Users/Anthony/Desktop/9Mar2021-owls2pddl/23Feb2021-setup/tmp"; //27Jan2021-setup/tmp";
	public static final String SERVER_LOCAL_DIRECTORY = "/mnt/E6742FA6742F7887/Users/Anthony/Desktop/9Mar2021-owls2pddl/23Feb2021-setup"; //"/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/23Feb2021-setup/";
	*/
	
	public static final String INITIAL_ONTOLOGY = "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel/Simple_InitialOntology.owl";
	public static final String GOAL_ONTOLOGY = "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel/Simple_GoalOntology.owl";
	public static final String SERVICES_DIRECTORY = "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel/services";
	public static final String TEMP_SERVICES_DIRECTORY = "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel/tmp"; //27Jan2021-setup/tmp";
	public static final String SERVER_LOCAL_DIRECTORY = "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel"; //"/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/23Feb2021-setup/";
	
	public static void main(String args[]) {

		OWLS2PDDL converter = new OWLS2PDDL("test", SERVER_LOCAL_DIRECTORY);

		File initFile = new File(INITIAL_ONTOLOGY);
		File goalFile = new File(GOAL_ONTOLOGY);
		
		boolean print = true;

		try {

			converter.addToInitialState(initFile.toURI());
			converter.addToGoalState(goalFile.toURI());

			File[] files = new File(SERVICES_DIRECTORY).listFiles();

			for (File file : files)
				if (!file.isDirectory() && file.getName().contains(".owl")) 
				{
				converter.addServices(file.toURI());
				}
		} catch (OWLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (print) {

			System.out.println("printing");
			
			FileWriter pddlDomainFileWriter;

			try {
				pddlDomainFileWriter = new FileWriter(PDDL_DOMAIN_PRINT_LOCATION);//new FileWriter(initFile.getAbsolutePath().split(initFile.getName())[0] +"_InitialOntology_Domain.xml");
				String pddlString = converter.getDomain().makePDDLTextDocument(false, converter.getFilesWithPddl());
				pddlDomainFileWriter.write(pddlString);
				pddlDomainFileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FileWriter pddlProblemFileWriter;
			try {
				pddlProblemFileWriter = new FileWriter(PDDL_PROBLEM_PRINT_LOCATION);//new FileWriter(initFile.getAbsolutePath().split(initFile.getName())[0]+"_InitialOntology_PB.xml");
				pddlProblemFileWriter.write(converter.getProblem().makePDDLTextDocument(false));
				pddlProblemFileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FileWriter domainFileWriter;

			try {
				domainFileWriter = new FileWriter(PDDXML_DOMAIN_PRINT_LOCATION);//new FileWriter(initFile.getAbsolutePath().split(initFile.getName())[0] +"_InitialOntology_Domain.xml");
				domainFileWriter.write(converter.getDomain().makeTextDocument());
				domainFileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileWriter problemFileWriter;
			try {
				problemFileWriter = new FileWriter(PDDXML_PROBLEM_PRINT_LOCATION);//new FileWriter(initFile.getAbsolutePath().split(initFile.getName())[0]+"_InitialOntology_PB.xml");
				problemFileWriter.write(converter.getProblem().makeTextDocument());
				problemFileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
