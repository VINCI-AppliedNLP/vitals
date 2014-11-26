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

public class AnalyzeNumbersAE extends LeoBaseAnnotator {
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
			FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Numeric.class.getCanonicalName());

			while (iter.hasNext()) {

				Numeric currNum = (Numeric) iter.next();
				String numberString = currNum.getCoveredText().trim().toLowerCase();

				if (currNum instanceof IntegerNumber) {
					currNum.setZero_decimal(false);
					currNum.setDecimal(false);
					currNum.setInteger(true);
					currNum.setValue(Integer.parseInt(numberString));
				}
				else if (currNum instanceof DoubleNumber) {
					currNum.setZero_decimal(false);
					currNum.setDecimal(true);
					currNum.setInteger(false);
					currNum.setValue(Double.parseDouble(numberString));

				}
			}

		} catch (AnalysisEngineProcessException ex) {
			// TODO Auto-generated catch block
			log.error(ex.getStackTrace());
		}

	}
}
