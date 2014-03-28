/**
 * Common configuration items
 */
/**************************************************************/
 /**  Change for each project or each run   */
 /**************************************************************/
processingOnVinci = true;
serviceQueueName = "VitalsForSepsis";
/*
 * clientEnvironment encodes the data source (file vs database)
 *   and  data target (csv, xmi, database, siman for chex)
 *
 * clientEvironment can be
 *   simple   =>  Reader: files, Listeners: csv, xmi
 *   localdb  =>  Reader: local MySql database, Listeners: csv, xmi  ( rare option )
 *   vinciDbToCsv   =>  Reader: database on VINCI, Listeners: csv, xmi
 *   vinciDbToDb    =>  Reader: database on VINCI, Listeners: database on VINCI
 *   vinciDbToSiman =>  Reader: database on VINCI, Listeners: siman database on VINCI
 */
clientEnvironment = "vinciDbToCsv";
startClient = true;


/**************************************************************/
/**  Do not change code below                                 */
/**************************************************************/
if(processingOnVinci )  {
  brokerUrl = "tcp://vhacdwuapp01:61616";
  jamURL = "http://vhacdwdev93:8080/jam";
}
else {
  brokerUrl = "tcp://localhost:61616";
  jamURL = "http://localhost:8080/jam";
}


casPoolSize = 4;


