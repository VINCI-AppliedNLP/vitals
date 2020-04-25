package listeners

import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.vitals.listeners.LogDatabaseListener

int batchSize = 100
String url = "jdbc:sqlserver://vhacdwrb03:1433;databasename=VINCI_Phenotypes;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String dbUser = ""
String dbPwd = ""

String dbsName = "VINCI_Phenotypes"
String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String tableName = "[nlp_temp].[log_test]"// + timeStamp.substring(0, 8) + "]"


boolean dropExisting = false;
listener = LogDatabaseListener.createNewListener(
        driver, url, dbUser, dbPwd,
        dbsName, tableName, batchSize
)

//create table
listener.createTable(dropExisting);
//println(listener.preparedStatementSQL)package listeners


