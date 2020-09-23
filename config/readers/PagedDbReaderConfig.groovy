db_engine = "vhacdwrb03"
db_name = "VINCI_COVIDNLP"

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://"+db_engine+":1433;databasename="+db_name+";integratedSecurity=true"
String dbUser = ""
String dbPwd = ""

String query =  '''  SELECT  t.[TIUDocumentSID], [ReportText], [id]  FROM   [nlp].[NLPTIULogs] l with(nolock)  JOIN CDWWork.STIUNotes.TIUDocument_8925_02 t  with(nolock) on l.TIUDocumentSID=t.TIUDocumentSID where [vitals] = 0  and Reporttext is not null   order by [id]   '''
idColumn = "TIUDocumentSID"
noteColumn = "ReportText"

Integer[] offset = [0,  1000]  //
batchsize = 30000
reader = new gov.va.vinci.leo.cr.SQLServerPagedDatabaseCollectionReader(
        driver,
        url,
        dbUser, dbPwd,
        query,
        idColumn.toLowerCase(),
        noteColumn.toLowerCase(),
        batchsize);
// , offset[0], offset[1])