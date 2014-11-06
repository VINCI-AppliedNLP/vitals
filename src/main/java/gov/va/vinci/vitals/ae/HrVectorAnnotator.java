package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import gov.va.vinci.vitals.types.*;
import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.sherlock.ae.BaseFeatureVectorAnnotator;
import gov.va.vinci.vitals.types.Hr_value;

/**
 * 
 * @author vhaslcpatteo
 *  The planned feature vector will have all tokens before 
 */
public class HrVectorAnnotator extends BaseFeatureVectorAnnotator {

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
		// 1: Distance to Indicator
		ArrayList<Annotation> indicatorList = (ArrayList) AnnotationLibrarian.getPreviousAnnotationsOfType(
		    currAnnotation, Indicator.type, 1);
		if (indicatorList.size() > 0) {
			Annotation closestIndicator = indicatorList.get(0);
			vector.put("iDistance", "" + (currAnnotation.getBegin() - closestIndicator.getEnd()));
		}

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

}
