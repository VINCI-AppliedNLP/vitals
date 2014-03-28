package gov.va.vinci.vitals.listeners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.eclipse.jetty.util.log.Log;

import gov.va.vinci.leo.listener.SimanDatabaseListener;
import gov.va.vinci.leo.model.SimanDataSourceConfiguration;
import gov.va.vinci.leo.tools.Common;
import gov.va.vinci.leo.tools.SimanUtils;
import gov.va.vinci.leo.tools.NameValue;

public class SimanListener extends SimanDatabaseListener {

	public static final Logger log = Logger.getLogger(Common.getRuntimeClass().toString());

	public SimanListener(SimanDataSourceConfiguration simanDataSourceConfiguration) throws SQLException {
		super(simanDataSourceConfiguration);
		LOG.info(" Initializing " + this.getClass().getCanonicalName() + "\r\n"
		    + simanDataSourceConfiguration.getDocumentSelectAllSql());
		try {
			validateSchemaAndCreateIfNeeded(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public SimanListener(SimanDataSourceConfiguration simanDataSourceConfiguration,
	    String[] inputType, int batchSize, boolean deleteIfExists) throws SQLException {
		super(simanDataSourceConfiguration, inputType, batchSize);

		try {
			validateSchemaAndCreateIfNeeded(deleteIfExists);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void createConnection() throws SQLException {
		super.createConnection();
		preparedDocumentXrefStatement = conn.prepareStatement("INSERT INTO " +
		    simanDataSourceConfiguration.schema + ".document_xref_example"
		    + simanDataSourceConfiguration.tableSuffix +
		    " ( guid, version, patient_sid, tiu_document_sid ) VALUES " +
		    " ( ?, ?, ?, ? )");
	}

	protected void validateSchemaAndCreateIfNeeded(boolean deleteIfExists) throws SQLException, IOException {
		try {
			Connection connection = simanDataSourceConfiguration.getDataSource().getConnection();
			connection.prepareStatement(simanDataSourceConfiguration.getDocumentSelectAllSql()).execute();
			if (deleteIfExists)
				createSchema();
		} catch (SQLException e) {
			System.out.println("Exception from document select, creating schema.");
			/**/
			createSchema();
			/* */
		}

	}

	protected void createSchema() throws SQLException, IOException {
		String dbsSimanCreate =  SimanUtils.getCreateTablesSQL(
		    simanDataSourceConfiguration.schema,
		    simanDataSourceConfiguration.tableSuffix,
		    SimanUtils.SchemaType.SQL_SERVER);
		    
		log.info("\r\n" + dbsSimanCreate);
		simanDataSourceConfiguration.getDataSource()
		    .getConnection()
		    .prepareStatement(dbsSimanCreate).execute();
		/* */
		log.info("Creating Siman database schema in "
		    + simanDataSourceConfiguration.schema + ".tables"
		    + simanDataSourceConfiguration.tableSuffix);
	}

	@Override
	protected NameValue insertDocumentXref(CAS arg0) throws SQLException {
		String tiuDocumentSID = (this.docInfo == null) ? "" : this.docInfo.getID();
		String patientSID = (this.docInfo == null || this.docInfo.getRowData() == null) ? "" : this.docInfo
		    .getRowData(1);
		String recordUUID = UUID.randomUUID().toString();
		PreparedStatement ps = conn
		    .prepareStatement("INSERT INTO [" +
		        simanDataSourceConfiguration.schema + "].[document_xref_example"
		        + simanDataSourceConfiguration.tableSuffix + "] " +
		        " ( [guid],[version],[patient_sid],[tiu_document_sid] ) VALUES " +
		        " ( ?, ?, ?, ? )");

		ps.setString(1, recordUUID);
		ps.setTimestamp(2, new Timestamp(new java.util.Date().getTime()));
		ps.setString(3, patientSID);
		ps.setString(4, tiuDocumentSID);
		ps.execute();

		return new NameValue("[" + simanDataSourceConfiguration.schema + "].[document_xref_example"
		    + simanDataSourceConfiguration.tableSuffix + "]", recordUUID);
	}

}