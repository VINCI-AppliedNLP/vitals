import gov.va.vinci.leo.listener.SimpleXmiListener

//String xmiPath = "test/xmi/";//"c:\\my-dir\\${new Date().getTime()}";
String xmiPath ="T:\\VINCI_COVIDNLP\\test\\output\\edge_case\\xml"

        File xmiPathFile = new File(xmiPath);
if (!xmiPathFile.exists())
    xmiPathFile.mkdirs();

Boolean openViewer = true;

listener = new SimpleXmiListener(xmiPathFile)
listener.setLaunchAnnotationViewer(openViewer);
listener.setTypeSystemDescriptor(new File ("config/TypeSystem.xml"));

String[] annotationsToOutput = [] as String[];

if (annotationsToOutput.size() > 0) {
    listener.setAnnotationTypeFilter(annotationsToOutput);
}