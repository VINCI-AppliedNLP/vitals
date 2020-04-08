package gov.va.vinci.vitals;


import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.pipeline.PipelineInterface;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.vitals.pipeline.VitalsPipeline;
import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * The Leo service that executes the pipeline.
 */
public class Service {
	public static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());
	
	
	File[] serviceConfigFile;
	int numberOfInstances = 1;
	boolean isAsync = false;
	boolean createTypes = false;
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		File[] configFiles = new File[]{new File("config/ServiceConfig.groovy")};
		
		try {
			new Service().run(configFiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(File[] configFiles) {
		serviceConfigFile = configFiles;
		
		gov.va.vinci.leo.Service service = null;
		
		try {
			service = new gov.va.vinci.leo.Service();
			
			setServerProperties(service);

			//deleteFilesFromDescriptorDirectory(service);
			LeoAEDescriptor aggregate = null;
			log.info("Using Vitals Pipe");
			PipelineInterface pipe = new VitalsPipeline();
			aggregate = pipe.getPipeline();
			
			/**
			 * If the type system does not exist in your code, or has been changed, run JCasGen to re-generate the
			 * type classes. This can safely be run anytime, though adds a bit of overhead to startup time of the service.
			 */
			if (createTypes) {
				pipe.getLeoTypeSystemDescription().jCasGen("src/main/java/", "target/classes");
				pipe.getLeoTypeSystemDescription().toXML("config/TypeSystem.xml");
			}
			aggregate.setIsAsync(isAsync);
			aggregate.setNumberOfInstances(numberOfInstances);
			
			
			/* Deploy the service. */
			service.deploy(aggregate);
			
			System.out.println("\r\nDeployment: " + service.getDeploymentDescriptorFile());
			System.out.println("Aggregate: " + service.getAggregateDescriptorFile());
			
			System.out.println("Service running, press enter in this console to stop.");
			System.in.read();
			service.undeploy();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Loading properties from configuration file
	 *
	 * @param leoServer
	 * @return
	 * @throws MalformedURLException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected gov.va.vinci.leo.Service setServerProperties(gov.va.vinci.leo.Service leoServer) throws MalformedURLException,
			InvocationTargetException, IllegalAccessException {
		if (serviceConfigFile.length != 1) {
			return leoServer;
		}
		
		ConfigSlurper configSlurper = new ConfigSlurper();
		ConfigObject o = configSlurper.parse(serviceConfigFile[0].toURI().toURL());
		if (o.keySet().contains("brokerURL"))
			leoServer.setBrokerURL(o.get("brokerURL").toString());
		
		if (o.keySet().contains("endpoint"))
			leoServer.setEndpoint(o.get("endpoint").toString());
		
		if (o.keySet().contains("deleteOnExit"))
			leoServer.setDeleteOnExit(Boolean.parseBoolean(o.get("deleteOnExit").toString()));
		
		if (o.keySet().contains("descriptorDirectory"))
			leoServer.setDescriptorDirectory(o.get("descriptorDirectory").toString());
		
		if (o.keySet().contains("casPoolSize"))
			leoServer.setCasPoolSize(Integer.parseInt(o.get("casPoolSize").toString()));
		
		if (o.keySet().contains("CCTimeout"))
			leoServer.setCCTimeout(Integer.parseInt(o.get("CCTimeout").toString()));
		
		if (o.keySet().contains("jamQueryIntervalInSeconds"))
			leoServer.setJamQueryIntervalInSeconds(Integer.parseInt(o.get("jamQueryIntervalInSeconds").toString()));
		
		if (o.keySet().contains("jamResetStatisticsAfterQuery"))
			leoServer.setJamResetStatisticsAfterQuery(Boolean.parseBoolean(o.get("jamResetStatisticsAfterQuery").toString()));
		
		if (o.keySet().contains("jamServerBaseUrl"))
			leoServer.setJamServerBaseUrl(o.get("jamServerBaseUrl").toString());
		
		if (o.keySet().contains("instanceNumber"))
			numberOfInstances = Integer.parseInt(o.get("instanceNumber").toString());
		
		if (o.keySet().contains("isAsync"))
			isAsync = Boolean.parseBoolean(o.get("isAsync").toString());
		
		
		if (o.keySet().contains("generateTypes"))
			createTypes = Boolean.parseBoolean(o.get("generateTypes").toString());
		
		return leoServer;
	}
}