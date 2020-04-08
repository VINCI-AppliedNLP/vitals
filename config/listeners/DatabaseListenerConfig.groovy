db_engine = "vhacdwrb03"
db_name = "VINCI_Phenotypes"

int batchSize = 2000
boolean dropExisting = false;
boolean createTable = true
String timeStamp = gov.va.vinci.leo.tools.LeoUtils.getTimestampDateUnderscoreTime()
String tableName = "[nlp_temp].[vitals_"+ timeStamp+"]"
fieldList = [
        ["VitalSignID", "-1", "int"],
        ["DocID", "0", "varchar(50)"],
      //  ["Term", "-1", "varchar(1000)"],
        ["VitalType", "-1", "varchar(1000)"],
        ["Result", "-1", "varchar(1000)"],
        ["Systolic", "-1", "varchar(1000)"],
        ["Diastolic", "-1", "varchar(1000)"],
        ["ValueString", "-1", "varchar(1000)"],
     //   ["Assessment", "-1", "varchar(1000)"],
        ["Unit", "-1", "varchar(1000)"],
        ["Timestamp", "-1", "varchar(1000)"],
        ["Snippets", "-1", "varchar(2000)"],
        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"]
]
/************************************************************/
/***** You should not need to change the code below *********/
/************************************************************/
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""
inTypes= "gov.va.vinci.leo.types.CSI"

listener = gov.va.vinci.vitals.listeners.VitalsDatabaseListener.createNewListener(
        driver,
        url,
        dbUser,
        dbPwd,
        db_name,
        tableName,
        batchSize,
        fieldList,
        inTypes
)
if(createTable ) listener.createTable(dropExisting);
println(listener.preparedStatementSQL)

