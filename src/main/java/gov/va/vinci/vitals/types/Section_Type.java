
/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Section Type
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * @generated */
public class Section_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Section.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Section");
 
  /** @generated */
  final Feature casFeat_SectionHeader;
  /** @generated */
  final int     casFeatCode_SectionHeader;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSectionHeader(int addr) {
        if (featOkTst && casFeat_SectionHeader == null)
      jcas.throwFeatMissing("SectionHeader", "gov.va.vinci.vitals.types.Section");
    return ll_cas.ll_getRefValue(addr, casFeatCode_SectionHeader);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSectionHeader(int addr, int v) {
        if (featOkTst && casFeat_SectionHeader == null)
      jcas.throwFeatMissing("SectionHeader", "gov.va.vinci.vitals.types.Section");
    ll_cas.ll_setRefValue(addr, casFeatCode_SectionHeader, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SectionHeaderText;
  /** @generated */
  final int     casFeatCode_SectionHeaderText;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSectionHeaderText(int addr) {
        if (featOkTst && casFeat_SectionHeaderText == null)
      jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SectionHeaderText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSectionHeaderText(int addr, String v) {
        if (featOkTst && casFeat_SectionHeaderText == null)
      jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_SectionHeaderText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Term;
  /** @generated */
  final int     casFeatCode_Term;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTerm(int addr) {
        if (featOkTst && casFeat_Term == null)
      jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Term);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTerm(int addr, String v) {
        if (featOkTst && casFeat_Term == null)
      jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_Term, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Snippet;
  /** @generated */
  final int     casFeatCode_Snippet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSnippet(int addr) {
        if (featOkTst && casFeat_Snippet == null)
      jcas.throwFeatMissing("Snippet", "gov.va.vinci.vitals.types.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Snippet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSnippet(int addr, String v) {
        if (featOkTst && casFeat_Snippet == null)
      jcas.throwFeatMissing("Snippet", "gov.va.vinci.vitals.types.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_Snippet, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Section_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_SectionHeader = jcas.getRequiredFeatureDE(casType, "SectionHeader", "uima.tcas.Annotation", featOkTst);
    casFeatCode_SectionHeader  = (null == casFeat_SectionHeader) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SectionHeader).getCode();

 
    casFeat_SectionHeaderText = jcas.getRequiredFeatureDE(casType, "SectionHeaderText", "uima.cas.String", featOkTst);
    casFeatCode_SectionHeaderText  = (null == casFeat_SectionHeaderText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SectionHeaderText).getCode();

 
    casFeat_Term = jcas.getRequiredFeatureDE(casType, "Term", "uima.cas.String", featOkTst);
    casFeatCode_Term  = (null == casFeat_Term) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Term).getCode();

 
    casFeat_Snippet = jcas.getRequiredFeatureDE(casType, "Snippet", "uima.cas.String", featOkTst);
    casFeatCode_Snippet  = (null == casFeat_Snippet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Snippet).getCode();

  }
}



    