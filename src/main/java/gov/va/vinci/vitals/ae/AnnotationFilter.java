package gov.va.vinci.vitals.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.ConfigurationParameterImpl;
import gov.va.vinci.leo.tools.LeoUtils;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.ConfigurationParameter;

/**
 * The purpose of the AnnotationFilter is to remove overannotated instances.
 * The parameters include 
 * typesToKeep - is a string array of anchor types 
 * typesToDelete - is a string array of types to remove
 * removeOverlapping is set to true if overlap typesToDelete need to be overlapping with
 * 	instead of completely covered by the instances of typesToKeep.
 * 
 * @author Olga Patterson
 *
 */
public class AnnotationFilter extends LeoBaseAnnotator {

	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());
	protected String[] typesToKeep = null;
	protected String[] typesToDelete = null;
	protected Boolean removeOverlapping = false;

	/**
	 * 
	 * @param a
	 * @param punc
	 */

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		typesToKeep = (String[]) aContext.getConfigParameterValue(Param.TYPES_TO_KEEP.getName());
		typesToDelete = (String[]) aContext.getConfigParameterValue(Param.TYPES_TO_DELETE.getName());
		if (aContext.getConfigParameterValue(Param.REMOVE_OVERLAPPING.getName()) != null) {
			removeOverlapping = (Boolean) aContext.getConfigParameterValue(Param.REMOVE_OVERLAPPING.getName());
		}
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);
		for (String type1 : typesToKeep) {
			if (typesToDelete == null)
				AnnotationLibrarian.removeCoveredAnnotations(aJCas, type1);
			else {
				for (String type2 : typesToDelete) {
					if (removeOverlapping)
						AnnotationLibrarian.removeOverlappingAnnotations(aJCas, type1, type2);
					else
						AnnotationLibrarian.removeCoveredAnnotations(aJCas, type1, type2);
				}
			}
		}
	}

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(), getAnnotatorParams());
	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {

		return null;
	}

	public static class Param extends LeoBaseAnnotator.Param {
		public static ConfigurationParameter TYPES_TO_KEEP = new ConfigurationParameterImpl("typesToKeep",
		    "The list of types to keep", "String", true, true, new String[0]);
		public static ConfigurationParameter TYPES_TO_DELETE = new ConfigurationParameterImpl("typesToDelete",
		    "The list of types to delete. If not specified, remove only annotations of the first type", "String",
		    false, true, new String[0]);
		public static ConfigurationParameter REMOVE_OVERLAPPING = new ConfigurationParameterImpl(
		    "removeOverlapping",
		    "Set true to remove overlapping, otherwise, remove completely covered only", "Boolean", false, false,
		    new String[0]);
	}
}
