package gov.va.vinci.vitals.ae;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

import java.util.Collection;

public class AdjustRangeAnnotator extends LeoBaseAnnotator {


	@Override
	public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

		AnnotationLibrarian.removeCoveredAnnotations(aJCas, Range.class.getCanonicalName(), false, null);

		Collection<Annotation> rangeIter = null;
		try {
			rangeIter = AnnotationLibrarian.getAllAnnotationsOfType(aJCas, Range.class.getCanonicalName(), false);
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}


		for (Annotation a: rangeIter) {
			Range r = (Range) a;
			Annotation v1 = r.getAnchor();
			Annotation v2 = r.getTarget();
			if (v1 != null) {
				r.setBegin(v1.getBegin());
				r.setValue1(v1);
			}
			if (v2 != null) {
				r.setEnd(v2.getEnd());
				r.setValue2(v2);
			}
		}

	}

}
