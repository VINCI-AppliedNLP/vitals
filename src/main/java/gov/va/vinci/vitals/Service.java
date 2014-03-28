package gov.va.vinci.vitals;

import java.util.Date;

import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.tools.Common;
import gov.va.vinci.leo.types.TypeLibrarian;
import gov.va.vinci.marian.regex.ae.RegexAnnotator;
import groovy.util.ConfigObject;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

import gov.va.vinci.marian.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.vitals.ae.*;
import gov.va.vinci.vitals.types.*;
import gov.va.vinci.vitals.types.RelationPattern;

/**
 * 
 * @author OVP
 * 
 * Elite pipeline includes:
 * -- RegEx to search for Term
 * -- WindowAnnotator to detect window boundaries
 * -- Context to determine the Term context within the Window
 * -- AnnotationPatternAnnotator to detect additional context information
 *
 */
public class Service {
	public static String SIMPLE_PIPELINE = "simple";
	public static String SIMPLE_UPDATE_TYPES = "updateTypes";
	protected static String RESOURCE_PATH = "src/main/resources/";

	public boolean START_CLIENT = false;
	public static final Logger log = Logger.getLogger(Common.getRuntimeClass().toString());

	public static void main(String[] args) {
		String environment = SIMPLE_UPDATE_TYPES;
		if (args.length > 0) {
			environment = args[0];
		}
		try {
			new Service().run(environment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(String environment) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info(" Starting " + this.getClass().getCanonicalName() + " at " + new Date(sw.getStartTime()));

		ConfigObject config = Utils.loadConfigFile(environment,
		    "ServerConfig.groovy",
		    "CommonConfig.groovy");

		// Create Service object
		gov.va.vinci.leo.Service service = new gov.va.vinci.leo.Service();
		service.setInputQueueName((String) config.get("serviceQueueName"));
		service.setBrokerURL((String) config.get("brokerUrl"));
		service.setCasPoolSize((Integer) config.get("casPoolSize"));

		if ((Boolean) config.get("registerWithJam")) {
			service.setJamServerBaseUrl((String) config.get("jamURL"));
			service.setJamQueryIntervalInSeconds((Integer) config.get("jamInterval"));
			service.setJamResetStatisticsAfterQuery((Boolean) config.get("jamResetAfterQuery"));
			log.info("\n\n Service is registered with JAM.  \n\n");
		}

		//Deploy the Service
		service.deploy(createPipeline((Boolean) config.get("generateTypes")));
		System.out.println("\r\n\r\nAggregate descriptor path: \r\n" + service.getAggregateDescriptorFile());

		System.out.println("Service running, press enter in this console to stop.");
		System.in.read();
		System.exit(0);

	}

	protected LeoAEDescriptor createPipeline(Boolean generateTypes) throws Exception {
		LeoAEDescriptor aggregate = new LeoAEDescriptor();
		LeoTypeSystemDescription types = createTypeSystem(generateTypes);
		aggregate
		    /*************************************************************************/
		    /***************         Start of initial regexes   **********************/
		    /*************************************************************************/

		    ////////  Term boundaries     
		    /*  Find other possible concepts  */
		    .addDelegate(createRegexAnnotator("TermAnnotator",
		        RESOURCE_PATH + "term.regex",
		        gov.va.vinci.vitals.types.Term.class.getCanonicalName(), types))

		    ////////////  Values and assessment     
		    /*  Find qualitative values such as normal, mild, or severe */
		    .addDelegate(createRegexAnnotator("QValueAnnotator",
		        RESOURCE_PATH + "qValues.regex",
		        gov.va.vinci.vitals.types.QValue.class.getCanonicalName(), types))
		    /* Find numerical values such as 1, 1.1, 0.1, or .1 */
		    .addDelegate(createRegexAnnotator("NumericValueAnnotator",
		        RESOURCE_PATH + "numericValues.regex",
		        gov.va.vinci.vitals.types.NumericValue.class.getCanonicalName(), types))
		    /* Find units of measure such as cm, mm, mmHz */
		    .addDelegate(createRegexAnnotator("UnitsAnnotator",
		        RESOURCE_PATH + "units.regex",
		        gov.va.vinci.vitals.types.Units.class.getCanonicalName(), types))

		    ///// Support annotations    
		    /*  Find some middle stuff that usually goes between concept and values */
		    .addDelegate(createRegexAnnotator("MiddleAnnotator",
		        RESOURCE_PATH + "middleStuff.regex",
		        gov.va.vinci.vitals.types.MiddleStuff.class.getCanonicalName(), types))
		    /* Create header annotations that would mark a possible header for the concepts that follow */

		    .addDelegate(createRegexAnnotator("ExcludeValueAnnotator",
		        RESOURCE_PATH + "valueExclude.regex",
		        gov.va.vinci.vitals.types.ExcludeValue.class.getCanonicalName(), types))

		    /********************************************************************************************/
		    /****    End of initial regexes      ***/
		    /********************************************************************************************/

		    /**** Servicing annotations, filtering, and combining ***************************************/
		    /*  Annotation filter removes some types if they overlap with other types:
		     *    Removing overlapping numeric and assessment values and term annotations*/
		    .addDelegate(new LeoAEDescriptor()
		        .setName("AnnotationFilter1Annotator")
		        .setImplementationName(AnnotationFilter.class.getCanonicalName())
		        .addParameterSetting("debug", false, false, "Boolean", false)
		         .addParameterSetting("step", true, false, "String",    "AnnotationFilter.FilterType.BASIC_TYPES.name()")
		        .addTypeSystemDescription(types))

		    /* Patterns to combine numeric values into ranges */
		    .addDelegate(new LeoAEDescriptor()
		        .setName("RangeAnnotator")
		        .setImplementationName(AnnotationPatternAnnotator.class.getCanonicalName())
		        .addParameterSetting("inputType", false, true, "String",
		            new String[] { NumericValue.class.getCanonicalName() })
		        .addParameterSetting("outputType", false, false, "String", Range.class.getCanonicalName())
		        .addParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(), true, false,
		            "String", RESOURCE_PATH + "range.pattern")
		        .addTypeSystemDescription(types))

		   
		    /********************                 Defining patterns *************************************/

		    /**************/
		    /*  Annotating <Term> <NumericValue> patterns     */
		    .addDelegate(new LeoAEDescriptor()
		        .setName("RelationPatternAnnotator")
		        .setImplementationName(AnnotationPatternAnnotator.class.getCanonicalName())
		        .addParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(), true, false,
		            "String", RESOURCE_PATH + "relation.pattern")
		        .addParameterSetting(AnnotationPatternAnnotator.Param.OUTPUT_TYPE.getName(), true, false,
		            "String", RelationPattern.class.getCanonicalName())
		        .addTypeSystemDescription(types))
		    /*  Annotation filter removes some types if they overlap with other types*/
		    /* Create final annotations*/
		    .addDelegate(new LeoAEDescriptor()
		        .setName("RelationAnnotator")
		        .setImplementationName(RelationAnnotator.class.getCanonicalName())
		        .addTypeSystemDescription(types))
		/**/
		;
		/*    */
		aggregate.setIsAsync(false);
		return aggregate;
	}

	protected LeoTypeSystemDescription createTypeSystem(boolean generateTypes) throws Exception {

		LeoTypeSystemDescription ftsd = new LeoTypeSystemDescription();
		// Relation Type description
		TypeDescription relationFtsd;
		String relationParent = "gov.va.vinci.vitals.types.Relation";
		relationFtsd = new TypeDescription_impl(relationParent, "", "uima.tcas.Annotation");
		relationFtsd.addFeature("Term", "", "uima.cas.String"); // Extracted normalized string
		relationFtsd.addFeature("Value", "", "uima.cas.String"); // Numeric  value
		relationFtsd.addFeature("Value2", "", "uima.cas.String"); // Numeric  value
		relationFtsd.addFeature("ValueString", "", "uima.cas.String"); // Numeric  value
		relationFtsd.addFeature("Concept", "", "uima.cas.String"); // String identifier of the mapped concept
		relationFtsd.addFeature("Assessment", "", "uima.cas.String"); // Assessment value
		relationFtsd.addFeature("Unit", "", "uima.cas.String"); // Measurement units
		relationFtsd.addFeature("Range", "", "uima.cas.String"); // Normal range - not needed.

		// Pattern Type description
		TypeDescription regexFtsd;
		String regexParent = "gov.va.vinci.vitals.types.Regex";
		regexFtsd = new TypeDescription_impl(regexParent, "", "uima.tcas.Annotation");
		regexFtsd.addFeature("Pattern", "", "uima.cas.String");
		regexFtsd.addFeature("Groups", "", "uima.cas.StringArray");

		// Pattern Type description
		TypeDescription patternFtsd;
		String patternParent = "gov.va.vinci.vitals.types.AnnotationPattern";
		patternFtsd = new TypeDescription_impl(patternParent, "", "uima.tcas.Annotation");
		patternFtsd.addFeature("anchor", "", "uima.tcas.Annotation");
		patternFtsd.addFeature("anchorPattern", "", "uima.cas.String");
		patternFtsd.addFeature("target", "", "uima.tcas.Annotation");
		patternFtsd.addFeature("targetPattern", "", "uima.cas.String");
		patternFtsd.addFeature("pattern", "", "uima.cas.String");

		// ///// Total type definition
		ftsd.addType(TypeLibrarian.getCSITypeSystemDescription())
		    .addType(TypeLibrarian.getRelationshipAnnotationTypeSystemDescription())
		    .addType(TypeLibrarian.getValidationAnnotationTypeSystemDescription())
		    .addType("gov.va.vinci.vitals.types.RefStAssessment", "", "uima.tcas.Annotation") //Assessment
		    .addType("gov.va.vinci.vitals.types.RefStValue", "", "uima.tcas.Annotation") // Value
		    .addType("gov.va.vinci.vitals.types.RefStLV_Term", "", "uima.tcas.Annotation") //LV_Systolic_Function
		    .addType("gov.va.vinci.vitals.types.RefStOthers", "", "uima.tcas.Annotation") // Other ref st annotations
		    .addType(regexFtsd)
		    .addType(patternFtsd)
		    .addType("gov.va.vinci.vitals.types.ExcludeValue", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.MiddleStuff", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.ExcludeConcept", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.Term", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.QValue", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.NumericValue", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.Units", "", regexParent)
		    .addType("gov.va.vinci.vitals.types.Range", "", patternParent)
		    .addType("gov.va.vinci.vitals.types.RelationPattern", "", patternParent)
		    .addType(relationFtsd)
		    .addType("gov.va.vinci.vitals.types.ValidationRelation", "", relationParent);
		if (generateTypes) {
			ftsd.jCasGen("src/main/java", "target/classes");
		}
		return ftsd;
	}

	/** @param name
	 * @param resourcePath
	 * @param outputType
	 * @param types
	 * @return
	 * @throws Exception */
	public LeoAEDescriptor createRegexAnnotator(String name,
	    String resourcePath, String outputType, LeoTypeSystemDescription types) throws Exception {
		return new LeoAEDescriptor()
		    .setName(name)
		    .setImplementationName(RegexAnnotator.class.getCanonicalName())
		    .addParameterSetting("resource", true, false, "String", resourcePath)
		    .addParameterSetting("outputType", true, false, "String", outputType)
		    .addParameterSetting("matchedPatternFeatureName", true, false, "String", "Pattern")
		    .addParameterSetting("groupFeatureName", true, false, "String", "Groups")
		    .addParameterSetting("case_sensitive", false, false, "Boolean", false)
		    .addParameterSetting("word_boundary", false, false, "Boolean", false)
		    .addParameterSetting("debug", false, false, "Boolean", false)
		    .addTypeSystemDescription(types);

	}
}
