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

import de.dfki.pddxml.relaxer.Predicate;


public class PDDXMLPredicateSignatureGenerator extends PDDXMLExpressionTraverser
{
    private DomainHelper fConverterContext;
    
    public PDDXMLPredicateSignatureGenerator(DomainHelper context)
    {
        fConverterContext = context;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.RVisitorBase#enter(de.dfki.pddxml.relaxer.Predicate)
     */
    @Override
    public boolean enter(Predicate visitable)
    {
        return fConverterContext.addRelationSignature(visitable.getName(), visitable.sizeParameter());
    }
    
    
}
