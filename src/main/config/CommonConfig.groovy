/**
 * Common configuration items
 */
/**************************************************************/
/**  Change for each project or each run   */
/**************************************************************/
processingOnVinci = false
serviceQueueName = "VitalsForSepsis"

casPoolSize=1

/**************************************************************/
/**  Do not change code below                                 */
/**************************************************************/
if(processingOnVinci )  {
	brokerUrl = "tcp://vhacdwuapp01:61616"
	jamURL = "http://vhacdwdev93:8080/jam"
}
else {
	brokerUrl = "tcp://localhost:61616"
	jamURL = "http://localhost:8080/jam"
}
















