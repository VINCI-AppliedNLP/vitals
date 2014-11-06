package gov.va.vinci.vitals.listeners;

import java.util.List;

import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;

import gov.va.vinci.sherlock.listeners.LearningListener;
import gov.va.vinci.sherlock.tools.SherlockVector;

public class HrLearningListener extends LearningListener {

	public HrLearningListener(String validationMap, boolean isExitOnError, String[] filter) {
	  super(validationMap, isExitOnError, filter);
	  // TODO Auto-generated constructor stub
  }

	public HrLearningListener(String tYPE_FeatureVector, String string, String string2, String string3,
      String string4, String validationMap, boolean isExitOnError, String tYPE_Prediction) {
      
		super(validationMap, isExitOnError, tYPE_Prediction);
	  // TODO Auto-generated constructor stub
  }

	@Override
  public List<SherlockVector> getVectorList(JCas jcas) throws CASException {
	  // TODO Auto-generated method stub
	  return null;
  }

}
