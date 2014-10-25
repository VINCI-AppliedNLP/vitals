package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.regex.Matcher;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * This is a custom annotator that reviews Relation annotations, gets the anchor
 * and target of the Relation annotation. If the anchor pattern has Pattern
 * feature as BP, replace Numeric annotation with the Bp_value annotation If the
 * anchor pattern has Pattern feature as HR, replace Numeric annotation with the
 * Hr_value annotation If the anchor pattern has Pattern feature as Temp,
 * replace Numeric annotation with the T_value annotation
 * 
 * @author olga.patterson@utah.edu
 * 
 */
public class SimplePatternAnnotator extends LeoBaseAnnotator {

	public static enum vitalTypes {
		Blood_Pressure, //1
		Heart_Rate,     //2
		Temperature,    //3
		Height,         //4
		Weight,         //5
		SO2,            //6
		BMI,            //7
		Pain,           //8
		Age;            //9
	}

	public static java.util.regex.Pattern bpPattern = java.util.regex.Pattern.compile(
	    "\\d{2,3}('?s)? {0,2}(/|over) {0,2}\\d{2,3}('?s)?",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern singleNumber = java.util.regex.Pattern.compile("\\d{2,3}",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern oneDecmalNumber = java.util.regex.Pattern.compile(
	    "\\b\\d{2,3}\\.\\d\\b", java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern anyDecmalNumber = java.util.regex.Pattern.compile(
	    "\\d*\\.\\d+", java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);;

	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	public int rightWindow = 400;

	/**
	 * INFO: analyzePatterns
	 * Annotation type Relation can be produced as a 
	 * @param aJCas
	 * @throws CASException 
	 * 
	 *  1) if pattern contains <Target: Numeric> and <Anchor: Term> , assign term.concept to numeric.concept  
	 *  2) if pattern contains <Target: Numeric> and <Unit> after <Indicator>, assign unit.concept to numeric.concept
	 *  
	 */
	public void analyzePatterns(JCas aJCas) throws CASException {

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		while (iter.hasNext()) {
			Relation currRelation = (Relation) iter.next();

			// INFO: Target stands for Numeric.
			if (currRelation.getTarget() != null) {
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
							if (times.get(0).getEnd() - number.getBegin() < 50) {
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
								if (!this.isBloodPressure(number.getCoveredText(), false)) {
									number.setConcept("Matched term " + termConcept + " but missed value");
								}
							} else if (termConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
								if (!this.isTemperature(number.getCoveredText(), false)) {
									number.setConcept("Matched term " + termConcept + " but missed value");

								}
							} else if (termConcept.equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
								if (!this.isHeartRate(number.getCoveredText())) {
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
							if (!this.isBloodPressure(number.getCoveredText(), false)) {
								number.setConcept("Matched term " + unitConcept + " but missed value");
							}
						} else if (unitConcept.equalsIgnoreCase(vitalTypes.Temperature.name())) {
							if (!this.isTemperature(number.getCoveredText(), false)) {
								number.setConcept("Matched term " + unitConcept + " but missed value");
							}
						} else if (unitConcept.equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
							if (!this.isHeartRate(number.getCoveredText())) {
								number.setConcept("Matched term " + unitConcept + " but missed value");
							}
						}

						if (StringUtils.isBlank(number.getConcept())) {
							number.setConcept(unitConcept);
						}
						number.setSource("Unit pattern");
					}
					// INFO:  at this time all pattern with term and all patterns with unit have been processed. 
					//The only patterns left are the ones that have a number and timestamp
				} // end if Target -- should always be the case in Relations					
			} // there is no target. If this is ever a case, the pattern is useless and will be skipped.
			else {
				log.error("Check pattern without target: " + currRelation);
			}
		}// end of while loop
	}

	/**
	 * Annotates Heart rate  when a time pattern is within right window from the indicator
	 * 
	 * @param aJCas
	 * @throws AnalysisEngineProcessException
	 */
	public void advancedHeuristics_Hr(JCas aJCas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iterI = this.getAnnotationListForType(aJCas, Indicator.class.getCanonicalName());

		while (iterI.hasNext()) {
			Annotation indicator = iterI.next();
			int end = indicator.getEnd() + rightWindow;
			if (end > aJCas.getDocumentText().length()) {
				end = aJCas.getDocumentText().length();
			}
			try {
				ArrayList<Annotation> numbers = (ArrayList<Annotation>) AnnotationLibrarian
				    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Numeric.type);
				if (numbers.size() > 0) {
					for (Annotation n : numbers) {

						Numeric number = (Numeric) n;
						if (StringUtils.isEmpty(number.getConcept())) {
							if (isHeartRate(number.getCoveredText())) {
								/**/
								if (AnnotationLibrarian
								    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Bp_value.type)
								    .size() > 0
								    && AnnotationLibrarian
								        .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, T_value.type)
								        .size() > 0) {
									if (StringUtils.isBlank(number.getConcept())) {
										number.setConcept(vitalTypes.Heart_Rate.name());
									}
								}
							}
							number.setSource("advancedHeuristics Hr");
							// INFO: adding timestamp
							ArrayList<Annotation> times = (ArrayList<Annotation>) AnnotationLibrarian
							    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Timestamp.type);
							if (times.size() > 0) {
								try {
									number.setTimestamp(((ArrayList<Annotation>) AnnotationLibrarian
									    .getPreviousClosestAnnotations(number, times)).get(0));
								} catch (Exception a) {
									number.setTimestamp(times.get(0));
								}
							}
						}// end of Number loop
					}
				}
			} catch (CASException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param aJCas
	 * @throws AnalysisEngineProcessException
	 */
	public void advancedHeuristics(JCas aJCas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iterI = this.getAnnotationListForType(aJCas, Indicator.class.getCanonicalName());

		while (iterI.hasNext()) {
			Annotation indicator = iterI.next();
			int end = indicator.getEnd() + rightWindow; // 200 is better than 150, 250 is better than 200, but 300 better than 400
			if (end > aJCas.getDocumentText().length()) {
				end = aJCas.getDocumentText().length();
			}
			try {
				ArrayList<Annotation> numbers = (ArrayList<Annotation>) AnnotationLibrarian
				    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Numeric.type);

				if (numbers.size() > 0) {
					for (Annotation n : numbers) {
						Numeric number = (Numeric) n;
						if (StringUtils.isEmpty(number.getConcept())) {
							/**/
							if (isBloodPressure(number.getCoveredText(), true)) {
								number.setConcept(vitalTypes.Blood_Pressure.name());
							} else if (isTemperature(number.getCoveredText(), true)) {
								number.setConcept(vitalTypes.Temperature.name());
							}
							/**
							else if (isHeartRate(number.getCoveredText())) {

								if (AnnotationLibrarian
								    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Bp_value.type)
								    .size() > 0
								    && AnnotationLibrarian.getAllOverlappingAnnotationsOfType(indicator.getBegin(), end,
								        aJCas, T_value.type).size() > 0) {
									number.setConcept(vitalTypes.Heart_Rate.name());
								}

							} // end if heart rate
							/**/
							// INFO: adding timestamp
							ArrayList<Annotation> times = (ArrayList<Annotation>) AnnotationLibrarian
							    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Timestamp.type);
							if (times.size() > 0) {
								try {
									number.setTimestamp(((ArrayList<Annotation>) AnnotationLibrarian
									    .getPreviousClosestAnnotations(number, times)).get(0));
								} catch (Exception a) {
									number.setTimestamp(times.get(0));
								}
							}
							number.setSource("advancedHeuristics");
						}// end of Number loop
					}
				}
			} catch (CASException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param aJCas
	 * @throws AnalysisEngineProcessException 
	 * @throws CASException 
	 */
	public void advancedHeuristics_Time(JCas aJCas) throws AnalysisEngineProcessException, CASException {
		// Step 1: remove allRelation_time if overlaps with any of the value types but keep Numeric
		// This should have been achieved in createValueTypes

		AnnotationLibrarian.removeOverlappingAnnotations(aJCas, Bp_value.class.getCanonicalName(),
		    Relation_Time.class.getCanonicalName());
		AnnotationLibrarian.removeOverlappingAnnotations(aJCas, Hr_value.class.getCanonicalName(),
		    Relation_Time.class.getCanonicalName());
		AnnotationLibrarian.removeOverlappingAnnotations(aJCas, T_value.class.getCanonicalName(),
		    Relation_Time.class.getCanonicalName());

		// Step 2: iterate through relation_time if it is within 350 chars after indicator
		FSIterator<Annotation> iterI = this.getAnnotationListForType(aJCas, Indicator.class.getCanonicalName());

		while (iterI.hasNext()) {
			Annotation indicator = iterI.next();
			int end = indicator.getEnd() + rightWindow + 1000; // 200 is better than 150, 250 is better than 200, but 300 better than 400
			if (end > aJCas.getDocumentText().length()) {
				end = aJCas.getDocumentText().length();
			}

			ArrayList<Annotation> relations = (ArrayList<Annotation>) AnnotationLibrarian
			    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end, aJCas, Relation_Time.type);

			if (relations.size() > 0) {
				for (Annotation r : relations) {
					Relation_Time currRelation = (Relation_Time) r;

					if (currRelation.getTarget() != null) { // Target stands for Numeric.
						Numeric number = (Numeric) currRelation.getTarget();

						if (StringUtils.isBlank(number.getConcept())) {
							/**/
							if (this.isBloodPressure(number.getCoveredText(), true)) {
								number.setConcept(vitalTypes.Blood_Pressure.name());

							} else if (this.isTemperature(number.getCoveredText(), true)) {
								number.setConcept(vitalTypes.Temperature.name());

							}
							/** else if (this.isHeartRate(number.getCoveredText())) {
							number.setConcept(vitalTypes.Heart_Rate.name());
							}/**/

							number.setSource("time_pattern");

						}

						// INFO: adding timestamp
						ArrayList<Annotation> times = (ArrayList<Annotation>) AnnotationLibrarian
						    .getAllOverlappingAnnotationsOfType(currRelation, Timestamp.type);
						if (times.size() > 0) {
							number.setTimestamp(times.get(0));
						}
					}
				}
			}

			// Step 3: check target isHeartRate

			//	gov.va.vinci.vitals.types.Relation_Time

		}
	}

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(), getAnnotatorParams());
	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {

		return null;
	}

	/**
	 * Blood pressure is either a single 2-3 digit whole number 
	 * or two 2-3 digit numbers with a slash
	 * @param text
	 * @return
	 */
	private boolean isBloodPressure(String text, boolean isStrict) {
		Matcher decimalMatcher = anyDecmalNumber.matcher(text);
		if (decimalMatcher.find()) {
			/**/
			if (text.endsWith(".0")) { // FIXME: checking if it helps  -- does not change anything.
			} else
				/**/
				return false;
		}
		if (isStrict) { // FIXME: check if it helps
			Matcher measureMatcher = bpPattern.matcher(text);
			if (measureMatcher.find()) {
				String m = text.substring(measureMatcher.start(), measureMatcher.end());
				Matcher digitMatcher = singleNumber.matcher(m);
				if (digitMatcher.find()) {
					String n = m.substring(digitMatcher.start(), digitMatcher.end());
					n = n.trim();
					try {
						int num = Integer.parseInt(n);
						if (num > 70 && num < 300)
							return true;
					} catch (Exception e) {
						return false;
					}
				}
			}
		} else {
			Matcher digitMatcher = singleNumber.matcher(text);
			if (digitMatcher.find()) {
				String n = text.substring(digitMatcher.start(), digitMatcher.end());
				n = n.trim();
				try {
					int num = Integer.parseInt(n);
					if (num > 50 && num < 300)
						return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Pulse or Heart rate is a 2-3 digit whole number in range [26 - 320]
	 * @param text
	 * @return
	 */
	private boolean isHeartRate(String text) {
		Matcher decimalMatcher = anyDecmalNumber.matcher(text);
		if (decimalMatcher.find()) {
			/** if (text.endsWith(".0")) { // FIXME: checking if it helps -- it actually hurts
			} else
			/**/
			return false;

		}

		Matcher digitMatcher = singleNumber.matcher(text);
		if (digitMatcher.find()) {
			String n = text.substring(digitMatcher.start(), digitMatcher.end());
			n = n.trim();
			try {
				int num = Integer.parseInt(n);
				if (num > 26 && num < 320)
					return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Temperature is a 2-3 digit number with possible decimal in range
	 * [35-42] or [ 95 - 107]
	 * 
	 * @param text
	 * @return
	 */
	private boolean isTemperature(String text, boolean isStrict) {
		// String requires a decimal point
		if (isStrict) {
			Matcher digitMatcher = oneDecmalNumber.matcher(text);
			if (digitMatcher.find()) {
				String n = text.substring(digitMatcher.start(), digitMatcher.end());
				n = n.trim();
				try {
					double num = Double.parseDouble(n);
					if ((num > 34 && num < 44) || (num > 94 && num < 107))
						return true;
					else
						return false;
				} catch (Exception e) {
					return false;
				}
			}
		} else {
			// Not strict does not require a decimal point. Only range
			double num = 0.0;
			try {
				num = Double.parseDouble(text);
			} catch (Exception e) {
				return false;
			}
			if ((num > 34 && num < 44) || (num > 94 && num < 107))
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public void process(JCas aJCas) {
		try {
			super.process(aJCas);

			/**
			 * INFO: analyzePatterns: Term:Numeric pattern for all vital type.  Numeric-Unit pattern for all vitals
			 * 
			 */
			analyzePatterns(aJCas);
			createValueTypes(aJCas);

			/**/
			/**
			 * INFO: advancedHeuristics : Numerics between Indicator+RightWindow for BP and T
			 */
			advancedHeuristics(aJCas);
			createValueTypes(aJCas);
			/**/
			/**
			 * INFO: advancedHeuristics_Time: Numeric-Timestamp pattern after Indicator+RightWindow+1000 for BP and T
			 */
			advancedHeuristics_Time(aJCas);
			createValueTypes(aJCas);
			/**/

			/**
			 * INFO: advancedHeuristics_Hr: Numbers between Indicator+rightWindow for HR if BP or T are present
			 */
			advancedHeuristics_Hr(aJCas);
			createValueTypes(aJCas);
			/**/
		} catch (AnalysisEngineProcessException ex1) {
			log.error(ex1.getMessage() + ex1.getStackTrace());
		} catch (CASException ex) {
			log.error(ex.getMessage() + ex.getStackTrace());
		}

	}

	private void createValueTypes(JCas aJCas) throws AnalysisEngineProcessException {
		FSIterator<Annotation> iterNums = this.getAnnotationListForType(aJCas, Numeric.class.getCanonicalName());

		while (iterNums.hasNext()) {
			Numeric curNum = (Numeric) iterNums.next();
			if (StringUtils.isNotBlank(curNum.getConcept())) {

				if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
					Bp_value newAnn = (Bp_value) this.addOutputAnnotation(Bp_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
					Hr_value newAnn = (Hr_value) this.addOutputAnnotation(Hr_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Temperature.name())) {
					T_value newAnn = (T_value) this.addOutputAnnotation(T_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
				}
			}
		}
	}
}
