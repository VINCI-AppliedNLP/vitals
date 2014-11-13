package gov.va.vinci.vitals.ae;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

public class NumericPatternAnnotator extends LeoBaseAnnotator {
	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		FSIterator<Annotation> rangeIter = this.getAnnotationListForType(aJCas, Range.class.getCanonicalName());

		while (rangeIter.hasNext()) {
			Range r = (Range) rangeIter.next();
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
