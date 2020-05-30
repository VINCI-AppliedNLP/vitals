package gov.va.vinci.vitals.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.vitals.types.LowerPrecisionWindow;
import gov.va.vinci.vitals.types.Output_Value;
import gov.va.vinci.vitals.types.Section;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;


public class UpdateOutputAE extends LeoBaseAnnotator {
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

        // Iterate through all output
        // set unitString and timelineString

        FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Output_Value.class.getCanonicalName());
        ArrayList <Annotation> toDelete = new ArrayList<Annotation>();
        while(iter.hasNext()){
            Output_Value currOut = (Output_Value) iter.next();
            if(currOut.getTimestamp() != null){
                currOut.setTimestampString(currOut.getTimestamp().getCoveredText());
            }
            if(currOut.getUnit() != null){
                currOut.setUnitString(currOut.getUnit().getCoveredText());
            }
            //TODO: Check the section of the value.
            try {
                ArrayList<Annotation> sections = (ArrayList<Annotation>) AnnotationLibrarian.getAllContainingAnnotationsOfType(currOut,                        Section.type, false);
                if(sections.size() > 0){
                    //TODO: add section to the out_type
                    currOut.setSection(sections.get(0));
                    currOut.setSectionType(((Section)sections.get(0)).getSectionHeaderText());
                }
            } catch (CASException e) {
                e.printStackTrace();
            }


        }

    }
}
