package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Iterator;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.ae.ProcessingStepAE.CheckRange;
import gov.va.vinci.vitals.ae.ProcessingStepAE.vitalTypes;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ExtractTemperatureAE extends ProcessingStepAE {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);

		try {

			analyzePatterns(aJCas);
			createValueTypes(aJCas);
			analyzeDoubles(aJCas);

			createValueTypes(aJCas);
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

				if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
					curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
					    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type)).get(0); // get the first unit in the pattern
				}

				// Has term?
				if (currRelation.getAnchor() != null) {
					Annotation term = currRelation.getAnchor();
					// check type
					if (term instanceof T_Term) {
						processValue(value, vitalTypes.Temperature.name(), curUnit, true);
					} else {
						continue;
					}
				} else if (curUnit != null) {
					if ((curUnit.getConcept().equalsIgnoreCase(vitalTypes.Temperature.name()))) {
						processValue(value, vitalTypes.Temperature.name(), curUnit, true);
					} else {
						continue;
					}
				}
			}
		}

	}

	public void analyzeDoubles(JCas aJCas) throws CASException {

		Iterator<Annotation> doubles = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    DoubleNumber.class.getCanonicalName()).iterator();
		while (doubles.hasNext()) {
			DoubleNumber d = (DoubleNumber) doubles.next();

			// make sure that the number is not claimed. If it is, then it is safe to skip the number
			if (StringUtils.isBlank(d.getConcept())) {
				Unit currUnit = null;

				ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(d,
				    HiPrecisionWindow.type);
				if (coverWindow.size() > 0) {
					processValue(d, vitalTypes.Temperature.name(), currUnit, false);
				}
			} // end of double number loop
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
				if (CheckRange.isTemperature(((Numeric) a).getValue())) {
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
				if (CheckRange.isTemperature(((Numeric) ((Range) a).getValue1()).getValue())
				    && CheckRange.isTemperature(((Numeric) ((Range) a).getValue2()).getValue())) {
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
