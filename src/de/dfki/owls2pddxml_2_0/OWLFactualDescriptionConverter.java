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
import org.semanticweb.owl.model.OWLDataCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataExactCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataMaxCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataMinCardinalityRestriction;
import org.semanticweb.owl.model.OWLDataSomeRestriction;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLObjectExactCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectMaxCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectMinCardinalityRestriction;
import org.semanticweb.owl.model.OWLObjectOneOf;
import org.semanticweb.owl.model.OWLObjectSelfRestriction;
import org.semanticweb.owl.model.OWLObjectSomeRestriction;
import org.semanticweb.owl.model.OWLObjectUnionOf;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.IExpressionChoice;

public class OWLFactualDescriptionConverter extends OWLDescriptionConverter
{
    public OWLFactualDescriptionConverter(DomainHelper context, String individualName, OWLOntologyManager owlManager)
    {
        super(context, individualName, owlManager);
    }

    public OWLFactualDescriptionConverter(OWLDescriptionConverter parent)
    {
        super(parent);
    }

    public void visit(OWLDataCardinalityRestriction arg0) throws OWLException
    {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
    }

    public void visit(OWLDataSomeRestriction arg0)
    {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");

    }

    public void visit(OWLObjectSomeRestriction arg0)
    {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");

    }

    public void visit(OWLObjectUnionOf arg0)
    {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");

    }

    public void visit(OWLClass owlClass)
    {
        getLogger().finer("Now visiting CLASS " + owlClass.getURI());

        addVisited(owlClass);

        /*
         * This class' definition might contain several expressions.
         */
        List<IExpressionChoice> expressions = new LinkedList<IExpressionChoice>();        

        /*
         * Explicit class statement.
         */
        expressions.add(getConverterContext().makeConcept(owlClass.getURI().toString(), getIndividualName()));

        /*
         * The individual also belongs to all of the superclasses.
         */
        Set<OWLDescription> descriptions = owlClass.getSuperClasses(owlManager.getOntologies());
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
        
        Conjunction conjunction = new Conjunction();
        conjunction.addExpression(expressions.toArray(new IExpressionChoice[0]));
        setPDDXMLExpression(conjunction);
    }

    public void visit(OWLObjectOneOf arg0)
    {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");

    }

    /** 
     * Gets an <code>OWLFactualDescriptionConverter</code> to handle descriptions contained in this description.
     * @return an <code>OWLFactualDescriptionConverter</code>
     * @see de.dfki.owls2pddxml_2_0.OWLDescriptionConverter#getSubOWLDescriptionConverter()
     */
    @Override
    protected OWLDescriptionConverter getSubOWLDescriptionConverter()
    {
        if(super.getSubOWLDescriptionConverter() == null)
        {
            super.setSubOWLDescriptionConverter(new OWLFactualDescriptionConverter(this));
        }
        return super.getSubOWLDescriptionConverter();
    }

	@Override
	public void visit(OWLObjectMinCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLObjectExactCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLObjectMaxCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLObjectSelfRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLDataMinCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLDataExactCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");
	}

	@Override
	public void visit(OWLDataMaxCardinalityRestriction arg0) {
        getLogger().finer("Ignoring non-deterministic description in facual statement.");	
	}
}
