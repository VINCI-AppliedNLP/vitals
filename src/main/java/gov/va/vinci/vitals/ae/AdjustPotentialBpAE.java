package gov.va.vinci.vitals.ae;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.vitals.types.*;

import java.util.Collection;

public class AdjustPotentialBpAE extends LeoBaseAnnotator {
	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	@Override
	public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
		try {

			AnnotationLibrarian.removeCoveredAnnotations(aJCas, PotentialBp.class.getCanonicalName(), false, null);

			Collection<Annotation> iter = null;
			try {
				iter = AnnotationLibrarian.getAllAnnotationsOfType(aJCas, PotentialBp.class.getCanonicalName(), false);
			} catch (CASException e) {
				throw new AnalysisEngineProcessException(e);
			}

			for (Annotation a: iter) {
				PotentialBp pbp = (PotentialBp)a;
				if (pbp.getAnchor() != null) {
					pbp.setBegin(pbp.getAnchor().getBegin());
				}
				if (pbp.getTarget() != null) {
					pbp.setEnd(pbp.getTarget().getEnd());
				}
			}
		} catch (AnalysisEngineProcessException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

}
