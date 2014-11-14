package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

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

public class AdjustPotentialBpAE extends LeoBaseAnnotator {
	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(JCas aJCas) {
		try {
			super.process(aJCas);
			// Remove Numerics if overlap with time

			AnnotationLibrarian.removeCoveredAnnotations(aJCas, PotentialBp.class.getCanonicalName());

			FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, PotentialBp.class.getCanonicalName());
			while (iter.hasNext()) {
				PotentialBp pbp = (PotentialBp) iter.next();
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
