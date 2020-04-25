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
import gov.va.vinci.vitals.pipeline.VitalsPipeline;

import java.io.File;

public class TypeSystem {
    public enum PARENT_CLASS {
        WINDOW("gov.va.vinci.leo.window.types.Window"),
        REGEX("gov.va.vinci.leo.regex.types.RegularExpressionType"),
        APA("gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType"),
        CONTEXT("gov.va.vinci.leo.context.types.Context"),
        SENTENCE("gov.va.vinci.leo.sentence.types.Sentence"),
        ANCHORED_SENTENCE("gov.va.vinci.leo.sentence.types.AnchoredSentence");

        public String type;

        private PARENT_CLASS(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

    }

    public static final String[] regex_types = new String[]{
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
            , "gov.va.vinci.vitals.types.SectionHeader"
           ,  "gov.va.vinci.vitals.types.Hr_value",
            //   TYPE_Bp_value,
            "gov.va.vinci.vitals.types.T_value",
            "gov.va.vinci.vitals.types.Weight_value",
            "gov.va.vinci.vitals.types.Height_value",
            "gov.va.vinci.vitals.types.So2_value",
            "gov.va.vinci.vitals.types.Resp_value",
            "gov.va.vinci.vitals.types.Pain_value",
            "gov.va.vinci.vitals.types.BMI_value"
    };
    public static final String[] pattern_types = new String[]{
            "gov.va.vinci.vitals.types.TermPattern"};

    public static final String[] windowsTypes = new String[]{
            "gov.va.vinci.vitals.types.HiPrecisionWindow",
            "gov.va.vinci.vitals.types.LowerPrecisionWindow",
            "gov.va.vinci.vitals.types.FVWindow",
            "gov.va.vinci.vitals.types.ExcludeAllWindow"
    };
    public static final String[] contextTypes = new String[]{
            "gov.va.vinci.vitals.types.TermContext"
    };
    public static final String[] sentenceTypes = new String[]{
    };
    public static final String[] anchoredTypes = new String[]{
    };
    private static String TYPE_SECTION = "gov.va.vinci.vitals.types.Section";


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
        /** Leo Bones **/

        types.addType(TypeLibrarian.getCSITypeSystemDescription());
        types.addTypeSystemDescription(new WindowAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnnotationPatternAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new SentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new AnchoredSentenceAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new ContextAnnotator().getLeoTypeSystemDescription());
        types.addTypeSystemDescription(new VitalsPipeline().getLeoTypeSystemDescription());
        //#types.addTypeSystemDescription(getLeoTypeSystemDescription_PipelineTypes());
        /**/
        return types;
    }

    public static LeoTypeSystemDescription getLeoTypeSystemDescription_PipelineTypes() {
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


    public static LeoTypeSystemDescription getLeoTypeSystemDescription_MethodTemplate() {
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
    public static void main(String[] args) {
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