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
 * <b>Equals</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <define name="Equals">
 *   <attribute name="equals">
 *     <data type="int">
 *       <param name="minInclusive">0</param>
 *     </data>
 *   </attribute>
 * </define>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;define name="Equals"&gt;
 *   &lt;attribute name="equals"&gt;
 *     &lt;data type="int"&gt;
 *       &lt;param name="minInclusive"&gt;0&lt;/param&gt;
 *     &lt;/data&gt;
 *   &lt;/attribute&gt;
 * &lt;/define&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Equals implements java.io.Serializable, Cloneable, IRVisitable, IRNode, ICardinalityChoice {
    private int equals_;
    private IRNode parentRNode_;

    /**
     * Creates a <code>Equals</code>.
     *
     */
    public Equals() {
    }

    /**
     * Creates a <code>Equals</code>.
     *
     * @param source
     */
    public Equals(Equals source) {
        setup(source);
    }

    /**
     * Creates a <code>Equals</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Equals(RStack stack) {
        setup(stack);
    }

    /**
     * Initializes the <code>Equals</code> by the Equals <code>source</code>.
     *
     * @param source
     */
    public void setup(Equals source) {
        int size;
        setEquals(source.getEquals());
    }

    /**
     * Initializes the <code>Equals</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        Element element = stack.getContextElement();
        equals_ = URelaxer.getAttributePropertyAsInt(element, "equals");
        stack.consumeAttribute(element.getAttributeNode("equals"));
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Equals(this));
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
        URelaxer.setAttributePropertyByInt(element, "equals", this.equals_);
    }

    /**
     * Gets the int property <b>equals</b>.
     *
     * @return int
     */
    public final int getEquals() {
        return (equals_);
    }

    /**
     * Sets the int property <b>equals</b>.
     *
     * @param equals
     */
    public final void setEquals(int equals) {
        this.equals_ = equals;
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
        buffer.append(" equals=\"");
        buffer.append(URelaxer.getString(getEquals()));
        buffer.append("\"");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer) throws IOException {
        int size;
        buffer.write(" equals=\"");
        buffer.write(URelaxer.getString(getEquals()));
        buffer.write("\"");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
        int size;
        buffer.print(" equals=\"");
        buffer.print(URelaxer.getString(getEquals()));
        buffer.print("\"");
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getEqualsAsString() {
        return (URelaxer.getString(getEquals()));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setEqualsByString(String string) {
        setEquals(Integer.parseInt(string));
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
     * is valid for the <code>Equals</code>.
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
     * is valid for the <code>Equals</code>.
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
        if (!URelaxer.hasAttributeHungry(target, "equals")) {
            return (false);
        }
        $match$ = true;
        return ($match$);
    }
}
