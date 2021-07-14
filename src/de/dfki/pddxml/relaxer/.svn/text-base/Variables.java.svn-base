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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>Variables</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="variables">
 *   <oneOrMore>
 *     <ref name="Variable"/>
 *   </oneOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="variables"&gt;
 *   &lt;oneOrMore&gt;
 *     &lt;ref name="Variable"/&gt;
 *   &lt;/oneOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Variables implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
    // List<Variable>
    private java.util.List variable_ = new java.util.ArrayList();
    private IRNode parentRNode_;

    /**
     * Creates a <code>Variables</code>.
     *
     */
    public Variables() {
    }

    /**
     * Creates a <code>Variables</code>.
     *
     * @param source
     */
    public Variables(Variables source) {
        setup(source);
    }

    /**
     * Creates a <code>Variables</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Variables(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Variables</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Variables(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Variables</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Variables(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Variables</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Variables</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Variables</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Variables</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Variables</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Variables</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Variables(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Variables</code> by the Variables <code>source</code>.
     *
     * @param source
     */
    public void setup(Variables source) {
        int size;
        this.variable_.clear();
        size = source.variable_.size();
        for (int i = 0;i < size;i++) {
            addVariable((Variable)source.getVariable(i).clone());
        }
    }

    /**
     * Initializes the <code>Variables</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Variables</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Variables</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        init(stack.popElement());
    }

    /**
     * @param element
     */
    private void init(Element element) {
        RStack stack = new RStack(element);
        variable_.clear();
        while (true) {
            if (Variable.isMatch(stack)) {
                addVariable(new Variable(stack));
            } else {
                break;
            }
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Variables(this));
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param parent
     */
    public void makeElement(Node parent) {
        Document doc;
        if (parent instanceof Document) {
            doc = (Document)parent;
        } else {
            doc = parent.getOwnerDocument();
        }
        Element element = doc.createElement("variables");
        int size;
        size = this.variable_.size();
        for (int i = 0;i < size;i++) {
            Variable value = (Variable)this.variable_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Variables</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the <code>Variables</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>Variables</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>Variables</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>Variables</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>Variables</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(reader, UJAXP.FLAG_NONE));
    }

    /**
     * Creates a DOM document representation of the object.
     *
     * @exception ParserConfigurationException
     * @return Document
     */
    public Document makeDocument() throws ParserConfigurationException {
        Document doc = UJAXP.makeDocument();
        makeElement(doc);
        return (doc);
    }

    /**
     * Gets the Variable property <b>Variable</b>.
     *
     * @return Variable[]
     */
    public final Variable[] getVariable() {
        Variable[] array = new Variable[variable_.size()];
        return ((Variable[])variable_.toArray(array));
    }

    /**
     * Sets the Variable property <b>Variable</b>.
     *
     * @param variable
     */
    public final void setVariable(Variable[] variable) {
        this.variable_.clear();
        for (int i = 0;i < variable.length;i++) {
            addVariable(variable[i]);
        }
        for (int i = 0;i < variable.length;i++) {
            variable[i].rSetParentRNode(this);
        }
    }

    /**
     * Sets the Variable property <b>Variable</b>.
     *
     * @param variable
     */
    public final void setVariable(Variable variable) {
        this.variable_.clear();
        addVariable(variable);
        if (variable != null) {
            variable.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Variable property <b>Variable</b>.
     *
     * @param variable
     */
    public final void addVariable(Variable variable) {
        this.variable_.add(variable);
        if (variable != null) {
            variable.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Variable property <b>Variable</b>.
     *
     * @param variable
     */
    public final void addVariable(Variable[] variable) {
        for (int i = 0;i < variable.length;i++) {
            addVariable(variable[i]);
        }
        for (int i = 0;i < variable.length;i++) {
            variable[i].rSetParentRNode(this);
        }
    }

    /**
     * Gets number of the Variable property <b>Variable</b>.
     *
     * @return int
     */
    public final int sizeVariable() {
        return (variable_.size());
    }

    /**
     * Gets the Variable property <b>Variable</b> by index.
     *
     * @param index
     * @return Variable
     */
    public final Variable getVariable(int index) {
        return ((Variable)variable_.get(index));
    }

    /**
     * Sets the Variable property <b>Variable</b> by index.
     *
     * @param index
     * @param variable
     */
    public final void setVariable(int index, Variable variable) {
        this.variable_.set(index, variable);
        if (variable != null) {
            variable.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Variable property <b>Variable</b> by index.
     *
     * @param index
     * @param variable
     */
    public final void addVariable(int index, Variable variable) {
        this.variable_.add(index, variable);
        if (variable != null) {
            variable.rSetParentRNode(this);
        }
    }

    /**
     * Remove the Variable property <b>Variable</b> by index.
     *
     * @param index
     */
    public final void removeVariable(int index) {
        this.variable_.remove(index);
    }

    /**
     * Remove the Variable property <b>Variable</b> by object.
     *
     * @param variable
     */
    public final void removeVariable(Variable variable) {
        this.variable_.remove(variable);
    }

    /**
     * Clear the Variable property <b>Variable</b>.
     *
     */
    public final void clearVariable() {
        this.variable_.clear();
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
        buffer.append("<variables");
        buffer.append(">");
        size = this.variable_.size();
        for (int i = 0;i < size;i++) {
            Variable value = (Variable)this.variable_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</variables>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<variables");
        buffer.write(">");
        size = this.variable_.size();
        for (int i = 0;i < size;i++) {
            Variable value = (Variable)this.variable_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</variables>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<variables");
        buffer.print(">");
        size = this.variable_.size();
        for (int i = 0;i < size;i++) {
            Variable value = (Variable)this.variable_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</variables>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer) throws IOException {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
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
        classNodes.addAll(variable_);
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>Variables</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "variables")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!Variable.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        while (true) {
            if (!Variable.isMatchHungry(target)) {
                break;
            }
            $match$ = true;
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>Variables</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        return (isMatch(element));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>Variables</code>.
     * This method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatchHungry(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        if (isMatch(element)) {
            stack.popElement();
            return (true);
        } else {
            return (false);
        }
    }
}
