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
 * <b>InitState</b> is generated from PDDXMLProblem.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="init">
 *   <ref name="Expression"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="init"&gt;
 *   &lt;ref name="Expression"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLProblem.rng (Wed Jan 03 14:40:06 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class InitState implements java.io.Serializable, Cloneable {
	private IExpressionChoice expression_;

	/**
	 * Creates a <code>InitState</code>.
	 *
	 */
	public InitState() {
	}

	/**
	 * Creates a <code>InitState</code>.
	 *
	 * @param source
	 */
	public InitState(InitState source) {
		setup(source);
	}

	/**
	 * Creates a <code>InitState</code> by the Stack <code>stack</code>
	 * that contains Elements.
	 * This constructor is supposed to be used internally
	 * by the Relaxer system.
	 *
	 * @param stack
	 */
	public InitState(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>InitState</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public InitState(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>InitState</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public InitState(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>InitState</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>InitState</code>
	 * by the String representation of URI <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>InitState</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>InitState</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>InitState</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>InitState</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public InitState(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>InitState</code> by the InitState <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(InitState source) {
		int size;
		if (source.expression_ != null) {
			setExpression((IExpressionChoice)source.getExpression().clone());
		}
	}

	/**
	 * Initializes the <code>InitState</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>InitState</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>InitState</code> by the Stack <code>stack</code>
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
		if (Conditional.isMatch(stack)) {
			setExpression(new Conditional(stack));
		} else if (Predicate.isMatch(stack)) {
			setExpression(new Predicate(stack));
		} else if (Cardinality.isMatch(stack)) {
			setExpression(new Cardinality(stack));
		} else if (Conjunction.isMatch(stack)) {
			setExpression(new Conjunction(stack));
		} else if (Disjunction.isMatch(stack)) {
			setExpression(new Disjunction(stack));
		} else if (ForAll.isMatch(stack)) {
			setExpression(new ForAll(stack));
		} else if (Exists.isMatch(stack)) {
			setExpression(new Exists(stack));
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
		return (new InitState(this));
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
		Element element = doc.createElement("init");
		int size;
		this.expression_.makeElement(element);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>InitState</code> by the File <code>file</code>.
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
	 * Initializes the <code>InitState</code>
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
	 * Initializes the <code>InitState</code> by the URL <code>url</code>.
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
	 * Initializes the <code>InitState</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>InitState</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>InitState</code> by the Reader <code>reader</code>.
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
		buffer.append("<init");
		expression_.makeTextAttribute(buffer);
		buffer.append(">");
		expression_.makeTextElement(buffer);
		buffer.append("</init>");

	}

	public void makePDDLTextElement(StringBuffer buffer) {


		buffer.append("(:init ");
		
		StringBuffer temporaryBuffer = new StringBuffer();
        expression_.makeTextElement(temporaryBuffer);
        
        XmlToPddlParser.appendConvertedPDDL(temporaryBuffer.toString(), buffer, true);
//		expression_.makeTextAttribute(buffer);
		//        buffer.append(">");
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
		buffer.write("<init");
		expression_.makeTextAttribute(buffer);
		buffer.write(">");
		expression_.makeTextElement(buffer);
		buffer.write("</init>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<init");
		expression_.makeTextAttribute(buffer);
		buffer.print(">");
		expression_.makeTextElement(buffer);
		buffer.print("</init>");
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
	 * for the <code>InitState</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "init")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		if (Conditional.isMatchHungry(target)) {
			$match$ = true;
		} else if (Predicate.isMatchHungry(target)) {
			$match$ = true;
		} else if (Cardinality.isMatchHungry(target)) {
			$match$ = true;
		} else if (Conjunction.isMatchHungry(target)) {
			$match$ = true;
		} else if (Disjunction.isMatchHungry(target)) {
			$match$ = true;
		} else if (ForAll.isMatchHungry(target)) {
			$match$ = true;
		} else if (Exists.isMatchHungry(target)) {
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
	 * is valid for the <code>InitState</code>.
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
	 * is valid for the <code>InitState</code>.
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
