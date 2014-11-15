

/* First created by JCasGen Fri Nov 14 17:40:11 CST 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Nov 14 17:40:11 CST 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/3/leoTypeDescription_25d12eba-6c16-41f4-83e1-123f95ced0f21157784217105982276.xml
 * @generated */
public class Output_Value extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Output_Value.class);
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
  protected Output_Value() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Output_Value(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Output_Value(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Output_Value(JCas jcas, int begin, int end) {
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
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: valueAnnotation

  /** getter for valueAnnotation - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getValueAnnotation() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_valueAnnotation == null)
      jcasType.jcas.throwFeatMissing("valueAnnotation", "gov.va.vinci.vitals.types.Output_Value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_valueAnnotation)));}
    
  /** setter for valueAnnotation - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValueAnnotation(Annotation v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_valueAnnotation == null)
      jcasType.jcas.throwFeatMissing("valueAnnotation", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_valueAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: concept

  /** getter for concept - gets 
   * @generated
   * @return value of the feature 
   */
  public String getConcept() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_concept == null)
      jcasType.jcas.throwFeatMissing("concept", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_concept);}
    
  /** setter for concept - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setConcept(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_concept == null)
      jcasType.jcas.throwFeatMissing("concept", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_concept, v);}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getUnit() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Output_Value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_unit)));}
    
  /** setter for unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(Annotation v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_unit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: timestamp

  /** getter for timestamp - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getTimestamp() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Output_Value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_timestamp)));}
    
  /** setter for timestamp - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimestamp(Annotation v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_timestamp, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    