package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.ae.ProcessingStepAE.CheckRange;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ExtractPainAE extends ProcessingStepAE {
	static String currentType = vitalTypes.Pain.name();

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
				if (currRelation.getTarget() != null) {
					Annotation value = currRelation.getTarget();

					Unit curUnit = null;

					// Has term?
					if (currRelation.getAnchor() != null) {
						Annotation term = currRelation.getAnchor();
						// check type
						if (term instanceof Pain_Term) {
							processValue(value, currentType, curUnit, true);
						} else {
							continue;
						}
					}
				}
			}
		} catch (Exception ex) {
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
				if (CheckRange.isPain(((Numeric) a).getValue())) {
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
				if (CheckRange.isPain(((Numeric) ((Range) a).getValue1()).getValue())
				    && CheckRange.isPain(((Numeric) ((Range) a).getValue2()).getValue())) {
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
		} else if (a instanceof PotentialBp) {
			Annotation pb1 = ((PotentialBp) a).getAnchor();
			Annotation pb2 = ((PotentialBp) a).getTarget();
			if (pb2 instanceof Numeric) {
				if (((Numeric) pb2).getValue() == 10) {
					if (pb1 instanceof Numeric) {
						if (StringUtils.isBlank(((Numeric) pb1).getConcept())) {
							if (CheckRange.isPain(((Numeric) pb1).getValue())) {
								((Numeric) pb1).setConcept(vital_type);
								((Numeric) pb1).setUnit(u);

							}
							if (markIt) {
								((Numeric) pb1).setConcept("Did not match on value: " + vital_type);
							}
						}
					}
				}
				//remove the denominator from index so that it is not confused with a value
				Annotation ten = pb2;
				((PotentialBp) a).setTarget(null);
				ten.removeFromIndexes();
			} else if (pb1 instanceof Range) {
				log.error("Unhandled condition in line 107 in ExtractPainAE.java");
			}
		}

	}
}
