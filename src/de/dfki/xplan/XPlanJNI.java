package de.dfki.xplan;



public class XPlanJNI
{
    private static int addr;

    private static native int getReferenceJNI();

    private static native int initializePlanningJNI(String domain, String problem, String param, int addr);

    private static native void resetJNI(int addr);

    private static native boolean doStaticPlanningJNI(int addr);

    private static native boolean doDynamicPlanningJNI(int addr);
    
    private static native int getGoalDistanceJNI(int addr);
    
    private static native String getCurrentPlanAsXMLJNI(int addr);

    private static native void disableActionJNI(int opId, int xPlanAddr);

    private static native int rePlanPlanStepJNI(int step, int xPlanAddr);
    
    private static native void enableDisabledActionJNI(int opId, int xPlanAddr);
    
    private static native String getCurrentActionInstanceJNI(int addr);

    private static native String getCurrentActionAsXMLJNI(int addr);

    private static native String getPlanInstanceJNI(int addr);

    private static native String getPlanAsXMLJNI(int addr);
   
    private static native int triggerEventJNI(int event, String data, int addr);
    
    private static native boolean handleEventsJNI(int addr);
    
    private static native boolean finishedPlanningJNI(int addr);
    
    private static native boolean existsPlanJNI(int addr);
   
    static {
    	
//    	System.loadLibrary("XPlanLib");
    	System.loadLibrary("xplanlib");
    	
    };

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
    
};
