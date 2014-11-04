
name = "ConceptsAndTermsAnnotation"
configuration {
	/* All configuration for this annotator. */
	defaults {
		/* Global for all configrations below if a property specified here is not overridden in a section below. */
		outputType = "gov.va.vinci.vitals.types.Term"
		concept_feature_name = "concept"
		case_sensitive = false }


	/* An arbitrary name for this set of patterns/config. */
	"Temperature" {
		expressions = [
			'\\btemp\\w*\\b',
			'fever\\b',
			'\\bt\\b',
			'\\bt(?=\\d)',
			'\\btm\\b',
			'\\btmax\\b',
			'\\btcur\\b',
			'\\bt *current\\b',
			'\\bTemp +F *\\(C\\)'
		]
		concept_feature_value = "Temperature" }
	"Blood_Pressure" {
		expressions = [
			'diastolic',
			'\\babp\\b',
			'\\bsys *dias',
			'bp *systolic',
			'systolic',
			'systolic *bp',
			'sytolic',
			'\\bb(/|-|\\.)?p(\\.)?\\b',
			'blood\\s*pressures?\\b',
			'bp *lying',
			'bp *range',
			'bp *recheck',
			'bp *standing',
			'bp *today',
			'lying *bp',
			'manual *pressure',
			'\\bnbp\\b',
			'repeat *bp\\b',
			'rest *bp\\b',
			'sbp *dbp\\b',
			'sitting *blood *presure',
			'sitting *bp',
			'sitting p\\b',
			'standing *blood *presures?\\b',
			'standing(\\s*after\\s*\\d+\\sminutes?)?',
			// standing after 3 minutes
			'lying\\b',
			'BP\\s+LEFT\\s+ARM\\s+SITTING'
		]
		concept_feature_value = "Blood_Pressure" }

	"BMI" {
		expressions = ['bmi', 'body\\s+mass\\s+index']
		concept_feature_value = "BMI" }

	"Heart_Rate" {
		expressions = [
			'\\bpulse\\b(\\srate)?',
			'(max|mean|min)\\s+pulse',
			'\\bhr\\b',
			'\\bheart\\s*rate\\b',
			'\\bp[uls]+e\\b',
			'\\bp\\b',
			'\\bpl\\b',
			'\\bpr\\b',
			'apical pulse',
			'heart *rate',
			'hr before tx',
			'pulse pre\\b',
			'pulse rate',
			'pulses\\b',
			'radial pulse',
			'\\bp(?=\\d{2,3}\\b)',
			'pulse\\s+(dropped|raised|went\\s+up)\\s+to',
			'Ventricular\\s+Rate'
		]
		concept_feature_value = "Heart_Rate" }

	"Height" {
		expressions = ['\\bht\\b', '\\bHt +in *(\\()?cm(\\))?', '\\bheight\\b']
		concept_feature_value = "Height" }

	"Pain" {
		expressions = ['Pain', 'pain score']
		concept_feature_value = "Pain" }

	"Respiratory" {
		expressions = [
			'\\brate\\b',
			'\\bresp\\b',
			'\\brespir\\b',
			'\\brr\\b',
			'\\br\\b',
			'respiration\\s*rate',
			'respirations',
			'respiration',
			'respiratory\\s*rate',
			'\\br(?=\\d{2,3}\\b)'
		]
		concept_feature_value = "Respiratory" }

	"SO2" {
		expressions = [
			'SO2',
			'\\bsat\\b',
			'\\bpo2',
			'SAO2',
			'saturation',
			'saturation\\s*O2',
			'saturation\\s*o2\\s*stats',
			'O2 SATS',
			'SpO2'
		]
		concept_feature_value = "SO2" }

	"Weight" {
		expressions = ['\\bWeight', '\\bwt\\b', '\\bw\\b', '\\bw(?<=\\d{2,3}\\b)']
		concept_feature_value = "Weight" }

	"Age" {
		expressions = ["\\bage\\b", "\\bdob\\b"]
		concept_feature_value = "Age" } }