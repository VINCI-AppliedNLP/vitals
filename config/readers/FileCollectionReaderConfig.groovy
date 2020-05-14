import gov.va.vinci.leo.cr.FileCollectionReader;
//String pathToFiles = "src/test/resources/input/"
String pathToFiles = "T:\\VINCI_COVIDNLP\\test\\input\\edge_cases"

boolean recurse = false

reader = new FileCollectionReader(new File(pathToFiles), recurse);