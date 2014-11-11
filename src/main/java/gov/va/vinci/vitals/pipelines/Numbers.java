package gov.va.vinci.vitals.pipelines;

import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.vitals.Service2.PipelineVariables;

/**
 * This pipeline finds numeric values of different types and detects the numeric values that they represent.
 * @author vhaslcpatteo
 *
 */
public class Numbers implements PipelineInterface {
	LeoAEDescriptor pipeline = null;
	static String TYPE_NUMERIC = "gov.va.vinci.vitals.types.Numeric";
	static String RESOURCE_PATH = "src/main/resources/";
	static String resourceNumbers = "numbers.groovy";
	static String[] TYPES_NUMERIC = new String[] {
	    "gov.va.vinci.vitals.types.WholeNumber",
	    "gov.va.vinci.vitals.types.SingleNumber",
	    "gov.va.vinci.vitals.types.DecimalNumber",
	    "gov.va.vinci.vitals.types.ZeroNumber"
	};

	@Override
	public LeoAEDescriptor getPipeline() {
		pipeline = new LeoAEDescriptor();

		try {
			pipeline.addDelegate(new LeoAEDescriptor()
			    .setName("NumericAnnotator")
			    .setImplementationName(RegexAnnotator.class.getCanonicalName())
			    .addParameterSetting(RegexAnnotator.Param.GROOVY_CONFIG_FILE.getName(), true, false, "String",
			        RESOURCE_PATH + resourceNumbers)
			    );
			    
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return pipeline;
	}

	@Override
	public LeoTypeSystemDescription getLeoTypeSystemDescription() {
		LeoTypeSystemDescription types = new LeoTypeSystemDescription();
		try {
			types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());

			TypeDescription numType = new TypeDescription_impl(TYPE_NUMERIC, "",
			    "gov.va.vinci.leo.regex.types.RegularExpressionType");
			numType.addFeature("comment", "", "uima.cas.String");
			numType.addFeature("value1", "", "uima.cas.Double");
			numType.addFeature("value2", "", "uima.cas.Double");
			numType.addFeature("unit", "", "uima.tcas.Annotation");
			numType.addFeature("source", "", "uima.cas.String");
			numType.addFeature("timestamp", "", "uima.tcas.Annotation");

			for (String a : TYPES_NUMERIC) {
				types.addType(a, "", TYPE_NUMERIC);
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return types;
	}
}
