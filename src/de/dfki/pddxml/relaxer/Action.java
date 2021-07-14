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
 * <b>Action</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="action">
 *   <ref name="NameAttr"/>
 *   <ref name="Parameters"/>
 *   <ref name="Precondition"/>
 *   <ref name="Effect"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="action"&gt;
 *   &lt;ref name="NameAttr"/&gt;
 *   &lt;ref name="Parameters"/&gt;
 *   &lt;ref name="Precondition"/&gt;
 *   &lt;ref name="Effect"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Action implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
	private String name_;
	private Parameters parameters_;
	private Precondition precondition_;
	private Effect effect_;
	private IRNode parentRNode_;

	/**
	 * Creates a <code>Action</code>.
	 *
	 */
	public Action() {
		name_ = "";
	}

	/**
	 * Creates a <code>Action</code>.
	 *
	 * @param source
	 */
	public Action(Action source) {
		setup(source);
	}

	/**
	 * Creates a <code>Action</code> by the Stack <code>stack</code>
	 * that contains Elements.
	 * This constructor is supposed to be used internally
	 * by the Relaxer system.
	 *
	 * @param stack
	 */
	public Action(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Action</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Action(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Action</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Action(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Action</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Action</code>
	 * by the String representation of URI <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Action</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Action</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Action</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Action</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Action(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Action</code> by the Action <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Action source) {
		int size;
		setName(source.getName());
		if (source.parameters_ != null) {
			setParameters((Parameters)source.getParameters().clone());
		}
		if (source.precondition_ != null) {
			setPrecondition((Precondition)source.getPrecondition().clone());
		}
		if (source.effect_ != null) {
			setEffect((Effect)source.getEffect().clone());
		}
	}

	/**
	 * Initializes the <code>Action</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Action</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Action</code> by the Stack <code>stack</code>
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
		setParameters(new Parameters(stack));
		setPrecondition(new Precondition(stack));
		setEffect(new Effect(stack));
	}

	/**
	 * @return Object
	 */
	public Object clone() {
		return (new Action(this));
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
		Element element = doc.createElement("action");
		int size;
		if (this.name_ != null) {
			URelaxer.setAttributePropertyByString(element, "name", this.name_);
		}
		this.parameters_.makeElement(element);
		this.precondition_.makeElement(element);
		this.effect_.makeElement(element);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Action</code> by the File <code>file</code>.
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
	 * Initializes the <code>Action</code>
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
	 * Initializes the <code>Action</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Action</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Action</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Action</code> by the Reader <code>reader</code>.
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
	 * Gets the Parameters property <b>Parameters</b>.
	 *
	 * @return Parameters
	 */
	public final Parameters getParameters() {
		return (parameters_);
	}

	/**
	 * Sets the Parameters property <b>Parameters</b>.
	 *
	 * @param parameters
	 */
	public final void setParameters(Parameters parameters) {
		this.parameters_ = parameters;
		if (parameters != null) {
			parameters.rSetParentRNode(this);
		}
	}

	/**
	 * Gets the Precondition property <b>Precondition</b>.
	 *
	 * @return Precondition
	 */
	public final Precondition getPrecondition() {
		return (precondition_);
	}

	/**
	 * Sets the Precondition property <b>Precondition</b>.
	 *
	 * @param precondition
	 */
	public final void setPrecondition(Precondition precondition) {
		this.precondition_ = precondition;
		if (precondition != null) {
			precondition.rSetParentRNode(this);
		}
	}

	/**
	 * Gets the Effect property <b>Effect</b>.
	 *
	 * @return Effect
	 */
	public final Effect getEffect() {
		return (effect_);
	}

	/**
	 * Sets the Effect property <b>Effect</b>.
	 *
	 * @param effect
	 */
	public final void setEffect(Effect effect) {
		this.effect_ = effect;
		if (effect != null) {
			effect.rSetParentRNode(this);
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
		buffer.append("<action");
		if (name_ != null) {
			buffer.append(" name=\"");
			buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.append("\"");
		}
		buffer.append(">");
		parameters_.makeTextElement(buffer);
		precondition_.makeTextElement(buffer);
		effect_.makeTextElement(buffer);
		buffer.append("</action>");
	}

	public void makePDDLTextElement(StringBuffer buffer, Map<URI, ArrayList<PDDLNode>> pddlMappings) {
		int size;
		//        buffer.append("<action");
		buffer.append("(:action ");

		String actionName = null;
		URI actionUri = null;
		ArrayList<PDDLNode> pddlNodes = null;
		
		
		//TODO make sure that all variables of an action declared in its (:parameters ...) without redundancies
		if (name_ != null) {
			actionName = URelaxer.escapeAttrQuot(URelaxer.getString(getName()));
			actionUri = XmlToPddlParser.getUri(actionName);
			pddlNodes = pddlMappings.get(actionUri);
			
			if (pddlNodes == null)
				pddlNodes = new ArrayList<PDDLNode>();

		}
		else
			actionName = "no_name_found_error";

		buffer.append(actionName);
		
		//        buffer.append(">");
		buffer.append("\n \n \t");
		parameters_.makePDDLTextElement(buffer, pddlNodes);
		buffer.append("\n \n \t");
		precondition_.makePDDLTextElement(buffer, pddlNodes);
		buffer.append("\n \n \t");
		effect_.makePDDLTextElement(buffer, pddlNodes);
		//        buffer.append("</action>");
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
		buffer.write("<action");
		if (name_ != null) {
			buffer.write(" name=\"");
			buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.write("\"");
		}
		buffer.write(">");
		parameters_.makeTextElement(buffer);
		precondition_.makeTextElement(buffer);
		effect_.makeTextElement(buffer);
		buffer.write("</action>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<action");
		if (name_ != null) {
			buffer.print(" name=\"");
			buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.print("\"");
		}
		buffer.print(">");
		parameters_.makeTextElement(buffer);
		precondition_.makeTextElement(buffer);
		effect_.makeTextElement(buffer);
		buffer.print("</action>");
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
		if (parameters_ != null) {
			classNodes.add(parameters_);
		}
		if (precondition_ != null) {
			classNodes.add(precondition_);
		}
		if (effect_ != null) {
			classNodes.add(effect_);
		}
		IRNode[] nodes = new IRNode[classNodes.size()];
		return ((IRNode[])classNodes.toArray(nodes));
	}

	/**
	 * Tests if a Element <code>element</code> is valid
	 * for the <code>Action</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "action")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		if (!URelaxer.hasAttributeHungry(target, "name")) {
			return (false);
		}
		$match$ = true;
		if (!Parameters.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		if (!Precondition.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		if (!Effect.isMatchHungry(target)) {
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
	 * is valid for the <code>Action</code>.
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
	 * is valid for the <code>Action</code>.
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
