

/* First created by JCasGen Thu Oct 02 11:45:54 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Oct 02 11:45:54 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/2/leoTypeDescription_4f22997c-3657-40bb-8c82-38d7ee0651362019680910730625302.xml
 * @generated */
public class Bp_value extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Bp_value.class);
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
  protected Bp_value() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Bp_value(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Bp_value(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Bp_value(JCas jcas, int begin, int end) {
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
  //* Feature: systolicValue

  /** getter for systolicValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSystolicValue() {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_systolicValue == null)
      jcasType.jcas.throwFeatMissing("systolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_systolicValue);}
    
  /** setter for systolicValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSystolicValue(String v) {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_systolicValue == null)
      jcasType.jcas.throwFeatMissing("systolicValue", "gov.va.vinci.vitals.types.Bp_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_systolicValue, v);}    
   
    
  //*--------------*
  //* Feature: diastolicValue

  /** getter for diastolicValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDiastolicValue() {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_diastolicValue == null)
      jcasType.jcas.throwFeatMissing("diastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_diastolicValue);}
    
  /** setter for diastolicValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDiastolicValue(String v) {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_diastolicValue == null)
      jcasType.jcas.throwFeatMissing("diastolicValue", "gov.va.vinci.vitals.types.Bp_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_diastolicValue, v);}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getUnit() {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Bp_value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Bp_value_Type)jcasType).casFeatCode_unit)));}
    
  /** setter for unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(Annotation v) {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Bp_value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Bp_value_Type)jcasType).casFeatCode_unit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Bp_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (Bp_value_Type.featOkTst && ((Bp_value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Bp_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Bp_value_Type)jcasType).casFeatCode_source, v);}    
  }

    