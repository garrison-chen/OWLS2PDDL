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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.dfki.pddxml.relaxer.Action;
import de.dfki.pddxml.relaxer.IExpressionChoice;
import de.dfki.pddxml.relaxer.PDDXMLObject;
import de.dfki.pddxml.relaxer.Parameter;
import de.dfki.pddxml.relaxer.Predicate;

public class ConverterContext
{
    public static final String OBJECT_TYPE = "object";

    private static final String AGENT_HAS_KNOWLEDGE_ABOUT = "agentHasKnowledgeAbout";
    private static final String IDENTITY = "identity";
    
    private Map<URI,IExpressionChoice> fPDDXMLClassAxioms;
    private Map<URI,IExpressionChoice> fPDDXMLDeterministicClassAxioms;
    
    private Map<String,Predicate> fPDDXMLPredicateDefinitions;
    private Set<Action> fPDDXMLActions;

    private Map<URI,IExpressionChoice> fPDDXMLIndividualExpressions;
    
    private Map<String,PDDXMLObject> fPDDXMLObjects;
    
    private int fOutputInstances;
    
    public ConverterContext()
    {
        fPDDXMLClassAxioms = new HashMap<URI,IExpressionChoice>();
        fPDDXMLDeterministicClassAxioms = new HashMap<URI,IExpressionChoice>();
        
        fPDDXMLPredicateDefinitions = new HashMap<String,Predicate>();
        fPDDXMLActions = new HashSet<Action>();
        
        fPDDXMLIndividualExpressions = new HashMap<URI, IExpressionChoice>();
        fPDDXMLObjects = new HashMap<String,PDDXMLObject>();
        
        Predicate predicate = new Predicate();
        predicate.setName(AGENT_HAS_KNOWLEDGE_ABOUT);
        Parameter parameter = new Parameter();
        parameter.setContent("?object-parameter");
        parameter.setType(OBJECT_TYPE);
        predicate.addParameter(parameter);
        fPDDXMLPredicateDefinitions.put(AGENT_HAS_KNOWLEDGE_ABOUT, predicate);
        
        fOutputInstances = 10;
    }


    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsClassAxiom(URI key)
    {
        return fPDDXMLClassAxioms.containsKey(key);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public IExpressionChoice putClassAxiom(URI key, IExpressionChoice value)
    {
        return fPDDXMLClassAxioms.put(key, value);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public IExpressionChoice getClassAxiom(URI key)
    {
        return fPDDXMLClassAxioms.get(key);
    }
    
    /**
     * @return
     * @see java.util.Map#values()
     */
    public Collection<IExpressionChoice> getClassAxioms()
    {
        return fPDDXMLClassAxioms.values();
    }

    
    /**
     * @return
     * @see java.util.Map#keySet()
     */
    public Set<URI> getClassAxiomURIs()
    {
        return fPDDXMLClassAxioms.keySet();
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsDeterministicClassAxiom(URI key)
    {
        return fPDDXMLDeterministicClassAxioms.containsKey(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public IExpressionChoice getDeterministicClassAxiom(URI key)
    {
        return fPDDXMLDeterministicClassAxioms.get(key);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public IExpressionChoice putDeterministicClassAxiom(URI key, IExpressionChoice value)
    {
        return fPDDXMLDeterministicClassAxioms.put(key, value);
    }

    public Predicate makeConcept(String typeName, String individual)
    {
        addConceptSignature(typeName);
        Predicate isA = new Predicate();
        isA.setName(typeName);
        Parameter parameter = new Parameter();
        parameter.setContent(individual);
        isA.addParameter(parameter);
        return isA;
    }

    public Predicate makeRelation(String relationName, String domainIndividual, String rangeIndividual)
    {
        addRelationSignature(relationName);
        Predicate relation = new Predicate();
        relation.setName(relationName);
        Parameter parameter = new Parameter();
        parameter.setContent(domainIndividual);
        relation.addParameter(parameter);
        parameter = new Parameter();
        parameter.setContent(rangeIndividual);
        relation.addParameter(parameter);
        return relation;
        
    }
    
    public Predicate makeIdentityPredicate(String individual)
    {
        return makeRelation(IDENTITY, individual, individual);
    }
    
    public Predicate makeAgentHasKnowledgeAboutPredicate(String individual)
    {
        return makeConcept(AGENT_HAS_KNOWLEDGE_ABOUT, individual);
    }
    
    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsPredicateSignature(String key)
    {
        return fPDDXMLPredicateDefinitions.containsKey(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public Predicate getPredicateSignature(String key)
    {
        return fPDDXMLPredicateDefinitions.get(key);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Predicate putPredicateSignature(String key, Predicate value)
    {
        return fPDDXMLPredicateDefinitions.put(key, value);
    }

    /**
     * @return
     * @see java.util.Map#values()
     */
    public Collection<Predicate> getPredicates()
    {
        return fPDDXMLPredicateDefinitions.values();
    }
    
    public boolean addRelationSignature(String name, int parameters)
    {
        if(containsPredicateSignature(name)) return false;

        Predicate predicate = new Predicate();        
        predicate.setName(name);
        
        for(int i = 0; i < parameters; ++i)
        {
            Parameter parameter = new Parameter();
            parameter.setContent("?individual-" + i);
            parameter.setType(ConverterContext.OBJECT_TYPE);
            predicate.addParameter(parameter);
        }
        putPredicateSignature(name, predicate);
        return true;
    }

    public boolean addConceptSignature(String name)
    {
        if(containsPredicateSignature(name)) return false;
        
        Predicate predicate = new Predicate();
        predicate.setName(name);
        Parameter parameter = new Parameter();
        parameter.setContent("?individual");
        parameter.setType(ConverterContext.OBJECT_TYPE);
        predicate.addParameter(parameter);
        putPredicateSignature(name, predicate);
        return true;
    }
    
    public boolean addRelationSignature(String name)
    {
        return addRelationSignature(name, 2);
    }

    /**
     * @param o
     * @return
     * @see java.util.Set#add(java.lang.Object)
     */
    public boolean addAction(Action o)
    {
        return fPDDXMLActions.add(o);
    }

    /**
     * @param o
     * @return
     * @see java.util.Set#contains(java.lang.Object)
     */
    public boolean containsAction(Action o)
    {
        return fPDDXMLActions.contains(o);
    }

    /**
     * @return
     * @see java.util.Set#iterator()
     */
    public Iterator<Action> actionIterator()
    {
        return fPDDXMLActions.iterator();
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsIndividual(URI key)
    {
        return fPDDXMLIndividualExpressions.containsKey(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#get(java.lang.Object)
     */
    public IExpressionChoice getIndividual(URI key)
    {
        return fPDDXMLIndividualExpressions.get(key);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public IExpressionChoice putIndividual(URI key, IExpressionChoice value)
    {
        return fPDDXMLIndividualExpressions.put(key, value);
    }

    /**
     * @return
     * @see java.util.Map#values()
     */
    public Collection<IExpressionChoice> getIndividuals()
    {
        return fPDDXMLIndividualExpressions.values();
    }


    /**
     * @param o
     */
    public void addObject(String o)
    {
        PDDXMLObject object = new PDDXMLObject();
        object.setContent(o);
        object.setType(ConverterContext.OBJECT_TYPE);
        fPDDXMLObjects.put(o, object);
    }


    /**
     * @param o
     * @return
     * @see java.util.Set#contains(java.lang.Object)
     */
    public boolean containsObject(Object o)
    {
        return fPDDXMLObjects.containsKey(o);
    }


    /**
     * 
     * @return the PDDXML objects.
     */
    public Collection<PDDXMLObject> getPDDXMLObjects()
    {
        return fPDDXMLObjects.values();
    }


    
    /**
     * @return the outputInstances
     */
    public int getOutputInstances()
    {
        return fOutputInstances;
    }


    
    /**
     * @param outputInstances the outputInstances to set
     */
    public void setOutputInstances(int outputInstances)
    {
        fOutputInstances = outputInstances;
    }
}
