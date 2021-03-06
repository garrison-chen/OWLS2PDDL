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
 * <b>Domain</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="define_domain">
 *   <ref name="NameAttr"/>
 *   <ref name="Requirements"/>
 *   <ref name="Types"/>
 *   <ref name="Predicates"/>
 *   <ref name="Actions"/>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="define_domain"&gt;
 *   &lt;ref name="NameAttr"/&gt;
 *   &lt;ref name="Requirements"/&gt;
 *   &lt;ref name="Types"/&gt;
 *   &lt;ref name="Predicates"/&gt;
 *   &lt;ref name="Actions"/&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Domain implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
	private String name_;
	private Requirements requirements_;
	private Types types_;
	private Predicates predicates_;
	private Actions actions_;
	private IRNode parentRNode_;

	/**
	 * Creates a <code>Domain</code>.
	 *
	 */
	public Domain() {
		name_ = "";
	}

	/**
	 * Creates a <code>Domain</code>.
	 *
	 * @param source
	 */
	public Domain(Domain source) {
		setup(source);
	}

	/**
	 * Creates a <code>Domain</code> by the Stack <code>stack</code>
	 * that contains Elements.
	 * This constructor is supposed to be used internally
	 * by the Relaxer system.
	 *
	 * @param stack
	 */
	public Domain(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Domain</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Domain(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Domain</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Domain(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Domain</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Domain</code>
	 * by the String representation of URI <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Domain</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Domain</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Domain</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Domain</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Domain(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Domain</code> by the Domain <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Domain source) {
		int size;
		setName(source.getName());
		if (source.requirements_ != null) {
			setRequirements((Requirements)source.getRequirements().clone());
		}
		if (source.types_ != null) {
			setTypes((Types)source.getTypes().clone());
		}
		if (source.predicates_ != null) {
			setPredicates((Predicates)source.getPredicates().clone());
		}
		if (source.actions_ != null) {
			setActions((Actions)source.getActions().clone());
		}
	}

	/**
	 * Initializes the <code>Domain</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Domain</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Domain</code> by the Stack <code>stack</code>
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
		setRequirements(new Requirements(stack));
		setTypes(new Types(stack));
		setPredicates(new Predicates(stack));
		setActions(new Actions(stack));
	}

	/**
	 * @return Object
	 */
	public Object clone() {
		return (new Domain(this));
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
		Element element = doc.createElement("define_domain");
		int size;
		if (this.name_ != null) {
			URelaxer.setAttributePropertyByString(element, "name", this.name_);
		}
		this.requirements_.makeElement(element);
		this.types_.makeElement(element);
		this.predicates_.makeElement(element);
		this.actions_.makeElement(element);
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Domain</code> by the File <code>file</code>.
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
	 * Initializes the <code>Domain</code>
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
	 * Initializes the <code>Domain</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Domain</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Domain</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Domain</code> by the Reader <code>reader</code>.
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
	 * Gets the Requirements property <b>Requirements</b>.
	 *
	 * @return Requirements
	 */
	public final Requirements getRequirements() {
		return (requirements_);
	}

	/**
	 * Sets the Requirements property <b>Requirements</b>.
	 *
	 * @param requirements
	 */
	public final void setRequirements(Requirements requirements) {
		this.requirements_ = requirements;
		if (requirements != null) {
			requirements.rSetParentRNode(this);
		}
	}

	/**
	 * Gets the Types property <b>Types</b>.
	 *
	 * @return Types
	 */
	public final Types getTypes() {
		return (types_);
	}

	/**
	 * Sets the Types property <b>Types</b>.
	 *
	 * @param types
	 */
	public final void setTypes(Types types) {
		this.types_ = types;
		if (types != null) {
			types.rSetParentRNode(this);
		}
	}

	/**
	 * Gets the Predicates property <b>Predicates</b>.
	 *
	 * @return Predicates
	 */
	public final Predicates getPredicates() {
		return (predicates_);
	}

	/**
	 * Sets the Predicates property <b>Predicates</b>.
	 *
	 * @param predicates
	 */
	public final void setPredicates(Predicates predicates) {
		this.predicates_ = predicates;
		if (predicates != null) {
			predicates.rSetParentRNode(this);
		}
	}

	/**
	 * Gets the Actions property <b>Actions</b>.
	 *
	 * @return Actions
	 */
	public final Actions getActions() {
		return (actions_);
	}

	/**
	 * Sets the Actions property <b>Actions</b>.
	 *
	 * @param actions
	 */
	public final void setActions(Actions actions) {
		this.actions_ = actions;
		if (actions != null) {
			actions.rSetParentRNode(this);
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

	public String makePDDLTextDocument(boolean excludeHTTPFormatting,  Map<URI, ArrayList<PDDLNode>> pddlNodes) {

		StringBuffer buffer = new StringBuffer();
		makePDDLTextElement(buffer, pddlNodes);
		
		
	
		if (excludeHTTPFormatting) {

			String done = new String(buffer);
			String actuallyDone = done.replaceAll("(http\\S+.owl#)", "");
			actuallyDone = actuallyDone.replaceAll("(file\\S+#)", "");

			return actuallyDone;
		}
		
		// replace remake- domains with original files
		return (PDDLNode.replaceRemakeUrls(new String(buffer), false));
	}

	public void makePDDLTextElement(StringBuffer buffer, Map<URI, ArrayList<PDDLNode>> pddlNodes) {

		buffer.append("(define (domain");

		if (name_ != null) {
			buffer.append(" ");
			buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.append(")");
		}
		else {
			buffer.append("DEFAULT_NAME)");
		}

		//        buffer.append(">");
		requirements_.makePDDLTextElement(buffer);
		buffer.append("\n");
		types_.makePDDLTextElement(buffer);
		buffer.append("\n");
		predicates_.makePDDLTextElement(buffer, pddlNodes);
		buffer.append("\n");
		buffer.append("\n");
		actions_.makePDDLTextElement(buffer, pddlNodes);

		// closing the "define" bracket
		buffer.append(")");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(StringBuffer buffer) {
		int size;
		buffer.append("<define_domain");
		if (name_ != null) {
			buffer.append(" name=\"");
			buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.append("\"");
		}
		buffer.append(">");
		requirements_.makeTextElement(buffer);
		types_.makeTextElement(buffer);
		predicates_.makeTextElement(buffer);
		actions_.makeTextElement(buffer);
		buffer.append("</define_domain>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<define_domain");
		if (name_ != null) {
			buffer.write(" name=\"");
			buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.write("\"");
		}
		buffer.write(">");
		requirements_.makeTextElement(buffer);
		types_.makeTextElement(buffer);
		predicates_.makeTextElement(buffer);
		actions_.makeTextElement(buffer);
		buffer.write("</define_domain>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<define_domain");
		if (name_ != null) {
			buffer.print(" name=\"");
			buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getName())));
			buffer.print("\"");
		}
		buffer.print(">");
		requirements_.makeTextElement(buffer);
		types_.makeTextElement(buffer);
		predicates_.makeTextElement(buffer);
		actions_.makeTextElement(buffer);
		buffer.print("</define_domain>");
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
		if (requirements_ != null) {
			classNodes.add(requirements_);
		}
		if (types_ != null) {
			classNodes.add(types_);
		}
		if (predicates_ != null) {
			classNodes.add(predicates_);
		}
		if (actions_ != null) {
			classNodes.add(actions_);
		}
		IRNode[] nodes = new IRNode[classNodes.size()];
		return ((IRNode[])classNodes.toArray(nodes));
	}

	/**
	 * Tests if a Element <code>element</code> is valid
	 * for the <code>Domain</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "define_domain")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
		if (!URelaxer.hasAttributeHungry(target, "name")) {
			return (false);
		}
		$match$ = true;
		if (!Requirements.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		if (!Types.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		if (!Predicates.isMatchHungry(target)) {
			return (false);
		}
		$match$ = true;
		if (!Actions.isMatchHungry(target)) {
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
	 * is valid for the <code>Domain</code>.
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
	 * is valid for the <code>Domain</code>.
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
