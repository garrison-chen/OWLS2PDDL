package de.dfki.owls2pddxml_2_0;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class OwlsPreparser {

	private static final String PDDL_NODE_IDENTIFIER = "pddl";
	private static final String RDF_NODE_IDENTIFIER = "rdf";
	private static final String PRECONDITION_IDENTIFIER = "precondition";
	private static final String EFFECT_IDENTIFIER = "effect";
	private static final String OWL_NODE_IDENTIFIER = "owl";
	private static final String IMPORT_NODE_IDENTIFIER = "import";
	private static final String CONTACT_NODE_IDENTIFIER = "contactinformation";
	private static final String OWLS_INPUT_IDENTIFIER = "hasinput";
	private static final String OWLS_OUTPUT_IDENTIFIER = "hasoutput";
	
	public static final String REMAKE_FILE_PREFIX = "remake-";
	
	private static final URI PDDXML_PATH = OWLS2PDDL.SERVER_URI.resolve("/pddxml.owl");
	private static final boolean PLACEHOLDER_STYLE = false;
	
	private static void removeInputsAndOutputs(NodeList nodeList) {
		
		
		for (int count = nodeList.getLength() - 1; count >= 0; count--) {

			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeName().toLowerCase().contains(OWLS_INPUT_IDENTIFIER) || tempNode.getNodeName().toLowerCase().contains(OWLS_OUTPUT_IDENTIFIER)) { 
				Node parent = tempNode.getParentNode();
				parent.removeChild(tempNode);
				
			}


			if (tempNode.hasChildNodes()) {

				// loop through child nodes
				removeInputsAndOutputs(tempNode.getChildNodes());

			}

		}
	}
	private static void findPDDLNodes(NodeList nodeList, URI originalURI, URI remakeFile, ArrayList<PDDLNode> pddlNodes) {

		for (int count = nodeList.getLength() - 1; count >= 0; count--) {

			Node tempNode = nodeList.item(count);

			// If tempNode is a PDDL node, store it in a PDDLNode object
			if (tempNode.getNodeName().toLowerCase().contains(PDDL_NODE_IDENTIFIER) && tempNode.getNodeType() == Node.ELEMENT_NODE) { 
				pddlNodes.add(new PDDLNode(tempNode, pddlNodes.size(), isPreconditionNode(tempNode)));
			}

			// edit prefix to include pddxml
			if (tempNode.getNodeName().toLowerCase().contains(RDF_NODE_IDENTIFIER) || tempNode.getNodeName().toLowerCase().contains(OWL_NODE_IDENTIFIER)) {//&& tempNode.getNodeType() == Node.ELEMENT_NODE) {

				
				if (tempNode.getNodeName().toLowerCase().contains(RDF_NODE_IDENTIFIER)) {
					
					((Element) tempNode).setAttribute("xmlns:pddxml", PDDXML_PATH.toString());

				}

				// rename any attributes that point to the original URI: instead, point to the remake- file
				int numAttrs = tempNode.getAttributes().getLength();
				for (int i = 0; i < numAttrs; i++){
					Attr attr = (Attr) tempNode.getAttributes().item(i);
					String attrValue = attr.getNodeValue();
					String[] fileName = originalURI.toString().split("/");

					if (attrValue.contains(fileName[fileName.length-1])) {
						attr.setNodeValue(remakeFile.toString());
					}


				}
			}


			if (tempNode.hasChildNodes()) {

				// loop through child nodes
				findPDDLNodes(tempNode.getChildNodes(), originalURI, remakeFile, pddlNodes);

			}

		}

	}

	private static boolean isPreconditionNode(Node check) {
		
		Node currentNode = check;
		
		while(currentNode.getParentNode() != null) {
			
			if (currentNode.getNodeName().toLowerCase().contains(PRECONDITION_IDENTIFIER))
				return true;
						
			currentNode = currentNode.getParentNode();
		}
		
		return false;
		
	}
	private static void printNodeData(Node tempNode) {

		if (tempNode.getNodeType() == Node.ELEMENT_NODE)
			System.out.println("Name: " + tempNode.getNodeName());

		if (tempNode.hasAttributes()) {
			int numAttrs = tempNode.getAttributes().getLength();
			System.out.println("Attributes:");

			for (int i = 0; i < numAttrs; i++){
				Attr attr = (Attr) tempNode.getAttributes().item(i);
				String attrName = attr.getNodeName();
				String attrValue = attr.getNodeValue();
				System.out.println("\t[" + attrName + "]=" + attrValue);
			}
		}

		if (tempNode.hasChildNodes())
			printNodeData(tempNode.getChildNodes());

	}

	private static void printNodeData(NodeList tempNodes) {

		for (int count = 0; count < tempNodes.getLength(); count++) {

			Node tempNode = tempNodes.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE)
				System.out.println("Name: " + tempNode.getNodeName());

			if (!tempNode.hasAttributes())
				continue;

			int numAttrs = tempNode.getAttributes().getLength();
			System.out.println("Attributes:");

			for (int i = 0; i < numAttrs; i++){
				Attr attr = (Attr) tempNode.getAttributes().item(i);
				String attrName = attr.getNodeName();
				String attrValue = attr.getNodeValue();
				System.out.println("\t[" + attrName + "]=" + attrValue);
			}

			if (tempNode.hasChildNodes())
				printNodeData(tempNode.getChildNodes());
		}

	}
	private static void printOnlyPDDL(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			boolean opened = false;

			if (tempNode.getNodeName().toLowerCase().contains("pddl")) {

				// make sure it's an element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

					opened = true;

					// get node name and value
					System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
					System.out.println("Node Value =" + tempNode.getTextContent());

					if (tempNode.hasAttributes()) {

						// get attributes names and values
						NamedNodeMap nodeMap = tempNode.getAttributes();

						for (int i = 0; i < nodeMap.getLength(); i++) {

							Node node = nodeMap.item(i);
							System.out.println("attr name : " + node.getNodeName());
							System.out.println("attr value : " + node.getNodeValue());

						}

					}
				}
			}

			if (tempNode.hasChildNodes()) {

				// loop again if has child nodes
				printOnlyPDDL(tempNode.getChildNodes());

			}
			if (opened)
				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

		}

	}

	
	private static void addPddxmlImport(NodeList nodeList, Document doc) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);
			boolean added = false;

			if (tempNode.getNodeName().toLowerCase().contains(OWL_NODE_IDENTIFIER)) { 

				Document xmlDoc = new DocumentImpl();
				//			Node toAdd = document.importNode(xmlDoc.createTextNode("placeholder" + p.getPlaceholderIndex()), true);

				// determine whether the pddl node was a result or a precondition, altering enclosing node hierarchy accordingly			
				Element root = null;

				root = xmlDoc.createElement("owl:imports");
				root.setAttribute("rdf:resource", PDDXML_PATH.toString());
				//placeHolderNode = xmlDoc.createTextNode("&lt;and>&lt;pred name=\"http://127.0.0.1:8000/health-scallops/ontology/FlightCompany_Ontology.owl#isValid\">&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#AccountData&lt;/param>&lt;/pred>&lt;pred name=\"http://127.0.0.1:8000/health-scallops/ontology/FlightCompany_Ontology.owl#isProvided\">&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#Flight&lt;/param>&lt;/pred>&lt;/and>");

				Node toAdd = doc.importNode(root, true);
				tempNode.appendChild(toAdd);
				added = true; // prevents infinite recursion

			}
			
			if (tempNode.hasChildNodes() && !added) {

				// loop through child nodes
				addPddxmlImport(tempNode.getChildNodes(), doc);

			}
	
		}



	}
	private static void deleteAllImports(NodeList nodeList, Document doc) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeName().toLowerCase().contains(IMPORT_NODE_IDENTIFIER)) { 

				Node parent = tempNode.getParentNode();
				parent.removeChild(tempNode);
				
			}
			
			if (tempNode.getNodeName().toLowerCase().contains(CONTACT_NODE_IDENTIFIER)) { 
				Node parent = tempNode.getParentNode();
				parent.removeChild(tempNode);
			}
			
			if (tempNode.hasChildNodes())
				deleteAllImports(tempNode.getChildNodes(), doc);

		}



	}
	
	
	private static void replacePDDLNodes(Document document, ArrayList<PDDLNode> nodes) {

		for (PDDLNode p: nodes){

			// delete original node
			Node pddlNode = p.getNode();
			Node parent = pddlNode.getParentNode();
			parent.removeChild(pddlNode);		
		
			if (PLACEHOLDER_STYLE) {
				
			deleteAllImports(document.getChildNodes(), document);
			addPddxmlImport(document.getChildNodes(), document);
				
			// replace with placeholder
			Document xmlDoc = new DocumentImpl();		
			Node item = null;
			Element root = null;
			Node placeHolderNode = xmlDoc.createTextNode("placeholder" + Integer.toString(p.getPlaceholderIndex()));

			// determine whether the pddl node was a result or a precondition, altering enclosing node hierarchy accordingly	
			if (parent.getNodeName().toLowerCase().contains(PRECONDITION_IDENTIFIER)) {
				root = xmlDoc.createElement("pddxml:PDDXML-Condition");
				root.setAttribute("rdf:ID", "PDDXML-Precondition");
				placeHolderNode = xmlDoc.createTextNode("&lt;and>&lt;pred name=\"http://127.0.0.1:8000/health-scallops/ontology/FlightCompany_Ontology.owl#isValid\">&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#AccountData&lt;/param>&lt;/pred>&lt;pred name=\"http://127.0.0.1:8000/health-scallops/ontology/FlightCompany_Ontology.owl#isProvided\">&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#Flight&lt;/param>&lt;/pred>&lt;/and>");

			}
			else if (parent.getNodeName().toLowerCase().contains(EFFECT_IDENTIFIER)) {
				root = xmlDoc.createElement("pddxml:PDDXML-Expression");
				root.setAttribute("rdf:ID", "PDDXML-Effect");
				placeHolderNode = xmlDoc.createTextNode("&lt;and>&lt;pred name=\"http://127.0.0.1:8000/health-scallops/ontology/Health-Scallops_Ontology.owl#isBookedFor\">&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#Flight&lt;/param>&lt;param>?http://127.0.0.1:8000/health-scallops/services/BookFlight.owl#Customer&lt;/param>&lt;/pred>&lt;/and>");
			}

			item = xmlDoc.createElement("expr:expressionBody");
			((Element)item).setAttribute("rdf:datatype", "http://www.w3.org/2001/XMLSchema#string");
			item.appendChild(placeHolderNode);
			root.appendChild(item);
			Node toAdd = document.importNode(root, true);
			parent.appendChild(toAdd);
			
		}
		}


	}
	
	public static void printOwlsFile(Document doc, File name) {
		
		DOMSource source = new DOMSource(doc);
		FileWriter writer;
		/*
		try {
			writer = new FileWriter(name.toString());
			StreamResult result = new StreamResult(writer);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} catch (IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			writer = new FileWriter(name.toString());
			StreamResult result = new StreamResult(writer);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (TransformerException e) {
			
			e.printStackTrace();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}

	}


	public static URI generateRemakeFile(URI ontologyURI, OWLS2PDDL owls2Pddl, ArrayList<PDDLNode> foundPddlNodes) {

		URI remakeFileUri = null;

		try {

			// read-in owl file
			File file = new File(ontologyURI);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			// determine the remake- file URI
			String remakeFileName = REMAKE_FILE_PREFIX + Paths.get(ontologyURI).getFileName().toString(); 
			File remakeFile = new File(owls2Pddl.getTempFolderPath().toFile(), remakeFileName);
			remakeFileUri = owls2Pddl.getTempFolderUri().resolve(remakeFileName);
			
			// scan the .owl file for PDDL
			if (doc.hasChildNodes()) 
				findPDDLNodes(doc.getChildNodes(), ontologyURI, remakeFileUri , foundPddlNodes);		
				
			if (foundPddlNodes.size() > 0) {
				
				System.out.println("pddl detected in " + ontologyURI);
				replacePDDLNodes(doc, foundPddlNodes);			
				
				System.out.println("Removing all OWL-S inputs and outputs!");
				removeInputsAndOutputs(doc.getChildNodes());
				
				printOwlsFile(doc, remakeFile);

			}
			
			else
				System.out.println("no pddl detected in " + ontologyURI);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (foundPddlNodes.size() > 0)
			return remakeFileUri;
		else
			return ontologyURI;

	}

}




