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

import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Disjunction;
import de.dfki.pddxml.relaxer.IExpressionChoice;

public class PDDXMLExpressionCleaner extends PDDXMLExpressionTraverser
{
    public boolean enter(Conjunction visitable)
    {
        boolean changed = super.enter(visitable);
        int currentIndex = 0;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            if(expression instanceof Conjunction)
            {
                for(IExpressionChoice expression2 : ((Conjunction)expression).getExpression())
                {
                    visitable.addExpression(expression2);
                }
                visitable.removeExpression(currentIndex);
                changed = true;
            }
            else
            {
                ++currentIndex;
            }
        }
        
        if(visitable.sizeExpression() == 1)
        {
            visitable.getExpression(0).rSetParentRNode(visitable.rGetParentRNode());
        }
        
        return changed;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Disjunction)
     */
    public boolean enter(Disjunction visitable)
    {
        boolean changed = super.enter(visitable);
        int currentIndex = 0;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            if(expression instanceof Disjunction)
            {
                for(IExpressionChoice expression2 : ((Disjunction)expression).getExpression())
                {
                    visitable.addExpression(expression2);
                }
                visitable.removeExpression(currentIndex);
                changed = true;
            }
            else
            {
                ++currentIndex;
            }
        }
        
        if(visitable.sizeExpression() == 1)
        {
            visitable.getExpression(0).rSetParentRNode(visitable.rGetParentRNode());
        }

        return changed;
    }

}
