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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import PDDXML_Datatypes.PDDXML_Objects;
import PDDXML_Datatypes.PDDXML_Predicates;

public class ProblemFileWriter {

	/**
	 * Empty constructor.
	 */
	public ProblemFileWriter() {
	}

	/**
	 * This class creates one file - the planning problem
	 * "'Name'_PB.xml". The domain file ist structured like this:
	 * 1) List of objects
	 * 2) init state
	 * 3) goal state
	 * @param OutputPath Absolute path
	 * @param Name First part of the output file name. "_pb.xml" is appended.
	 * @param Objects PDDXML objects
	 * @param InitState Initial state description
	 * @param GoalState Goal  state description
	 */
	public ProblemFileWriter(String OutputPath, String Name,
			PDDXML_Objects Objects, PDDXML_Predicates InitState,
			PDDXML_Predicates GoalState) {
		try {
			FileOutputStream fos = new FileOutputStream(OutputPath + Name
					+ "_PB.xml");
			Writer w = new BufferedWriter(new OutputStreamWriter(fos));
			w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r");
			w
					.write("<define_problem xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \r xsi:noNamespaceSchemaLocation=\"./Domain.xsd\" name=\""
							+ Name + "_PD\">" + "\r");
			w.write("<domain>" + Name + "</domain>\r");

			// inserting the objects
			w.write("<objects>\r");
			for (Iterator o = Objects.elements.iterator(); o.hasNext();) {
				PDDXML_Objects.ObjectElement object = (PDDXML_Objects.ObjectElement) o
						.next();
				w.write("<object type=\"" + object.type + "\">" + object.object
						+ "</object>\r");
			}
			w.write("</objects>\r");

			// inserting the predicates of the init state
			w.write("<init>\r");
			w.write("<and>\r");
			for (Iterator p = InitState.elements.iterator(); p.hasNext();) {
				PDDXML_Predicates.PredicateElement predicate = (PDDXML_Predicates.PredicateElement) p
						.next();
				w.write("<pred name=\"" + predicate.name + "\">\r");
				for (Iterator pm = predicate.paramList.iterator(); pm.hasNext();) {
					PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
							.next();
					w.write("<param>" + parameter.parameter + "</param>\r");
				}
				w.write("</pred>\r");
			}
			w.write("</and>\r");
			w.write("</init>\r");

			// inserting the predicates of the goal state
			w.write("<goal>\r");
			w.write("<and>\r");
			for (Iterator p = GoalState.elements.iterator(); p.hasNext();) {
				PDDXML_Predicates.PredicateElement predicate = (PDDXML_Predicates.PredicateElement) p
						.next();
				w.write("<pred name=\"" + predicate.name + "\">\r");
				for (Iterator pm = predicate.paramList.iterator(); pm.hasNext();) {
					PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
							.next();
					w.write("<param>" + parameter.parameter + "</param>\r");
				}
				w.write("</pred>\r");
			}
			w.write("</and>\r");
			w.write("</goal>\r");
			w.write("</define_problem>");
			w.flush();
			w.close();
		} catch (Exception e) {
			System.err
					.println("ProblemFileWriter: Error while creating domain file \n"
							+ e.getMessage());
			e.printStackTrace();
		}
	} // end of methode

}
