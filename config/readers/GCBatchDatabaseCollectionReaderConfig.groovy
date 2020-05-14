import gov.va.vinci.vitals.readers.GCBatchDatabaseCollectionReader
db_engine = "vhacdwrb03"
db_name = "VINCI_COVIDNLP"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""

String query = ''' SELECT t.[TIUDocumentSID], [ReportText]  FROM  [nlp].[NLPTIULogs] l with(nolock) 
                      JOIN CDWWork.STIUNotes.TIUDocument_8925_02 t with(nolock) 
                       on l.TIUDocumentSID=t.TIUDocumentSID 
                       where vitals = 0 and [id] between {min} and {max}  
                       '''

//[vitals] in ( 0, 1) and
row_index = [ 15306000, 15580000] //6,436,979  //8,863,592  // 15,305,267
int batchSize  = 30000;

reader = new GCBatchDatabaseCollectionReader(
        driver,
        url,
        "", "",
        query,
        "TIUDocumentSID".toLowerCase(),
        "ReportText".toLowerCase(),
        row_index[0], row_index[1]
        , batchSize)

