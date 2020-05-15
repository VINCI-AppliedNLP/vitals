db_engine = "vhacdwrb03"
db_name = "VINCI_COVIDNLP"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"

def chexTypes = [
        "gov.va.vinci.vitals.types.Bp_Systolic_value",
        "gov.va.vinci.vitals.types.Bp_Diastolic_value",
        "gov.va.vinci.vitals.types.Hr_value",
        "gov.va.vinci.vitals.types.T_value"
        ,"gov.va.vinci.vitals.types.So2_value"
        ,"gov.va.vinci.vitals.types.Resp_value" ] ;

String documentTextSelectQuery = ""; // Not needed in this instance.
String schema = "chex";
String tableSuffix = "_200TIUs_20200514"; // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
String columnPrefix = "[";
String columnSuffix = "]";
int chexBatchSize = 1000;

boolean deleteIfExists = true;


listener = gov.va.vinci.vitals.listeners.ChexListener.newChexListener(
        driver,
        url,
        documentTextSelectQuery,
        schema,
        tableSuffix,
        columnPrefix,
        columnSuffix,
        chexTypes,
        chexBatchSize,
        deleteIfExists)