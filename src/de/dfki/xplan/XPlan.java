package de.dfki.xplan;

/**
 * This class provides the functionality of XPlan via the JNI 
 * 
 * @author krenner
 */

//import de.dfki.xplan.*;

import java.util.List;
import java.util.ArrayList;

public class XPlan
{
    private XPlanJNI xPlanJNI = null;
 
    private int xPlanAddr;
    
    private List list = null;

    private boolean finishedPlanning = false;

    public static int NEWOP = 1;

    public static int LOSSOP = 2;

    public static int NEWFACT = 3;

    public static int NEWOBJ = 4;

    public static int LOSSOBJ = 5;

    public static int NEWPRED = 6;

    public static int LOSSPRED = 7;

    public static int NEWGOAL = 8;


    public XPlan()
    {
    	System.out.println("XPlan called");
    }

    /**
     * Creates a new instance of XPlan
     * @param domainFile   the name of the domain file
     * @param problemFile  the name of the problem file
     * @param param        parameter not in use
     * @return 1 if initialization was successful, -1 if the given problem is not solvable (reachability test)
     */
    public int initialize(String domainFile, String problemFile, String param)
    {
    	System.out.println("XPlan: Initializing XPlanJNI... ");
    	xPlanJNI = new XPlanJNI();
    	int res = xPlanJNI.initialize(domainFile, problemFile, param);
    	xPlanAddr = xPlanJNI.getAddress();
		return res;
    };


    public void reset()
    {
    	System.out.println("reset... ");
    	xPlanJNI.Reset(xPlanAddr);
		System.out.println("done");
    }

    /**
    /* Creates a plan
    /* @return always true
    */
    public boolean doStaticPlanning()
    {
    	System.out.println("XPlan: Start planning");
		return xPlanJNI.doStaticPlanning(xPlanAddr);
    };

    /**
    /* Function returns after each plan step.
    /* @return true as long no plan has been found
    */
    public boolean doDynamicPlanning()
    {
    	System.out.println("XPlan: continue planning...");
    	finishedPlanning = xPlanJNI.doDynamicPlanning(xPlanAddr);
		return finishedPlanning;
    };

    
    public int getGoalDistance()
    {
    	return xPlanJNI.getGoalDistance(xPlanAddr);
    }
    
    /**
    /* Getting the plan.
    /* @return the in PDDXML
    */
    public String getCurrentPlanAsXML()
    {
    	return xPlanJNI.getCurrentPlanAsXML(xPlanAddr);
    };


    public void disableAction(int opId)
    {
    	xPlanJNI.disableAction(opId,xPlanAddr);
    	if(list == null)
    		list = new ArrayList();
    	list.add((Object)new Integer(opId));
    };
    
    /**
     * Function initiates a replanning for a certain plan step
     * @param step the plan step from which replanning should start (0 = initial state)
     * @return  0, success
     *         -1, if opId is out of range (opId < 0 || opId >= number of actions)
     *         -2, if planning has not started yet => no replanning possible
     *         -3, if Enforced-Hill-Climbing cannot find an action for the specified plan step
     */
    public int rePlanPlanStep(int step)
    {
    	return xPlanJNI.rePlanPlanStep(step,xPlanAddr);
    }
    
    public void enableDisabledActions()
    {
    	for(int i=0 ; i<list.size() ; i++)
    		xPlanJNI.enableDisabledAction(((Integer)list.get(i)).intValue(),xPlanAddr);
    	list.clear();
    };
    
    /**
    /* fires an event
    /* @return alway 0
    */
    public int triggerEvent(int event, String data)
    {
    	System.out.println("XPlan: EVENT");
    	System.out.println("XPlan: event = '"+event+"'");
    	System.out.println("XPlan: data  = '"+data+"'");
    	return xPlanJNI.triggerEvent(event,data,xPlanAddr);
    };

    /**
    /* Starting replanning for the triggered event
    /* @return true if replanning was successful, false otherwise (in this case a call of doDynamicPlanning() switches to breadth first search)
    */
    public boolean handleEvents()
    {
    	return xPlanJNI.handleEvents(xPlanAddr);
    };

    
    public String getCurrentActionAsXML()
    {
    	return xPlanJNI.getCurrentActionAsXML(xPlanAddr);
    };

    /**
    /*
    /* @return the plan in PDDXML format
    */
    public String getPlanAsXML()
    {
    	return xPlanJNI.getPlanAsXML(xPlanAddr);
    };


    /**
    /*
    /* @return the plan as a sequence of instantiated operators
    **/
    public String getPlanInstance()
    {
    	return xPlanJNI.getPlanInstance(xPlanAddr);
    };

    /**
    /* Not implemented yet.
    /* @return alway null
    **/
    public String getCurrentActionInstance()
    {
    	return xPlanJNI.getCurrentActionInstance(xPlanAddr);
    };

    
    public boolean finishedPlanning()
    {
    	return xPlanJNI.finishedPlanning(xPlanAddr);
    }
    
    /**
    /* 
    /* @return true if a plan exists, false if the goal has not been reached yet or no plan exists
    **/
    public boolean existsPlan()
    {
    	return xPlanJNI.existsPlan(xPlanAddr);
    };
    
    
    public static void main(String[] args){
	
	XPlan xplan = new XPlan();
	if(xplan.initialize("domain.xml", "problem.xml", null) == -1){
	    System.out.println("problem not solvable");
	    return;
	}
	
	// Example for static planning
	

	//xplan.doStaticPlanning();
	//if(xplan.existsPlan()){
	//	System.out.println("plan instance:");
	//	System.out.print( xplan.getPlanInstance());
	//	return;
	//}

	
	// Example for dynamic planning
	
	//String event =  "<init><and><pred	name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param></pred></and></init>";
	//<define_domain xsi:noNamespaceSchemaLocation=\"Domain.xsd\" name=\"DockWorkerRobot\"><actions><action name=\"drop\"><parameters><param type=\"robot\">?r</param><param type=\"container\">?c</param><param type=\"pile\">?p</param><param type=\"location\">?l</param><param type=\"container\">?else</param></parameters><precondition><and><pred name=\"at\"><param>?r</param><param>?l</param></pred><pred name=\"loaded\"><param>?r</param><param>?c</param></pred><pred name=\"attached\"><param>?p</param><param>?l</param></pred><pred name=\"clear\"><param>?c</param></pred><pred name=\"clear\"><param>?else</param></pred><pred name=\"in\"><param>?else</param><param>?p</param></pred></and></precondition><effect><and><pred name=\"in\"><param>?c</param><param>?p</param></pred><pred name=\"on\"><param>?c</param><param>?else</param></pred><pred name=\"unloaded\"><param>?r</param></pred><not><pred name=\"clear\"><param>?else</param></pred></not><not><pred name=\"loaded\"><param>?r</param><param>?c</param></pred></not></and></effect></action></actions></define_domain>";
	
	//"http://127.0.0.1/health-scallops/services/RegisterPersonWithMedicalTransport.owl#RegisterPersonWithMedicalTransportService"

	//String event = "<init><and><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalFlightParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred></and></init>";

	//String event = "<init><and><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalFlightParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#assuresMedicalTreatment\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param><param>http://www.owl-ontologies.com/Ontology1.owl#RequiredTreatment</param></pred></and></init>";
	String event = "<init><and><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalFlightParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param></pred></and></init>";
//	                <init><and><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport1Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalVehicleTransportParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#Transport2Parameters</param></pred><pred name=\"http://127.0.0.1/health-scallops/ontology/Health-Scallops_Ontology.owl#MedicalFlightParameters\"><param>http://www.owl-ontologies.com/Ontology1.owl#FlightParameters</param></pred></and></init>";
    int i = 0;
	//if(i==0){
	//	xplan.triggerEvent(XPlan.NEWFACT, event);//http://127.0.0.1/health-scallops/services/RegisterPersonWithTransport.owl#RegisterPersonWIthTransportService");
	//	xplan.handleEvents();
	//}
	while(xplan.doDynamicPlanning()){
		if(i==0){
			xplan.triggerEvent(XPlan.NEWFACT, event);//http://127.0.0.1/health-scallops/services/RegisterPersonWithTransport.owl#RegisterPersonWIthTransportService");
			xplan.handleEvents();
		}
		//xplan.getCurrentPlanAsXML();
		System.out.println("-----");
	    //System.out.print(xplan.GetCurrentPlanAsXML());
	    System.out.println("-----");
	    i++;
	}
	//xplan.triggerEvent(XPlan.NEWFACT, event);//http://127.0.0.1/health-scallops/services/RegisterPersonWithTransport.owl#RegisterPersonWIthTransportService");
	//xplan.handleEvents();
	//xplan.TriggerEvent(XPlan.LOSSOP, "http://127.0.0.1/health-scallops/services/RegisterPersonWithMedicalTransport.owl#RegisterPersonWIthMedicalTransportService");
	//xplan.HandleEvents();
	//xplan.TriggerEvent(XPlan.NEWOP,event);
	//xplan.HandleEvents();
	xplan.doDynamicPlanning();
	try{
		Thread.sleep(1000);
	}catch(Exception e){
		e.printStackTrace();
	}
	System.out.println("plan instance:");
	System.out.print( xplan.getPlanInstance());
    }
	
}
