/*
 * Created on 12.01.2005
 *
 */
package PDDXML_Datatypes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Andreas Gerber
 *
 * This class contains all pddxml predicates which stand for owl-S property.
 * PDDXML_Predicates is a container, that includes a list of PredicateElements.
 * Each PredicateElement is a representative of an owl-s property (object-, 
 * datatype-, functional-property). These predicates are listed in the 
 * PDDXML_Predicates.element datatstructure.
 */
public class PDDXML_Predicates {

	public PDDXML_Predicates() {
	}

	// contains all predicates in the pddxml-datastructure
	public ArrayList elements = new ArrayList();

	// ParamClass contains one parameter values of a predicate-element
	public class ParamClass {
		public ParamClass() {
		}

		public String type = "";

		public String parameter = "";

		// makes a copy of this parameter-objects and returns it as a result
		public ParamClass cloneParam() {
			ParamClass result = new ParamClass();
			result.type = this.type;
			result.parameter = this.parameter;
			return result;
		}
	}

	// Creates a new parameter object an returns it
	public ParamClass createNewParameter() {
		return new ParamClass();
	}

	// description for an owl-s property in pddxml as a predicate with a dynamic 
	// number of parameters (normally 2 for "range", "domain").
	public class PredicateElement {

		public PredicateElement() {
		}

		// name of this predicate
		public String name = "";

		// state of this predicate, if it is false => a "not" will be inserted in the pddxml file 
		public boolean state = true;

		// list of predicate parameters, containing objects of type ParamClass
		public ArrayList paramList = new ArrayList();

		// this method makes a copy of each element of the parameter list and returns
		// it as an arraylist
		public ArrayList cloneParameter() {
			ArrayList result = new ArrayList();
			for (Iterator p = paramList.iterator(); p.hasNext();) {
				ParamClass param = (ParamClass) p.next();
				ParamClass n = new ParamClass();
				n.parameter = param.parameter;
				n.type = param.type;
				result.add(n);
			}
			return result;
		}

		public boolean containsParameter(ParamClass Param) {
			boolean result = false;
			for (Iterator p = this.paramList.iterator(); p.hasNext();) {
				ParamClass param = (ParamClass) p.next();
				if (param.parameter.equals(Param.parameter)
						&& param.type.equals(Param.type)) {
					result = true;
					break;
				}
			}
			return false;
		}

		public boolean predicateEqual(PredicateElement Pred) {
			boolean result = true;
			if (Pred.name.equals(this.name) && Pred.state == this.state) {
				for (Iterator p = Pred.paramList.iterator(); p.hasNext();) {
					ParamClass param = (ParamClass) p.next();
					if (!this.containsParameter(param))
						result = false;
				}
			} else
				result = false;
			return result;
		}

		// creates a copy of this predicate-object and returns it
		public PredicateElement clonePredicateElement() {
			PredicateElement result = new PredicateElement();
			result.paramList = this.cloneParameter();
			result.name = this.name;
			result.state = this.state;
			return result;
		}

		// this methode set a value of a parameter of this predicate. Therefore
		// the relevant parameter is searched for by its type. The first parameter
		// found with the given "Type" is set the "Value". If there are more 
		// parameters with the same type, than there is no change to distinquish 
		// between them and a FALSE VALUE SETTING is possible !!!
		public void setValue(String Type, String Value) {
			for (Iterator p = this.paramList.iterator(); p.hasNext();) {
				ParamClass param = (ParamClass) p.next();
				if (param.type.equals(Type))
					param.parameter = Value;
			}
		}

		// tests whether this predicate is equal to a given other predicate and returns a boolean
		public boolean equal(PredicateElement Predicate) {
			boolean result = true;
			if (this.name.equals(Predicate.name)) {
				for (int i = 0; i < this.paramList.size(); i++) {
					ParamClass param1 = (ParamClass) this.paramList.get(i);
					ParamClass param2 = (ParamClass) Predicate.paramList.get(i);
					if (!param1.parameter.equals(param2.parameter)
							|| !param1.type.equals(param2.type)) {
						result = false;
						break;
					}
				}
			} else
				result = false;
			return result;
		}
	}

	// creates a new predicate object and returns its
	public PredicateElement createNewPredicate() {
		return new PredicateElement();
	}

	public boolean containsPredicate(PredicateElement Pred) {
		boolean result = false;
		for (Iterator p = this.elements.iterator(); p.hasNext();) {
			PredicateElement pred = (PredicateElement) p.next();
			if (pred.predicateEqual(Pred)) {
				result = true;
				break;
			}
		}
		return false;
	}

	// makes a 1:1 copy of a predicate and returns it
	public PredicateElement clone(String Name) {
		PredicateElement result = new PredicateElement();
		for (Iterator p = this.elements.iterator(); p.hasNext();) {
			PredicateElement pred = (PredicateElement) p.next();
			if (pred.name.equals(Name)) {
				result.name = pred.name;
				result.state = pred.state;
				result.paramList = pred.cloneParameter();
			}
		}
		return result;
	}

	// searchs for a predicate in the elements-list by the given "Name"
	public PredicateElement find(String Name) {
		PredicateElement result = null;
		for (Iterator p = this.elements.iterator(); p.hasNext();) {
			PredicateElement pred = (PredicateElement) p.next();
			if (pred.name.equals(Name)) {
				result = pred;
			}
		}
		return result;
	}

	// Checks whether a predicate by its name still exists in the element list and
	// returns a boolean
	public boolean containsPredicate(String PredName) {
		boolean result = false;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			PredicateElement pred = (PredicateElement) i.next();
			if (pred.name.equals(PredName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// makes the list of predicates unique and removes double entries
	public void removeDoubleElements() {
		ArrayList removables = new ArrayList();
		for (int i = 0; i < this.elements.size(); i++) {
			PredicateElement te = (PredicateElement) this.elements.get(i);
			for (int j = i + 1; j < this.elements.size(); j++) {
				PredicateElement ite = (PredicateElement) this.elements.get(j);
				if (te.equal(ite))
					removables.add(ite);
			}
		}
		for (Iterator h = removables.iterator(); h.hasNext();) {
			PredicateElement te = (PredicateElement) h.next();
			this.elements.remove(te);
		}
	}
}
