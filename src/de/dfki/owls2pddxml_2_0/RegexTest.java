package de.dfki.owls2pddxml_2_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static final String TEST_FILE = "/home/anthony/Documents/pddl_domain.pddl";

	public static void main(String args[]) {		

		// don't wait until thursday when this is done -- message elena and matthias as soon as this is done

		// check the android version of XPlan for the resource that allows it to run


		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader(TEST_FILE));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			// delete the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			
			reader.close();
			editOrStatements(new StringBuffer(stringBuilder));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void editOrStatements(StringBuffer buffer) {

		Pattern p = Pattern.compile("\\((.*?)\\)(\\s+or\\s\\((.*?)\\))+");//,Pattern.DOTALL);

		Matcher matcher = p.matcher(buffer);
		
		StringBuffer fixed = new StringBuffer();
		
		while(matcher.find())
			matcher.appendReplacement(fixed, fixOrStatement(matcher.group()));
		
		matcher.appendTail(fixed);
		System.out.println(fixed);

	}


	public static String fixOrStatement(String s) {
		
		
		StringBuilder builder = new StringBuilder(s.replaceAll("(\\s+or\\s)+\\(", "\n\t\t\\("));
		
		builder.insert(0, "(or \t");
		builder.insert(builder.length(), ")");

		return builder.toString();
	}

}
