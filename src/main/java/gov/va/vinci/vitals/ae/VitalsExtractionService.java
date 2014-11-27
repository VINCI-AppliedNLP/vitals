package gov.va.vinci.vitals.ae;

import java.util.Iterator;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public abstract class VitalsExtractionService {
	public static enum vitalTypes {
		NotIt_Term,
		Blood_Pressure, //1
		Heart_Rate,     //2
		Systolic,
		Diastolic,
		Temperature,    //3
		Height,         //4
		Weight,         //5
		SO2,            //6
		BMI,            //7
		Pain,           //8
		Respiratory,   //9
		Age;            //10
	}

	public static boolean inRange(double num, double[][] ranges) {

		for (double[] range : ranges) {
			if ((num > range[0] && num < range[1]))
				return true;
		}
		return true;
	}

	static class CheckRange {
		public static boolean isSo2(double num) {
			if ((num > 50 && num < 101))
				return true;
			else
				return false;
		}

		public static boolean isRespRate(Double num) {
			if ((num > 8 && num < 45))
				return true;
			else
				return false;
		}

		public static boolean isHeartRate(Double num) {
			if ((num > 30 && num < 150))
				return true;
			else
				return false;
		}

		public static boolean isTemperature(Double num) {
			if ((num > 34 && num < 44) || (num > 94 && num < 107))
				return true;
			else
				return false;
		}

		public static boolean isSystolicBp(Double num) {
			if (num > 29 && num < 220)
				return true;
			else
				return false;
		}

		public static boolean isDiastolicBp(Double num) {
			if (num > 14 && num < 200)
				return true;
			else
				return false;
		}

		public static boolean isPain(double num) {
			if (num >= 0 && num < 11)
				return true;
			else
				return false;
		}
	}

	public static void createValueTypes(JCas aJCas) throws AnalysisEngineProcessException, CASException {
		Iterator<Annotation> iterNums = AnnotationLibrarian.getAllAnnotationsOfType(aJCas, Numeric.class.getCanonicalName())
		    .iterator();

		while (iterNums.hasNext()) {
			Numeric curNum = (Numeric) iterNums.next();
			if (StringUtils.isNotBlank(curNum.getConcept())) {
				if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Systolic.name())) {
					Bp_Systolic_value newAnn = new Bp_Systolic_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Diastolic.name())) {
					Bp_Diastolic_value newAnn = new Bp_Diastolic_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
					Bp_value newAnn = new Bp_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
					Hr_value newAnn = new Hr_value(aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Temperature.name())) {
					T_value newAnn = new T_value(aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} ///////////////////////
				else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Respiratory.name())) {
					Resp_value newAnn = new Resp_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);
				}
				else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Height.name())) {
					Height_value newAnn = new Height_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Weight.name())) {
					Weight_value newAnn = new Weight_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);
				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.SO2.name())) {
					So2_value newAnn = new So2_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.BMI.name())) {
					BMI_value newAnn = new BMI_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Pain.name())) {
					Pain_value newAnn = new Pain_value(aJCas, curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
					newAnn.addToIndexes(aJCas);
				}
			}
		}
	}

	public abstract void analyzePatterns(JCas aJCas);

	public abstract void processValue(Annotation currentAnnotation, String vital_type, Annotation unit);

	public abstract void processValue(Annotation currentAnnotation, String vital_type, Annotation unit, boolean markIt);
}
