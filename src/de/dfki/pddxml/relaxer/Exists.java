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
 * <b>Exists</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="exists">
 *   <ref name="Variables"/>
 *   <ref name="Expression"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="exists"&gt;
 *   &lt;ref name="Variables"/&gt;
 *   &lt;ref name="Expression"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:18 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Exists implements java.io.Serializable, Cloneable, IRVisitable, IRNode, IExpressionChoice {
    private Variables variables_;
    private IExpressionChoice expression_;
    private IRNode parentRNode_;

    /**
     * Creates a <code>Exists</code>.
     *
     */
    public Exists() {
    }

    /**
     * Creates a <code>Exists</code>.
     *
     * @param source
     */
    public Exists(Exists source) {
        setup(source);
    }

    /**
     * Creates a <code>Exists</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Exists(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Exists</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Exists(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Exists</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Exists(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Exists</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Exists</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Exists</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Exists</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Exists</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Exists</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Exists(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Exists</code> by the Exists <code>source</code>.
     *
     * @param source
     */
    public void setup(Exists source) {
        int size;
        if (source.variables_ != null) {
            setVariables((Variables)source.getVariables().clone());
        }
        if (source.expression_ != null) {
            setExpression((IExpressionChoice)source.getExpression().clone());
        }
    }

    /**
     * Initializes the <code>Exists</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Exists</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Exists</code> by the Stack <code>stack</code>
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
        setVariables(new Variables(stack));
        if (Predicate.isMatch(stack)) {
            setExpression(new Predicate(stack));
        } else if (Conditional.isMatch(stack)) {
            setExpression(new Conditional(stack));
        } else if (Cardinality.isMatch(stack)) {
            setExpression(new Cardinality(stack));
        } else if (ForAll.isMatch(stack)) {
            setExpression(new ForAll(stack));
        } else if (Exists.isMatch(stack)) {
            setExpression(new Exists(stack));
        } else if (Conjunction.isMatch(stack)) {
            setExpression(new Conjunction(stack));
        } else if (Disjunction.isMatch(stack)) {
            setExpression(new Disjunction(stack));
        } else if (Negation.isMatch(stack)) {
            setExpression(new Negation(stack));
        } else {
            throw (new IllegalArgumentException());
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Exists(this));
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
        Element element = doc.createElement("exists");
        int size;
        this.variables_.makeElement(element);
        this.expression_.makeElement(element);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Exists</code> by the File <code>file</code>.
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
     * Initializes the <code>Exists</code>
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
     * Initializes the <code>Exists</code> by the URL <code>url</code>.
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
     * Initializes the <code>Exists</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Exists</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Exists</code> by the Reader <code>reader</code>.
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
     * Gets the Variables property <b>Variables</b>.
     *
     * @return Variables
     */
    public final Variables getVariables() {
        return (variables_);
    }

    /**
     * Sets the Variables property <b>Variables</b>.
     *
     * @param variables
     */
    public final void setVariables(Variables variables) {
        this.variables_ = variables;
        if (variables != null) {
            variables.rSetParentRNode(this);
        }
    }

    /**
     * Gets the IExpressionChoice property <b>Expression</b>.
     *
     * @return IExpressionChoice
     */
    public final IExpressionChoice getExpression() {
        return (expression_);
    }

    /**
     * Sets the IExpressionChoice property <b>Expression</b>.
     *
     * @param expression
     */
    public final void setExpression(IExpressionChoice expression) {
        this.expression_ = expression;
        if (expression != null) {
            expression.rSetParentRNode(this);
        }
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
        buffer.append("<exists");
        expression_.makeTextAttribute(buffer);
        buffer.append(">");
        variables_.makeTextElement(buffer);
        expression_.makeTextElement(buffer);
        buffer.append("</exists>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<exists");
        expression_.makeTextAttribute(buffer);
        buffer.write(">");
        variables_.makeTextElement(buffer);
        expression_.makeTextElement(buffer);
        buffer.write("</exists>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<exists");
        expression_.makeTextAttribute(buffer);
        buffer.print(">");
        variables_.makeTextElement(buffer);
        expression_.makeTextElement(buffer);
        buffer.print("</exists>");
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
        if (variables_ != null) {
            classNodes.add(variables_);
        }
        if (expression_ != null) {
            classNodes.add(expression_);
        }
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>Exists</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "exists")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!Variables.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (Predicate.isMatchHungry(target)) {
            $match$ = true;
        } else if (Conditional.isMatchHungry(target)) {
            $match$ = true;
        } else if (Cardinality.isMatchHungry(target)) {
            $match$ = true;
        } else if (ForAll.isMatchHungry(target)) {
            $match$ = true;
        } else if (Exists.isMatchHungry(target)) {
            $match$ = true;
        } else if (Conjunction.isMatchHungry(target)) {
            $match$ = true;
        } else if (Disjunction.isMatchHungry(target)) {
            $match$ = true;
        } else if (Negation.isMatchHungry(target)) {
            $match$ = true;
        } else {
            return (false);
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>Exists</code>.
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
     * is valid for the <code>Exists</code>.
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
