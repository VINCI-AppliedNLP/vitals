// Knowtator variables are shared because the service has to create annotation types
// Knowtator Variables

backupknowtatorToUimaTypeMap = [

	// knowtatorAnnotation : UIMA annotation ,
	"blood_pressure_term":"gov.va.vinci.kttr.types.RefTerm",
	"blood_pressure_value":"gov.va.vinci.kttr.types.RefValue",
	"Indicator":"gov.va.vinci.kttr.types.Indicator",
	"pulse_term":"gov.va.vinci.kttr.types.RefTerm",
	"pulse_value":"gov.va.vinci.kttr.types.RefValue",
	"temperature_term":"gov.va.vinci.kttr.types.RefTerm",
	"temperature_value":"gov.va.vinci.kttr.types.RefValue"
]

//Original
knowtatorCorpusPath="P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\FinalVal_20141210\\corpus\\"
knowtatorXmlPath="P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\FinalVal_20141210\\saved\\"

//knowtatorCorpusPath="P:\\ORD_Iwashyna_201108021D\\NLP\\testCases\\ryan\\"
//knowtatorXmlPath="P:\\ORD_Iwashyna_201108021D\\NLP\\testCases\\ryan\\saved\\"


//\DevSet_20141204
//FinalVal_20141210
//"P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\RefSt_20140923_Batch1_10_updated\\saved\\"
//"P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\RefSt_20140923_Batch1_10\\saved\\"
//"P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\RefSt_20140522\\saved\\"
//"P:\\ORD_Iwashyna_201108021D\\NLP\\kttrTest\\saved\\";
//"P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\ProblematicRefSt\\saved\\"

outputXmi = true

knowtatorToUimaFeatureMap = [
	// knowtatorAnnotation : [ knowtatorAttribute : UIMA feature (all features are assumed to be string) ],
]

knowtatorToUimaTypeMap = [
	// knowtatorAnnotation : UIMA annotation ,
	"blood_pressure_term":"gov.va.vinci.kttr.types.BPTerm",
	"blood_pressure_value":"gov.va.vinci.kttr.types.BPValue",
	"BP_systolic":"gov.va.vinci.kttr.types.BPSysValue",
	"BP_diastolic":"gov.va.vinci.kttr.types.BPDiasValue",
	"Indicator":"gov.va.vinci.kttr.types.Indicator",
	"pulse_term":"gov.va.vinci.kttr.types.HRTerm",
	"pulse_value":"gov.va.vinci.kttr.types.HRValue",
	"temperature_term":"gov.va.vinci.kttr.types.TTerm",
	"temperature_value":"gov.va.vinci.kttr.types.TValue",
	"Document_reviewed":"gov.va.vinci.kttr.types.Other",
	"bmi_value":"gov.va.vinci.kttr.types.BMIValue",
	"height_value":"gov.va.vinci.kttr.types.HeightValue",
	"weight_value":"gov.va.vinci.kttr.types.WeightValue",
	"oxygen_value":"gov.va.vinci.kttr.types.OxygenValue",
	"pain_value":"gov.va.vinci.kttr.types.PainValue",
	"respiration_value":"gov.va.vinci.kttr.types.RespValue",
	"time_value":"gov.va.vinci.kttr.types.TimeValue",
	"pain_term":"gov.va.vinci.kttr.types.Other",
	"bmi_term":"gov.va.vinci.kttr.types.Other",
	"height_term":"gov.va.vinci.kttr.types.Other",
	"weight_term":"gov.va.vinci.kttr.types.Other",
	"oxygen_term":"gov.va.vinci.kttr.types.Other",
	"respiration_term":"gov.va.vinci.kttr.types.Other",
	"time_term":"gov.va.vinci.kttr.types.Other"

]






