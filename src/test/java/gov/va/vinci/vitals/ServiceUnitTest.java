package gov.va.vinci.vitals;

import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.types.CSI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.tools.AnnotationViewerMain;
import org.apache.uima.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiceUnitTest {

	protected LeoAEDescriptor aggregate = null;
	protected LeoAEDescriptor aggLearning = null;
	protected LeoTypeSystemDescription types = null;
	protected String outputDir = "src/test/resources/output/xmi";
	protected String inputDir = "src/test/resources/input/";
	protected int numDocs = 18;
	// protected int numDocs = 1;
	protected boolean launchView = false;
	protected String aggXmi = "aggregateDesc";

	@Before
	public void setup() throws Exception {
		Service ds = new Service();
		// aggregate = ds.createPipeline(false);
		LeoTypeSystemDescription types = ds.createTypeSystem();
		aggLearning = ds.createPipeline(types);
		types = ds.createTypeSystem();
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
		String types = "";
		String docText = "";
		AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aggLearning
				.getAnalysisEngineDescription());
		numDocs = 1;
		for (int i = 0; i < numDocs; i++) {
			String filename = null;
			try {
				filename = i + ".txt";
		
				docText = getDocText(filename);
				} catch (Exception e){
					System.out.println("No file found. creating new");
				filename = "test.txt";
				docText =
				"Annular bulge of the disc is seen at C3-C4 level. Spinal canal is widely \r\n"
						+ "patent.  Exit nerve root foramina reveal no signifi @@IMPRESSION@@ 1) Small posterocentral disc protrusion in the upper thoracic spine at \r\n"
						+ "T3-T4 level just abutting the ventral aspect of the cord."
						+

						" ";

				}
			if (StringUtils.isBlank(docText))
				continue;
			JCas jcas = createJCas(ae, docText, filename);
			ae.process(jcas);
			try {
				File xmio = new File(outputDir, filename + ".xmi");
				XmiCasSerializer.serialize(jcas.getCas(), new FileOutputStream(
						xmio));
			} catch (Exception e) {
				continue;
			}
		}
		if (launchView)
			launchViewer();
		System.out.println(types);
		if (launchView)
			System.in.read();
	}// testXmi method

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
		if (aggLearning == null) {
			throw new RuntimeException(
					"Aggregate is null, unable to generate descriptor for viewing xmi");
		}
		aggLearning.toXML(aggXmi);
		String aggLoc = aggLearning.getDescriptorLocator();
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
		File o = new File(outputDir);
		if (o.exists()) {
			FileUtils.deleteRecursive(o);
		}// if
	}// cleanup method

}
