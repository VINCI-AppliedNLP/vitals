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
	*   "kttrToCsv"
	*   "compare"
	*/
 clientEnvironment = "compare"
 startClient = false
 
/**
 * UIMA-AS service Variables
 */
numInstances = 1;
runAsync = false;
generateTypes=true
registerWithJam=false

//descriptorPath="P:\\ORD_Iwashyna_201108021D\\NLP\\desc";
//"src\\main\\desc";
//delete_on_exit=false;
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
