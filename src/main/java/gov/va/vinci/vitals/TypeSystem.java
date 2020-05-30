package gov.va.vinci.vitals;

import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.context.ae.ContextAnnotator;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.descriptors.TypeDescriptionBuilder;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.sentence.ae.AnchoredSentenceAnnotator;
import gov.va.vinci.leo.sentence.ae.SentenceAnnotator;
import gov.va.vinci.leo.types.TypeLibrarian;
import gov.va.vinci.leo.window.ae.WindowAnnotator;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class TypeSystem {
    public enum PARENT_CLASS {
        WINDOW("gov.va.vinci.leo.window.types.Window"),
        REGEX("gov.va.vinci.leo.regex.types.RegularExpressionType"),
        APA("gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType"),
        CONTEXT("gov.va.vinci.leo.context.types.Context"),
        SENTENCE("gov.va.vinci.leo.sentence.types.Sentence"),
        ANCHORED_SENTENCE("gov.va.vinci.leo.sentence.types.AnchoredSentence");

        public String type;

        PARENT_CLASS(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

    }

    public static class LearningVariables {
        public static String TYPE_FeatureVector = "gov.va.vinci.vitals.types.Hr_Vector";
        public static String TYPE_Prediction = "gov.va.vinci.vitals.types.Hr_Prediction";
    }

    public static class PipelineVariables {
        public static final String TYPE_NUMERIC = "gov.va.vinci.vitals.types.Numeric";
        public static final String[] TYPES_NUMERIC = new String[]{
                "gov.va.vinci.vitals.types.IntegerNumber",
                "gov.va.vinci.vitals.types.DoubleNumber"
        };
        public static final String TYPE_NUMEXCLUDE = "gov.va.vinci.vitals.types.NumericExclude";
        public static final String TYPE_UNIT = "gov.va.vinci.vitals.types.Unit";
        public static final String TYPE_TERM = "gov.va.vinci.vitals.types.Term";
        public static final String[] TYPES_TERM = new String[]{
                "gov.va.vinci.vitals.types.Bp_Term",
                "gov.va.vinci.vitals.types.Bp_Systolic_Term",
                "gov.va.vinci.vitals.types.Bp_Diastolic_Term",
                "gov.va.vinci.vitals.types.Resp_Term",
                "gov.va.vinci.vitals.types.Hr_Term",
                "gov.va.vinci.vitals.types.Pain_Term",
                "gov.va.vinci.vitals.types.T_Term",
                "gov.va.vinci.vitals.types.Weight_Term",
                "gov.va.vinci.vitals.types.Height_Term",
                "gov.va.vinci.vitals.types.So2_Term",
                "gov.va.vinci.vitals.types.Bmi_Term",
                "gov.va.vinci.vitals.types.Age_Term",
                "gov.va.vinci.vitals.types.NotIt_Term"
        };

        public static final String TYPE_INDICATOR = "gov.va.vinci.vitals.types.Indicator";
        public static final String TYPE_RANGE = "gov.va.vinci.vitals.types.Range";
        public static final String TYPE_POTENTIAL_BP = "gov.va.vinci.vitals.types.PotentialBp";
        public static final String TYPE_EX_POTENTIAL_BP = "gov.va.vinci.vitals.types.ExcludePotentialBp";
        public static final String TYPE_POTENTIAL_HEIGHT = "gov.va.vinci.vitals.types.PotentialHeight";
        public static final String TYPE_EX_POTENTIAL_HEIGHT = "gov.va.vinci.vitals.types.ExcludePotentialHeight";

        public static final String TYPE_TERMEXCLUDE = "gov.va.vinci.vitals.types.TermExclude";
        public static final String TYPE_RELATION = "gov.va.vinci.vitals.types.Relation";

        public static final String TYPE_RELATION_TIMESTAMP = "gov.va.vinci.vitals.types.Relation_Time";

        public static final String TYPE_OUTPUT = "gov.va.vinci.vitals.types.Output_Value";
        public static final String TYPE_Bp_value = "gov.va.vinci.vitals.types.Bp_value";
        public static final String[] TYPES_VALUES = new String[]{
                "gov.va.vinci.vitals.types.Hr_value",
                //   TYPE_Bp_value,
                "gov.va.vinci.vitals.types.T_value",
                "gov.va.vinci.vitals.types.Weight_value",
                "gov.va.vinci.vitals.types.Height_value",
                "gov.va.vinci.vitals.types.So2_value",
                "gov.va.vinci.vitals.types.Resp_value",
                "gov.va.vinci.vitals.types.Pain_value",
                "gov.va.vinci.vitals.types.BMI_value"
        };
        public static final String[] valueBPTypes = new String[]{
                "gov.va.vinci.vitals.types.Bp_Systolic_value",
                "gov.va.vinci.vitals.types.Bp_Diastolic_value"};
    }

    public static final String[] regex_types = new String[]{
            "gov.va.vinci.vitals.types.SectionHeader",
            "gov.va.vinci.vitals.types.Month",
            "gov.va.vinci.vitals.types.ExcludePrefix",
            "gov.va.vinci.vitals.types.Timestamp"
    };

    public static final String[] pattern_types = new String[]{
            PipelineVariables.TYPE_INDICATOR,
            PipelineVariables.TYPE_TERMEXCLUDE,
            PipelineVariables.TYPE_NUMEXCLUDE,
            PipelineVariables.TYPE_RELATION,
            PipelineVariables.TYPE_RELATION_TIMESTAMP
    };
    public static final String[] windowsTypes = new String[]{
            "gov.va.vinci.vitals.types.HiPrecisionWindow",
            "gov.va.vinci.vitals.types.LowerPrecisionWindow",
            "gov.va.vinci.vitals.types.FVWindow",
            "gov.va.vinci.vitals.types.ExcludeAllWindow"
    };
    public static final String[] sentenceTypes = new String[]{
    };
    public static final String[] anchoredTypes = new String[]{
    };
    public static final String[] contextTypes = new String[]{
    };
    private static String TYPE_SECTION = "gov.va.vinci.vitals.types.Section";

    /**
     * public static class PipelineVariables {
     * <p>
     * static String RESOURCE_PATH = "src/main/resources/";
     * static String TYPE_NUMERIC = "gov.va.vinci.vitals.types.Numeric";
     * static String[] TYPES_NUMERIC = new String[] {
     * "gov.va.vinci.vitals.types.IntegerNumber",
     * "gov.va.vinci.vitals.types.DoubleNumber"
     * };
     * <p>
     * <p>
     * <p>
     * <p>
     * static String TYPE_INDICATOR = "gov.va.vinci.vitals.types.Indicator";
     * <p>
     * static String TYPE_NUMEXCLUDE = "gov.va.vinci.vitals.types.NumericExclude";
     * <p>
     * static String TYPE_RANGE = "gov.va.vinci.vitals.types.Range";
     * <p>
     * static String TYPE_POTENTIAL_BP = "gov.va.vinci.vitals.types.PotentialBp";
     * static String TYPE_EX_POTENTIAL_BP = "gov.va.vinci.vitals.types.ExcludePotentialBp";
     * static String TYPE_POTENTIAL_HEIGHT = "gov.va.vinci.vitals.types.PotentialHeight";
     * static String TYPE_EX_POTENTIAL_HEIGHT = "gov.va.vinci.vitals.types.ExcludePotentialHeight";
     * <p>

     * <p>
     * <p>
     * static String TYPE_RELATION = "gov.va.vinci.vitals.types.Relation";
     * <p>
     * <p>
     * static String TYPE_RELATION_TIMESTAMP = "gov.va.vinci.vitals.types.Relation_Time";
     * <p>
     * <p>
     * static String TYPE_OUTPUT = "gov.va.vinci.vitals.types.Output_Value";
     * static String TYPE_Bp_value = "gov.va.vinci.vitals.types.Bp_value";
     * static String[] valueTypes = new String[] {
     * "gov.va.vinci.vitals.types.Hr_value",
     * //   TYPE_Bp_value,
     * "gov.va.vinci.vitals.types.T_value",
     * "gov.va.vinci.vitals.types.Weight_value",
     * "gov.va.vinci.vitals.types.Height_value",
     * "gov.va.vinci.vitals.types.So2_value",
     * "gov.va.vinci.vitals.types.Resp_value",
     * "gov.va.vinci.vitals.types.Pain_value",
     * "gov.va.vinci.vitals.types.BMI_value"
     * };
     * static String[] valueBPTypes = new String[] {
     * "gov.va.vinci.vitals.types.Bp_Systolic_value",
     * "gov.va.vinci.vitals.types.Bp_Diastolic_value" };
     * <p>
     * static String TYPE_WINDOW = "gov.va.vinci.leo.window.types.Window";
     * static String[] TYPES_WINDOW = new String[] {
     * "gov.va.vinci.vitals.types.HiPrecisionWindow",
     * "gov.va.vinci.vitals.types.LowerPrecisionWindow",
     * "gov.va.vinci.vitals.types.FVWindow",
     * "gov.va.vinci.vitals.types.ExcludeAllWindow"
     * };
     * }
     * <p>
     * public static class LearningVariables {
     * static String TYPE_FeatureVector = "gov.va.vinci.vitals.types.Hr_Vector";
     * static String TYPE_Prediction = "gov.va.vinci.vitals.types.Hr_Prediction";
     *;
     * }
     * /*
     *     public LeoTypeSystemDescription getLeoTypeSystemDescription2()   {
     *         LeoTypeSystemDescription types = new LeoTypeSystemDescription();
     *         types.addType(TypeLibrarian.getCSITypeSystemDescription());
     *         try {
     *             // Adding all knowtator annotations to the type list
     *             boolean addExtra = true;
     *             TypeDescription kttrType;
     *             String kttrStrType = "gov.va.vinci.kttr.types.RefValue";
     *             kttrType = new TypeDescription_impl(kttrStrType, "", "uima.tcas.Annotation");
     *             types.addType(kttrType);
     *             /*
     *             for (String type : KnowtatorVariables.uimaTypeFeatureMap.keySet()) {
     *                 TypeDescription newType;
     *                 newType = new TypeDescription_impl(type, "", kttrStrType);
     *                 for (String feature : KnowtatorVariables.uimaTypeFeatureMap.get(type)) {
     *                     newType.addFeature(feature, "", "uima.cas.String");
     *                 }
     *                 addExtra = false;
     *                 types.addType(newType);
     *             }
     *
     *
     *             if (addExtra)
     *                 types.addType("gov.va.vinci.kttr.types.HRValue", "", kttrStrType);
     *
     *
     *             // Regex default type
     *             types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
     *
     *             TypeDescription numType = new TypeDescription_impl(PipelineVariables.TYPE_NUMERIC, "",
     *                     TypeSystem.PARENT_CLASS.REGEX.getType());
     *             numType.addFeature("comment", "", "uima.cas.String");
     *             numType.addFeature("value", "", "uima.cas.Double");
     *             numType.addFeature("decimal", "", "uima.cas.Boolean");
     *             numType.addFeature("integer", "", "uima.cas.Boolean");
     *             numType.addFeature("zero_decimal", "", "uima.cas.Boolean");
     *             numType.addFeature("unit", "", "uima.tcas.Annotation");
     *             numType.addFeature("source", "", "uima.cas.String");
     *             numType.addFeature("timestamp", "", "uima.tcas.Annotation");
     *             types.addType(numType);
     *
     *             for (String a : PipelineVariables.TYPES_NUMERIC) {
     *                 types.addType(a, "", PipelineVariables.TYPE_NUMERIC);
     *             }
     *
     *             //////////////////
     *             types.addType(PipelineVariables.TYPE_UNIT, "",TypeSystem.PARENT_CLASS.REGEX.getType());
     *             types.addType(PipelineVariables.TYPE_TERM, "", TypeSystem.PARENT_CLASS.REGEX.getType());
     *             for (String a : PipelineVariables.TYPES_TERM) {
     *                 types.addType(a, "", PipelineVariables.TYPE_TERM);
     *             }
     *             types.addType(PipelineVariables.TYPE_TIMESTAMP, "", TypeSystem.PARENT_CLASS.REGEX.getType());
     *
     *             // APA default type
     *             TypeDescription newType = new TypeDescription_impl(PipelineVariables.PatternType, "",
     *                     "uima.tcas.Annotation");
     *             newType.addFeature("pattern", "", "uima.cas.String");
     *             newType.addFeature("anchor", "", "uima.tcas.Annotation");
     *             newType.addFeature("target", "", "uima.tcas.Annotation");
     *             newType.addFeature("anchorPattern", "", "uima.cas.String");
     *             newType.addFeature("targetPattern", "", "uima.cas.String");
     *             types.addType(newType);
     *
     *             types.addType(PipelineVariables.TYPE_INDICATOR, "", PipelineVariables.PatternType);
     *             types.addType(PipelineVariables.TYPE_TERMEXCLUDE, "", PipelineVariables.PatternType);
     *             types.addType(PipelineVariables.TYPE_NUMEXCLUDE, "", PipelineVariables.PatternType);
     *
     *             types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_RANGE, "", PipelineVariables.PatternType)
     *                     .addFeature("value1", "", "uima.tcas.Annotation")
     *                     .addFeature("value2", "", "uima.tcas.Annotation")
     *                     .getTypeDescription());
     *
     *             types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_POTENTIAL_BP, "", PipelineVariables.PatternType)
     *                     .addFeature("value1", "", "uima.tcas.Annotation")
     *                     .addFeature("value2", "", "uima.tcas.Annotation")
     *                     .getTypeDescription());
     *             types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_EX_POTENTIAL_BP, "", PipelineVariables.PatternType)
     *                     .getTypeDescription());
     *
     *             types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_POTENTIAL_HEIGHT, "", PipelineVariables.PatternType)
     *                     .addFeature("value1", "", "uima.tcas.Annotation")
     *                     .addFeature("value2", "", "uima.tcas.Annotation")
     *                     .getTypeDescription());
     *
     *             types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_EX_POTENTIAL_HEIGHT, "", "uima.tcas.Annotation")
     *                     .getTypeDescription());
     *
     *             types.addType(PipelineVariables.TYPE_RELATION, "", PipelineVariables.PatternType);
     *             types.addType(PipelineVariables.TYPE_RELATION_TIMESTAMP, "", PipelineVariables.PatternType);
     *
     *             types.addType(TypeDescriptionBuilder.create(LearningVariables.TYPE_FeatureVector,
     *                     "Type used to store the fearures and values", "uima.tcas.Annotation")
     *                     .addFeature("keys", "", "uima.cas.StringArray")
     *                     .addFeature("values", "", "uima.cas.StringArray")
     *                     .addFeature("context", "", "uima.tcas.Annotation")
     *                     .getTypeDescription());
     *
     *             types.addType(TypeDescriptionBuilder
     *                     .create(LearningVariables.TYPE_Prediction, "Type used to output predictions", "uima.tcas.Annotation")
     *                     .addFeature("srcFVFeature", "Feature vector annotation", "uima.tcas.Annotation")
     *                     .addFeature("prediction", "", "uima.cas.String")
     *                     .getTypeDescription());
     *
     *             // Additional annotations for specific values
     *
     *             TypeDescription outType = new TypeDescription_impl(PipelineVariables.TYPE_OUTPUT, "",
     *                     "uima.tcas.Annotation");
     *             outType.addFeature("value", "", "uima.cas.String");
     *             outType.addFeature("valueAnnotation", "", "uima.tcas.Annotation");
     *             outType.addFeature("concept", "", "uima.cas.String");
     *             outType.addFeature("unit", "", "uima.tcas.Annotation");
     *             outType.addFeature("source", "", "uima.cas.String");
     *             outType.addFeature("timestamp", "", "uima.tcas.Annotation");
     *             types.addType(outType);
     *
     *             for (String a : PipelineVariables.valueTypes) {
     *                 types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_OUTPUT));
     *             }
     *             types.addType(new TypeDescription_impl(PipelineVariables.TYPE_Bp_value, "", PipelineVariables.TYPE_OUTPUT));
     *
     *             for (String a : PipelineVariables.valueBPTypes) {
     *                 types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_Bp_value));
     *             }
     *
     *             types.addTypeSystemDescription(new WindowAnnotator().getLeoTypeSystemDescription());
     *             for (String a : PipelineVariables.TYPES_WINDOW) {
     *                 types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_WINDOW));
     *             }
     *
     *
     *             types.addType(new TypeDescription_impl("gov.va.vinci.vitals.types.Month", "", TypeSystem.PARENT_CLASS.REGEX.getType()));
     *
     *             types.addType(new TypeDescription_impl("gov.va.vinci.vitals.types.ExcludePrefix", "",TypeSystem.PARENT_CLASS.REGEX.getType()));
     *         } catch(Exception e) {
     *             System.out.print("Opps");
     *         }
     *         return types;
     *     }
     */
    public static class KnowtatorVariables {
        public static HashMap<String, ArrayList<String>> uimaTypeFeatureMap = new HashMap<String, ArrayList<String>>();

    }

    public static LeoTypeSystemDescription getLeoTypeSystemDescription_DuplicateTypes() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription();
        types.addType(TypeDescriptionBuilder.create("gov.va.vinci.leo.types.DuplicateType", "", "uima.tcas.Annotation")
                .addFeature("original", "The original annotation. ", "uima.tcas.Annotation")
                .addFeature("ExtraAnnotationFeature", "Just in case feature ", "uima.tcas.Annotation")
                .addFeature("ExtraStringFeature", "Just in case feature ", "uima.cas.String")
                .getTypeDescription());
        return types;
    }

    public static LeoTypeSystemDescription getLeoTypeSystemDescription() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription();
        types.addType(TypeLibrarian.getCSITypeSystemDescription());
        types.addTypeSystemDescription(new WindowAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnnotationPatternAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new SentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnchoredSentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new ContextAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(getLeoTypeSystemDescription_BasePipelineTypes());
        types.addTypeSystemDescription(getLeoTypeSystemDescription_PipelineTypes());
        types.addTypeSystemDescription(getLeoTypeSystemDescription_KttrTypes());
        return types;
    }


    public static LeoTypeSystemDescription getLeoTypeSystemDescription_BasePipelineTypes() {
        LeoTypeSystemDescription description = new LeoTypeSystemDescription();
        try {

            for (String r : regex_types) {
                description.addType(r, "RegEx type", PARENT_CLASS.REGEX.type);
            }
            for (String r : pattern_types) {
                description.addType(r, "Annotation Pattern Annotator type", PARENT_CLASS.APA.type);
            }
            for (String r : windowsTypes) {
                description.addType(r, "Window type", PARENT_CLASS.WINDOW.type);
            }
            for (String r : contextTypes) {
                description.addType(TypeDescriptionBuilder.create(r, "Context type", PARENT_CLASS.CONTEXT.type)
                        .addFeature("Section", "Section header", "uima.cas.String")
                        .addFeature("SectionHeaderText", "Section header", "uima.cas.String")
                        .getTypeDescription());
            }
            for (String r : sentenceTypes) {
                description.addType(r, "Sentence type", PARENT_CLASS.SENTENCE.type);
            }
            for (String r : anchoredTypes) {
                description.addType(r, "Anchored sentence type", PARENT_CLASS.ANCHORED_SENTENCE.type);
            }
            description.addType(TypeDescriptionBuilder.create(TYPE_SECTION, "Section Type", "uima.tcas.Annotation")
                    .addFeature("SectionHeader", "Anchor annotation around which the section was created", "uima.tcas.Annotation")
                    .addFeature("SectionHeaderText", "text of the header", "uima.cas.String")
                    .addFeature("Term", "text of the header", "uima.cas.String")
                    .addFeature("Snippet", "text", "uima.cas.String")
                    .getTypeDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return description;
    }

    public static LeoTypeSystemDescription getLeoTypeSystemDescription_PipelineTypes() {
        LeoTypeSystemDescription types = new LeoTypeSystemDescription();
        try {

            types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_NUMERIC, "", PARENT_CLASS.REGEX.type)
                    .addFeature("comment", "", "uima.cas.String")
                    .addFeature("value", "", "uima.cas.Double")
                    .addFeature("decimal", "", "uima.cas.Boolean")
                    .addFeature("integer", "", "uima.cas.Boolean")
                    .addFeature("zero_decimal", "", "uima.cas.Boolean")
                    .addFeature("unit", "", "uima.tcas.Annotation")
                    .addFeature("source", "", "uima.cas.String")
                    .addFeature("timestamp", "", "uima.tcas.Annotation").getTypeDescription());

            for (String a : PipelineVariables.TYPES_NUMERIC) {
                types.addType(a, "", PipelineVariables.TYPE_NUMERIC);
            }

            //////////////////
            types.addType(PipelineVariables.TYPE_TERM, "", PARENT_CLASS.REGEX.getType());
            for (String a : PipelineVariables.TYPES_TERM) {
                types.addType(a, "", PipelineVariables.TYPE_TERM);
            }
            //////////////////
            types.addType(PipelineVariables.TYPE_UNIT, "", PARENT_CLASS.REGEX.getType());


            types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_RANGE, "", PARENT_CLASS.APA.getType())
                    .addFeature("value1", "", "uima.tcas.Annotation")
                    .addFeature("value2", "", "uima.tcas.Annotation")
                    .getTypeDescription());

            types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_POTENTIAL_BP, "", PARENT_CLASS.APA.getType())
                    .addFeature("value1", "", "uima.tcas.Annotation")
                    .addFeature("value2", "", "uima.tcas.Annotation")
                    .getTypeDescription());
            types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_EX_POTENTIAL_BP, "", PARENT_CLASS.APA.getType())
                    .getTypeDescription());

            types.addType(TypeDescriptionBuilder.create(PipelineVariables.TYPE_POTENTIAL_HEIGHT, "", PARENT_CLASS.APA.getType())
                    .addFeature("value1", "", "uima.tcas.Annotation")
                    .addFeature("value2", "", "uima.tcas.Annotation")
                    .getTypeDescription());

            types.addType(PipelineVariables.TYPE_EX_POTENTIAL_HEIGHT, "", PARENT_CLASS.REGEX.getType());


            types.addType(TypeDescriptionBuilder.create(LearningVariables.TYPE_FeatureVector,
                    "Type used to store the fearures and values", "uima.tcas.Annotation")
                    .addFeature("keys", "", "uima.cas.StringArray")
                    .addFeature("values", "", "uima.cas.StringArray")
                    .addFeature("context", "", "uima.tcas.Annotation")
                    .getTypeDescription());

            types.addType(TypeDescriptionBuilder
                    .create(LearningVariables.TYPE_Prediction, "Type used to output predictions", "uima.tcas.Annotation")
                    .addFeature("srcFVFeature", "Feature vector annotation", "uima.tcas.Annotation")
                    .addFeature("prediction", "", "uima.cas.String")
                    .getTypeDescription());

            // Additional annotations for specific values

            types.addType(TypeDescriptionBuilder
                    .create(PipelineVariables.TYPE_OUTPUT, "", "uima.tcas.Annotation")
                    .addFeature("value", "", "uima.cas.String")
                    .addFeature("valueAnnotation", "", "uima.tcas.Annotation")
                    .addFeature("concept", "", "uima.cas.String")
                    .addFeature("unit", "", "uima.tcas.Annotation")
                    .addFeature("unitString", "", "uima.cas.String")
                    .addFeature("source", "", "uima.cas.String")
                    .addFeature("timestamp", "", "uima.tcas.Annotation")
                    .addFeature("timestampString", "", "uima.cas.String")
                    .addFeature("section", "", "uima.tcas.Annotation")
                    .addFeature("sectionType", "", "uima.cas.String")
                    .getTypeDescription());

            for (String a : PipelineVariables.TYPES_VALUES) {
                types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_OUTPUT));
            }
            types.addType(new TypeDescription_impl(PipelineVariables.TYPE_Bp_value, "", PipelineVariables.TYPE_OUTPUT));

            for (String a : PipelineVariables.valueBPTypes) {
                types.addType(new TypeDescription_impl(a, "", PipelineVariables.TYPE_Bp_value));
            }


        } catch (Exception e) {
            System.out.print("Oops");
        }
        return types;


    }


    public static LeoTypeSystemDescription getLeoTypeSystemDescription_KttrTypes() {
        String ktt_type = "gov.va.vinci.leo.types.ValidationAnnotation";
        LeoTypeSystemDescription description = new LeoTypeSystemDescription();
        description.addType(TypeLibrarian.getValidationAnnotationTypeSystemDescription());
        try {
            // Adding all knowtator annotations to the type list
            boolean addExtra = true;
            TypeDescription kttrType;
            String kttrStrType = "gov.va.vinci.kttr.types.RefValue";
            kttrType = new TypeDescription_impl(kttrStrType, "", "uima.tcas.Annotation");
            description.addType(kttrType);
            for (String type : KnowtatorVariables.uimaTypeFeatureMap.keySet()) {
                TypeDescription newType;
                newType = new TypeDescription_impl(type, "", kttrStrType);
                for (String feature : KnowtatorVariables.uimaTypeFeatureMap.get(type)) {
                    newType.addFeature(feature, "", "uima.cas.String");
                }
                addExtra = false;
                description.addType(newType);
            }
            if (addExtra)
                description.addType("gov.va.vinci.kttr.types.HRValue", "", kttrStrType);



            description.addType("gov.va.vinci.kttr.types.BPValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.TValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.HRValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.BMIValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.HeightValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.WeightValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.PainValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.RespValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.OxygenValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.BPDiasValue", "", ktt_type);
            description.addType("gov.va.vinci.kttr.types.BPSysValue", "", ktt_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return description;
    }


    public static LeoTypeSystemDescription getLeoTypeSystemDescription_MethodTemplate () {
        LeoTypeSystemDescription description = new LeoTypeSystemDescription();
        return description;
    }

    /**
     * Generate type descriptor
     *
     * @param args
     */
    /**
     * Generate type files and descriptor
     *
     * @param args
     */
    public static void main (String[]args){
        try {
            LeoTypeSystemDescription types = new LeoTypeSystemDescription();
            types.addTypeSystemDescription(getLeoTypeSystemDescription());

            File srcDir = new File("generated-types/src");
            srcDir.mkdirs();

            File classesDir = new File("generated-types/classes");
            classesDir.mkdirs();

            types.jCasGen(srcDir.getCanonicalPath(), classesDir.getCanonicalPath());

            File resDir = new File("generated-types/");
            resDir.mkdirs();

            types.toXML(resDir.getCanonicalPath() + "/TypeSystem.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}