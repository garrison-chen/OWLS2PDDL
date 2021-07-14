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
import de.dfki.pddxml.relaxer.Cardinality;
import de.dfki.pddxml.relaxer.CardinalitySequence;
import de.dfki.pddxml.relaxer.Conditional;
import de.dfki.pddxml.relaxer.Conjunction;
import de.dfki.pddxml.relaxer.Disjunction;
import de.dfki.pddxml.relaxer.Domain;
import de.dfki.pddxml.relaxer.Effect;
import de.dfki.pddxml.relaxer.Equals;
import de.dfki.pddxml.relaxer.Exists;
import de.dfki.pddxml.relaxer.ForAll;
import de.dfki.pddxml.relaxer.IExpressionChoice;
import de.dfki.pddxml.relaxer.IRVisitor;
import de.dfki.pddxml.relaxer.Max;
import de.dfki.pddxml.relaxer.Negation;
import de.dfki.pddxml.relaxer.Parameter;
import de.dfki.pddxml.relaxer.Parameters;
import de.dfki.pddxml.relaxer.Precondition;
import de.dfki.pddxml.relaxer.Predicate;
import de.dfki.pddxml.relaxer.Predicates;
import de.dfki.pddxml.relaxer.Requirements;
import de.dfki.pddxml.relaxer.Type;
import de.dfki.pddxml.relaxer.Types;
import de.dfki.pddxml.relaxer.Variable;
import de.dfki.pddxml.relaxer.Variables;

public class PDDXMLCleaner implements IRVisitor
{
    public PDDXMLCleaner()
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
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Cardinality)
     */
    public boolean enter(Cardinality visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.CardinalitySequence)
     */
    public boolean enter(CardinalitySequence visitable)
    {
        // TODO Auto-generated method stub
        return false;
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
        int currentIndex = 0;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            expression.enter(this);
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
        return changed;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Disjunction)
     */
    public boolean enter(Disjunction visitable)
    {
        boolean changed = false;
        int currentIndex = 0;
        for(IExpressionChoice expression : visitable.getExpression())
        {
            expression.enter(this);
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
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Equals)
     */
    public boolean enter(Equals visitable)
    {
        // TODO Auto-generated method stub
        return false;
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
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Max)
     */
    public boolean enter(Max visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Negation)
     */
    public boolean enter(Negation visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Parameter)
     */
    public boolean enter(Parameter visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Parameters)
     */
    public boolean enter(Parameters visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Precondition)
     */
    public boolean enter(Precondition visitable)
    {
        return visitable.getExpression().enter(this);
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Predicate)
     */
    public boolean enter(Predicate visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Predicates)
     */
    public boolean enter(Predicates visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Requirements)
     */
    public boolean enter(Requirements visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Type)
     */
    public boolean enter(Type visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Types)
     */
    public boolean enter(Types visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Variable)
     */
    public boolean enter(Variable visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#enter(de.dfki.pddxml.relaxer.Variables)
     */
    public boolean enter(Variables visitable)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Action)
     */
    public void leave(Action visitable)
    {
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Actions)
     */
    public void leave(Actions visitable)
    {
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Cardinality)
     */
    public void leave(Cardinality visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.CardinalitySequence)
     */
    public void leave(CardinalitySequence visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Conditional)
     */
    public void leave(Conditional visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Conjunction)
     */
    public void leave(Conjunction visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Disjunction)
     */
    public void leave(Disjunction visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Domain)
     */
    public void leave(Domain visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Effect)
     */
    public void leave(Effect visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Equals)
     */
    public void leave(Equals visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Exists)
     */
    public void leave(Exists visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.ForAll)
     */
    public void leave(ForAll visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Max)
     */
    public void leave(Max visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Negation)
     */
    public void leave(Negation visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Parameter)
     */
    public void leave(Parameter visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Parameters)
     */
    public void leave(Parameters visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Precondition)
     */
    public void leave(Precondition visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Predicate)
     */
    public void leave(Predicate visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Predicates)
     */
    public void leave(Predicates visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Requirements)
     */
    public void leave(Requirements visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Type)
     */
    public void leave(Type visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Types)
     */
    public void leave(Types visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Variable)
     */
    public void leave(Variable visitable)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see de.dfki.pddxml.relaxer.IRVisitor#leave(de.dfki.pddxml.relaxer.Variables)
     */
    public void leave(Variables visitable)
    {
        // TODO Auto-generated method stub
        
    }
    
}
