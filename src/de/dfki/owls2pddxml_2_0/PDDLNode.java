package de.dfki.owls2pddxml_2_0;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;

public class PDDLNode {

	private Node node;
	private String originalString;
	private int placeholderIndex;
	private boolean isPrecondition;
	
	public static String SERVICE_FOLDER_NAME = "services";

	public PDDLNode(Node node, int depth, boolean isPrecondition) {
		super();
		this.node = node;
		this.originalString = node.getTextContent();
		this.placeholderIndex = depth;
		this.isPrecondition = isPrecondition;

	}

	public Node getNode() {
		return node;
	}

	public String getPddlContent() {
		return node.getTextContent();
	}

	public int getPlaceholderIndex() {
		return placeholderIndex;
	}

	public boolean isPrecondition() {
		return this.isPrecondition;
	}

	public ArrayList<String> getAllVariables(){

		ArrayList<String> variables = new ArrayList<String>();

		// split according to all white space
		for (String s: node.getTextContent().split("\\s+")){

			if (!s.contains("?"))
				continue;
			
			s = s.replace(")", "");
			s = s.replace("?", "");
			
			try {
				URI validityCheck = new URI(s);
				if (validityCheck.isAbsolute())
					variables.add(s);				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		return variables;

	}

	public ArrayList<String> getAllParametersAsPddl(){

		ArrayList<String> parameters = new ArrayList<String>();

		// iterate line-by-line
		for (String s: node.getTextContent().split("\n")){

			String[] split = s.split("\\s+");
			
			if (split.length < 2)
				continue;
			
			StringBuffer predicateConverter = new StringBuffer();

			for (int i = 0; i < split.length; i++) { 
				String noSpaces = split[i].replace("\\s+", "");

				// predicates don't contain "?" -- this filters them out
				if (noSpaces.contains("?")) {

					noSpaces = noSpaces.replace("?", "");
					noSpaces = noSpaces.replace(")", "");

					try {
						URI validityCheck = new URI(noSpaces);
						if (validityCheck.isAbsolute())
							predicateConverter.append("?" + noSpaces + " - object\n");

					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			}
			
			String check = predicateConverter.toString();
			
			if (check.length() > 1)
			parameters.add(check);

			/*
			int check = s.indexOf("?http://");

			if (check > 0) {

				s = s.replace(")", "");
				// drop everything until the first parameter, then split according to whitespace
				// everything that follows the first parameter must be a parameter
				String[] split = s.substring(check).split("\\s+");
				StringBuffer predicateConverter = new StringBuffer();

				for (int i = 0; i < split.length; i++) { 
					String noSpaces = split[i].replace("\\s+", "");
					predicateConverter.append(noSpaces + " - object\n");
				}

				parameters.add(predicateConverter.toString());
			}

			 */

		}

		return parameters;

	}



	public ArrayList<String> getAllPredicatesAsPddl(){

		ArrayList<String> predicates = new ArrayList<String>();

		// iterate through the original PDDL string line-by-line,
		// assuming that any predicates would be expressed in a single line
		for (String s: node.getTextContent().split("\n")){

			// split according to whitespace to divide a predicate from its parameter(s)
			String[] split = s.split("\\s+");

			// a predicate has at least a name (1) and a parameter (2)
			if (split.length < 2)
				continue;

			StringBuffer predicateConverter = new StringBuffer();
			
			int parameterCount = 0;
			for (int i = 0; i < split.length; i++) {

				String predicateOrParameter = split[i];
				predicateOrParameter = predicateOrParameter.replace("(", "");
				URI test;

				try {
					// check whether this is a predicate
					test = new URI(predicateOrParameter);

					if (test.isAbsolute()) // a proxy for indicating whether a URI is proper
						predicateConverter.append(test.toString());

					else {

						// check if this is a parameter
						predicateOrParameter = predicateOrParameter.replace("?", "");
						test = new URI(predicateOrParameter);

						if (test.isAbsolute()) {
							predicateConverter.append(" ?individual-" + parameterCount + " - object");
							parameterCount++;
						}
					}
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (predicateConverter.length() < 2)
				continue;
			
			String bracketCheck = predicateConverter.toString();
			
			if (!bracketCheck.substring(0, 1).equals("("))
				bracketCheck = "(" + bracketCheck;
			
			if (!bracketCheck.substring(bracketCheck.length() - 1).equals(")"))
				bracketCheck = bracketCheck + ")";
			
			predicates.add(bracketCheck);

		}

		return predicates;

	}

	public ArrayList<String> getAllPredicates(){

		ArrayList<String> predicates = new ArrayList<String>();

		// split according to all white space
		for (String s: node.getTextContent().split("\\s+")){

			s = s.replace("(", "");

			try {

				// if the string is a valid URL and doesn't contain a question mark, then it's a predicate
				URI test = new URI(s);

				if (test.isAbsolute()) 
					predicates.add(s);

			}
			catch(Exception e){
				// not a valid URL; hence, not a predicate
			}


		}

		return predicates;
	}

	public static String replaceRemakeUrls(String toRemove, boolean preferServicesFolder) {
		// using jsoup (https://github.com/jhy/jsoup) to grab the names of all files on localhost
		List<URL> localHostFiles = new ArrayList<URL>();
		try {
			myCrawler(OWLS2PDDL.SERVER_URI.toString() + "/" + PDDLNode.SERVICE_FOLDER_NAME, localHostFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// check if there's a file matching the name of this remake-
		String[] toCheck = toRemove.split("\n");

		for (int i = 0; i < toCheck.length -1; i++) {

			if (!toRemove.split("\n")[i].contains(OwlsPreparser.REMAKE_FILE_PREFIX))
				continue;
			
			String check = toCheck[i];

			// clean the query
			int toDrop = toCheck[i].indexOf("#");

			if (toDrop > 0)
				toCheck[i] = toCheck[i].substring(0, toDrop);

			toDrop = toCheck[i].indexOf("http");

			if (toDrop > 0)
				toCheck[i] = toCheck[i].substring(toDrop);


			// switch out the remake-
			String completeOriginalHttp = originalFileOnServer(parseOriginalName(toCheck[i]), localHostFiles, preferServicesFolder);
			toRemove = toRemove.replaceAll(toCheck[i], completeOriginalHttp);

		}

		return toRemove;
	}

	public static void myCrawler(String url, List<URL> toFill) throws IOException{

		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		Elements links = doc.select("a[href]");

		for (Element i : links) {
			
			URL link = new URL(print("%s", i.attr("abs:href")));
			
			if (toFill.contains(link))
				continue;
			
			if (link.toString().endsWith("/")){
				myCrawler(link.toString(), toFill);}
			else {toFill.add(link);}
		}

	}
/*
	private static boolean hasSeenLink(String link, List<String> seenLinks) {
		
		for (String check: seenLinks)
			if (link.equals(check))
				return true;
			
		
		return false;
		
	}
	*/
	//Translates the link into a readable string object
	private static String print(String msg, Object... args){return String.format(msg, args);}


	private static String parseOriginalName(String remakeFile) {

		String[] splits = remakeFile.split("/");

		for (int i = splits.length - 1; i > 0; i--) {

			if (splits[i].contains(OwlsPreparser.REMAKE_FILE_PREFIX))
				return splits[i].replaceAll(OwlsPreparser.REMAKE_FILE_PREFIX, "");
		}


		return null;
	}

	private static String originalFileOnServer(String name, List<URL> serverFiles, boolean preferServicesFolder) {

		String answer = null;

		//int onlyFileName = name.indexOf("#");

		//if (onlyFileName > 0)
		//	name = name.substring(0, onlyFileName);

		// priority given to files that are in a "services" directory
		for (URL localFile: serverFiles) {

			// don't use remake- files
			if (localFile.toString().contains(OwlsPreparser.REMAKE_FILE_PREFIX))
				continue;

			if (localFile.toString().contains(name)) {

				String[] splitLocalFile = localFile.toString().split("/");
				String parentDirectory = splitLocalFile[splitLocalFile.length -2];

				if (preferServicesFolder && parentDirectory.equals(PDDLNode.SERVICE_FOLDER_NAME))
					return localFile.toString();
				else
					answer = localFile.toString();

			}
		}


		return answer;
	}


}
