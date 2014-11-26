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
		try {
			processValue(a, vital_type, u, false);
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param a
	 * @param vital_type
	 * @param u
	 * @throws CASException 
	 */
	public void processValue(Annotation a, String vital_type, Annotation u, boolean markIt) throws CASException {
		if (a instanceof Numeric) {
			if (StringUtils.isBlank(((Numeric) a).getConcept())) {
				if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(a, PotentialBp.class.getCanonicalName()).size() > 0) {
					return;
				}
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
			if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(a, PotentialBp.class.getCanonicalName()).size() > 0) {
				return;
			}
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

	public static void discardSystolic(Annotation systolicAnnotation) {
		if (systolicAnnotation != null) {
			if (systolicAnnotation instanceof IntegerNumber) {
				((IntegerNumber) systolicAnnotation).setConcept("Mathched BP pattern but not value");
			} else if (systolicAnnotation instanceof Range) {
				Annotation rv1 = ((Range) systolicAnnotation).getValue1();
				Annotation rv2 = ((Range) systolicAnnotation).getValue2();
				if (rv1 instanceof IntegerNumber && rv2 instanceof IntegerNumber) {

					((IntegerNumber) rv1).setConcept("Mathched BP pattern but not value");
					((IntegerNumber) rv2).setConcept("Mathched BP pattern but not value");
				}

			}
		}
	}

	public static void processPotentialBp(PotentialBp currBp) {
		try {
			// Anchor is Systolic
			// Target is Diastolic
			// if diastolic fails then systolic should be changed as well.
			Annotation systolicAnnotation = null;
			Annotation diastolicAnnotation = null;

			boolean bothMatch = true;
			if (currBp.getAnchor() != null) {
				systolicAnnotation = currBp.getAnchor();
				// The first annotation can be either an integer or a range
				if (systolicAnnotation instanceof IntegerNumber) {
					if (StringUtils.isBlank(((Numeric) systolicAnnotation).getConcept())) {
						if (CheckRange.isSystolicBp(((IntegerNumber) systolicAnnotation).getValue())) {
							((IntegerNumber) systolicAnnotation).setConcept(vitalTypes.Systolic.name());
						} else {
							bothMatch = false;
							((IntegerNumber) systolicAnnotation).setConcept("Mathched BP pattern but not value");
						}
					}
				} else if (systolicAnnotation instanceof Range) {
					Annotation rv1 = ((Range) systolicAnnotation).getValue1();
					Annotation rv2 = ((Range) systolicAnnotation).getValue2();
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
			/**/
			if (bothMatch) {
				if (currBp.getTarget() != null) {
					diastolicAnnotation = currBp.getTarget();

					if (diastolicAnnotation instanceof IntegerNumber) {
						if (StringUtils.isBlank(((Numeric) diastolicAnnotation).getConcept())) {
							if (CheckRange.isDiastolicBp(((IntegerNumber) diastolicAnnotation).getValue())) {
								((IntegerNumber) diastolicAnnotation).setConcept(vitalTypes.Diastolic.name());
							} else {
								bothMatch = false;
								((IntegerNumber) diastolicAnnotation).setConcept("Mathched BP pattern but not value");
								// if the second number fails, discard the first number as well
								discardSystolic(systolicAnnotation);
							}
						}
					} else if (diastolicAnnotation instanceof Range) {
						Annotation diastolicRange1 = ((Range) diastolicAnnotation).getValue1();
						Annotation diastolicRange2 = ((Range) diastolicAnnotation).getValue2();
						if (diastolicRange1 instanceof IntegerNumber && diastolicRange2 instanceof IntegerNumber) {
							if (StringUtils.isBlank(((Numeric) diastolicRange1).getConcept())
							    && StringUtils.isBlank(((Numeric) diastolicRange2).getConcept())) {
								if (CheckRange.isDiastolicBp(((IntegerNumber) diastolicRange1).getValue())
								    && CheckRange.isDiastolicBp(((IntegerNumber) diastolicRange2).getValue())) {
									((IntegerNumber) diastolicRange1).setConcept(vitalTypes.Diastolic.name());
									((IntegerNumber) diastolicRange2).setConcept(vitalTypes.Diastolic.name());
								} else {
									bothMatch = false;
									((IntegerNumber) diastolicRange1).setConcept("Mathched BP pattern but not value");
									((IntegerNumber) diastolicRange2).setConcept("Mathched BP pattern but not value");
									discardSystolic(systolicAnnotation);
								}
							}
						}
					} // end if Range
				}
			} /**/

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
