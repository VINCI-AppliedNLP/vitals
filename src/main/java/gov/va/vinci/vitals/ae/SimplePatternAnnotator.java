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
import org.apache.uima.pear.util.StringUtil;

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
	public static java.util.regex.Pattern bpPattern = java.util.regex.Pattern
	    .compile("\\b\\d{2,3}/\\d{2,3}\\b", java.util.regex.Pattern.MULTILINE
	        | java.util.regex.Pattern.CASE_INSENSITIVE);
	public static java.util.regex.Pattern singleNumber = java.util.regex.Pattern.compile("\\b\\d{2,3}\\b",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);
	public static java.util.regex.Pattern oneDecmalNumber = java.util.regex.Pattern.compile(
	    "\\b\\d{2,3}\\.\\d\\b",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);

	public static enum vitalTypes {
		Blood_Pressure, Heart_Rate, Temperature
	};

	private static final Logger log = Logger.getLogger(LeoUtils
	    .getRuntimeClass().toString());

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		ArrayList<Annotation> annsToRemove = new ArrayList<Annotation>();

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				Relation currRelation = (Relation) iter.next();
				if (currRelation.getTarget() != null) {
					Annotation number = currRelation.getTarget();
					Annotation term = null;
					if (currRelation.getAnchor() != null) {
						term = currRelation.getAnchor();

					}
					if (term != null) {
						String pattern = ((Term) term).getPattern();
						if (StringUtils.isNotBlank(pattern)) {
							String vitalType = pattern.split("\\|")[0];
							if (vitalType.contains(vitalTypes.Blood_Pressure.name())) {
								this.addOutputAnnotation(Bp_value.class.getCanonicalName(), aJCas, number.getBegin(),
								    number.getEnd());
								annsToRemove.add(number);
							} else if (vitalType.contains(vitalTypes.Heart_Rate.name())) {
								this.addOutputAnnotation(Hr_value.class.getCanonicalName(), aJCas, number.getBegin(),
								    number.getEnd());
								annsToRemove.add(number);
							} else if (vitalType.contains(vitalTypes.Temperature.name())) {
								this.addOutputAnnotation(T_value.class.getCanonicalName(), aJCas, number.getBegin(),
								    number.getEnd());
								annsToRemove.add(number);
							}
						}
					}
				}
			}
		}// else {
		 // FIXME: now need to analyze text 
		 // For those documents that do not contain Pattern, find Indicator, mark 100 chars after and check if Numeric fall in that interval
		 //
		for (Annotation a : annsToRemove) {
			a.removeFromIndexes(aJCas);
		}
		annsToRemove = new ArrayList<Annotation>();
		FSIterator<Annotation> iterI = this.getAnnotationListForType(aJCas, Indicator.class.getCanonicalName());

		while (iterI.hasNext()) {
			Annotation indicator = iterI.next();
			int end = indicator.getEnd() + 200;
			if (end > aJCas.getDocumentText().length()) {
				end = aJCas.getDocumentText().length();
			}
			try {
				ArrayList<Annotation> numbers = (ArrayList<Annotation>) AnnotationLibrarian
				    .getAllOverlappingAnnotationsOfType(indicator.getBegin(), end,
				        aJCas, Numeric.type);
				if (numbers.size() > 0) {
					for (Annotation number : numbers) {
						if (isBloodPressure(number.getCoveredText())) {
							Annotation newAnn = this.addOutputAnnotation(Bp_value.class.getCanonicalName(), aJCas,
							    number.getBegin(),
							    number.getEnd());
							((Bp_value) newAnn).setSource("heuristics");
						} else if (isTemperature(number.getCoveredText())) {
							T_value newAnn = (T_value) this.addOutputAnnotation(T_value.class.getCanonicalName(), aJCas,
							    number.getBegin(),
							    number.getEnd());
							newAnn.setSource("heuristics");
						} else if (isPulse(number.getCoveredText())) {
							Hr_value newAnn = (Hr_value) this.addOutputAnnotation(Hr_value.class.getCanonicalName(), aJCas,
							    number.getBegin(),
							    number.getEnd());
							newAnn.setSource("heuristics");
						}

						annsToRemove.add(number);
					}// end of Number loop
				}
				for (Annotation a : annsToRemove) {
					a.removeFromIndexes(aJCas);
				}
			} catch (CASException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//}
	}

	private boolean isPulse(String text) {
		return false;
	}

	private boolean isTemperature(String text) {
		Matcher digitMatcher = oneDecmalNumber.matcher(text);
		if (digitMatcher.find()) {
			String n = text.substring(digitMatcher.start(), digitMatcher.end());
			try {
				double num = Double.parseDouble(n);
				if ((num > 32 && num < 43) || (num > 94 && num < 105))
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	private boolean isBloodPressure(String text) {
		Matcher measureMatcher = bpPattern.matcher(text);
		if (measureMatcher.find()) {
			String m = text.substring(measureMatcher.start(), measureMatcher.end());
			Matcher digitMatcher = singleNumber.matcher(m);
			if (digitMatcher.find()) {
				String n = m.substring(digitMatcher.start(), digitMatcher.end());
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

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(),
		    getAnnotatorParams());
	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {

		return null;
	}

}
