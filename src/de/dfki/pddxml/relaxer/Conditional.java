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
 * <b>Conditional</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="if">
 *   <ref name="Expression"/>
 *   <ref name="Expression"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="if"&gt;
 *   &lt;ref name="Expression"/&gt;
 *   &lt;ref name="Expression"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Conditional implements java.io.Serializable, Cloneable, IRVisitable, IRNode, IExpressionChoice {
    private IExpressionChoice expression1_;
    private IExpressionChoice expression2_;
    private IRNode parentRNode_;

    /**
     * Creates a <code>Conditional</code>.
     *
     */
    public Conditional() {
    }

    /**
     * Creates a <code>Conditional</code>.
     *
     * @param source
     */
    public Conditional(Conditional source) {
        setup(source);
    }

    /**
     * Creates a <code>Conditional</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Conditional(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Conditional</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Conditional(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Conditional</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Conditional(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Conditional</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Conditional</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Conditional</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Conditional</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Conditional</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Conditional</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Conditional(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Conditional</code> by the Conditional <code>source</code>.
     *
     * @param source
     */
    public void setup(Conditional source) {
        int size;
        if (source.expression1_ != null) {
            setExpression1((IExpressionChoice)source.getExpression1().clone());
        }
        if (source.expression2_ != null) {
            setExpression2((IExpressionChoice)source.getExpression2().clone());
        }
    }

    /**
     * Initializes the <code>Conditional</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Conditional</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Conditional</code> by the Stack <code>stack</code>
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
        if (Predicate.isMatch(stack)) {
            setExpression1(new Predicate(stack));
        } else if (Cardinality.isMatch(stack)) {
            setExpression1(new Cardinality(stack));
        } else if (ForAll.isMatch(stack)) {
            setExpression1(new ForAll(stack));
        } else if (Exists.isMatch(stack)) {
            setExpression1(new Exists(stack));
        } else if (Conjunction.isMatch(stack)) {
            setExpression1(new Conjunction(stack));
        } else if (Disjunction.isMatch(stack)) {
            setExpression1(new Disjunction(stack));
        } else if (Negation.isMatch(stack)) {
            setExpression1(new Negation(stack));
        } else if (Conditional.isMatch(stack)) {
            setExpression1(new Conditional(stack));
        } else {
            throw (new IllegalArgumentException());
        }
        if (Predicate.isMatch(stack)) {
            setExpression2(new Predicate(stack));
        } else if (Cardinality.isMatch(stack)) {
            setExpression2(new Cardinality(stack));
        } else if (ForAll.isMatch(stack)) {
            setExpression2(new ForAll(stack));
        } else if (Exists.isMatch(stack)) {
            setExpression2(new Exists(stack));
        } else if (Conditional.isMatch(stack)) {
            setExpression2(new Conditional(stack));
        } else if (Conjunction.isMatch(stack)) {
            setExpression2(new Conjunction(stack));
        } else if (Disjunction.isMatch(stack)) {
            setExpression2(new Disjunction(stack));
        } else if (Negation.isMatch(stack)) {
            setExpression2(new Negation(stack));
        } else {
            throw (new IllegalArgumentException());
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Conditional(this));
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
        Element element = doc.createElement("if");
        int size;
        this.expression1_.makeElement(element);
        this.expression2_.makeElement(element);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Conditional</code> by the File <code>file</code>.
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
     * Initializes the <code>Conditional</code>
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
     * Initializes the <code>Conditional</code> by the URL <code>url</code>.
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
     * Initializes the <code>Conditional</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Conditional</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Conditional</code> by the Reader <code>reader</code>.
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
     * Gets the IExpressionChoice property <b>Expression1</b>.
     *
     * @return IExpressionChoice
     */
    public final IExpressionChoice getExpression1() {
        return (expression1_);
    }

    /**
     * Sets the IExpressionChoice property <b>Expression1</b>.
     *
     * @param expression1
     */
    public final void setExpression1(IExpressionChoice expression1) {
        this.expression1_ = expression1;
        if (expression1 != null) {
            expression1.rSetParentRNode(this);
        }
    }

    /**
     * Gets the IExpressionChoice property <b>Expression2</b>.
     *
     * @return IExpressionChoice
     */
    public final IExpressionChoice getExpression2() {
        return (expression2_);
    }

    /**
     * Sets the IExpressionChoice property <b>Expression2</b>.
     *
     * @param expression2
     */
    public final void setExpression2(IExpressionChoice expression2) {
        this.expression2_ = expression2;
        if (expression2 != null) {
            expression2.rSetParentRNode(this);
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
        buffer.append("<if");
        expression1_.makeTextAttribute(buffer);
        expression2_.makeTextAttribute(buffer);
        buffer.append(">");
        expression1_.makeTextElement(buffer);
        expression2_.makeTextElement(buffer);
        buffer.append("</if>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<if");
        expression1_.makeTextAttribute(buffer);
        expression2_.makeTextAttribute(buffer);
        buffer.write(">");
        expression1_.makeTextElement(buffer);
        expression2_.makeTextElement(buffer);
        buffer.write("</if>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<if");
        expression1_.makeTextAttribute(buffer);
        expression2_.makeTextAttribute(buffer);
        buffer.print(">");
        expression1_.makeTextElement(buffer);
        expression2_.makeTextElement(buffer);
        buffer.print("</if>");
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
        if (expression1_ != null) {
            classNodes.add(expression1_);
        }
        if (expression2_ != null) {
            classNodes.add(expression2_);
        }
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>Conditional</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "if")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (Predicate.isMatchHungry(target)) {
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
        } else if (Conditional.isMatchHungry(target)) {
            $match$ = true;
        } else {
            return (false);
        }
        if (Predicate.isMatchHungry(target)) {
            $match$ = true;
        } else if (Cardinality.isMatchHungry(target)) {
            $match$ = true;
        } else if (ForAll.isMatchHungry(target)) {
            $match$ = true;
        } else if (Exists.isMatchHungry(target)) {
            $match$ = true;
        } else if (Conditional.isMatchHungry(target)) {
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
     * is valid for the <code>Conditional</code>.
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
     * is valid for the <code>Conditional</code>.
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
