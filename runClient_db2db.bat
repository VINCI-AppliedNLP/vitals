java -Xmx1024m -Xms256m -Djava.library.path="lib;" -Dlog4j.configuration=config/log4j.properties  -cp  "config/*;lib/*;vitals-2020.04.0-SNAPSHOT-jar-with-dependencies.jar"  gov.va.vinci.vitals.VitalsClient -clientConfigFile="config/ClientConfig.groovy" -readerConfigFile="config/readers/DatabaseCollectionReaderConfig.groovy" -listenerConfigFile="config/listeners/DatabaeListenerConfig.groovy"

pause
