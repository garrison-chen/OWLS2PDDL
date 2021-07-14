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
 * <b>PDDXMLObjects</b> is generated from PDDXMLProblem.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="objects">
 *   <zeroOrMore>
 *     <ref name="PDDXMLObject"/>
 *   </zeroOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="objects"&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="PDDXMLObject"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLProblem.rng (Wed Jan 03 14:40:06 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class PDDXMLObjects implements java.io.Serializable, Cloneable {
    // List<PDDXMLObject>
    private java.util.List pDDXMLObject_ = new java.util.ArrayList();

    /**
     * Creates a <code>PDDXMLObjects</code>.
     *
     */
    public PDDXMLObjects() {
    }

    /**
     * Creates a <code>PDDXMLObjects</code>.
     *
     * @param source
     */
    public PDDXMLObjects(PDDXMLObjects source) {
        setup(source);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public PDDXMLObjects(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public PDDXMLObjects(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public PDDXMLObjects(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(File file) throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>PDDXMLObjects</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(String uri) throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(URL url) throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(InputSource is) throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>PDDXMLObjects</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public PDDXMLObjects(Reader reader) throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Initializes the <code>PDDXMLObjects</code> by the PDDXMLObjects <code>source</code>.
     *
     * @param source
     */
    public void setup(PDDXMLObjects source) {
        int size;
        this.pDDXMLObject_.clear();
        size = source.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            addPDDXMLObject((PDDXMLObject)source.getPDDXMLObject(i).clone());
        }
    }

    /**
     * Initializes the <code>PDDXMLObjects</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>PDDXMLObjects</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>PDDXMLObjects</code> by the Stack <code>stack</code>
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
        pDDXMLObject_.clear();
        while (true) {
            if (PDDXMLObject.isMatch(stack)) {
                addPDDXMLObject(new PDDXMLObject(stack));
            } else {
                break;
            }
        }
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new PDDXMLObjects(this));
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
        Element element = doc.createElement("objects");
        int size;
        size = this.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            PDDXMLObject value = (PDDXMLObject)this.pDDXMLObject_.get(i);
            value.makeElement(element);
        }
        parent.appendChild(element);
    }

    /**
     * Initializes the <code>PDDXMLObjects</code> by the File <code>file</code>.
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
     * Initializes the <code>PDDXMLObjects</code>
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
     * Initializes the <code>PDDXMLObjects</code> by the URL <code>url</code>.
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
     * Initializes the <code>PDDXMLObjects</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>PDDXMLObjects</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>PDDXMLObjects</code> by the Reader <code>reader</code>.
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
     * Gets the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @return PDDXMLObject[]
     */
    public final PDDXMLObject[] getPDDXMLObject() {
        PDDXMLObject[] array = new PDDXMLObject[pDDXMLObject_.size()];
        return ((PDDXMLObject[])pDDXMLObject_.toArray(array));
    }

    /**
     * Sets the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @param pDDXMLObject
     */
    public final void setPDDXMLObject(PDDXMLObject[] pDDXMLObject) {
        this.pDDXMLObject_.clear();
        for (int i = 0;i < pDDXMLObject.length;i++) {
            addPDDXMLObject(pDDXMLObject[i]);
        }
    }

    /**
     * Sets the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @param pDDXMLObject
     */
    public final void setPDDXMLObject(PDDXMLObject pDDXMLObject) {
        this.pDDXMLObject_.clear();
        addPDDXMLObject(pDDXMLObject);
    }

    /**
     * Adds the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @param pDDXMLObject
     */
    public final void addPDDXMLObject(PDDXMLObject pDDXMLObject) {
        this.pDDXMLObject_.add(pDDXMLObject);
    }

    /**
     * Adds the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @param pDDXMLObject
     */
    public final void addPDDXMLObject(PDDXMLObject[] pDDXMLObject) {
        for (int i = 0;i < pDDXMLObject.length;i++) {
            addPDDXMLObject(pDDXMLObject[i]);
        }
    }

    /**
     * Gets number of the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     * @return int
     */
    public final int sizePDDXMLObject() {
        return (pDDXMLObject_.size());
    }

    /**
     * Gets the PDDXMLObject property <b>PDDXMLObject</b> by index.
     *
     * @param index
     * @return PDDXMLObject
     */
    public final PDDXMLObject getPDDXMLObject(int index) {
        return ((PDDXMLObject)pDDXMLObject_.get(index));
    }

    /**
     * Sets the PDDXMLObject property <b>PDDXMLObject</b> by index.
     *
     * @param index
     * @param pDDXMLObject
     */
    public final void setPDDXMLObject(int index, PDDXMLObject pDDXMLObject) {
        this.pDDXMLObject_.set(index, pDDXMLObject);
    }

    /**
     * Adds the PDDXMLObject property <b>PDDXMLObject</b> by index.
     *
     * @param index
     * @param pDDXMLObject
     */
    public final void addPDDXMLObject(int index, PDDXMLObject pDDXMLObject) {
        this.pDDXMLObject_.add(index, pDDXMLObject);
    }

    /**
     * Remove the PDDXMLObject property <b>PDDXMLObject</b> by index.
     *
     * @param index
     */
    public final void removePDDXMLObject(int index) {
        this.pDDXMLObject_.remove(index);
    }

    /**
     * Remove the PDDXMLObject property <b>PDDXMLObject</b> by object.
     *
     * @param pDDXMLObject
     */
    public final void removePDDXMLObject(PDDXMLObject pDDXMLObject) {
        this.pDDXMLObject_.remove(pDDXMLObject);
    }

    /**
     * Clear the PDDXMLObject property <b>PDDXMLObject</b>.
     *
     */
    public final void clearPDDXMLObject() {
        this.pDDXMLObject_.clear();
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
        buffer.append("<objects");
        buffer.append(">");
        size = this.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            PDDXMLObject value = (PDDXMLObject)this.pDDXMLObject_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</objects>");
    }
    
    public void makePDDLTextElement(StringBuffer buffer) {
    	
        int size;
        buffer.append("(:objects \n");
        size = this.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            PDDXMLObject value = (PDDXMLObject)this.pDDXMLObject_.get(i);
            value.makePDDLTextElement(buffer);
            buffer.append("\n");
        }
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
        buffer.write("<objects");
        buffer.write(">");
        size = this.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            PDDXMLObject value = (PDDXMLObject)this.pDDXMLObject_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</objects>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<objects");
        buffer.print(">");
        size = this.pDDXMLObject_.size();
        for (int i = 0;i < size;i++) {
            PDDXMLObject value = (PDDXMLObject)this.pDDXMLObject_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</objects>");
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
     * Tests if a Element <code>element</code> is valid
     * for the <code>PDDXMLObjects</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "objects")) {
            return (false);
        }
        RStack target = new RStack(element);
        boolean $match$ = false;
        Element child;
        while (true) {
            if (!PDDXMLObject.isMatchHungry(target)) {
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
     * is valid for the <code>PDDXMLObjects</code>.
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
     * is valid for the <code>PDDXMLObjects</code>.
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
