
/* First created by JCasGen Fri May 01 12:38:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import gov.va.vinci.leo.context.types.Context_Type;

/** Context type
 * Updated by JCasGen Fri May 01 12:38:40 CDT 2020
 * @generated */
public class TermContext_Type extends Context_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TermContext.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.TermContext");
 
  /** @generated */
  final Feature casFeat_Section;
  /** @generated */
  final int     casFeatCode_Section;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSection(int addr) {
        if (featOkTst && casFeat_Section == null)
      jcas.throwFeatMissing("Section", "gov.va.vinci.vitals.types.TermContext");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Section);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSection(int addr, String v) {
        if (featOkTst && casFeat_Section == null)
      jcas.throwFeatMissing("Section", "gov.va.vinci.vitals.types.TermContext");
    ll_cas.ll_setStringValue(addr, casFeatCode_Section, v);}
    
  
 
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
      jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.TermContext");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SectionHeaderText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSectionHeaderText(int addr, String v) {
        if (featOkTst && casFeat_SectionHeaderText == null)
      jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.TermContext");
    ll_cas.ll_setStringValue(addr, casFeatCode_SectionHeaderText, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TermContext_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Section = jcas.getRequiredFeatureDE(casType, "Section", "uima.cas.String", featOkTst);
    casFeatCode_Section  = (null == casFeat_Section) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Section).getCode();

 
    casFeat_SectionHeaderText = jcas.getRequiredFeatureDE(casType, "SectionHeaderText", "uima.cas.String", featOkTst);
    casFeatCode_SectionHeaderText  = (null == casFeat_SectionHeaderText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SectionHeaderText).getCode();

  }
}



    