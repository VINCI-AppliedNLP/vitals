package gov.va.vinci.vitals.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.uima.cas.CAS;

public class ListenerLogic {

// Using HashMap instead of String[] to allow arbitrary ordering of columns
	public static ArrayList<HashMap<String,String>> getRows(CAS aCas) {
		ArrayList<HashMap<String,String>> allRows = new ArrayList<HashMap<String,String>>();
		///////////////////////////////////////////////////////////		
		//  This is project specific!!!
		///////////////////////////////////////////////////////////
		HashMap<String, String> rowItems = new HashMap<String,String>();
		rowItems.put("Term", "Term placeholder");
		allRows.add(rowItems);
		
		
		
		///////////////////////////////////////////////////////////
	  return allRows;
  }

}
