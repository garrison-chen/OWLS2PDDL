package de.dfki.owls2pddxml_2_0;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParserTest {

	public static final String XML_TEST_FILE = "/tmp/xmltest.txt";
	
	// begin a new nest if the node has the following name(s)
	public static final String[] NEST_NAMES = {"and"};
	
	// literally print the node name when encountered
	public static final String[] LITERAL_PRINT_NAMES = {"not", "or"};
	
	public static final String[] PREDICATE_MARKERS = {"pred"};
	
	public static final String PARAMETER_MARKER = "param";

	public static final String NAME_ATTRIBUTE = "name";
	
	/*
	 * 
	 * last week:
	 * 	- packaged and sent windows XP XPlan
	 * 	- checked out MyMediaPeer version of XPlan -- missing dependencies again
	 * 	- started work on converter
	 * 
	 * this week:
	 * 
	 * 	- coverter seems to be able to convert everything except "actions":
	 * 	precondition and effect have nested "and" and "or" statements, which have to be parsed from XML
	 * 	(not able to directly access the string builder, like in other parts of the PDDXML program
	 * 
	 */





	public static void main(String args[]) {

		String xmlString = //"<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
				"<and>"
				+ "<pred name=\"agentHasKnowledgeAbout\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#AccountData"
					+ "</param>"
				+ "</pred>"
				+ "<or>"
				+ "<pred name=\"http://127.0.0.1:8000/health-scallops/ontology/MedicalFlightCompany_Ontology.owl#ValidAccount\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#AccountData"
					+ "</param>"
				+ "</pred>"
				+ "</or>"
				+ "<pred name=\"agentHasKnowledgeAbout\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Attendant"
					+ "</param>"
				+ "</pred>"
				+ "<or>"
				+ "<pred name=\"http://127.0.0.1:8000/health-scallops/ontology/Health-Scallops_Ontology.owl#Person\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Attendant"
					+ "</param>"
				+ "</pred>"
				+ "</or>"
				+ "<pred name=\"agentHasKnowledgeAbout\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Flight"
					+ "</param>"
				+ "</pred>"
				+ "<or>"
				+ "<pred name=\"http://127.0.0.1:8000/health-scallops/ontology/MedicalFlightCompany_Ontology.owl#ProvidedFlight\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Flight"
					+ "</param>"
				+ "</pred>"
				+ "</or>"
				+ "<pred name=\"agentHasKnowledgeAbout\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Patient"
					+ "</param>"
				+ "</pred>"
				+ "<or>"
				+ "<pred name=\"http://127.0.0.1:8000/health-scallops/ontology/Health-Scallops_Ontology.owl#Patient\">"
					+ "<param>?http://127.0.0.1:8000/health-scallops/services/BookMedicalFlight.owl#Patient"
					+ "</param>"
				+ "</pred>"
				+ "</or>"
				+ "</and>";

		StringBuffer buffer = new StringBuffer();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;  

		try {  
			
			builder = factory.newDocumentBuilder();  
			Document check = builder.parse(new InputSource(new StringReader(xmlString)));
			Node rootNode = check.getDocumentElement();
			listUntilNewNest(rootNode, buffer);
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}

		FileWriter pddlDomainFileWriter;

		try {
			pddlDomainFileWriter = new FileWriter(XML_TEST_FILE);//new FileWriter(initFile.getAbsolutePath().split(initFile.getName())[0] +"_InitialOntology_Domain.xml");
			pddlDomainFileWriter.write(new String(buffer));
			pddlDomainFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void beginNest(Node rootNode, StringBuffer buffer) {
		
		buffer.append("(");
		buffer.append(rootNode.getNodeName());
		buffer.append("\n");
		
		for (Node n: XmlUtil.asList(rootNode.getChildNodes())) {
			buffer.append("\t");
			listUntilNewNest(n, buffer);
		}
		
		
		buffer.append("\n )");
	}

	public static void listUntilNewNest(Node rootNode, StringBuffer buffer) {
		
		boolean additionalNewLine = false;
		
		if (Arrays.asList(NEST_NAMES).contains(rootNode.getNodeName())) 
			beginNest(rootNode, buffer);
		
		else {
			
			// deal with root node, then with its children
			if (Arrays.asList(NEST_NAMES).contains(rootNode.getNodeName())) {
				buffer.append(" " + rootNode.getNodeName() + " ");
			}
			
			if (Arrays.asList(LITERAL_PRINT_NAMES).contains(rootNode.getNodeName())) {
				buffer.append("\t " + rootNode.getNodeName() + " ");
				
				// one has to look at the children within the "or" statement
				rootNode = rootNode.getFirstChild();
				additionalNewLine = true;
			}
			
			buffer.append("(");
			
			if (Arrays.asList(PREDICATE_MARKERS).contains(rootNode.getNodeName())) 
				buffer.append(rootNode.getAttributes().getNamedItem(NAME_ATTRIBUTE).getNodeValue());	
			
			// iterate through children
			for (Node n: XmlUtil.asList(rootNode.getChildNodes())) {
				
				if (Arrays.asList(LITERAL_PRINT_NAMES).contains(n.getNodeName())) {
					buffer.append(" " + rootNode.getNodeName() + " ");
					listUntilNewNest(n, buffer);
				}
				
				if (Arrays.asList(NEST_NAMES).contains(n.getNodeName())) 
					beginNest(rootNode, buffer);
				
				if (PARAMETER_MARKER.equals(n.getNodeName())) {
					buffer.append(" ");
					buffer.append(n.getFirstChild().getNodeValue());
					}
			}
			
			
			buffer.append(") \n");
			
			if (additionalNewLine)
				buffer.append("\n");
			
			}
			
			
			
			
			
		}
		
	
		
		


	}
	



