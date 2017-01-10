import gov.va.vinci.leo.model.DatabaseConnectionInformation
import gov.va.vinci.vitals.listeners.DbsListener

DatabaseConnectionInformation databaseConnectionInformation = new DatabaseConnectionInformation(
        "com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "jdbc:sqlserver://myserver:1433;databasename=project1;integratedSecurity=true",
        "myUsername", "myPassword");

listener = DbsListener.createNewListener(databaseConnectionInformation, "outputdatabase", "output_table", 1000,
        ["VitalSignID", "-1", "int"],
        ["Sta3n", "4", "varchar(10)"],
        //["TIUDocumentSID", "0", "varchar(30)"],//
        ["TIUDocumentSID", "0", "bigint"],
        ["ReferenceDateTime", "3", "datetime"],
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
        ["SpanEnd", "-1", "int"]);
