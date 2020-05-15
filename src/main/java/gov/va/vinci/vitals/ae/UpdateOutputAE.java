package gov.va.vinci.vitals.ae;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.vitals.types.Output_Value;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


public class UpdateOutputAE extends LeoBaseAnnotator {
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

        // Iterate through all output
        // set unitString and timelineString

        FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, Output_Value.class.getCanonicalName());
        while(iter.hasNext()){
            Output_Value currOut = (Output_Value) iter.next();
            if(currOut.getTimestamp() != null){
                currOut.setTimestampString(currOut.getTimestamp().getCoveredText());
            }
            if(currOut.getUnit() != null){
                currOut.setUnitString(currOut.getUnit().getCoveredText());
            }


        }

    }
}
