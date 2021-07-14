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

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataAllRestriction;
import org.semanticweb.owl.model.OWLDataValueRestriction;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLDescriptionVisitor;
import org.semanticweb.owl.model.OWLObjectAllRestriction;
import org.semanticweb.owl.model.OWLObjectComplementOf;
import org.semanticweb.owl.model.OWLObjectIntersectionOf;
import org.semanticweb.owl.model.OWLObjectValueRestriction;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Disjunction;
import de.dfki.pddxml.relaxer.ForAll;
import de.dfki.pddxml.relaxer.Negation;
import de.dfki.pddxml.relaxer.Predicate;
import de.dfki.pddxml.relaxer.Variable;
import de.dfki.pddxml.relaxer.Variables;

public abstract class OWLDescriptionConverter extends OWLObjectConverter implements OWLDescriptionVisitor
{

    public static final String FREE_VARIABLE = "?individual";
    /** The converter for sub-descriptions. */
    private OWLDescriptionConverter fSubOWLDescriptionConverter;
    private Set<OWLDescription> fVisited;
    private static int sQuantifierVariableCount = 0;
    /** The individual URI or parameter URI the converted description is for. */
    private String fIndividualName;

    public OWLDescriptionConverter(DomainHelper context, String individualName, OWLOntologyManager owlManager)
    {
        super(context, owlManager);
        fIndividualName = individualName;
        fSubOWLDescriptionConverter = null;

        fVisited = new HashSet<OWLDescription>();
    }
    
    protected OWLDescriptionConverter(OWLDescriptionConverter parent)
    {
        this(parent.getConverterContext(), parent.getIndividualName(), parent.owlManager);
        this.fVisited = parent.fVisited;
    }

    /**
     * @return the subOWLDescriptionConverter
     */
    protected OWLDescriptionConverter getSubOWLDescriptionConverter()
    {
        return fSubOWLDescriptionConverter;
    }

    /**
     * @param subOWLDescriptionConverter the subOWLDescriptionConverter to set
     */
    protected void setSubOWLDescriptionConverter(OWLDescriptionConverter subOWLDescriptionConverter)
    {
        fSubOWLDescriptionConverter = subOWLDescriptionConverter;
    }

    public void visit(OWLObjectIntersectionOf owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
        Conjunction conjunction = new Conjunction();
        
        for(OWLDescription operandDescription : (Set<OWLDescription>)owlDescription.getOperands())
        {
            operandDescription.accept(getSubOWLDescriptionConverter());
            conjunction.addExpression(getSubOWLDescriptionConverter().getPDDXMLExpression());
        }
        
        setPDDXMLExpression(conjunction);
    }

    public void visit(OWLDataAllRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");       
    }

    public void visit(OWLDataValueRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
    }

    public void visit(OWLObjectComplementOf owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
        Negation negation = new Negation();
        
        owlDescription.getOperand().accept(getSubOWLDescriptionConverter());
        negation.setExpression(getSubOWLDescriptionConverter().getPDDXMLExpression());
        
        setPDDXMLExpression(negation);
    }

    public void visit(OWLObjectAllRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
                
        OWLClass valuesFromClass = (OWLClass)owlDescription.getFiller();
        makeAllValuesFromRestriction(getNextQuantifierVariable(), owlDescription.getProperty().asOWLObjectProperty().getURI().toString(), valuesFromClass.getURI().toString());
    }

    public void visit(OWLObjectValueRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
        String relation = owlDescription.getProperty().asOWLObjectProperty().getURI().toString();
        String rangeIndividual = owlDescription.getValue().getURI().toString();
        setPDDXMLExpression(getConverterContext().makeRelation(relation, fIndividualName, rangeIndividual));
    }

    /**
     * @return the visited
     */
    protected boolean visited(OWLDescription description)
    {
        return fVisited.contains(description);
    }

    /**
     * @param o
     * @return
     * @see java.util.Set#add(java.lang.Object)
     */
    protected boolean addVisited(OWLDescription o)
    {
        return fVisited.add(o);
    }

    protected String getNextQuantifierVariable()
    {
        return "?Variable_" + (++sQuantifierVariableCount);
    }

    /**
     * @return the individualName
     */
    protected String getIndividualName()
    {
        return fIndividualName;
    }

    protected void makeAllValuesFromRestriction(String variableName, String predicateName, String typeName)
    {
        ForAll forAll = new ForAll();
        
        /* Make the variable declaration */
        
        // ForAll ?x: ...
        Variables variables = new Variables();
        Variable variable = new Variable();
        variable.setContent(variableName);
        variables.addVariable(variable);
        forAll.setVariables(variables);
    
        
        /* 
         * Make the expression body which specifies that if the property holds for an object,
         * then the object must be of the specified type. The implication x->y is done by -x OR y .
         */ 
    
        Disjunction disjunction = new Disjunction();
    
        // ForAll x? :  (NOT predicate(containingClass, ?x)) OR ...
        Negation negation = new Negation();
        String relationName = predicateName;
        String domainIndividual = getIndividualName();
        String rangeIndividual = variableName;
        Predicate predicate = getConverterContext().makeRelation(relationName, domainIndividual, rangeIndividual);
        negation.setExpression(predicate);
        disjunction.addExpression(negation);
    
        
        // ForAll x? : (NOT predicate(containingClass, ?x)) OR allValuesFromType(?x) 
        disjunction.addExpression(getConverterContext().makeConcept(typeName, variableName));
        
        forAll.setExpression(disjunction);
        
        setPDDXMLExpression(forAll);
        setPDDXMLExpression(new Conjunction());
    }

}