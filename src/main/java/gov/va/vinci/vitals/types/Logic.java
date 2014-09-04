

/* First created by JCasGen Thu Sep 04 16:25:34 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Sep 04 16:25:34 CDT 2014
 * XML source: C:/DOCUME~1/VH813C~1/LOCALS~1/Temp/5/leoTypeDescription_d041bc56-47e2-4ef3-9cb8-a9df965f16c63306118127274868107.xml
 * @generated */
public class Logic extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Logic.class);
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
  protected Logic() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Logic(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Logic(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Logic(JCas jcas, int begin, int end) {
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
  //* Feature: VitalType

  /** getter for VitalType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getVitalType() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalType == null)
      jcasType.jcas.throwFeatMissing("VitalType", "gov.va.vinci.vitals.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalType);}
    
  /** setter for VitalType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVitalType(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalType == null)
      jcasType.jcas.throwFeatMissing("VitalType", "gov.va.vinci.vitals.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalType, v);}    
   
    
  //*--------------*
  //* Feature: VitalTerm

  /** getter for VitalTerm - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getVitalTerm() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalTerm == null)
      jcasType.jcas.throwFeatMissing("VitalTerm", "gov.va.vinci.vitals.types.Logic");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalTerm)));}
    
  /** setter for VitalTerm - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVitalTerm(Annotation v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalTerm == null)
      jcasType.jcas.throwFeatMissing("VitalTerm", "gov.va.vinci.vitals.types.Logic");
    jcasType.ll_cas.ll_setRefValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalTerm, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: VitalValue

  /** getter for VitalValue - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getVitalValue() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalValue == null)
      jcasType.jcas.throwFeatMissing("VitalValue", "gov.va.vinci.vitals.types.Logic");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalValue)));}
    
  /** setter for VitalValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVitalValue(Annotation v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_VitalValue == null)
      jcasType.jcas.throwFeatMissing("VitalValue", "gov.va.vinci.vitals.types.Logic");
    jcasType.ll_cas.ll_setRefValue(addr, ((Logic_Type)jcasType).casFeatCode_VitalValue, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: ValueString

  /** getter for ValueString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValueString() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_ValueString == null)
      jcasType.jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_ValueString);}
    
  /** setter for ValueString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValueString(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_ValueString == null)
      jcasType.jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_ValueString, v);}    
   
    
  //*--------------*
  //* Feature: Unit

  /** getter for Unit - gets 
   * @generated
   * @return value of the feature 
   */
  public String getUnit() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Unit);}
    
  /** setter for Unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Unit, v);}    
  }

    