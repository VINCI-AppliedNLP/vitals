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
	public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

		// Iterate through all Relations
		// if the first target overlaps with the other target do:
		// if the target is an instance of Numeric, check if it overlaps with another Relation that has target Range

		ArrayList<Annotation> annsToRemove = new ArrayList<Annotation>();
		FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Relation.class.getCanonicalName());
		
		relationLoop:
		while (iter.hasNext()) {

			Relation currRelation = (Relation) iter.next();

			if (currRelation.getTarget() != null) {
				Annotation value = currRelation.getTarget();
				InstanceType valueInstanceType = new InstanceType(value);

				// if more than one relation overlaps with the annotation which is the target of the currRelation.
				try {
					if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(value, Relation.type, false).size() > 1) {
						ArrayList<Annotation> overlappingRelations = (ArrayList<Annotation>) AnnotationLibrarian
						    .getAllOverlappingAnnotationsOfType(value, Relation.type, false);
						
						overlapLoop:
						for (Annotation or : overlappingRelations) {
							Relation overRel = (Relation) or;
							if (overRel.getTarget() != null) { // that means there is a value associated with the relation. Should always be true
								Annotation valueOverRel = overRel.getTarget();
								InstanceType valueOverRelInstanceType = new InstanceType(valueOverRel);
								// FIXME: Finish annotator at this point
								if (valueInstanceType.valueIsNumeric && valueOverRelInstanceType.valueIsNumeric) {
									if (value == valueOverRel) {
										if (currRelation != overRel) {
											// remove either currRelation or overRel - whichever is shorter.  
											// case 1 : start at the same point, end at the same point -- delete either one
											// case 2 : start1 < start2 && end1 > end2 or other way around -- delete relation2
											// case 3 : start1 < start2 but end1 < end2 -- change annotation relation1 setEnd(end2) 
										} else { // move on to the next overlapping relationship.
											continue overlapLoop;
										}
									}
								}else { // move on to the next overlapping relationship because the two relations deal with a different numeric
									// FIXME: check if the numerics overlap
									continue overlapLoop;
								}
							}
						}

					} else {
						// the current pattern is the only pattern that overlaps with the numeric
						continue;
					}
				} catch (CASException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		} // end of relation loop
	}

}
