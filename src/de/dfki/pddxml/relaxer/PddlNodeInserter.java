package de.dfki.pddxml.relaxer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.dfki.owls2pddxml_2_0.PDDLNode;

public class PddlNodeInserter {
	
	public static void insert(StringBuffer pddlAsString,  Map<URI, ArrayList<PDDLNode>> nodes) {
		
		// key: integer (ascending according to the line the value was found), value: string (switched-out value)
		Map<Integer, String> mappings = new HashMap<>();
		// go line-by-line, adding to mappings if found
		
	}

	public static void encodeHttp(StringBuffer pddlAsString, Map<Integer, String> map) {

		Pattern p = Pattern.compile("(http:\\S+.owl#)");

		Matcher matcher = p.matcher(pddlAsString);

		StringBuffer fixed = new StringBuffer();

		Integer key = new Integer(0);
		
		while(matcher.find()) {
			matcher.appendReplacement(fixed, fixOrStatement(matcher.group(0), key, map));
		}

		matcher.appendTail(fixed);
		pddlAsString.delete(0, pddlAsString.length());

		pddlAsString.append(fixed);

	}


	public static String fixOrStatement(String lineContainingHttp, Integer key, Map<Integer, String> map) {
		
		List<Object> integers = null;
		String replacement = null;
		
		if (map.containsValue(lineContainingHttp)) {
			integers = getKeysFromValue(map, lineContainingHttp);
//			replacement = PLACEHOLDER_STRING+(String)integers.get(0);
		}
		else {
			key = new Integer(key.intValue() + 1);
//			replacement = PLACEHOLDER_STRING+Integer.toString(key);
			map.put(key, lineContainingHttp);
		}
		
		StringBuilder builder = new StringBuilder(lineContainingHttp.replaceAll("(http:\\S+.owl#)", replacement));

//		builder.insert(0, "(or \t");
//		builder.insert(builder.length(), ")");

		return builder.toString();
	}
	
	  public static List<Object> getKeysFromValue(Map<?, ?> hm, Object value){
		    List <Object>list = new ArrayList<Object>();
		    for(Object o:hm.keySet()){
		        if(hm.get(o).equals(value)) {
		            list.add(o);
		        }
		    }
		    return list;
		  }
}
