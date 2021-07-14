/*
 * Created on 12.01.2005
 *
 */
package converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import PDDXML_Datatypes.PDDXML_Actions;
import PDDXML_Datatypes.PDDXML_Objects;
import PDDXML_Datatypes.PDDXML_Predicates;
import PDDXML_Datatypes.PDDXML_Types;

/**
 * @author Andreas Gerber
 *
 * this gets the inputs from FileReader and converts the datastructures
 * into PDDXML style, which is written by the FileWriter object into two
 * files - one for the domain and one for the problem.
 */
public class FileConverter extends JDialog{

	public FileConverter() {
	}

	public PDDXML_Types pddxml_types = new PDDXML_Types();

	public PDDXML_Predicates pddxml_predicates = new PDDXML_Predicates();

	public PDDXML_Actions pddxml_actions = new PDDXML_Actions();

	public PDDXML_Objects pddxml_objects = new PDDXML_Objects();

	public PDDXML_Predicates pddxml_initstate = new PDDXML_Predicates();

	public PDDXML_Predicates pddxml_goalstate = new PDDXML_Predicates();

	public boolean convertOWLS2PDDXML(String Path, String FileName) {

		// first delete the old plan-file
		try {
			String osName = System.getProperty("os.name");
			String[] cmd = new String[3];
			System.out.println("OS: "+osName);
			if (osName.equals("Windows NT")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";
			} else if (osName.equals("Windows 95")) {
				cmd[0] = "command.com";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "command.com";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";
			}

			else if (osName.equals("Windows XP")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";

			} else if (osName.equals("Windows 2000")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";

			}

			Runtime rt = Runtime.getRuntime();
			System.out.println("Execing " + cmd[0] + " " + cmd[1]  + " " + cmd[2]);
			Process proc = rt.exec(cmd);
			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(proc
					.getErrorStream(), "ERROR");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(proc
					.getInputStream(), "OUTPUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = proc.waitFor();
			//System.out.println("ExitValue: " + exitVal);        
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("FileConverter: Error while removing <name>_Plan-file: \n"
							+ t + "\n");
			return false;
		}

		// parse the 'Ontology'-file
		DomainFileReader dfr = new DomainFileReader(Path + FileName);

		//parse the 'GoalOnotontology'-file
		DomainFileReader goalfr = new DomainFileReader(Path
				+ FileName.split("InitialOntology")[0] + "GoalOntology.owl");

		//collect the parse data-structures of the init-state
		this.pddxml_initstate = dfr.pddxml_initState;
		this.pddxml_objects = dfr.pddxml_objects;
		this.pddxml_predicates = dfr.pddxml_predicates;
		this.pddxml_types = dfr.pddxml_types;

		//collect the parse data-structures of the goal-state
		this.pddxml_goalstate = goalfr.pddxml_initState;
		this.pddxml_types.elements.addAll(goalfr.pddxml_types.elements);
		this.pddxml_predicates.elements
				.addAll(goalfr.pddxml_predicates.elements);
		this.pddxml_objects.elements.addAll(goalfr.pddxml_objects.elements);

		// parse the service-file
		File file = new File(Path);
		for (int i = 0; i < file.list().length; i++) {
			if (file.list()[i].contains("Services.owl")) {
				ServiceReader sr = new ServiceReader(Path + file.list()[i],this);
			}
		}

		// parse the effects and precondition-file
		PrecondEffectReader servicfr = new PrecondEffectReader(Path
				+ FileName.split("InitialOntology")[0]
				+ "PreconditionsAndEffects.xml", this);

		this.removeDoubleElementsInPDDXML_Lists();
		dfr.createKnowledge(this);

		if(!this.writePDDXMLFile(Path, FileName.substring(0, FileName.indexOf(".")))){
			System.out.println("ERROR while writing: "+Path+FileName.substring(0, FileName.indexOf(".")));
			return false;
		}else
			return true;
		//return this.writePDDXMLFile(Path, FileName.substring(0, FileName.indexOf(".")));
	}

	public boolean invokePlaner(String Path, String FileName) {

		// first delete the old plan-file
		try {
			String osName = System.getProperty("os.name");
			String[] cmd = new String[3];

			if (osName.equals("Windows NT")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";
			} else if (osName.equals("Windows 95")) {
				cmd[0] = "command.com";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "command.com";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";
			}

			else if (osName.equals("Windows XP")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				Runtime rt = Runtime.getRuntime();
				//System.out.println("Execing " + cmd[0] + " " + cmd[1] 
				//                 + " " + cmd[2]);
				Process proc = rt.exec(cmd);

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";

				// System.err.println("cmd : " + cmd);

			} else if (osName.equals("Windows 2000")) {
				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan.xml";

				cmd[0] = "cmd.exe";
				cmd[1] = "/C";
				cmd[2] = "del " + Path + FileName.split(".owl")[0]
						+ "_Plan_m.xml";

			}

			Runtime rt = Runtime.getRuntime();
			System.out.println("Execing " + cmd[0] + " " + cmd[1]  + " " + cmd[2]);
			Process proc = rt.exec(cmd);
			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(proc
					.getErrorStream(), "ERROR");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(proc
					.getInputStream(), "OUTPUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = proc.waitFor();
			//System.out.println("ExitValue: " + exitVal);        
		} catch (Throwable t) {
			t.printStackTrace();
			System.out
					.println("FileConverter: Error while removing <name>_Plan-file: \n"
							+ t + "\n");

		}

		try {
			System.out
					.println("FileConverter: Inputfiles for the planning model: \n"
							+ Path
							+ FileName.split(".owl")[0]
							+ "_Domain.xml\n"
							+ Path
							+ FileName.split(".owl")[0]
							+ "_PB.xml\n\n"
							+ "Planer Output-File: \n"
							+ Path
							+ FileName.split(".owl")[0] + "_Plan.xml\n");

			//***************************************************************************************
			//**    for testing the planer-invocation with orginal pddxml-domain and problem files    **
			//**    therefore please decomment the following section                               **
			//***************************************************************************************
			try {
				Runtime rt = Runtime.getRuntime();

				Process proc = rt.exec("./OWL-S_XPlan/XPlan.exe " + "\""+Path
						+ FileName.split(".owl")[0] + "_Domain.xml\" " +"\"" +Path
						+ FileName.split(".owl")[0] + "_PB.xml\" " + "\""+Path
						+ FileName.split(".owl")[0] + "_Plan.xml\"");

				// System.err.println("proc.exitValue() : " + proc.getErrorStream().toString());

//				proc = rt.exec("../OWL-S_XPlan/XPlan.exe " + Path
//						+ FileName.split(".owl")[0] + "_Domain.xml " + Path
//						+ FileName.split(".owl")[0] + "_PB.xml " + Path
//						+ FileName.split(".owl")[0] + "_Plan_m.xml");

				StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");

				// any output?
				StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
				// kick them off
				errorGobbler.start();
				outputGobbler.start();

				// any error???
				int exitVal = proc.waitFor();
				
				//if plan element is NULL

				try {
					System.out.println("SEARCHING: "+Path+FileName.split(".owl")[0] + "_Plan.xml");

					File f = new File(Path+FileName.split(".owl")[0] + "_Plan.xml");//"./OWLS-Dateien/Health-Scallops_InitialOntology_Plan.xml");
					if (!f.exists()) {

						JOptionPane.showMessageDialog(
										this,
										"The \"Plan\" file is not found\\generated in the intended directory\n\n"
												+ "Reason(s):\n"
												+ "\rCorrupted \"Initital Ontology\", \"Domain Description\", \"Problem Description\" or all \nfiles\n"
												+ "\n"
												+ "\rEither \"Initital Ontology\", \"Domain Description\", \"Problem Description\" or all files\n"
												+ "are not selected from the intended source directory and hence the \"Plan\" file is\n"
												+ "not found\\generated\n\n"
												+ "Suggestion: \n"
												+ "\rIntended source folder: \"(xxx) Installation Path (xxx)\\OWLS-Dateien\\xxx (file) xxx\"",
										"Plan File missing", JOptionPane.ERROR_MESSAGE);
						return false;

					} else {

						String abs = f.getAbsolutePath();
						List stepList = parseXMLPlan(abs);
						
						if(stepList.size()==0){
						//System.out.println("Size of Step list is 0");
						return false;
						}
					
						
					}//else ends here
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
				///
				
				
				

			} catch (Throwable t) {
				t.printStackTrace();
				return false;
			}

		} catch (Exception ee) {
			System.out
					.println("FileConverter: Planner-Error while executing the planner-module! "
							+ ee);
			ee.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List parseXMLPlan(String fileName) {

		List stepList = new ArrayList();
		SAXBuilder builder = new SAXBuilder();

		// command line should offer URIs or file names
		try {

			Document resultDoc = builder.build(new File(fileName));
			Element result = resultDoc.getRootElement();

			Element plan = (Element) result.getChild("plan");
			stepList = plan.getChildren("step");
		}
		// indicates a well-formedness error
		catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stepList;

	}//parseXMLPlan ends here
	
	//added a subclass, handling intermediate process execution

	class StreamGobbler extends Thread {
		InputStream is;

		String type;

		StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
				}
				//System.out.println(type + ">" + line);    
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void removeDoubleElementsInPDDXML_Lists() {
		this.pddxml_types.removeDoubleElements();
		this.pddxml_predicates.removeDoubleElements();
		this.pddxml_actions.removeDoubleElements();
		this.pddxml_goalstate.removeDoubleElements();
		this.pddxml_initstate.removeDoubleElements();
		this.pddxml_objects.removeDoubleElements();
	}

	// write the two pddxml-files
	public boolean writePDDXMLFile(String Path, String Name) {
		DomainFileWriter dfw = new DomainFileWriter(Path, Name, pddxml_types,
				pddxml_predicates, pddxml_actions);
		ProblemFileWriter pfw = new ProblemFileWriter(Path, Name,
				pddxml_objects, pddxml_initstate, pddxml_goalstate);
		return true;
	}

	/************** TEST   TEST  TEST  ***********************************
	 * This method creates a hard coded pddxml data-structure to TEST the 
	 * filewriter class.
	 *********************************************************************/
	private void buildTestDatas() {
		//**********************************************************
		//       Problem definition
		//**********************************************************
		// first creating some objects
		PDDXML_Objects.ObjectElement o1 = pddxml_objects.createNewObject();
		o1.type = "robot";
		o1.object = "r1";
		PDDXML_Objects.ObjectElement o2 = pddxml_objects.createNewObject();
		o2.type = "location";
		o2.object = "l1";
		PDDXML_Objects.ObjectElement o3 = pddxml_objects.createNewObject();
		o3.type = "location";
		o3.object = "l2";
		pddxml_objects.elements.add(o1);
		pddxml_objects.elements.add(o2);
		pddxml_objects.elements.add(o3);

		// second creating an init state
		PDDXML_Predicates.PredicateElement i1 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass i1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass i1p2 = pddxml_predicates
				.createNewParameter();
		i1.name = "adjacent";
		i1p1.parameter = "l1";
		i1p2.parameter = "l2";
		i1.paramList.add(i1p1);
		i1.paramList.add(i1p2);
		pddxml_initstate.elements.add(i1);

		PDDXML_Predicates.PredicateElement i2 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass i2p1 = pddxml_predicates
				.createNewParameter();
		i2.name = "empty";
		i2p1.parameter = "k2";
		i2.paramList.add(i2p1);
		pddxml_initstate.elements.add(i2);

		// thrid creating a goal state
		PDDXML_Predicates.PredicateElement g1 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass g1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass g1p2 = pddxml_predicates
				.createNewParameter();
		g1.name = "in";
		g1p1.parameter = "cf";
		g1p2.parameter = "q2";
		g1.paramList.add(g1p1);
		g1.paramList.add(g1p2);
		pddxml_goalstate.elements.add(g1);

		PDDXML_Predicates.PredicateElement g2 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass g2p1 = pddxml_predicates
				.createNewParameter();
		g2.name = "clear";
		g2p1.parameter = "k2";
		g2.paramList.add(g2p1);
		pddxml_goalstate.elements.add(g2);

		//**********************************************************
		//       Domain definition
		//**********************************************************

		// first creating some PDDXML Types
		PDDXML_Types.TypeElement e1 = pddxml_types.createNewType();
		e1.type = "location";
		PDDXML_Types.TypeElement e2 = pddxml_types.createNewType();
		e2.type = "pile";
		PDDXML_Types.TypeElement e3 = pddxml_types.createNewType();
		e3.type = "robot";
		pddxml_types.elements.add(e1);
		pddxml_types.elements.add(e2);
		pddxml_types.elements.add(e3);

		// second creating some PDDXML Predicates
		PDDXML_Predicates.PredicateElement p1 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass p1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass p1p2 = pddxml_predicates
				.createNewParameter();
		p1.name = "adjacent";
		p1p1.type = "location";
		p1p1.parameter = "l1";
		p1p2.type = "location";
		p1p2.parameter = "l2";
		p1.paramList.add(p1p1);
		p1.paramList.add(p1p2);
		pddxml_predicates.elements.add(p1);

		PDDXML_Predicates.PredicateElement p2 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass p2p1 = pddxml_predicates
				.createNewParameter();
		p2.name = "occupied";
		p2p1.type = "location";
		p2p1.parameter = "l";
		p2.paramList.add(p2p1);
		pddxml_predicates.elements.add(p2);

		// third creating some PDDXML Actions

		// definition of an action class
		PDDXML_Actions.ActionElement a1 = pddxml_actions.createNewAction();
		a1.name = "move";
		a1.qos = "0.9";
		// definition of the input paramters
		PDDXML_Predicates.ParamClass a1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass a1p2 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass a1p3 = pddxml_predicates
				.createNewParameter();
		a1p1.type = "robot";
		a1p1.parameter = "r";
		a1p2.type = "location";
		a1p2.parameter = "from";
		a1p3.type = "location";
		a1p3.parameter = "to";

		a1.inputParamList.add(a1p1);
		a1.inputParamList.add(a1p2);
		a1.inputParamList.add(a1p3);

		// definition of the preconditions
		PDDXML_Predicates.PredicateElement a1_p1 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass a1_p1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass a1_p1p2 = pddxml_predicates
				.createNewParameter();
		a1_p1.name = "adjacent";
		a1_p1p1.type = "location";
		a1_p1p1.parameter = "from";
		a1_p1p2.type = "location";
		a1_p1p2.parameter = "to";
		a1_p1.paramList.add(a1_p1p1);
		a1_p1.paramList.add(a1_p1p2);

		PDDXML_Predicates.PredicateElement a1_p2 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass a1_p2p1 = pddxml_predicates
				.createNewParameter();
		a1_p2.name = "occupied";
		a1_p2.state = false;
		a1_p2p1.type = "location";
		a1_p2p1.parameter = "to";
		a1_p2.paramList.add(a1_p2p1);

		a1.preconditionList.add(a1_p1);
		a1.preconditionList.add(a1_p2);

		// definition of the effects
		PDDXML_Predicates.PredicateElement a1_e1 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass a1_e1p1 = pddxml_predicates
				.createNewParameter();
		PDDXML_Predicates.ParamClass a1_e1p2 = pddxml_predicates
				.createNewParameter();
		a1_e1.name = "at";
		a1_e1p1.type = "location";
		a1_e1p1.parameter = "r";
		a1_e1p2.type = "location";
		a1_e1p2.parameter = "to";
		a1_e1.paramList.add(a1_e1p1);
		a1_e1.paramList.add(a1_e1p2);

		PDDXML_Predicates.PredicateElement a1_e2 = pddxml_predicates
				.createNewPredicate();
		PDDXML_Predicates.ParamClass a1_e2p1 = pddxml_predicates
				.createNewParameter();
		a1_e2.name = "occupied";
		a1_e2.state = false;
		a1_e2p1.type = "location";
		a1_e2p1.parameter = "from";
		a1_e2.paramList.add(a1_e2p1);

		a1.effectsList.add(a1_e1);
		a1.effectsList.add(a1_e2);
		pddxml_actions.elements.add(a1);

	}

	public void checkPredicatesIfObejctExists() {
		ArrayList remove = new ArrayList();
		for (Iterator i = this.pddxml_predicates.elements.iterator(); i
				.hasNext();) {
			PDDXML_Predicates.PredicateElement pred = (PDDXML_Predicates.PredicateElement) i
					.next();
			for (Iterator p = pred.paramList.iterator(); p.hasNext();) {
				PDDXML_Predicates.ParamClass param = (PDDXML_Predicates.ParamClass) p
						.next();
				if (!this.pddxml_objects.containsObjectOfType(param.type))
					remove.add(pred);
			}
		}
		for (Iterator k = remove.iterator(); k.hasNext();) {
			PDDXML_Predicates.PredicateElement pred = (PDDXML_Predicates.PredicateElement) k
					.next();
			this.pddxml_predicates.elements.remove(pred);
		}
	}

}
