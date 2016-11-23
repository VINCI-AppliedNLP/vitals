package gov.va.vinci.vitals.listeners;

import java.util.ArrayList;
import java.util.List;

import gov.va.vinci.leo.sherlock.listeners.LearningListener;
import gov.va.vinci.leo.sherlock.tools.SherlockVector;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.kttr.types.HRValue;
import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.vitals.types.Hr_Vector;

public class HrLearningListener extends LearningListener {
	static int numVectors = 0;

	public HrLearningListener(String validationMap, boolean isExitOnError, String[] filter) {
		super(validationMap, isExitOnError, filter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SherlockVector> getVectorList(JCas jcas) throws CASException {
		ArrayList<SherlockVector> vectors = new ArrayList<SherlockVector>();

		ArrayList<Annotation> vAnnotationList = (ArrayList<Annotation>) AnnotationLibrarian
		    .getAllAnnotationsOfType(jcas, Hr_Vector.type, false);
		for (Annotation v : vAnnotationList) {
			StringArray keys = null;
			StringArray values = null;
			String prediction = null;
			values = ((Hr_Vector) v).getValues();
			if (values == null) {
				LOG.warn("Error getting prediction annotation, feature value empty!");
				continue;
			}
			if ((AnnotationLibrarian.getAllOverlappingAnnotationsOfType(v, HRValue.type, false)).size() > 0) {
				prediction = "hr";
			} else {
				prediction = "other";
			}
			//Get the keys and values string arrays from the validation annotation             
			keys = ((Hr_Vector) v).getKeys();
			String[][] vector = new String[2][keys.size()];
			keys.copyToArray(0, vector[0], 0, keys.size());
			values.copyToArray(0, vector[1], 0, values.size());               //Add the vector to the list             
			vectors.add(new SherlockVector(prediction, vector[0], vector[1]));
		}//for
		
		numVectors += vectors.size();

		return vectors;
	}

}
