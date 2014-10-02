

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
public class T_value extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(T_value.class);
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
  protected T_value() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public T_value(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public T_value(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public T_value(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.T_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((T_value_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.T_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((T_value_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getUnit() {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.T_value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((T_value_Type)jcasType).casFeatCode_unit)));}
    
  /** setter for unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(Annotation v) {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.T_value");
    jcasType.ll_cas.ll_setRefValue(addr, ((T_value_Type)jcasType).casFeatCode_unit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.T_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((T_value_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (T_value_Type.featOkTst && ((T_value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.T_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((T_value_Type)jcasType).casFeatCode_source, v);}    
  }

    