package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.ConfigurationParameter;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.tools.ConfigurationParameterImpl;
import gov.va.vinci.sherlock.ae.BaseFeatureVectorAnnotator;
import gov.va.vinci.sherlock.ae.AmbientInputFeatureVectorAE.StringToken;
import gov.va.vinci.vitals.types.*;

/**
 * 
 * @author vhaslcpatteo
 *  The planned feature vector will have all tokens before 
 */
public class HrVectorAnnotator extends BaseFeatureVectorAnnotator {

	/**
	 * Static character types
	 */
	public static final int TK_LETTER = 0;
	public static final int TK_NUMBER = 1;
	public static final int TK_WHITESPACE = 2;
	public static final int TK_PUNCTUATION = 3;
	public static final int TK_NEWLINE = 4;
	public static final int TK_CONTROL = 5;
	public static final int TK_WORD = 6;
	public static final int TK_UNKNOWN = -1;

	public static java.util.regex.Pattern singleNumber = java.util.regex.Pattern.compile("\\d+",
	    java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.CASE_INSENSITIVE);
	Pattern sentenceBoundary = Pattern.compile("[.!?]\\s+");
	Pattern lineBreak = Pattern.compile("(\\r\\n|\\n)");
	Pattern punctuation = Pattern.compile("(\\[|\\]|\\(|\\)|,|:|\\*|--+)");
	char[] docText = null;

	/**/
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		try {
			initialize(aContext, this.getAnnotatorParams());
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}

		if (aContext.getConfigParameterValue(Param.KEY_FEATURE_PARAM.getName()) != null) {
			keysFeature = (String) aContext.getConfigParameterValue(Param.KEY_FEATURE_PARAM.getName());
		}//if

		if (aContext.getConfigParameterValue(Param.VALUE_FEATURE_PARAM.getName()) != null) {
			valuesFeature = (String) aContext.getConfigParameterValue(Param.VALUE_FEATURE_PARAM.getName());
		}//if
	}

	/**/
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		int refStType = Hr_value.type;//HRValue.type;
		int sysType = Hr_value.type;

		Collection<Annotation> systemTypes = AnnotationLibrarian.getAllAnnotationsOfType(aJCas, sysType);
		Collection<Annotation> refStTypes = AnnotationLibrarian.getAllAnnotationsOfType(aJCas, refStType);
		// All system annotations + all refst annotations not overlapping with system annotations.
		try {
			for (Annotation sys : systemTypes) {
				HashMap<String, String> featureMap = null;
				featureMap = getFeatureVector(sys, aJCas);

				if (featureMap.size() > 0) {
					Annotation o = addFeatureVectorAnnotation(aJCas,
					    sys.getBegin(), sys.getEnd(), featureMap);

					Feature conFeature = o.getType().getFeatureByBaseName("context");
					o.setFeatureValue(conFeature, sys);
				}
			}//for
		} catch (CASException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	/**
	 * Add the Feature Vector annotation of the output type specified after the feature
	 * vector has been extracted from the Annotation list.
	 * @param aJCas 
	 *
	 * @param types
	 * 		List of annotation types used to create the feature vector
	 * @return
	 * 		Feature Vector Annotation added to the CAS
	 * @throws AnalysisEngineProcessException
	 * @throws CASException 
	 */
	protected HashMap<String, String> getFeatureVector(Annotation currAnnotation, JCas aJCas)
	    throws AnalysisEngineProcessException, CASException {

		HashMap<String, String> vector = new HashMap<String, String>();
		// Features:
		// INFO: Distance to Indicator and indicator text
		ArrayList<Annotation> indicatorList = (ArrayList<Annotation>) AnnotationLibrarian
		    .getPreviousAnnotationsOfType(currAnnotation, Indicator.type, 1);
		Annotation closestIndicator = null;
		if (indicatorList.size() > 0) {
			closestIndicator = indicatorList.get(0);
			vector.put("indicator_Distance",
			    Integer.toString(currAnnotation.getBegin() - closestIndicator.getEnd()));

			// tokenize indicator covered text
			ArrayList<StringToken> tokens = tokenize(closestIndicator.getCoveredText().trim().toLowerCase());
			for (StringToken st : tokens) {
				if (st.type == TK_WORD || st.type == TK_NUMBER) {
					vector.put("i_" + st.token.toString().toLowerCase().trim(), "1");
				}
			}//for

		}

		// INFO: Distance to Indicator and indicator text
		ArrayList<Annotation> termList = (ArrayList<Annotation>) AnnotationLibrarian
		    .getPreviousAnnotationsOfType(currAnnotation, Term.type, 1);
		Annotation closestTerm = null;

		int closestNonHrTerm = 99999;
		for (Annotation t : termList) {
			if ((currAnnotation.getBegin() - t.getEnd()) < closestNonHrTerm) {
				closestNonHrTerm = currAnnotation.getBegin() - t.getEnd();
			}
			if ("Heart_rate".equalsIgnoreCase(((Term) t).getConcept())) {
				closestTerm = t;
				vector.put("term_hr_Distance", Integer.toString(currAnnotation.getBegin() - closestTerm.getEnd()));

				// tokenize indicator covered text
				ArrayList<StringToken> tokens = tokenize(closestTerm.getCoveredText().trim().toLowerCase());
				for (StringToken st : tokens) {
					if (st.type == TK_WORD || st.type == TK_NUMBER) {
						vector.put("term_hr_" + st.token.toString().toLowerCase().trim(), "1");
					}
				}//for

			} else {

				ArrayList<StringToken> tokens = tokenize(t.getCoveredText().trim().toLowerCase());
				for (StringToken st : tokens) {
					if (st.type == TK_WORD || st.type == TK_NUMBER) {
						vector.put("term_not_hr_" + st.token.toString().toLowerCase().trim(), "1");
					}
				}//for				
			}
		}

		// INFO: Numeric value and covered text.
		String numberString = currAnnotation.getCoveredText();
		vector.put("text", numberString);
		Matcher digitMatcher = singleNumber.matcher(numberString);
		if (digitMatcher.find()) {
			String n = numberString.substring(digitMatcher.start(), digitMatcher.end());
			n = n.trim();
			try {
				int num = Integer.parseInt(n);
				vector.put("value", Integer.toString(num));
			} catch (Exception e) {
				// the number did not parse. Let's skip it for now.
			}
		} else {
			// there are no numbers found. Let's skip it for now.
		}

		// 3. Number of Temperature annotations between the currentAnnotation and the indicator
		if (closestIndicator != null) {
			ArrayList<Annotation> tList = (ArrayList<Annotation>) AnnotationLibrarian
			    .getAllCoveredAnnotationsOfType(closestIndicator.getBegin(), currAnnotation.getBegin(), aJCas,
			        T_value.type);
			vector.put("tCount", Integer.toString(tList.size()));
		

		// 4. Number of Temperature annotations between the currentAnnotation and the indicator
		
			ArrayList<Annotation> bList = (ArrayList<Annotation>) AnnotationLibrarian
			    .getAllCoveredAnnotationsOfType(closestIndicator.getBegin(), currAnnotation.getBegin(), aJCas,
			        Bp_value.type);
			vector.put("BpCount", Integer.toString(bList.size()));
		

		// INFO:  Number of Temperature annotations between the currentAnnotation and the indicator
		
			ArrayList<Annotation> hList = (ArrayList<Annotation>) AnnotationLibrarian
			    .getAllCoveredAnnotationsOfType(closestIndicator.getBegin(), currAnnotation.getBegin(), aJCas,
			        Hr_value.type);
			vector.put("HrCount", Integer.toString(hList.size()));

			String spanText = aJCas.getDocumentText().substring(
			    closestIndicator.getBegin(), currAnnotation.getBegin());
			//Find the number of sentence boundaries in the span        
			Matcher sentenceMatcher = sentenceBoundary.matcher(spanText);
			int sentenceBoundaries = 0;
			while (sentenceMatcher.find())
				sentenceBoundaries++;
			vector.put("sentenceBoundaries", Integer.toString(sentenceBoundaries));

			//Find the number of line breaks       
			Matcher lineMatcher = lineBreak.matcher(spanText);
			int lineBreaks = 0;
			while (lineMatcher.find())
				lineBreaks++;
			vector.put("lineBreaks", Integer.toString(lineBreaks));
			//Find the number of punctuation        
			Matcher puncMatcher = punctuation.matcher(spanText);
			int puncs = 0;
			while (puncMatcher.find()) {
				puncs++;
			}
			vector.put("punctuation", Integer.toString(puncs));
			}
			
		// tokens around the annotation
		int begin = currAnnotation.getBegin()-1000;
		int end = currAnnotation.getBegin() + 1000;
		if(begin < 0) begin = 0;
		if(end > aJCas.getDocumentText().length()) end = aJCas.getDocumentText().length();
		
		String window = aJCas.getDocumentText().substring(begin, end);
		ArrayList<StringToken> tokens = tokenize(window.trim().toLowerCase());
		for (StringToken st : tokens) {
			if (st.type == TK_WORD || st.type == TK_NUMBER) {
				vector.put(st.token.toString().toLowerCase().trim(), "1");
			}
		}//for
		
			
		return vector;
	}//getFeatureVector method

	/**
	 * Add the vector annotation and return a reference to the annotation added
	 * @param jcas
	 * @param begin
	 * @param end
	 * @param vector
	 * @return
	 * @throws AnalysisEngineProcessException
	 */
	protected Annotation addFeatureVectorAnnotation(JCas jcas, int begin, int end,
	    HashMap<String, String> vector) throws AnalysisEngineProcessException {
		int size = vector.size();
		StringArray keys = new StringArray(jcas, size);
		StringArray values = new StringArray(jcas, size);
		keys.copyFromArray(vector.keySet().toArray(new String[size]), 0, 0, size);
		values.copyFromArray(vector.values().toArray(new String[size]), 0, 0, size);
		return this.addFeatureVectorAnnotation(jcas, begin, end, keys, values);
	}

	/**
	 * Tokenize the string of text into tokens of non-whitespace strings and their types.
	 * 
	 * @param text
	 * 		Text to be tokenized
	 * @return
	 * 		ArrayList of StringTokens
	 * @throws AnalysisEngineProcessException
	 */
	public ArrayList<StringToken> tokenize(String text) throws AnalysisEngineProcessException {
		if (StringUtils.isBlank(text))
			throw new AnalysisEngineProcessException("Empty Text String cannot be tokenized", null);
		ArrayList<StringToken> tokens = new ArrayList<StringToken>();
		StringToken token = null;
		int i = 0;
		for (char c : text.toCharArray()) {
			int currType = characterType(c);
			switch (currType) {
				case TK_LETTER:
				case TK_NUMBER:
					if (token == null || (token != null && token.type != TK_WORD)) {
						if (token != null)
							tokens.add(token);
						token = new StringToken();
						token.type = TK_WORD;
						token.pos = i;
					}
					token.token.append(c);
					break;
				case TK_PUNCTUATION:
					if (token == null || (token != null && token.type != TK_PUNCTUATION)) {
						if (token != null)
							tokens.add(token);
						token = new StringToken();
						token.type = TK_PUNCTUATION;
						token.pos = i;
					}
					token.token.append(c);
					break;
				default:
					if (token != null) {
						tokens.add(token);
						token = null;
					}//if 
			}//switch
			i++;
		}//for
		if (token != null)
			tokens.add(token);
		return tokens;
	}//tokenize method

	/**
	 * Given a character c return the type definition from the 
	 * list of public static type definitions in this class.
	 * 
	 * @param c
	 * @return type definition for the character c
	 */
	public static int characterType(char c) {
		switch (Character.getType(c)) {
		//letters
			case Character.UPPERCASE_LETTER:
			case Character.LOWERCASE_LETTER:
			case Character.TITLECASE_LETTER:
			case Character.MODIFIER_LETTER:
			case Character.OTHER_LETTER:
			case Character.NON_SPACING_MARK:
			case Character.ENCLOSING_MARK:
			case Character.COMBINING_SPACING_MARK:
			case Character.PRIVATE_USE:
			case Character.SURROGATE:
			case Character.MODIFIER_SYMBOL:
				return TK_LETTER;
				//numbers
			case Character.DECIMAL_DIGIT_NUMBER:
			case Character.LETTER_NUMBER:
			case Character.OTHER_NUMBER:
				return TK_NUMBER;
				//Regular Whitespace
			case Character.SPACE_SEPARATOR:
				return TK_WHITESPACE;
				//Punctuation
			case Character.DASH_PUNCTUATION:
			case Character.START_PUNCTUATION:
			case Character.END_PUNCTUATION:
			case Character.OTHER_PUNCTUATION:
				return TK_PUNCTUATION;
				//Simple NewLine
			case Character.LINE_SEPARATOR:
			case Character.PARAGRAPH_SEPARATOR:
				return TK_NEWLINE;
				//Other types of "control" characters
			case Character.CONTROL:
				if (c == '\n' || c == '\r')
					return TK_NEWLINE;
				if (Character.isWhitespace(c))  //Tab char is a "Control" character
					return TK_WHITESPACE;
				return TK_CONTROL;
			default:
				if (Character.isWhitespace(c)) {
					return TK_WHITESPACE;
				}//if
				return TK_UNKNOWN;
		}//switch
	}//characterType method

	/**
	 * Storing a string token as well as the type of token
	 * 
	 * @author thomasginter
	 */
	public class StringToken {
		public int type = TK_UNKNOWN;
		public int pos = 0;
		public StringBuilder token = new StringBuilder();
	}//StringToken class

}
