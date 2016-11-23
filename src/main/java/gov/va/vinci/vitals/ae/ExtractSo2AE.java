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

public class ExtractSo2AE extends BaseVitalExtractorAE {
	public static String currentType = "SO2";
	public static String outputValue = So2_value.class.getCanonicalName();

	public static double[][] typeRanges = { { 50, 100 } };

	@Override
	public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);

		analyzePatterns(aJCas);
		createValueTypes(aJCas, currentType, outputValue);
	}

	/** 
	 * If a document contains pattern, process that pattern
	 * variables to change -- vital name
	 * 
	 * @param aJCas
	 */
	public void analyzePatterns(JCas aJCas) {

		try {
			FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
			while (iter.hasNext()) {
				Relation currRelation = (Relation) iter.next();
				if (currRelation.getTarget() != null) {
					Annotation value = currRelation.getTarget();

					Unit curUnit = null;

					if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type, false).size() > 0) {
						curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
						    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type, false)).get(0); // get the first unit in the pattern
					}

					// Has term?
					if (currRelation.getAnchor() != null) {
						Annotation term = currRelation.getAnchor();
						// check type
						if (term instanceof So2_Term) {
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
		} catch (CASException ex) {
			ex.printStackTrace();
		}
	}


}
