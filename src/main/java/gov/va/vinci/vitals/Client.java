package gov.va.vinci.vitals;

import java.util.Date;
import java.util.List;

import gov.va.vinci.leo.listener.BaseListener;
import gov.va.vinci.leo.tools.Common;
import groovy.util.ConfigObject;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.uima.collection.CollectionReader;

/**
 * 
 * @author OVP
 * 
 * This client is general enough that it does not need to be changed for most new projects. Use as is.
 *
 */
public class Client {

	public static Logger log = Logger.getLogger(Common.getRuntimeClass().toString());

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

	public void run(String environment) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		log.info(" Starting " + this.getClass().getCanonicalName() + "  at " + new Date(sw.getStartTime()));
		ConfigObject config = Utils.loadConfigFile(environment,
		    "CommonConfig.groovy",
		    "ClientConfig.groovy");

		log.info("Loading properties took " + sw.toString() + " seconds.");

		//  Creating a general client
		gov.va.vinci.leo.Client myClient = new gov.va.vinci.leo.Client();
		myClient.setInputQueueName((String) config.get("serviceQueueName"));
		myClient.setBrokerURL((String) config.get("brokerUrl"));
		myClient.setCasPoolSize((Integer) config.get("casPoolSize"));

		// Creating reader
		CollectionReader myReader = (CollectionReader) config.get("collectionReader");
		myClient.setCollectionReader(myReader);
		log.info(" Reader " + myReader.getConfigParameterValue("SubClassName") );

		// Creating listeners
		@SuppressWarnings("unchecked")
		List<BaseListener> listenerList = (List<BaseListener>) config.get("listeners");
		BaseListener[] listeners = new BaseListener[listenerList.size()];
		listenerList.toArray(listeners);
		for (BaseListener a : listeners) {
			log.info(" Listener " + a.getClass().getCanonicalName());
		}
		//  Running the client
		myClient.run(listeners);

		// Client run is completed
		log.info("Processing time: " + sw.toString() + "\n" +
		    "Processing ended at: " + new Date(System.currentTimeMillis()));
	}
}