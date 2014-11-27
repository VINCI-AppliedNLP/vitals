package gov.va.vinci.vitals.ae;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;

public class VitalSignsExtractorAE extends LeoBaseAnnotator {

	@Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
	  // TODO Auto-generated method stub
	  super.process(aJCas);
	  ExtractService_Temperature tempAnalyzer = new ExtractService_Temperature();
	  tempAnalyzer.analyzePatterns(aJCas);
  }

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
