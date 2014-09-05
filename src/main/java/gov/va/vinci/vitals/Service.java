package gov.va.vinci.vitals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map.Entry;
import gov.va.vinci.knowtator.model.KnowtatorToUimaTypeMap;

import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.types.TypeLibrarian;
import gov.va.vinci.vitals.ae.AnnotationFilter;
import groovy.util.ConfigObject;

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

	public static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	public static class GeneralSettings {
		static String ENVIRONMENT = "simple";
		static int CAS_POOL_SIZE = 8;
		static boolean START_CLIENT = false;
		static boolean GENERATE_TYPES = true;
		static String SERVICE_NAME = "DefaultServiceName";
		static String BROKER_URL = "tcp://localhost:61616";
	}

	public static class KnowtatorVariables {
		public static HashMap<String, ArrayList<String>> uimaTypeFeatureMap = new HashMap<String, ArrayList<String>>();

	}

	public static class PipelineVariables {
		static String RESOURCE_PATH = "src/main/resources/";
		static String PatternType = "gov.va.vinci.vitals.types.Pattern";
		static String RegexType = "gov.va.vinci.vitals.types.RegularExpression";
		static String LogicType = "gov.va.vinci.vitals.types.Logic";
		static HashMap<String, String> regexResourceToType = new HashMap<String, String>();

		static {
			regexResourceToType.put("gov.va.vinci.vitals.types.Indicator", "indicator.regex");
			regexResourceToType.put("gov.va.vinci.vitals.types.Numeric", "numericValues.regex");
			regexResourceToType.put("gov.va.vinci.vitals.types.Unit", "units.regex");
			regexResourceToType.put("gov.va.vinci.vitals.types.NumericExclude", "numericValuesExclude.regex");
			regexResourceToType.put("gov.va.vinci.vitals.types.Term", "terms.regex");
			regexResourceToType.put("gov.va.vinci.vitals.types.TermExcludeRegex", "termsExclude.regex");
		}

		static HashMap<String, String> apaResourceToType = new HashMap<String, String>();
		static {
			apaResourceToType.put("gov.va.vinci.vitals.types.NumericExclude", "numericValuesExclude.pattern");
			apaResourceToType.put("gov.va.vinci.vitals.types.TermExclude", "termsExclude.pattern");
			apaResourceToType.put("gov.va.vinci.vitals.types.Relation", "relation.pattern");
		}

		static HashMap<String, String[]> filterTypes = new HashMap<String, String[]>();
		static {
			filterTypes.put("gov.va.vinci.vitals.types.NumericExclude",
			    new String[] { "gov.va.vinci.vitals.types.Numeric" });
			filterTypes.put("gov.va.vinci.vitals.types.TermExclude",
			    new String[] { "gov.va.vinci.vitals.types.Term" });
			filterTypes.put("gov.va.vinci.vitals.types.Numeric",
			    new String[] { "gov.va.vinci.vitals.types.Numeric" });
			filterTypes.put("gov.va.vinci.vitals.types.Term",
			    new String[] { "gov.va.vinci.vitals.types.Term" });
		}
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
	 * run method loads 
	 * 
	 * @param environment
	 * @throws Exception
	 */
	public void run() throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("Starting the server   " + this.getClass().getCanonicalName() + " at "
		    + new Date(sw.getStartTime()));
		ConfigObject config = Utils.loadConfigFile(GeneralSettings.ENVIRONMENT,
		    "KnowtatorConfig.groovy",
		    "CommonConfig.groovy",
		    "ServerConfig.groovy");

		loadProperties(config);
		// Create Service object
		gov.va.vinci.leo.Service service = new gov.va.vinci.leo.Service();
		service.setInputQueueName(GeneralSettings.SERVICE_NAME);
		service.setServiceName(GeneralSettings.SERVICE_NAME);
		service.setBrokerURL(GeneralSettings.BROKER_URL);
		service.setCasPoolSize(GeneralSettings.CAS_POOL_SIZE);

		//INFO: Deploy the Service

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

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadProperties(ConfigObject config) {
		GeneralSettings.SERVICE_NAME = (String) config.get("serviceQueueName");
		GeneralSettings.BROKER_URL = (String) config.get("brokerUrl");
		GeneralSettings.CAS_POOL_SIZE = (Integer) config.get("casPoolSize");
		GeneralSettings.START_CLIENT = (Boolean) config.get("startClient");

		@SuppressWarnings("unchecked")
		HashMap<String, String> typeList = (HashMap<String, String>) config.get("knowtatorToUimaTypeMap");
		HashMap<String, HashMap<String, String>> featureList = new HashMap<String, HashMap<String, String>>();

		try {
			if (config.get("knowtatorToUimaFeatureMap") instanceof HashMap) {
				if (((HashMap) config.get("knowtatorToUimaFeatureMap")).size() > 0) {
					featureList.putAll((HashMap<String, HashMap<String, String>>) config
					    .get("knowtatorToUimaFeatureMap"));

				} else {
					log.warn("No features were added to the types!");
				}
			} else {
				log.warn("No features were added to the types!");
			}

		} catch (Exception e) {
			log.warn("No features were added to the types!");
		}
		//Deploy the Service
		loadTypeMap(typeList, featureList);

	}

	/**
	* 
	* @param typeList  -- HashMap<String, String> typeList -- HashMap <Knowtator_Annotation , UIMA_Annotation>
	* @param featureList -- HashMap<String, HashMap<String, String>> featureList -- HashMap<Knowtator_Annotation, HashMap<Knowtator_attribute, UIMA_feature>> featureList
	* @return
	*/
	private void loadTypeMap(HashMap<String, String> typeList,
	    HashMap<String, HashMap<String, String>> featureList) {
		if (typeList != null && featureList != null) {
			// For all knowtator annotations, need to create UIMA annotation
			for (String kttrType : typeList.keySet()) {
				String uimaType = typeList.get(kttrType);
				ArrayList<String> uimaFeatures = new ArrayList<String>();
				// If a knowtator Annotation has an attribute, create a String feature in the UIMA annotation type
				if (featureList.containsKey(kttrType)) {
					for (String kttrFeature : featureList.get(kttrType).keySet()) {
						uimaFeatures.add(featureList.get(kttrType).get(kttrFeature));
					}
				}
				KnowtatorVariables.uimaTypeFeatureMap.put(uimaType, uimaFeatures);
			}
		}
	}

	/**
	 * createPipeline defines all the parts of the pipeline
	 * @param types 
	 * 
	 * @param generateTypes
	 * @return
	 * @throws Exception
	 */
	protected LeoAEDescriptor createPipeline(LeoTypeSystemDescription types) throws Exception {
		LeoAEDescriptor aggregate = new LeoAEDescriptor();

		//INFO: Create initial annotations
		int i = 0;
		for (Entry<String, String> a : PipelineVariables.regexResourceToType.entrySet()) {
			i++;
			aggregate
			    /*  Concept recognition using Regular Expression annotator -- term.regex */
			    .addDelegate(new RegexAnnotator()
			        .getLeoAEDescriptor()
			        .setName("RegexAnnotator" + i)
			        .setParameterSetting(RegexAnnotator.Param.INPUT_TYPE.getName(), null)
			        .setParameterSetting(RegexAnnotator.Param.OUTPUT_TYPE.getName(), a.getKey())
			        .setParameterSetting(RegexAnnotator.Param.MATCHED_PATTERN_FEATURE_NAME.getName(), "Pattern")
			        .setParameterSetting(RegexAnnotator.Param.RESOURCE.getName(),
			            PipelineVariables.RESOURCE_PATH + a.getValue())
			        .setParameterSetting(RegexAnnotator.Param.CASE_SENSITIVE.getName(), false)
			        .setParameterSetting(RegexAnnotator.Param.WORD_BOUNDARY.getName(), false)
			        .addTypeSystemDescription(types));
		}
		// INFO: Filter overannotated instances
		aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
		    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] {
		        "gov.va.vinci.vitals.types.Numeric" })
		    .addTypeSystemDescription(types));
		aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
		    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] {
		        "gov.va.vinci.vitals.types.Term" })
		    .addTypeSystemDescription(types));

		// INFO:  Created annotators to find patterns.
		i = 0;
		for (Entry<String, String> a : PipelineVariables.apaResourceToType.entrySet()) {
			i++;
			//  Pattern detection AnnotationPatternAnnotation -- context.pattern   */
			aggregate.addDelegate(new AnnotationPatternAnnotator()
			    .getLeoAEDescriptor()
			    .setName("PatternAnnotator" + i)
			    .setParameterSetting(AnnotationPatternAnnotator.Param.RESOURCE.getName(),
			        PipelineVariables.RESOURCE_PATH + a.getValue())
			    .setParameterSetting(AnnotationPatternAnnotator.Param.OUTPUT_TYPE.getName(),
			        a.getKey())
			    .addTypeSystemDescription(types));
		}
		// INFO: Filter unneeded annotations*/
		aggregate.addDelegate(new AnnotationFilter().getLeoAEDescriptor()
		    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] {
		        "gov.va.vinci.vitals.types.Relation" })
		    .setParameterSetting(AnnotationFilter.Param.REMOVE_OVERLAPPING.getName(), true)
		    .addTypeSystemDescription(types));
		aggregate.addDelegate(new AnnotationFilter()
		    .getLeoAEDescriptor()
		    .setParameterSetting(AnnotationFilter.Param.TYPES_TO_KEEP.getName(), new String[] {
		        "gov.va.vinci.vitals.types.NumericExclude", "gov.va.vinci.vitals.types.TermExclude"
		        , "gov.va.vinci.vitals.types.TermExcludeRegex" })
		    .setParameterSetting(
		        AnnotationFilter.Param.TYPES_TO_DELETE.getName(),
		        new String[] {
		            "gov.va.vinci.vitals.types.Numeric", "gov.va.vinci.vitals.types.Term",
		            "gov.va.vinci.vitals.types.Relation" })
		    .addTypeSystemDescription(types));

		aggregate.setNumberOfInstances(GeneralSettings.CAS_POOL_SIZE);
		return aggregate;
	}

	/**
	 * 
	 * @param generateTypes
	 * @return
	 * @throws Exception
	 */
	protected LeoTypeSystemDescription createTypeSystem()
	    throws Exception {
		LeoTypeSystemDescription types = new LeoTypeSystemDescription();

		// Adding all knowtator annotations to the type list
		for (String type : KnowtatorVariables.uimaTypeFeatureMap.keySet()) {
			TypeDescription newType;
			newType = new TypeDescription_impl(type, "", "uima.tcas.Annotation");
			for (String feature : KnowtatorVariables.uimaTypeFeatureMap.get(type)) {
				newType.addFeature(feature, "", "uima.cas.String");
			}
			types.addType(newType);
		}
		types.addType(TypeLibrarian.getRelationshipAnnotationTypeSystemDescription());
		types.addType("gov.va.vinci.knowtator.types.RelationshipAnnotation", "",
		    "gov.va.vinci.leo.types.RelationshipAnnotation");

		// FIXME:  types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
		TypeDescription newType;

		//Regex default type
		newType = new TypeDescription_impl(PipelineVariables.RegexType, "", "uima.tcas.Annotation");
		newType.addFeature("Pattern", "", "uima.cas.String");
		types.addType(newType);

		for (Entry<String, String> a : PipelineVariables.regexResourceToType.entrySet()) {
			types.addType(a.getKey(), "", PipelineVariables.RegexType);
		}

		// APA default type	
		newType = new TypeDescription_impl(PipelineVariables.PatternType, "", "uima.tcas.Annotation");
		newType.addFeature("pattern", "", "uima.cas.String");
		newType.addFeature("anchor", "", "uima.tcas.Annotation");
		newType.addFeature("target", "", "uima.tcas.Annotation");
		newType.addFeature("anchorPattern", "", "uima.cas.String");
		newType.addFeature("targetPattern", "", "uima.cas.String");
		types.addType(newType);

		for (Entry<String, String> a : PipelineVariables.apaResourceToType.entrySet()) {
			types.addType(a.getKey(), "", PipelineVariables.PatternType);
		}

		/**/

		newType = new TypeDescription_impl(PipelineVariables.LogicType, "", "uima.tcas.Annotation");
		newType.addFeature("VitalType", "", "uima.cas.String");
		newType.addFeature("VitalTerm", "", "uima.tcas.Annotation");
		newType.addFeature("VitalValue", "", "uima.tcas.Annotation");
		newType.addFeature("ValueString", "", "uima.cas.String");
		newType.addFeature("Unit", "", "uima.cas.String");
		types.addType(newType);
		/**/
		if (GeneralSettings.GENERATE_TYPES) {
			types.jCasGen("src/main/java/", "target/classes");
		}
		return types;
	}
}
