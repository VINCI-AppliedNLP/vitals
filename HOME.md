
Pipeline

- createNumericPipeline

	NumericAnnotator -- RegexAnnotator - GROOVY_CONFIG_FILE => IntegerNumber, DoubleNumber
	AnnotationFilter -- TYPE_NUMERIC with REMOVE_CHILDREN=true
	TimestampAnnotator -- RegexAnnotator => TYPE_TIMESTAMP
	ExcludeNumberPattern -- AnnotationPatternAnnotator => TYPE_NUMEXCLUDE
	AnalyzeNumbersAE -- removeOverlappingAnnotations(NumericExclude, Numeric), number.setValue

createTermAndIndicatorPipeline
createWindowsPipeline
createPatternsPipeline
createVitalRulesPipeline
createML_Pipeline