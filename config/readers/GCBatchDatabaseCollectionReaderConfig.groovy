db_engine = "vhacdwrb02"
db_name = "VINCI_COVIDNLP"

row_index = [0, 100]
batchSize = 20000;
idColumn = "TIUDocumentSID"
noteColumn = "ReportText"
String query = ''' SELECT t.[TIUDocumentSID], [ReportText]  FROM  [nlp].[NLPTIULogs] l with(nolock) 
                      JOIN CDWWork.STIUNotes.TIUDocument_8925_02 t with(nolock) 
                       on l.TIUDocumentSID=t.TIUDocumentSID 
                       where vitals = 0 and [id] between {min} and {max}
                       '''
//and [id] between {min} and {max}


String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""
reader = new gov.va.vinci.vitals.readers.GCBatchDatabaseCollectionReader(
        driver,
        url,
        dbUser , dbPwd,
        query,
        idColumn.toLowerCase(),
        noteColumn.toLowerCase(),
        row_index[0], row_index[1]
        , batchSize)

