package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/** 
 * @author vhaslcpatteo
 *
 *   the extractor does not handle complex representation such as 5'4" or 5ft 4in
 */
public class ExtractHeightAE extends BaseVitalExtractorAE {

	static String currentType = "Height";
	public static String outputValue = Height_value.class.getCanonicalName();
	public static double[][] typeRanges = { { 52.0, 84.0 }, { 132.0, 214.0 } };  // 4'6" - 7"

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		try {
			analyzePatterns(aJCas);
			processPotentialHeight(aJCas);
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		createValueTypes(aJCas, currentType, outputValue);
	}

	public void processPotentialHeight(JCas aJCas) throws CASException {

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, PotentialHeight.class.getCanonicalName());
		while (iter.hasNext()) {
			PotentialHeight potentialHeight = (PotentialHeight) iter.next();
			Annotation feet = null;
			Annotation inch = null;
			double v = 0;
			if (potentialHeight.getAnchor() != null) {
				feet = potentialHeight.getAnchor();
				v = 12 * ((Numeric) feet).getValue();
			}

			if (potentialHeight.getTarget() != null) {
				inch = potentialHeight.getTarget();
				v = v + ((Numeric) inch).getValue();
			}

			Numeric newNumeric = new Numeric(aJCas);
			if (feet != null) {
				newNumeric.setBegin(feet.getBegin());
				newNumeric.setEnd(feet.getEnd());
			}
			if (inch != null) {
				newNumeric.setEnd(inch.getEnd());
			}

			if (StringUtils.isNotBlank(newNumeric.getCoveredText())) {
				newNumeric.addToIndexes(aJCas);
				newNumeric.setConcept(currentType);
				newNumeric.setValue(v);
				

			}
		}
	}

	/** 
	 * If a document contains pattern, process that pattern
	 * variables to change -- vital name
	 * 
	 * @param aJCas
	 * @throws CASException 
	 */
	public void analyzePatterns(JCas aJCas) throws CASException {

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		while (iter.hasNext()) {
			Relation currRelation = (Relation) iter.next();
			//		if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currRelation, LowerPrecisionWindow.type).size() > 0) {
			if (currRelation.getTarget() != null) {
				Annotation value = currRelation.getTarget();
				Unit curUnit = null;
				if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
					curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
					    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type)).get(0); // get the first unit in the pattern
				}

				// Has term?
				if (currRelation.getAnchor() != null) {
					Annotation term = currRelation.getAnchor();
					// check type
					if (term instanceof Height_Term) {
						processValue(value, currentType, curUnit, true, typeRanges);
					} else {
						continue;
					}
				} else if (curUnit != null) {
					if ((curUnit.getConcept().equalsIgnoreCase(currentType))) {
						processValue(value, currentType, curUnit, true, typeRanges);
					} else {
						continue;
					}
				}
			}
			//}
		}

	}

    public static class Param extends BaseVitalExtractorAE.Param {
        /** No addtional parameters **/
    }

}
