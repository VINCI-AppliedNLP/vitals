package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.Hr_Prediction;
import gov.va.vinci.vitals.types.Hr_value;

public class FilterHeartRateAnnotator extends LeoBaseAnnotator {

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {

		return null;
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		ArrayList<Annotation> predictionList = (ArrayList<Annotation>) AnnotationLibrarian
		    .getAllAnnotationsOfType(aJCas,
		        Hr_Prediction.type);
		for (Annotation a : predictionList) {
			Hr_Prediction p = (Hr_Prediction) a;
			try {
				if ("0.0".equalsIgnoreCase(p.getPrediction())) {
					ArrayList<Annotation> hrAnnotations;

					hrAnnotations = (ArrayList<Annotation>) AnnotationLibrarian.getAllOverlappingAnnotationsOfType(p,
					    Hr_value.type);

					for (Annotation hr : hrAnnotations) {
						hr.removeFromIndexes(aJCas);
					}
				}
			} catch (CASException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
}
