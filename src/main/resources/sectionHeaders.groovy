    import gov.va.vinci.leo.regex.types.RegularExpressionType

    /* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
    name = "SectionHeaderAnnotator"

    configuration {
        /* All configuration for this annotator. */
        defaults {
            /* Global for all configrations below if a property specified here is not overridden in a section below. */

            outputType = "gov.va.vinci.vitals.types.SectionHeader"
            case_sensitive = false
            matchedPatternFeatureName = "pattern"
            concept_feature_name = "concept"
            groupFeatureName = "group"
        }
    /**/
        "separator" {
            expressions = [
                   // '\\r\\n *\\r\\n'
                    '^\\s*\\w'
            ]
            concept_feature_value = "separator"
            //
        }

    /**/
        "vitals" {
            expressions = [
                    '\\bVS:'
                    , '\\R{1,2} *vital *signs?.{0,5}:'
                    , 'Vitals:'
                    , 'Anthropometrics:'
                    , "Today's Vital Signs"
                    , "Vital Signs"
                    , 'ANTHROPOMETRIC MEASUREMENTS'
            ]
            concept_feature_value = "Vitals"

        }
        "pmh" {
            expressions = [
                    '(past )?medica\\s+(history|hx)(:|-)',
                    '\\bmhx?(:|-)',
                    '\\bmh?(:|-)',
                    'pohx(:|-)',
                    'pmh?(:|-)',
                    'past\\s*history(:|-)',
                    'history(:|-)',
                    'p[hm]+\\s*computerized\\s+problem\\s+list(:|-)',
                    'significant\\smedical\\shx(:|-)',
                    'past\\smedical\\shistory\\s?\\/\\s?problem list(:|-)',
                    //SecTag Additions
                    'patient_history(:|-)',
                    'patient history(:|-)',
                    'pt history(:|-)',
                    'history/physical examination(:|-)',
                    'history physical examination(:|-)',
                    'clinical history(:|-)',
                    'in clinical history(:|-)',
                    'clinical history/indications(:|-)',
                    'clinical history indication(:|-)',
                    'issues briefly as following(:|-)',
                    'issue briefly as following(:|-)',
                    'history(:|-)',
                    'current medical problems(:|-)',
                    'current medical problem(:|-)',
                    'history of chronic illness(:|-)',
                    'history chronic illness(:|-)',
                    'clinical presentation(:|-)',
                    'issues briefly as follows(:|-)',
                    'issue briefly as follow(:|-)',
                    'interval history(:|-)',
                    'past medical history and review of systems(:|-)',
                    'past medical history and review of system(:|-)',
                    'past medical history review system(:|-)',
                    'past medical problems(:|-)',
                    'past medical problem(:|-)',
                    'history of past illness(:|-)',
                    'history past illness(:|-)',
                    'past medical history(:|-)',
                    'previous medical history(:|-)',
                    'hematology/oncology history(:|-)',
                    'hematology oncology history(:|-)',
                    'history of general health(:|-)',
                    'history general health(:|-)',
                    'past medical history/past surgical history(:|-)',
                    'past medical history past surgical history(:|-)',
                    'medical problems(:|-)',
                    'medical problem(:|-)',
                    'significant past medical history(:|-)',
                    'history of major illnesses and injuries(:|-)',
                    'history of major illness and injury(:|-)',
                    'history major illness injury(:|-)',
                    'past med history(:|-)',
                    'past hospitalization history(:|-)',
                    'medical history(:|-)',
                    'past medical and surgical history(:|-)',
                    'past medical surgical history(:|-)',
                    'brief medical history(:|-)',
                    'Past Medical History/Problem List(:|-)',
                    'past medical history problem list(:|-)',
                    'past medical issues(:|-)',
                    'past medical issue(:|-)',
                    'past_medical_history(:|-)',
                    'past medical history/surgical history(:|-)',
                    'past medical history surgical history(:|-)',
                    'past infectious history(:|-)',
                    'past medical history/family history(:|-)',
                    'past medical history family history(:|-)',
                    'Known Significant Medical Diagnoses and Conditions(:|-)',
                    'past history(:|-)',
                    'past medical history and physical examination(:|-)',
                    'past medical history physical examination(:|-)',
                    'past medical history/physical examination(:|-)',
                    'past_medical_history_and_physical_examination(:|-)',
                    //Custom @ Barb
                    'Historical\\s*data(:|-)?'
                    ,'\\bPMHx includes'
                    , 'PMH/PSH'
                    ,'pmhx\\/pshx(:|-)'
            ]
            concept_feature_value = "PMH"

        }
        /*
        //Specific to Pneumonia Project
        Excluded CXR Option
        "cxr" {
            expressions = [
                  //  'cxr(:|-)',
                    'x-?rays?'
                    //Please correlate for
                    //visualizing
            ]
            concept_feature_value = "cxr"

        }
    */
        "problem_list" {
            expressions = [
                    'active problem list(:|-)',
                    'chronic\\s+stable\\s+problems(:|-)',
                    'PROBLEMS ?\\- ACTIVE(:|-)',
                    'problem\\s*list'
                    , 'Current Problems:'
                    , 'Active provlems?'
                    , 'Active Problem\\(s\\):\n'
                    ,'\\R{1,2}Risk Factors:'

            ]
            concept_feature_value = "ProblemList"

        }

        "sexual and social history" {
            expressions = [
                    '\\R{1,2}SH:',
                    'social history:',
                    'PSHx:',
                    'soc\\s*hx(:|-)',
                    'mh/pshx(:|-)',
                    'sexual history',
                    'pmhs(:|-)',
                    'sexual history:',
                    'LGBTQ SCREENING NWI(:|-)'
                    ,'\\R{1,2} *Sx:'

            ]
            concept_feature_value = "Social_History"

        }

        "HIV screening" {
            expressions = [
                    'HIV(:|-)',
                    'HIV Screening(:|-)',
                    'HIV Risk(:|-)'

            ]
            concept_feature_value = "HIV_Screening"

        }
        "Observation_and_plan" {
            expressions = [
                    'ADDITIONAL ASSESSMENT(:|-)',
                    'MEDICAL\\s*DECISION\\s*MAKING/PLAN:',
                    '\\bASS(:|-)',
                    'ASSESMENT(:|-)',
                    '\\R{1,2}( |\\*|-|/|\\\\){0,20}ASSESSMENT',
                    'ASSESS(:|-)',
                    'ASSESSMENT(:|-)',
                    'Assessment\\s*.\\s*Plans?(:|-)?',
                    'As+es+ment and Plan:',
                    'ASSESSMENT and management *:',
                    'CLINICAL IMPRESSION(:|-)',
                    'CLINICAL IMPRESSIONS(:|-)',
                    '\\bimp(:|-)',
                    //'Imp:',
                    //'IMPRESSION AND RECOMMENDATION(:|-)',
                    'IMPRESSION AND RECOMMENDATIONS?(:|-)',
                    'IMPRESSION RECOMMENDATION(:|-)',
                    'IMPRESSION SECTION(:|-)',
                    'IMPRESSION(:|-)',
                    //'IMPRESSION/ASSESSMENT(:|-)',
                    'IMPRESSIONS(:|-)',
                    'IMPRESSIONS?/ASSESSMENT(:|-)',
                    'IMPRESSSION(:|-)',
                    'IMPRESSSIONS(:|-)',
                    'INITAL IMPRESSION(:|-)',
                    'INITIAL ASSESSMENT(:|-)',
                    'INITIAL IMPRESSION(:|-)',
                    'INITIAL IMPRESSION/ASSESSMENT(:|-)',
                    'Recommendations(:|-)',
                    '\\R{1,2} *A/P(:|-)',
                    '\\R{1,2} *A(:|-)',
                    '\\R{1,2} *P(:|-) *\\R',
                    '\\R{1,2} *ASSESSMENT AND PLAN(:|-)',
                    '\\R{1,2} *Impression(:|-)',
                    'assessment and plan(:|-)',
                    'assessment and recommendation(:|-)',
                    'assessment and recommendation(:|-)',
                    'assessment and recommendations(:|-)',
                    'assessment plan(:|-)',
                    'assessment recommendation(:|-)',
                    'assessment(:|-)',
                    'assessment/plan(:|-)',
                    'assessment(:|-)',
                    'assessment_and_plan(:|-)',
                    'clinical comment(:|-)',
                    'clinical comments(:|-)',
                    'clinical impression(:|-)',
                    'diagnoses(:|-)',
                    'diagnosis(:|-)',
                    'diagnosis(:|-)',
                    'impression and plan(:|-)',
                    'impression and plans(:|-)',
                    'impression and recommendation(:|-)',
                    'impression and recommendations(:|-)',
                    'impression plan(:|-)',
                    'impression recommendation(:|-)',
                    'impression(:|-|\\*)',
                    'impresion(:|-)',
                    'impression/plan(:|-)',
                    'impression/recommendations(:|-)',
                    'initial impression(:|-)',
                    'interpretation(:|-)',
                    'objective(:|-)',
                    'plan and discussion(:|-)',
                    'plan discussion(:|-)',
                    'medical decision making(:|-)',
                    //Addednum
                    '\\bplan(:|-)'
                    ,'\\R{1,2} *plan *\\R{1,2}'
                    ,'Diagnostic Impression:'
                    ,'Disposition:'
                    ,'I-INTERVENTION/PLAN'
                    ,'Interdisciplinary Care Plan'
                    ,'NEURO/MENTAL STATUS'
                    ,'Physical Assessment'
            ]
            concept_feature_value = "Observation_and_Plan"

        }


        "medication" {
            expressions = [
                    '\\R{1,2} *meds(:|-)',
                    '\\R{1,2} *medications(:|-)',
                    'active and recently expired outpatient medications \\(including supplies\\):',
                    'active inpatient medications \\(excluding supplies\\):',
                    'active non-va medications',
                    'active outpatient medications(:|-)',
                    'active outpatient medications \\(excluding supplies\\)' ,
                    'active/pending/expired medications \\(w/o supplies\\)',
                    'active/pending/expired medications',
                    'active\\s*(out|in)patient\\s*medications(:|-)',
                    'active\\s*inpatient\\s*and\\s*outpatient\\s*medications(:|-)',
                    'active\\s*inpatient\\s*medications\\s*\\(including\\s*supplies\\)(:|-)',
                    'active\\s*inpatient\\s*medications\\s*drug\\s*dosage(:|-)',
                    'active\\s*inpatient\\s*medications\\s*status(:|-)',
                    'active\\s*medications(:|-)',
                    'active\\s*medications?(:|-)',
                    'active\\s*medications?\\s*prior\\s*to\\s*admission(:|-)',
                    'active\\s*medications\\s*\\(including\\s*supplies\\)(:|-)',
                    'active\\s*medications\\s*combined(:|-)',
                    'active\\s*medications\\s*from\\s*remote\\s*data(:|-)',
                    'active\\s*medications\\s*include(:|-)',
                    'active\\s*medications\\s*list(:|-)',
                    'active\\s*medications\\s*prescribed\\s*at\\s*(\\w+ +\\w+)\\s*vamc(:|-)',
                    'active\\s*medications\\s*prescribed\\s*at\\s*\\w+\\s*vamc(:|-)',
                    'active\\s*non\\s*va\\s*medications(:|-)',
                    'active\\s*nonva\\s*medications(:|-)',
                    'active\\s*non-va\\s*medications(:|-)',
                    'active\\s*opt\\s*medications(:|-)',
                    'active\\s*outpatient\\s*medications\\s*\\(including\\s*supplies\\)(:|-)',
                    'active\\s*outpatient\\s*prescriptions(:|-)',
                    'active\\s*va\\s*medications(:|-)',
                    'admission\\s*medications(:|-)',
                    'all\\s*active\\s*medications(:|-)',
                    'correct\\s*medications\\s*include(:|-)',
                    'current\\s*inpatient\\s*medications(:|-)',
                    'current\\s*inpatient\\s*medications\\s*include(:|-)',
                    'current\\s*medications(:|-)',
                    'current\\s*medications/reconciliation(:|-)',
                    'current\\s*medications\\s*list(:|-)',
                    'discharge\\s*medications(:|-)',
                    'drugs(:|-)',
                    'health\\s*supplements(:|-)',
                    'history/medications(:|-)',
                    'history\\s*of\\s*medication\\s*treatments(:|-)',
                    'history\\s*of\\s*medication\\s*use(:|-)',
                    'home\\s*medications(:|-)',
                    'inactive\\s*outpatient\\s*medications(:|-)',
                    'inhospital\\s*medications(:|-)',
                    'inpatient\\s*medication\\s*reconciliation(:|-)',
                    'inpatient\\s*medications(:|-)',
                    'inpatient\\s*medications?(:|-)',
                    'inpatient\\s*medications?\\s*=(:|-)',
                    'inpt\\s*medications(:|-)',
                    'm\\s*e\\s*d\\s*i\\s*c\\s*a\\s*t\\s*i\\s*o\\s*n\\s*s(:|-)',
                    'med\\s*recon(:|-)',
                    'med\\s*reconciliation(:|-)',
                    'med\\s*reconciliation\\s*outpt(:|-)',
                    'medication co-?management:',
                    'medication(s)\\s*review(:|-)',
                    'medication\\s*admission(:|-)',
                    'medication\\s*at\\s*admission(:|-)',
                    'medication\\s*at\\s*discharge(:|-)',
                    'medication\\s*during\\s*admission(:|-)',
                    'medication\\s*history(:|-)',
                    'medication\\s*management\\s*at\\s*discharge(:|-)',
                    'medication\\s*on\\s*admission(:|-)',
                    'medication\\s*prior\\s*admission(:|-)',
                    'medication\\s*prior\\s*to\\s*admission(:|-)',
                    'medication\\s*reconciliation(:|-)',
                    'medication\\s*reconciliation\\s*review(:|-)',
                    'medication\\s*reconciliation\\s*summary(:|-)',
                    'medication\\s*reconcilliation(:|-)',
                    'medication\\s*review\\s*for\\s*medication\\s*reconciliation(:|-)',
                    'medications(:|-)',
                    'medications\\s*at\\s*admission(:|-)',
                    'medications\\s*at\\s*discharge(:|-)',
                    'medications\\s*during\\s*admission(:|-)',
                    'medications\\s*given\\s*today(:|-)',
                    'medications\\s*on\\s*admission(:|-)',
                    'medications\\s*on\\s*discharge(:|-)',
                    'medications\\s*prior\\s*to\\s*admission(:|-)',
                    'medicines\\s*at\\s*pharmacy(:|-)',
                    'misuse\\s*of\\s*medications(:|-)',
                    'new pacu medications include:',
                    'non\\s*va(:|-)',
                    'non\\s*va\\s*medications(:|-)',
                    'non\\s*va\\s*prescribed(:|-)',
                    'non\\s*va\\s*prescription\\s*medications(:|-)',
                    'non\\s*va\\s*prescriptions(:|-)',
                    'non\\s*va\\s*supplied\\s*medications(:|-)',
                    'nonva\\s*medications(:|-)',
                    'non-va\\s*medications(:|-)',
                    'Non-VA Meds Last Documented On:',
                    'nonva\\s*medications\\s*list(:|-)',
                    'non-va\\s*prescribed(:|-)',
                    'non-va\\s*prescription\\s*medications(:|-)',
                    'non-va\\s*prescriptions(:|-)',
                    'non-va\\s*supplied\\s*medications(:|-)',
                    'NOT INCLUDED IN THIS LIST:',
                    'outpatient\\s*medication\\s*review(:|-)',
                    'outpatient\\s*medications(:|-)',
                    'outpatient\\s*medications\\s*status(:|-)',
                    'outpatient\\s*meds\\s*drug\\s*list(:|-)',
                    'outpt.\\s*medication\\s*reconciliation(:|-)',
                    'outpt\\s*medications(:|-)',
                    'pending outpatient medications',
                    'pending\\s*inpatient\\s*medications(:|-)',
                    'preadmission\\s*medication(:|-)',
                    'pre-admission\\s*medications(:|-)',
                    'present\\s*medications(:|-)',
                    'pre-visit\\s*med\\s*reconciliation(:|-)',
                    'provider\\s*med\\s*reconciliation(:|-)',
                    'pta\\s*meds(:|-)',
                    'reconciled\\s*medication\\s*list(:|-)',
                    'reconciliation(:|-)',
                    'reconciliation\\s*of\\s*medications\\s*completed(:|-)',
                    'significant\\s*medications(:|-)',
                    'status\\s*active(:|-)',
                    'substance\\s*use/misuse\\s*of\\s*medications(:|-)',
                    'summary\\s*of\\s*medications',
                    'va\\s*medications(:|-)'
            ]
            concept_feature_value = "Medications"

        }
    /*
        "Plan_Alone" {
            expressions = [
                    '\\bplan:'
            ]
            concept_feature_value = "Plan"

        }
    */

        "allergy" {
            expressions = [
                    'A L L E R G I E S ?(:|-)',
                    'ADDITIONAL ADRS AND/OR ALLERGIES(:|-)',
                    'ADR(:|-)',
                    'ADVERSE DRUG REACTIONS(:|-)',
                    'ADVERSE EVENTS(:|-)',
                    'ADVERSE REACTION(:|-)',
                    'ADVERSE REACTIONS(:|-)',
                    'ALLERGIC DISORDER HISTORY(:|-)',
                    'ALLERGIC REACTIONS(:|-)',
                    'ALLERGIC(:|-)',
                    'ALLERGIES AND ADVERSE REACTIONS(:|-)',
                    'ALLERGIES AND SENSITIVITIES(:|-)',
                    'ALLERGIES FAMILY HISTORY(:|-)',
                    'ALLERGIES REVIEWED(:|-)',
                    'ALLERGIES TO MEDICATIONS(:|-)',
                    'ALLERGIES/ADVERSE REACTIONS(:|-)',
                    'ALLERGIES/REACTIONS(:|-)',
                    'ALLERGIES(:|-)',
                    'ALLERGY ADVERSE REACTION(:|-)',
                    'ALLERGY ENVIRONMENTAL ALLERGEN(:|-)',
                    'ALLERGY FAMILY HISTORY(:|-)',
                    'ALLERGY INFORMATION(:|-)',
                    'ALLERGY REVIEW(:|-)',
                    'ALLERGY SCREENING(:|-)',
                    'ALLERGY SYMPTOM(:|-)',
                    'ALLERGY SYMPTOMS(:|-)',
                    'ALLERGY TO ENVIRONMENTAL ALLERGEN(:|-)',
                    'ALLERGY TO ENVIRONMENTAL ALLERGENS(:|-)',
                    'ALLERGY/ADVERSE DRUG REACTION HISTORY(:|-)',
                    'ALLERGY/ADVERSE DRUG REACTION INFORMATION(:|-)',
                    'ALLERGY/ADVERSE DRUG REACTION(:|-)',
                    'ALLERGY(:|-)',
                    'CONCOMITANT MEDICATIONS(:|-)',
                    'CURRENT ALLERGIES(:|-)',
                    'DRUG ALLERGIC REACTIONS(:|-)',
                    'DRUG ALLERGIC REACTIONS(:|-)',
                    'DRUG ALLERGIES(:|-)',
                    'DRUG SENSITIVITIES(:|-)',
                    'FOOD & DRUG REACTIONS INCLUDING ALLERGIES AS ENTERED IN CPRS(:|-)',
                    'FOOD ALLERGIES(:|-)',
                    'HISTORY ALLERGY(:|-)',
                    'HISTORY OF ALLERGIES(:|-)',
                    'KNOWN ALLERGIES(:|-)',
                    'LATEX ALLERGY(:|-)',
                    'MEDICATIONS ALLERGIES(:|-)',
                    'NEW ALLERGIES(:|-)',
                    'NEWLY IDENTIFIED ALLERGIES(:|-)',
                    'OTHER ALLERGIES(:|-)',
                    'PREVIOUSLY DOCUMENTED ALLERGIES(:|-)',
                    'SEASONAL ALLERGIES(:|-)',
                    'SEASONAL ALLERGY(:|-)',
                    'SENSITIVITIES(:|-)',
                    '\\r\\n *\\d+ *\\).? *allergies(:|-)',
                    '\\r\\n *all(:|-)',
                    '\\r\\n *allergies(:|-)',
                    '\\r\\n *allergy(:|-)',
                    'allergies/adr(:|-)',
                    'allergies(:|-)',
                    'allergy'
            ]
            concept_feature_value = "Allergies"

        }
        "Chief complaint" {
            expressions = [
                    'CHIEF COMPLAINT(:|-)'
                    , 'Subjective/Chief Complaint:'
                    , 'Current Concern for Observation Patient:'
                    , '(\\R{1,2}|^) *Reason for contact'
                    , '\\R{1,2} *Symptoms:'
                    , '\\R{1,2} *Complaints:'
                    , 'Symptoms presented:'
                    , '(\\R{1,2}|^) *cc:'
                    , '(\\R{1,2}|^) *s:'
                    , '(\\R{1,2}|^) *Reason for call'
                    ,'ALL PRINCIPAL ADMIT DIAGNOSES:'

            ]
            concept_feature_value = "CC"

        }

        "Physical Exam" {
            expressions = [
                    '\\R{1,2} *Physical Exam\\w*?(:|-)',
                    '\\R{1,2} *Review of systems(:|-)',
                    'PHYSICAL EXAMINATION',
                    '\\R{1,2} *PE(:|-)',
                    '\\R{1,2} *exam(:|-)'
                    , 'Nutrition Focused Physical Exam'
            ]
            concept_feature_value = "Exam"

        }


        "ED_Course" {
            expressions = [
                    '\\R{1,2} *ED\\s*COURSE(:|-)',
                    '\\R{1,2} *Er\\s*COURSE(:|-)',
                    'Emergency\\s*Department\\s*Course'

            ]
            concept_feature_value = "ED_Course"

        }

        "labs_and_studies" {
            expressions = [
                    '\\R{1,2} *findings *(:|-)',
                    '\\R{1,2} *collection',
                    '\\R{1,2} *LABORATORY DATA(:|-)',
                    '\\R{1,2} *operation and findings *(:|-)',
                    '\\R{1,2} *operative findings *(:|-)',
                    '\\R{1,2} *pathologic staging *(:|-)',
                    '\\R{1,2} *pathology report *(:|-)',
                    '\\R{1,2} *performing lab\\b *(:|-)',
                    '\\R{1,2} *performing laboratory *(| *\r(\n)?)(:|-)',
                    '\\R{1,2} *reporting lab *(:|-)',
                    '\\bo:',
                    'objective(:|-)',
                    '\\bs/o(:|-)',
                    'indication(:|-)',
                    'clinical indication(:|-)',
                    'indication(:|-)',
                    'indications(:|-)',
                    '\\R{1,2} *micro *(:|-)',
                    '\\R{1,2} *micro exam *',
                    'labs(:|-)'
                    ,'\\R+ *labs? *\\R+'
                    ,'SEE BELOW FOR FULL LIST OF RECENT LABS'
                    ,'LABS  AND RADIOLOGICAL STUDIES DONE IN ER:'


            ]
            concept_feature_value = "Labs_and_Studies"

        }



        "Present Illness" {
            expressions = [
                    'hpi/interval history(:|-)',
                    'hpi interval history(:|-)',
                    'patient hpi(:|-)',
                    'present illness(:|-)',
                    'history_present_illness(:|-)',
                    'history of the present illness(:|-)',
                    'history of present illness(:|-)',
                    'history present illness(:|-)',
                    'summary of present illness(:|-)',
                    'summary present illness'
                    , '\\bhpi:'
                    , '\\R{1,2} *hpi * \\R{1,2}'
                    , '\\R{1,2} *Other RISK FACTORS'
            ]
            concept_feature_value = "HPI"

        }
        "education" {
            expressions = [
                    'education:'
                    , 'DISCHARGE MEDICATIONS AND INSTRUCTIONS:'
                    ,'DISCHARGE DIETARY INSTRUCTIONS:'
                    , 'COORDINATION OF CARE:'
                    ,'\\R+Information:'
                    ,'Educated patient regarding the following \\(select all that apply\\):'
                    , 'Recommendations'
                    ,'Recommendations/Interventions:'
            ]
            concept_feature_value = "Education"

        }

    /*

    /**Warning removed for being too greedy/they were orginially used
    as an extra exclusion and "Other stuff" category for Betablockers project,
    as such, could be used as suggested exclusions, but are overly specific to that project

    */
        /**/
        "warnings" {
            expressions = [
                    'mrn\\s*adl\\/hygiene\\s*view(:|-)',
                    'negative responses(:|-)',
                    'the most common effects of(:|-)',
                    'to monitor(:|-)',
                    '"\\s+Adverse Effects\\s*o\\s+COMMON(:|-)',
                    '^common(:|-)',
                    '^serious(:|-)',
                    'a\\s+variety\\s+of\\s+hematologic\\s+toxicities\\s+have been reported(:|-)',
                    'adverse effects include(:|-)',
                    'adverse effects such as(:|-)',
                    'allergic reactions,? including(:|-)',
                    'bibliography(:|-)',
                    'caution with colchicine(:|-)',
                    'discussed\\s+medication\\s+side\\-effects(:|-)',
                    'discussed\\s*(se|ade|risks?)(:|-)',
                    'discussed\\s*side\\s*effects(:|-)',
                    'drug.drug\\s*interactions(:|-)',
                    'drug\\s*interactions(:|-)',
                    'drugs that might cause(:|-)',
                    'education(:|-)',
                    'Education provided on(:|-)',
                    'Educated on(:|-)',
                    'expected toxicity?(:|-)',
                    'general information(:|-)',
                    'hematologic\\s*adverse reactions(:|-)',
                    'informed consent(:|-)',
                    'known\\s*side\\s*effects\\s*and\\s*toxicities(:|-)',
                    'micromedex?(:|-)',
                    'omeprazole "?warning"?(:|-)',
                    'patient\\s*education(:|-)',
                    'Per clinical pharmacology(:|-)',
                    'per micromedix?(:|-)',
                    'pinworm\\s*infections(:|-)',
                    'please monitor for signs\\s*(.|and)\\s*Symptom_Terms(:|-)',
                    'reactions\\s*include(:|-)',
                    'reactions\\s*includeomeprazole "?warning"?(:|-)',
                    'reactions\\s*reported(:|-)',
                    'responses\\s*\\+\\s*HPI(:|-)',
                    'responses\\s*\\-\\s*HPI(:|-)',
                    'reviewed adverse reaction(:|-)',
                    'serious reactions(:|-)',
                    'serious\\s*adverse\\s*effects?(:|-)',
                    'serious\\s*adverse\\s*effects\\s*include(:|-)',
                    'severe\\s*skin\\s*reactions,?\\s*including(:|-)',
                    'side\\s*effects\\s*including(:|-)',
                    'Symptom_Terms\\s*of\\s*\\w+\\s*include(:|-)',
                    'these include(:|-)',
                    'typically(:|-)',
                    'uptodate(:|-)',
                    'warnings\\s*for(:|-)',
                    'warnings\\s*reviewed(:|-)',
                    'watch\\s*out\\s*for(:|-)',
                    'watching\\s*for(:|-)',
                    'were discussed(:|-)',
                    'WHAT YOU SHOULD KNOW(:|-)',
                    'will\\s*discuss(:|-)',
                    'risks of'
            ]
            concept_feature_value = "warnings"

        }
    /**/


        "other" {
            expressions = [
                    '\\R{1,2} *A signed copy of this report(:|-)',
                    '\\R{1,2} *modified report *(:|-)',
                    '\\R{1,2} *note *(:|-)',
                    '\\R{1,2} *postoperative diagnosis *(| *\r(\n)?)(:|-)',
                    '\\R{1,2} *preoperative diagnosis *(| *\r(\n)?)(:|-)',
                    '\\R{1,2} *procedure *(:|-)',
                    '\\R{1,2} *rectal mass *(:|-)',
                    '\\R{1,2} *regional lymph nodes *(:|-)',
                    // '\\R{1,2} *result *(:|-)',
                    '\\R{1,2} *smw *(:|-)',
                    '\\R{1,2} *\\bsp\\b *(:|-)',
                    '\\R{1,2} *submitted *(:|-)',
                    '\\R{1,2} *summary of section *(:|-)',
                    '\\R{1,2} *supplementary report *(:|-)',
                    '\\R{1,2} *supplementary report\\(s\\) *(:|-)',
                    '\\R{1,2} *synoptic report for colon rectum *(:|-)',
                    '\\R{1,2} *test performed at *(:|-)',
                    '\\R{1,2} *tumor synopsis *(:|-)',
                    '\\R{1,2} *Home isolation:',
                    '/es/(:|-)',
                    'medications\\s*held\\s*or\\s*discontinued\\s*upon\\s*admission(:|-)',
                    'changes/additions(:|-)',
                    'possible risks or complications include'
                    , 'Suicide Screen:'
                    ,'MED RECONCILIATION'
                    , 'SUGGESTED PLAN FOR FOLLOWUP \\(SCHEDULED APPOINTMENTS\\):'
                    ,' Malnutrition Screening:'
                    ,'INTERVENTIONS:'
                   // ,'\\R{1,2} *\\w+ *: *\\R'
                   // ,'\\R{1,2} *\\w+ +\\w+ *: *\\R'
            ]
            concept_feature_value = "other"

        }

    }







