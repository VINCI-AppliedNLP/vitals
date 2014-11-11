
name = "NumbersAnnotation"
configuration {
	/* All configuration for this annotator. */
	defaults {
		/* Global for all configurations below if a property specified here is not overridden in a section below. */
		outputType = "gov.va.vinci.vitals.types.Numeric"
		concept_feature_name = "comment"
		matchedPatternFeatureName = "pattern"
		groupFeatureName="group"
		case_sensitive = false }


	/* An arbitrary name for this set of patterns/config. */
	"Whole_number" {
		expressions = ['(?<!\\.)\\b\\d{1,3}\\b(?!\\.\\d)']
		concept_feature_value = "pressure, rates"
		outputType = "gov.va.vinci.vitals.types.IntegerNumber" }

	"ZeroDigit_number" {
		expressions = ['\\b\\d{2,3}\\.0\\b']
		concept_feature_value = "Pressure, rates"
		outputType = "gov.va.vinci.vitals.types.ZeroDecimalNumber" }

	"Decimal_number"{
		expressions = ['\\b\\d{2,3}\\.\\d\\b']
		concept_feature_value = "temperature, weight"
		outputType = "gov.va.vinci.vitals.types.DoubleNumber"} }