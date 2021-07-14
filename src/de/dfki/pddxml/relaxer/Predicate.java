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
 * <b>Predicate</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="pred">
 *   <ref name="NameAttr"/>
 *   <oneOrMore>
 *     <ref name="Parameter"/>
 *   </oneOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="pred"&gt;
 *   &lt;ref name="NameAttr"/&gt;
 *   &lt;oneOrMore&gt;
 *     &lt;ref name="Parameter"/&gt;
 *   &lt;/oneOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Predicate implements java.io.Serializable, Cloneable, IRVisitable, IRNode, IExpressionChoice {
    private String name_;
    // List<Parameter>
    private java.util.List parameter_ = new java.util.ArrayList();
    private IRNode parentRNode_;

    /**
     * Creates a <code>Predicate</code>.
     *
     */
    public Predicate() {
        name_ = "";
    }

    /**
     * Creates a <code>Predicate</code>.
     *
     * @param source
     */
    public Predicate(Predicate source) {
        setup(source);
    }

    /**
     * Creates a <code>Predicate</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Predicate(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Predicate</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Predicate(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Predicate</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Predicate(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Predicate</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Predicate</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Predicate</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Predicate</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Predicate</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Predicate</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Predicate(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Predicate</code> by the Predicate <code>source</code>.
     *
     * @param source
     */
    public void setup(Predicate source) {
        int size;
        setName(source.getName());
        this.parameter_.clear();
        size = source.parameter_.size();
        for (int i = 0;i < size;i++) {
            addParameter((Parameter)source.getParameter(i).clone());
        }
    }

    /**
     * Initializes the <code>Predicate</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Predicate</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Predicate</code> by the Stack <code>stack</code>
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
        name_ = URelaxer.getAttributePropertyAsString(element, "name");
        parameter_.clear();
        while (true) {
            if (Parameter.isMatch(stack)) {
                addParameter(new Parameter(stack));
            } else {
                break;
            }
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Predicate(this));
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
        Element element = doc.createElement("pred");
        int size;
        if (this.name_ != null) {
            URelaxer.setAttributePropertyByString(element, "name", this.name_);
        }
        size = this.parameter_.size();
        for (int i = 0;i < size;i++) {
            Parameter value = (Parameter)this.parameter_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Predicate</code> by the File <code>file</code>.
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
     * Initializes the <code>Predicate</code>
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
     * Initializes the <code>Predicate</code> by the URL <code>url</code>.
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
     * Initializes the <code>Predicate</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Predicate</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Predicate</code> by the Reader <code>reader</code>.
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
     * Gets the String property <b>name</b>.
     *
     * @return String
     */
    public final String getName() {
        return (name_);
    }

    /**
     * Sets the String property <b>name</b>.
     *
     * @param name
     */
    public final void setName(String name) {
        this.name_ = name;
    }

    /**
     * Gets the Parameter property <b>Parameter</b>.
     *
     * @return Parameter[]
     */
    public final Parameter[] getParameter() {
        Parameter[] array = new Parameter[parameter_.size()];
        return ((Parameter[])parameter_.toArray(array));
    }

    /**
     * Sets the Parameter property <b>Parameter</b>.
     *
     * @param parameter
     */
    public final void setParameter(Parameter[] parameter) {
        this.parameter_.clear();
        for (int i = 0;i < parameter.length;i++) {
            addParameter(parameter[i]);
        }
        for (int i = 0;i < parameter.length;i++) {
            parameter[i].rSetParentRNode(this);
        }
    }

    /**
     * Sets the Parameter property <b>Parameter</b>.
     *
     * @param parameter
     */
    public final void setParameter(Parameter parameter) {
        this.parameter_.clear();
        addParameter(parameter);
        if (parameter != null) {
            parameter.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Parameter property <b>Parameter</b>.
     *
     * @param parameter
     */
    public final void addParameter(Parameter parameter) {
        this.parameter_.add(parameter);
        if (parameter != null) {
            parameter.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Parameter property <b>Parameter</b>.
     *
     * @param parameter
     */
    public final void addParameter(Parameter[] parameter) {
        for (int i = 0;i < parameter.length;i++) {
            addParameter(parameter[i]);
        }
        for (int i = 0;i < parameter.length;i++) {
            parameter[i].rSetParentRNode(this);
        }
    }

    /**
     * Gets number of the Parameter property <b>Parameter</b>.
     *
     * @return int
     */
    public final int sizeParameter() {
        return (parameter_.size());
    }

    /**
     * Gets the Parameter property <b>Parameter</b> by index.
     *
     * @param index
     * @return Parameter
     */
    public final Parameter getParameter(int index) {
        return ((Parameter)parameter_.get(index));
    }

    /**
     * Sets the Parameter property <b>Parameter</b> by index.
     *
     * @param index
     * @param parameter
     */
    public final void setParameter(int index, Parameter parameter) {
        this.parameter_.set(index, parameter);
        if (parameter != null) {
            parameter.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Parameter property <b>Parameter</b> by index.
     *
     * @param index
     * @param parameter
     */
    public final void addParameter(int index, Parameter parameter) {
        this.parameter_.add(index, parameter);
        if (parameter != null) {
            parameter.rSetParentRNode(this);
        }
    }

    /**
     * Remove the Parameter property <b>Parameter</b> by index.
     *
     * @param index
     */
    public final void removeParameter(int index) {
        this.parameter_.remove(index);
    }

    /**
     * Remove the Parameter property <b>Parameter</b> by object.
     *
     * @param parameter
     */
    public final void removeParameter(Parameter parameter) {
        this.parameter_.remove(parameter);
    }

    /**
     * Clear the Parameter property <b>Parameter</b>.
     *
     */
    public final void clearParameter() {
        this.parameter_.clear();
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
        buffer.append("<pred");
        if (name_ != null) {
            buffer.append(" name=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.append("\"");
        }
        buffer.append(">");
        size = this.parameter_.size();
        for (int i = 0;i < size;i++) {
            Parameter value = (Parameter)this.parameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</pred>");
    }
    
    public void makePDDLTextElement(StringBuffer buffer) {
    	
        int size;
//        buffer.append("<pred");
        buffer.append("(");
        if (name_ != null) {
//            buffer.append(" name=\"");
        	
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
//            buffer.append("\"");
        }
        size = this.parameter_.size();
        for (int i = 0;i < size;i++) {
            Parameter value = (Parameter)this.parameter_.get(i);
            value.makePDDLTextElement(buffer);
        }
        
        
//        buffer.append(">");
        

//        buffer.append("</pred>");
        buffer.append(")");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<pred");
        if (name_ != null) {
            buffer.write(" name=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.write("\"");
        }
        buffer.write(">");
        size = this.parameter_.size();
        for (int i = 0;i < size;i++) {
            Parameter value = (Parameter)this.parameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</pred>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<pred");
        if (name_ != null) {
            buffer.print(" name=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
            buffer.print("\"");
        }
        buffer.print(">");
        size = this.parameter_.size();
        for (int i = 0;i < size;i++) {
            Parameter value = (Parameter)this.parameter_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</pred>");
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
     * Gets the property value as String.
     *
     * @return String
     */
    public String getNameAsString() {
        return (URelaxer.getString(getName()));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setNameByString(String string) {
        setName(string);
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
        classNodes.addAll(parameter_);
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>Predicate</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "pred")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        if (!URelaxer.hasAttributeHungry(target, "name")) {
            return (false);
        }
        $match$ = true;
        if (!Parameter.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        while (true) {
            if (!Parameter.isMatchHungry(target)) {
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
     * is valid for the <code>Predicate</code>.
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
     * is valid for the <code>Predicate</code>.
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
