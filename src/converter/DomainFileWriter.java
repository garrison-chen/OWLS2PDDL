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

import PDDXML_Datatypes.PDDXML_Actions;
import PDDXML_Datatypes.PDDXML_Predicates;
import PDDXML_Datatypes.PDDXML_Types;

public class DomainFileWriter {

	/**
	 * This class creates one file - the PDDXML planning 
	 * domain "Filename"_Domain.xml. The domain file ist structured like this:
	 * 1) List of object types
	 * 2) List of predicates
	 * 3) List of actions
	 */
	public DomainFileWriter() {
	}

	/**
	 * Creating a new file with name "NAME".
	 * @param OutputPath Absolute path
	 * @param Name Filename
	 * @param Types PDDXML_Types
	 * @param Predicates PDDXML_Predicates
	 * @param Actions PDDXML_Actions
	 */
	public DomainFileWriter(String OutputPath, String Name, PDDXML_Types Types,
			PDDXML_Predicates Predicates, PDDXML_Actions Actions) {
		try {
			FileOutputStream fos = new FileOutputStream(OutputPath + Name
					+ "_Domain.xml");
			Writer w = new BufferedWriter(new OutputStreamWriter(fos));
			w.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r");
			w
					.write("<define_domain xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \r xsi:noNamespaceSchemaLocation=\"./Domain.xsd\" name=\""
							+ Name + "\">" + "\r");
			w.write("<requirements>\r");
			w.write("<req>typing</req>\r");
			w.write("</requirements>\r");

			// inserting the types
			w.write("<types>\r");
			for (Iterator t = Types.elements.iterator(); t.hasNext();) {
				PDDXML_Types.TypeElement type = (PDDXML_Types.TypeElement) t
						.next();
				w.write("<type super=\"" + type.supertype + "\">\r");
				w.write("<t>" + type.type + "</t>\r");
				w.write("</type>\r");
			}
			w.write("</types>\r");

			// inserting the predicates
			w.write("<predicates>\r");
			for (Iterator p = Predicates.elements.iterator(); p.hasNext();) {
				PDDXML_Predicates.PredicateElement predicate = (PDDXML_Predicates.PredicateElement) p
						.next();
				w.write("<pred name=\"" + predicate.name + "\">\r");
				for (Iterator pm = predicate.paramList.iterator(); pm.hasNext();) {
					PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
							.next();
					w.write("<param type=\"" + parameter.type + "\">?"
							+ parameter.parameter + "</param>\r");
				}
				w.write("</pred>\r");
			}
			w.write("</predicates>\r");

			// inserting the actions
			w.write("<actions>\r");
			for (Iterator a = Actions.elements.iterator(); a.hasNext();) {
				PDDXML_Actions.ActionElement action = (PDDXML_Actions.ActionElement) a
						.next();
				w.write("<action name=\"" + action.name + "\">\r");
				if (action.qos != "")
					w.write("<QualityOfService>" + action.qos
							+ "</QualityOfService>\r");

				// Input parameter
				w.write("<parameters>\r");
				for (Iterator pm = action.inputParamList.iterator(); pm
						.hasNext();) {
					PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
							.next();
					w.write("<param type=\"" + parameter.type + "\">?"
							+ parameter.parameter + "</param>\r");
				}
				w.write("</parameters>\r");

				// Preconditions
				w.write("<precondition>\r");
				w.write("<and>\r");
				for (Iterator p = action.preconditionList.iterator(); p
						.hasNext();) {
					PDDXML_Predicates.PredicateElement predicate = (PDDXML_Predicates.PredicateElement) p
							.next();
					if (predicate.state == false)
						w.write("<not>\r");
					w.write("<pred name=\"" + predicate.name + "\">\r");
					for (Iterator pm = predicate.paramList.iterator(); pm
							.hasNext();) {
						PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
								.next();
						w
								.write("<param>?" + parameter.parameter
										+ "</param>\r");
					}
					w.write("</pred>\r");
					if (predicate.state == false)
						w.write("</not>\r");
				}
				w.write("</and>\r");
				w.write("</precondition>\r");

				//Effects
				w.write("<effect>\r");
				w.write("<and>\r");
				for (Iterator p = action.effectsList.iterator(); p.hasNext();) {
					PDDXML_Predicates.PredicateElement predicate = (PDDXML_Predicates.PredicateElement) p
							.next();
					if (predicate.state == false)
						w.write("<not>\r");
					w.write("<pred name=\"" + predicate.name + "\">\r");
					for (Iterator pm = predicate.paramList.iterator(); pm
							.hasNext();) {
						PDDXML_Predicates.ParamClass parameter = (PDDXML_Predicates.ParamClass) pm
								.next();
						w
								.write("<param>?" + parameter.parameter
										+ "</param>\r");
					}
					w.write("</pred>\r");
					if (predicate.state == false)
						w.write("</not>\r");
				}
				w.write("</and>\r");
				w.write("</effect>\r");
				w.write("</action>\r");
			}

			w.write("</actions>\r");

			w.write("</define_domain>");
			w.flush();
			w.close();
		} catch (Exception e) {
			System.err
					.println("DomainFileWriter: Error while creating domain file \n"
							+ e.getMessage());
			e.printStackTrace();
		}
	} // end of methode
}
