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
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.dfki.owls2pddxml_2_0.PDDLNode;

/**
 * <b>Predicates</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="predicates">
 *   <zeroOrMore>
 *     <ref name="Predicate"/>
 *   </zeroOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="predicates"&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="Predicate"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Predicates implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
	// List<Predicate>
	private java.util.List predicate_ = new java.util.ArrayList();
	private IRNode parentRNode_;

	/**
	 * Creates a <code>Predicates</code>.
	 *
	 */
	public Predicates() {
	}

	/**
	 * Creates a <code>Predicates</code>.
	 *
	 * @param source
	 */
	public Predicates(Predicates source) {
		setup(source);
	}

	/**
	 * Creates a <code>Predicates</code> by the Stack <code>stack</code>
	 * that contains Elements.
	 * This constructor is supposed to be used internally
	 * by the Relaxer system.
	 *
	 * @param stack
	 */
	public Predicates(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Predicates</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Predicates(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Predicates</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Predicates(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Predicates</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Predicates</code>
	 * by the String representation of URI <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Predicates</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Predicates</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Predicates</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Predicates</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Predicates(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Predicates</code> by the Predicates <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Predicates source) {
		int size;
		this.predicate_.clear();
		size = source.predicate_.size();
		for (int i = 0;i < size;i++) {
			addPredicate((Predicate)source.getPredicate(i).clone());
		}
	}

	/**
	 * Initializes the <code>Predicates</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Predicates</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Predicates</code> by the Stack <code>stack</code>
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
		predicate_.clear();
		while (true) {
			if (Predicate.isMatch(stack)) {
				addPredicate(new Predicate(stack));
			} else {
				break;
			}
		}
	}

	/**
	 * @return Object
	 */
	public Object clone() {
		return (new Predicates(this));
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
		Element element = doc.createElement("predicates");
		int size;
		size = this.predicate_.size();
		for (int i = 0;i < size;i++) {
			Predicate value = (Predicate)this.predicate_.get(i);
			value.makeElement(element);
		}
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Predicates</code> by the File <code>file</code>.
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
	 * Initializes the <code>Predicates</code>
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
	 * Initializes the <code>Predicates</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Predicates</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Predicates</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Predicates</code> by the Reader <code>reader</code>.
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
	 * Gets the Predicate property <b>Predicate</b>.
	 *
	 * @return Predicate[]
	 */
	public final Predicate[] getPredicate() {
		Predicate[] array = new Predicate[predicate_.size()];
		return ((Predicate[])predicate_.toArray(array));
	}

	/**
	 * Sets the Predicate property <b>Predicate</b>.
	 *
	 * @param predicate
	 */
	public final void setPredicate(Predicate[] predicate) {
		this.predicate_.clear();
		for (int i = 0;i < predicate.length;i++) {
			addPredicate(predicate[i]);
		}
		for (int i = 0;i < predicate.length;i++) {
			predicate[i].rSetParentRNode(this);
		}
	}

	/**
	 * Sets the Predicate property <b>Predicate</b>.
	 *
	 * @param predicate
	 */
	public final void setPredicate(Predicate predicate) {
		this.predicate_.clear();
		addPredicate(predicate);
		if (predicate != null) {
			predicate.rSetParentRNode(this);
		}
	}

	/**
	 * Adds the Predicate property <b>Predicate</b>.
	 *
	 * @param predicate
	 */
	public final void addPredicate(Predicate predicate) {
		this.predicate_.add(predicate);
		if (predicate != null) {
			predicate.rSetParentRNode(this);
		}
	}

	/**
	 * Adds the Predicate property <b>Predicate</b>.
	 *
	 * @param predicate
	 */
	public final void addPredicate(Predicate[] predicate) {
		for (int i = 0;i < predicate.length;i++) {
			addPredicate(predicate[i]);
		}
		for (int i = 0;i < predicate.length;i++) {
			predicate[i].rSetParentRNode(this);
		}
	}

	/**
	 * Gets number of the Predicate property <b>Predicate</b>.
	 *
	 * @return int
	 */
	public final int sizePredicate() {
		return (predicate_.size());
	}

	/**
	 * Gets the Predicate property <b>Predicate</b> by index.
	 *
	 * @param index
	 * @return Predicate
	 */
	public final Predicate getPredicate(int index) {
		return ((Predicate)predicate_.get(index));
	}

	/**
	 * Sets the Predicate property <b>Predicate</b> by index.
	 *
	 * @param index
	 * @param predicate
	 */
	public final void setPredicate(int index, Predicate predicate) {
		this.predicate_.set(index, predicate);
		if (predicate != null) {
			predicate.rSetParentRNode(this);
		}
	}

	/**
	 * Adds the Predicate property <b>Predicate</b> by index.
	 *
	 * @param index
	 * @param predicate
	 */
	public final void addPredicate(int index, Predicate predicate) {
		this.predicate_.add(index, predicate);
		if (predicate != null) {
			predicate.rSetParentRNode(this);
		}
	}

	/**
	 * Remove the Predicate property <b>Predicate</b> by index.
	 *
	 * @param index
	 */
	public final void removePredicate(int index) {
		this.predicate_.remove(index);
	}

	/**
	 * Remove the Predicate property <b>Predicate</b> by object.
	 *
	 * @param predicate
	 */
	public final void removePredicate(Predicate predicate) {
		this.predicate_.remove(predicate);
	}

	/**
	 * Clear the Predicate property <b>Predicate</b>.
	 *
	 */
	public final void clearPredicate() {
		this.predicate_.clear();
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
		buffer.append("<predicates");
		buffer.append(">");
		size = this.predicate_.size();
		for (int i = 0;i < size;i++) {
			Predicate value = (Predicate)this.predicate_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.append("</predicates>");
	}

	public void makePDDLTextElement(StringBuffer buffer, Map<URI, ArrayList<PDDLNode>> pddlNodes) {
		int size;

		//        buffer.append(">");
		size = this.predicate_.size();

		if (size == 0)
			return;

		buffer.append("(:predicates ");

		StringBuffer onlyPredicates = new StringBuffer();

		for (int i = 0;i < size;i++) {

			if (i != 0) {
				onlyPredicates.append("\n \t");
			}

			Predicate value = (Predicate)this.predicate_.get(i);
			value.makePDDLTextElement(onlyPredicates);
		}

		String[] existingPredicates = onlyPredicates.toString().split("\n");
		ArrayList<String> alreadyAdded = new ArrayList<String>();
		// if there are predicates within the PDDLNodes that aren't already listed, then add them
		for(Entry<URI, ArrayList<PDDLNode>> entry: pddlNodes.entrySet()) {

			for (PDDLNode node: entry.getValue()) {
				
				ArrayList<String> check = node.getAllPredicates();
				for (String s: node.getAllPredicatesAsPddl()) {
					
					String onlyPredicate = s.split("\\s+")[0];
					if (!existingPredicates.toString().contains(onlyPredicate) && !alreadyAdded.contains(s)) {
						alreadyAdded.add(s);
						buffer.append(s);
						buffer.append("\n");
					}
			}
			
		}
		}
		//buffer.append("\n;<debugging> predicates above were parsed from PDDL-expressions within OWL-S files; those below were not\n\n");
		buffer.append(onlyPredicates);

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
		buffer.write("<predicates");
		buffer.write(">");
		size = this.predicate_.size();
		for (int i = 0;i < size;i++) {
			Predicate value = (Predicate)this.predicate_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.write("</predicates>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<predicates");
		buffer.print(">");
		size = this.predicate_.size();
		for (int i = 0;i < size;i++) {
			Predicate value = (Predicate)this.predicate_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.print("</predicates>");
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
		classNodes.addAll(predicate_);
		IRNode[] nodes = new IRNode[classNodes.size()];
		return ((IRNode[])classNodes.toArray(nodes));
	}

	/**
	 * Tests if a Element <code>element</code> is valid
	 * for the <code>Predicates</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "predicates")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		while (true) {
			if (!Predicate.isMatchHungry(target)) {
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
	 * is valid for the <code>Predicates</code>.
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
	 * is valid for the <code>Predicates</code>.
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
