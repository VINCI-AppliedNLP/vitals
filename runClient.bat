java -Xmx1024m -Xms256m -Djava.library.path="lib;" -Dlog4j.configuration=config/log4j.properties  -cp "config/*;lib/*;leo-elite-2020.04.0-SNAPSHOT-jar-with-dependencies.jar"  gov.va.vinci.vitals.CitalsClient -clientConfigFile="config/ClientConfig.groovy" -readerConfigFile="config/readers/FileCollectionReaderConfig.groovy" -listenerConfigFile="config/listeners/SimpleXmiListenerConfig.groovy"
pause
