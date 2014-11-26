package gov.va.vinci.vitals.ae;

import java.util.ArrayList;
import java.util.List;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.ConfigurationParameterImpl;
import gov.va.vinci.leo.tools.LeoUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
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
public class AnnotationFilter_archive extends LeoBaseAnnotator {

	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());
	protected String[] typesToKeep = null;
	protected String[] typesToDelete = null;
	protected Boolean removeOverlapping = false;
	protected Boolean removeChildren = false;

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
		if (aContext.getConfigParameterValue(Param.REMOVE_CHILDREN.getName()) != null) {
			removeChildren = (Boolean) aContext.getConfigParameterValue(Param.REMOVE_CHILDREN.getName());
		}
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);

		for (String type1 : typesToKeep) {
			if (typesToDelete == null)
				removeCoveredAnnotations(aJCas, type1, removeChildren);
			else {
				for (String type2 : typesToDelete) {
					if (removeOverlapping)
						removeOverlappingAnnotations(aJCas, type1, type2, removeChildren);
					else
						removeCoveredAnnotations(aJCas, type1, type2, removeChildren);
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

	public static void removeCoveredAnnotations(final JCas aJCas, String type1, String type2, boolean removeChildren)
	    throws AnalysisEngineProcessException {
		if (removeChildren) {
		} else {
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, type1, type2);
		}
	}

	public static void removeOverlappingAnnotations(final JCas aJCas, String type1, String type2, boolean removeChildren)
	    throws AnalysisEngineProcessException {
		if (removeChildren) {
		} else {
			AnnotationLibrarian.removeOverlappingAnnotations(aJCas, type1, type2);
		}
	}

	public static void removeCoveredAnnotations(final JCas aJCas, String type, boolean removeChildren)
	    throws AnalysisEngineProcessException {
		if (aJCas == null)
			throw new IllegalArgumentException("Missing jcas parameter!", null);
		if (StringUtils.isBlank(type))
			throw new IllegalArgumentException("Missing type name parameter!", null);
		if (removeChildren) {
			Type typeObj = null;
			try {
				typeObj = aJCas.getRequiredType(type);
			} catch (CASException e) {
				throw new AnalysisEngineProcessException(e);
			}
			ArrayList<Annotation> list = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas, typeObj);
			removeCoveredFromList(list, type, removeChildren);
		} else {
			AnnotationLibrarian.removeCoveredAnnotations(aJCas, type);
		}
	}//removeCoveredAnnotations method

	public static void removeCoveredFromList(final List<Annotation> annotations,
	    final String typeToRemove, boolean removeChildren) {
		if (annotations == null)
			throw new IllegalArgumentException("Missing annotations parameter!", null);
		if (StringUtils.isBlank(typeToRemove))
			throw new IllegalArgumentException("Missing type2remove parameter or String is blank!", null);
		for (int current = 0; current < annotations.size(); current++) {
			Annotation ca = annotations.get(current);
			for (int next = current + 1; next < annotations.size(); next++) {
				Annotation na = annotations.get(next);
				if (AnnotationLibrarian.completelyCovers(ca, na)) {

					if (isInstance(na, typeToRemove, removeChildren)) {
						na.removeFromIndexes();
					}
				} else {
					current = next - 1;
					break;
				}
			}//for
		}//for
	}//removeCoveredAnnotations method

	public static boolean isInstance(Object o, String className, boolean removeChildren) {
		if (removeChildren) {
			try {
				Class c = Class.forName(className);
				return c.isInstance(o);
			} catch (Exception e) {
				return false;
			}
		} else {
			String classString = o.getClass().getCanonicalName();
			if (StringUtils.endsWithIgnoreCase(className, classString)) {
				return true;
			}
			else
				return false;
		}

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
		public static ConfigurationParameter REMOVE_CHILDREN = new ConfigurationParameterImpl(
		    "removeChildren",
		    "Set true to remove overlapping, otherwise, remove completely covered only", "Boolean", false, false,
		    new String[0]);

	}
}
