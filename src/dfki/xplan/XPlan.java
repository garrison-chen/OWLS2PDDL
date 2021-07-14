/*
This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.

Contact and Copyright

The mobile service selector iSeM (1.1), S2P2P, DSDR and S3M Peer was developed
at the German Research Center for Articifial Intelligence DFKI GmbH (http://ww.dfki.de)
in Saarbr�cken, Germany.

Copyright: DFKI, 2014, All Rights Reserved.

For bug reports, other technical problems and feature requests please contact Patrick Kapahnke: patrick.kapahnke@dfki.de

For general scientific inquiries please contact PD Dr. Matthias Klusch: klusch@dfki.de
*/

package dfki.xplan;

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
    public String initialize(String domainFile, String problemFile, String param)
    {
    	System.out.println("XPlan: Initializing XPlanJNI... ");
    	xPlanJNI = new XPlanJNI();
    	int res = xPlanJNI.initialize(domainFile, problemFile, param);
    	xPlanAddr = xPlanJNI.getAddress();
    	String result = xPlanJNI.getPlanAsXML(xPlanAddr);
		return result;
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
		
		if(xplan.initialize("domain.xml", "problem.xml", "out.xml").equals("yo")){
		    System.out.println("problem not solvable");
		    return;
		}
    }
}