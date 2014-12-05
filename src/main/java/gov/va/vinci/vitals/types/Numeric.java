

/* First created by JCasGen Fri Dec 05 00:31:14 CST 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import gov.va.vinci.leo.regex.types.RegularExpressionType;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Dec 05 00:31:14 CST 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/3/leoTypeDescription_838ce1f1-3764-412e-965a-87430202e7108032335724053080453.xml
 * @generated */
public class Numeric extends RegularExpressionType {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Numeric.class);
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
  protected Numeric() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Numeric(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Numeric(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Numeric(JCas jcas, int begin, int end) {
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
  //* Feature: comment

  /** getter for comment - gets 
   * @generated
   * @return value of the feature 
   */
  public String getComment() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_comment == null)
      jcasType.jcas.throwFeatMissing("comment", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Numeric_Type)jcasType).casFeatCode_comment);}
    
  /** setter for comment - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setComment(String v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_comment == null)
      jcasType.jcas.throwFeatMissing("comment", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setStringValue(addr, ((Numeric_Type)jcasType).casFeatCode_comment, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public double getValue() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Numeric_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(double v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Numeric_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: decimal

  /** getter for decimal - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getDecimal() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_decimal == null)
      jcasType.jcas.throwFeatMissing("decimal", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_decimal);}
    
  /** setter for decimal - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDecimal(boolean v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_decimal == null)
      jcasType.jcas.throwFeatMissing("decimal", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_decimal, v);}    
   
    
  //*--------------*
  //* Feature: integer

  /** getter for integer - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getInteger() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_integer == null)
      jcasType.jcas.throwFeatMissing("integer", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_integer);}
    
  /** setter for integer - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setInteger(boolean v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_integer == null)
      jcasType.jcas.throwFeatMissing("integer", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_integer, v);}    
   
    
  //*--------------*
  //* Feature: zero_decimal

  /** getter for zero_decimal - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getZero_decimal() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_zero_decimal == null)
      jcasType.jcas.throwFeatMissing("zero_decimal", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_zero_decimal);}
    
  /** setter for zero_decimal - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setZero_decimal(boolean v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_zero_decimal == null)
      jcasType.jcas.throwFeatMissing("zero_decimal", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Numeric_Type)jcasType).casFeatCode_zero_decimal, v);}    
   
    
  //*--------------*
  //* Feature: unit

  /** getter for unit - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getUnit() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Numeric");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Numeric_Type)jcasType).casFeatCode_unit)));}
    
  /** setter for unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(Annotation v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_unit == null)
      jcasType.jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setRefValue(addr, ((Numeric_Type)jcasType).casFeatCode_unit, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Numeric");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Numeric_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setStringValue(addr, ((Numeric_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: timestamp

  /** getter for timestamp - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getTimestamp() {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Numeric");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Numeric_Type)jcasType).casFeatCode_timestamp)));}
    
  /** setter for timestamp - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimestamp(Annotation v) {
    if (Numeric_Type.featOkTst && ((Numeric_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Numeric");
    jcasType.ll_cas.ll_setRefValue(addr, ((Numeric_Type)jcasType).casFeatCode_timestamp, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    