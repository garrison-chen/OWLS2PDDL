/*
This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.

Contact and Copyright

The mobile service selector iSeM (1.1), S2P2P, DSDR and S3M Peer was developed
at the German Research Center for Articifial Intelligence DFKI GmbH (http://ww.dfki.de)
in Saarbrücken, Germany.

Copyright: DFKI, 2014, All Rights Reserved.

For bug reports, other technical problems and feature requests please contact Patrick Kapahnke: patrick.kapahnke@dfki.de

For general scientific inquiries please contact PD Dr. Matthias Klusch: klusch@dfki.de
*/

package de.dfki.owls2pddxml_2_0;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.dfki.pddxml.relaxer.IExpressionChoice;

public class StateHelper
{
    private Map<URI,IExpressionChoice> fPDDXMLIndividualExpressions;
    private Map<String,Map<String,Set<String>>> fRelations;
    
    public StateHelper()
    {
        fRelations = new HashMap<String, Map<String,Set<String>>>();
        fPDDXMLIndividualExpressions = new HashMap<URI, IExpressionChoice>();

    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean contains(String leftValue, String relation, String rightValue)
    {
        if(!fRelations.containsKey(leftValue)) return false;
        if(!fRelations.get(leftValue).containsKey(relation)) return false;
        return fRelations.get(leftValue).get(relation).contains(rightValue);
    }

    /**
     * @return
     * @see java.util.Map#keySet()
     */
    public Set<String> leftValueSet()
    {
        return fRelations.keySet();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public void put(String leftValue, String relation, String rightValue)
    {
        if(!fRelations.containsKey(leftValue))
        {
            fRelations.put(leftValue, new HashMap<String,Set<String>>());
        }
        if(!fRelations.get(leftValue).containsKey(relation))
        {
            fRelations.get(leftValue).put(relation, new HashSet<String>());
        }
        fRelations.get(leftValue).get(relation).add(rightValue);
    }

    /**
     * @param individual
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsIndividual(URI individual)
    {
        return fPDDXMLIndividualExpressions.containsKey(individual);
    }

    /**
     * @param individual
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public IExpressionChoice getIndividual(URI individual)
    {
        return fPDDXMLIndividualExpressions.get(individual);
    }

    /**
     * @param individual
     * @param expression
     * @return true iff the individual was not yet known and added to this state.
     */
    public boolean putIndividual(URI individual, IExpressionChoice expression)
    {
        if(containsIndividual(individual))
        {
            return false;
        }        
        fPDDXMLIndividualExpressions.put(individual, expression);
        return true;
    }

    /**
     * @return
     * @see java.util.Map#values()
     */
    public Collection<IExpressionChoice> getIndividuals()
    {
        return fPDDXMLIndividualExpressions.values();
    }
}
