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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <b>CardinalitySequence</b> is generated from PDDXMLDomain.rng by Relaxer.
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class CardinalitySequence implements java.io.Serializable, Cloneable, IRVisitable, IRNode, ICardinalityChoice {
    private int min_;
    private Integer max_;
    private IRNode parentRNode_;

    /**
     * Creates a <code>CardinalitySequence</code>.
     *
     */
    public CardinalitySequence() {
    }

    /**
     * Creates a <code>CardinalitySequence</code>.
     *
     * @param source
     */
    public CardinalitySequence(CardinalitySequence source) {
        setup(source);
    }

    /**
     * Creates a <code>CardinalitySequence</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public CardinalitySequence(RStack stack) {
        setup(stack);
    }

    /**
     * Initializes the <code>CardinalitySequence</code> by the CardinalitySequence <code>source</code>.
     *
     * @param source
     */
    public void setup(CardinalitySequence source) {
        int size;
        setMin(source.getMin());
        setMax(source.getMax());
    }

    /**
     * Initializes the <code>CardinalitySequence</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        Element element = stack.getContextElement();
        min_ = URelaxer.getAttributePropertyAsInt(element, "min");
        stack.consumeAttribute(element.getAttributeNode("min"));
        max_ = URelaxer.getAttributePropertyAsIntObject(element, "max");
        stack.consumeAttribute(element.getAttributeNode("max"));
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new CardinalitySequence(this));
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param parent
     */
    public void makeElement(Node parent) {
        Document doc = parent.getOwnerDocument();
        Element element = (Element)parent;
        int size;
        URelaxer.setAttributePropertyByInt(element, "min", this.min_);
        if (this.max_ != null) {
            URelaxer.setAttributePropertyByInt(element, "max", this.max_);
        }
    }

    /**
     * Gets the int property <b>min</b>.
     *
     * @return int
     */
    public final int getMin() {
        return (min_);
    }

    /**
     * Sets the int property <b>min</b>.
     *
     * @param min
     */
    public final void setMin(int min) {
        this.min_ = min;
    }

    /**
     * Gets the int property <b>max</b>.
     *
     * @return int
     */
    public int getMax() {
        if (max_ == null) {
            return(-1);
        }
        return (max_.intValue());
    }

    /**
     * Gets the int property <b>max</b>.
     *
     * @param max
     * @return int
     */
    public int getMax(int max) {
        if (max_ == null) {
            return(max);
        }
        return (this.max_.intValue());
    }

    /**
     * Gets the int property <b>max</b>.
     *
     * @return Integer
     */
    public Integer getMaxAsInteger() {
        return (max_);
    }

    /**
     * Check the int property <b>max</b>.
     *
     * @return boolean
     */
    public boolean checkMax() {
        return (max_ != null);
    }

    /**
     * Sets the int property <b>max</b>.
     *
     * @param max
     */
    public void setMax(int max) {
        this.max_ = new Integer(max);
    }

    /**
     * Sets the int property <b>max</b>.
     *
     * @param max
     */
    public void setMax(Integer max) {
        this.max_ = max;
    }

    /**
     * Makes an XML text representation.
     *
     * @return String
     */
    public String makeTextDocument() {
        StringBuffer buffer = new StringBuffer();
        makeTextElement(buffer);
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuffer buffer) {
        int size;
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
        int size;
        buffer.append(" min=\"");
        buffer.append(URelaxer.getString(getMin()));
        buffer.append("\"");
        if (max_ != null) {
            buffer.append(" max=\"");
            buffer.append(URelaxer.getString(getMax()));
            buffer.append("\"");
        }
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer) throws IOException {
        int size;
        buffer.write(" min=\"");
        buffer.write(URelaxer.getString(getMin()));
        buffer.write("\"");
        if (max_ != null) {
            buffer.write(" max=\"");
            buffer.write(URelaxer.getString(getMax()));
            buffer.write("\"");
        }
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
        int size;
        buffer.print(" min=\"");
        buffer.print(URelaxer.getString(getMin()));
        buffer.print("\"");
        if (max_ != null) {
            buffer.print(" max=\"");
            buffer.print(URelaxer.getString(getMax()));
            buffer.print("\"");
        }
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getMinAsString() {
        return (URelaxer.getString(getMin()));
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getMaxAsString() {
        return (URelaxer.getString(getMax()));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setMinByString(String string) {
        setMin(Integer.parseInt(string));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setMaxByString(String string) {
        setMax(Integer.parseInt(string));
    }

    /**
     * Returns a String representation of this object.
     * While this method informs as XML format representaion, 
     *  it's purpose is just information, not making 
     * a rigid XML documentation.
     *
     * @return String
     */
    public String toString() {
        try {
            return (makeTextDocument());
        } catch (Exception e) {
            return (super.toString());
        }
    }

    /**
     * Accepts the Visitor for enter behavior.
     *
     * @param visitor
     * @return boolean
     */
    public boolean enter(IRVisitor visitor) {
        return (visitor.enter(this));
    }

    /**
     * Accepts the Visitor for leave behavior.
     *
     * @param visitor
     */
    public void leave(IRVisitor visitor) {
        visitor.leave(this);
    }

    /**
     * Gets the IRNode property <b>parentRNode</b>.
     *
     * @return IRNode
     */
    public final IRNode rGetParentRNode() {
        return (parentRNode_);
    }

    /**
     * Sets the IRNode property <b>parentRNode</b>.
     *
     * @param parentRNode
     */
    public final void rSetParentRNode(IRNode parentRNode) {
        this.parentRNode_ = parentRNode;
    }

    /**
     * Gets child RNodes.
     *
     * @return IRNode[]
     */
    public IRNode[] rGetRNodes() {
        java.util.List classNodes = new java.util.ArrayList();
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>CardinalitySequence</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        return (isMatchHungry(stack.makeClone()));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>CardinalitySequence</code>.
     * This method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatchHungry(RStack stack) {
        RStack target = stack;
        boolean $match$ = false;
        Element element = stack.peekElement();
        Element child;
        if (!URelaxer.hasAttributeHungry(target, "min")) {
            return (false);
        }
        $match$ = true;
        return ($match$);
    }
}
