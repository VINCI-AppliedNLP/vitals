package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.vitals.types.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * This is a custom annotator that reviews Relation annotations, gets the anchor
 * and target of the Relation annotation. If the anchor pattern has Pattern
 * feature as BP, replace Numeric annotation with the Bp_value annotation If the
 * anchor pattern has Pattern feature as HR, replace Numeric annotation with the
 * Hr_value annotation If the anchor pattern has Pattern feature as Temp,
 * replace Numeric annotation with the T_value annotation
 * 
 * @author olga.patterson@utah.edu
 * 
 */
public class SimplePatternAnnotator extends LeoBaseAnnotator {
	public static enum vitalTypes {
		Blood_Pressure, Heart_Rate, Temperature
	};

	private static final Logger log = Logger.getLogger(LeoUtils
			.getRuntimeClass().toString());

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		ArrayList<Annotation> annsToRemove = new ArrayList<Annotation>();

		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas,
				Relation.class.getCanonicalName());
		while (iter.hasNext()) {
			Relation currRelation = (Relation) iter.next();
			if (currRelation.getTarget() != null) {
				Annotation number = currRelation.getTarget();
				Annotation term = null;
				if (currRelation.getAnchor() != null) {
					term = currRelation.getAnchor();

				}
				if (term != null) {
					String pattern = ((Term) term).getPattern();
					if (StringUtils.isNotBlank(pattern)) {
						String vitalType = pattern.split("\\|")[0];
						if (vitalType
								.contains(vitalTypes.Blood_Pressure.name())) {

							this.addOutputAnnotation(
									Bp_value.class.getCanonicalName(), aJCas,
									number.getBegin(), number.getEnd());
							annsToRemove.add(number);

						} else if (vitalType.contains(vitalTypes.Heart_Rate
								.name())) {
							this.addOutputAnnotation(
									Hr_value.class.getCanonicalName(), aJCas,
									number.getBegin(), number.getEnd());
							annsToRemove.add(number);

						} else if (vitalType.contains(vitalTypes.Temperature
								.name())) {
							this.addOutputAnnotation(
									T_value.class.getCanonicalName(), aJCas,
									number.getBegin(), number.getEnd());
							annsToRemove.add(number);
						}
					}
				}
			}
		}
		for (Annotation a : annsToRemove) {
			a.removeFromIndexes(aJCas);
		}

	}

	private boolean possibleBP(Annotation number) {
		// TODO Auto-generated method stub
		return false;
	}

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(),
				getAnnotatorParams());
	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {

		return null;
	}

}
