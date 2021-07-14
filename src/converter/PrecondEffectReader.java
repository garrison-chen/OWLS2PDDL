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

public class PrecondEffectReader {

	private RandomAccessFile f;

	private FileConverter fc = null;

	private PDDXML_Objects helpObjectList = new PDDXML_Objects();

	private ArrayList delActions = new ArrayList();

	/**
	 * Empty constructor.
	 */
	public PrecondEffectReader() {
	}

	/**
	 * Reads and parses the _PreconditionsAndEffects.xml-file which contains the precondition and effect 
	 * description of the services.
	 */
	public PrecondEffectReader(String Name, FileConverter FC) {
		fc = FC;
		String line = "";
		try {
			f = new RandomAccessFile(Name, "r");

			while ((line = f.readLine()) != null
					&& !line.contains("</define_domain>")) {
				if (line.contains("<service name")) {
					String serviceName = line.split("<service name\"")[1]
							.split("\"")[0];
					this.parseService(serviceName);
				}
			}
			f.close();
			for (Iterator k = delActions.iterator(); k.hasNext();) {
				String actionName = (String) k.next();
				fc.pddxml_actions.removeAction(actionName);
				//System.out.println("Service removed: " + actionName);
			}

		} catch (FileNotFoundException e) {
			System.err.println("PrecondEffectReader: File does not exists! "
					+ e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("PrecondEffectReader: read - write error: " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.err
					.println("PrecondEffectReader: Error while parsing the preconditions and effects! "
							+ line);
			e.printStackTrace();
		}
	}

	/**
	 * Parses the entires of a service and adds the preconditions, effects and qos-value to the 
	 * service given by name.
	 * @param ServiceName Name of a service to parse the preconditions, effects and qos-values.
	 */
	private void parseService(String ServiceName) {
		String line = "";
		boolean parsePrecond = false;
		boolean parseEffects = false;
		boolean predState = true;
		PDDXML_Actions.ActionElement action = fc.pddxml_actions
				.find(ServiceName);
		try {
			while ((line = f.readLine()) != null
					&& !line.contains("</service>")) {
				if (action != null) {
					if (line.contains("<qos>")) {
						action.qos = line.split("<qos>")[1].split("</qos>")[0];
					}
					if (line.contains("<precondition>"))
						parsePrecond = true;
					if (line.contains("</precondition>"))
						parsePrecond = false;
					if (line.contains("<effect>"))
						parseEffects = true;
					if (line.contains("</effect>"))
						parseEffects = false;
					if (line.contains("<not>"))
						predState = false;
					if (line.contains("</not>"))
						predState = true;
					if (line.contains("<pred name")) {
						PDDXML_Predicates.PredicateElement result = this
								.parsePredicate(line, action);
						if (result != null) {
							for (Iterator p = result.paramList.iterator(); p
									.hasNext();) {
								PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) p
										.next();
								if (!action.hasInputParameter(param.parameter)) {
									System.out
											.println("No input variables found! "
													+ action.name
													+ "   "
													+ param.parameter
													+ "  "
													+ line);
									result = null;
									break;
								}
							}
						} else
							System.out.println("Predicate not found: "
									+ action.name + "   " + line);
						if (result != null) {
							result.state = predState;
							if (parsePrecond)
								action.preconditionList.add(result);
							if (parseEffects)
								action.effectsList.add(result);
						} else {
							delActions.add(ServiceName);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err
					.println("PrecondEffectReader: Error while parsing the servide description! "
							+ line);
			e.printStackTrace();
		}
	}

	/**
	 * Parses a predicate
	 * @param Line Line (string) in the parsed file.
	 * @param Action Reference to the pddxml action element.
	 * @return New predicate element.
	 */
	private PDDXML_Predicates.PredicateElement parsePredicate(String Line,
			PDDXML_Actions.ActionElement Action) {
		PDDXML_Predicates.PredicateElement result = fc.pddxml_predicates
				.createNewPredicate();
		String predName = Line.split("pred name=\"")[1].split("\"")[0];
		if (fc.pddxml_predicates.containsPredicate(predName)) {
			result = fc.pddxml_predicates.clone(predName);
			if (result.name != "") {
				ArrayList param = new ArrayList();
				try {
					while ((Line = f.readLine()) != null
							&& !Line.contains("</pred>")) {
						if (Line.contains("<param>"))
							param.add(Line.replace('?', '#').split("#")[1]
									.split("</")[0]);
					}
				} catch (Exception e) {
					System.err
							.println("PrecondEffectReader: Error(1) while parsing a predicate! "
									+ Line);
					e.printStackTrace();
				}
				for (int i = 0; i < param.size(); i++) {
					String paramName = (String) param.get(i);
					PDDXML_Predicates.ParamClass paramElement = (PDDXML_Predicates.ParamClass) result.paramList
							.get(i);
					paramElement.parameter = paramName;
				}
				Action.removeDoubleInputParameter();
			} else
				result = null;
		} else {
			System.out
					.println("PrecondEffectReader: Error(2) while parsing a predicate: "
							+ predName);
			result = null;
		}
		return result;
	}
}
