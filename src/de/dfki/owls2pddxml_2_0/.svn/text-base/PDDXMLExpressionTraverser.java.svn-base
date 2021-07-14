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

import de.dfki.pddxml.relaxer.Action;
import de.dfki.pddxml.relaxer.Actions;
import de.dfki.pddxml.relaxer.Conditional;
import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Disjunction;
import de.dfki.pddxml.relaxer.Domain;
import de.dfki.pddxml.relaxer.Effect;
import de.dfki.pddxml.relaxer.Exists;
import de.dfki.pddxml.relaxer.ForAll;
import de.dfki.pddxml.relaxer.IExpressionChoice;
import de.dfki.pddxml.relaxer.Negation;
import de.dfki.pddxml.relaxer.Precondition;
import de.dfki.pddxml.relaxer.RVisitorBase;

public abstract class PDDXMLExpressionTraverser extends RVisitorBase
{
    public PDDXMLExpressionTraverser()
    {
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Action)
     */
    public boolean enter(Action visitable)
    {
        boolean changed = visitable.getPrecondition().getExpression().enter(this);
        changed |= visitable.getEffect().getExpression().enter(this);
        return changed;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Actions)
     */
    public boolean enter(Actions visitable)
    {
        boolean changed = false;
        for(Action action : visitable.getAction())
        {
            changed |= action.enter(this);
        }
        return true;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Conditional)
     */
    public boolean enter(Conditional visitable)
    {
        boolean changed = visitable.getExpression1().enter(this);
        changed |= visitable.getExpression2().enter(this);
        return changed;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Conjunction)
     */
    public boolean enter(Conjunction visitable)
    {
        boolean changed = false;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            changed |= expression.enter(this);
        }
        return changed;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Disjunction)
     */
    public boolean enter(Disjunction visitable)
    {
        boolean changed = false;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            changed |= expression.enter(this);
        }
        return changed;
    }
    

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Domain)
     */
    public boolean enter(Domain visitable)
    {
        return visitable.getActions().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Effect)
     */
    public boolean enter(Effect visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Exists)
     */
    public boolean enter(Exists visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.ForAll)
     */
    public boolean enter(ForAll visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Negation)
     */
    public boolean enter(Negation visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Precondition)
     */
    public boolean enter(Precondition visitable)
    {
        return visitable.getExpression().enter(this);
    }

}
