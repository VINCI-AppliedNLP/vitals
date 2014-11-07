/**
 * Common configuration items
 */
/**************************************************************/
/**  Change for each project or each run   */
/**************************************************************/
processingOnVinci = true
serviceQueueName = "VitalsForSepsis"

// SVM model
svmModelPath="src/main/resources/hr_model_500_2.svm"

casPoolSize=10

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


















