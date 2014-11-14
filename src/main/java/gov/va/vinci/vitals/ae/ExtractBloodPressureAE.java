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

public class ExtractBloodPressureAE extends ProcessingStepAE {
	static String currentType = vitalTypes.Systolic.name();

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		try {
			analyzePatterns(aJCas);
			createValueTypes(aJCas);

			//processed potential bps within the lowerprecision
			analyzeLowerPWindow(aJCas);
			createValueTypes(aJCas);

		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void analyzeLowerPWindow(JCas aJCas) throws CASException {

		Iterator<Annotation> pbps = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    PotentialBp.class.getCanonicalName()).iterator();
		while (pbps.hasNext()) {
			PotentialBp d = (PotentialBp) pbps.next();

			ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(d,
			    LowerPrecisionWindow.type);

			// FIXME

			if (coverWindow.size() > 0) {

				processPotentialBp(d);
			}
		} // end of double number loop
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
					// if systolic - test for systolic
					// if diastolic -- test for diastolic
					// if bp pattern -- split left and right
					if (term instanceof Bp_Term) {
						String bpType = ((Bp_Term) term).getConcept();

						processValue(value, bpType, curUnit, true);
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
				if (CheckRange.isSystolicBp(((Numeric) a).getValue())) {
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
				if (CheckRange.isSystolicBp(((Numeric) ((Range) a).getValue1()).getValue())
				    && CheckRange.isSystolicBp(((Numeric) ((Range) a).getValue2()).getValue())) {
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
			processPotentialBp((PotentialBp) a);
		}
	}

	public static void processPotentialBp(PotentialBp currBp) {
		try {
			// Anchor is Systolic
			// Target is Diastolic
			Annotation value1 = null;
			Annotation value2 = null;
			boolean bothMatch = true;
			if (currBp.getAnchor() != null) {
				value1 = currBp.getAnchor();
				if (value1 instanceof IntegerNumber) {
					if (StringUtils.isBlank(((Numeric) value1).getConcept())) {
						if (CheckRange.isSystolicBp(((IntegerNumber) value1).getValue())) {
							((IntegerNumber) value1).setConcept(vitalTypes.Systolic.name());
						} else {
							bothMatch = false;
							((IntegerNumber) value1).setConcept("Mathched BP pattern but not value");

						}
					}
				} else if (value1 instanceof Range) {
					Annotation rv1 = ((Range) value1).getValue1();
					Annotation rv2 = ((Range) value1).getValue2();
					if (rv1 instanceof IntegerNumber && rv2 instanceof IntegerNumber) {
						if (StringUtils.isBlank(((Numeric) rv1).getConcept())
						    && StringUtils.isBlank(((Numeric) rv2).getConcept())) {
							if (CheckRange.isSystolicBp(((IntegerNumber) rv1).getValue())
							    && CheckRange.isSystolicBp(((IntegerNumber) rv2).getValue())) {
								((IntegerNumber) rv1).setConcept(vitalTypes.Systolic.name());
								((IntegerNumber) rv2).setConcept(vitalTypes.Systolic.name());
							}
						}
					}
				} // end if Range
			} // end if Anchor
			if (bothMatch) {
				if (currBp.getTarget() != null) {
					value2 = currBp.getTarget();

					if (value2 instanceof IntegerNumber) {
						if (StringUtils.isBlank(((Numeric) value2).getConcept())) {
							if (CheckRange.isDiastolicBp(((IntegerNumber) value2).getValue())) {
								((IntegerNumber) value2).setConcept(vitalTypes.Diastolic.name());
							} else {
								bothMatch = false;
								((IntegerNumber) value2).setConcept("Mathched BP pattern but not value");
								if (value1 != null)
									((IntegerNumber) value1).setConcept("Mathched BP pattern but not value");
							}
						}
					} else if (value2 instanceof Range) {
						Annotation rv1 = ((Range) value2).getValue1();
						Annotation rv2 = ((Range) value2).getValue2();
						if (rv1 instanceof IntegerNumber && rv2 instanceof IntegerNumber) {
							if (StringUtils.isBlank(((Numeric) rv1).getConcept())
							    && StringUtils.isBlank(((Numeric) rv2).getConcept())) {
								if (CheckRange.isDiastolicBp(((IntegerNumber) rv1).getValue())
								    && CheckRange.isDiastolicBp(((IntegerNumber) rv2).getValue())) {
									((IntegerNumber) rv1).setConcept(vitalTypes.Diastolic.name());
									((IntegerNumber) rv2).setConcept(vitalTypes.Diastolic.name());
								}
							}
						}
					} // end if Range
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
