

/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Section Type
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * XML source: C:/Users/VHE850~1/AppData/Local/Temp/3/leoTypeDescription_a77d34c5-1212-4303-982b-d7a6472e86994599447137304841678.xml
 * @generated */
public class Section extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Section.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Section() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Section(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Section(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Section(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: SectionHeader

  /** getter for SectionHeader - gets Anchor annotation around which the section was created
   * @generated
   * @return value of the feature 
   */
  public Annotation getSectionHeader() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_SectionHeader == null)
      jcasType.jcas.throwFeatMissing("SectionHeader", "gov.va.vinci.vitals.types.Section");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Section_Type)jcasType).casFeatCode_SectionHeader)));}
    
  /** setter for SectionHeader - sets Anchor annotation around which the section was created 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSectionHeader(Annotation v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_SectionHeader == null)
      jcasType.jcas.throwFeatMissing("SectionHeader", "gov.va.vinci.vitals.types.Section");
    jcasType.ll_cas.ll_setRefValue(addr, ((Section_Type)jcasType).casFeatCode_SectionHeader, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: SectionHeaderText

  /** getter for SectionHeaderText - gets text of the header
   * @generated
   * @return value of the feature 
   */
  public String getSectionHeaderText() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_SectionHeaderText == null)
      jcasType.jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.Section");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Section_Type)jcasType).casFeatCode_SectionHeaderText);}
    
  /** setter for SectionHeaderText - sets text of the header 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSectionHeaderText(String v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_SectionHeaderText == null)
      jcasType.jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.Section");
    jcasType.ll_cas.ll_setStringValue(addr, ((Section_Type)jcasType).casFeatCode_SectionHeaderText, v);}    
   
    
  //*--------------*
  //* Feature: Term

  /** getter for Term - gets text of the header
   * @generated
   * @return value of the feature 
   */
  public String getTerm() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_Term == null)
      jcasType.jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Section");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Section_Type)jcasType).casFeatCode_Term);}
    
  /** setter for Term - sets text of the header 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTerm(String v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_Term == null)
      jcasType.jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Section");
    jcasType.ll_cas.ll_setStringValue(addr, ((Section_Type)jcasType).casFeatCode_Term, v);}    
   
    
  //*--------------*
  //* Feature: Snippet

  /** getter for Snippet - gets text
   * @generated
   * @return value of the feature 
   */
  public String getSnippet() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_Snippet == null)
      jcasType.jcas.throwFeatMissing("Snippet", "gov.va.vinci.vitals.types.Section");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Section_Type)jcasType).casFeatCode_Snippet);}
    
  /** setter for Snippet - sets text 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSnippet(String v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_Snippet == null)
      jcasType.jcas.throwFeatMissing("Snippet", "gov.va.vinci.vitals.types.Section");
    jcasType.ll_cas.ll_setStringValue(addr, ((Section_Type)jcasType).casFeatCode_Snippet, v);}    
  }

    