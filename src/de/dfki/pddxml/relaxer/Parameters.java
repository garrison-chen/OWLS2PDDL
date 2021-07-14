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
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import de.dfki.owls2pddxml_2_0.OWLS2PDDL;
import de.dfki.owls2pddxml_2_0.PDDLNode;

/**
 * <b>Parameters</b> is generated from PDDXMLDomain.rng by Relaxer.
 * This class is derived from:
 * 
 * <!-- for programmer
 * <element name="parameters">
 *   <zeroOrMore>
 *     <ref name="Parameter"/>
 *   </zeroOrMore>
 * </element>
 * -->
 * <!-- for javadoc -->
 * <pre> &lt;element name="parameters"&gt;
 *   &lt;zeroOrMore&gt;
 *     &lt;ref name="Parameter"/&gt;
 *   &lt;/zeroOrMore&gt;
 * &lt;/element&gt;
 * </pre>
 *
 * @version PDDXMLDomain.rng (Wed Jan 03 14:40:17 CET 2007)
 * @author  Relaxer 1.0 (http://www.relaxer.org)
 */
public class Parameters implements java.io.Serializable, Cloneable, IRVisitable, IRNode {
	// List<Parameter>
	private java.util.List parameter_ = new java.util.ArrayList();
	private IRNode parentRNode_;

	/**
	 * Creates a <code>Parameters</code>.
	 *
	 */
	public Parameters() {
	}

	/**
	 * Creates a <code>Parameters</code>.
	 *
	 * @param source
	 */
	public Parameters(Parameters source) {
		setup(source);
	}

	/**
	 * Creates a <code>Parameters</code> by the Stack <code>stack</code>
	 * that contains Elements.
	 * This constructor is supposed to be used internally
	 * by the Relaxer system.
	 *
	 * @param stack
	 */
	public Parameters(RStack stack) {
		setup(stack);
	}

	/**
	 * Creates a <code>Parameters</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public Parameters(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Creates a <code>Parameters</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public Parameters(Element element) {
		setup(element);
	}

	/**
	 * Creates a <code>Parameters</code> by the File <code>file</code>.
	 *
	 * @param file
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(File file) throws IOException, SAXException, ParserConfigurationException {
		setup(file);
	}

	/**
	 * Creates a <code>Parameters</code>
	 * by the String representation of URI <code>uri</code>.
	 *
	 * @param uri
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(String uri) throws IOException, SAXException, ParserConfigurationException {
		setup(uri);
	}

	/**
	 * Creates a <code>Parameters</code> by the URL <code>url</code>.
	 *
	 * @param url
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(URL url) throws IOException, SAXException, ParserConfigurationException {
		setup(url);
	}

	/**
	 * Creates a <code>Parameters</code> by the InputStream <code>in</code>.
	 *
	 * @param in
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(InputStream in) throws IOException, SAXException, ParserConfigurationException {
		setup(in);
	}

	/**
	 * Creates a <code>Parameters</code> by the InputSource <code>is</code>.
	 *
	 * @param is
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(InputSource is) throws IOException, SAXException, ParserConfigurationException {
		setup(is);
	}

	/**
	 * Creates a <code>Parameters</code> by the Reader <code>reader</code>.
	 *
	 * @param reader
	 * @exception IOException
	 * @exception SAXException
	 * @exception ParserConfigurationException
	 */
	public Parameters(Reader reader) throws IOException, SAXException, ParserConfigurationException {
		setup(reader);
	}

	/**
	 * Initializes the <code>Parameters</code> by the Parameters <code>source</code>.
	 *
	 * @param source
	 */
	public void setup(Parameters source) {
		int size;
		this.parameter_.clear();
		size = source.parameter_.size();
		for (int i = 0;i < size;i++) {
			addParameter((Parameter)source.getParameter(i).clone());
		}
	}

	/**
	 * Initializes the <code>Parameters</code> by the Document <code>doc</code>.
	 *
	 * @param doc
	 */
	public void setup(Document doc) {
		setup(doc.getDocumentElement());
	}

	/**
	 * Initializes the <code>Parameters</code> by the Element <code>element</code>.
	 *
	 * @param element
	 */
	public void setup(Element element) {
		init(element);
	}

	/**
	 * Initializes the <code>Parameters</code> by the Stack <code>stack</code>
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
		return (new Parameters(this));
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
		Element element = doc.createElement("parameters");
		int size;
		size = this.parameter_.size();
		for (int i = 0;i < size;i++) {
			Parameter value = (Parameter)this.parameter_.get(i);
			value.makeElement(element);
		}
		parent.appendChild(element);
	}

	/**
	 * Initializes the <code>Parameters</code> by the File <code>file</code>.
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
	 * Initializes the <code>Parameters</code>
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
	 * Initializes the <code>Parameters</code> by the URL <code>url</code>.
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
	 * Initializes the <code>Parameters</code> by the InputStream <code>in</code>.
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
	 * Initializes the <code>Parameters</code> by the InputSource <code>is</code>.
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
	 * Initializes the <code>Parameters</code> by the Reader <code>reader</code>.
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
		buffer.append("<parameters");
		buffer.append(">");
		size = this.parameter_.size();
		for (int i = 0;i < size;i++) {
			Parameter value = (Parameter)this.parameter_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.append("</parameters>");
	}


	public void makePDDLTextElement(StringBuffer buffer, ArrayList<PDDLNode> variablesToInsert) {
		int size;
		buffer.append(":parameters (");
		//        buffer.append(">");
		size = this.parameter_.size();

		StringBuffer onlyParameters = new StringBuffer();
		//ArrayList<String> parameterFileAndName = new ArrayList<String>();

		for (int i = 0;i < size;i++) {

			if (i != 0) {
				onlyParameters.append("\n \t");
			}

			Parameter value = (Parameter)this.parameter_.get(i);
			value.makePDDLTextElement(onlyParameters);
		}

		if (variablesToInsert.size() > 0) {

			String done = PDDLNode.replaceRemakeUrls(new String(onlyParameters), true);

			//String actuallyDone = done.replaceAll("(http://127.0.0.1:8080/tmp/remake-)", "http://127.0.0.1:8080/health-scallops/services/");

			String[] existingParameters = done.split("\n");
			ArrayList<String> alreadyAdded = new ArrayList<String>();

			for (PDDLNode node: variablesToInsert) {

				for (String s: node.getAllParametersAsPddl()) {

					boolean alreadyIncluded = false;
					// take the file name as well as the parameter name (e.g., Welcome.owl#PersonalInformation)
					String[] splitParameterHttp = s.split("\\s+")[0].split("/"); // e.g., ?http://welcome/services/RegistrationService#TCNIDNumber - object
					String onlyName = splitParameterHttp[splitParameterHttp.length - 1];
					String[] fileAndParameter = onlyName.split("#");

					for (String check: existingParameters) {

						if (check.contains(fileAndParameter[0]) && check.contains(fileAndParameter[1])) {
							alreadyIncluded = true;
							break;
						}

					}

					if (!alreadyIncluded && !alreadyAdded.contains(s)) {
						alreadyAdded.add(s);
						buffer.append(s);
						//buffer.append("\n");
					}}}

			//buffer.append("\n;<debugging> variables above were parsed from PDDL-expressions within OWL-S files; those below were not\n\n");

			// 

			buffer.append(done);
		}
		else
			buffer.append(onlyParameters);

		buffer.append(")");
		//        buffer.append("</parameters>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 * @exception IOException
	 */
	public void makeTextElement(Writer buffer) throws IOException {
		int size;
		buffer.write("<parameters");
		buffer.write(">");
		size = this.parameter_.size();
		for (int i = 0;i < size;i++) {
			Parameter value = (Parameter)this.parameter_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.write("</parameters>");
	}

	/**
	 * Makes an XML text representation.
	 *
	 * @param buffer
	 */
	public void makeTextElement(PrintWriter buffer) {
		int size;
		buffer.print("<parameters");
		buffer.print(">");
		size = this.parameter_.size();
		for (int i = 0;i < size;i++) {
			Parameter value = (Parameter)this.parameter_.get(i);
			value.makeTextElement(buffer);
		}
		buffer.print("</parameters>");
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
		classNodes.addAll(parameter_);
		IRNode[] nodes = new IRNode[classNodes.size()];
		return ((IRNode[])classNodes.toArray(nodes));
	}

	/**
	 * Tests if a Element <code>element</code> is valid
	 * for the <code>Parameters</code>.
	 *
	 * @param element
	 * @return boolean
	 */
	public static boolean isMatch(Element element) {
		if (!URelaxer.isTargetElement(element, "parameters")) {
			return (false);
		}
		RStack target = new RStack(element);
		boolean $match$ = false;
		Element child;
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
	 * is valid for the <code>Parameters</code>.
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
	 * is valid for the <code>Parameters</code>.
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
