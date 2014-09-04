package gov.va.vinci.vitals.listeners;

import gov.va.vinci.vitals.ae.RelationAnnotator;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;

public class ListenerLogic {

	// Using HashMap instead of String[] to allow arbitrary ordering of columns
	public static ArrayList<HashMap<String, String>> getRows(CAS aCas) {
		ArrayList<HashMap<String, String>> allRows = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rowItems = new HashMap<String, String>();

		///////////////////////////////////////////////////////////		
		//  This is project specific!!!
		///////////////////////////////////////////////////////////
		Type inputType = aCas.getTypeSystem().getType("");

		FSIterator<AnnotationFS> lit = aCas.getAnnotationIndex(inputType).iterator();
		
		while (lit.hasNext()) {
				

			allRows.add(rowItems);
			rowItems = new HashMap<String, String>();
		}

		///////////////////////////////////////////////////////////
		return allRows;
	}

}
