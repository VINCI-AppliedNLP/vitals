/**
 * Server configuration variables
 * Author: Olga Patterson
 */
/**************************************************************/
/**  Change for each project or each run   */
/**************************************************************/

 pathToResourceDirectory="src/main/resources/"
 /**************************************************************/
/**  Do not change code below                                 */ 
/**************************************************************/

/**
 * UIMA-AS service Variables
 */
numInstances = 4;
runAsync = false;
generateTypes = false;

/**
 * JAM variables
 */
registerWithJam = false;
jamInterval = 600;
jamResetAfterQuery = false;

/**
 * Environment Parameters
 */

environments {
	simple{ generateTypes = false }
	updateTypes{  generateTypes = true }
	simpleWithJam{
		generateTypes = false
		registerWithJam = true
	}
}
