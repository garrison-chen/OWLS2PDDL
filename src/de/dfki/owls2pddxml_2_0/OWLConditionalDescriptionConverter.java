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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataExactCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataMaxCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataMinCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataSomeRestriction;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectExactCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectMaxCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectMinCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectOneOf;
import org.semanticweb.owl.model.OWLObjectSelfRestriction;
import org.semanticweb.owl.model.OWLObjectSomeRestriction;
import org.semanticweb.owl.model.OWLObjectUnionOf;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Cardinality;
import de.dfki.pddxml.relaxer.CardinalitySequence;
import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Disjunction;
import de.dfki.pddxml.relaxer.Equals;
import de.dfki.pddxml.relaxer.Exists;
import de.dfki.pddxml.relaxer.IExpressionChoice;
import de.dfki.pddxml.relaxer.Predicate;
import de.dfki.pddxml.relaxer.Variable;
import de.dfki.pddxml.relaxer.Variables;

public class OWLConditionalDescriptionConverter extends OWLDescriptionConverter 
{
    

    public OWLConditionalDescriptionConverter(DomainHelper context, String individualName, OWLOntologyManager owlManager)
    {
        super(context, individualName, owlManager);
    }

    public OWLConditionalDescriptionConverter(OWLDescriptionConverter parent)
    {
        super(parent);
    }

    /* (non-Javadoc)
     * @see org.semanticweb.owl.model.OWLDescriptionVisitor#visit(org.semanticweb.owl.model.OWLClass)
     */
    public void visit(OWLClass owlDescription)
    {
        getLogger().finer("Now visiting CLASS " + owlDescription.getURI());

        addVisited(owlDescription);
        
        /*
         * Check if we have already converted this class.
         */
        if(getConverterContext().containsClassAxiom(owlDescription.getURI()))
        {
            setPDDXMLExpression(getConverterContext().getClassAxiom(owlDescription.getURI()));
            return;
        }
        
        /*
         * This class' definition might contain several expressions.
         */
        List<IExpressionChoice> expressions = new LinkedList<IExpressionChoice>();        

        /*
         * Any individual stating explicitly to belong to this class will belong to this class.
         */
        expressions.add(getConverterContext().makeConcept(owlDescription.getURI().toString(), getIndividualName()));
        
        /*
         * Any individual satisfying sufficient conditions (in OWL expressed by equivalent class descriptions)
         * will belong to this class.
         */
        Set<OWLDescription> descriptions = owlDescription.getEquivalentClasses(owlManager.getOntologies());
        for(OWLDescription description : descriptions)
        {
            /* 
             * Check if a parent Visitor already visited the description. If so, we already added it to the
             * list of equivalent classes.
             */ 
            if(!visited(description))
            {
                description.accept(getSubOWLDescriptionConverter());
                IExpressionChoice expr = getSubOWLDescriptionConverter().getPDDXMLExpression();
                expressions.add(expr);
            }
        }

        // TODO ? descriptions.addAll(owlDescription.getEnumerations(owlDescription.getOntologies()));
        
        Disjunction disjunction = new Disjunction();
        disjunction.addExpression(expressions.toArray(new IExpressionChoice[0]));
        setPDDXMLExpression(disjunction);
    }

    /* (non-Javadoc)
     * @see org.semanticweb.owl.model.OWLDescriptionVisitor#visit(org.semanticweb.owl.model.OWLDataSomeRestriction)
     */
    public void visit(OWLDataSomeRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
    }

    /* (non-Javadoc)
     * @see org.semanticweb.owl.model.OWLDescriptionVisitor#visit(org.semanticweb.owl.model.OWLEnumeration)
     */
    public void visit(OWLObjectOneOf owlDescription)
    {
        getLogger().finer("Now visiting an Enumeration!");
        
        List<IExpressionChoice> expressions = new LinkedList<IExpressionChoice>();
        for(OWLIndividual individual : (Set<OWLIndividual>)owlDescription.getIndividuals())
        {
            String individualName = individual.getURI().toString();
            expressions.add(getConverterContext().makeIdentityPredicate(individualName));
        }
        
        Disjunction disjunction = new Disjunction();
        for(IExpressionChoice expression : expressions)
        {
            disjunction.addExpression(expression);
        }
        setPDDXMLExpression(disjunction);
    }

    /* (non-Javadoc)
     * @see org.semanticweb.owl.model.OWLDescriptionVisitor#visit(org.semanticweb.owl.model.OWLObjectSomeRestriction)
     */
    public void visit(OWLObjectSomeRestriction owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");

        Exists exists = new Exists();
        
        
        /* Make the variable declaration */
        
        // Exists ?x: ...
        Variables variables = new Variables();
        String variableName = getNextQuantifierVariable();
        Variable variable = new Variable();
        variable.setContent(variableName);
        variables.addVariable(variable);
        exists.setVariables(variables);

        
        /* Make the expression body which consists of a conjunction of
         * 1. The restriction of the variable to the class that some values must be from.
         * 2. The actual property (predicate) expression.   
         */ 

        // Exists x? : someValuesFromClass(?x) AND ...
        Conjunction conjunction = new Conjunction();
        
        OWLClass someValuesFromClass = (OWLClass)owlDescription.getFiller();
        conjunction.addExpression(getConverterContext().makeConcept(someValuesFromClass.getURI().toString(), variableName));

        // Exists x? : someValuesFromClass(?x) AND predicate(containingClass, ?x)
        String relationName = owlDescription.getProperty().asOWLObjectProperty().getURI().toString();
        String domainIndividual = getIndividualName();
        String rangeIndividual = variableName;
        Predicate predicate = getConverterContext().makeRelation(relationName, domainIndividual, rangeIndividual);
        conjunction.addExpression(predicate);
        
        exists.setExpression(conjunction);
        
        setPDDXMLExpression(exists);
    }

    /* (non-Javadoc)
     * @see org.semanticweb.owl.model.OWLDescriptionVisitor#visit(org.semanticweb.owl.model.OWLOr)
     */
    public void visit(OWLObjectUnionOf owlDescription)
    {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");
        
        Disjunction disjunction = new Disjunction();
        
        for(OWLDescription operandDescription : (Set<OWLDescription>)owlDescription.getOperands())
        {
            operandDescription.accept(getSubOWLDescriptionConverter());
            disjunction.addExpression(getSubOWLDescriptionConverter().getPDDXMLExpression());
        }
        setPDDXMLExpression(disjunction);

    }
    
    /** 
     * Gets an <code>OWLConditionalDescriptionConverter</code> to handle descriptions contained in this description.
     * @return an <code>OWLConditionalDescriptionConverter</code>
     * @see de.dfki.owls2pddxml_2_0.OWLDescriptionConverter#getSubOWLDescriptionConverter()
     */
    @Override
    protected OWLDescriptionConverter getSubOWLDescriptionConverter()
    {
        if(super.getSubOWLDescriptionConverter() == null)
        {
            super.setSubOWLDescriptionConverter(new OWLConditionalDescriptionConverter(this));
        }
        return super.getSubOWLDescriptionConverter();
    }

	@Override
	public void visit(OWLObjectMinCardinalityRestriction owlDescription) {
        Cardinality cardinality = new Cardinality();

        String relationName = owlDescription.getProperty().asOWLObjectProperty().getURI().toString();
        String domainIndividual = getIndividualName();
        String rangeIndividual = OWLConditionalDescriptionConverter.FREE_VARIABLE;
        Predicate predicate = getConverterContext().makeRelation(relationName, domainIndividual, rangeIndividual);
        cardinality.setPredicate(predicate);
        CardinalitySequence minmax = new CardinalitySequence();
        minmax.setMin(owlDescription.getCardinality());
        cardinality.setContent(minmax);

        setPDDXMLExpression(cardinality);
	}

	@Override
	public void visit(OWLObjectExactCardinalityRestriction owlDescription) {
        Cardinality cardinality = new Cardinality();

        String relationName = owlDescription.getProperty().asOWLObjectProperty().getURI().toString();
        String domainIndividual = getIndividualName();
        String rangeIndividual = OWLConditionalDescriptionConverter.FREE_VARIABLE;
        Predicate predicate = getConverterContext().makeRelation(relationName, domainIndividual, rangeIndividual);
        cardinality.setPredicate(predicate);
        Equals equals = new Equals();
        equals.setEquals(owlDescription.getCardinality());
        cardinality.setContent(equals);

        setPDDXMLExpression(cardinality);
	}

	@Override
	public void visit(OWLObjectMaxCardinalityRestriction owlDescription) {
        Cardinality cardinality = new Cardinality();

        String relationName = owlDescription.getProperty().asOWLObjectProperty().getURI().toString();
        String domainIndividual = getIndividualName();
        String rangeIndividual = OWLConditionalDescriptionConverter.FREE_VARIABLE;
        Predicate predicate = getConverterContext().makeRelation(relationName, domainIndividual, rangeIndividual);
        cardinality.setPredicate(predicate);
        CardinalitySequence minmax = new CardinalitySequence();
        minmax.setMax(owlDescription.getCardinality());
        cardinality.setContent(minmax);

        setPDDXMLExpression(cardinality);
	}

	@Override
	public void visit(OWLObjectSelfRestriction owlDescription) {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");       
	}

	@Override
	public void visit(OWLDataMinCardinalityRestriction owlDescription) {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");       
	}

	@Override
	public void visit(OWLDataExactCardinalityRestriction owlDescription) {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");       
	}

	@Override
	public void visit(OWLDataMaxCardinalityRestriction owlDescription) {
        getLogger().finer("Now visiting " + owlDescription.toString() + " (Class " + owlDescription.getClass().getName() + ")");       
	}

}
