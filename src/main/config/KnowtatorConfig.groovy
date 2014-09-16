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

//knowtatorCorpusPath="P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\ProblematicRefSt\\corpus\\"
knowtatorCorpusPath="P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\RefSt_20140522\\corpus\\"
//knowtatorCorpusPath="P:\\ORD_Iwashyna_201108021D\\NLP\\kttrTest\\corpus\\";
knowtatorXmlPath="P:\\ORD_Iwashyna_201108021D\\Annotations\\AnnotationAdmin\\RefSt_20140522\\saved\\"
//knowtatorXmlPath="P:\\ORD_Iwashyna_201108021D\\NLP\\kttrTest\\saved\\";
outputXmi = true

knowtatorToUimaFeatureMap = [
// knowtatorAnnotation : [ knowtatorAttribute : UIMA feature (all features are assumed to be string) ],

]

knowtatorToUimaTypeMap = [
	
	// knowtatorAnnotation : UIMA annotation ,
	"blood_pressure_term":"gov.va.vinci.kttr.types.BPTerm",
	"blood_pressure_value":"gov.va.vinci.kttr.types.BPValue",
	"Indicator":"gov.va.vinci.kttr.types.Indicator",
	"pulse_term":"gov.va.vinci.kttr.types.HRTerm",
	"pulse_value":"gov.va.vinci.kttr.types.HRValue",
	"temperature_term":"gov.va.vinci.kttr.types.TTerm",
	"temperature_value":"gov.va.vinci.kttr.types.TValue",
	"Document_reviewed":"gov.va.vinci.kttr.types.Other"
]


















