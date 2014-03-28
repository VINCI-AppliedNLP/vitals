package gov.va.vinci.vitals.ae;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

public class AnnotationFilter extends LeoBaseAnnotator {


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, Term.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, Range.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, Range.class.getCanonicalName(),
			    NumericValue.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, NumericValue.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, QValue.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, Units.class.getCanonicalName());

			AnnotationLibrarian.removeCoveredAnnotations(aJCas, MiddleStuff.class.getCanonicalName());
			
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, QValue.class.getCanonicalName(),
			    Units.class.getCanonicalName());
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, Units.class.getCanonicalName(),
			    NumericValue.class.getCanonicalName());

		}
	@Override
  public LeoTypeSystemDescription getLeoTypeSystemDescription() {
	  // TODO Auto-generated method stub
	  return null;
  }

}
