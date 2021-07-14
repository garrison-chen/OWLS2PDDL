/*
 * OWLS2PDDL Converter
 *
 * Copyright (C) 2005 DFKI GmbH, Germany
 * Developed by Andreas Gerber, Matthias Klusch
 *
 * The code is free for non-commercial use only.
 * You can redistribute it and/or modify it under the terms
 * of the Mozilla Public License version 1.1  as
 * published by the Mozilla Foundation at
 * http://www.mozilla.org/MPL/MPL-1.1.txt
 */

package converter;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OWLS2PDDXML_Converter extends JPanel implements ActionListener {

	/**
	 * This is the main object, that includes the main-function. This is an example 
	 * how to use the converter. This class opens a standard file-requester to select
	 * an owls-file which than is automatically converted to pddxml.
	 */
	public OWLS2PDDXML_Converter() {
		super();

		this.setBackground(new Color(210, 220, 255));

		//Create the log first, to display some additional informations.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		logScrollPane.setBounds(180, 100, 300, 100);

		setLayout(null);

		//Create describing help text area
		JTextArea help = new JTextArea("Convert an OWL-S file to PDDXML:");
		help.setBounds(10, 10, 150, 50);
		//help.setAlignmentX(help.CENTER_ALIGNMENT);
		help.setBackground(new Color(210, 220, 255));
		help.setForeground(Color.RED);
		help.setLineWrap(true);
		help.setWrapStyleWord(true);
		help.setEditable(false);
		help.setFont(new Font("Sans", Font.BOLD, 14));
		help.setPreferredSize(new Dimension(200, 50));

		//Create a file chooser, initialised with a path 
		fc = new JFileChooser(
				"C:/tmp/workspace/OWLS2PDDXML Converter/bin/Scenarios/5_services_w_o_replanning");

		//Create the open button.  
		openButton = new JButton("Convert OWL-S File");
		openButton.addActionListener(this);
		openButton.setBounds(180, 20, 150, 30);

		JLabel ScrollPaneLabel = new JLabel("Log Text:");
		ScrollPaneLabel.setBounds(10, 120, 80, 30);

		add(ScrollPaneLabel);
		add(help);
		add(openButton);
		add(logScrollPane);
	}

	/**
	 * When clicked on <code>openButton</code> a file chooser is opened.
	 */
	JButton openButton;

	/**
	 * <code>log</code> holding the log entries.
	 */
	JTextArea log;

	/**
	 * <code>fc</code> file chooser object.
	 */
	JFileChooser fc;

	/**
	 * Handler to perform the open button action, when clicked.
	 */
	public void actionPerformed(ActionEvent e) {

		//Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(OWLS2PDDXML_Converter.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				log.append("Opening: " + file.getName() + "\n");
				log.append("Parsing the owl-s files \n");

				//*********************************************************//
				//* This is where a real application would open the file. *//
				//*********************************************************//
				try {
					// initialising an owls2pddxml file converter object
					FileConverter fc = new FileConverter();

					// invoking the converter by sending the absolute pfad and the file
					// name to be converted.
					boolean result = fc.convertOWLS2PDDXML(file
							.getAbsolutePath().split(file.getName())[0], file
							.getName());

					if (result) {
						log.append("OWL-S to PDDXML - SUCCESS\n");

					} else
						log.append("OWL-S to PDDXML - FAILED\n");

				} catch (Exception ee) {
					System.out
							.println("Error during converting the owl-s files! \n"
									+ ee);
					ee.printStackTrace();
				}
			} else {
				log.append("Open command cancelled by user.\n");
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	/**
	 * Creates the GUI and shows it. This method is only for the demonstration and
	 * to include the file-chooser.   
	 */
	private static void createAndShowGUI() {
		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		//Create and set up the window.
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		JComponent newContentPane = new OWLS2PDDXML_Converter();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creating and showing this application's GUI.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

} // End of Document

