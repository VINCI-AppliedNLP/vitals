import gov.va.vinci.leo.model.ChexSimanDataSourceConfiguration
import gov.va.vinci.leo.model.DatabaseConnectionInformation
import gov.va.vinci.vitals.listeners.ChexListener


String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Iwashyna_201108021D;integratedSecurity=true";
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String username = "";
String password = "";

String[] typeArray = [
        "gov.va.vinci.vitals.types.Bp_value",
        "gov.va.vinci.vitals.types.Hr_value",
        "gov.va.vinci.vitals.types.T_value" ] as String[];

String documentTextSelectQuery = ""; // Not needed in this instance.
String schema = "validation";
String tableSuffix = "_xxx"; // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
String columnPrefix = "[";
String columnSuffix = "]";
int chexBatchSize = 1000;

boolean deleteIfExists = true;

ChexSimanDataSourceConfiguration simanDataSourceConfiguration = new ChexSimanDataSourceConfiguration(
        new DatabaseConnectionInformation(driver, url, username, password),
        documentTextSelectQuery,
        schema, tableSuffix, columnPrefix, columnSuffix);

listener = new ChexListener(simanDataSourceConfiguration, typeArray, chexBatchSize, deleteIfExists);