package gov.va.vinci.vitals.listeners;

import gov.va.vinci.vitals.ae.RelationAnnotator;
import gov.va.vinci.vitals.types.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
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
		Type inputType = aCas.getTypeSystem().getType(Relation.class.getCanonicalName());

		FSIterator<AnnotationFS> lit = aCas.getAnnotationIndex(inputType).iterator();
		
		while (lit.hasNext()) {
			Relation currRelation = (Relation) lit.next();
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_TERM.feature, currRelation.getTerm());

			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_TERM.feature, currRelation.getTerm());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_ASSESSMENT.feature, currRelation.getAssessment());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_CONCEPT.feature, currRelation.getConcept());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_UNIT.feature, currRelation.getUnit());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_VALUE1.feature, currRelation.getValue());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_VALUE2.feature, currRelation.getValue2());
			rowItems.put(RelationAnnotator.RelationFeatures.FEATURE_VALUESTRING.feature, currRelation.getValueString());
			

			allRows.add(rowItems);
		}

		///////////////////////////////////////////////////////////
		return allRows;
	}

}
