/*
 * Created on 29.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author andi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceExecutionFileReader {

	private RandomAccessFile f;

	private FileConverter fc = null;

	public ServiceExecutionFileReader(String Path, FileConverter FC) {
		fc = FC;
		String line = "";
		String totalLine = "";
		String parts[];
		ArrayList serviceNameToRemove = new ArrayList();
		ArrayList serviceNameThatAreExecuted = new ArrayList();
		try {
			f = new RandomAccessFile(Path + "PlannerInfeasibleServices.xml",
					"r");
			boolean parsed = false;
			do {
				totalLine = f.readLine();
			} while (!totalLine.contains("result"));
			parts = totalLine.split("</");
			// executed services
			String part = parts[0];
			String help[] = part.split("/>");
			int counter = help.length - 1;
			do {
				if (help[counter] != null) {
					if (help[counter].contains("name=")) {
						serviceNameThatAreExecuted.add(help[counter]
								.split("name=\"")[1].split("\"")[0]);
					}
				}
				counter--;
			} while (counter == 0);

			// failed services
			part = parts[1];
			help = part.split("/>");
			counter = help.length - 1;
			do {
				if (help[counter] != null) {
					if (help[counter].contains("name=")) {
						serviceNameToRemove
								.add(help[counter].split("name=\"")[1]
										.split("\"")[0]);
					}
				}
				counter--;
			} while (counter == 0);

			// expensive services
			part = parts[2];
			help = part.split("/>");
			counter = help.length - 1;
			do {
				if (help[counter] != null) {
					if (help[counter].contains("name=")) {
						serviceNameToRemove
								.add(help[counter].split("name=\"")[1]
										.split("\"")[0]);
					}
				}
				counter--;
			} while (counter == 0);
			/*while ( (line=f.readLine()) != null  && !line.contains("</result>")) {
			 
			 if(line.contains("executed_services")) {
			 while ((line=f.readLine()) != null  &&  !line.contains("</executed_services>")) {
			 if (line.contains("name=\"")) {
			 serviceNameThatAreExecuted.add(line.split("name=\"")[1].split("\"")[0]);
			 }
			 }
			 }
			 if(line.contains("expensive_services")) {
			 while ((line=f.readLine()) != null  &&  !line.contains("</expensive_services>")) {
			 if (line.contains("name=\"")) {
			 serviceNameToRemove.add(line.split("name=\"")[1].split("\"")[0]);
			 }
			 }
			 }
			 
			 if(line.contains("failed_services")) {
			 while ((line=f.readLine()) != null  &&  !line.contains("</failed_services>")) {
			 if (line.contains("name=\"")) {
			 serviceNameToRemove.add(line.split("name=\"")[1].split("\"")[0]);
			 }
			 }
			 }
			 } // end of while
			 */
			f.close();
			//  serviceNameToRemove.add(line.split("name=\"")[1].split("\"")[0]);

			for (int i = 0; i < serviceNameToRemove.size(); i++) {
				String test = (String) serviceNameToRemove.get(i);
				//   	System.out.println("serv-to-remove  " +(String)serviceNameToRemove.get(i));
				if (!test.equals("null")) {
					fc.pddxml_actions.removeAction((String) serviceNameToRemove
							.get(i));
				}
			}

		} catch (FileNotFoundException e) // File does not exists
		{
			System.err.println("ServiceExecutionFileReader: File not found ! "
					+ e);
			e.printStackTrace();
		} catch (IOException e) // Read / write error
		{
			System.err.println("ServiceExecutionFileReader: Read / write error"
					+ e);
			e.printStackTrace();
		} catch (Exception e) // any other problems
		{
			System.err
					.println("ServiceExecutionFileReader: An error occurs while parsing this line: "
							+ totalLine);
			e.printStackTrace();
		}
	}
}
