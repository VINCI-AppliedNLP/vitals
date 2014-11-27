package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.types.*;

public class VitalClassifier {

	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		//super.process(aJCas);
		// According to Makoto:
		// Step 1 - look at decimal --> Temperature
		// Step 2 - look at potentia bp --> bp
		// Step 3 - look at integer with % --> sat
		// Step 4 - compare two integer values -- range 8-35 -> resp rate, the other one is hr
		/**/
		try {

			analyzePatterns(aJCas);
			//createValueTypes(aJCas);
			/**	
				analyzeDoubles(aJCas);
				createValueTypes(aJCas);

				analyzePotentialBp(aJCas);
				createValueTypes(aJCas);

				analyzeIntegers(aJCas);
				createValueTypes(aJCas);

				//analyzeSpecialPatterns(aJCas);
				//createValueTypes(aJCas);
				/**/
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	public void analyzePatterns(JCas aJCas) throws CASException {
		/**
				FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
				while (iter.hasNext()) {
					Relation currRelation = (Relation) iter.next();

					// INFO: Target stands for Numeric.
					if (currRelation.getTarget() != null) {
						if (currRelation.getTarget() instanceof Numeric) {
							Numeric number = (Numeric) currRelation.getTarget();
							if (StringUtils.isEmpty(number.getConcept())) {  // make sure the concept is not set already

								Unit curUnit = null;

								if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
									curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
									    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type)).get(0); // get the first unit in the pattern
									number.setUnit(curUnit);
								}
								// INFO: adding timestamp
								ArrayList<Annotation> times = (ArrayList<Annotation>) AnnotationLibrarian
								    .getAllOverlappingAnnotationsOfType(currRelation, Timestamp.type);
								if (times.size() > 0) {
									number.setTimestamp(times.get(0));
								} else {
									times = (ArrayList<Annotation>) AnnotationLibrarian.getPreviousAnnotationsOfType(number,
									    Timestamp.type, 1);
									if (times.size() > 0) {
										if (times.get(0).getEnd() - number.getBegin() < 30) {
											number.setTimestamp(times.get(0));
										}
									}
								}
								// INFO: Anchor stands for Term with pattern
								if (currRelation.getAnchor() != null) {
									Annotation term = currRelation.getAnchor();
									String termConcept = ((Term) term).getConcept();

									// check if termConcept is set  
									if (StringUtils.isNotBlank(termConcept)) {

										if (termConcept.equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
											//if (!this.isBloodPressure(number.getVnumber.getCoveredText(), false)) {
											if (!CheckRange.isSystolicBp(number.getValue())) {
												number.setConcept("Matched term " + termConcept + " but missed value");
											}
										} else if (termConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
											if (!CheckRange.isTemperature(number.getValue())) {//number.getCoveredText(), false)) {
												number.setConcept("Matched term " + termConcept + " but missed value");

											}
										} else if (termConcept.equalsIgnoreCase(vitalTypes.Respiratory.name())) {
											if (!CheckRange.isRespRate(number.getValue())) {//(number.getCoveredText())) {
												number.setConcept("Matched term " + termConcept + " but missed value");
											}
										} else if (termConcept.equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
											if (!CheckRange.isHeartRate(number.getValue())) {//(number.getCoveredText())) {
												number.setConcept("Matched term " + termConcept + " but missed value");
											}
										}

										// there is a term in the pattern and it not discarded 
										if (StringUtils.isBlank(number.getConcept())) {
											number.setConcept(termConcept);
										}
										number.setSource("Term pattern");
									} // number gets concept as term concept
								} else // No term. Check if there is a unit

								if (curUnit != null) {
									String unitConcept = curUnit.getConcept();

									if (unitConcept.equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
										if (!CheckRange.isSystolicBp(number.getValue())) {//if (!this.isBloodPressure(number.getCoveredText(), false)) {
											number.setConcept("Matched term " + unitConcept + " but missed value");
										} else {
											number.setConcept(unitConcept);
										}
									} else if (unitConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
										if (!CheckRange.isTemperature(number.getValue())) {//(number.getCoveredText(), false)) {
											number.setConcept("Matched term " + unitConcept + " but missed value");
										} else {
											number.setConcept(unitConcept);
										}
									} else if (unitConcept.equalsIgnoreCase(vitalTypes.Respiratory.name())) {
										if (!CheckRange.isRespRate(number.getValue())) {//(number.getCoveredText())) {
											number.setConcept("Matched term " + unitConcept + " but missed value");
										}
									} else if (unitConcept.equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
										if (!CheckRange.isHeartRate(number.getValue())) {//(number.getCoveredText())) {
											number.setConcept("Matched term " + unitConcept + " but missed value");
										} else {
											number.setConcept(unitConcept);
										}
									}

									if (unitConcept.equalsIgnoreCase(vitalTypes.Weight.name())) {
										number.setConcept(unitConcept);
									}

									if (StringUtils.isBlank(number.getConcept())) {
										number.setConcept("possible: " + unitConcept);
									}
									number.setSource("Unit pattern");
								}
								// INFO:  at this time all pattern with term and all patterns with unit have been processed. 
								//The only patterns left are the ones that have a number and timestamp
							} // end if Target -- should always be the case in Relations		
						}
						else if (currRelation.getTarget() instanceof PotentialBp) {
							// mark first number as systolic and second number as diastolic
							processPotentialBp((PotentialBp) currRelation.getTarget());
						} else if (currRelation.getTarget() instanceof Range) {
							// Mark all items in the range the same
						}
					} // there is no target. If this is ever a case, the pattern is useless and will be skipped.

					else {
						log.error("Check pattern without target: " + currRelation);
					}
				}// end of while loop
				/**/

	}

	public static void analyzeSpecialPatterns(JCas aJCas) throws CASException {
		/**
		Iterator<Annotation> integers = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    IntegerNumber.class.getCanonicalName()).iterator();
		while (integers.hasNext()) {
			IntegerNumber inum = (IntegerNumber) integers.next();
			if (StringUtils.isBlank(inum.getConcept())) {
				if (AnnotationLibrarian.getAllContainingAnnotationsOfType(inum, LowerPrecisionWindow.type).size() > 0) {
					if (CheckRange.isHeartRate(inum.getValue())) {
						inum.setConcept(vitalTypes.Heart_Rate.name());
					} else if (CheckRange.isRespRate(inum.getValue())) {
						inum.setConcept(vitalTypes.Respiratory.name());
					}
				}
			}
		}
		/**/

	}

	/**
	 * Step 1: numeric in number pattern?
	 * 	-- is it in a range? 
	 *  -- is it in a potentialBp?
	 *  -> link all values together.
	 *  
	 * Steps 1: numeric in a pattern
	 *    -- is it in a pattern with anchor?  -> check the anchor instanceof match
	 *   -- is it in a pattern with unit? -> check the unit concept 
	 *   -- if term and unit concepts match, check the appropriate range
	 * 
	 * Step 2: if not in a pattern
	 * 	-- is it in HiPrecisionWindow? -> 
	 *   
	 *   
	 * @param aJCas
	 * @throws CASException
	 */
	public static void analyzeDoubles(JCas aJCas) throws CASException {
		/**
		Iterator<Annotation> doubles = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    DoubleNumber.class.getCanonicalName()).iterator();
		while (doubles.hasNext()) {
			DoubleNumber d = (DoubleNumber) doubles.next();

			// make sure that the number is not claimed. If it is, then it is safe to skip the number
			if (StringUtils.isBlank(d.getConcept())) {
				String source = "";
				Unit currUnit = null;
				String suggestedType = "";

				ArrayList<Annotation> coverPatterns = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(d,
				    Relation.type);
				ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(d,
				    HiPrecisionWindow.type);

				// FIXME

				if (coverPatterns.size() > 0) {

					for (Annotation r : coverPatterns) {
						Relation currRelation = (Relation) r;
						// check if the numeric the same as the target of the relation
						if (currRelation.getTarget() != null) {
							if (d == currRelation.getTarget()) {
								// Check if the relation has anchor
								if (currRelation.getAnchor() != null) {
									Annotation term = currRelation.getAnchor();
									if (term instanceof T_Term) {
										if (CheckRange.isTemperature(d.getValue())) {
											suggestedType = vitalTypes.Temperature.name();
										}
									} else if (term instanceof Weight_Term) {
										suggestedType = vitalTypes.Weight.name();
									} else if (term instanceof Height_Term) {
										suggestedType = vitalTypes.Height.name();
									}
								}
								source = "Term pattern";
							}
						} else {
							// need to check for unit

							//	DoubleNumber number = d;
							if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
								currUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian.getNextClosestAnnotations(d,
								    AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type))).get(0); // get the first unit in the pattern

							}
							if (currUnit != null) {
								String unitConcept = currUnit.getConcept();

								if (unitConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
									if (!CheckRange.isTemperature(d.getValue())) {
										suggestedType = "Matched term " + unitConcept + " but missed value";
									} else {
										suggestedType = vitalTypes.Temperature.name();
									}

								} else if (unitConcept.equalsIgnoreCase(vitalTypes.Weight.name())) {
									suggestedType = vitalTypes.Weight.name();
								} else if (unitConcept.equalsIgnoreCase(vitalTypes.Height.name())) {
									suggestedType = vitalTypes.Height.name();
								}

								source = "Unit pattern";
							}

						}
					}

				} else { // the number is not covered by relation
					// Check if it is in the HiPrecision window
					if (coverWindow.size() > 0) {
						if (CheckRange.isTemperature(d.getValue())) {
							suggestedType = vitalTypes.Temperature.name();
						}
					}//if the number is in the high precision window and is in the temp range, it is Temperature
				} // 

				// found all info - now set the new values

				d.setSource(source);
				d.setConcept(suggestedType);
				d.setUnit(currUnit);

				// check if the number is the first number in the range. 
				// If it is, then set the second number the same.
				ArrayList<Annotation> coverRange = (ArrayList<Annotation>)
				    AnnotationLibrarian.getAllContainingAnnotationsOfType(d, Range.type);
				if (coverRange.size() > 0) {
					for (Annotation a : coverRange) {
						Range currRange = (Range) a;
						if (d == currRange.getValue1()) {
							((Numeric) (currRange.getValue2())).setConcept(suggestedType);
						}
					}
				} // end of range

			}// the number is already claimed. So it should be skipped
		} // end of double number loop
		/**/

	}

	public static void analyzePotentialBp(JCas aJCas) throws CASException {
		/**

		Iterator<Annotation> pbps = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    PotentialBp.class.getCanonicalName()).iterator();
		while (pbps.hasNext()) {
			PotentialBp currBp = (PotentialBp) pbps.next();
			if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currBp, Relation.type).size() > 0 ||
			    AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currBp, HiPrecisionWindow.type).size() > 0) {
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
								((IntegerNumber) value1).setConcept(vitalTypes.Blood_Pressure.name());
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
									((IntegerNumber) rv1).setConcept(vitalTypes.Blood_Pressure.name());
									((IntegerNumber) rv2).setConcept(vitalTypes.Blood_Pressure.name());
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
									((IntegerNumber) value2).setConcept(vitalTypes.Blood_Pressure.name());
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
										((IntegerNumber) rv1).setConcept(vitalTypes.Blood_Pressure.name());
										((IntegerNumber) rv2).setConcept(vitalTypes.Blood_Pressure.name());
									}
								}
							}
						} // end if Range
					}
				} // end while bp loop
			}
		} // make sure it is in a relation

		}

		public static void processPotentialBp(PotentialBp currBp) {
		try {
			if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currBp, Relation.type).size() > 0 ||
			    AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currBp, HiPrecisionWindow.type).size() > 0) {
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
								((IntegerNumber) value1).setConcept(vitalTypes.Blood_Pressure.name());
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
									((IntegerNumber) rv1).setConcept(vitalTypes.Blood_Pressure.name());
									((IntegerNumber) rv2).setConcept(vitalTypes.Blood_Pressure.name());
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
									((IntegerNumber) value2).setConcept(vitalTypes.Blood_Pressure.name());
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
										((IntegerNumber) rv1).setConcept(vitalTypes.Blood_Pressure.name());
										((IntegerNumber) rv2).setConcept(vitalTypes.Blood_Pressure.name());
									}
								}
							}
						} // end if Range
					}
				}
			}
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		/**/

	}

	public static void analyzeIntegers(JCas aJCas) throws CASException {
		/**
		Iterator<Annotation> integers = (Iterator<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas,
		    IntegerNumber.class.getCanonicalName()).iterator();
		while (integers.hasNext()) {
			IntegerNumber inum = (IntegerNumber) integers.next();

			// make sure that the number is not claimed. If it is, then it is safe to skip the number
			if (StringUtils.isBlank(inum.getConcept())) {
				String source = "";
				Unit currUnit = null;
				String suggestedType = "";

				ArrayList<Annotation> coverPatterns = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(inum,
				    Relation.type);
				ArrayList<Annotation> coverWindow = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(inum,
				    HiPrecisionWindow.type);

				// FIXME

				if (coverPatterns.size() > 0) {
					for (Annotation r : coverPatterns) {
						Relation currRelation = (Relation) r;
						// check if the numeric the same as the target of the relation
						if (currRelation.getTarget() != null) {
							if (inum == currRelation.getTarget()) {
								// Check if the relation has anchor
								if (currRelation.getAnchor() != null) {
									Annotation term = currRelation.getAnchor();
									// 1
									if (term instanceof T_Term) {
										if (CheckRange.isTemperature(inum.getValue())) {
											suggestedType = vitalTypes.Temperature.name();
										}
									} // 2
									else if (term instanceof Bp_Term) {
										if (CheckRange.isSystolicBp(inum.getValue())) {
											suggestedType = vitalTypes.Blood_Pressure.name();
										}
									} // 3
									else if (term instanceof Resp_Term) {
										if (CheckRange.isRespRate(inum.getValue())) {
											suggestedType = vitalTypes.Respiratory.name();
										}
									} // 4
									else if (term instanceof Hr_Term) {
										if (CheckRange.isHeartRate(inum.getValue())) {
											suggestedType = vitalTypes.Heart_Rate.name();
										}
									} else if (term instanceof Weight_Term) {
										suggestedType = vitalTypes.Weight.name();
									} else if (term instanceof Height_Term) {
										suggestedType = vitalTypes.Height.name();
									} else if (term instanceof Pain_Term) {
										suggestedType = vitalTypes.Pain.name();
									} else if (term instanceof So2_Term) {
										if (!CheckRange.isSo2(inum.getValue())) {
											suggestedType = "Matched term So2 but missed value";
										} else {
											suggestedType = vitalTypes.SO2.name();
										}
									} else if (term instanceof Bmi_Term) {
										suggestedType = vitalTypes.BMI.name();
									}
									source = "Term pattern";
								} // the pattern has anchor - which is term
								else {
									// need to check for unit

									//	DoubleNumber number = d;
									if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type).size() > 0) {
										currUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian.getNextClosestAnnotations(inum,
										    AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation, Unit.type))).get(0); // get the first unit in the pattern

									}
									if (currUnit != null) {
										String unitConcept = currUnit.getConcept();

										if (unitConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
											if (!CheckRange.isTemperature(inum.getValue())) {
												suggestedType = "Matched term " + unitConcept + " but missed value";
											} else {
												suggestedType = vitalTypes.Temperature.name();
											}

										} else if (unitConcept.equalsIgnoreCase(vitalTypes.SO2.name())) {
											if (!CheckRange.isSo2(inum.getValue())) {
												suggestedType = "Matched term " + unitConcept + " but missed value";
											} else {
												suggestedType = vitalTypes.SO2.name();
											}

										} else if (unitConcept.equalsIgnoreCase(vitalTypes.Weight.name())) {
											suggestedType = vitalTypes.Weight.name();

										} else if (unitConcept.equalsIgnoreCase(vitalTypes.Height.name())) {
											suggestedType = vitalTypes.Height.name();
										}

										source = "Unit pattern";
									}

								}  // pattern has unit
							}// pattern has target.
						}
					}

				} else { // the number is not covered by relation
					// Check if it is in the HiPrecision window
					if (coverWindow.size() > 0) {
						//FIXME

					}//if the number is in the high precision window and is in the temp range, it is Temperature
				} // 

				// found all info - now set the new values
				if (StringUtils.isBlank(inum.getConcept())) {
					inum.setSource(source);
					inum.setConcept(suggestedType);
					inum.setUnit(currUnit);

					// check if the number is the first number in the range. 
					// If it is, then set the second number the same.
					ArrayList<Annotation> coverRange = (ArrayList<Annotation>)
					    AnnotationLibrarian.getAllContainingAnnotationsOfType(inum, Range.type);
					if (coverRange.size() > 0) {
						for (Annotation a : coverRange) {
							Range currRange = (Range) a;
							if (inum == currRange.getValue1()) {
								((Numeric) (currRange.getValue2())).setConcept(suggestedType);
							}
						}
					} // end of range
				}
			}// the number is already claimed. So it should be skipped
		} // end of double number loop
		/**/
	}

}
