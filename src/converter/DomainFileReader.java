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

import PDDXML_Datatypes.PDDXML_Objects;
import PDDXML_Datatypes.PDDXML_Predicates;
import PDDXML_Datatypes.PDDXML_Types;
import PDDXML_Datatypes.PDDXML_Predicates.ParamClass;

public class DomainFileReader {

	/**
	 * Object hanling the list of <code>pddxml_types</code>
	 */
	public PDDXML_Types pddxml_types = new PDDXML_Types();

	/**
	 * Object hanling the list of <code>pddxml_predicates</code>
	 */
	public PDDXML_Predicates pddxml_predicates = new PDDXML_Predicates();

	/**
	 * Object hanling the list of <code>pddxml_initState</code>
	 */
	public PDDXML_Predicates pddxml_initState = new PDDXML_Predicates();

	/**
	 * Object hanling the list of <code>pddxml_objects</code>
	 */
	public PDDXML_Objects pddxml_objects = new PDDXML_Objects();

	/**
	 * <code>commandLevel</code> is only for internal use.
	 */

	/**
	 * <code>linePos</code> is only for internal use.
	 */
	private int commandLevel = 0, linePos = 0;

	/**
	 * <code>f</code> is a file-stream pointer
	 */
	private RandomAccessFile f;

	/**
	 * <code>additionalObjects</code> contains a list 
	 * of additional objects, which have to be included into pddxml_objects. 
	 * Only for internal use
	 */
	private ArrayList additionalObjects = new ArrayList();

	/**
	 * This object is the first to invoke when parsing the owl-S files.
	 * It starts with the "filename"InitialOntology.owl file and generates the
	 * types, objects, predicates and initstate.
	 * @param Name includes an absolut file-path including the file-name
	 */
	public DomainFileReader(String Name) {
		// add a predicates for handling the input and output of the services. This 
		// is not necessary, but should help the domain designer not to forget this kind
		// of predicate necessary for dealing with inputs and outputs.
		PDDXML_Predicates.ParamClass inputParam = pddxml_predicates
				.createNewParameter();
		inputParam.parameter = "object-parameter";
		inputParam.type = "object";
		
		PDDXML_Predicates.PredicateElement inputPredicate = pddxml_predicates
				.createNewPredicate();
		inputPredicate.name = "agentHasKnowledgeAbout";
		inputPredicate.paramList.add(inputParam);
		pddxml_predicates.elements.add(inputPredicate);
		
		
		// line is a variable containing one line read from the owl-file
		String line = "";
		try {
			f = new RandomAccessFile(Name, "r");
			boolean parsed = false;
			// read the owl-file till the end is reached
			while ((line = f.readLine()) != null
					&& !line.contains("</rdf:RDF>")) {
				this.linePos = 0;
				parsed = false;
				String firstWord = this.deleteEmptyChar(line).split("\"")[0];

				// pddl-type definition
				if (firstWord.equals("<owl:Class rdf:ID=")
						&& this.commandLevel == 2) {
					parsed = true;
					this
							.parsePDDXML_Type(line
									.split("<owl:Class rdf:ID=\"")[1]);
				}
				// pddl-type definition with #
				if (firstWord.equals("<owl:Class rdf:about=")
						&& this.commandLevel == 2) {
					parsed = true;
					this.parsePDDXML_Type2(line
							.split("<owl:Class rdf:about=\"#")[1]);
				}

				// pddl-predicate definition with and without # - obect properties
				if (firstWord.equals("<owl:ObjectProperty rdf:ID=")
						&& this.commandLevel == 2) {
					parsed = true;
					this.parsePDDXML_Predicate_OP(line
							.split("<owl:ObjectProperty rdf:ID=\"")[1]);
				}

				// pddl-predicate definition - datatype properties
				if (firstWord.equals("<owl:DatatypeProperty rdf:ID=")
						&& this.commandLevel == 2) {
					parsed = true;
					this.parsePDDXML_Predicate_DP(line
							.split("<owl:DatatypeProperty rdf:ID=\"")[1]);
				}

				// pddl-predicate definition - functional properties
				if (firstWord.equals("<owl:FunctionalProperty rdf:ID=")
						&& this.commandLevel == 2) {
					parsed = true;
					this.parsePDDXML_Predicate_FP(line
							.split("<owl:FunctionalProperty rdf:ID=\"")[1]);
				}

				// pddl-predicate definition - inverse functional properties 
				if (firstWord.equals("<owl:InverseFunctionalProperty rdf:ID=")
						&& this.commandLevel == 2) {
					parsed = true;
					this
							.parsePDDXML_Predicate_IFP(line
									.split("<owl:InverseFunctionalProperty rdf:ID=\"")[1]);
				}

				//pddl init-state parsing
				if (!parsed && this.commandLevel == 2)
					this.parsePDDXML_InitState(line);
			}
			f.close();
		} catch (FileNotFoundException e) // File does not exists
		{
			System.err.println("DomainFileReader: File not found ! " + e);
			e.printStackTrace();
		} catch (IOException e) // Read / write error
		{
			System.err.println("DomainFileReader: Read / write error" + e);
			e.printStackTrace();
		} catch (Exception e) // any other problems
		{
			System.err
					.println("DomainFileReader: An error occurs while parsing this line: "
							+ line);
			e.printStackTrace();
		}

		// generate additional objects based on the entries in additionalObjects
		this.generateAdditionalObjects();
	}

	//   
	/**
	 * Starts parsing the init-state
	 * @param Line Contains the string (line in the file) to parse
	 */
	private void parsePDDXML_InitState(String Line) {
		String help[] = new String[2];
		if (Line.charAt(2) == '<' && !Line.contains("owl:")) {
			help = Line.split("<")[1].split(" rdf:ID=\"");
			String type = help[0];
			String object = help[1].split("\"")[0];
			if (pddxml_types.containsType(type)) {
				// create a new opbject
				PDDXML_Objects.ObjectElement pddxml_object = pddxml_objects
						.createNewObject();
				pddxml_object.object = object;
				pddxml_object.type = type;
				this.pddxml_objects.elements.add(pddxml_object);

				// check if object has some addition predicates
				if (!Line.contains("/>") && true) {
					// paring all following predicates belonging to this object
					try {
						while ((Line = f.readLine()) != null
								&& !Line.contains("</" + type + ">")) {
							String predName = Line.split("<")[1].split(">")[0];
							this.parsePred(Line, predName, type, object);
						}// end of while
					} catch (Exception e) {
						System.err
								.println("DomainFileReader: Error while parsing init state: "
										+ e);
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 
	/**
	 * Parses a predicate and all its sub objects and sub-predicates recursivly
	 * @param OldLine Contains the previous string (line in the file) to parse
	 * @param PredicateName Name of the predicate to parse
	 * @param PrevParamType Previous parameter type
	 * @param PrevParamValue Previous parameter value
	 */
	private void parsePred(String OldLine, String PredicateName,
			String PrevParamType, String PrevParamValue) {
		PDDXML_Predicates.PredicateElement predicate = this.pddxml_predicates
				.clone(PredicateName.split(" ")[0]);
		this.setParamValueInPredicate(predicate, PrevParamType, PrevParamValue);
		PDDXML_Objects.ObjectElement pddxml_object = pddxml_objects
				.createNewObject();
		pddxml_object.object = PrevParamValue;
		pddxml_object.type = PrevParamType;
		this.pddxml_objects.elements.add(pddxml_object);
		String Line = "";
		try {
			if (PredicateName.contains("rdf:datatype")) {
				// create a new predicate
				while (!PredicateName.contains("#")) {
					if ((Line = f.readLine()) != null)
						OldLine = Line;
				}
				// pddxml_types.addPrimitivTypes(OldLine.split("#")[1].split("\"")[0]);
				PDDXML_Predicates.PredicateElement predicate2 = this.pddxml_predicates
						.clone(PredicateName.split(" ")[0]);
				String type = OldLine.split("#")[1].split("\"")[0], value = OldLine
						.split(">")[1].split("<")[0];
				this.setParamValueInPredicate(predicate2, PrevParamType,
						PrevParamValue);
				this.setParamValueInPredicate(predicate2, type, value);
				pddxml_initState.elements.add(predicate2);

				// create a new object
				pddxml_object = pddxml_objects.createNewObject();
				pddxml_object.object = value;
				pddxml_object.type = type;
				this.pddxml_objects.elements.add(pddxml_object);
			} else {
				if (PredicateName.contains("rdf:resource")) {
					// create a new predicate
					while (!PredicateName.contains("#")) {
						if ((Line = f.readLine()) != null)
							OldLine = Line;
					}
					PDDXML_Predicates.PredicateElement predicate2 = this.pddxml_predicates
							.clone(PredicateName.split(" ")[0]);
					String type = this.pddxml_objects.getTypeOfObject(OldLine
							.split("#")[1].split("\"")[0]), value = OldLine
							.split("#")[1].split("\"")[0];
					this.setParamValueInPredicate(predicate2, PrevParamType,
							PrevParamValue);
					this.setParamValueInPredicate(predicate2, type, value);
					pddxml_initState.elements.add(predicate2);

					// create a new object
					pddxml_object = pddxml_objects.createNewObject();
					pddxml_object.object = value;
					pddxml_object.type = type;
					this.pddxml_objects.elements.add(pddxml_object);
				} else {
					while ((Line = f.readLine()) != null
							&& !Line.contains("</" + PredicateName + ">")) {
						String nextElement = Line.split("<")[1].split(" ")[0]
								.split(">")[0];

						if (pddxml_types.containsType(nextElement)) {
							// input value for the predicate
							pddxml_object = pddxml_objects.createNewObject();
							pddxml_object.object = Line.split("rdf:ID=\"")[1]
									.split("\"")[0];
							pddxml_object.type = nextElement;
							this.pddxml_objects.elements.add(pddxml_object);
							PrevParamType = nextElement;
							PrevParamValue = Line.split("rdf:ID=\"")[1]
									.split("\"")[0];
							this.setParamValueInPredicate(predicate,
									nextElement, Line.split("rdf:ID=\"")[1]
											.split("\"")[0]);
						} // end if

						if (pddxml_predicates.containsPredicate(nextElement)) {
							if (!Line.contains("resource")) {
								if (Line.contains("/" + nextElement)
										|| Line.contains("/>")) {

									// create new predicate with value 
									PDDXML_Predicates.PredicateElement subPredicate2 = this.pddxml_predicates
											.clone(nextElement);//predicate.clonePredicateElement();//this.pddxml_initState.createNewPredicate();
									for (Iterator p = predicate.paramList
											.iterator(); p.hasNext();) {
										PDDXML_Predicates.ParamClass param2 = (PDDXML_Predicates.ParamClass) p
												.next();
										this.setParamValueInPredicate(
												subPredicate2, param2.type,
												param2.parameter);
									}
									this.setParamValueInPredicate(
											subPredicate2, Line.split("#")[1]
													.split("\"")[0],
											Line.split(">")[1].split("<")[0]);
									pddxml_initState.elements
											.add(subPredicate2);
									pddxml_object = pddxml_objects
											.createNewObject();
									pddxml_object.object = Line.split(">")[1]
											.split("<")[0];
									pddxml_object.type = Line.split("#")[1]
											.split("\"")[0];
									this.pddxml_objects.elements
											.add(pddxml_object);

								} else {
									// a new predicate described in more than one line is found -> start recursin
									this.parsePred(Line, nextElement,
											PrevParamType, PrevParamValue);
								}
							} else {
								PDDXML_Predicates.PredicateElement subPredicate2 = this.pddxml_predicates
										.clone(nextElement);//predicate.clonePredicateElement();//this.pddxml_initState.createNewPredicate();
								for (Iterator p = predicate.paramList
										.iterator(); p.hasNext();) {
									PDDXML_Predicates.ParamClass param2 = (PDDXML_Predicates.ParamClass) p
											.next();
									this.setParamValueInPredicate(
											subPredicate2, param2.type,
											param2.parameter);
								}
								boolean f = this.setParamValueInPredicate(
										subPredicate2, this.pddxml_objects
												.getTypeOfObject(Line
														.split("#")[1]
														.split("\"")[0]), Line
												.split("#")[1].split("\"")[0]);
								pddxml_initState.elements.add(subPredicate2);
								pddxml_object = pddxml_objects
										.createNewObject();
								pddxml_object.object = Line.split("#")[1]
										.split("\"")[0];
								pddxml_object.type = this.pddxml_objects
										.getTypeOfObject(Line.split("#")[1]
												.split("\"")[0]);
								this.pddxml_objects.elements.add(pddxml_object);

							}
						} // end if
					} // end while 
					pddxml_initState.elements.add(predicate);
					this.linePos = 0;
				}
			}
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing a predicate in the init-state: "
							+ Line);
			e.printStackTrace();
		}
	}

	//****************************************************
	//      PDDXML_Predicates Parsing
	//****************************************************

	/**
	 * Generates additional pddxml object types for the case, the domain description is faulty.
	 * For every used object of a given type, a new type-object of root-supertype "object" is created.
	 */
	private void generateAdditionalObjects() {
		for (Iterator p = this.additionalObjects.iterator(); p.hasNext();) {
			String typeName = (String) p.next();
			if (!pddxml_types.containsType(typeName)) {
				PDDXML_Types.TypeElement type = pddxml_types.createNewType();
				type.type = typeName;
				type.supertype = "object";
				pddxml_types.elements.add(type);
			} // end if
		} // end for
	}

	/**
	 * Parsing an owl-s "InverseFunctionalProperty".
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Predicate_IFP(String Line) {
		try {
			boolean exists_domain = false;
			boolean exists_range = false;
			String help;
			PDDXML_Predicates.PredicateElement predicate = this.pddxml_predicates
					.createNewPredicate();
			String result[] = Line.split("\"");
			predicate.name = result[0];
			if (!result[1].equals("/>")) {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:InverseFunctionalProperty>")) {
					PDDXML_Predicates.ParamClass param = this.pddxml_predicates
							.createNewParameter();
					help = this.deleteEmptyChar(Line);

					if (help.contains("<rdfs:range rdf:resource=")) {
						exists_range = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_range-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 

					if (help.contains("<rdfs:domain rdf:resource=")) {
						exists_domain = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_domain-parameter";
						predicate.paramList.add(param);
					} // end of if-operator
				} // end of while
			} // end if
			this.pddxml_predicates.elements.add(predicate);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing a inverse functional property: "
							+ e);
			e.printStackTrace();
		}
	}

	/**
	 * Parsing an owl-s "FunctionalProperty".
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Predicate_FP(String Line) {
		try {
			boolean exists_domain = false;
			boolean exists_range = false;
			String help;
			PDDXML_Predicates.PredicateElement predicate = this.pddxml_predicates
					.createNewPredicate();
			String result[] = Line.split("\"");
			predicate.name = result[0];
			if (!result[1].equals("/>")) {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:FunctionalProperty>")) {
					PDDXML_Predicates.ParamClass param = this.pddxml_predicates
							.createNewParameter();
					help = this.deleteEmptyChar(Line);

					if (help.contains("<rdfs:range rdf:resource=")) {
						exists_range = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_range-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 

					if (help.contains("<rdfs:domain rdf:resource=")) {
						exists_domain = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_domain-parameter";
						predicate.paramList.add(param);
					} // end of if-operator
				} // end of while
			} // end if
			this.pddxml_predicates.elements.add(predicate);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing a functional property: "
							+ e);
		}
	}

	/**
	 * Parsing an owl-s "DatatypeProperty".
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Predicate_DP(String Line) {
		try {
			boolean exists_domain = false;
			boolean exists_range = false;
			String help;
			PDDXML_Predicates.PredicateElement predicate = this.pddxml_predicates
					.createNewPredicate();
			String result[] = Line.split("\"");
			predicate.name = result[0];
			if (!result[1].equals("/>")) {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:DatatypeProperty>")) {
					PDDXML_Predicates.ParamClass param = this.pddxml_predicates
							.createNewParameter();
					help = this.deleteEmptyChar(Line);

					if (help.contains("<rdfs:range rdf:resource=")) {
						exists_range = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_range-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 

					if (help.contains("<rdfs:domain rdf:resource=")) {
						exists_domain = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_domain-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 
				} // end of while
			}
			this.pddxml_predicates.elements.add(predicate);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing a datatype property: "
							+ e);
			e.printStackTrace();
		}
	}

	/**
	 * Parsing an owl-s "ObjectProperty".
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Predicate_OP(String Line) {
		try {
			boolean exists_domain = false;
			boolean exists_range = false;
			ArrayList collection = new ArrayList();
			String help;
			PDDXML_Predicates.PredicateElement predicate = this.pddxml_predicates
					.createNewPredicate();
			String result[] = Line.split("\"");
			predicate.name = result[0];
			if (!result[1].equals("/>")) {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:ObjectProperty>")) {
					PDDXML_Predicates.ParamClass param = this.pddxml_predicates
							.createNewParameter();
					help = this.deleteEmptyChar(Line);

					if (help.contains("<rdfs:range rdf:resource=")) {
						exists_range = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_range-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 

					if (help.contains("<rdfs:domain rdf:resource=")) {
						exists_domain = true;
						param.type = Line.split("#")[1].split("\"")[0];
						param.parameter = param.type + "_domain-parameter";
						predicate.paramList.add(param);
					} // end of if-operator 
				} // end of while
			} // end if
			this.pddxml_predicates.elements.add(predicate);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing a object property: "
							+ e);
			e.printStackTrace();
		}
	}

	//****************************************************
	//      PDDXML_Type Parsing
	//****************************************************

	/**
	 * Parsing an owl-s type with "rdf:about=\"#"".
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Type2(String Line) {
		try {
			String help, pddxml_supertype = "object";
			String result[] = Line.split("\"");
			String pddxml_type = result[0];
			if (result[1].equals("/>"))
				pddxml_supertype = "object";
			else {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:Class>")) {
					help = this.deleteEmptyChar(Line);
					if (help.equals("<rdfs:subClassOf>")) {
						if ((Line = f.readLine()) != null) {
							try {
								pddxml_supertype = Line.split("rdf:about=\"#")[1]
										.split("\"")[0];
							} catch (Exception e) {
								pddxml_supertype = Line.split("rdf:ID=\"")[1]
										.split("\"")[0];
							}
							additionalObjects.add(pddxml_supertype);
							break;
						} // end if
					} // end if
					else {
						if (help.contains("<rdfs:subClassOf rdf:resource=")) {
							try {
								pddxml_supertype = Line
										.split("rdf:resource=\"#")[1]
										.split("\"")[0];
							} catch (Exception e) {
								System.err
										.println("DomainFileReader: Error while parsing pddxml types with '<rdfs:subClassOf rdf:resource=#' - ErrorNo1: "
												+ Line);
								e.printStackTrace();
							}
							additionalObjects.add(pddxml_supertype);
							break;
						}
					}
				}
			}
			PDDXML_Types.TypeElement type = this.pddxml_types.createNewType();
			type.supertype = pddxml_supertype;
			type.type = pddxml_type;
			if (!pddxml_types.containsType(pddxml_type))
				this.pddxml_types.elements.add(type);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing pddxml types with #: "
							+ e);
			e.printStackTrace();
		}
	}

	/**
	 * Parsing an owl-s type with "rdf:ID=\"". 
	 * @param Line Contains the string (line in the file) to parse.
	 */
	private void parsePDDXML_Type(String Line) {
		try {
			String pddxml_supertype = "object";
			String result[] = Line.split("\"");
			String pddxml_type = result[0];
			if (Line.contains("/>"))
				pddxml_supertype = "object";
			else {
				while ((Line = f.readLine()) != null
						&& !Line.contains("</owl:Class>")) {
					pddxml_supertype = this.deleteEmptyChar(Line);
					if (pddxml_supertype.equals("<rdfs:subClassOf>")) {
						if ((Line = f.readLine()) != null) {
							try {
								pddxml_supertype = Line.split("rdf:ID=\"")[1]
										.split("\"")[0];
							} catch (Exception e) {
								pddxml_supertype = Line.split("rdf:about=\"#")[1]
										.split("\"")[0];
							}
							additionalObjects.add(pddxml_supertype);
							break;
						} // end if
					} // end if
					else {
						if (pddxml_supertype
								.contains("<rdfs:subClassOf rdf:resource=")) {
							try {
								pddxml_supertype = Line
										.split("rdf:resource=\"#")[1]
										.split("\"")[0];
							} catch (Exception ee) {
								System.err
										.println("DomainFileReader: Error while parsing pddxml types with '<rdfs:subClassOf rdf:resource=#' - ErrorNo2: "
												+ Line);
								ee.printStackTrace();
							}
							additionalObjects.add(pddxml_supertype);
							break;
						}
					}
				}
			}
			PDDXML_Types.TypeElement type = this.pddxml_types.createNewType();
			type.supertype = pddxml_supertype;
			type.type = pddxml_type;
			if (!pddxml_types.containsType(pddxml_type))
				this.pddxml_types.elements.add(type);
		} catch (Exception e) {
			System.err
					.println("DomainFileReader: Error while parsing pddxml types: "
							+ e);
			e.printStackTrace();
		}
	}

	/**
	 * Deletes empty spaces before a word-string and increases the hierarchy-number of the 
	 * owl-command in the owl-file.
	 * @param Line Contains the string (line in the file) to parse.
	 * @return String without leading empty space
	 */
	private String deleteEmptyChar(String Line) {
		int charnumber = 0;
		while (Line.charAt(charnumber) == ' ')
			charnumber++;
		this.commandLevel = charnumber;
		this.linePos = charnumber;
		return Line.substring(charnumber);
	}

	/**
	 * Sets a value of a parameter of a predicate with respect to the tpye of this value. Returns true 
	 * if a relevant parameter could be found and the value set, else false.
	 * @param Pred PredicateElement.
	 * @param Type Type of the parameter which value's should be changed.
	 * @param Value String set the parameter
	 * @return boolean 
	 */
	private boolean setParamValueInPredicate(
			PDDXML_Predicates.PredicateElement Pred, String Type, String Value) {
		boolean result = false;
		for (Iterator p = Pred.paramList.iterator(); p.hasNext();) {
			ParamClass param = (ParamClass) p.next();
			if (param.type.equals(Type)) {
				param.parameter = Value;
				result = true;
				break;
			} else {
				if (pddxml_types.isSubTypeOf(param.type, Type)) {
					param.parameter = Value;
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Creates and adds a new predicate to the initial state, that describes the local knowledge.
	 * @param FC Reference to the file converter object.
	 */
	public void createKnowledge(FileConverter FC) {
		for (Iterator p = FC.pddxml_objects.elements.iterator(); p.hasNext();) {
			PDDXML_Objects.ObjectElement object = (PDDXML_Objects.ObjectElement) p
					.next();
			PDDXML_Predicates.PredicateElement predicate = FC.pddxml_predicates
					.createNewPredicate();
			predicate.name = "agentHasKnowledgeAbout";
			PDDXML_Predicates.ParamClass param2 = FC.pddxml_predicates
					.createNewParameter();
			param2.parameter = object.object;
			predicate.paramList.add(param2);
			FC.pddxml_initstate.elements.add(predicate);
		}
	}

	/**
	 * Parsing the file containing the agents local knowledge.
	 * @param Path String, absolut path.
	 * @param FC Reference to a file converter object.
	 */
	public void readAgentKnowledge(String Path, FileConverter FC) {
		String line = "";
		try {
			f = new RandomAccessFile(Path + "AgentHasKnowledgeAbout.xml", "r");

			while ((line = f.readLine()) != null) {
				if (line.contains("<agentHasKnowledgeAbout")) {
					PDDXML_Predicates.PredicateElement inputPredicate = pddxml_predicates
							.createNewPredicate();
					inputPredicate.name = "agentHasKnowledgeAbout";
					PDDXML_Predicates.ParamClass param = pddxml_predicates
							.createNewParameter();
					param.parameter = line.split("resource=\"#")[1].split("\"")[0];
					param.type = pddxml_objects
							.getTypeOfObject(param.parameter);
					inputPredicate.paramList.add(param);
					pddxml_initState.elements.add(inputPredicate);
				}
			}
			f.close();
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
}
