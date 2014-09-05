package gov.va.vinci.vitals.listeners;

import gov.va.vinci.kttr.types.BPValue;
import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.listener.BaseListener;
import gov.va.vinci.leo.tools.LeoUtils;
import gov.va.vinci.leo.types.CSI;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.jcas.tcas.Annotation;


public class CompareListener extends BaseListener {
	public static Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());
	
	protected StatClass knowtatorStats = new StatClass("Knowtator");
	protected StatClass nlpStats = new StatClass("NLP");
	long totalKnowtatorRelationships = 0;
	long totalNlpRelationships = 0;
	public boolean debug = true;
	public static String time = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_");
	public String outputPath = "";
	public File knowtatorContextFile;
	public File nlpContextFile;
	public File tableContextFile;

	public int knowtatorNoAnatomy = 0;
	public int nlpNoAnatomy = 0;
	private Pattern linePattern = Pattern.compile(".+", Pattern.CASE_INSENSITIVE);

	public CompareListener(String outputPath) {
		super();
		this.outputPath = outputPath;
		knowtatorContextFile = new File(outputPath + "FalseNegative_knowtatorFile_" + time + ".txt");
		nlpContextFile = new File(outputPath + "\\FalsePositive_nlpFile_" + time + ".txt");
		tableContextFile = new File(outputPath + "\\ExcludedTable_" + time + ".txt");

	}

	/**
	 * @param aCas    the CAS containing the processed entity and the analysis results
	 * @param aStatus the status of the processing. This object contains a record of any Exception that occurred, as well as timing information.
	 * @see org.apache.uima.aae.client.UimaAsBaseCallbackListener#entityProcessComplete(org.apache.uima.cas.CAS, org.apache.uima.collection.EntityProcessStatus)
	 */
	@Override
	public void entityProcessComplete(CAS aCas, EntityProcessStatus aStatus) {
		CSI csi;
		String docID = "";
		String docText = aCas.getDocumentText();
		try {
			csi = (CSI) aCas.getJCas().getAnnotationIndex(CSI.type).iterator().next();
			docID = csi.getID();
		} catch (CASRuntimeException ex) {
			ex.printStackTrace();
		} catch (CASException ex) {
			ex.printStackTrace();
		}

		super.entityProcessComplete(aCas, aStatus);
		
		
	}

	
	private MatchState compareAnnotationSpans(Annotation a1, Annotation a2) {
		if (a1 == null || a2 == null) {
			return MatchState.NONE;
		}

		if (AnnotationLibrarian.coversSameSpan(a1, a2)) {
			/** Exact, Exact **/
			return MatchState.EXACT;
		} else if (AnnotationLibrarian.overlaps(a1, a2)) {
			/** Overlap, Overlap **/
			return MatchState.OVERLAP;
		}

		return MatchState.NONE;
	}

	/**
	 * CollectionProcessComplete event thrown when the processing of the entire document
	 * collection has been completed.  In this case we simply close the connection.
	 *
	 * @param aStatus Status object that contains the exception if one is thrown
	 */
	public void collectionProcessComplete(EntityProcessStatus aStatus) {
		//System.out.println(knowtatorStats);
		LOG.info(knowtatorStats);
		LOG.info(nlpStats);
		LOG.info(" Total Knowtator Relationships=\t" + totalKnowtatorRelationships
		    + "\t ::Total NLP Relationships=\t" + totalNlpRelationships);
		LOG.info("Nlp no anatomy=\t" + nlpNoAnatomy + "\t :: Knowtator no anatomy=\t"
		    + knowtatorNoAnatomy);
	}//collectionProcessComplete method

	/**
	 * Returns a snippet of text around the annotation.
	 * @param annotation  the annotation to get the snippet for.
	 * @param windowSize  the number of characters to get before and after the annotation. A window size of 50 will
	 *                    return 100 + annotation length characters (50 on each side of the annotation + the annotation text);
	 * @return            A string of windowSize + annotation text + window size text.
	 */
	public String getSnippet(Annotation annotation, int windowSize) {
		int startIndex = 0;
		int endIndex = annotation.getEnd() + windowSize;

		if (annotation.getBegin() > windowSize) {
			startIndex = annotation.getBegin() - windowSize;
		}

		if (endIndex > annotation.getCAS().getDocumentText().length()) {
			endIndex = annotation.getCAS().getDocumentText().length();
		}
		return annotation.getCAS().getDocumentText().substring(startIndex, endIndex);
	}

	/**
	 * Class for holding stats about a run.
	 */
	public class StatClass {
		public long eEE;
		public long eEI;
		public long oOE;
		public long oOI;
		public long matchNotFound;

		String name;

		public StatClass(String name) {
			this.name = name;
		}

		public void addStat(MatchState anatomyMatch, MatchState measurementMatch, boolean typesMatch) {
			if (anatomyMatch == MatchState.EXACT && measurementMatch == MatchState.EXACT && typesMatch) {
				this.eEE++;
				this.eEI++;
				this.oOE++;
				this.oOI++;
			} else if (anatomyMatch == MatchState.EXACT && measurementMatch == MatchState.EXACT && !typesMatch) {
				this.eEI++;
				this.oOI++;
			} else if (anatomyMatch == MatchState.OVERLAP && measurementMatch == MatchState.OVERLAP && typesMatch) {
				this.oOE++;
				this.oOI++;
			} else if (anatomyMatch == MatchState.OVERLAP && measurementMatch == MatchState.OVERLAP && !typesMatch) {
				this.oOI++;
			}
		}

		

	}

	public enum MatchState {
		EXACT(true), OVERLAP(true), NONE(false);

		boolean matched;

		MatchState(boolean matched) {
			this.matched = matched;
		}
	}
}