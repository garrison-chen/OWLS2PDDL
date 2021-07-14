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
 * This class contains all pddxml objects which stand for owl-S objects
 * given in the ontology by the instantiation of a type. PDDXML_Objects 
 * is a container, that includes a list of ObjectElements. Each ObjectElement 
 * is a representative of an instantiated owl-s type in the ontology file. 
 * These objects are listed in the PDDXML_Objects.element datatstructure.
 */
public class PDDXML_Objects {

	public PDDXML_Objects() {
	}

	// description for an owl-s object in pddxml
	public class ObjectElement {

		public ObjectElement() {
		}

		public String object = "";

		public String type = "";
	}

	public ObjectElement createNewObject() {
		return new ObjectElement();
	}

	// Searches for a type of a given object by its name (=.object) and return it
	// if it exists, or "" if no object with name Object exists.
	public String getTypeOfObject(String Object) {
		String result = "";
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			ObjectElement obj = (ObjectElement) i.next();
			if (obj.object.equals(Object)) {
				result = obj.type;
				break;
			}
		}
		return result;
	}

	// Checks whether a given object (by its name Object) still exists in the
	// pddxml_objects list and returns a boolean as result of this search.
	public boolean contains(String Object) {
		boolean result = false;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			ObjectElement obj = (ObjectElement) i.next();
			if (obj.object.equals(Object)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// Checks whether an object of a given type exists in the
	// parse pddxml_objects list and returns a boolean as result of this search.
	public boolean containsObjectOfType(String Type) {
		boolean result = false;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			ObjectElement obj = (ObjectElement) i.next();
			if (obj.type.equals(Type)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// makes the list of objects unique and removes double entries
	public void removeDoubleElements() {
		ArrayList removables = new ArrayList();
		for (int i = 0; i < this.elements.size(); i++) {
			ObjectElement te = (ObjectElement) this.elements.get(i);
			for (int j = i + 1; j < this.elements.size(); j++) {
				ObjectElement ite = (ObjectElement) this.elements.get(j);
				if (te.object.equals(ite.object))
					removables.add(ite);
			}

		}
		for (Iterator h = removables.iterator(); h.hasNext();) {
			ObjectElement te = (ObjectElement) h.next();
			this.elements.remove(te);
		}
	}

	// contains the list of pddxml_objects
	public ArrayList elements = new ArrayList();
}
