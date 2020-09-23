

/* First created by JCasGen Fri May 01 12:38:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import gov.va.vinci.leo.context.types.Context;


/** Context type
 * Updated by JCasGen Fri May 01 12:38:40 CDT 2020
 * XML source: C:/Users/VHE850~1/AppData/Local/Temp/6/leoTypeDescription_79d17792-8545-4888-a7a9-e02949e7efb01819319309204041246.xml
 * @generated */
public class TermContext extends Context {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TermContext.class);
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
  protected TermContext() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TermContext(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TermContext(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TermContext(JCas jcas, int begin, int end) {
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
  //* Feature: Section

  /** getter for Section - gets Section header
   * @generated
   * @return value of the feature 
   */
  public String getSection() {
    if (TermContext_Type.featOkTst && ((TermContext_Type)jcasType).casFeat_Section == null)
      jcasType.jcas.throwFeatMissing("Section", "gov.va.vinci.vitals.types.TermContext");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TermContext_Type)jcasType).casFeatCode_Section);}
    
  /** setter for Section - sets Section header 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSection(String v) {
    if (TermContext_Type.featOkTst && ((TermContext_Type)jcasType).casFeat_Section == null)
      jcasType.jcas.throwFeatMissing("Section", "gov.va.vinci.vitals.types.TermContext");
    jcasType.ll_cas.ll_setStringValue(addr, ((TermContext_Type)jcasType).casFeatCode_Section, v);}    
   
    
  //*--------------*
  //* Feature: SectionHeaderText

  /** getter for SectionHeaderText - gets Section header
   * @generated
   * @return value of the feature 
   */
  public String getSectionHeaderText() {
    if (TermContext_Type.featOkTst && ((TermContext_Type)jcasType).casFeat_SectionHeaderText == null)
      jcasType.jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.TermContext");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TermContext_Type)jcasType).casFeatCode_SectionHeaderText);}
    
  /** setter for SectionHeaderText - sets Section header 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSectionHeaderText(String v) {
    if (TermContext_Type.featOkTst && ((TermContext_Type)jcasType).casFeat_SectionHeaderText == null)
      jcasType.jcas.throwFeatMissing("SectionHeaderText", "gov.va.vinci.vitals.types.TermContext");
    jcasType.ll_cas.ll_setStringValue(addr, ((TermContext_Type)jcasType).casFeatCode_SectionHeaderText, v);}    
  }

    