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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owl.model.OWLOntologyManager;

import de.dfki.pddxml.relaxer.IExpressionChoice;

public abstract class OWLObjectConverter
{

    /** The PDDXML Expression representing the top level OWL Description.*/
    private IExpressionChoice fPDDXMLExpression;
    private DomainHelper fConverterContext;
    private Logger fLogger;
    
    protected OWLOntologyManager owlManager;
    

    protected OWLObjectConverter(DomainHelper context, OWLOntologyManager owlManager)
    {
        fConverterContext = context;
        fLogger = Logger.getLogger(this.getClass().getName());
        fLogger.setLevel(Level.ALL);
        
        this.owlManager = owlManager;
    }
    
    /**
     * @return the converterContext
     */
    protected DomainHelper getConverterContext()
    {
        return fConverterContext;
    }


    /**
     * @return the PDDXMLExpression
     */
    public IExpressionChoice getPDDXMLExpression()
    {
        return fPDDXMLExpression;
    }

    /**
     * @param expression the pDDXMLExpression to set
     */
    protected void setPDDXMLExpression(IExpressionChoice expression)
    {
        if(expression == null)
        {
            throw new NullPointerException("Expression must not be null!"); 
        }
        fPDDXMLExpression = expression;
    }


    /**
     * @return the logger
     */
    public Logger getLogger()
    {
        return fLogger;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger)
    {
        fLogger = logger;
    }

}