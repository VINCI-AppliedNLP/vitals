package gov.va.vinci.vitals.ae;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Proof of concept regex annotator for vitals using dk.brics.automaton. This is NOT case sensitive, and uses the
 * dk.brics.automaton, which has a very limited regex format. The regex I tested on where 3-4x faster with Brics,
 * but it can only be used on simple regexs.
 */
public class AutomatonRegexAnnotator  extends RegexAnnotator{
    /**
     * Map of output annotation type names to associated Constructor objects.
     */
    Map<String, Constructor<?>> outputTypeMap = new HashMap<String, Constructor<?>>();

    Map<String, RunAutomaton> automatonList = new HashMap<String, RunAutomaton>();
    /** Performance monitoring variables. **/
    private static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

    /**
     * Method to get the input Annotation type, Output Annotation type and the
     * resource file
     *
     * @param aContext instance
     */
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
    }

    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        for (String configurationName : configuration.getConfigNames()) {
            Map parameters = configuration.getParameters(configurationName);
            Map<Pattern, Matcher> patterns = configuration.getPatterns(configurationName);
            for (Pattern patt1 : patterns.keySet()) {
                processPattern(aJCas, patt1, patterns.get(patt1), parameters);
            }
        } // End Loop through configuration names.
    }//process method

    /**
     * Processes an individual pattern on the cas.
     *
     * @param aJCas         the cas to run the pattern on.
     * @param patt1         the pattern to be executed.
     * @param m             the matcher to use on the pattern.
     * @param parameters    the parameters for this pattern.
     * @throws AnalysisEngineProcessException any exception that occurs while processing.
     */
    protected void processPattern(JCas aJCas, Pattern patt1, Matcher m, Map parameters) throws AnalysisEngineProcessException {
        EtmPoint point = null;
        if (configuration.isPerformanceMonitoring()) {
            point = etmMonitor.createPoint(AutomatonRegexAnnotator.class.getCanonicalName() + ":Pattern=" + patt1.pattern());
        }

        RunAutomaton a = automatonList.get(patt1.pattern());

        if (a == null) {
            String pattern = patt1.pattern();
            RegExp r = new RegExp(patt1.pattern());

            a = new RunAutomaton(r.toAutomaton());
            automatonList.put(patt1.pattern(), a);
        }

        // For each input type, get all annotations belonging to this type
        for (String type : (String[]) parameters.get("inputTypes")) {
            // Get an iterator for all annotations of type
            Type inputTypeObj = typeMap.get(type);
            FSIterator<Annotation> annotationList = null;
            if (inputTypeObj == null) {
                inputTypeObj = aJCas.getTypeSystem().getType(type);
                //If there is no such type on the typeSystem it is an exception and likely configured wrong.
                if (inputTypeObj == null) {
                    throw new AnalysisEngineProcessException(new Exception("Could not find type (" + type + ") in the CAS."));
                }
                typeMap.put(type, inputTypeObj);
            }

            annotationList = aJCas.getAnnotationIndex(inputTypeObj).iterator();
            // Iterate over the input type annotations
            while (annotationList.hasNext()) {

                Annotation inputAnnotation = annotationList.next();
                // Get the text of the document covered by this annotation
                String coveredText = inputAnnotation.getCoveredText().toLowerCase();
                AutomatonMatcher matcher = a.newMatcher(coveredText);

                while (matcher.find()) {
                    Constructor<?> con1 = outputTypeMap.get(parameters.get("outputType"));
                    if (con1 == null) {
                        try {
                            con1 = Class.forName((String) parameters.get("outputType")).getConstructor(JCas.class);
                        } catch (Exception e) {
                            throw new AnalysisEngineProcessException(e);
                        }
                        outputTypeMap.put((String) parameters.get("outputType"), con1);
                    }

                    Annotation outputAnnotationType;
                    try {
                        outputAnnotationType = (Annotation) con1.newInstance(aJCas);
                    } catch (Exception e) {
                        throw new AnalysisEngineProcessException(e);
                    }
                    outputAnnotationType.setBegin(matcher.start() + inputAnnotation.getBegin());
                    outputAnnotationType.setEnd(matcher.end() + inputAnnotation.getBegin());

                    // Set matched pattern
                    doSetFeatureValue((String) parameters.get("matchedPatternFeatureName"), patt1.pattern(), outputAnnotationType);

                    // Set concept value (if needed)
                    doSetFeatureValue((String) parameters.get("concept_feature_name"), (String) parameters.get("concept_feature_value"), outputAnnotationType);

                    // Add the output annotation feature structure to the Cas index
                    outputAnnotationType.addToIndexes();
                }
            }
        }
        if (configuration.isPerformanceMonitoring()) {
            point.collect();
        }
    }
}
