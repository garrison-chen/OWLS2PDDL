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

package de.dfki.owls2pddxml_2_0;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectPropertyExpression;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.IExpressionChoice;

public class OWLKnownFactConverter extends OWLFactConverter
{

    public OWLKnownFactConverter(DomainHelper context, StateHelper stateHelper, OWLOntologyManager owlManager)
    {
        super(context, stateHelper, owlManager);
    }

    /**
     * 
     * @see de.dfki.owls2pddxml_2_0.OWLFactConverter#visit(org.semanticweb.owl.model.OWLIndividual)
     */
    @Override
    public void visit(OWLIndividual owlEntity)
    {
        if(fState.containsIndividual(owlEntity.getURI()))
        {
            //setPDDXMLExpression(fState.getIndividual(owlEntity.getURI()));
            setPDDXMLExpression(new Conjunction());
            return;
        }

        super.visit(owlEntity);
        Conjunction conjunction = new Conjunction();
        conjunction.addExpression(getPDDXMLExpression());

        /*
         * Add agentHasKnowledgeAbout(individualName)
         */
        
        conjunction.addExpression(getConverterContext().makeAgentHasKnowledgeAboutPredicate(owlEntity.getURI().toString()));
        /*
         * Convert the properties
         */
       
        Map<OWLObjectPropertyExpression,Set<OWLIndividual>> objectPropertyMap = new HashMap<OWLObjectPropertyExpression, Set<OWLIndividual>>();
        for(OWLOntology ontology : owlManager.getOntologies()) {
        	objectPropertyMap.putAll(owlEntity.getObjectPropertyValues(ontology));
        }
        for(OWLObjectPropertyExpression property : objectPropertyMap.keySet())
        {
            Set<OWLIndividual> values = objectPropertyMap.get(property);
            for(OWLIndividual value : values)
            {
                conjunction.addExpression(makePropertyExpression(owlEntity, property, value));
            }
        }
        
//        Map<OWLDataProperty,Set<OWLDataProperty>> dataPropertyMap = owlEntity.getDataPropertyValues(owlManager.getOntologies());

        setPDDXMLExpression(conjunction);
        fState.putIndividual(owlEntity.getURI(), getPDDXMLExpression());

    }

    private IExpressionChoice makePropertyExpression(OWLIndividual leftValue, OWLObjectPropertyExpression property, OWLIndividual rightValue) {
        OWLFactConverter rightConverter = new OWLKnownFactConverter(getConverterContext(), fState, owlManager);
        rightValue.accept(rightConverter);

        Conjunction conjunction = new Conjunction();
        conjunction.addExpression(rightConverter.getPDDXMLExpression());
        
        String leftValueName = leftValue.getURI().toString();
        String relationName = property.getNamedProperty().getURI().toString();
        String rightValueName = rightValue.getURI().toString();

        conjunction.addExpression(getConverterContext().makeRelation(relationName, leftValueName, rightValueName));
        fState.put(leftValueName, relationName, rightValueName);
        
        if(property.isSymmetric(owlManager.getOntologies()))
        {
            if(!fState.contains(rightValueName, relationName, leftValueName))
            {
                conjunction.addExpression(getConverterContext().makeRelation(relationName, rightValueName, leftValueName));
                fState.put(rightValueName, relationName, leftValueName);
               
            }
        }
        
        for(OWLObjectPropertyExpression invers : property.getInverses(owlManager.getOntologies()))
        {
            conjunction.addExpression(getConverterContext().makeRelation(invers.getNamedProperty().getURI().toString(), rightValueName, leftValueName));
            fState.put(rightValueName, invers.getNamedProperty().getURI().toString(), leftValueName);
        }                

        return conjunction;
    }

}
