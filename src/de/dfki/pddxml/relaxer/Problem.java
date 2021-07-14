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
 * <b>Problem</b> is generated from PDDXMLProblem.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="define_problem">
 *   <ref name="DomainRef"/>
 *   <ref name="PDDXMLObjects"/>
 *   <ref name="InitState"/>
 *   <ref name="GoalState"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="define_problem"&gt;
 *   &lt;ref name="DomainRef"/&gt;
 *   &lt;ref name="PDDXMLObjects"/&gt;
 *   &lt;ref name="InitState"/&gt;
 *   &lt;ref name="GoalState"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLProblem.rng (Wed Jan 03 14:40:06 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Problem implements java.io.Serializable, Cloneable {
    private String domain_;
    private PDDXMLObjects pDDXMLObjects_;
    private InitState initState_;
    private GoalState goalState_;

    /**
     * Creates a <code>Problem</code>.
     *
     */
    public Problem() {
        domain_ = "";
    }

    /**
     * Creates a <code>Problem</code>.
     *
     * @param source
     */
    public Problem(Problem source) {
        setup(source);
    }

    /**
     * Creates a <code>Problem</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Problem(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Problem</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Problem(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Problem</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Problem(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Problem</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Problem</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Problem</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Problem</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Problem</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Problem</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Problem(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Problem</code> by the Problem <code>source</code>.
     *
     * @param source
     */
    public void setup(Problem source) {
        int size;
        setDomain(source.getDomain());
        if (source.pDDXMLObjects_ != null) {
            setPDDXMLObjects((PDDXMLObjects)source.getPDDXMLObjects().clone());
        }
        if (source.initState_ != null) {
            setInitState((InitState)source.getInitState().clone());
        }
        if (source.goalState_ != null) {
            setGoalState((GoalState)source.getGoalState().clone());
        }
    }

    /**
     * Initializes the <code>Problem</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Problem</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Problem</code> by the Stack <code>stack</code>
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
        domain_ = URelaxer.getElementPropertyAsString(stack.popElement());
        setPDDXMLObjects(new PDDXMLObjects(stack));
        setInitState(new InitState(stack));
        setGoalState(new GoalState(stack));
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Problem(this));
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
        Element element = doc.createElement("define_problem");
        int size;
        URelaxer.setElementPropertyByString(element, "domain", this.domain_);
        this.pDDXMLObjects_.makeElement(element);
        this.initState_.makeElement(element);
        this.goalState_.makeElement(element);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Problem</code> by the File <code>file</code>.
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
     * Initializes the <code>Problem</code>
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
     * Initializes the <code>Problem</code> by the URL <code>url</code>.
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
     * Initializes the <code>Problem</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Problem</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Problem</code> by the Reader <code>reader</code>.
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
     * Gets the String property <b>domain</b>.
     *
     * @return String
     */
    public final String getDomain() {
        return (domain_);
    }

    /**
     * Sets the String property <b>domain</b>.
     *
     * @param domain
     */
    public final void setDomain(String domain) {
        this.domain_ = domain;
    }

    /**
     * Gets the PDDXMLObjects property <b>PDDXMLObjects</b>.
     *
     * @return PDDXMLObjects
     */
    public final PDDXMLObjects getPDDXMLObjects() {
        return (pDDXMLObjects_);
    }

    /**
     * Sets the PDDXMLObjects property <b>PDDXMLObjects</b>.
     *
     * @param pDDXMLObjects
     */
    public final void setPDDXMLObjects(PDDXMLObjects pDDXMLObjects) {
        this.pDDXMLObjects_ = pDDXMLObjects;
    }

    /**
     * Gets the InitState property <b>InitState</b>.
     *
     * @return InitState
     */
    public final InitState getInitState() {
        return (initState_);
    }

    /**
     * Sets the InitState property <b>InitState</b>.
     *
     * @param initState
     */
    public final void setInitState(InitState initState) {
        this.initState_ = initState;
    }

    /**
     * Gets the GoalState property <b>GoalState</b>.
     *
     * @return GoalState
     */
    public final GoalState getGoalState() {
        return (goalState_);
    }

    /**
     * Sets the GoalState property <b>GoalState</b>.
     *
     * @param goalState
     */
    public final void setGoalState(GoalState goalState) {
        this.goalState_ = goalState;
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
    
    public String makePDDLTextDocument(boolean excludeHTTPFormatting) {
    	
        StringBuffer buffer = new StringBuffer();
        makePDDLTextElement(buffer);
        
//        if (pddlNodes.size() > 0)
//			PddlNodeInserter.insert(buffer, pddlNodes);
        
        if (excludeHTTPFormatting) {
        String done = new String(buffer);
        String actuallyDone = done.replaceAll("(http:\\S+.owl#)", "");

		return actuallyDone;
        }
        
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuffer buffer) {
        int size;
        buffer.append("<define_problem");
        buffer.append(">");
        buffer.append("<domain>");
        buffer.append(URelaxer.escapeCharData(URelaxer.getString(getDomain())));
        buffer.append("</domain>");
        pDDXMLObjects_.makeTextElement(buffer);
        initState_.makeTextElement(buffer);
        goalState_.makeTextElement(buffer);
        buffer.append("</define_problem>");
    }
    
    public void makePDDLTextElement(StringBuffer buffer) {
      
    	
        buffer.append("(define (problem PROBLEM_NAME) \n");
        buffer.append("(:domain ");
        buffer.append(URelaxer.escapeCharData(URelaxer.getString(getDomain())));
        buffer.append(")");
        
        pDDXMLObjects_.makePDDLTextElement(buffer);
        
        buffer.append("\n");
        
        initState_.makePDDLTextElement(buffer);
        
        buffer.append("\n");
        
        goalState_.makePDDLTextElement(buffer);
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
        buffer.write("<define_problem");
        buffer.write(">");
        buffer.write("<domain>");
        buffer.write(URelaxer.escapeCharData(URelaxer.getString(getDomain())));
        buffer.write("</domain>");
        pDDXMLObjects_.makeTextElement(buffer);
        initState_.makeTextElement(buffer);
        goalState_.makeTextElement(buffer);
        buffer.write("</define_problem>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<define_problem");
        buffer.print(">");
        buffer.print("<domain>");
        buffer.print(URelaxer.escapeCharData(URelaxer.getString(getDomain())));
        buffer.print("</domain>");
        pDDXMLObjects_.makeTextElement(buffer);
        initState_.makeTextElement(buffer);
        goalState_.makeTextElement(buffer);
        buffer.print("</define_problem>");
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
    public String getDomainAsString() {
        return (URelaxer.getString(getDomain()));
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setDomainByString(String string) {
        setDomain(string);
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
     * Tests if a Element <code>element</code> is valid
     * for the <code>Problem</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "define_problem")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        child = target.popElement();
        if (child == null) {
            return (false);
        }
        if (!URelaxer.isTargetElement(child, "domain")) {
            return (false);
        }
        $match$ = true;
        if (!PDDXMLObjects.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (!InitState.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (!GoalState.isMatchHungry(target)) {
            return (false);
        }
        $match$ = true;
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>Problem</code>.
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
     * is valid for the <code>Problem</code>.
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
