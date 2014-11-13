package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ExtractHeightAE extends ProcessingStepAE {
	static String currentType = vitalTypes.Height.name();

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);

		analyzePatterns(aJCas);
		createValueTypes(aJCas);
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
				if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currRelation, HiPrecisionWindow.type).size() > 0) {
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
								processValue(value, currentType, curUnit, true);
							} else {
								continue;
							}
						} else if (curUnit != null) {
							if ((curUnit.getConcept().equalsIgnoreCase(currentType))) {
								processValue(value, currentType, curUnit, true);
							} else {
								continue;
							}
						}
					}
				}
			}
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void processValue(Annotation a, String vital_type, Annotation u) {
		processValue(a, vital_type, u, false);
	}

	/**
	 * 
	 * @param a
	 * @param vital_type
	 * @param u
	 */
	public void processValue(Annotation a, String vital_type, Annotation u, boolean markIt) {
		if (a instanceof Numeric) {
			if (StringUtils.isBlank(((Numeric) a).getConcept())) {
				((Numeric) a).setConcept(vital_type);
				((Numeric) a).setUnit(u);

			} else if (a instanceof Range) {
				if (StringUtils.isBlank(((Numeric) ((Range) a).getValue1()).getConcept())) {
					((Numeric) ((Range) a).getValue1()).setConcept(vital_type);
					((Numeric) ((Range) a).getValue1()).setUnit(u);
					((Numeric) ((Range) a).getValue2()).setConcept(vital_type);
					((Numeric) ((Range) a).getValue2()).setUnit(u);

				}
			}
		}
	}
}
