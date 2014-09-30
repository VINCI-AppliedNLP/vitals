package gov.va.vinci.leo.listener;

import com.google.gson.JsonSyntaxException;
import gov.va.vinci.leo.model.DatabaseConnectionInformation;
import gov.va.vinci.leo.tools.AuCompare;
import gov.va.vinci.leo.tools.AuStats;
import gov.va.vinci.leo.tools.LeoUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Use the AuCompare service object to compare gold standard to tool generated output. Row data listing the annotations
 * that were compared along with their related stats classes are written to a database table in the format of:
 * Document ID, Begin, End, Covered Text, Stats Class
 *
 * User: Thomas Ginter
 * Date: 11/21/13
 * Time: 10:21 AM
 */
public class AuCompareDbListener extends BaseDatabaseListener {
    /**
     * Compare service object to perform the annotation comparison and store the stats.
     */
    protected AuCompare compare = null;

    /**
     * Logging object of output.
     */
    private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

    /**
     * Gold Compare Database Listener constructor, initialize database information for output rows.
     *
     * @param auAnnotationMap               JSON formatted string that maps a Gold to Tool annotation types
     * @param databaseConnectionInformation required information to connect to the the database
     * @param schema                        optional, name of the schema if the database type supports it
     * @param tableSuffix                   suffix to be used on the output table
     * @param type                          type of database server being accessed, one of the constants defined in the internal enum SchemaType
     * @param batchSize                     the size of batch inserts to send to the database, 1,000 is a common setting for this parameter
     * @param validateConnectionEachBatch   if true, the databaseConnectioninformation.validationQuery will be run before each batch in addition to making sure the connection is live.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap has an invalid JSON format
     */
    public AuCompareDbListener(Map<String,String> auAnnotationMap,
                               DatabaseConnectionInformation databaseConnectionInformation,
                               String schema,
                               String tableSuffix,
                               SchemaType type,
                               int batchSize,
                               boolean validateConnectionEachBatch) throws JsonSyntaxException {
        super(databaseConnectionInformation, getInsertAuTableSQL(schema, tableSuffix, type), batchSize, validateConnectionEachBatch);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Abstract method: You must implement this method.
     *
     * @param aCas the currently processed CAS
     * @return a list of object[]. Each entry in the list is a row to be inserted in the database, and each
     * element in the object array is the column. This needs to match up with the prepared statement specified in the constructor.
     */
    @Override
    protected List<Object[]> getRows(CAS aCas) {
        List<Object[]> output = new ArrayList<Object[]>();
        try {
            JCas jCas = aCas.getJCas();
            for(String[] row : compare.annotationComparison(jCas)) {
              Object[] oRow = Arrays.copyOf(row, row.length);
              output.add(oRow);
            }
        } catch(CASException e) {
            log.error("Error getting JCAS reference for compare.annotationCompare", e);
        }
        return output;
    }

    public static String getInsertAuTableSQL(String schema, String tableSuffix, SchemaType type) {
        String sql = "";
        switch (type) {
            case HSQLDB:
                sql = "INSERT INTO auCompare!tableSuffix!";
                sql += " (\"docID\", \"mapping\", \"start\", \"end\", \"type\", \"coveredText\", \"statClass\")";
                break;
            case MYSQL:
                sql = "INSERT INTO auCompare!tableSuffix!";
                sql += " ('docID', 'mapping', `start`, `end`, `type`, `coveredText`, `statClass`)";
                break;
            case SQL_SERVER:
                sql = "INSERT INTO [!schema!].auCompare!tableSuffix!";
                sql += " ([docId], [mapping], [start], [end], [type], [coveredText], [statClass])";
                break;
        }
        sql += " values (?, ?, ?, ?, ?, ?, ?)";
        sql = sql.replaceAll("!tableSuffix!", tableSuffix);
        if(StringUtils.isNotBlank(schema)) {
            sql = sql.replaceAll("!schema!", schema);
        }
        return sql;
    }

    /**
     * Create the output auCompare table.
     *
     * @param dbConnectionInfo DatabaseConnectionInformation object with the required connection information
     * @param schema database schema to which the table will belong, null if no applicable schema, such as in mysql
     * @param tableSuffix table suffix, or "" for no suffix
     * @param type type of database in which the table will be created @see AuCompareDbListener.SchemaType
     * @throws Exception
     */
    public static void createAuTable(DatabaseConnectionInformation dbConnectionInfo, String schema, String tableSuffix, SchemaType type) throws Exception {
        //Get the create statement
        String createStatement = getCreateAuTableSQL(schema, tableSuffix, type);
        if(StringUtils.isBlank(createStatement)) {
            throw new RuntimeException("Create Statement is blank, unable to get au create statement from getCreateAuTableSQL");
        }
        //Create the table
        Class.forName(dbConnectionInfo.getDriver()).newInstance();
        Connection conn = DriverManager.getConnection(dbConnectionInfo.getUrl(), dbConnectionInfo.getUsername(), dbConnectionInfo.getPassword());
        Statement statement = conn.createStatement();
        statement.execute(createStatement);
    }

    /**
     * Given a table suffix and schema type, return the create SQL to create all of the tables and
     * relationships for that instance of a SimpleAnnotationSchema.
     *
     * @param schema      table schema, or "" for no prefix.
     * @param tableSuffix table suffix, or "" for no suffix.
     * @param type        target database type.
     * @return the sql statements to create the schema in the appropriate db sql and table suffix.
     * @throws java.io.IOException   if there is an error reading the template sql scripts.
     */
    public static String getCreateAuTableSQL(String schema, String tableSuffix, SchemaType type) throws IOException {
        String sql = "";
        InputStream in = null;
        switch (type) {
            case HSQLDB:
                in = AuCompareDbListener.class.getResourceAsStream("/au-compare/create-au-compare-db-hsqldb.sql");
                break;
            case MYSQL:
                in = AuCompareDbListener.class.getResourceAsStream("/au-compare/create-au-compare-db-mysql.sql");
                break;
            case SQL_SERVER:
                in = AuCompareDbListener.class.getResourceAsStream("/au-compare/create-au-compare-db-sql-server.sql");
                break;
        }

        if (in == null) {
            throw new RuntimeException("Could not find schema file for specified type.");
        }

        StringWriter writer = new StringWriter();
        IOUtils.copy(in, writer);
        sql = writer.toString();

        sql = sql.replaceAll("!tableSuffix!", tableSuffix);
        return sql.replaceAll("!schema!", schema);
    }

    /**
     * Write the stat string for each AuStats object out to the console.
     */
    public void outputStatsToConsole() {
        if(compare == null) {
            return;
        }
        compare.outputStatsToConsole();
    }

    /**
     * Return a list of the stat String objects for each AuStats object.
     *
     * @return list of stat Strings
     */
    public List<String> outputStatStrings() {
        if(compare == null) {
            return null;
        }
        return compare.outputStatStrings();
    }

    /**
     * Return a list of AuStats objects used in the compare service accessed by the gov.va.vinci.leo.listener.
     *
     * @return list of AuStats objects
     */
    public List<AuStats> getStatList() { return compare.getStatList(); }

    /**
     * Database schema type supported.
     */
    public enum SchemaType {
        /**
         * Hypersonic SQL.
         */
        HSQLDB,
        /**
         * My SQL DB.
         */
        MYSQL,
        /**
         * Microsoft SQL Server.
         */
        SQL_SERVER
    }
}
