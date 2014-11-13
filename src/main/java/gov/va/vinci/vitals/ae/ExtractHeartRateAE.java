package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Iterator;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ExtractHeartRateAE extends ProcessingStepAE {
	static String currentType = vitalTypes.Heart_Rate.name();

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		try {
			analyzePatterns(aJCas);
			createValueTypes(aJCas);

			analyzeHiPWindow(aJCas);
			createValueTypes(aJCas);
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	public void analyzeHiPWindow(JCas aJCas) throws CASException {

		Iterator<Annotation> pbps = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    IntegerNumber.class.getCanonicalName()).iterator();
		while (pbps.hasNext()) {
			IntegerNumber value = (IntegerNumber) pbps.next();

			ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(value,
			    HiPrecisionWindow.type);
			ArrayList<Annotation> potentialBps = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(value,
			    PotentialBp.type);

			// FIXME
			Unit curUnit = null;
			if (coverWindow.size() > 0 && potentialBps.size() == 0) {
				Annotation currWindow = (coverWindow.get(0));
				if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currWindow, Numeric.type).size() > 4) {
					processValue(value, currentType, curUnit, false);
				}
			} // end of double number loop
		}
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

					if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
						curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
						    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type)).get(0); // get the first unit in the pattern
					}

					// Has term?
					if (currRelation.getAnchor() != null) {
						Annotation term = currRelation.getAnchor();
						// check type
						if (term instanceof Hr_Term) {
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
				if (CheckRange.isHeartRate(((Numeric) a).getValue())) {
					((Numeric) a).setConcept(vital_type);
					((Numeric) a).setUnit(u);
				} else {
					if (markIt) {
						((Numeric) a).setConcept("Did not match on value: " + vital_type);
					}
				}
			}
		} else if (a instanceof Range) {
			if (StringUtils.isBlank(((Numeric) ((Range) a).getValue1()).getConcept())) {
				if (CheckRange.isHeartRate(((Numeric) ((Range) a).getValue1()).getValue())
				    && CheckRange.isHeartRate(((Numeric) ((Range) a).getValue2()).getValue())) {
					((Numeric) ((Range) a).getValue1()).setConcept(vital_type);
					((Numeric) ((Range) a).getValue1()).setUnit(u);
					((Numeric) ((Range) a).getValue2()).setConcept(vital_type);
					((Numeric) ((Range) a).getValue2()).setUnit(u);
				} else {
					if (markIt) {
						((Numeric) ((Range) a).getValue1()).setConcept("Did not match on value: " + vital_type);
						((Numeric) ((Range) a).getValue2()).setConcept("Did not match on value: " + vital_type);
					}
				}
			}
		}
	}
}
