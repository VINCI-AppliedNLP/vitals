package gov.va.vinci.vitals.listeners;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import gov.va.vinci.leo.listener.BaseListener;
import gov.va.vinci.leo.tools.AuCompare;
import gov.va.vinci.leo.tools.AuStats;
import gov.va.vinci.leo.tools.LeoUtils;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.jcas.JCas;

import java.util.List;
import java.util.Map;

/**
 * Given a map of gold standard to tool generated annotation types compare each type set to
 * determine True Positives, False Positives, False Negatives.
 *
 * User: Thomas Ginter
 * Date: 10/25/13
 * Time: 9:39 AM
 */
public class AuSummaryListener extends BaseListener {
    /**
     * AuCompare service object.
     */
    protected AuCompare compare = null;

    /**
     * Logging object of output.
     */
    private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

    /**
     * Constructor to initialize the map of gold standard to tool generated annotation types.
     *
     * @param auAnnotationMap
     *      JSON formatted string mapping each gold standard annotation type to a tool type.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap has an invalid JSON format
     */
    public AuSummaryListener(Map<String,String> auAnnotationMap)  {
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Increment the number of processed CAS objects received and check for errors.
     *
     * @param aCas Cas returned by the service after processing.
     * @param aStatus Status object returned by the service, can carry service exceptions, handled by BaseListener.
     * @see org.apache.uima.aae.client.UimaAsBaseCallbackListener#entityProcessComplete(org.apache.uima.cas.CAS,
     *      org.apache.uima.collection.EntityProcessStatus)
     */
    @Override
    public void entityProcessComplete(CAS aCas, EntityProcessStatus aStatus) {
        super.entityProcessComplete(aCas, aStatus);
        JCas jCas;
        try {
            jCas = aCas.getJCas();
            compare.annotationComparison(jCas);
        } catch (CASException e) {
            throw new RuntimeException("Error getting reference to JCas object!", e);
        }
    }

    @Override
    public void collectionProcessComplete(EntityProcessStatus aStatus) {
	    // TODO Auto-generated method stub
	    super.collectionProcessComplete(aStatus);
	    outputStatsToConsole();
    }

		/**
     * Write the stat string for each AuStats object out to the console.
     */
    public void outputStatsToConsole() {
        if(compare == null) {
            return;
        }
        compare.outputStatsToConsole();
    }

    /**
     * Return a list of the stat String objects for each AuStats object.
     *
     * @return list of stat Strings
     */
    public List<String> outputStatStrings() {
        if(compare == null) {
            return null;
        }
        return compare.outputStatStrings();
    }

    /**
     * Return a list of AuStats objects used in the compare service accessed by the gov.va.vinci.leo.listener.
     *
     * @return list of AuStats objects
     */
    public List<AuStats> getStatList() { return compare.getStatList(); }

}
