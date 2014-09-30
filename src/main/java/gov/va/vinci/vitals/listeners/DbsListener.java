package gov.va.vinci.vitals.listeners;

import gov.va.vinci.kttr.types.BPValue;
import gov.va.vinci.kttr.types.HRValue;
import gov.va.vinci.kttr.types.TValue;
import gov.va.vinci.leo.listener.BaseDatabaseListener;
import gov.va.vinci.leo.model.DatabaseConnectionInformation;
import gov.va.vinci.leo.tools.LeoUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;

public class DbsListener extends BaseDatabaseListener {

	private static final Logger log = Logger.getLogger(LeoUtils
			.getRuntimeClass().toString());
	protected HashMap<String, Integer> fields = new HashMap<String, Integer>();
	protected ArrayList<String> headers = new ArrayList<String>();

	public String createStatement;

	/**
	 * This method creates a DbsListener object and initializes createStatement
	 * 
	 * @param databaseConnectionInformation
	 * @param dbsName
	 * @param tableName
	 * @param batchSize
	 * @param fieldList
	 * @return
	 */
	public static DbsListener createNewListener(
			DatabaseConnectionInformation databaseConnectionInformation,
			String dbsName, String tableName, int batchSize,
			ArrayList<ArrayList<String>> fieldList) {

		String createStatement = createCreateStatement(dbsName, tableName,
				fieldList);
		String insertStatement = createInsertStatement(dbsName, tableName,
				fieldList);
		boolean validateConnectionEachBatch = true;
		return new DbsListener(databaseConnectionInformation, insertStatement,
				batchSize, validateConnectionEachBatch, createStatement,
				fieldList);
	}

	/**
	 * Main constructor
	 * 
	 * @param databaseConnectionInformation
	 * @param preparedStatementSQL
	 * @param batchSize
	 * @param validateConnectionEachBatch
	 * @param createStatement
	 */
	public DbsListener(
			DatabaseConnectionInformation databaseConnectionInformation,
			String preparedStatementSQL, int batchSize,
			boolean validateConnectionEachBatch, String createStatement,
			ArrayList<ArrayList<String>> fieldList) {
		super(databaseConnectionInformation, preparedStatementSQL, batchSize,
				validateConnectionEachBatch);
		this.createStatement = createStatement;
		this.setHeaders(fieldList);
	}

	/**
	 * Creates the database table. Accepts the create statement and executes it
	 * in the target database
	 * 
	 * @param dbConnectionInfo
	 * @param createStatement
	 * @param dropFirst
	 * @param tableName
	 * @throws Exception
	 */
	public void createTable(DatabaseConnectionInformation dbConnectionInfo,
			String createStatement, boolean dropFirst, String tableName)
			throws Exception {
		Class.forName(dbConnectionInfo.getDriver()).newInstance();
		log.info("Creating a table for output \r\n" + createStatement);
		Connection conn = DriverManager.getConnection(
				dbConnectionInfo.getUrl(), dbConnectionInfo.getUsername(),
				dbConnectionInfo.getPassword());
		if (dropFirst && StringUtils.isNotBlank(tableName)) {
			conn.createStatement().execute("DROP TABLE " + tableName);
		}// if

		conn.createStatement().execute(createStatement);
	}// createTable method

	/**
	 * Static method to create insert statement based on the database and table
	 * name
	 * 
	 * @param dbsName
	 * @param tableName
	 * @param fieldList
	 * @return
	 */
	public static String createInsertStatement(String dbsName,
			String tableName, ArrayList<ArrayList<String>> fieldList) {
		String statement = "INSERT INTO " + dbsName + "." + tableName + " ( ";
		String values = "";
		for (ArrayList<String> entry : fieldList) {
			statement = statement + entry.get(0) + ", ";
			values = values + " ?,";
		}
		statement = statement.substring(0, statement.length() - 2)
				+ " ) VALUES ( " + values.substring(0, values.length() - 1)
				+ " ) ;";
log.info(statement);
		return statement;
	}

	/**
	 * Static method to create a table create statement based on the database
	 * and table name and list of fields
	 * 
	 * @param dbsName
	 * @param tableName
	 * @param fieldList
	 * @return
	 */
	public static String createCreateStatement(String dbsName,
			String tableName, ArrayList<ArrayList<String>> fieldList) {

		String statement = "Drop table  " + dbsName + "." + tableName +"; CREATE TABLE " + dbsName + "." + tableName + " ( ";
		for (ArrayList<String> entry : fieldList) {
			statement = statement + entry.get(0) + " " + entry.get(2) + ", ";
		}
		statement = statement.substring(0, (statement.length() - 2)) + " ) ;";
		return statement;
	}

	@Override
	protected List<Object[]> getRows(CAS aCas) {
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		
		// Output all refst annotations
		String[] types = new String[] { BPValue.class.getCanonicalName(),
				HRValue.class.getCanonicalName(),
				TValue.class.getCanonicalName() };
		for (String singleType : types) {
			Type type = aCas.getTypeSystem().getType(singleType);
			FSIndex<?> index = aCas.getAnnotationIndex(type);
			FSIterator<?> iterator = index.iterator();

			String refLoc;
			try {
				refLoc = getReferenceLocation(aCas.getJCas());
			} catch (CASException e1) {
				throw new RuntimeException(e1);
			}

			while (iterator.hasNext()) {  
				Annotation a = (Annotation) iterator.next();
				ArrayList<String> lineRow = new ArrayList<String>();
				lineRow.add(refLoc);
				lineRow.add(singleType);
				lineRow.add(a.getCoveredText().replaceAll("\\s+", " "));
				lineRow.add("" + a.getBegin());
				lineRow.add("" + a.getEnd());
				lineRow.add("177");
				
				rows.add(lineRow.toArray(new Object[lineRow.size()]));
			}
		}
			    	      
		return rows;
	}

	/**
	 * 
	 * @param fieldList
	 */
	protected void setHeaders(ArrayList<ArrayList<String>> fieldList) {
		fields = new HashMap<String, Integer>();
		for (ArrayList<String> entry : fieldList) {
			headers.add(entry.get(0));
			fields.put(entry.get(0), Integer.parseInt(entry.get(1)));
		}
	}

}// DBListener

