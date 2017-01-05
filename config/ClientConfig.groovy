brokerURL	  = "tcp://localhost:61616";
endpoint   	  = "VitalsForSepsis"
casPoolSize   = 300


mainPath       = "P:\\ORD_Iwashyna_201108021D\\NLP\\ryan"
mainOutPath    = mainPath + "\\ryan-test\\output\\{suffix}\\"
mainInPath     = mainPath + "\\input\\"

getFilesFromSubdirectories=false


// Simple CSV Listener
csvOutPath = mainOutPath

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
	["Timestamp", "-1", "varchar(1000)"],
	["Snippets", "-1", "varchar(2000)"],
	["SpanStart", "-1", "int"],
	["SpanEnd", "-1", "int"]
	//,["InstanceID", "-1", "int"]
]


// INFO: Database listener
outTableName="[nlp].[output_{suffix}]";