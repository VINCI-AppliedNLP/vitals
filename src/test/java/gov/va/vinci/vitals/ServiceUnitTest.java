package gov.va.vinci.vitals;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.types.CSI;
import gov.va.vinci.vitals.types.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tools.AnnotationViewerMain;
import org.apache.uima.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiceUnitTest {

	protected LeoAEDescriptor aggregate = null;
	protected LeoTypeSystemDescription types = null;
	protected String outputDir = "src/test/resources/output/xmi";
	protected String inputDir = "src/test/resources/input/";
	protected int numDocs = 18;
	// protected int numDocs = 1;
	protected boolean launchView = true;
	protected String aggXmi = "aggregateDesc";

	@Before
	public void setup() throws Exception {
		Service ds = new Service();
		// aggregate = ds.createPipeline(false);
		LeoTypeSystemDescription types = ds.createTypeSystem();
		aggregate = ds.createPipeline(types);

		File o = new File(outputDir);
		if (!o.exists()) {
			o.mkdirs();
		}// if
	}// setup
	
public String getDocText(String filename) throws IOException{
	return FileUtils.file2String(new  File(inputDir + filename));
}
	@Test
	public void testXmi() throws Exception {

		String docText = "";
		AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aggregate.getAnalysisEngineDescription());
		numDocs = 3;
		for (int i = 0; i < numDocs; i++) {
			String filename = null;
			try {
			
				filename ="file" + i + ".txt";
				System.out.println(filename);
				docText = getDocText(filename);
				} catch (Exception e){
					System.out.println("No file found. creating new");
				filename = "test.txt";
				docText =
				"Blank test \r\n" 					+
						" ";
				}
			if (StringUtils.isBlank(docText))
				continue;
			JCas jcas = createJCas(ae, docText, filename);
			ae.process(jcas);
			ArrayList<Annotation> list =  (ArrayList) AnnotationLibrarian.getAllAnnotationsOfType(jcas, Bp_value.type);
			System.out.println("BP count : " + list.size());
			for(Annotation a: list){
				System.out.println("BP:" + a.getCoveredText());
			}
			list = (ArrayList) AnnotationLibrarian.getAllAnnotationsOfType(jcas, T_value.type);
			System.out.println("T count : " + list.size());
			for(Annotation a: list){
				System.out.println("T :" + a.getCoveredText());
			}
			
			list = (ArrayList) AnnotationLibrarian.getAllAnnotationsOfType(jcas, Hr_value.type);
			System.out.println("HR count : " + list.size());
			for(Annotation a: list){
				System.out.println("HR:"+a.getCoveredText());
			}
			try {
				
				File xmio = new File(outputDir, filename + ".xmi");
				XmiCasSerializer.serialize(jcas.getCas(), new FileOutputStream(	xmio));
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
	}// testXmi method

	/**
	 * 
	 * @param ae
	 * @param docText
	 * @param id
	 * @return
	 * @throws ResourceInitializationException
	 */
		protected JCas createJCas(AnalysisEngine ae, String docText, String id)
		    throws ResourceInitializationException {
			JCas jcas = ae.newJCas();
			jcas.setDocumentText(docText);
			CSI csi = new CSI(jcas);
			csi.setBegin(0);
			csi.setEnd(docText.length());
			csi.setID(id);
			csi.addToIndexes();
			return jcas;
		}
	protected void launchViewer() throws Exception {
		if (aggregate == null) {
			throw new RuntimeException(
					"Aggregate is null, unable to generate descriptor for viewing xmi");
		}
		aggregate.toXML(aggXmi);
		String aggLoc = aggregate.getDescriptorLocator();
		Preferences prefs = Preferences.userRoot().node(
				"org/apache/uima/tools/AnnotationViewer");
		if (aggLoc != null) {
			prefs.put("taeDescriptorFile", aggLoc);
		}// if mAggDesc != null
		if (outputDir != null) {
			prefs.put("inDir", outputDir);
		}// if mOutputDir != null
		AnnotationViewerMain avm = new AnnotationViewerMain();
		avm.setBounds(0, 0, 1000, 225);
		avm.setVisible(true);
	}// launchViewer method

	@After
	public void cleanup() throws Exception {
	
		}// cleanup method
/*
 file0.txt
 BP count : 6
BP:126/50
BP:144/69
BP:128/82
BP:142/81
BP:128/75
BP:124/70
--BP: 129/83
--BP: 128/85
--BP: 135/88
--BP: 106/74
 
 T count : 5
T :98.2
T :98.1
T :98.6
T :99.1
T :97
-- T:36.5
-- T: 97.9F
-- T: 36.6C
HR count : 8
HR:88
HR:70
HR:76
HR:77
HR:64
HR:79
HR:79
HR:72

file1.txt
BP count : 5
BP:96/64
BP:100/64
BP:106/64
BP:100/67
BP:121/52
T count : 2
T :98.2
T :97.4
HR count : 1
HR:72

file2.txt
BP count : 0
T count : 0
HR count : 0


 */
}
