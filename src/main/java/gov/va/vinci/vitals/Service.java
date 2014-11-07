package gov.va.vinci.vitals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import gov.va.vinci.kttr.types.HRValue;
import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.descriptors.TypeDescriptionBuilder;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.regex.ae.RegexAnnotator.Param;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.types.TypeLibrarian;
import gov.va.vinci.sherlock.ae.LearningAnnotator;
import gov.va.vinci.svmlib.ml.SvmVectorTranslator;
import gov.va.vinci.vitals.Client;
import gov.va.vinci.vitals.Utils;
import gov.va.vinci.vitals.ae.*;
import gov.va.vinci.vitals.types.*;
import groovy.util.ConfigObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

/**
 * 
 * @author OVP
 * 
 */
public class Service {

	public static final Logger log = Logger.getLogger(LeoUtils
	    .getRuntimeClass().toString());

	public static class GeneralSettings {
		static String ENVIRONMENT = "simple";
		static int CAS_POOL_SIZE = 8;
		static boolean START_CLIENT = false;
		static boolean GENERATE_TYPES = false;
		static String SERVICE_NAME = "DefaultServiceName";
		static String BROKER_URL = "tcp://localhost:61616";
		static Boolean REGISTER_WITH_JAM = false;
		static String DESCRIPTOR_PATH = "";
		static Boolean DESCRIPTOR_DELETE_ON_EXIT = true;
	}

	public static class KnowtatorVariables {
		public static HashMap<String, ArrayList<String>> uimaTypeFeatureMap = new HashMap<String, ArrayList<String>>();

	}

	public static class PipelineVariables {
		static String RESOURCE_PATH = "src/main/resources/";
		static String PatternType = "gov.va.vinci.vitals.types.Pattern";
		static String RegexType = "gov.va.vinci.leo.regex.types.RegularExpressionType";
		static HashMap<String, String> regexResourceToType = new HashMap<String, String>();
		static String TYPE_NUMERIC = "gov.va.vinci.vitals.types.Numeric";
		static String resourceNumeric = "numericValues.regex";

		static String TYPE_UNIT = "gov.va.vinci.vitals.types.Unit";
		static String resourceUnit = "unitsOfMeasure.groovy";

		static String TYPE_TERM = "gov.va.vinci.vitals.types.Term";
		static String resourceTerm = "concepts.groovy";
		static {
			regexResourceToType.put("indicator.regex", "gov.va.vinci.vitals.types.Indicator");
			regexResourceToType.put("date.regex", "gov.va.vinci.vitals.types.Timestamp");
		}

		static HashMap<String, String> apaResourceToType = new HashMap<String, String>();
		static {
			apaResourceToType.put("numericValuesExclude.pattern", "gov.va.vinci.vitals.types.NumericExclude");
			apaResourceToType.put("termsExclude.pattern", "gov.va.vinci.vitals.types.TermExclude");
			apaResourceToType.put("indicator.pattern", "gov.va.vinci.vitals.types.Indicator");

		}
		static String TYPE_RELATION = "gov.va.vinci.vitals.types.Relation";
		static String RESOURCE_RELATION = "relation.pattern";

		static String TYPE_RELATION_TIMESTAMP = "gov.va.vinci.vitals.types.Relation_Time";
		static String RESOURCE_RELATION_TIMESTAMP = "relation_time.pattern";

		static HashMap<String, String[]> filterTypes = new HashMap<String, String[]>();
		static {
			filterTypes.put("gov.va.vinci.vitals.types.Timestamp", new String[] { PipelineVariables.TYPE_NUMERIC });
			filterTypes.put("gov.va.vinci.vitals.types.TermExclude", new String[] { PipelineVariables.TYPE_UNIT,
			    PipelineVariables.TYPE_TERM });
			filterTypes.put("gov.va.vinci.vitals.types.NumericExclude",
			    new String[] { PipelineVariables.TYPE_NUMERIC });
		}

		static String TYPE_OUTPUT = "gov.va.vinci.vitals.types.OutputValue";
		static String[] valueTypes = new String[] {
		    "gov.va.vinci.vitals.types.Hr_value",
		    "gov.va.vinci.vitals.types.Bp_value",
		    "gov.va.vinci.vitals.types.Bp_Systolic_value",
		    "gov.va.vinci.vitals.types.Bp_Diastolic_value",
		    "gov.va.vinci.vitals.types.T_value",
		    "gov.va.vinci.vitals.types.Weight_value",
		    "gov.va.vinci.vitals.types.Height_value",
		    "gov.va.vinci.vitals.types.SO2_value",
		    "gov.va.vinci.vitals.types.Resp_value",
		    "gov.va.vinci.vitals.types.Pain_value",
		    "gov.va.vinci.vitals.types.BMI_value"
		};
	}

	public static class LearningVariables {
		static String TYPE_FeatureVector = "gov.va.vinci.vitals.types.Hr_Vector";
		static String TYPE_Prediction = "gov.va.vinci.vitals.types.Hr_Prediction";
		static String Hr_SvmModelPath = PipelineVariables.RESOURCE_PATH + "/hr_model.svm";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args != null)
			if (args.length > 0)
				GeneralSettings.ENVIRONMENT = args[0];
		try {
			new Service().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * run method loads parameters from groovy config files
	 * 
	 * @param environment
	 * @throws Exception
	 */
	public void run() throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("Starting the server   " + this.getClass().getCanonicalName()
		    + " at " + new Date(sw.getStartTime()));
		ConfigObject config = Utils.loadConfigFile(GeneralSettings.ENVIRONMENT,
		    "KnowtatorConfig.groovy", "CommonConfig.groovy",
		    "ServerConfig.groovy");

		loadProperties(config);
		// Create Service object
		gov.va.vinci.leo.Service service = new gov.va.vinci.leo.Service();
		service.setInputQueueName(GeneralSettings.SERVICE_NAME);
		service.setBrokerURL(GeneralSettings.BROKER_URL);
		//service.setCasPoolSize(GeneralSettings.CAS_POOL_SIZE);

		if (GeneralSettings.REGISTER_WITH_JAM) {
			service.setJamServerBaseUrl((String) config.get("jamURL"));
			service.setJamQueryIntervalInSeconds((Integer) config.get("jamInterval"));
			service.setJamResetStatisticsAfterQuery((Boolean) config.get("jamResetAfterQuery"));
			log.info("\n\n Service is registered with JAM.  \n\n");
		}

		if (StringUtils.isNotBlank(GeneralSettings.DESCRIPTOR_PATH)) {
			service.setDescriptorDirectory(GeneralSettings.DESCRIPTOR_PATH);//"P:\\ORD_Singh_201211012D\\nlp\\desc\\");				
		}
		if (GeneralSettings.DESCRIPTOR_DELETE_ON_EXIT != null) {
			service.setDeleteOnExit(GeneralSettings.DESCRIPTOR_DELETE_ON_EXIT);
		}

		// INFO: Deploy the Service

		LeoTypeSystemDescription types = createTypeSystem();
		service.deploy(createPipeline(types));
		log.info("Aggregate descriptor: " + service.getAggregateDescriptorFile());
		// Starting Client
		if (GeneralSettings.START_CLIENT) {
			String[] args = { (String) config.get("clientEnvironment") };
			log.info("Starting client" + args);
			Client.main(args);
		}
		sw.stop();
		log.info("\nAggregate descriptor file is located at: \n" + service.getAggregateDescriptorFile());
		System.out.println("\nService running, press enter in this console to stop.");
		System.in.read();
		System.exit(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadProperties(ConfigObject config) {
		GeneralSettings.START_CLIENT = (Boolean) config.get("startClient");
		GeneralSettings.GENERATE_TYPES = (Boolean) config.get("generateTypes");
		GeneralSettings.BROKER_URL = (String) config.get("brokerUrl");
		GeneralSettings.SERVICE_NAME = (String) config.get("serviceQueueName");
		GeneralSettings.CAS_POOL_SIZE = (Integer) config.get("casPoolSize");
		GeneralSettings.REGISTER_WITH_JAM = (Boolean) config.get("registerWithJam");
		GeneralSettings.DESCRIPTOR_PATH = (String) config.get("descriptorPath");
		GeneralSettings.DESCRIPTOR_DELETE_ON_EXIT = (Boolean) config.get("delete_on_exit");

		HashMap<String, String> typeList = (HashMap<String, String>) config.get("knowtatorToUimaTypeMap");
		HashMap<String, HashMap<String, String>> featureList = new HashMap<String, HashMap<String, String>>();

		try {
			if (config.get("knowtatorToUimaFeatureMap") instanceof HashMap) {
				if (((HashMap) config.get("knowtatorToUimaFeatureMap")).size() > 0) {
					featureList.putAll((HashMap<String, HashMap<String, String>>)
					    config.get("knowtatorToUimaFeatureMap"));
				} else
					log.warn("No features were added to the types!");

			} else
				log.warn("No features were added to the types!");

		} catch (Exception e) {
			log.warn("No features were added to the types!");
		}

		loadTypeMap(typeList, featureList);
		if (config.get("svmModelPath") != null) {
			LearningVariables.Hr_SvmModelPath = (String) config.get("svmModelPath");
		}
	}

	/**
	 * 
	 * @param typeList
	 *            -- HashMap<String, String> typeList -- HashMap
	 *            <Knowtator_Annotation , UIMA_Annotation>
	 * @param featureList
	 *            -- HashMap<String, HashMap<String, String>> featureList --
	 *            HashMap<Knowtator_Annotation, HashMap<Knowtator_attribute,
	 *            UIMA_feature>> featureList
	 * @return
	 */
	private void loadTypeMap(HashMap<String, String> typeList,
	    HashMap<String, HashMap<String, String>> featureList) {
		if (typeList != null && featureList != null) {
			// For all knowtator annotations, need to create UIMA annotation
			for (String kttrType : typeList.keySet()) {
				String uimaType = typeList.get(kttrType);
				ArrayList<String> uimaFeatures = new ArrayList<String>();
				// If a knowtator Annotation has an attribute, create a String
				// feature in the UIMA annotation type
				if (featureList.containsKey(kttrType)) {
					for (String kttrFeature : featureList.get(kttrType)
					    .keySet()) {
						uimaFeatures.add(featureList.get(kttrType).get(
						    kttrFeature));
					}
				}
				KnowtatorVariables.uimaTypeFeatureMap.put(uimaType,
				    uimaFeatures);
			}
		}
	}

	/**
	 * createPipeline defines all the parts of the pipeline
	 * 
	 * @param types
	 * 
	 * @param generateTypes
	 * @return
	 * @throws Exception
	 */
	protected LeoAEDescriptor createPipeline(LeoTypeSystemDescription types)
	    throws Exception {
		LeoAEDescriptor aggregate = new LeoAEDescriptor();

		aggregate
		    .addDelegate(new LeoAEDescriptor()
		        .setName("NumericAnnotator")
		        .setImplementationName(RegexAnnotator.class.getCanonicalName())
		        .addParameterSetting(RegexAnnotator.Param.RESOURCE.getName(), true, false, "String",
		            PipelineVariables.RESOURCE_PATH + PipelineVariables.resourceNumeric)
		        .addParameterSetting(RegexAnnotator.Param.OUTPUT_TYPE.getName(), true, false, "String",
		            PipelineVariables.TYPE_NUMERIC)
		        .addParameterSetting(Param.MATCHED_PATTERN_FEATURE_NAME.getName(), false, false, "String",
		            "pattern"));

		aggregate
		    .addDelegate(new LeoAEDescriptor()
		        .setName("UnitsAnnotator")
		        .setImplementationName(RegexAnnotator.class.getCanonicalName())
		        .addParameterSetting(RegexAnnotator.Param.GROOVY_CONFIG_FILE.getName(), true, false, "String",
		            PipelineVariables.RESOURCE_PATH + PipelineVariables.resourceUnit)
		        .addParameterSetting(Param.MATCHED_PATTERN_FEATURE_NAME.getName(), false, false, "String",
		            "pattern"));

		aggregate
		    .addDelegate(new LeoAEDescriptor()
		        .setName("TermAnnotator")
		        .setImplementationName(RegexAnnotator.class.getCanonicalName())
		        .addParameterSetting(RegexAnnotator.Param.GROOVY_CONFIG_FILE.getName(), true, false, "String",
		            PipelineVariables.RESOURCE_PATH + PipelineVariables.resourceTerm)
		        .addParameterSetting(Param.MATCHED_PATTERN_FEATURE_NAME.getName(), false, false, "String",
		            "pattern"));

		// INFO: Create initial annotations
		int i = 0;
		for (Entry<String, String> a : PipelineVariables.regexResourceToType.entrySet()) {
			i++;
			LeoAEDescriptor regexAnnotator = new LeoAEDescriptor()
			    .setName("RegexAnnotator_" + i)
			    .setImplementationName(RegexAnnotator.class.getCanonicalName())
			    .addParameterSetting(RegexAnnotator.Param.RESOURCE.getName(), true, false, "String",
			        PipelineVariables.RESOURCE_PATH + a.getKey())
			    .addParameterSetting(RegexAnnotator.Param.OUTPUT_TYPE.getName(), true, false, "String",
			        a.getValue())
			    .addParameterSetting(Param.MATCHED_PATTERN_FEATURE_NAME.getName(), false, false, "String",
			        "pattern");

			aggregate.addDelegate(regexAnnotator);
		}

		aggregate.addDelegate(new AnnotationFilter()
		    .getLeoAEDescriptor()
		    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(),
		        new String[] { "gov.va.vinci.vitals.types.Term", "gov.va.vinci.vitals.types.Indicator" })
		    .addTypeSystemDescription(types));

		// INFO: Created annotators to find patterns.
		i = 0;
		for (Entry<String, String> a : PipelineVariables.apaResourceToType.entrySet()) {
			i++;
			// Pattern detection AnnotationPatternAnnotation -- context.pattern
			// */
			aggregate
			    .addDelegate(new AnnotationPatternAnnotator()
			        .getLeoAEDescriptor()
			        .setName("PatternAnnotator" + i)
			        .setParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(),
			            PipelineVariables.RESOURCE_PATH + a.getKey())
			        .setParameterSetting(AnnotationPatternAnnotator.Param.OUTPUT_TYPE.getName(), a.getValue())
			        .addTypeSystemDescription(types));
		}
		// INFO: Filter unneeded annotations*/
		for (Entry<String, String[]> a : PipelineVariables.filterTypes.entrySet()) {
			aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
			    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] { a.getKey() })
			    .addTypeSystemDescription(types));
			aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
			    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), a.getValue())
			    .addTypeSystemDescription(types));
			aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
			    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] { a.getKey() })
			    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_DELETE.getName(), a.getValue())
			    .setParameterSetting(AnnotationFilter.Param.REMOVE_OVERLAPPING.getName(), true)
			    .addTypeSystemDescription(types));
		}

		i++;
		aggregate
		    .addDelegate(new AnnotationPatternAnnotator()
		        .getLeoAEDescriptor()
		        .setName("PatternAnnotator" + i)
		        .setParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(),
		            PipelineVariables.RESOURCE_PATH + PipelineVariables.RESOURCE_RELATION)
		        .setParameterSetting(AnnotationPatternAnnotator.Param.OUTPUT_TYPE.getName(),
		            PipelineVariables.TYPE_RELATION)
		        .addTypeSystemDescription(types));
		i++;
		aggregate
		    .addDelegate(new AnnotationPatternAnnotator()
		        .getLeoAEDescriptor()
		        .setName("PatternAnnotator" + i)
		        .setParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(),
		            PipelineVariables.RESOURCE_PATH + PipelineVariables.RESOURCE_RELATION_TIMESTAMP)
		        .setParameterSetting(AnnotationPatternAnnotator.Param.OUTPUT_TYPE.getName(),
		            PipelineVariables.TYPE_RELATION_TIMESTAMP)
		        .addTypeSystemDescription(types));

		aggregate.addDelegate(new VitalsExtractorAnnotator().getLeoAEDescriptor()
		    .addTypeSystemDescription(types));

		// Remove overannotated    
		for (String a : PipelineVariables.valueTypes) {
			aggregate.addDelegate(new AnnotationFilter()
			    .getLeoAEDescriptor()
			    .setName("AnnotationFilter")
			    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] { a })
			    .addTypeSystemDescription(types));
		}

		// FIXME: Add a feature vector AE - for all Hr_values

		aggregate.addDelegate(new HrVectorAnnotator()
		    .getLeoAEDescriptor()
		    .setName("HrVectorAnnotator")
		    .addParameterSetting(HrVectorAnnotator.Param.INPUT_TYPE.getName(), false, true, "String",
		        new String[] { Hr_value.class.getCanonicalName(), HRValue.class.getCanonicalName() })
		    .addParameterSetting(HrVectorAnnotator.Param.OUTPUT_TYPE.getName(), false, false, "String",
		        LearningVariables.TYPE_FeatureVector)
		    .addParameterSetting(HrVectorAnnotator.Param.KEY_FEATURE_PARAM.getName(), false, false, "String",
		        "keys")
		    .addParameterSetting(HrVectorAnnotator.Param.VALUE_FEATURE_PARAM.getName(), false, false, "String",
		        "values")
		    .addTypeSystemDescription(types));

		if (GeneralSettings.ENVIRONMENT.equalsIgnoreCase("predict")) {
			aggregate.addDelegate(LearningAnnotator.getLeoAEDescriptor(
			    SvmVectorTranslator.class.getCanonicalName(),
			    LearningVariables.TYPE_Prediction, "srcFVFeature", "prediction",
			    LearningVariables.TYPE_FeatureVector,
			    "keys", "values", LearningVariables.Hr_SvmModelPath).addTypeSystemDescription(types));
			;

			aggregate.addDelegate(new FilterHeartRateAnnotator().getLeoAEDescriptor()
			    .setName("FilterHeartRateAnnotator")
			    .addTypeSystemDescription(types));
		}
		return aggregate;
	}

	/**
	 * 
	 * @param generateTypes
	 * @return
	 * @throws Exception
	 */
	protected LeoTypeSystemDescription createTypeSystem() throws Exception {
		LeoTypeSystemDescription types = new LeoTypeSystemDescription();
		types.addType(TypeLibrarian.getCSITypeSystemDescription());
		// Adding all knowtator annotations to the type list
		for (String type : KnowtatorVariables.uimaTypeFeatureMap.keySet()) {
			TypeDescription newType;
			newType = new TypeDescription_impl(type, "", "uima.tcas.Annotation");
			for (String feature : KnowtatorVariables.uimaTypeFeatureMap
			    .get(type)) {
				newType.addFeature(feature, "", "uima.cas.String");
			}
			types.addType(newType);
		}
		types.addType(TypeLibrarian.getRelationshipAnnotationTypeSystemDescription());
		types.addType("gov.va.vinci.knowtator.types.RelationshipAnnotation",
		    "", "gov.va.vinci.leo.types.RelationshipAnnotation");

		// Regex default type
		types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());

		TypeDescription numType = new TypeDescription_impl(PipelineVariables.TYPE_NUMERIC, "",
		    PipelineVariables.RegexType);
		// numType.addFeature("concept", "", "uima.cas.String");  -- already set 
		numType.addFeature("value1", "", "uima.cas.String");
		numType.addFeature("value2", "", "uima.cas.String");
		//numType.addFeature("valueType", "", "uima.cas.String");
		numType.addFeature("unit", "", "uima.tcas.Annotation");
		numType.addFeature("source", "", "uima.cas.String");
		numType.addFeature("timestamp", "", "uima.tcas.Annotation");

		types.addType(PipelineVariables.TYPE_UNIT, "", PipelineVariables.RegexType);
		types.addType(PipelineVariables.TYPE_TERM, "", PipelineVariables.RegexType);
		types.addType(numType);
		for (Entry<String, String> a : PipelineVariables.regexResourceToType
		    .entrySet()) {
			types.addType(a.getValue(), "", PipelineVariables.RegexType);
		}

		// APA default type
		TypeDescription newType = new TypeDescription_impl(PipelineVariables.PatternType, "",
		    "uima.tcas.Annotation");
		newType.addFeature("pattern", "", "uima.cas.String");
		newType.addFeature("anchor", "", "uima.tcas.Annotation");
		newType.addFeature("target", "", "uima.tcas.Annotation");
		newType.addFeature("anchorPattern", "", "uima.cas.String");
		newType.addFeature("targetPattern", "", "uima.cas.String");
		types.addType(newType);
		for (Entry<String, String> a : PipelineVariables.apaResourceToType.entrySet()) {
			types.addType(a.getValue(), "", PipelineVariables.PatternType);
		}

		types.addType(PipelineVariables.TYPE_RELATION, "", PipelineVariables.PatternType);
		types.addType(PipelineVariables.TYPE_RELATION_TIMESTAMP, "", PipelineVariables.PatternType);

		types.addType(TypeDescriptionBuilder
		    .create(LearningVariables.TYPE_FeatureVector, "Type used to store the fearures and values",
		        "uima.tcas.Annotation")
		    .addFeature("keys", "", "uima.cas.StringArray")
		    .addFeature("values", "", "uima.cas.StringArray")
		    .addFeature("context", "", "uima.tcas.Annotation")
		    .getTypeDescription());

		types.addType(TypeDescriptionBuilder
		    .create(LearningVariables.TYPE_Prediction, "Type used to output predictions", "uima.tcas.Annotation")
		    .addFeature("srcFVFeature", "Feature vector annotation", "uima.tcas.Annotation")
		    .addFeature("prediction", "", "uima.cas.String")
		    .getTypeDescription());

		/* Additional annotations for specific values */

		TypeDescription outType = new TypeDescription_impl(PipelineVariables.TYPE_OUTPUT, "",
		    "uima.tcas.Annotation");
		outType.addFeature("value1", "", "uima.cas.String");
		outType.addFeature("value2", "", "uima.cas.String");
		outType.addFeature("concept", "", "uima.cas.String");
		outType.addFeature("unit", "", "uima.tcas.Annotation");
		outType.addFeature("source", "", "uima.cas.String");
		outType.addFeature("timestamp", "", "uima.tcas.Annotation");
		types.addType(outType);

		for (String a : PipelineVariables.valueTypes) {
			types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_OUTPUT));
		}

		/*****/

		if (GeneralSettings.GENERATE_TYPES) {
			log.info("Generating types! ");
			types.jCasGen("src/main/java/", "target/classes");
		}
		return types;
	}
}
