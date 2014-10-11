// Reader type is one of the following:
// knowtator or database or file
readerType = "database"

// Listener types is one or more of the following
// simpleCsv|simplexmi|csv|xmi|aucompare|compare|database
listenerTypes = "database"

///////////////////////////////////////////////////////////////////////////////
sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
projectDbsName = "ORD_Iwashyna_201108021D"
projectServer  = "vhacdwrb02"

connectionURL = "jdbc:sqlserver://"+projectServer+":1433;databasename="+projectDbsName+";integratedSecurity=true";

mainPath       = "P:\\ORD_Iwashyna_201108021D\\NLP"
mainOutPath    = mainPath + "\\output\\{suffix}\\"
mainInPath     = mainPath + "\\input\\"

getFilesFromSubdirectories=false

query= "SELECT  a.[TIUDocumentSID] ,[ReportText], a.patientsid,b.Entrydatetime, Sta3n    FROM [ORD_Iwashyna_201108021D].[Src].[TIUDocument_8925] b,   [ORD_Iwashyna_201108021D].[nlp].[NLP_VitalsCorpus_v2]  a   where a.TIUDocumentSID=b.TIUDocumentSID;"
batchSize=10000
startId= 1
endId=   100
idIndex="tiudocumentsid"
noteIndex="reporttext"

// Listener options
// Available listeners: simple, knowtator, csv, xmi,
//              database.vinci, database.siman, compare

// Simple CSV Listener
csvOutPath = mainOutPath
simpleCsvOutTypes=[ "outputRefSt.csv": ["gov.va.vinci.kttr.types.BPValue","gov.va.vinci.kttr.types.HRValue",	"gov.va.vinci.kttr.types.TValue"]]

// Knowtator listener
knowtatorOutTypes = ["gov.va.vinci.example.types.Logic"]

// Project specific CSV listener
// Absolute or relative path for a new comma-delimited file. If the file exists, it will be overwritten.
// if the path does not exist, it will be created.
csvFileName = mainOutPath + "outputTable_{suffix}.csv"
csvFieldList = [
	["VitalSignID", "-1", "int"],
	["Sta3n", "4", "varchar(10)"],
	["TIUDocumentSID", "0", "bigint"],
	["Term", "-1", "varchar(1000)"],
	["VitalType", "-1", "varchar(1000)"],
	["Result", "-1", "varchar(1000)"],
	["Systolic", "-1", "varchar(1000)"],
	["Diastolic", "-1", "varchar(1000)"],
	["ValueString", "-1", "varchar(1000)"],
	["Assessment", "-1", "varchar(1000)"],
	["Unit", "-1", "varchar(1000)"],
	["Snippets", "-1", "varchar(2000)"],
	["SpanStart", "-1", "int"],
	["SpanEnd", "-1", "int"]
	//,["InstanceID", "-1", "int"]
]


//  INFO: XMI listener

/* Full path for a new xmi output files. These files can be viewed with UIMA viewer.
 *   If xmi files exist, they will be overwritten.
 *   If the path does not exist, it will be created.  */
xmiOutPath = mainOutPath + "xmi\\"
xmiOutputTypeList =  ["gov.va.vinci.vitals.types.T_value", "gov.va.vinci.vitals.types.Hr_value", "gov.va.vinci.vitals.types.Bp_value"]

openViewerAfterProcessing =  false
// if the list is empty, all files will be outputted

// INFO: Database listener
outTableName="[nlp].[output_NLP_VitalsCorpus_v2]"



// Siman listener
dbSchema = "dflt" // unless you create your own schema, "dflt" should be used
outBatchSize= 1000

dbFieldList = [
	["VitalSignID", "-1", "int"],
	["Sta3n", "4", "varchar(10)"],
	["TIUDocumentSID", "0", "bigint"],
	["Term", "-1", "varchar(1000)"],
	["VitalType", "-1", "varchar(1000)"],
	["Result", "-1", "varchar(1000)"],
	["Systolic", "-1", "varchar(1000)"],
	["Diastolic", "-1", "varchar(1000)"],
	["ValueString", "-1", "varchar(1000)"],
	["Assessment", "-1", "varchar(1000)"],
	["Unit", "-1", "varchar(1000)"],
	["Snippets", "-1", "varchar(2000)"],
	["SpanStart", "-1", "int"],
	["SpanEnd", "-1", "int"]
]


// Siman output is for Chex
simanSchema = "nlp"  //
simanSuffix = "_test" // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
simanTypes= []  // when blank, SimanListener outputs all annotations
simanOverwrite = true


// AuCompare -- not setup yet
auMap = ["gov.va.vinci.kttr.types.BPValue":"gov.va.vinci.vitals.types.Bp_value",
	"gov.va.vinci.kttr.types.HRValue":"gov.va.vinci.vitals.types.Hr_value",
	"gov.va.vinci.kttr.types.TValue":"gov.va.vinci.vitals.types.T_value"
]

// INFO: environments
environments {
	simple{ envType = "simple"; }
	kttrToCsv{
		readerType = "knowtator"
		listenerTypes = "csv|xmi"
		envType = "kttrToCsv";
	}
	compare{
		readerType = "knowtator"
		listenerTypes = "aucompare|xmi"
		envType = "compare";
	}
	dbOut{
		readerType = "database"
		listenerTypes = "database"
		envType = "dbOut";
	}
}


/// INFO: End of config file
