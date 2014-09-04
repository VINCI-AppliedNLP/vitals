/**
 * Server configuration variables
 * Author: Olga Patterson
 */
/**************************************************************/
/**  Change for each project or each run   */
/**************************************************************/

 pathToResourceDirectory="src/main/resources/"
 regexes = [ "gov.va.vinci.vitals.types.BloodPressureTerm":"bp_term.regex"
	 ,"gov.va.vinci.vitals.types.TemperatureTerm":"temp_term.regex"
	 ,"gov.va.vinci.vitals.types.PulseTerm":"pulse_term.regex"
	 ,"gov.va.vinci.vitals.types.NumericValue":"numericValues.regex"
	 // , "additional type" : "resource file for the type" // comma-delimited pairs
	 ] // make sure that the closing bracket is in place
 
 patterns = ["gov.va.vinci.vitals.types.ExcludeValue":"excludeValue.pattern"
	 ,"gov.va.vinci.example.types.RecurrNegPattern":"recurrneg.pattern"
	 ]
 // Remove 2 if is contained in 1
 filterList = [
	 ["gov.va.vinci.example.types.RecurrNegPattern","gov.va.vinci.example.types.Recurr"]
	 ]
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
