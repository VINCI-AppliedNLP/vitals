
name = "TermExcludePatternAnnotator"
configuration {
	 global_settings {
		performance_monitoring = false
	}

	/* All configuration for this annotator. */
	defaults {
		/* Global for all configurations below if a property specified here is not overridden in a section below. */
		outputType = "gov.va.vinci.vitals.types.TermExclude"
		matchedPatternFeatureName = "pattern"
		case_sensitive = false }


	/* An arbitrary name for this set of patterns/config. */
	"excludeSectionHeader" {
		expressions = [
				"labs",
				"chemistry",
				"laboratory",
				"HEMODYNAMICS",
				"ANGIOGRAPHY",
				"goal",
				"panel",
				"Glucose",
				"Electrolytes",
				"\\bi/o\\b",
				"Orthostats",
				"\\bABG"
				, "Clinical Reminder Activity"
				,"\\R+ *IMPORTANT:"
				, "ALLERGIES:"
				,"NUTRITIONAL STATUS:"
				,"Non-VA Meds Last Documented On:"
				,"NOT INCLUDED IN THIS LIST:"
				,"if needed"
				,"by mouth"
				,"medications?"

			]
	}

}
