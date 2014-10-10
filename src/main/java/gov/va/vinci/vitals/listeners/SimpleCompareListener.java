package gov.va.vinci.vitals.listeners;

import org.apache.uima.cas.*;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.jcas.tcas.Annotation;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.listener.BaseCsvListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleCompareListener extends BaseCsvListener {
	public static HashMap<String, Integer> tpCount_au = new HashMap<String, Integer>();;
	public static HashMap<String, Integer> tpCount_sys = new HashMap<String, Integer>();;
	public static HashMap<String, Integer> fpCount_sys = new HashMap<String, Integer>();;
	public static HashMap<String, Integer> fnCount_au = new HashMap<String, Integer>();
	public static HashMap<String, Integer> totalCount = new HashMap<String, Integer>();;
	/**
	 * The type name this annotation listener is limited to.
	 */
	protected HashMap<String, String> auSysMap;

	/**
	 * 
	 * @param gold
	 * @param sys
	 * @throws FileNotFoundException 
	 */
	public SimpleCompareListener(HashMap<String, String> goldSysMap, File file) throws FileNotFoundException {
		super(file);
		this.auSysMap = goldSysMap;
		for (java.util.Map.Entry<String, String> a : auSysMap.entrySet()) {
			tpCount_au.put(a.getKey(), 0);
			tpCount_sys.put(a.getValue(), 0);
			fpCount_sys.put(a.getValue(), 0);
			fnCount_au.put(a.getKey(), 0);
			totalCount.put(a.getKey(), 0);
			totalCount.put(a.getValue(), 0);
		}
	}

	@Override
	public void collectionProcessComplete(EntityProcessStatus aStatus) {
		// TODO Auto-generated method stub
		super.collectionProcessComplete(aStatus);
		System.out.println("TP:" + tpCount_au);
		System.out.println("TP:" + tpCount_sys);
		System.out.println("FP:" + fpCount_sys);
		System.out.println("FN:" + fnCount_au);
		System.out.println("Totals:" + totalCount);
	}

	public static String[] outTP(Annotation a, String referenceID, String type) {
		if (tpCount_au.containsKey(type)) {
			int i = tpCount_au.get(type);
			i++;
			tpCount_au.put(type, i);
		} else if (tpCount_sys.containsKey(type)) {
			int i = tpCount_sys.get(type);
			i++;
			tpCount_sys.put(type, i);
		} else {
			System.out.println("Processing error " + referenceID + " " + type + " TP map");
		}
		return new String[] { referenceID, type, " TP ", "" + a.getBegin(), "" + a.getEnd(), a.getCoveredText() };

	}

	public static String[] outFP(Annotation a, String referenceID, String type) {
		if (fpCount_sys.containsKey(type)) {
			int i = fpCount_sys.get(type);
			i++;
			fpCount_sys.put(type, i);
		} else {
			System.out.println("Processing error " + referenceID + " " + type + " FP map");
		}
		return new String[] { referenceID, type, " FP ", "" + a.getBegin(), "" + a.getEnd(), a.getCoveredText() };
	}

	public static String[] outFN(Annotation a, String referenceID, String type) {

		if (fnCount_au.containsKey(type)) {
			int i = fnCount_au.get(type);
			i++;
			fnCount_au.put(type, i);
		} else {
			System.out.println("Processing error " + referenceID + " " + type + " FN map");
		}
		return new String[] { referenceID, type, " FN ", "" + a.getBegin(), "" + a.getEnd(), a.getCoveredText() };
	}

	@Override
	protected List<String[]> getRows(CAS cas) {
		ArrayList<String[]> rows = new ArrayList<String[]>();
		for (String auType : auSysMap.keySet()) {
			String toolType = auSysMap.get(auType);
			
			try {
				ArrayList<Annotation> auAnns = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(	    cas.getJCas(), auType);
				ArrayList<Annotation> toolAnns = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(				    cas.getJCas(), toolType);
				if (auAnns.size() == 0 && toolAnns.size() == 0) {
					continue;
				}
				else {
					if (totalCount.containsKey(auType)) {
						int i = totalCount.get(auType);
						i = i + auAnns.size();
						totalCount.put(auType, i);
					} else {
						System.out.println("Processing error " + referenceID + " " + auType + " total map");
					}
					if (totalCount.containsKey(toolType)) {
						int i = totalCount.get(toolType);
						i = i + toolAnns.size();
						totalCount.put(toolType, i);
					} else {
						System.out.println("Processing error " + referenceID + " " + toolType + " total map");
					}

					//	System.out.println(referenceID + " count au:" + auType + " -- " + auAnns.size() + " tool: "					    + toolType + " -- " + toolAnns.size());
					if (auAnns.size() > 0) {
						if (toolAnns.size() > 0) {
							for (Annotation au : auAnns) {
								if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(au, toolType).size() > 0) {
									rows.add(outTP(au, referenceID, auType));
								} else {
									// FN case.
									rows.add(outFN(au, referenceID, auType));
								}
							}
							for (Annotation tool : toolAnns) {
								if (AnnotationLibrarian.getAllOverlappingAnnotationsOfType(tool, auType).size() > 0) {
									// TP case.
									rows.add(outTP(tool, referenceID, toolType));
								} else {
									// FP case.
									rows.add(outFP(tool, referenceID, toolType));
								}
							}
						} else {
							// auAnns > 0, but toolAnns == 0. All auAnns are FN
							for (Annotation au : auAnns) {
								rows.add(outFN(au, referenceID, auType));
							}
						} // end auAnns > 0
					} else { // auAnns == 0
						// toolAnns > 0, all toolAnns are FP
						for (Annotation tool : toolAnns) {
							rows.add(outFP(tool, referenceID, toolType));
						}
					}
				}
			} catch (CASException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	protected String[] getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
