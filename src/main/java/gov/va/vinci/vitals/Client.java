package gov.va.vinci.vitals;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;
import gov.va.vinci.leo.cr.FileCollectionReader;
import gov.va.vinci.leo.cr.LeoCollectionReaderInterface;
import gov.va.vinci.leo.listener.*;
import gov.va.vinci.leo.model.ChexSimanDataSourceConfiguration;
import gov.va.vinci.leo.model.DatabaseConnectionInformation;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.tools.TextFilter;
import gov.va.vinci.svmlib.ml.SvmVectorTranslator;
import gov.va.vinci.vitals.listeners.*;
import gov.va.vinci.vitals.types.*;
import groovy.util.ConfigObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.uima.collection.CollectionReader;

import gov.va.vinci.knowtator.cr.KnowtatorCollectionReader;
import gov.va.vinci.knowtator.model.KnowtatorToUimaTypeMap;
import gov.va.vinci.kttr.types.*;

/**
 * 
 * @author OVP
 * 
 *         This client is general enough that it does not need to be changed for
 *         most new projects. Use as is.
 * 
 */
public class Client {
	public static Logger log = Logger.getLogger(LeoUtils.getRuntimeClass()
	    .toString());

	public static class GeneralSettings {
		static String ENVIRONMENT = "simple";
		static int CAS_POOL_SIZE = 8;
		static String SERVICE_NAME = "DefaultServiceName";
		static String BROKER_URL = "tcp://localhost:61616";
	}

	public enum READERS { // INFO: enum READERS
		knowtator, file, database
	};

	/**
	 * The following listeners are possible: SimpleCsvListener SimpleXmiListener
	 * VitalsCsvListener - Sharing ListenerLogic VitalsDbListener - Sharing
	 * ListenerLogic SimanChexListener KnowtatorListener CustomListener
	 */
	public enum LISTENERS { // INFO: enum LISTENERS
		simpleCsv, csv, xmi, compare, aucompare, knowtator, database, chex, training;
	}

	public static class KnowtatorVariables {
		public static KnowtatorToUimaTypeMap knowtatorToUimaMap = new KnowtatorToUimaTypeMap();
	}

	public static class ListenerVariables {

		static Boolean addSimpleCsvListeners = false;
		static HashMap<String, String> simpleCsvTypesFilesMap = new HashMap<String, String>();

		static Boolean addXmiListener = false;
		static String[] xmiOutputTypes = null;
		static String xmiOutputPath = "";

		static boolean useKnowtatorListener = false;
		static String[] knowtatorOutTypes = null;
		static String knowtatorOutPath = "";

		static boolean useTrainingListener = false;
		static String hrValidationMap;
		static String hrSvmModelPath = "src/resources/hr_model.svm";

	}

	public static class ReaderVariables {
		static Boolean useKnowtatorReader = false;
		static Boolean useDatabaseReader = false;
		static Boolean useFileReader = false;
		static Boolean useCustomReader = false;
	}

	public static void main(String[] args) {
		String environment = "simple";

		if (args.length > 0) {
			environment = args[0];
		}

		try {
			new Client().run(environment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	@SuppressWarnings({ "unchecked" })
	public void run(String environment) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info(" Starting " + this.getClass().getCanonicalName() + "  at "
		    + new Date(sw.getStartTime()));
		ConfigObject config = Utils.loadConfigFile(environment,
		    "KnowtatorConfig.groovy",
		    "CommonConfig.groovy",
		    "ClientConfig.groovy");
		loadProperties(config);
		log.info("Loading properties took " + sw.toString() + " seconds.");
		String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]",
		    "_");
		// INFO: Creating a general client
		gov.va.vinci.leo.Client myClient = new gov.va.vinci.leo.Client();
		myClient.setInputQueueName(GeneralSettings.SERVICE_NAME);
		myClient.setServiceName(GeneralSettings.SERVICE_NAME);
		myClient.setBrokerURL(GeneralSettings.BROKER_URL);
		myClient.setCasPoolSize(1);

		CollectionReader reader = (CollectionReader) config.get("collectionReader");
		if ( reader == null) {
			throw new IllegalArgumentException("Collection reader not set in client config.");
		}

		myClient.setLeoCollectionReader((LeoCollectionReaderInterface) reader);

		// INFO: Listeners
		ArrayList<BaseListener> listenerList = (ArrayList<BaseListener>) config.get("listeners");
		if (listenerList == null) {
			throw new IllegalArgumentException("No listeners defined in client config.");
		}

		BaseListener[] listeners = new BaseListener[listenerList.size()];
		listenerList.toArray(listeners);
		for (BaseListener a : listeners) {
			log.info(" Listener " + a.getClass().getCanonicalName());
		}

		// Running the client
		myClient.run(listeners);


		// Client run is completed
		log.info("Processing time: " + sw.toString() + "\n"
		    + "Processing ended at: "
		    + new Date(System.currentTimeMillis()));

	}

	/**
	 * 
	 * @param config
	 */
	private void loadProperties(ConfigObject config) {
		GeneralSettings.SERVICE_NAME = (String) config.get("serviceQueueName");
		GeneralSettings.BROKER_URL = (String) config.get("brokerUrl");
		GeneralSettings.CAS_POOL_SIZE = (Integer) config.get("casPoolSize");

	}

	/**
	 * @param featureList
	 *            -- HashMap<String, HashMap<String, String>> featureList --
	 *            HashMap<Knowtator_Annotation, HashMap<Knowtator_attribute,
	 *            UIMA_feature>> featureList
	 * @return
	 */
	private static HashMap<String, ArrayList<String>> getUimaTypeMap(
	    HashMap<String, HashMap<String, String>> featureList) {
		HashMap<String, ArrayList<String>> typeFeatureMap = new HashMap<String, ArrayList<String>>();
		// For each UIMA type, check if the corresponding Knowtator class has
		// attributes.
		for (String uimaType : KnowtatorVariables.knowtatorToUimaMap
		    .getUimaTypes()) {
			String kttrType = KnowtatorVariables.knowtatorToUimaMap
			    .getKnowtatorType(uimaType);
			ArrayList<String> uimaFeatures = new ArrayList<String>();
			// If a knowtator class has an attribute, create a String feature in
			// the UIMA annotation type
			if (featureList.containsKey(kttrType)) {
				for (String kttrFeature : featureList.get(kttrType).keySet()) {
					uimaFeatures
					    .add(featureList.get(kttrType).get(kttrFeature));
				}
			}
			typeFeatureMap.put(uimaType, uimaFeatures);
		}
		return typeFeatureMap;
	}

	@SuppressWarnings("unchecked")
	private void createKnowtatorToUimaMap(ConfigObject config) {
		try {
			HashMap<String, String> typeList = (HashMap<String, String>) config
			    .get("knowtatorToUimaTypeMap");
			HashMap<String, HashMap<String, String>> featureList = new HashMap<String, HashMap<String, String>>();

			try {
				if (config.get("knowtatorToUimaFeatureMap") instanceof HashMap) {
					if (((HashMap) config.get("knowtatorToUimaFeatureMap"))
					    .size() > 0) {
						featureList
						    .putAll((HashMap<String, HashMap<String, String>>) config
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
			if (typeList != null) {
				for (java.util.Map.Entry<String, String> types : typeList
				    .entrySet()) {
					String knowtatorType = types.getKey();
					String uimaType = types.getValue();
					KnowtatorVariables.knowtatorToUimaMap.addAnnotationTypeMap(
					    knowtatorType, uimaType);
					if (featureList.containsKey(knowtatorType)) {
						for (Entry<String, String> featureEntry : featureList
						    .get(knowtatorType).entrySet()) {
							String knowtatorName = featureEntry.getKey();
							String uimaName = featureEntry.getValue();
							KnowtatorVariables.knowtatorToUimaMap
							    .addFeatureTypeMap(knowtatorType,
							        knowtatorName, uimaName);
						}
					}
				}
			}// end if typeList == null
		} catch (Exception e) {
			log.warn("No knowtator map was added to the types!");
		}
	}
}
