

/* First created by JCasGen Mon Sep 22 23:11:26 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Sep 22 23:11:26 CDT 2014
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/4/leoTypeDescription_e3440b51-a485-49b2-876c-ad489b86a2d82000585658972327005.xml
 * @generated */
public class Hr_value extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Hr_value.class);
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
  protected Hr_value() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Hr_value(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Hr_value(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Hr_value(JCas jcas, int begin, int end) {
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
  //* Feature: Value

  /** getter for Value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (Hr_value_Type.featOkTst && ((Hr_value_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Hr_value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Hr_value_Type)jcasType).casFeatCode_Value);}
    
  /** setter for Value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (Hr_value_Type.featOkTst && ((Hr_value_Type)jcasType).casFeat_Value == null)
      jcasType.jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Hr_value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Hr_value_Type)jcasType).casFeatCode_Value, v);}    
   
    
  //*--------------*
  //* Feature: Unit

  /** getter for Unit - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getUnit() {
    if (Hr_value_Type.featOkTst && ((Hr_value_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Hr_value");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Hr_value_Type)jcasType).casFeatCode_Unit)));}
    
  /** setter for Unit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUnit(Annotation v) {
    if (Hr_value_Type.featOkTst && ((Hr_value_Type)jcasType).casFeat_Unit == null)
      jcasType.jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Hr_value");
    jcasType.ll_cas.ll_setRefValue(addr, ((Hr_value_Type)jcasType).casFeatCode_Unit, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    