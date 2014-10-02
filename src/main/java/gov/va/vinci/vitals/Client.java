package gov.va.vinci.vitals;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;
import gov.va.vinci.leo.cr.FileCollectionReader;
import gov.va.vinci.leo.cr.LeoCollectionReaderInterface;
import gov.va.vinci.leo.listener.BaseListener;
import gov.va.vinci.leo.listener.SimpleCsvListener;
import gov.va.vinci.leo.listener.SimpleXmiListener;
import gov.va.vinci.leo.model.DatabaseConnectionInformation;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.tools.TextFilter;
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
		simpleCsv, csv, xmi, compare, aucompare, knowtator, database;
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

	@SuppressWarnings("unchecked")
	public void run(String environment) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info(" Starting " + this.getClass().getCanonicalName() + "  at "
		    + new Date(sw.getStartTime()));
		ConfigObject config = Utils.loadConfigFile(environment,
		    "KnowtatorConfig.groovy", "CommonConfig.groovy",
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

		// INFO: Creating reader
		String readerType = (String) config.get("readerType");
		if (readerType.equalsIgnoreCase(READERS.knowtator.name())) {
			ReaderVariables.useKnowtatorReader = true;
		} else if (readerType.equalsIgnoreCase(READERS.database.name())) {
			ReaderVariables.useDatabaseReader = true;
		} else if (readerType.equalsIgnoreCase(READERS.knowtator.name())) {
			ReaderVariables.useFileReader = true;
		} else {
			log.fatal("Reader was not selected. Reader type is " + readerType);
		}
		CollectionReader reader = null;
		if (ReaderVariables.useKnowtatorReader) {
			String kttrCorpus = (String) config.get("knowtatorCorpusPath");
			String kttrSaved = (String) config.get("knowtatorXmlPath");
			createKnowtatorToUimaMap(config);
			reader = (CollectionReader) new KnowtatorCollectionReader(new File(
			    kttrCorpus), new File(kttrSaved),
			    KnowtatorVariables.knowtatorToUimaMap, true)
			    .produceCollectionReader();

		} else if (ReaderVariables.useDatabaseReader) {
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String server = "";
			String dbsName = "";
			String username = "";
			String password = "";
			String query = "";
			String idColumn = "";
			String noteColumn = "";
			int minRecordNumber = 0;
			int maxRecordNumber = 0;
			int batchSize = 0;
			String url = "jdbc:sqlserver://" + server + ":1433;databasename="
			    + dbsName + ";integratedSecurity=true";
			reader = new BatchDatabaseCollectionReader(driver, url, username,
			    password, query, idColumn, noteColumn, minRecordNumber,
			    maxRecordNumber, batchSize).produceCollectionReader();

		} else if (ReaderVariables.useFileReader) {
			File inputDirectory = null;
			boolean recurse = false;
			TextFilter[] filterList = null;
			reader = new FileCollectionReader(inputDirectory, recurse,
			    filterList).produceCollectionReader();

		} else if (ReaderVariables.useCustomReader) {
			// TODO: Update this if ever needed
		} // / End Reader selection

		if (reader != null) {
			myClient.setLeoCollectionReader((LeoCollectionReaderInterface) reader);
		} else {
			log.fatal("Reader was not set! Reader type is " + readerType);
			System.exit(0);
		}

		// INFO: Creating listeners

		ArrayList<BaseListener> listenerList = new ArrayList<BaseListener>();

		String strListenerTypes = (String) config.get("listenerTypes");
		String[] listenerTypes = null;
		if (StringUtils.isNotBlank(strListenerTypes))
			listenerTypes = strListenerTypes.split("\\|");

		if (listenerTypes != null) {
			for (String type : listenerTypes) {
				if (type.equalsIgnoreCase(LISTENERS.knowtator.name())) {
				}
				if (type.equalsIgnoreCase(LISTENERS.database.name())) {
					String driver = (String) config.get("sqlDriver");
					String url = (String) config.get("connectionURL");
					String dbUser = "";
					String dbPwd = "";
					DatabaseConnectionInformation dbi = new DatabaseConnectionInformation(
					    driver, url, dbUser, dbPwd);

					String dbsName = (String) config.get("projectDbsName");
					String tableName = (String) config.get("outTableName");
					int batchSize = (Integer) config.get("outBatchSize");

					ArrayList<ArrayList<String>> fieldList = (ArrayList<ArrayList<String>>) config.get("dbFieldList");

					DbsListener listener = DbsListener.createNewListener(dbi, dbsName, tableName, batchSize, fieldList);
					listener.createTable(dbi, listener.createStatement, false, tableName);
					listenerList.add(listener);
				}
				// INFO: XMI Listener

				if (type.equalsIgnoreCase(LISTENERS.xmi.name())) {
					SimpleXmiListener listener = null;

					String xmiPath = ((String) config.get("xmiOutPath"))
					    .replaceAll("\\{suffix\\}", timeStamp);
					File xmiPathFile = new File(xmiPath);
					if (!xmiPathFile.exists())
						xmiPathFile.mkdirs();

					Boolean openViewer = (Boolean) config.get("openViewerAfterProcessing");
					listener = new SimpleXmiListener(xmiPathFile, openViewer);

					ArrayList<String> annotationsOut = (ArrayList<String>) config.get("xmiOutputTypeList");
					String[] annotationTypeFilter = new String[annotationsOut.size()];
					annotationsOut.toArray(annotationTypeFilter);
					if (annotationTypeFilter != null) {
						if (annotationTypeFilter.length != 0) {
							listener.setAnnotationTypeFilter(annotationTypeFilter);
						}
					}
					listenerList.add(listener);
				}

				// INFO: Adding CSV listeners
				if (type.equalsIgnoreCase(LISTENERS.csv.name())) {
					CsvListener listener = null;
					String csvPath = ((String) config.get("csvFileName")).replaceAll("\\{suffix\\}", timeStamp);
					ArrayList<ArrayList<String>> fieldList = (ArrayList<ArrayList<String>>) config.get("csvFieldList");
					if (!(new File(csvPath).getParentFile().exists()))
						new File(csvPath).getParentFile().mkdirs();
					listener = new CsvListener(new File(csvPath), fieldList);
					listener.writeHeaders();
					listenerList.add(listener);
				}

				if (type.equalsIgnoreCase(LISTENERS.compare.name())) {
				}
				// TODO: AuCompare
				if (type.equalsIgnoreCase(LISTENERS.aucompare.name())) {
					SimpleCompareListener listener = null;
					String csvPath = ((String) config.get("csvFileName")).replaceAll("\\{suffix\\}", timeStamp)
					    + "_Compare.csv";
					HashMap<String, String> comparePairs = new HashMap<String, String>();
					comparePairs.put(BPValue.class.getCanonicalName(), Bp_value.class.getCanonicalName());
					comparePairs.put(TValue.class.getCanonicalName(), T_value.class.getCanonicalName());
					comparePairs.put(HRValue.class.getCanonicalName(), Hr_value.class.getCanonicalName());

					if (!(new File(csvPath).getParentFile().exists()))
						new File(csvPath).getParentFile().mkdirs();
					listener = new SimpleCompareListener(comparePairs, new File(csvPath));
					listenerList.add(listener);

					/**
						gov.va.vinci.leo.listener.AuSummaryListener listener = null;
						HashMap<String, String> auMap = ((HashMap<String, String>) config.get("auMap"));
						if (auMap == null) {
							log.error("Error getting the mapping string for the gold compare listener, NOT initializing!");
						}
						listener = new gov.va.vinci.leo.listener.AuSummaryListener(auMap);

						listenerList.add(listener);
						String csvPath = ((String) config.get("csvFileName")).replaceAll("\\{suffix\\}", timeStamp);
						
						if (!(new File(csvPath).getParentFile().exists()))
							new File(csvPath).getParentFile().mkdirs();
						gov.va.vinci.leo.listener.AuCompareCSVListener listener2 = new gov.va.vinci.leo.listener.AuCompareCSVListener(		    auMap, new File(csvPath));
						listenerList.add(listener2);
						*/
				}
				// INFO: SimpleCSV
				if (type.equalsIgnoreCase(LISTENERS.simpleCsv.name())) {
					SimpleCsvListener listener = null;
					HashMap<String, ArrayList<String>> simpleListenerTypes = (HashMap<String, ArrayList<String>>) config
					    .get("simpleCsvOutTypes");
					String csvDirPath = ((String) config.get("csvOutPath")).replaceAll("\\{suffix\\}", timeStamp);
					if (!(new File(csvDirPath).exists()))
						new File(csvDirPath).mkdirs();
					if (simpleListenerTypes != null) {
						for (String outFileName : simpleListenerTypes.keySet()) {
							String filePathString = csvDirPath + "\\" + outFileName;

							String[] listenerOutTypes = new String[simpleListenerTypes.get(outFileName).size()];
							listenerOutTypes = simpleListenerTypes.get(outFileName).toArray(listenerOutTypes);
							listener = new SimpleCsvListener(new File(filePathString), true, listenerOutTypes);
							listenerList.add(listener);
						}
					}
				}
			}
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
