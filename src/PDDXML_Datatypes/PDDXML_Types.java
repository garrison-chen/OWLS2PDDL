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
 * This class contains all pddxml types which stand for owl-S class.
 * PDDXML_Types is a container, that includes a list of TypeElements.
 * Each TypeElement is a representative of an owl-s class and contains
 * a refence to its super-class. These pddxml_types are listed in the 
 * PDDXML_Types.element datatstructure.
 */
public class PDDXML_Types {

	public PDDXML_Types() {
	}

	// contains all types in the pddxml-datastructure
	public ArrayList elements = new ArrayList();

	// description for an owl-s class in pddxml
	public class TypeElement {

		public TypeElement() {
		}

		public String supertype = "object";

		public String type = "";
	}

	// creates a new type object and returns it
	public TypeElement createNewType() {
		return new TypeElement();
	}

	// checks whether a type exists in the element list of before parsed types and
	// returns a boolean as result.
	public boolean containsType(String Type) {
		boolean result = false;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			TypeElement type = (TypeElement) i.next();
			if (type.type.equals(Type)) {
				result = true;
				break;
			}
		}
		return result;
	}

	// searchs for a type in the elements-list by the given "Type"-name
	public TypeElement find(String Type) {
		TypeElement result = null;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			TypeElement type = (TypeElement) i.next();
			if (type.type.equals(Type)) {
				result = type;
				break;
			}
		}
		return result;
	}

	// adds a type with super-class "object" to the element list. 
	// So only the name "Type" has to be given.
	public void addPrimitivTypes(String Type) {
		TypeElement te = new TypeElement();
		te.type = Type;
		if (!this.containsType(Type))
			this.elements.add(te);
	}

	// checks whether a given "Type" is a subtype of "SuperType" and
	// returns a boolean
	public boolean isSubTypeOf(String SuperType, String Type) {
		boolean result = false;
		if (SuperType.equals(Type))
			result = true;
		else {
			TypeElement te = this.find(Type);
			if (te == null)
				result = false;
			else {
				if (te.type.equals(SuperType))
					result = true;
				else
					result = isSubTypeOf(SuperType, te.supertype);
			}
		}
		return result;
	}

	// makes the list of predicates unique and removes double entries	
	public void removeDoubleElements() {
		boolean halt = false;
		ArrayList removables = new ArrayList();
		for (int i = 0; i < this.elements.size(); i++) {
			TypeElement te = (TypeElement) this.elements.get(i);
			for (int j = i + 1; j < this.elements.size(); j++) {
				TypeElement ite = (TypeElement) this.elements.get(j);
				if (te.type.equals(ite.type))
					removables.add(ite);
			}

		}
		for (Iterator h = removables.iterator(); h.hasNext();) {
			TypeElement te = (TypeElement) h.next();
			this.elements.remove(te);
		}
	}

}
