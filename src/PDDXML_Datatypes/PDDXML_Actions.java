/*
 * Created on 12.01.2005
 *
 */
package PDDXML_Datatypes;

import java.util.ArrayList;
import java.util.Iterator;

import PDDXML_Datatypes.PDDXML_Predicates.PredicateElement;

/**
 * @author Andreas Gerber
 *
 * This class contains all pddxml actions which stand for owl-S services.
 * PDDXML_Actions is a container, that includes a list of ActionElements.
 * Each ActionElement is a representative of an owl-s service. These actions
 * are listed in the PDDXML_Actions.element datatstructure.
 */
public class PDDXML_Actions {

	public PDDXML_Actions() {
	}

	// description for an owl-s service in pddxml
	public class ActionElement {

		public ActionElement() {
		}

		public String name = "";

		public String qos = "";

		public ActionElement cloneAction() {
			ActionElement result = new ActionElement();
			result.name = this.name;
			result.qos = this.qos;
			for (Iterator i = this.inputParamList.iterator(); i.hasNext();) {
				PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) i
						.next();
				result.inputParamList.add(param.cloneParam());
			}
			for (Iterator i = this.effectsList.iterator(); i.hasNext();) {
				PDDXML_Predicates.PredicateElement pred = (PDDXML_Predicates.PredicateElement) i
						.next();
				result.effectsList.add(pred.clonePredicateElement());
			}
			for (Iterator i = this.preconditionList.iterator(); i.hasNext();) {
				PDDXML_Predicates.PredicateElement pred = (PDDXML_Predicates.PredicateElement) i
						.next();
				result.preconditionList.add(pred.clonePredicateElement());
			}
			return result;
		}

		// List of PDDXML_Predicates.ParamClass for the input parameters of an owls service
		public ArrayList inputParamList = new ArrayList();

		// List of PDDXML_Predicates for the preconditions and input predicates 
		// "agentHasKnowledgeAbout" of an owls service
		public ArrayList preconditionList = new ArrayList();

		// List of PDDXML_Predicates for the effects and output predicates 
		// "agentHasKnowledgeAbout" of an owls service.
		public ArrayList effectsList = new ArrayList();

		public boolean hasInputParameter(String Name) {
			boolean result = false;
			for (Iterator p = this.inputParamList.iterator(); p.hasNext();) {
				PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) p
						.next();
				if (param.parameter.equals(Name)) {
					result = true;
					break;
				}
			}
			return result;
		}

		public void removeDoublePrecond() {
			ArrayList removables = new ArrayList();
			for (int i = 0; i < this.preconditionList.size(); i++) {
				PredicateElement te = (PredicateElement) this.preconditionList
						.get(i);
				for (int j = i + 1; j < this.preconditionList.size(); j++) {
					PredicateElement ite = (PredicateElement) this.preconditionList
							.get(j);
					if (te.equal(ite))
						removables.add(ite);
				}
			}
			for (Iterator h = removables.iterator(); h.hasNext();) {
				PredicateElement te = (PredicateElement) h.next();
				this.preconditionList.remove(te);
			}
		}

		public void removeDoubleEffect() {
			ArrayList removables = new ArrayList();
			for (int i = 0; i < this.effectsList.size(); i++) {
				PredicateElement te = (PredicateElement) this.effectsList
						.get(i);
				for (int j = i + 1; j < this.effectsList.size(); j++) {
					PredicateElement ite = (PredicateElement) this.effectsList
							.get(j);
					if (te.equal(ite))
						removables.add(ite);
				}
			}
			for (Iterator h = removables.iterator(); h.hasNext();) {
				PredicateElement te = (PredicateElement) h.next();
				this.effectsList.remove(te);
			}
		}

		/*		public boolean containsPrecond(PredicateElement Pred) {
		 boolean result = false;
		 for (Iterator p = this.preconditionList.iterator(); p.hasNext();) {
		 PredicateElement pred= (PredicateElement)p.next();
		 if (pred.predicateEqual(Pred)) {
		 result = true;
		 break;
		 }
		 }
		 return false;
		 }

		 
		 public boolean containsEffect(PredicateElement Pred) {
		 boolean result = false;
		 for (Iterator p = this.preconditionList.iterator(); p.hasNext();) {
		 PredicateElement pred= (PredicateElement)p.next();
		 if (pred.predicateEqual(Pred)) {
		 result = true;
		 break;
		 }
		 }
		 return false;
		 }*/

		public void removeDoubleInputParameter() {
			boolean halt = false;
			ArrayList removables = new ArrayList();
			for (int i = 0; i < this.inputParamList.size(); i++) {
				PDDXML_Predicates.ParamClass te = (PDDXML_Predicates.ParamClass) this.inputParamList
						.get(i);
				for (int j = i + 1; j < this.inputParamList.size(); j++) {
					PDDXML_Predicates.ParamClass ite = (PDDXML_Predicates.ParamClass) this.inputParamList
							.get(j);
					if (te.parameter.equals(ite.parameter))
						removables.add(ite);
				}

			}
			for (Iterator h = removables.iterator(); h.hasNext();) {
				PDDXML_Predicates.ParamClass te = (PDDXML_Predicates.ParamClass) h
						.next();
				this.inputParamList.remove(te);
			}
		}
	}

	public ActionElement createNewAction() {
		return new ActionElement();
	}

	// contains all parsed services in pddxml
	public ArrayList elements = new ArrayList();

	// makes the list of actions unique and removes double entries
	public void removeDoubleElements() {
		boolean halt = false;
		ArrayList removables = new ArrayList();
		for (int i = 0; i < this.elements.size(); i++) {
			ActionElement te = (ActionElement) this.elements.get(i);
			for (int j = i + 1; j < this.elements.size(); j++) {
				ActionElement ite = (ActionElement) this.elements.get(j);
				if (te.name.equals(ite.name))
					removables.add(ite);
			}

		}
		for (Iterator h = removables.iterator(); h.hasNext();) {
			ActionElement te = (ActionElement) h.next();
			this.elements.remove(te);
		}
	}

	public boolean removeAction(String Name) {
		boolean result = false;
		ActionElement ae = null;
		for (int i = 0; i < this.elements.size(); i++) {
			ActionElement ae1 = (ActionElement) this.elements.get(i);
			if (ae1.name.equals(Name)) {
				ae = ae1;
				break;
			}
		}
		if (ae != null) {
			result = true;
			this.elements.remove(ae);
		}
		return result;
	}

	// Search for an action with a given ActionName (String) and returns
	// an ActionElement (pddxml action = owl-s service) as result. If
	// no action with ActionName name exists, null will be returned
	public ActionElement find(String ActionName) {
		ActionElement result = null;
		for (Iterator i = this.elements.iterator(); i.hasNext();) {
			ActionElement action = (ActionElement) i.next();
			if (action.name.equals(ActionName)) {
				result = action;
				break;
			}
		}
		return result;
	}

}
