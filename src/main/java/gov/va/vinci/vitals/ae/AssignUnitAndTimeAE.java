package gov.va.vinci.vitals.ae;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.vitals.types.*;

public class AssignUnitAndTimeAE extends LeoBaseAnnotator {
	public static final String NEW_LINE = System.getProperty("line.separator");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		super.process(aJCas);
		processTimestamp(aJCas);
		processUnits(aJCas);
	}

	public void processUnits(JCas aJCas) {
		ArrayList<Annotation> unitList = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas, Unit.type);

		if (unitList.size() > 0) {
			FSIterator<Annotation> iterNums = this.getAnnotationListForType(aJCas, Numeric.class.getCanonicalName());
			try {
				while (iterNums.hasNext()) {
					Numeric currNum = (Numeric) iterNums.next();
					if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum, Relation.type).size() > 0) {
						Relation currRelation = (Relation) ((ArrayList) AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum,
						    Relation.type)).get(0);
						if (AnnotationLibrarian.getNextClosestAnnotations(currNum, unitList).size() > 0) {
							Unit units = (Unit) ((ArrayList) AnnotationLibrarian.getNextClosestAnnotations(currNum, unitList)).get(0);
							currNum.setUnit(units);
						}
					}

					if (currNum.getUnit() == null) {
						if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum, Relation_Time.type).size() > 0) {
							Relation_Time currRelation = (Relation_Time) ((ArrayList) AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum, Relation_Time.type)).get(0);
							if (AnnotationLibrarian.getAllCoveredAnnotationsOfType(currRelation, Unit.type).size() > 0) {
								Unit units = (Unit) ((ArrayList) AnnotationLibrarian.getAllCoveredAnnotationsOfType(currRelation, Unit.type)).get(0);
								currNum.setUnit(units);
							}
						}
					}
				}
			} catch (CASException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}

	public void processTimestamp(JCas aJCas) {
		ArrayList<Annotation> timeList = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas, Timestamp.type);
		ArrayList<Annotation> timesToKeep = new ArrayList<Annotation>();
		if (timeList.size() > 0) {
			FSIterator<Annotation> iterNums = this.getAnnotationListForType(aJCas, Numeric.class.getCanonicalName());
			try {
				while (iterNums.hasNext()) {
					Numeric currNum = (Numeric) iterNums.next();

					if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum, Relation.type).size() > 0) {
						Relation currRelation = (Relation) ((ArrayList) AnnotationLibrarian.getAllContainingAnnotationsOfType(
						    currNum, Relation.type)).get(0);
						if (AnnotationLibrarian.getAllCoveredAnnotationsOfType(currRelation, Timestamp.type).size() > 0) {
							Timestamp stamp = (Timestamp) ((ArrayList) AnnotationLibrarian.getAllCoveredAnnotationsOfType(
							    currRelation, Timestamp.type)).get(0);
							currNum.setTimestamp(stamp);
							timesToKeep.add(stamp);
						}
					}

					if (currNum.getTimestamp() == null) {
						if (AnnotationLibrarian.getAllContainingAnnotationsOfType(currNum, Relation_Time.type).size() > 0) {
							Relation_Time currRelation = (Relation_Time) ((ArrayList) AnnotationLibrarian.getAllContainingAnnotationsOfType(
							    currNum, Relation_Time.type)).get(0);
							if (AnnotationLibrarian.getAllCoveredAnnotationsOfType(currRelation, Timestamp.type).size() > 0) {
								Timestamp stamp = (Timestamp) ((ArrayList) AnnotationLibrarian.getAllCoveredAnnotationsOfType(
								    currRelation, Timestamp.type)).get(0);
								currNum.setTimestamp(stamp);
								timesToKeep.add(stamp);
							}
						}
					}
					if (currNum.getTimestamp() == null) {
						if (AnnotationLibrarian.getPreviousClosestAnnotations(currNum, timeList).size() > 0) {
							Timestamp stamp = (Timestamp) ((ArrayList) AnnotationLibrarian.getPreviousClosestAnnotations(currNum, timeList))
							    .get(0);
							int len = aJCas.getDocumentText().substring(stamp.getEnd(), currNum.getBegin()).split(NEW_LINE).length;
							if (len < 5) {
								//if (!aJCas.getDocumentText().substring(stamp.getEnd(), currNum.getBegin()).contains(NEW_LINE)) {
								currNum.setTimestamp(stamp);
								timesToKeep.add(stamp);
							}
						}
					}

					if (currNum.getTimestamp() == null) {
						if (AnnotationLibrarian.getNextClosestAnnotations(currNum, timeList).size() > 0) {
							Timestamp stamp = (Timestamp) ((ArrayList) AnnotationLibrarian.getNextClosestAnnotations(currNum, timeList))
							    .get(0);
							if (!aJCas.getDocumentText().substring(currNum.getEnd(), stamp.getBegin()).contains(NEW_LINE)) {
								currNum.setTimestamp(stamp);
								timesToKeep.add(stamp);
							}
						}
					}
				}
			} catch (CASException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}
		checkingLoop: for (Annotation t : timeList) {
			toKeep: for (Annotation k : timesToKeep) {
				if (t == k) {
					continue checkingLoop;
					// moving to the next timestamp
				}
			}
		//	t.removeFromIndexes(aJCas);
		}
	}

	public static class Param extends LeoBaseAnnotator.Param {
		/** No addtional parameters **/
	}
}
