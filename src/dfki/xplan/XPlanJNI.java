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


public class XPlanJNI
{
    private static int addr;

    private native int getReferenceJNI();

    private native int initializePlanningJNI(String domain, String problem, String param, int addr);

    private native void resetJNI(int addr);

    private native boolean doStaticPlanningJNI(int addr);

    private native boolean doDynamicPlanningJNI(int addr);
    
    private native int getGoalDistanceJNI(int addr);
    
    private native String getCurrentPlanAsXMLJNI(int addr);

    private native void disableActionJNI(int opId, int xPlanAddr);

    private native int rePlanPlanStepJNI(int step, int xPlanAddr);
    
    private native void enableDisabledActionJNI(int opId, int xPlanAddr);
    
    private native String getCurrentActionInstanceJNI(int addr);

    private native String getCurrentActionAsXMLJNI(int addr);

    private native String getPlanInstanceJNI(int addr);

    private native String getPlanAsXMLJNI(int addr);
   
    private native int triggerEventJNI(int event, String data, int addr);
    
    private native boolean handleEventsJNI(int addr);
    
    private native boolean finishedPlanningJNI(int addr);
    
    private native boolean existsPlanJNI(int addr);
   
    
    static {
        System.loadLibrary("xplan-jni");
    }
    
    public int initialize(String domainFile, String problemFile, String param)
    {
    	addr = getReferenceJNI();
    	return initializePlanningJNI(domainFile,problemFile,param,addr);
    };

    public void Reset(int addr)
    {
    	resetJNI(addr);
    }
 
    public int getAddress()
    {
    	return addr;
    };

    public boolean doStaticPlanning(int addr)
    {
    	return doStaticPlanningJNI(addr);
    };

    public boolean doDynamicPlanning(int addr)
    {
    	return doDynamicPlanningJNI(addr);
    };
    
    public int getGoalDistance(int addr)
    {
    	return getGoalDistanceJNI(addr);
    }
    
    public String getCurrentPlanAsXML(int addr)
    {
    	return getCurrentPlanAsXMLJNI(addr);
    };

    public void disableAction(int opId, int xPlanAddr)
    {
    	disableActionJNI(opId, xPlanAddr);
    };

    public int rePlanPlanStep(int step, int addr)
    {
    	return rePlanPlanStepJNI(step,addr);
    }
    
    public void enableDisabledAction(int opId, int xPlanAddr)
    {
    	enableDisabledActionJNI(opId, xPlanAddr);
    };
      
    public String getCurrentActionInstance(int addr)
    {
    	return getCurrentActionInstanceJNI(addr);
    };
    
    public String getCurrentActionAsXML(int addr)
    {
    	return getCurrentActionAsXMLJNI(addr);
    };

    public String getPlanInstance(int addr)
    {
    	return getPlanInstanceJNI(addr);
    };

    public String getPlanAsXML(int addr)
    {
    	return getPlanAsXMLJNI(addr);
    };

    public int triggerEvent(int event, String data, int addr)
    {
    	return triggerEventJNI(event,data,addr);
    };

    public boolean handleEvents(int addr)
    {
    	return handleEventsJNI(addr);
    };
    
    public boolean finishedPlanning(int addr)
    {
    	return finishedPlanningJNI(addr);
    }
    
    public boolean existsPlan(int addr)
    {
    	return existsPlanJNI(addr);
    };
    
}