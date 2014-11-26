package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

public class FilterRelationsAE extends LeoBaseAnnotator {
	public class InstanceType {
		boolean valueIsNumeric = false;
		boolean valueIsRange = false;
		boolean valueIsBp = false;

		public InstanceType(Annotation value) {
			if (value instanceof Numeric)
				valueIsNumeric = true;

			if (value instanceof Range)
				valueIsRange = true;

			if (value instanceof PotentialBp)
				valueIsBp = true;
		}

	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		// There are no new types created in this annotator
		return null;
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		super.process(aJCas);

		// Iterate through all Relations
		// if the first target overlaps with the other target do:
		// if the target is an instance of Numeric, check if it overlaps with another Relation that has target Range

		ArrayList<Annotation> annsToRemove = new ArrayList<Annotation>();
		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		while (iter.hasNext()) {

			Relation currRelation = (Relation) iter.next();

			if (currRelation.getTarget() != null) {
				Annotation value = currRelation.getTarget();
				InstanceType valueInstanceType = new InstanceType(value);
				

				// if more than one relation overlaps with the annotation which is the target of the currRelation.
				try {
					if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(value, Relation.type).size() > 1) {
						ArrayList<Annotation> overlappingRelations = (ArrayList<Annotation>) AnnotationLibrarian
						    .getAllOverlappingAnnotationsOfType(value, Relation.type);
						for (Annotation or : overlappingRelations) {
							Relation overRel = (Relation) or;
							if (overRel.getTarget() != null) { // that means there is a value associated with the relation. Should always be true
								Annotation valueOverRel = overRel.getTarget();
								InstanceType valueOverRelInstanceType = new InstanceType(valueOverRel);
								// FIXME: Finish annotator at this point
							}
						}

					}
				} catch (CASException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		} // end of relation loop
	}

	public LeoAEDescriptor getLeoAEDescriptor() throws Exception {
		return getLeoAEDescriptor(this.getClass().getCanonicalName(), getAnnotatorParams());
	}
}
