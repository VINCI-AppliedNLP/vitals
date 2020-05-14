package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Iterator;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ExtractTemperatureAE extends BaseVitalExtractorAE {
	public static String currentType = "Temperature";
	public static String outputValue = T_value.class.getCanonicalName();
	public static double[][] typeRanges = { { 34.0, 44.0 }, { 94.0, 107.0 } };

	@Override
	public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

		try {

			analyzePatterns(aJCas);
			createValueTypes(aJCas, currentType, outputValue);

			analyzeDoubles(aJCas);
			createValueTypes(aJCas, currentType, outputValue);
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	/** 
	 * If a document contains pattern, process that pattern
	 * @param aJCas
	 * @throws CASException 
	 */
	public void analyzePatterns(JCas aJCas) throws CASException {

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		while (iter.hasNext()) {
			Relation currRelation = (Relation) iter.next();
			if (currRelation.getTarget() != null) {
				Annotation value = currRelation.getTarget();

				Unit curUnit = null;
				ArrayList<Annotation> units = ((ArrayList<Annotation>) AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type, false));
				if (units.size() > 0) {
					if(AnnotationLibrarian.getNextClosestAnnotations(value, units).size()>0) {
						curUnit = (Unit)((ArrayList<Annotation>)(AnnotationLibrarian.getNextClosestAnnotations(value, units))).get(0); // get the first unit in the pattern
					}else{
						curUnit = (Unit)((ArrayList<Annotation>)(AnnotationLibrarian.getPreviousClosestAnnotations(value, units))).get(0);
					}
				}

				// Has term?
				if (currRelation.getAnchor() != null) {
					Annotation term = currRelation.getAnchor();
					// check type
					if (term instanceof T_Term) {
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
		}

	}

	public void analyzeDoubles(JCas aJCas) throws CASException {

		Iterator<Annotation> doubles = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    DoubleNumber.class.getCanonicalName(), false).iterator();
		while (doubles.hasNext()) {
			DoubleNumber d = (DoubleNumber) doubles.next();

			// make sure that the number is not claimed. If it is, then it is safe to skip the number
			if (StringUtils.isBlank(d.getConcept())) {
				Unit currUnit = null;

				ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(d,
				    HiPrecisionWindow.type, false);
				if (coverWindow.size() > 0) {
					processValue(d, currentType, currUnit, false, typeRanges);
				}
			} // end of double number loop
		}
	}

}
