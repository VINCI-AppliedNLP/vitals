package gov.va.vinci.vitals.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.descriptors.TypeDescriptionBuilder;
import gov.va.vinci.leo.model.AnnotatorParam;
import gov.va.vinci.leo.tools.Common;
import gov.va.vinci.vitals.types.AnnotationPattern;
import gov.va.vinci.vitals.types.NumericValue;
import gov.va.vinci.vitals.types.QValue;
import gov.va.vinci.vitals.types.Range;
import gov.va.vinci.vitals.types.Relation;
import gov.va.vinci.vitals.types.RelationPattern;
import gov.va.vinci.vitals.types.Term;
import gov.va.vinci.vitals.types.Units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

public class RelationAnnotator extends LeoBaseAnnotator {
	protected static HashMap<String, String> targetTerms = new HashMap<String, String>();

	/**
	* Enumeration of the parameters this annotator uses.
	*/
	public enum Param implements AnnotatorParam {
		/**
		 * The output type annotation to create.
		 */
		OUTPUT_TYPE("outputType", false, false, "String");

		private String name;
		private Boolean required = false;
		private Boolean multiValue;
		private String type;

		private Param(String name, Boolean required, Boolean multiValue, String type) {
			this.name = name;
			this.required = required;
			this.multiValue = multiValue;
			this.type = type;
		}

		public Boolean getMultiValue() {
			return multiValue;
		}

		public String getName() {
			return name;
		}

		public Boolean getRequired() {
			return required;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * Output annotation features
	 * @author vhaslcpatteo
	 */

	public enum RelationFeatures {
		FEATURE_TERM("Term", "uima.cas.String"),  // the term snippet
		FEATURE_CONCEPT("Concept", "uima.cas.String"), // normalized concept
		FEATURE_VALUE1("Value", "uima.cas.String"),  // first numeric value
		FEATURE_VALUE2("Value2", "uima.cas.String"), // second numeric value
		FEATURE_VALUESTRING("ValueString", "uima.cas.String"), // exact string for the numeric value or range
		FEATURE_ASSESSMENT("Assessment", "uima.cas.String"), // qualitative value
		FEATURE_UNIT("Unit", "uima.cas.String"); // unit of measure if explicitly mentioned.

		public String feature;
		public String type;

		RelationFeatures(String str, String str2) {
			this.feature = str;
			this.type = str2;
		}
	}

	private static final Logger log = Logger.getLogger(Common.getRuntimeClass().toString());

	private Pattern numericPatterns;

	private void checkTemplate(JCas aJCas, AnnotationPattern relationship_pattern) {
		String termString = "";
		String strValue = "";
		String assessment = "";
		String unit = "";
		String range = "";
		String term = "";
		String concept = "";
		// if there is an anchor - it means it has a term
		if (relationship_pattern.getAnchor() != null) {
			// The pattern might overlap with multiple terms, 			
			Term anchorAnnotation = (Term) relationship_pattern.getAnchor();
			term = anchorAnnotation.getCoveredText(); // this is my concept string
			concept = getConcept(anchorAnnotation);
			if (StringUtils.isNotBlank(term)) {
				termString = normalize(term, true);
				// check the number of mappings

				ArrayList<Annotation> values = new ArrayList<Annotation>();
				ArrayList<Annotation> units = new ArrayList<Annotation>();
				ArrayList<Annotation> ranges = new ArrayList<Annotation>();
				ArrayList<Annotation> assessments = new ArrayList<Annotation>();
				try {
					values = (ArrayList<Annotation>) AnnotationLibrarian
					    .getAllCoveredAnnotationsOfType(relationship_pattern, NumericValue.type);
					units = (ArrayList<Annotation>) AnnotationLibrarian
					    .getAllCoveredAnnotationsOfType(relationship_pattern, Units.type);
					ranges = (ArrayList<Annotation>) AnnotationLibrarian
					    .getAllCoveredAnnotationsOfType(relationship_pattern, Range.type);
					assessments = (ArrayList<Annotation>) AnnotationLibrarian
					    .getAllCoveredAnnotationsOfType(relationship_pattern, QValue.type);

					HashSet<String> tempSet = new HashSet<String>();
					if (assessments.size() > 0) {
						for (Annotation ann : assessments) {
							tempSet.add(normalize(ann.getCoveredText(), false));
						}
					}
					for (String s : tempSet) {
						if (StringUtils.isBlank(assessment))
							assessment = s;
						else
							assessment = assessment + "; " + s;
					}

					if (values.size() > 0)
						strValue = values.get(0).getCoveredText();

					if (units.size() > 0)
						unit = normalize(units.get(0).getCoveredText(), true);
					if (ranges.size() > 0) {
						range = (ranges.get(0).getCoveredText()).replace("(", "")
						    .replace(")", "").replaceAll("\\s+", " ").trim();
					}
					Relation outAnnotation = null;
					if (StringUtils.isNotBlank(assessment)
					    || StringUtils.isNotBlank(strValue)
					    || StringUtils.isNotBlank(range)) {
						//// Starting building output annotation

						outAnnotation = (Relation) this.addOutputAnnotation(outputType,
						    aJCas, relationship_pattern.getBegin(), relationship_pattern.getEnd());
					} else { // exit if strValue is still blank
						return;
					}

					if (outAnnotation != null) {
						String value = "";
						String value2 = "";
						Matcher matching = numericPatterns.matcher(strValue);
						if (matching.find()) {
							value = strValue.substring(matching.start(), matching.end());
						}
						if (matching.find()) {
							value2 = strValue.substring(matching.start(), matching.end());
						}
						strValue = normalize(strValue, false);
						outAnnotation.setTerm(termString);
						outAnnotation.setValue(value);
						outAnnotation.setValue2(value2);
						outAnnotation.setValueString(strValue);
						outAnnotation.setUnit(unit);
						outAnnotation.setAssessment(assessment);
						outAnnotation.setConcept(concept);
						AnnotationLibrarian.trimAnnotation(outAnnotation);
					}
				} catch (Exception e) {
					// TODO: exception code
				}
			}
		}
	}

	private String getConcept(Term anchorAnnotation) {
		String pattern = anchorAnnotation.getPattern();
		String[] splitPattern = pattern.split("!");
		return targetTerms.get(splitPattern[0]);
	}

	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		return getLeoTypeSystemDescription("gov.va.vinci.vitals.types.Relation");
	}

	public static LeoTypeSystemDescription getLeoTypeSystemDescription(String type) {
		LeoTypeSystemDescription leoType = new LeoTypeSystemDescription();
		String relationParent = "gov.va.vinci.vitals.types.Relation";
		if (StringUtils.isBlank(type))
			type = relationParent;
		TypeDescription relationTypeDescription;
		relationTypeDescription = new TypeDescription_impl(relationParent, "", "uima.tcas.Annotation");
		for (RelationAnnotator.RelationFeatures a : RelationAnnotator.RelationFeatures.values()) {
			relationTypeDescription.addFeature(a.feature, "", a.type);
		}
		leoType.addType(relationTypeDescription);
		return leoType;
	}

	@Override
	public void initialize(UimaContext aContext, AnnotatorParam[] params)
	    throws ResourceInitializationException {
		super.initialize(aContext, params);
		String regex = "(\\d+(\\.\\d+)?)|(\\.\\d+)";
		numericPatterns = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		targetTerms.put("1", "Temperature");
		targetTerms.put("2", "Blood pressure");
		targetTerms.put("3", "Heart rate");

	}

	/**
	 * Normalization removes [,][;][=][:][multiple -][|][*]
	 * , replaces multiple whitespaces with one
	 * and sets to lower case
	 * @param concept
	 * @return
	 */

	private String normalize(String concept, boolean removeIfNumeric) {
		if (StringUtils.isNotBlank(concept)) {
			String str = concept.toLowerCase()
			    .replaceAll(",", " ")
			    .replaceAll(";", " ")
			    .replaceAll("==+", " ")
			    //.replaceAll(":", "")
			    .replaceAll("--+", " ")
			    .replaceAll("\\|", "")
			    .replaceAll("\\*", " ")
			    .replaceAll("\\s+", " ").trim();
			if (removeIfNumeric) {
				try {
					Double.parseDouble(str);
					return "";
				} catch (Exception e) {
					return str;
				}
			} else {
				return str;
			}
		} else {
			return "";
		}
	}

	/**
	 * 
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		FSIterator<Annotation> patterns = this.getAnnotationListForType(aJCas,
		    RelationPattern.class.getCanonicalName());
		while (patterns.hasNext()) {
			try {
				RelationPattern relationship_pattern = (RelationPattern) patterns.next();
				checkTemplate(aJCas, relationship_pattern);
			} catch (Exception e) {
				log.warn("Failed processing relationship patterns.");
			}
		}
	}

}
