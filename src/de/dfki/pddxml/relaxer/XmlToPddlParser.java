package de.dfki.pddxml.relaxer;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import de.dfki.owls2pddxml_2_0.PDDLNode;
import de.dfki.owls2pddxml_2_0.XmlUtil;

public class XmlToPddlParser {

	public static final String[] NEST_NAMES = {"and", "not", "imply", "at start", "exists", "at end", "over all", "when", "variables", "variable"};

	// literally print the node name when encountered
	public static final String[] LITERAL_PRINT_NAMES = {"or", "variable"};

	public static final String[] PREDICATE_MARKERS = {"pred"};

	public static final String PARAMETER_MARKER = "param";

	public static final String NAME_ATTRIBUTE = "name";
	
	/*
	 * Takes a pddxml expression (xmlString) and a nascent PDDL file (buffer), converts the pddxml to pddl, then appends the
	 * converted PDDL to the PDDL file
	 */
	public static void appendConvertedPDDL(String xmlString, StringBuffer buffer, boolean noInitialAnd) {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;  
        
    	try {  
			
			builder = factory.newDocumentBuilder();  
			Document check = builder.parse(new InputSource(new StringReader(xmlString)));
			Node rootNode = check.getDocumentElement();
			listUntilNewNest(rootNode, buffer, noInitialAnd);
			//editOrStatements(buffer);
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}
		
		
	}
	
	public static int lastCurvyBracketInex(StringBuffer buffer) {
		
		for (int i = buffer.length() - 1; i >= 0; i--) {
			
			if (buffer.charAt(i) == ')')
				return i;
		}
		
		return -1;
	}
	
	public static void appendConvertedPDDL(String xmlString, StringBuffer buffer) {
		
		appendConvertedPDDL(xmlString, buffer, false); 
		
	}
	/*
	public static ArrayList<PDDLNode> getPreconditionNodes(ArrayList<PDDLNode> nodes) {
		
		ArrayList<PDDLNode> answer = new ArrayList<PDDLNode>();
		
		for (int i = 0; i < nodes.size(); i++) {
			
			Node currentNode = nodes.get(i).getNode();
			Node checkAgain = currentNode.getParentNode();
			
			while(currentNode.getParentNode() != null) {
				
				String check = currentNode.getNodeName().toLowerCase();
				if (currentNode.getNodeName().toLowerCase().contains("precondition")) {
					answer.add(nodes.get(i));
					break;
				}
				
				currentNode = currentNode.getParentNode();
			}	
		}
		
		return answer;
	}
	*/
	
	public static URI getUri(String fullActionName) {
		
		// assumes fullActionName is a one-line String such as:
		// http://127.0.0.1:8080/health-scallops/services/CreateFlightAccount.owl#CreateFlightAccountService
		// which would return:
		// http://127.0.0.1:8080/health-scallops/services/CreateFlightAccount.owl
		
		Pattern regexPattern = Pattern.compile("(http://\\S+.owl)");
		Matcher m = regexPattern.matcher(fullActionName);
		
		while (m.find()) {
			
		    return URI.create(m.group(1));
		}
		
		return null;
	}
	
//	public static void editOrStatements(StringBuffer buffer) {
//		
//		// if a series of "or" are found between bracket statements,
//		// then insert a new set of brackets around the series, with "or" at the front of the bracket:
//		// (statement 1) or (statement 2) or (statement ) -> (or (statement 1) (statement 2) (statement 3))
//		
//		int check = buffer.indexOf(" or (");
//		
//
//
////		Pattern p = Pattern.compile("\\((.*?)\\)",Pattern.DOTALL);
//		
//		Pattern p = Pattern.compile("\\((.*?)\\)(\\s+)or(\\s)\\((.*?)\\)",Pattern.DOTALL);
//		
//		Matcher matcher = p.matcher(buffer);
//		while(matcher.find())
//		{
//		    System.out.println("found match: "+matcher.group(0));
//		}
//		
//	}
	
	public static void editOrStatements(StringBuffer buffer) {
		
		Pattern p = Pattern.compile("or\\s\\((.*?)\\)(\\s+or\\s\\((.*?)\\))+");
//		Pattern p = Pattern.compile("\\((.*?)\\)(\\s+[^\\W\\d_]+\\s\\((.*?)\\))+");

		Matcher matcher = p.matcher(buffer);
		
		StringBuffer fixed = new StringBuffer();
		
		while(matcher.find())
			matcher.appendReplacement(fixed, fixOrStatement(matcher.group(0)));
			
		matcher.appendTail(fixed);
		buffer.delete(0, buffer.length());
		
		buffer.append(fixed);

	}


	public static String fixOrStatement(String s) {
		
		s = s.replaceAll("(\\s+or\\s)+\\(", "\n\t\t\\(");
				
		StringBuilder builder = new StringBuilder(s.replaceAll("or\\s", ""));
		builder.insert(0, "(or \t");
		builder.insert(builder.length(), ")");

		return builder.toString();
	}
	
	public static void beginNest(Node rootNode, StringBuffer buffer, boolean skipFirstAnd) {		
		
		
		// skip all "exists"
		if (rootNode.getNodeName().equals("exists"))
			return;
		
		if (!skipFirstAnd) {
			buffer.append("(");
			buffer.append(rootNode.getNodeName());
		}
		
		buffer.append("\n");
		
		if (rootNode.getNodeName().equals("variable")) {
			buffer.append("(");
			buffer.append(rootNode.getFirstChild().getNodeValue());
			buffer.append(")");
			
		} else

		for (Node n: XmlUtil.asList(rootNode.getChildNodes())) {
			buffer.append("\t");
			listUntilNewNest(n, buffer, false);
		}
		
		

		if (!skipFirstAnd)
			buffer.append("\n )");
		
		editOrStatements(buffer);
	}

	public static void listUntilNewNest(Node rootNode, StringBuffer buffer, boolean skipFirstAnd) {

		boolean additionalNewLine = false;
		String nodeName = rootNode.getNodeName();
		
		if (Arrays.asList(NEST_NAMES).contains(rootNode.getNodeName())) 
			beginNest(rootNode, buffer, skipFirstAnd);

		else {

			// deal with root node, then with its children
			if (Arrays.asList(NEST_NAMES).contains(rootNode.getNodeName())) {
				buffer.append(" " + rootNode.getNodeName() + " ");
			}

			if (Arrays.asList(LITERAL_PRINT_NAMES).contains(rootNode.getNodeName())) {
				buffer.append(" " + rootNode.getNodeName() + " ");

//				if (rootNode.getNodeName().equals("variable")) {
//					
//					buffer.append(" (");
//					buffer.append(rootNode.getFirstChild().getNodeValue());
//					
//					if (XmlUtil.asList(rootNode.getChildNodes()).size() < 2) {
//						
//						buffer.append("(");
//						buffer.append(rootNode.getFirstChild().getNodeValue());
//						
//					}
//						return;
//					
//				}
				
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
					listUntilNewNest(n, buffer, false);
				}

				if (Arrays.asList(NEST_NAMES).contains(n.getNodeName())) 
					beginNest(rootNode, buffer, false);

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
