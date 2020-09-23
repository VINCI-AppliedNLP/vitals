db_engine = "vhacdwrb03"
db_name = "VINCI_COVIDNLP"
//28953251]
row_index = [20000000, 29000000]  // CAS Count: 6896329  - Client finished in: 21:57:14.258
        // [15000000, 20000000] -- CAS Count: 4950021 - Client finished in: 21:13:36.097
        // [10000000, 15000000] -- CAS Count:  776765 - Client finished in: 03:16:16.786
        //  [5000000, 10000000] -- CAS Count: 3038193 - Client finished in: 15:17:21.73
        //         [0, 5000000] -- CAS Count: 4991147 - Client finished in: 21:19:51.767
batchSize = 40000;
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

