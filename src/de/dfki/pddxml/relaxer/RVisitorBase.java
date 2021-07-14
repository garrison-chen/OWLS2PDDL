/*
 * The Relaxer artifact
 * Copyright (c) 2000-2003, ASAMI Tomoharu, All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer. 
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.dfki.pddxml.relaxer;

/**
 * @version PDDXMLDomain.rng 1.0 (Wed Jan 03 14:40:18 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class RVisitorBase implements IRVisitor {

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Domain visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Domain visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Requirements visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Requirements visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Types visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Types visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Type visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Type visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Predicates visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Predicates visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Predicate visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Predicate visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Parameter visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Parameter visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Actions visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Actions visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Action visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Action visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Parameters visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Parameters visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Precondition visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Precondition visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Conditional visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Conditional visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Cardinality visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Cardinality visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(ForAll visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(ForAll visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Variables visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Variables visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Variable visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Variable visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Exists visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Exists visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Conjunction visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Conjunction visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Disjunction visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Disjunction visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Negation visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Negation visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Effect visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Effect visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(CardinalitySequence visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(CardinalitySequence visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Max visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Max visitable) {
    }

    /**
     * Visits this node for enter behavior.
     *
     * @param visitable
     * @return boolean
     */
    public boolean enter(Equals visitable) {
        return (true);
    }

    /**
     * Visits this node for leave behavior.
     *
     * @param visitable
     */
    public void leave(Equals visitable) {
    }
}
