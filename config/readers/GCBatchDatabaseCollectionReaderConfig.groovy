import gov.va.vinci.vitals.readers.GCBatchDatabaseCollectionReader
db_engine = "vhacdwrb02"
db_name = "VINCI_COVIDNLP"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""

String query = ''' SELECT t.[TIUDocumentSID], [ReportText]  FROM  [nlp].[NLPTIULogs] l with(nolock) JOIN CDWWork.TIU.TIUDocument_8925_02 t on l.TIUDocumentSID=t.TIUDocumentSID where [vitals] = 0 and [id] between {min} and {max}  '''

row_index = [0,6500000] //6,436,979
int batchSize  = 1000000;

reader = new GCBatchDatabaseCollectionReader(
        driver,
        url,
        "", "",
        query,
        "TIUDocumentSID".toLowerCase(),
        "ReportText".toLowerCase(),
        row_index[0], row_index[1]
        , batchSize)

