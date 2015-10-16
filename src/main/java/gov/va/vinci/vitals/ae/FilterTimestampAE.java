package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

public class FilterTimestampAE extends LeoBaseAnnotator {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.process(aJCas);
		ArrayList<Annotation> timeList = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas, Timestamp.type);
		ArrayList<Annotation> timesToKeep = new ArrayList<Annotation>();
		if (timeList.size() > 0) {
			FSIterator<Annotation> iterNums = this.getAnnotationListForType(aJCas, Output_Value.class.getCanonicalName());

			while (iterNums.hasNext()) {
				// Need to delete all Timestamp annotations that are not included into OutputType feature Timestamp
				Output_Value currOut = (Output_Value) iterNums.next();
				if (currOut.getTimestamp() != null) {
					if (currOut.getTimestamp() instanceof Timestamp) {
						timesToKeep.add(currOut.getTimestamp());
					}
				}
			}
		}

		checkingLoop: for (Annotation t : timeList) {
			toKeep: for (Annotation k : timesToKeep) {
				if (t == k) {
					continue checkingLoop;
					// moving to the next timestamp
				}
			}
			t.removeFromIndexes(aJCas);
		}
	}

	public static class Param extends LeoBaseAnnotator.Param {
		/** No additional parameters **/
	}

}
