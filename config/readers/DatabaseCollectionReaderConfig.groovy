import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader

String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=ORD_Iwashyna_201108021D;integratedSecurity=true";
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String username = "";
String password = "";
String query = "SELECT a.[TIUDocumentSID],  a.[PatientSID], b.ReportText, b.ReferenceDateTime , Sta3n   FROM  [ORD_Iwashyna_201108021D].[nlp].[NLP_VitalsCorpus_v2] a with (NOLOCK) " +
                "   join  [ORD_Iwashyna_201108021D].[Src].[TIUDocument_8925] b  with (NOLOCK)" +
                "   on a.TIUDocumentSID = b.TIUDocumentSID   where   reporttext is not null ;"
String idColumn = "tiudocumentsid";
String noteColumn = "reporttext";
int minRecordNumber = 1
int maxRecordNumber = 100;
int batchSize = 1000;

reader = new BatchDatabaseCollectionReader(driver, url, username, password,
                                           query, idColumn, noteColumn, minRecordNumber,
                                           maxRecordNumber, batchSize).produceCollectionReader();