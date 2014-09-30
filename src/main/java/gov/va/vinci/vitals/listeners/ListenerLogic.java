package gov.va.vinci.vitals.listeners;

import gov.va.vinci.kttr.types.*;
import gov.va.vinci.vitals.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;

public class ListenerLogic {
	public static int recordID = 0;

	// Using HashMap instead of String[] to allow arbitrary ordering of columns
	public static ArrayList<HashMap<String, String>> getRows(CAS aCas) {
		ArrayList<HashMap<String, String>> allRows = new ArrayList<HashMap<String, String>>();

		///////////////////////////////////////////////////////////		
		//  This is project specific!!!
		///////////////////////////////////////////////////////////
		// Output all refst annotations
		String[] types = new String[] {
		    //BPValue.class.getCanonicalName(), HRValue.class.getCanonicalName(), TValue.class.getCanonicalName()
		    //, 
		    	Bp_value.class.getCanonicalName(),				Hr_value.class.getCanonicalName(),				T_value.class.getCanonicalName()
		};
		for (String singleType : types) {
			Type type = aCas.getTypeSystem().getType(singleType);
			FSIndex<?> index = aCas.getAnnotationIndex(type);
			FSIterator<?> iterator = index.iterator();

			while (iterator.hasNext()) {
				recordID++;
				HashMap<String, String> lineRow = new HashMap<String, String>();
				Annotation a = (Annotation) iterator.next();
				lineRow.put("VitalType", singleType);
				lineRow.put("ValueString", a.getCoveredText().replaceAll("\\s+", " "));
				lineRow.put("SpanStart", "" + a.getBegin());
				lineRow.put("SpanEnd", "" + a.getEnd());
				lineRow.put("VitalSignID", "" + recordID);
				allRows.add(lineRow);
			}
		}

		///////////////////////////////////////////////////////////
		return allRows;
	}

}
