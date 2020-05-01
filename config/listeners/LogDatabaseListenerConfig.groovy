import gov.va.vinci.vitals.listeners.LogDatabaseListener

db_engine = "vhacdwrb02"
db_name = "VINCI_COVIDNLP"

int batchSize = 2000
boolean dropExisting = false;
boolean createTable = true
String timeStamp = gov.va.vinci.leo.tools.LeoUtils.getTimestampDateUnderscoreTime()
String tableName = "[nlp_temp].[vitals_log_"+ timeStamp+"]"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""



listener = LogDatabaseListener.createNewListener(
        driver, url, dbUser, dbPwd,
        db_name, tableName, batchSize
)

//create table
if(createTable ) listener.createTable(dropExisting);
println(listener.preparedStatementSQL)

