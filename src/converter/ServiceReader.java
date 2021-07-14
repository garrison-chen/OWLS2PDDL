/*
 * OWLS2PDDL Converter
 *
 * Copyright (C) 2005 DFKI GmbH, Germany
 * Developed by Andreas Gerber, Matthias Klusch
 *
 * The code is free for non-commercial use only.
 * You can redistribute it and/or modify it under the terms
 * of the Mozilla Public License version 1.1  as
 * published by the Mozilla Foundation at
 * http://www.mozilla.org/MPL/MPL-1.1.txt
 */

package converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

import PDDXML_Datatypes.PDDXML_Actions;
import PDDXML_Datatypes.PDDXML_Objects;
import PDDXML_Datatypes.PDDXML_Predicates;

public class ServiceReader {

	private RandomAccessFile f;

	private FileConverter fc = null;

	private PDDXML_Objects helpObjectList = new PDDXML_Objects();

	private PDDXML_Objects helpObjectList2 = new PDDXML_Objects();

	private String line = "";

	private ArrayList input = new ArrayList();

	private ArrayList output = new ArrayList();

	private PDDXML_Actions.ActionElement action;

	/**
	 * Empty constructor.
	 */
	public ServiceReader() {
	}

	/**
	 * Parses the service file of the owl-description and build an internal data structure.
	 * @param Name File name
	 * @param FC Reference to the file converter object.
	 */
	public ServiceReader(String Name, FileConverter FC) {
		fc = FC;
		action = fc.pddxml_actions.createNewAction();
		helpObjectList2 = FC.pddxml_objects;
		try {
			f = new RandomAccessFile(Name, "r");
			boolean AtomicProcess = false;

			while ((line = f.readLine()) != null
					&& !line.contains("</rdf:RDF>")) {

				if (line.contains("<process:AtomicProcess")) {
					PDDXML_Actions.ActionElement ac = fc.pddxml_actions
							.find(line.split("process:AtomicProcess rdf:ID=\"")[1]
									.split("_Model")[0]);
					if (ac != null)
						action = ac;
					AtomicProcess = true;
				}
				if (line.contains("process:Output ")) {
					// create pddxml_object
					String objectName = line.split("rdf:ID=\"")[1].split("\"")[0];
					while ((line = f.readLine()) != null
							&& !line.contains("</process:Output>")) {
						if (line.contains("rdf:datatype")) {
							while (!line.contains("#"))
								line = f.readLine();
							PDDXML_Objects.ObjectElement object = fc.pddxml_objects
									.createNewObject();
							object.object = objectName;
							object.type = line.split(".owl")[1].split("#")[1]
									.split("</process:parameterType>")[0];
							helpObjectList.elements.add(object);
							PDDXML_Predicates.ParamClass param = fc.pddxml_predicates
									.createNewParameter();
							param.parameter = object.object;
							param.type = object.type;
							if (AtomicProcess) {
								output.add(param);
							}
						}
					}
				}

				if (line.contains("process:Input ")) {
					String objectName = line.split("rdf:ID=\"")[1].split("\"")[0];
					while ((line = f.readLine()) != null
							&& !line.contains("</process:Input>")) {
						if (line.contains("rdf:datatype")) {
							while (!line.contains("#")) {
								line = f.readLine();
							}
							PDDXML_Objects.ObjectElement object = fc.pddxml_objects
									.createNewObject();
							object.object = objectName;
							object.type = line.split(".owl")[1].split("#")[1]
									.split("</process:parameterType>")[0];
							helpObjectList.elements.add(object);
							PDDXML_Predicates.ParamClass param = fc.pddxml_predicates
									.createNewParameter();
							param.parameter = object.object;
							param.type = object.type;
							if (AtomicProcess) {
								input.add(param);
							}
						}
					}
				}

				if (line.contains("<process:hasInput")) {
					PDDXML_Predicates.ParamClass param = fc.pddxml_predicates
							.createNewParameter();
					if (!line.contains("rdf:resource=\"#")) {
						while ((line = f.readLine()) != null
								&& !line.contains("</process:hasInput>")) {
							if (line.contains("rdf:datatype"))
								param.type = line.split(".owl")[1].split("#")[1]
										.split("</process:parameterType>")[0];
							if (line.contains("process:Input rdf:ID"))
								param.parameter = line.split("rdf:ID=\"")[1]
										.split("\"")[0];
						}
					} else {
						param.parameter = line.split("rdf:resource=\"#")[1]
								.split("\"")[0];
						param.type = helpObjectList
								.getTypeOfObject(param.parameter);
					}
					input.add(param);
				}
				if (line.contains("<process:hasOutput")
						&& line.contains("rdf:resource=\"#")) {
					PDDXML_Predicates.ParamClass param = fc.pddxml_predicates
							.createNewParameter();
					if (!line.contains("rdf:resource=\"#")) {
						while ((line = f.readLine()) != null
								&& !line.contains("</process:hasOutput>")) {
							if (line.contains("rdf:datatype"))
								param.type = line.split(".owl")[1].split("#")[1]
										.split("</process:parameterType>")[0];
							if (line.contains("process:Output rdf:ID"))
								param.parameter = line.split("rdf:ID=\"")[1]
										.split("\"")[0];
						}
					} else {
						param.parameter = line.split("rdf:resource=\"#")[1]
								.split("\"")[0];
						param.type = helpObjectList
								.getTypeOfObject(param.parameter);
					}
					output.add(param);
				}

				if (line.contains("<service:Service")) {
					action = fc.pddxml_actions.createNewAction();
					action.name = line.split("rdf:ID=\"")[1].split("\"")[0];
					fc.pddxml_actions.elements.add(action);
					AtomicProcess = true;
				}
				if (false && line.contains("</profile:Profile>"))
					action = fc.pddxml_actions.createNewAction();
				if (line.contains("</process:AtomicProcess>")
						|| line.contains("</service:Service>")) {
					// generate predicate out of the input of an owl-s service and the parameter as an input-value
					AtomicProcess = false;
					for (Iterator i = input.iterator(); i.hasNext();) {
						PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) i
								.next();
						PDDXML_Predicates.PredicateElement predicate = fc.pddxml_predicates
								.createNewPredicate();
						predicate.name = "agentHasKnowledgeAbout";
						PDDXML_Predicates.ParamClass param2 = fc.pddxml_predicates
								.createNewParameter();
						param2.parameter = param.parameter;
						predicate.paramList.add(param2);
						action.inputParamList.add(param);
						action.preconditionList.add(predicate);
					}

					// generate predicate out of the output of an owl-s service and the parameter as an input-value
					for (Iterator i = output.iterator(); i.hasNext();) {
						PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) i
								.next();
						PDDXML_Predicates.PredicateElement predicate = fc.pddxml_predicates
								.createNewPredicate();
						predicate.name = "agentHasKnowledgeAbout";
						PDDXML_Predicates.ParamClass param2 = fc.pddxml_predicates
								.createNewParameter();
						param2.parameter = param.parameter;
						predicate.paramList.add(param2);
						action.inputParamList.add(param);
						action.effectsList.add(predicate);
					}
					action.removeDoubleInputParameter();
					action.removeDoublePrecond();
					action.removeDoubleEffect();
					input = new ArrayList();
					output = new ArrayList();

				}
			} // end of while
			f.close();
		} catch (FileNotFoundException e) // File does not exists
		{
			System.err.println("ServiceReader: File not found ! " + e);
			e.printStackTrace();
		} catch (IOException e) // Read / write error
		{
			System.err.println("ServiceReader: Read / write error" + e);
			e.printStackTrace();
		} catch (Exception e) // any other problems
		{
			System.err
					.println("ServiceReader: An error occurs while parsing this line: "
							+ Name + "  " + line);
			e.printStackTrace();
		}
	}
}
