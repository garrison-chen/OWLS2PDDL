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
 * <b>Requirements</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="requirements">
 *   <zeroOrMore>
 *     <ref name="Req"/>
 *   </zeroOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="requirements"&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="Req"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Requirements implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
    // List<String>
    private java.util.List req_ = new java.util.ArrayList();
    private IRNode parentRNode_;

    /**
     * Creates a <code>Requirements</code>.
     *
     */
    public Requirements() {
    }

    /**
     * Creates a <code>Requirements</code>.
     *
     * @param source
     */
    public Requirements(Requirements source) {
        setup(source);
    }

    /**
     * Creates a <code>Requirements</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public Requirements(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>Requirements</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public Requirements(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>Requirements</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public Requirements(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>Requirements</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>Requirements</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>Requirements</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>Requirements</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>Requirements</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>Requirements</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public Requirements(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>Requirements</code> by the Requirements <code>source</code>.
     *
     * @param source
     */
    public void setup(Requirements source) {
        int size;
        setReq(source.getReq());
    }

    /**
     * Initializes the <code>Requirements</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>Requirements</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>Requirements</code> by the Stack <code>stack</code>
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
        req_ = URelaxer.getElementPropertyAsStringListByStack(stack, "req");
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new Requirements(this));
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
        Element element = doc.createElement("requirements");
        int size;
        URelaxer.setElementPropertyByStringList(element, "req", this.req_);
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>Requirements</code> by the File <code>file</code>.
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
     * Initializes the <code>Requirements</code>
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
     * Initializes the <code>Requirements</code> by the URL <code>url</code>.
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
     * Initializes the <code>Requirements</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>Requirements</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>Requirements</code> by the Reader <code>reader</code>.
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
     * Gets the String property <b>req</b>.
     *
     * @return String[]
     */
    public final String[] getReq() {
        String[] array = new String[req_.size()];
        return ((String[])req_.toArray(array));
    }

    /**
     * Sets the String property <b>req</b>.
     *
     * @param req
     */
    public final void setReq(String[] req) {
        this.req_.clear();
        for (int i = 0;i < req.length;i++) {
            addReq(req[i]);
        }
    }

    /**
     * Sets the String property <b>req</b>.
     *
     * @param req
     */
    public final void setReq(String req) {
        this.req_.clear();
        addReq(req);
    }

    /**
     * Adds the String property <b>req</b>.
     *
     * @param req
     */
    public final void addReq(String req) {
        this.req_.add(req);
    }

    /**
     * Adds the String property <b>req</b>.
     *
     * @param req
     */
    public final void addReq(String[] req) {
        for (int i = 0;i < req.length;i++) {
            addReq(req[i]);
        }
    }

    /**
     * Gets number of the String property <b>req</b>.
     *
     * @return int
     */
    public final int sizeReq() {
        return (req_.size());
    }

    /**
     * Gets the String property <b>req</b> by index.
     *
     * @param index
     * @return String
     */
    public final String getReq(int index) {
        return ((String)req_.get(index));
    }

    /**
     * Sets the String property <b>req</b> by index.
     *
     * @param index
     * @param req
     */
    public final void setReq(int index, String req) {
        this.req_.set(index, req);
    }

    /**
     * Adds the String property <b>req</b> by index.
     *
     * @param index
     * @param req
     */
    public final void addReq(int index, String req) {
        this.req_.add(index, req);
    }

    /**
     * Remove the String property <b>req</b> by index.
     *
     * @param index
     */
    public final void removeReq(int index) {
        this.req_.remove(index);
    }

    /**
     * Remove the String property <b>req</b> by object.
     *
     * @param req
     */
    public final void removeReq(String req) {
        this.req_.remove(req);
    }

    /**
     * Clear the String property <b>req</b>.
     *
     */
    public final void clearReq() {
        this.req_.clear();
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
        buffer.append("<requirements");
        buffer.append(">");
        size = sizeReq();
        for (int i = 0;i < size;i++) {
            buffer.append("<req>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getReq(i))));
            buffer.append("</req>");
        }
        buffer.append("</requirements>");
    }
    
    public void makePDDLTextElement(StringBuffer buffer) {
    	
    	int size;
        
        size = sizeReq();
        
        if (size == 0)
        	return;
        
        buffer.append("(:requirements");
        for (int i = 0;i < size;i++) {
//            buffer.append("<req>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getReq(i))));
            buffer.append(" ");
        }
        buffer.append(") \n");
        
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer) throws IOException {
        int size;
        buffer.write("<requirements");
        buffer.write(">");
        size = sizeReq();
        for (int i = 0;i < size;i++) {
            buffer.write("<req>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getReq(i))));
            buffer.write("</req>");
        }
        buffer.write("</requirements>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<requirements");
        buffer.print(">");
        size = sizeReq();
        for (int i = 0;i < size;i++) {
            buffer.print("<req>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getReq(i))));
            buffer.print("</req>");
        }
        buffer.print("</requirements>");
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
     * Gets the property value as String array.
     *
     * @return String[]
     */
    public String[] getReqAsString() {
        int size = sizeReq();
        String[] array = new String[size];
        for (int i = 0;i < size;i++) {
            array[i] = URelaxer.getString(getReq(i));
        }
        return (array);
    }

    /**
     * Gets the property value by index as String.
     *
     * @param index
     * @return String
     */
    public String getReqAsString(int index) {
        return (URelaxer.getString(getReq(index)));
    }

    /**
     * Sets the property value by String array.
     *
     * @param strings
     */
    public void setReqByString(String[] strings) {
        if (strings.length > 0) {
            String string = strings[0];
            setReq(string);
            for (int i = 1;i < strings.length;i++) {
                string = strings[i];
                addReq(string);
            }
        }
    }

    /**
     * Sets the property value by String via index.
     *
     * @param index
     * @param value
     */
    public void setReqByString(int index, String value) {
        setReq(index, value);
    }

    /**
     * Adds the property value by String.
     *
     * @param string
     */
    public void addReqByString(String string) {
        addReq(string);
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
     * Tests if a Element <code>element</code> is valid
     * for the <code>Requirements</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "requirements")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while ((child = target.peekElement()) != null) {
            if (!URelaxer.isTargetElement(child, "req")) {
                break;
            }
            target.popElement();
            $match$ = true;
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>Requirements</code>.
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
     * is valid for the <code>Requirements</code>.
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
