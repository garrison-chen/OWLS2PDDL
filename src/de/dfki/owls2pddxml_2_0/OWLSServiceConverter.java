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

import java.io.StringReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owl.io.UnparsableOntologyException;
import org.semanticweb.owl.model.OWLConstant;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLDataPropertyExpression;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLObjectPropertyExpression;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.Action;
import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Effect;
import de.dfki.pddxml.relaxer.IExpressionChoice;
import de.dfki.pddxml.relaxer.Parameter;
import de.dfki.pddxml.relaxer.Parameters;
import de.dfki.pddxml.relaxer.Precondition;
import de.dfki.pddxml.relaxer.Predicate;

public class OWLSServiceConverter 
{

	private static URI				OWLS_SERVICE_PRESENTS = URI.create("http://www.daml.org/services/owl-s/1.1/Service.owl#presents");

	private static URI				OWLS_SERVICE_DESCRIBEDBY = URI.create("http://www.daml.org/services/owl-s/1.1/Service.owl#describedBy");
	
	private static URI				OWLS_PROFILE_HASINPUT = URI.create("http://www.daml.org/services/owl-s/1.1/Profile.owl#hasInput");
	
	private static URI				OWLS_PROFILE_HASOUTPUT = URI.create("http://www.daml.org/services/owl-s/1.1/Profile.owl#hasOutput");

	private static URI				OWLS_PROCESS_PARAMETERTYPE = URI.create("http://www.daml.org/services/owl-s/1.1/Process.owl#parameterType");

	private static URI				OWLS_PROCESS_HASPRECONDITION = URI.create("http://www.daml.org/services/owl-s/1.1/Process.owl#hasPrecondition");

	private static URI				OWLS_PROCESS_HASRESULT = URI.create("http://www.daml.org/services/owl-s/1.1/Process.owl#hasResult");

	private static URI				OWLS_PROCESS_HASEFFECT = URI.create("http://www.daml.org/services/owl-s/1.1/Process.owl#hasEffect");

	private static URI				OWLS_EXPRESSION_EXPRESSIONBODY = URI.create("http://www.daml.org/services/owl-s/1.1/generic/Expression.owl#expressionBody");

        
    private DomainHelper fConverterContext;
    private PDDXMLPredicateSignatureGenerator fPDDXMLPredicateSignatureGenerator;
    
    private OWLOntologyManager owlManager;
    
    public OWLSServiceConverter(DomainHelper context, OWLOntologyManager owlManager)
    {
    	this.owlManager = owlManager;
        fConverterContext = context;
        fPDDXMLPredicateSignatureGenerator = new PDDXMLPredicateSignatureGenerator(context);
    }

    public void convert(URI serviceUri) throws OWLException
    {

    	OWLOntology serviceOnt = owlManager.loadOntology(serviceUri);

		// get service process
		OWLIndividual service = owlManager.getOWLDataFactory().getOWLIndividual(serviceUri);			
		OWLIndividual process = getObjectProperty(service, OWLS_SERVICE_DESCRIBEDBY, serviceOnt); 
		OWLIndividual profile = getObjectProperty(service, OWLS_SERVICE_PRESENTS, serviceOnt);
        if(process == null)
        {
            OWLS2PDDL.LOGGER.log(Level.WARNING, "Not adding service " + service.getURI() + " because it has no process definition!");
            return;
        }    	
    	
        OWLS2PDDL.LOGGER.info("Converting service " + serviceUri);

        Action action = new Action();
        action.setName(service.getURI().toString());
            
        /*
         * Construct the precondition. For each input i, 
         * - the action gets an according parameter,
         * - the agentHasKnowledgeAbout(i) and
         * - its type's description must hold. 
         */
        
        Parameters parameters = new Parameters();
        
        Precondition precondition = new Precondition();
        Conjunction precondition_conjunction = new Conjunction();
        
		Set<OWLIndividual> inputs = getObjectProperties(profile, OWLS_PROFILE_HASINPUT, serviceOnt);	
		for(OWLIndividual input : inputs) {
            // Add the parameter to the action
            String paramName = "?" + input.getURI().toString();
            Parameter parameter = new Parameter();
            parameter.setContent(paramName);
            parameter.setType(DomainHelper.OBJECT_TYPE);
            parameters.addParameter(parameter);
            
            // Add agentHasKnowledgeAbout(i) to the precondition
            // 23Mar2021: removing agentHasKnowledgeAbout
            //precondition_conjunction.addExpression(fConverterContext.makeAgentHasKnowledgeAboutPredicate(paramName));
            
            // Add the type description to the precondition
			OWLConstant literal = getDataProperty(input, OWLS_PROCESS_PARAMETERTYPE, serviceOnt);
			String paramType = literal.getLiteral();
			// Get the WonderWeb OWL Ontology
            OWLConditionalDescriptionConverter converter = new OWLConditionalDescriptionConverter(fConverterContext, paramName, owlManager);
            owlManager.loadOntologyFromPhysicalURI(URI.create(paramType));
            owlManager.getOWLDataFactory().getOWLClass(URI.create(paramType)).accept(converter);
            
            precondition_conjunction.addExpression(converter.getPDDXMLExpression());
		}
		
		Set<OWLIndividual> preconditions = getObjectProperties(process, OWLS_PROCESS_HASPRECONDITION, serviceOnt);
		for(OWLIndividual condition : preconditions) {
			OWLConstant literal = getDataProperty(condition, OWLS_EXPRESSION_EXPRESSIONBODY, serviceOnt);
			
			String pddxml = null;
			try {
			pddxml = literal.getLiteral();
			}
			catch(NullPointerException e){
				// handle PDDL
				System.out.println("no precondtions");
			}
            StringReader reader = new StringReader(pddxml);
            IExpressionChoice preconditionExpression = null;
            
            try
            {
                preconditionExpression = new Conjunction(reader);
            }
            catch(Exception e)
            {
                try
                {
                    reader.reset();
                    Predicate predicate = new Predicate(reader);
                    preconditionExpression = predicate;
                    
                    fConverterContext.addRelationSignature(predicate.getName(), predicate.sizeParameter());
                }
                catch(Exception e2)
                {
                    Logger.getLogger(this.getClass().getName()).warning("Ignoring precondition because it neither a conjunciton nor a predicate; The condition:\n" + pddxml);
                }
            }
            precondition_conjunction.addExpression(preconditionExpression);			
		}
        
        Effect effect = new Effect();
        Conjunction effect_conjunction = new Conjunction();
		Set<OWLIndividual> outputs = getObjectProperties(profile, OWLS_PROFILE_HASOUTPUT, serviceOnt);	
		for(OWLIndividual output : outputs) {        
            // Add the parameter to the action
            String paramName = "?" + output.getURI().toString();
            Parameter parameter = new Parameter();
            parameter.setContent(paramName);
            parameter.setType(DomainHelper.OBJECT_TYPE);
            parameters.addParameter(parameter);
            
            // Add agentHasKnowledgeAbout(i) to the effect
            // 23Mar2021: removing agentHasKnowledgeAbout
            // effect_conjunction.addExpression(fConverterContext.makeAgentHasKnowledgeAboutPredicate(paramName));
            
            /*
             * Make an identity predicate (or, more acurately: every individual belongs to 
             * the concept containing only itself).
             */
            effect_conjunction.addExpression(fConverterContext.makeIdentityPredicate(paramName));

            // Add the type description to the _precondition_ (!)
            // Add the type description to the precondition
			OWLConstant literal = getDataProperty(output, OWLS_PROCESS_PARAMETERTYPE, serviceOnt);
			String paramType = literal.getLiteral();
            // Get the WonderWeb OWL Ontology
            OWLConditionalDescriptionConverter converter = new OWLConditionalDescriptionConverter(fConverterContext, paramName, owlManager);
            owlManager.loadOntologyFromPhysicalURI(URI.create(paramType));
            owlManager.getOWLDataFactory().getOWLClass(URI.create(paramType)).accept(converter);
            precondition_conjunction.addExpression(converter.getPDDXMLExpression());
		}

        precondition.setExpression(precondition_conjunction);
        action.setPrecondition(precondition);
        
		OWLIndividual result = getObjectProperty(process, OWLS_PROCESS_HASRESULT, serviceOnt);
		if(result != null) {		
			Set<OWLIndividual> effects = getObjectProperties(result, OWLS_PROCESS_HASEFFECT, serviceOnt);
			for(OWLIndividual condition : effects) {
				OWLConstant literal = getDataProperty(condition, OWLS_EXPRESSION_EXPRESSIONBODY, serviceOnt);
				String pddxml = literal.getLiteral();
                StringReader reader = new StringReader(pddxml);
                IExpressionChoice effectExpression = null;

                try
                {
                    effectExpression = new Conjunction(reader);
                }
                catch(Exception e)
                {
                    try
                    {
                        reader.reset();
                        Predicate predicate = new Predicate(reader);
                        effectExpression = predicate;
                        
                        fConverterContext.addRelationSignature(predicate.getName(), predicate.sizeParameter());

                    }
                    catch(Exception e2)
                    {
                        Logger.getLogger(this.getClass().getName()).warning("Ignoring precondition because it neither a conjunciton nor a predicate; The condition:\n" + pddxml);
                    }
                }
                
                effect_conjunction.addExpression(effectExpression);
			}
		}
        effect_conjunction.enter(fPDDXMLPredicateSignatureGenerator);
        effect.setExpression(effect_conjunction);
        action.setEffect(effect);

        action.setParameters(parameters);
        
        fConverterContext.addAction(action);
    }
    
	private OWLObjectProperty findObjectProperty(OWLIndividual individual,
			URI propertyURI, OWLOntology serviceOnt) throws OWLException {
		Map<OWLObjectPropertyExpression, Set<OWLIndividual>> propertyMap = individual
				.getObjectPropertyValues(serviceOnt);
		if (propertyMap != null) {
			for (OWLObjectPropertyExpression property : propertyMap.keySet()) {
				if (property.asOWLObjectProperty().getURI().equals(propertyURI))
					return property.asOWLObjectProperty();
			}
		}
		return null;
	}
	
	private Set<OWLIndividual> getObjectProperties(OWLIndividual individual,
			URI objectPropertyURI, OWLOntology serviceOnt) throws OWLException {
		OWLObjectProperty property = (OWLObjectProperty) findObjectProperty(
				individual, objectPropertyURI, serviceOnt);
		Set<OWLIndividual> values = (Set<OWLIndividual>) individual
				.getObjectPropertyValues(serviceOnt).get(property);
		if (values != null)
			return values;
		return new HashSet<OWLIndividual>();
	}

	private OWLIndividual getObjectProperty(OWLIndividual individual,
			URI objectPropertyURI, OWLOntology serviceOnt) throws OWLException {
		Set<OWLIndividual> values = getObjectProperties(individual,
				objectPropertyURI, serviceOnt);
		if (values.size() >= 1)
			return values.iterator().next();
		return null;
	}
	
	private OWLDataProperty findDataProperty(OWLIndividual individual,
			URI propertyURI, OWLOntology serviceOnt) throws OWLException {
		Map<OWLDataPropertyExpression, Set<OWLConstant>> propertyMap = individual
				.getDataPropertyValues(serviceOnt);
		if (propertyMap != null) {
			for (OWLDataPropertyExpression property : propertyMap.keySet()) {
				if (property.asOWLDataProperty().getURI().equals(propertyURI))
					return property.asOWLDataProperty();
			}
		}
		return null;
	}
	
	private Set<OWLConstant> getDataProperties(OWLIndividual individual,
			URI dataPropertyURI, OWLOntology serviceOnt) throws OWLException {
		OWLDataProperty property = (OWLDataProperty) findDataProperty(
				individual, dataPropertyURI, serviceOnt);
		return (Set<OWLConstant>) individual.getDataPropertyValues(serviceOnt)
				.get(property);
	}

	private OWLConstant getDataProperty(OWLIndividual individual,
			URI dataPropertyURI, OWLOntology serviceOnt) throws OWLException {
		Set<OWLConstant> s = getDataProperties(individual, dataPropertyURI, serviceOnt);
		
//		(2Dec2020. maybe?)
		if(s == null)
			return null;
		
		if (s.iterator().hasNext()) {
			return s.iterator().next();
		}
		return null;
	}
}
