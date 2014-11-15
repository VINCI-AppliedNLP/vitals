package gov.va.vinci.vitals.ae;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.vitals.types.*;

public class ProcessingStepAE extends LeoBaseAnnotator {
	public static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

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

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		// TODO Auto-generated method stub
		return null;
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

	public void createValueTypes(JCas aJCas) throws AnalysisEngineProcessException {
		FSIterator<Annotation> iterNums = this.getAnnotationListForType(aJCas, Numeric.class.getCanonicalName());

		while (iterNums.hasNext()) {
			Numeric curNum = (Numeric) iterNums.next();
			if (StringUtils.isNotBlank(curNum.getConcept())) {
				if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Systolic.name())) {
					Bp_Systolic_value newAnn = (Bp_Systolic_value) this.addOutputAnnotation(Bp_Systolic_value.class.getCanonicalName(),
					    aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Diastolic.name())) {
					Bp_Diastolic_value newAnn = (Bp_Diastolic_value) this.addOutputAnnotation(Bp_Diastolic_value.class.getCanonicalName(),
					    aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Blood_Pressure.name())) {
					Bp_value newAnn = (Bp_value) this.addOutputAnnotation(Bp_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Heart_Rate.name())) {
					Hr_value newAnn = (Hr_value) this.addOutputAnnotation(Hr_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Temperature.name())) {
					T_value newAnn = (T_value) this.addOutputAnnotation(T_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
				} ///////////////////////
				else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Respiratory.name())) {
					Resp_value newAnn = (Resp_value) this.addOutputAnnotation(Resp_value.class.getCanonicalName(),
					    aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
				}
				else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Height.name())) {
					Height_value newAnn = (Height_value) this.addOutputAnnotation(
					    Height_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Weight.name())) {
					Weight_value newAnn = (Weight_value) this.addOutputAnnotation(
					    Weight_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.SO2.name())) {
					So2_value newAnn = (So2_value) this.addOutputAnnotation(So2_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.BMI.name())) {
					BMI_value newAnn = (BMI_value) this.addOutputAnnotation(BMI_value.class.getCanonicalName(), aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);

				} else if (curNum.getConcept().equalsIgnoreCase(vitalTypes.Pain.name())) {
					Pain_value newAnn = (Pain_value) this.addOutputAnnotation(Pain_value.class.getCanonicalName(),
					    aJCas,
					    curNum.getBegin(), curNum.getEnd());
					newAnn.setSource(curNum.getSource());
					newAnn.setUnit(curNum.getUnit());
					newAnn.setTimestamp(curNum.getTimestamp());
					newAnn.setValue("" + curNum.getValue());
					newAnn.setValueAnnotation(curNum);
				}
			}
		}
	}
}
