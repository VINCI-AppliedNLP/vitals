db_engine = "vhacdwrb02"
db_name = "VINCI_COVIDNLP"

query = ''' SELECT a.[TIUDocumentSID], ReportText 
            FROM [nlp].[comparison_cohort_112_FullCorpus_20200407] a 
			join [CDWWork].[TIU].[TIUDocument_8925] b on a.TIUDocumentSID=b.TIUDocumentSID  
			where ReportText is not null and RecordID > {min} and RecordID <= {max}
'''

batches = [0, 100]
batchSize = 20000;
idColumn = "TIUDocumentSID"
noteColumn = "ReportText"

/************************************************************/
/***** You should not need to change the code below *********/
/************************************************************/
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""
reader = new gov.va.vinci.leo.cr.BatchDatabaseCollectionReader(driver, url, dbUser, dbPwd, query, idColumn.toLowerCase(), noteColumn.toLowerCase(), batches[0], batches[1], batchSize)
