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
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLDataType;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLEntityVisitor;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.IExpressionChoice;


public class OWLFactConverter extends OWLObjectConverter implements OWLEntityVisitor
{   
    static int count = 1;
    
    protected StateHelper fState;
      
    public OWLFactConverter(DomainHelper context, StateHelper stateHelper, OWLOntologyManager owlManager)
    {
        super(context, owlManager);
        fState = stateHelper;
    }

    public void visit(OWLDataProperty owlEntity)
    {
        System.out.println("Now visiting " + owlEntity.toString());

    }

    public void visit(OWLObjectProperty owlEntity)
    {
        System.out.println("Now visiting " + owlEntity.toString());

    }

    public void visit(OWLIndividual owlEntity)
    {
        if(fState.containsIndividual(owlEntity.getURI()))
        {
            //setPDDXMLExpression(fState.getIndividual(owlEntity.getURI()));
            setPDDXMLExpression(new Conjunction());
            return;
        }
        
        String individualName = owlEntity.getURI().toString();
        getConverterContext().addObject(individualName);

        /*
         * Collects all the expressions defining this individual.
         */
        List<IExpressionChoice> expressions = new LinkedList<IExpressionChoice>();

        /*
         * Make an identity predicate (or, more acurately: every individual belongs to 
         * the concept containing only itself).
         */
        expressions.add(getConverterContext().makeIdentityPredicate(individualName));
        
        /*
         * Convert the descriptions of all types of this individual.
         */
        OWLFactualDescriptionConverter converter = new OWLFactualDescriptionConverter(getConverterContext(), individualName, owlManager);
                
        for(OWLDescription type : (Set<OWLDescription>)owlEntity.getTypes(owlManager.getOntologies()))
        {
            type.accept(converter);
            expressions.add(converter.getPDDXMLExpression());
        }
        
        Conjunction conjunction = new Conjunction();
        for(IExpressionChoice expression : expressions)
        {
            conjunction.addExpression(expression);
        }
        setPDDXMLExpression(conjunction);
        
        fState.putIndividual(owlEntity.getURI(), getPDDXMLExpression());
    }
    
    
    public void visit(OWLClass owlEntity)
    {
    }

	@Override
	public void visit(OWLDataType arg0) {
		// TODO Auto-generated method stub
		
	}

}
