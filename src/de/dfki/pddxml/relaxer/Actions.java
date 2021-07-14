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
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.dfki.owls2pddxml_2_0.PDDLNode;

/**
 * <b>Actions</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="actions">
 *   <zeroOrMore>
 *     <ref name="Action"/>
 *   </zeroOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="actions"&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="Action"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Actions implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
    // List<Action>
    private java.util.List action_ = new java.util.ArrayList();
    private IRNode parentRNode_;

    /**
     * Creates a <code>Actions</code>.
     *
     */
    public Actions() {
    }

    /**
     * Creates a <code>Actions</code>.
     *
     * @param source
     */
    public Actions(Actions source) {
        setup(source);
    }

    /**
     * Creates a <code>Actions</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Actions(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Actions</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Actions(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Actions</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Actions(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Actions</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Actions</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Actions</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Actions</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Actions</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Actions</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Actions(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Actions</code> by the Actions <code>source</code>.
     *
     * @param source
     */
    public void setup(Actions source) {
        int size;
        this.action_.clear();
        size = source.action_.size();
        for (int i = 0;i < size;i++) {
            addAction((Action)source.getAction(i).clone());
        }
    }

    /**
     * Initializes the <code>Actions</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Actions</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Actions</code> by the Stack <code>stack</code>
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
        action_.clear();
        while (true) {
            if (Action.isMatch(stack)) {
                addAction(new Action(stack));
            } else {
                break;
            }
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Actions(this));
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
        Element element = doc.createElement("actions");
        int size;
        size = this.action_.size();
        for (int i = 0;i < size;i++) {
            Action value = (Action)this.action_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Actions</code> by the File <code>file</code>.
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
     * Initializes the <code>Actions</code>
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
     * Initializes the <code>Actions</code> by the URL <code>url</code>.
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
     * Initializes the <code>Actions</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Actions</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Actions</code> by the Reader <code>reader</code>.
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
     * Gets the Action property <b>Action</b>.
     *
     * @return Action[]
     */
    public final Action[] getAction() {
        Action[] array = new Action[action_.size()];
        return ((Action[])action_.toArray(array));
    }

    /**
     * Sets the Action property <b>Action</b>.
     *
     * @param action
     */
    public final void setAction(Action[] action) {
        this.action_.clear();
        for (int i = 0;i < action.length;i++) {
            addAction(action[i]);
        }
        for (int i = 0;i < action.length;i++) {
            action[i].rSetParentRNode(this);
        }
    }

    /**
     * Sets the Action property <b>Action</b>.
     *
     * @param action
     */
    public final void setAction(Action action) {
        this.action_.clear();
        addAction(action);
        if (action != null) {
            action.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Action property <b>Action</b>.
     *
     * @param action
     */
    public final void addAction(Action action) {
        this.action_.add(action);
        if (action != null) {
            action.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Action property <b>Action</b>.
     *
     * @param action
     */
    public final void addAction(Action[] action) {
        for (int i = 0;i < action.length;i++) {
            addAction(action[i]);
        }
        for (int i = 0;i < action.length;i++) {
            action[i].rSetParentRNode(this);
        }
    }

    /**
     * Gets number of the Action property <b>Action</b>.
     *
     * @return int
     */
    public final int sizeAction() {
        return (action_.size());
    }

    /**
     * Gets the Action property <b>Action</b> by index.
     *
     * @param index
     * @return Action
     */
    public final Action getAction(int index) {
        return ((Action)action_.get(index));
    }

    /**
     * Sets the Action property <b>Action</b> by index.
     *
     * @param index
     * @param action
     */
    public final void setAction(int index, Action action) {
        this.action_.set(index, action);
        if (action != null) {
            action.rSetParentRNode(this);
        }
    }

    /**
     * Adds the Action property <b>Action</b> by index.
     *
     * @param index
     * @param action
     */
    public final void addAction(int index, Action action) {
        this.action_.add(index, action);
        if (action != null) {
            action.rSetParentRNode(this);
        }
    }

    /**
     * Remove the Action property <b>Action</b> by index.
     *
     * @param index
     */
    public final void removeAction(int index) {
        this.action_.remove(index);
    }

    /**
     * Remove the Action property <b>Action</b> by object.
     *
     * @param action
     */
    public final void removeAction(Action action) {
        this.action_.remove(action);
    }

    /**
     * Clear the Action property <b>Action</b>.
     *
     */
    public final void clearAction() {
        this.action_.clear();
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
        buffer.append("<actions");
        buffer.append(">");
        size = this.action_.size();
        for (int i = 0;i < size;i++) {
            Action value = (Action)this.action_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</actions>");
    }
    
    public void makePDDLTextElement(StringBuffer buffer, Map<URI, ArrayList<PDDLNode>> pddlNodes) {
        int size;
//        buffer.append("<actions");
//        buffer.append(">");
        
        
        size = this.action_.size();
        for (int i = 0;i < size;i++) {
        	
            Action value = (Action)this.action_.get(i);
            value.makePDDLTextElement(buffer, pddlNodes);
            
            buffer.append("\n \n");
        }
//        buffer.append("</actions>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<actions");
        buffer.write(">");
        size = this.action_.size();
        for (int i = 0;i < size;i++) {
            Action value = (Action)this.action_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</actions>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<actions");
        buffer.print(">");
        size = this.action_.size();
        for (int i = 0;i < size;i++) {
            Action value = (Action)this.action_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</actions>");
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
        classNodes.addAll(action_);
        IRNode[] nodes = new IRNode[classNodes.size()];
        return ((IRNode[])classNodes.toArray(nodes));
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>Actions</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "actions")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while (true) {
            if (!Action.isMatchHungry(target)) {
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
     * is valid for the <code>Actions</code>.
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
     * is valid for the <code>Actions</code>.
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
