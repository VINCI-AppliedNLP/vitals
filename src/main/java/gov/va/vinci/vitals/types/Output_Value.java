

/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * XML source: C:/Users/VHE850~1/AppData/Local/Temp/3/leoTypeDescription_a77d34c5-1212-4303-982b-d7a6472e86994599447137304841678.xml
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
  //* Feature: unitString

  /** getter for unitString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getUnitString() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_unitString == null)
      jcasType.jcas.throwFeatMissing("unitString", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_unitString);}
    
  /** setter for unitString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnitString(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_unitString == null)
      jcasType.jcas.throwFeatMissing("unitString", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_unitString, v);}    
   
    
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
   
    
  //*--------------*
  //* Feature: timestampString

  /** getter for timestampString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTimestampString() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_timestampString == null)
      jcasType.jcas.throwFeatMissing("timestampString", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_timestampString);}
    
  /** setter for timestampString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimestampString(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_timestampString == null)
      jcasType.jcas.throwFeatMissing("timestampString", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_timestampString, v);}    
   
    
  //*--------------*
  //* Feature: section

  /** getter for section - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getSection() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_section == null)
      jcasType.jcas.throwFeatMissing("section", "gov.va.vinci.vitals.types.Output_Value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_section)));}
    
  /** setter for section - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSection(Annotation v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_section == null)
      jcasType.jcas.throwFeatMissing("section", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Output_Value_Type)jcasType).casFeatCode_section, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sectionType

  /** getter for sectionType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSectionType() {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_sectionType == null)
      jcasType.jcas.throwFeatMissing("sectionType", "gov.va.vinci.vitals.types.Output_Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_sectionType);}
    
  /** setter for sectionType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSectionType(String v) {
    if (Output_Value_Type.featOkTst && ((Output_Value_Type)jcasType).casFeat_sectionType == null)
      jcasType.jcas.throwFeatMissing("sectionType", "gov.va.vinci.vitals.types.Output_Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Output_Value_Type)jcasType).casFeatCode_sectionType, v);}    
  }

    