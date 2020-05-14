package gov.va.vinci.vitals.pipeline;

import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.filter.ae.FilterAnnotator;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.sherlock.ae.LearningAnnotator;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.window.ae.WindowAnnotator;
import gov.va.vinci.svmlib.ml.SvmVectorTranslator;
import gov.va.vinci.vitals.TypeSystem;
import gov.va.vinci.vitals.ae.*;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static gov.va.vinci.vitals.TypeSystem.PipelineVariables.*;

public class VitalsPipeline extends BasePipeline {
    private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());
    public static String RESOURCE_PATH = "src/main/resources/";
    static String Hr_SvmModelPath =  RESOURCE_PATH + "/hr_model.svm";

    public VitalsPipeline() {
        this(null);
    }

    public VitalsPipeline(HashMap args) {
        pipeline = new LeoAEDescriptor();
        try {
            pipeline.addDelegate(createNumericPipeline());
            pipeline.addDelegate(createTermAndIndicatorPipeline());
            pipeline.addDelegate(createWindowsPipeline());
            pipeline.addDelegate(createPatternsPipeline());
            pipeline.addDelegate(createVitalRulesPipeline());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LeoTypeSystemDescription defineTypeSystem() {
        description = TypeSystem.getLeoTypeSystemDescription();
        return description;
    }

    protected LeoAEDescriptor createNumericPipeline() throws Exception {
        LeoAEDescriptor pipe = new LeoAEDescriptor();
        pipe.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "numbers.groovy")
                .setName("NumericRegexAnnotator")
                .getLeoAEDescriptor()
                .addTypeSystemDescription(getLeoTypeSystemDescription())
        );
        pipe.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.vitals.types.DoubleNumber","gov.va.vinci.vitals.types.IntegerNumber"})
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepTypesNumber")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));
        // remove all numeric types ( Integer or DoubleNumber) if covered by another numeric
        pipe.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.vitals.types.DoubleNumber"})
                .setTypesToDelete(new String[]{"gov.va.vinci.vitals.types.IntegerNumber"})
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepTypesNumberRemoveTypesNumber")
                .getLeoAEDescriptor().setTypeSystemDescription(getLeoTypeSystemDescription()));

        pipe.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "dates.groovy")
                .setName("DateRegexAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipe.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "numericValuesExclude.pattern")
                .setOutputType(TYPE_NUMEXCLUDE)
                .getLeoAEDescriptor().setName("ExcludeNumberPattern")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipe.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "numericValuesExclude.groovy")
                .setName("ExcludeNumberPattern")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipe.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_NUMEXCLUDE})
                .setTypesToDelete(TYPES_NUMERIC)
                .setRemoveOverlapping(true)
                .setDeleteChildren(true)
                .setName("AnnotationFilterKeepNumExcludeRemoveNumeric")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipe.addDelegate(new AnalyzeNumbersAE()
                .setName("AnalyzeNumbersAE")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        return pipe;
    }

    protected LeoAEDescriptor createTermAndIndicatorPipeline() throws Exception {
        LeoAEDescriptor aggregate = new LeoAEDescriptor();
        aggregate.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "unitsOfMeasure.groovy")
                .setName("UnitsAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "concepts.groovy")
                .getLeoAEDescriptor()
                .setName("TermAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        /** INFO: Filter unneeded annotations*/

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(TYPES_TERM)
                .setKeepChildren(true)
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepTermRemoveTerm")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_UNIT})
                .setName("AnnotationFilterKeepUnit")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        // delete terms that are covered by units -- FIXME: exception "BPS"
        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_UNIT})
                .setTypesToDelete(TYPES_TERM)
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepUnitRemoveTerm")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "indicator.pattern")
                .setOutputType(TYPE_INDICATOR)
                .getLeoAEDescriptor().setName("IndicatorPatternAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "indicator.groovy")
                .setName("IndicatorRegexAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_INDICATOR})
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepTypeIndication")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setResource(RESOURCE_PATH + "excludeSectionHeader.pattern")
                .setIncludeChildAnnotations(true)
                .setOutputType(TYPE_TERMEXCLUDE)
                .setName("TermExcludePatternAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile(RESOURCE_PATH + "excludeSectionHeader.groovy")
                .setName("TermExcludePatternAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        return aggregate;
    }

    protected LeoAEDescriptor createWindowsPipeline() throws Exception {
        LeoAEDescriptor aggregate = new LeoAEDescriptor();

        aggregate.addDelegate(new WindowAnnotator("gov.va.vinci.vitals.types.HiPrecisionWindow", TYPE_INDICATOR)
                .setAnchorFeature("Anchor")
                .setRtWindowSize(new Integer(20))
                .getLeoAEDescriptor()
                .setTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new WindowAnnotator("gov.va.vinci.vitals.types.LowerPrecisionWindow", TYPE_INDICATOR)
                .setAnchorFeature("Anchor")
                .setRtWindowSize(new Integer(50))
                .getLeoAEDescriptor()
                .setTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new WindowAnnotator("gov.va.vinci.vitals.types.ExcludeAllWindow", TYPE_TERMEXCLUDE)
                .setAnchorFeature("Anchor")
                .setRtWindowSize(new Integer(10))
                .getLeoAEDescriptor()
                .setTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.vitals.types.ExcludeAllWindow"})
                .setTypesToDelete(new String[]{TYPE_NUMERIC})
                .setDeleteChildren(true)
                .setRemoveOverlapping(true)
                .setName("AnnotationFilterKeepExcludeAllWindowRemoveNumeric")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        return aggregate;
    }

    protected LeoAEDescriptor createPatternsPipeline() throws Exception {
        LeoAEDescriptor aggregate = new LeoAEDescriptor();
        ///////////// INFO: Creating patterns
        /**/
        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "range.pattern")
                .setOutputType(TYPE_RANGE)
                .setName("RangePattern")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        /**/

        aggregate.addDelegate(new AdjustRangeAnnotator().setName("AdjustRangeAnnotator")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "bp.pattern")
                .setOutputType(TYPE_POTENTIAL_BP)
                .setName("PotentialBpPattern")
                .getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "height.pattern")
                .setOutputType(TYPE_POTENTIAL_HEIGHT)
                .setName("PotentialHeightPattern")
                .getLeoAEDescriptor()
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "bp_exclude.pattern")
                .setOutputType(TYPE_EX_POTENTIAL_BP)
                .setName("PotentialBpPattern")
                .getLeoAEDescriptor()
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{TYPE_EX_POTENTIAL_BP})
                .setTypesToDelete(new String[]{TYPE_POTENTIAL_BP})
                .setRemoveOverlapping(false)
                .setName("AnnotationFilterKeepExPotentialBPRemovePotentialBP")
                .getLeoAEDescriptor()
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new AdjustPotentialBpAE()
                .getLeoAEDescriptor()
                .setName("AdjustPotentialBpAE")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        /**/
        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "relation.pattern")
                .setOutputType(TYPE_RELATION)
                .getLeoAEDescriptor()
                .setName("RelationPatternAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new AnnotationPatternAnnotator()
                .setIncludeChildAnnotations(true)
                .setResource(RESOURCE_PATH + "relation_time.pattern")
                .setOutputType(TYPE_RELATION_TIMESTAMP)
                .getLeoAEDescriptor()
                .setName("RelationWithTimePatternAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        // cannot filter duplicate Patterns because some patterns overlap by design.
        // FIXME: create a special AE that filters out overlapping patterns that have the same Numeric as target.
        //  create annotator that removes Relation if the target is a part of Range or PotentialBp
        // This has to be done because overannotations cause multiple problems
        // 	aggregate.addDelegate(new FilterRelationsAE().getLeoAEDescriptor().setName("FilterRelationsAE").addTypeSystemDescription(types));

        return aggregate;
    }

    protected LeoAEDescriptor createVitalRulesPipeline() throws Exception {
        LeoAEDescriptor aggregate = new LeoAEDescriptor();
        aggregate.addDelegate(new AssignUnitAndTimeAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new MarkNotItAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractTemperatureAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractSo2AE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractBmi().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractBloodPressureAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractRespiratoryAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractHeightAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractWeightAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractPainAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new ExtractHeartRateAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        // Remove overannotated

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(TYPES_VALUES)
                .setRemoveOverlapping(false)
                .getLeoAEDescriptor().setName("AnnotationFilterKeepValueTypes")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        aggregate.addDelegate(new FilterAnnotator()
                .setTypesToKeep(valueBPTypes)
                .setRemoveOverlapping(false)
                .getLeoAEDescriptor().setName("AnnotationFilterKeepValueBPTypes")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        aggregate.addDelegate(new FilterTimestampAE().getLeoAEDescriptor().addTypeSystemDescription(getLeoTypeSystemDescription()));

        return aggregate;
    }
    /**/
    protected LeoAEDescriptor createML_Pipeline( String environment) throws Exception {
        LeoAEDescriptor aggregate = new LeoAEDescriptor();
        if ("predict".equalsIgnoreCase(environment) || "train".equalsIgnoreCase(environment)) {
            aggregate.addDelegate(new HrVectorAnnotator()
                    .setKeysFeature("keys")
                    .setValuesFeature("values")
                    .setOutputType(TypeSystem.LearningVariables.TYPE_FeatureVector)
                    .setInputTypes( new String[] { "gov.va.vinci.kttr.type.Hr_value", "gov.va.vinci.vitals.type.HRValue" })
                    .getLeoAEDescriptor().setName("HrVectorAnnotator")
                    .addTypeSystemDescription(getLeoTypeSystemDescription()));

            if ("predict".equalsIgnoreCase(environment)) {
                aggregate.addDelegate(LearningAnnotator.getLeoAEDescriptor(
                        SvmVectorTranslator.class.getCanonicalName(),
                        TypeSystem.LearningVariables.TYPE_Prediction, "srcFVFeature", "prediction",
                        TypeSystem.LearningVariables.TYPE_FeatureVector,
                        "keys", "values", Hr_SvmModelPath).addTypeSystemDescription(getLeoTypeSystemDescription()));
                ;

                aggregate.addDelegate(new FilterHeartRateAnnotator().getLeoAEDescriptor()
                        .setName("FilterHeartRateAnnotator")
                        .addTypeSystemDescription(getLeoTypeSystemDescription()));
            }
        }
        return aggregate;
    }
    /**/

}
