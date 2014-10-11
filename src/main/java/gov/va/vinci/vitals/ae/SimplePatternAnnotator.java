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
		Blood_Pressure, Heart_Rate, Temperature, Height, Weight, SO2, BMI, Pain;
	}

	public static java.util.regex.Pattern bpPattern = java.util.regex.Pattern.compile(
	    "\\b\\d{2,3} {0,2}/ {0,2}\\d{2,3}\\b",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern singleNumber = java.util.regex.Pattern.compile("\\b\\d{2,3}\\b",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern oneDecmalNumber = java.util.regex.Pattern.compile(
	    "\\b\\d{2,3}\\.\\d\\b", java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static java.util.regex.Pattern anyDecmalNumber = java.util.regex.Pattern.compile(
	    "\\b\\d*\\.\\d+\\b", java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);;

	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	public int rightWindow = 400;

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
						if (StringUtils.isEmpty(number.getValueType())) {
							if (isBloodPressure(number.getCoveredText())) {
								number.setValueType(vitalTypes.Blood_Pressure.name());
								number.setSource("heuristics");
							} else if (isTemperature(number.getCoveredText(), true)) {
								number.setValueType(vitalTypes.Temperature.name());
								number.setSource("heuristics");
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
			int end = indicator.getEnd() + rightWindow; // 200 is better than 150, 250 is better than 200, but 300 better than 400
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

						/** if (this.isBloodPressure(number.getCoveredText())) {
							number.setValueType(vitalTypes.Blood_Pressure.name());
						} else if (this.isTemperature(number.getCoveredText())) {
							number.setValueType(vitalTypes.Temperature.name());

						} else
						/**/
						if (this.isHeartRate(number.getCoveredText())) {
							number.setValueType(vitalTypes.Heart_Rate.name());
							number.setSource("time_pattern");
						}
					}
				}
			}
			/**else if (isPulse(number.getCoveredText())) {  -- gets additional Recall 14%, but down precision 44%
			number.setValueType(vitalTypes.Heart_Rate.name());
			number.setSource("heuristics");
			} /**/

			// Step 3: check target isHeartRate

			//	gov.va.vinci.vitals.types.Relation_Time

		}
	}

	/**
	 * Annotation type Relation can be produced as a 
	 * @param aJCas
	 * @throws CASException 
	 */
	public void analyzePatterns(JCas aJCas) throws CASException {

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		if (iter.hasNext()) {

			while (iter.hasNext()) {
				Relation currRelation = (Relation) iter.next();

				if (currRelation.getTarget() != null) { // Target stands for Numeric.
					Numeric number = (Numeric) currRelation.getTarget();
					if (StringUtils.isEmpty(number.getValueType())) {
						if (currRelation.getAnchor() != null) {  // Anchor stands for Term with pattern
							Annotation term = currRelation.getAnchor();
							String pattern = ((Term) term).getPattern();

							if (StringUtils.isNotBlank(pattern) && (StringUtils.isBlank(number.getConcept()))) {
								String vitalType = pattern.split("\\|")[0];

								if (vitalType.equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
									if (this.isBloodPressure(number.getCoveredText())) {
										number.setValueType(vitalTypes.Blood_Pressure.name());
									} else {
										number.setValueType("None");
									}
									number.setSource("pattern");
								} else if (vitalType.equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
									if (this.isHeartRate(number.getCoveredText()))
										number.setValueType(vitalTypes.Heart_Rate.name());
									else
										number.setValueType("None");
									number.setSource("pattern");
								} else if (vitalType.equalsIgnoreCase(vitalTypes.Temperature.name())) {
									if (this.isTemperature(number.getCoveredText(), false))
										number.setValueType(vitalTypes.Temperature.name());
									else
										number.setValueType("None");
									number.setSource("pattern");
								} else {
									number.setValueType(vitalType);
								}
							}
						} else // No term. Check if there is a unit
						if (((ArrayList<Annotation>) AnnotationLibrarian.getAllOverlappingAnnotationsOfType(currRelation,
						    Unit.type)).size() > 0) {

							Unit curUnit = (Unit) ((ArrayList<Annotation>) AnnotationLibrarian
							    .getAllOverlappingAnnotationsOfType(currRelation, Unit.type)).get(0); // get the first unit in the pattern
							if (StringUtils.isNotBlank(curUnit.getConcept())) {
								if (curUnit.getConcept().equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
									if (this.isBloodPressure(number.getCoveredText()))
										number.setValueType(vitalTypes.Blood_Pressure.name());
									else
										number.setValueType("None");
									number.setSource("pattern");
								} else if (curUnit.getConcept().equalsIgnoreCase(vitalTypes.Temperature.name())) {
									if (this.isTemperature(number.getCoveredText(), false))
										number.setValueType(vitalTypes.Temperature.name());
									else
										number.setValueType("None");
									number.setSource("pattern");
								} else if (curUnit.getConcept().equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
									if (this.isHeartRate(number.getCoveredText()))
										number.setValueType(vitalTypes.Heart_Rate.name());
									else
										number.setValueType("None");
									number.setSource("pattern");

								} else {
									number.setValueType(curUnit.getConcept());
									number.setSource("pattern");
								}
							}
						} else  // at this time all pattern with term and all patterns with unit have been processed. The only patterns left are the ones that have a number and timestamp
						{
						}
					} // end if Target -- should always be the case in Relations

				} // end of while loop
			}

		}  // The end of going through relations
	}

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(),
		    getAnnotatorParams());
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
	private boolean isBloodPressure(String text) {
		Matcher decimalMatcher = anyDecmalNumber.matcher(text);
		if (decimalMatcher.find()) {
			return false;
		}

		Matcher measureMatcher = bpPattern.matcher(text);
		if (measureMatcher.find()) {
			String m = text.substring(measureMatcher.start(), measureMatcher.end());
			Matcher digitMatcher = singleNumber.matcher(m);
			if (digitMatcher.find()) {
				String n = m.substring(digitMatcher.start(), digitMatcher.end());
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
		/**else {   -- this line decreased precision of BP from 98 to 60%.
		Matcher digitMatcher = singleNumber.matcher(text);
		if (digitMatcher.find()) {
			String n = text.substring(digitMatcher.start(), digitMatcher.end());
			try {
				int num = Integer.parseInt(n);
				if (num > 50 && num < 300)
					return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		}	**/
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
	 * [25-42] or [ 95 - 107]
	 * 
	 * @param text
	 * @return
	 */
	private boolean isTemperature(String text, boolean isStrict) {
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

			analyzePatterns(aJCas);
			//createValueTypes(aJCas);
			advancedHeuristics(aJCas);
			createValueTypes(aJCas);
			advancedHeuristics_Time(aJCas);

			createValueTypes(aJCas);

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
			if (StringUtils.isNotBlank(curNum.getValueType())) {

				if (curNum.getValueType().equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
					Bp_value newAnn = (Bp_value) this.addOutputAnnotation(Bp_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setValueType(curNum.getValueType());

				} else if (curNum.getValueType().equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
					Hr_value newAnn = (Hr_value) this.addOutputAnnotation(Hr_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setValueType(curNum.getValueType());
				} else if (curNum.getValueType().equalsIgnoreCase(vitalTypes.Temperature.name())) {
					T_value newAnn = (T_value) this.addOutputAnnotation(T_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setValueType(curNum.getValueType());
					newAnn.setSource(curNum.getSource());
				}
			}
		}
	}

}
