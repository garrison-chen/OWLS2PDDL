/*
 * Created on 09.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package converter;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//import javax.swing.filechooser.*;

/**
 * @author andi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class Composition_Planner extends JPanel implements ActionListener {

	//public class FileChooserDemo extends JPanel
	//                             implements ActionListener {
	static private final String newline = "\n";

	JButton openButton, saveButton;

	JTextArea log;

	JFileChooser fc;

	public Composition_Planner() { //FileChooserDemo() {
		super(new BorderLayout());

		//Create the log first, because the action listeners
		//need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		//Create a file chooser, initialised with a path 
		// my uni-path:  C:/tmp/eclipse/workspace/OWLS-XPlan/converter/OWLS-Dateien/NeueOWLDat
		fc = new JFileChooser(
				"C:/tmp/eclipse/workspace/OWLS-XPlan/converter/OWLS-Dateien/NeueOWLDat");

		//Uncomment one of the following lines to try a different
		//file selection mode.  The first allows just directories
		//to be selected (and, at least in the Java look and feel,
		//shown).  The second allows both files and directories
		//to be selected.  If you leave these lines commented out,
		//then the default mode (FILES_ONLY) will be used.
		//
		//fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		//Create the open button.  We use the image from the JLF
		//Graphics Repository (but we extracted it from the jar).
		openButton = new JButton("Convert OWL-S File...",
				createImageIcon("images/Open16.gif"));
		openButton.addActionListener(this);

		//Create the save button.  We use the image from the JLF
		//Graphics Repository (but we extracted it from the jar).
		/* saveButton = new JButton("Save a File...",
		 createImageIcon("images/Save16.gif"));
		 saveButton.addActionListener(this);*/

		//For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); //use FlowLayout
		buttonPanel.add(openButton);

		//Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {

		//Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(Composition_Planner.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//This is where a real application would open the file.
				log.append("Opening: " + file.getName() + newline);
				log.append("Parsing the owl-s files \n");
				log.append("Generating a pddxml-plan \n");
				try {
					FileConverter fc = new FileConverter();
					boolean result = fc.convertOWLS2PDDXML(file
							.getAbsolutePath().split(file.getName())[0], file
							.getName());
					try {
						//Thread.sleep(700);
						RandomAccessFile f = new RandomAccessFile(
								file.getAbsolutePath().split(file.getName())[0]
										+ file.getName().split(".owl")[0]
										+ "_Plan.xml", "r");
						;
						f.close();
					} catch (Exception ee) {
						System.out
								.println("FileConverter: plan file creation failed! Maybe the Thread.Wait() time was too short!");
						result = false;
					}
					if (result) {
						log.append("Plan computation - SUCCESS\n");

						/*********************************************************************************************/
						/*********************************************************************************************/
						//Now calling Execution Engine Agent for creating Execution Plan and Service Execution
						/*********************************************************************************************/
						/*********************************************************************************************/
						/*********************************************************************************************/
						/*********************************************************************************************/
						/*********************************************************************************************/

					} else
						log.append("Plan computation - FAILED\n");

					System.currentTimeMillis();
				} catch (Exception ee) {
					System.out
							.println("Composition_Planner: Error while parsing and planning: "
									+ ee);
				}
			} else {
				log.append("Open command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	//*************************************************************
	// not used by Andreas Gerber
	//*************************************************************

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Composition_Planner.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		//Create and set up the window.
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		JComponent newContentPane = new Composition_Planner();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

} // End of Document

