/*
This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.

Contact and Copyright

The mobile service selector iSeM (1.1), S2P2P, DSDR and S3M Peer was developed
at the German Research Center for Articifial Intelligence DFKI GmbH (http://ww.dfki.de)
in Saarbr�cken, Germany.

Copyright: DFKI, 2014, All Rights Reserved.

For bug reports, other technical problems and feature requests please contact Patrick Kapahnke: patrick.kapahnke@dfki.de

For general scientific inquiries please contact PD Dr. Matthias Klusch: klusch@dfki.de
 */

package de.dfki.owls2pddxml_2_0;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Action;
import de.dfki.pddxml.relaxer.Actions;
import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Domain;
import de.dfki.pddxml.relaxer.GoalState;
import de.dfki.pddxml.relaxer.InitState;
import de.dfki.pddxml.relaxer.PDDXMLObject;
import de.dfki.pddxml.relaxer.PDDXMLObjects;
import de.dfki.pddxml.relaxer.Predicate;
import de.dfki.pddxml.relaxer.Predicates;
import de.dfki.pddxml.relaxer.Problem;
import de.dfki.pddxml.relaxer.Requirements;
import de.dfki.pddxml.relaxer.Types;

public class OWLS2PDDL {

	private static URI	OWLS_SERVICE = URI.create("http://www.daml.org/services/owl-s/1.1/Service.owl#Service");

	public static final Logger LOGGER = Logger.getLogger(OWLS2PDDL.class
			.getName());

	private DomainHelper fConverterContext;

	private Conjunction fInitState;
	private Conjunction fGoalState;

	private OWLKnownFactConverter fInitKnownOWLFactConverter;
	private OWLFactConverter fInitUnknownOWLFactConverter;
	private OWLKnownFactConverter fGoalOWLFactConverter;

	private OWLSServiceConverter fOWLSServiceConverter;

	private String fDomainName;

	private PDDXMLExpressionCleaner fPDDXMLExpressionCleaner;

	/**
	 * OWL ontology manager used for the whole conversion process.
	 */
	private OWLOntologyManager owlManager = null;

	private OWLOntologyManager owlGoalManager = null;

	// temp folder is always in the root directory of the server hosted at 8080
	public static final String TEMP_FOLDER_NAME = "/tmp/";
	public static final URI SERVER_URI = URI.create("http://127.0.0.1:8080");

	private Map<URI, ArrayList<PDDLNode>> filesWithPddl;
	private Path serverLocalPath; 
	private Path tempFolderPath;
	private URI tempFolderUri;

	public OWLS2PDDL(String domainName, String serverDirectory) {
		this(domainName, new File("."), OWLManager.createOWLOntologyManager());
		filesWithPddl = new HashMap<URI, ArrayList<PDDLNode>>();
		parseServerDirectory(serverDirectory);
	}

	public OWLS2PDDL(String domainName) {
		this(domainName, new File("."), OWLManager.createOWLOntologyManager());
	}

	public OWLS2PDDL(String domainName, File cacheDirectory) {
		this(domainName, cacheDirectory, OWLManager.createOWLOntologyManager());
	}

	public OWLS2PDDL(String domainName, OWLOntologyManager owlManager) {
		this(domainName, new File("."), owlManager);
	}

	public OWLS2PDDL(String domainName, File cacheDirectory,
			OWLOntologyManager owlManager) {
		this.owlManager = owlManager;
		// this is a new manager, because it must not be related to the overall
		// manager representing the current world state
		this.owlGoalManager = OWLManager.createOWLOntologyManager();

		fConverterContext = new DomainHelper();
		fInitState = new Conjunction();
		fGoalState = new Conjunction();
		StateHelper initialStateHelper = new StateHelper();
		fInitKnownOWLFactConverter = new OWLKnownFactConverter(
				fConverterContext, initialStateHelper, owlManager);
		fInitUnknownOWLFactConverter = new OWLFactConverter(fConverterContext,
				initialStateHelper, owlManager);
		fGoalOWLFactConverter = new OWLKnownFactConverter(fConverterContext,
				new StateHelper(), this.owlGoalManager);
		fOWLSServiceConverter = new OWLSServiceConverter(fConverterContext,
				owlManager);
		fDomainName = domainName;
		fPDDXMLExpressionCleaner = new PDDXMLExpressionCleaner();
	}

	public void addToInitialState(URI ontologyURI) throws OWLException {
		OWLOntology ontology = owlManager
				.loadOntologyFromPhysicalURI(ontologyURI);

		for (OWLIndividual individual : ontology.getIndividualsInSignature()) {
			individual.accept(fInitKnownOWLFactConverter);
			fInitState.addExpression(fInitKnownOWLFactConverter
					.getPDDXMLExpression());
		}
	}

	public void addToGoalState(URI ontologyURI) throws OWLException {
		// use temporary OWL manager for goal, since this ontology does not
		// relate to the overall world state
		OWLOntology ontology = owlGoalManager
				.loadOntologyFromPhysicalURI(ontologyURI);

		for (OWLIndividual individual : ontology.getIndividualsInSignature()) {
			individual.accept(fGoalOWLFactConverter);
			fGoalState.addExpression(fGoalOWLFactConverter
					.getPDDXMLExpression());

			individual.accept(fInitUnknownOWLFactConverter);
			fInitState.addExpression(fInitUnknownOWLFactConverter
					.getPDDXMLExpression());
		}
	}

	private void parseServerDirectory(String serverLocalPath) {
		try {
			File f = new File(serverLocalPath);

			if (!f.exists() || !f.isDirectory())
				throw new Exception("Unable to find server directory. Please doublecheck the provided path:\n" + serverLocalPath);

			else {
				this.serverLocalPath = Paths.get(serverLocalPath);
				this.tempFolderPath = this.serverLocalPath.resolve(TEMP_FOLDER_NAME.replace("/", ""));
				
				if(!tempFolderPath.toFile().canWrite()) 
					throw new Exception("Java does not have write privileges in:\n" + tempFolderPath.toString() + "\nEither make a directory called \"tmp\" in this directory, or move the server directory where Java has write privileges");

				if (!tempFolderPath.toFile().exists())
					tempFolderPath.toFile().mkdirs();
				
				this.tempFolderUri = SERVER_URI.resolve(TEMP_FOLDER_NAME);
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public URI getTempFolderUri() {
		return this.tempFolderUri;
	}
	
	public Path getTempFolderPath() {
		return this.tempFolderPath;
	}

	private void deleteTempDirectory() {

		if (tempFolderPath.toFile().exists()) {

			try {
				for (File tempFile : tempFolderPath.toFile().listFiles())
					Files.delete(tempFile.toPath());
				Files.delete(tempFolderPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

	public Map<URI, ArrayList<PDDLNode>> getFilesWithPddl(){
		return this.filesWithPddl;
	}
	
	public void addServices(URI ontologyURI) throws OWLException, IOException {

		OWLOntology serviceOnt = null;
		ArrayList<PDDLNode> pddlNodes = new ArrayList<PDDLNode>();
		URI noPddl = OwlsPreparser.generateRemakeFile(ontologyURI, this, pddlNodes);
		serviceOnt = owlManager.loadOntology(noPddl);
		
		if(pddlNodes.size() > 0)
			filesWithPddl.put(noPddl, pddlNodes);
			
		//OWLOntology serviceOnt = owlManager.loadOntologyFromPhysicalURI(ontologyURI);
		// find all services (URI)
		Set<OWLIndividual> services = owlManager.getOWLDataFactory().getOWLClass(OWLS_SERVICE).getIndividuals(serviceOnt);

		URI serviceUri;
		for(OWLIndividual service : services) {
			// TODO only named services accepted for now...
			if(service.isAnonymous())
				continue;

			serviceUri = ((OWLIndividual) service).getURI();
			System.out.println("Adding service " + serviceUri.toString());
			fOWLSServiceConverter.convert(serviceUri);
		}	

		//		OWLKnowledgeBase kb = OWLFactory.createKB();
		//		kb.read(ontologyURI);
		//		// for(Service s : (List<Service>)kb.getServices(false))
		//		for (Service s : kb.getServices()) {
		//			System.out.println("Adding service " + s);
		//			fOWLSServiceConverter.convert(s);
		//		}
	}

	public Problem getProblem() {
		Problem problem = new Problem();

		problem.setDomain(fDomainName);

		PDDXMLObjects objects = new PDDXMLObjects();
		objects.addPDDXMLObject(fConverterContext.getPDDXMLObjects().toArray(
				new PDDXMLObject[0]));
		problem.setPDDXMLObjects(objects);

		fInitState.enter(fPDDXMLExpressionCleaner);
		fGoalState.enter(fPDDXMLExpressionCleaner);

		InitState initState = new InitState();
		initState.setExpression(fInitState);
		problem.setInitState(initState);

		GoalState goalState = new GoalState();
		goalState.setExpression(fGoalState);
		problem.setGoalState(goalState);

		return problem;
	}

	public Domain getDomain() {
		Domain domain = new Domain();

		domain.setName(fDomainName);

		Requirements reqs = new Requirements();
		domain.setRequirements(reqs);

		Types types = new Types();
		domain.setTypes(types);

		Predicates predicates = new Predicates();
		predicates.addPredicate(fConverterContext.getPredicates().toArray(
				new Predicate[0]));
		domain.setPredicates(predicates);

		Actions actions = new Actions();
		for (Iterator<Action> it = fConverterContext.actionIterator(); it
				.hasNext();) {
			actions.addAction(it.next());
		}
		actions.enter(fPDDXMLExpressionCleaner);
		domain.setActions(actions);

		return domain;
	}

}
