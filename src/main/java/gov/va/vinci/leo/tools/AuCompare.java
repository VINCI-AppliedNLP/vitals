package gov.va.vinci.leo.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.model.NameValue;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * Creates a mapping from a JSON formatted string defining a gold (Au) standard to tool generated output.  Tracks
 * aggregate stats for each mapped comparison.
 *
 * User: Thomas Ginter
 * Date: 11/19/13
 * Time: 10:36 AM
 */
public class AuCompare {
	/**
	 * List of AuStats objects for each mapped Au to Tool annotation pair.
	 */
	protected ArrayList<AuStats> stats = new ArrayList<AuStats>();

	/**
	 * Document identifier for the most recent CAS processed.
	 */
	protected String documentID = null;

	/**
	 * Logging object of output
	 */
	private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

	/**
	 * Constructor to initialize the map of gold standard to tool generated annotation types.
	 *
	 * @param auAnnotationMap
	 *      JSON formatted string mapping each gold standard annotation type to a tool type.
	 * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap has an invalid JSON format
	 */
	public AuCompare(Map<String, String> auMap) {
		if (auMap == null) {
			throw new IllegalArgumentException("Gold Standard map cannot be blank!");
		}
		for (Map.Entry<String, String> entry : auMap.entrySet()) {
			String[] toolClasses = entry.getValue().split("\\|");
			String auClass = entry.getKey();
			for (String toolClass : toolClasses) {
				stats.add(new AuStats(auClass, toolClass.trim()));
			}
		}
	}

	/**
	 * Perform an annotation comparison on the annotations found in this CAS using the mapping provided during
	 * initialization.  If no known document ID annotation is found then one is assigned in the format of:
	 * AuCompareDocumentID{RandomUUID}
	 *
	 * @param jCas CAS object whose annotations will be used in the comparison
	 * @return list of String arrays representing the detailed comparison results
	 * @throws org.apache.uima.cas.CASException if the CAS cannot be accessed or the types are not found
	 */
	public List<String[]> annotationComparison(JCas jCas) throws CASException {
		if (jCas == null) {
			throw new IllegalArgumentException("JCAS parameter cannot be null!");
		}
		List<String[]> rows = new ArrayList<String[]>();
		NameValue nv = CasTools.getReferenceID(jCas);
		if (nv == null || StringUtils.isBlank(nv.getName())) {
			documentID = "AuCompareDocumentID" + LeoUtils.getUUID();
		} else {
			documentID = nv.getName();
		}
		for (AuStats stat : stats) {
			annotationComparison(stat, jCas, rows);
		}
		return rows;
	}

	/**
	 * Get the list of Au and Tool annotations in this CAS and add the appropriate stats.
	 *
	 * @param stats AuStats object that stores the name of the Au and Tool annotations and their stats
	 * @param jCas  the CAS whose annotations will be used in the comparison
	 * @param rows  list of String arrays representing the annotation comparison detailed results
	 * @throws org.apache.uima.cas.CASException if an error occurs getting the Au or Tool types from the CAS
	 */
	protected void annotationComparison(AuStats stats, JCas jCas, List<String[]> rows) throws CASException {
		if (jCas == null)
			throw new RuntimeException("Missing required JCAS parameter!");
		if (stats == null)
			throw new RuntimeException("Missing required stats parameter!");

		Type auType = jCas.getRequiredType(stats.getAuAnnotation());
		Type toolType = jCas.getRequiredType(stats.getToolAnnotation());
		ArrayList<Annotation> auList = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(jCas,
		    auType);
		ArrayList<Annotation> tList = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(jCas,
		    toolType);
		int toolIndex = 0, toolMatchIndex = 0;
		boolean isMatch;
		String[] statsClassList;
		Annotation t = null;
		for (Annotation au : auList) {
			isMatch = false;
			statsClassList = null;
			while (toolIndex < tList.size()) {
				t = tList.get(toolIndex);
				if (au.getBegin() >= t.getEnd()) { //Au is to the right of the current tool
					if (toolMatchIndex != toolIndex) {
						//There is a set of tool matches so remove them
						for (int i = 0; i < toolMatchIndex; i++) {
							tList.remove(0);
						}
						toolIndex = toolMatchIndex = 0;
					} else {
						//this tool has no match
						statsClassList = stats.addToolOnly();
						addRows(rows, statsClassList, stats, t);
						String i = "";
						for (String r : statsClassList) {
							i = i + ";" + r;
						}
						System.out.println("False Positive: " + documentID + "[ " + i + "] " + tList.get(0).getCoveredText().replaceAll("\\s+", " "));
						tList.remove(0);
					}
				} else if (AnnotationLibrarian.overlaps(t, au)) { //Au overlaps the current tool
					isMatch = true;
					if (AnnotationLibrarian.coversSameSpan(au, t)) { //Exact Match
						statsClassList = stats.addExactMatch();
					} else {
						statsClassList = stats.addOverlapMatch();
					}
					addRows(rows, statsClassList, stats, t, au);
					if (toolIndex == toolMatchIndex) {
						toolMatchIndex++;
					}
					toolIndex++;
				} else if (t.getBegin() >= au.getEnd()) { //Au is to the left of the current tool
					toolIndex = 0;
					break;
				}
			}
			if (!isMatch) {
				statsClassList = stats.addNoMatch();
				addRows(rows, statsClassList, stats, au);

			}
		}
		if (toolIndex > 0 && toolIndex == toolMatchIndex) {
			//There is one last match in the tool list that needs to be removed
			tList.remove(0);
		}
		//If there are any tool annotations left then they have no match, add those results
		statsClassList = stats.addToolOnly(tList.size());
		for (Annotation a : tList) {
			String i = "";
			for (String r : statsClassList) {
				i = i + ";" + r;
			}
			System.out.println("False Positive: " + documentID + "[ " + i + "] " + tList.get(0).getCoveredText().replaceAll("\\s+", " "));
			
			addRows(rows, statsClassList, stats, a);
		}
	}

	/**
	 * Add the annotation information rows for each annotation and stats class listed.  The rows are in the format of:
	 * documentID, begin, end, annotation type, coveredText, statClass.
	 *
	 * @param rows list of String arrays representing the annotation comparison detailed results
	 * @param classList list of statsClasses returned by the AuStats object for this Annotation(s)
	 * @param rowAnnotations one or more Annotations whose action resulted in the stats class list
	 */
	protected void addRows(List<String[]> rows, String[] classList, AuStats auStats,
	    Annotation... rowAnnotations) {
		if (rows == null) {
			throw new IllegalArgumentException("Missing required parameter rows!");
		}
		if (rowAnnotations == null) {
			throw new IllegalArgumentException(
			    "Missing required parameter rowAnnotations, at least one Annotation must be provided!");
		}
		if (classList == null || classList.length == 0) {
			throw new IllegalArgumentException("Missing required parameter classList!");
		}
		if (rows == null) {
			throw new MissingResourceException("Missing required rows list", ArrayList.class.getCanonicalName(),
			    "rows");
		}
		String begin, end, type, coveredText = null, mapping = auStats.getAuAnnotation() + "->"
		    + auStats.getToolAnnotation();
		for (Annotation a : rowAnnotations) {
			begin = "" + a.getBegin();
			end = "" + a.getEnd();
			type = a.getType().getName();
			try {
				coveredText = a.getCoveredText();
			} catch (StringIndexOutOfBoundsException e) {
				coveredText = "";
				log.error("Exception thrown getting the covered text, documentID: " + documentID
				    + ", Annotation.begin: " + a.getBegin()
				    + ", Annotation.end: " + a.getEnd(), e);
			}
			for (String statClass : classList) {
				rows.add(new String[] { documentID, mapping, begin, end, type, coveredText, statClass });
			}
		}
	}

	/**
	 * Write the stat string for each AuStats object out to the console.
	 */
	public void outputStatsToConsole() {
		for (AuStats stat : stats) {
			System.out.println(stat.toString());
		}
	}

	/**
	 * Return a list of the stat String objects for each AuStats object.
	 *
	 * @return list of stat Strings
	 */
	public List<String> outputStatStrings() {
		List<String> output = new ArrayList<String>();
		for (AuStats stat : stats) {
			output.add(stat.toString());
		}
		return output;
	}

	/**
	 * Return a list of AuStats objects used for tracking stats in this comparison.
	 *
	 * @return list of AuStats objects
	 */
	public List<AuStats> getStatList() {
		return stats;
	}
}
