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

		// setup:
		// python3 "/home/anthony/Documents/Python Workspace/welcomeScripts/WELCOMESetup.py" "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/23Feb2021-setup/" -p 8080
		// python3 "/home/anthony/Documents/Python Workspace/welcomeScripts/WELCOMESetup.py" "/home/anthony/Documents/Bioinformatics/Work/WELCOME/PDDL-and-OWL-Files/Beispiel" -p 8080
		
		// replace all instances of a given string (e.g., "127.0.0.1" with "127.0.0.1:8080") by calling
		// $ python3 ~/Documents/python-scripts/replaceText.py <path> -s 127.0.0.1 -r 127.0.0.1:8080
		
		
		//TODO (22Apr2021) switch to the first reception service problem instead of health scallops
		//TODO (22Apr2021) make some documentation to describe switching the port of the converter
		//TODO (15Apr2021) send the signed hiwi timesheets (from december 2020 onwards)
		//TODO (15Apr2021) constrain the remake- name matcher to only search the services folder

		//TODO (4Mar2021) Finish leaving DFKI email requests 
		
		//TODO (29Apr2021) Send the leaving papers to Matthias last. Make sure to send the picture with him as well
		
		/*
		 * 
		 *Precondition
-    In line 18, please change "rdf:resource" by "rdf:ID" since remember
that the first one you use it when you are making reference to some element
defined elsewhere. While in line 18 you are defining the pre-condition
inside the tag.
-    Could you please also send me the plan to I can check if the
expressionBody agrees the precondition of the plan?
-    Why the pre-condition includes "&lt;" "&gt" ? Could you please
delete it? Or is it due to the fact that those HTML tag are in the source
file?

Prefixes
-    You are using the "pddlexpr" prefix but that is not defined at the
beginning of the file.
-    You are using the "objList" prefix but that is not defined at the
beginning of the file.

Entities
- There are several entities which are referenced with "rdf:resource" but
that are not defined in the document. For example: "AccountData", "Flight",
"Request", etc. Remember that inputs/outputs/pre-condition/effects defined
in your new composite service ("26042021182557032261BsFxCyagIGOMuwqHnEUWb"
in your document) are also new. So you need to define them in the document
(new IRI). For example:

<process:hasInput>
    <process:Input rdf:ID="ObtainAsylumClaimComposed">
        <process:parameterType
rdf:datatype="http://www.w3.org/2001/XMLSchema#anyURI">https://raw.githubuse
rcontent.com/gtzionis/WelcomeOntology/main/welcome.ttl#AsylumClaim</process:
parameterType>
        <welcome:hasTemplateId
rdf:datatype="http://www.w3.org/2001/XMLSchema#string">TemplateObtainAsylumC
laim</welcome:hasTemplateId>
        <welcome:isOptional
rdf:datatype="http://www.w3.org/2001/XMLSchema#boolean">false</welcome:isOpt
ional>
    </process:Input>
</process:hasInput>

when the definition of this new entity must agree with the definition of the
corresponding binding entity in the existing atomic/composed service.

Binding
- You are using "CreateFlightAccountAtomicProcess" as both 1) a reference to
a process and 2) a reference to a variable.
- There is a wrong binding in the variables. Please see that you are binding
the variable "CreateFlightAccountProcess" to
"CreateFlightAccountAtomicProcess" several times (three times). It does not
make sense, that will mean that you are using three times the same variable
as an input.

Output
- Please check the following lines:

<process:valueSource>
    <process:ValueOf />
            <process:theVar rdf:resource="#ProposedFlightAtomic" />
                  <process:fromProcess
rdf:resource="#ProposeFlightAtomicProcess" />
</process:valueSource>

the right syntax is
<process:valueSource>
    <process:ValueOf>
            <process:theVar rdf:resource="#ProposedFlightAtomic" />
                  <process:fromProcess
rdf:resource="#ProposeFlightAtomicProcess" />
    </process:ValueOf>
</process:valueSource>

Existing atomic/composite process (steps)
- The definition of the existing process (steps in the plan) is incomplete.
Remember that you need to include all the inputs/outputs and their
corresponding definition.

Profile
- The "presentedBy" property must reference to an element of type "Service"
but you are referencing to the complete ontology (owl:Ontology rdf:about), a
Service (service:Service), and a Result (process:Result rdf:ID). Please use
different IRI for each new entity.
- The reference to the inputs in the profile is correct. Although you are
defining only one (Customer) in the definition of the composite service.
- In the profile you are making reference to three different "Result"
entities but in your Composite service you only have one
(26042021182557032261BsFxCyagIGOMuwqHnEUW). The same for the Precondition

		 *  
		 * 
		 */

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
