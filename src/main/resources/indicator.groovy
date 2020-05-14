
name = "IndicatorRegexAnnotator"
configuration {
	global_settings {
		performance_monitoring = false
	}

	/* All configuration for this annotator. */
	defaults {
		/* Global for all configurations below if a property specified here is not overridden in a section below. */
		outputType = "gov.va.vinci.vitals.types.Indicator"
		case_sensitive = false
	}


	/* An arbitrary name for this set of patterns/config. */
	"Indicators" {
		expressions = [
				"\\bvitals",
				"\\bvs\\b",
				"\\bv/s\\b",
				"\\bv\\. *s\\.",
				"\\bv\\. *s\\b",
				"\\R *\\bo\\b",
				"\\R *\\bpe\\b",
				"\\bphysical exam",
				"\\bphysical examination",
				"\\bobjective",
				"\\R *\\bv/s\\b",
				"\\bexam\\b",
				"\\bvital\\b(?! capacity)",
				"\\bmost recent vitals",
				"\\bpost-blood pressure and pulse",
				"\\bpre-blood pressure and pulse",
				"\\bvss\\b",
				"\\bexamination",
				"\\bassessment",
				"\\bpost-blood pressure and pulse seated",
				"\\bpost-blood pressure and pulse standing",
				"\\bpost-temp\\b",
				"\\bpre-temp\\b",
				"\\brecent vitals",
				"\\badmission vitals",
				"\\bbaseline vitals",
				"\\bblood pressure check",
				"\\bblood pressure rechecked",
				"\\bblood pressure taken at this visit",
				"\\bbp check",
				"\\bcurrent vitals",
				"\\blatest vitals",
				"\\bnormal physical exam findings include",
				"\\R *\\bo:",
				"\\bpast 7 day ht-ivr data",
				"\\bpatient's vital signs are",
				"\\bpre-blood pressure and pulse seated",
				"\\bpre-blood pressure and pulse standing",
				"\\bprevious vitals",
				"\\R *\\bpx\\b",
				"\\brechecked bp",
				"\\brechecked vitals",
				"\\bvitals? signs?",
				"\\bvital signs entry",
				"\\bvital/signs",
				"\\bvitals are as per nursing",
				"\\R *CV:"
		]
	}

}
