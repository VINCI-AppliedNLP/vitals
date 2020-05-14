db_engine = "vhacdwrb03"
db_name = "VINCI_COVIDNLP"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""

String query =  ''' SELECT t.[TIUDocumentSID], [ReportText]  FROM  [nlp].[NLPTIULogs] l with(nolock) JOIN CDWWork.TIU.TIUDocument_8925_02 t on l.TIUDocumentSID=t.TIUDocumentSID where [vitals] = 0  order by [id]  '''
idColumn = "TIUDocumentSID"
noteColumn = "ReportText"

//Integer[] offset = [0,  280000]  //
batchsize = 30000
reader = new gov.va.vinci.leo.cr.SQLServerPagedDatabaseCollectionReader(
        driver,
        url,
        "", "",
        query,
        idColumn.toLowerCase(),
        noteColumn.toLowerCase(),
        batchsize)
//, offset[0], offset[1])